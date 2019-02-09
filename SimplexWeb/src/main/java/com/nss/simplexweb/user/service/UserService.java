package com.nss.simplexweb.user.service;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nss.simplexweb.enums.ROLE;
import com.nss.simplexweb.user.model.Role;
import com.nss.simplexweb.user.model.User;
import com.nss.simplexweb.user.repository.RoleRepository;
import com.nss.simplexweb.user.repository.UserRepository;
import com.nss.simplexweb.utility.Utility;
import com.nss.simplexweb.utility.mail.EmailController;

@Service("userService")
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private EmailController emailController;
	
	@Autowired
	public UserService(UserRepository userRepository,
			RoleRepository roleRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public User saveDistributer(User user) {
		User originalUserBean = (User)Utility.deepClone(user);
		
		if(user.getPassword() == null) {
			user.setPassword(Utility.generateRandomPassword(8));
			originalUserBean.setPassword(user.getPassword());
		}
		user.setIsActive(1);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRole(predictRoleAbbrByCompanyName(user.getCompanyName()));		
        User savedUser =  userRepository.save(user);
        
        //Send registration mail
        if(savedUser != null) {
        	emailController.sendRegistrationEmail(originalUserBean);
        }
        
        return savedUser;
    }
	
	public User saveEmployee(User user) {
		User originalUserBean = (User)Utility.deepClone(user);
		
		if(user.getPassword() == null) {
			user.setPassword(Utility.generateRandomPassword(8));
			originalUserBean.setPassword(user.getPassword());
		}
		user.setIsActive(1);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRole(roleRepository.findByRoleId(user.getRole().getRoleId()));		
        return userRepository.save(user);
	}
	
	private Role predictRoleAbbrByCompanyName(String companyName) {
		String roleAbbr = ROLE.DIST.name();	//By default, Distributer role will be set on signup
		
		/*switch (companyName.toLowerCase()) {
		case "simplex":
			roleAbbr = ROLE.DIR.name();
			break;
		default:
			break;
		}*/
		
		return roleRepository.findByRoleAbbr(roleAbbr);
	}

	public @Valid User processUseNameBeforeSaving(@Valid User user) {
		String userFullName  = user.getFullName();
		String[] names = userFullName.split("\\s+");
		System.out.println();
		
		if(names.length > 1) {	//John Adam Doe or John Doe
			user.setFirstName(names[0]);
			user.setLastName(names[names.length-1]);
		}else {	//John
			user.setFirstName(names[0]);
		}
		user.setFullName((user.getFirstName().concat(" ").concat(user.getLastName() == null ? "" : user.getLastName())).trim());
		return user;
	}

	public ArrayList<User> getAllUsers() {
		ArrayList<User> userList = (ArrayList<User>) userRepository.findAll();
		return userList;
	}

	//Role Level Hierarchy
	public ArrayList<User> getAllEmployeesUnderMe(long roleId) {
		ArrayList<User> userList = null;
		ArrayList<Role> childRoles = roleService.getAllLevelChildRolesById(roleId);
		System.out.println("childRoles : " + childRoles);
		if(childRoles != null) {
			for(Role role : childRoles) {
				if(userList == null)
					userList = new ArrayList<>();
				userList.addAll(userRepository.findByRoleAndEmpIdIsNotNull(role));
			}
		}
		return userList;
	}
	
	//User Level Hierarchy
	public ArrayList<User> getAllUsersImmediatelyUnderMe(long currentUserId) {
		return null;
	}

	public User findUserByUserId(long userId) {
		return userRepository.findByUserId(userId);
	}

	public User updateEmployee(User user) {
		User oldUserData = userRepository.findByUserId(user.getUserId());
		user.setIsActive(1);
		if (user.getPassword() == null) {
		    user.setPassword(oldUserData.getPassword());
		}else if(bCryptPasswordEncoder.matches(oldUserData.getPassword(), user.getPassword())){
			user.setPassword(oldUserData.getPassword());
		}else {
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		}
		return userRepository.save(user);
	}

	public Object getAllDistributersList() {
		return userRepository.findByRoleAndEmpIdIsNull(roleRepository.findByRoleAbbr(ROLE.DIST.name()));
	}

	public User getUserById(Long userId) {
		return userRepository.findByUserId(userId);
	}

	public void emailNewPassword(User userExists) {
		
	}
	
	public User deleteUser(Long userid) {
		User user = userRepository.findByUserId(userid);
		if(user != null) {
			userRepository.deleteById(userid);
		}
		return user;
	}

	public User updateDistributer(User user) {
		User oldUserData = userRepository.findByUserId(user.getUserId());
		user.setIsActive(1);
		if (user.getPassword() == null) {
		    user.setPassword(oldUserData.getPassword());
		}else if(bCryptPasswordEncoder.matches(oldUserData.getPassword(), user.getPassword())){
			user.setPassword(oldUserData.getPassword());
		}else {
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		}
		return userRepository.save(user);
	}

	public User findUserByEmailAndPassword(String email, String password) {
		User user = userRepository.findByEmail(email);
		if(user != null) {
			if(bCryptPasswordEncoder.matches(password, user.getPassword())) {
				return user;
			}
		}
		return null;
	}

	public boolean checkIfUserExists(User userDetails) {
		try {
			if(userRepository.findById(userDetails.getUserId()) != null) {
				return true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean doesPasswordExist(User existingUser, String password) {
		if(bCryptPasswordEncoder.matches(password, existingUser.getPassword())) {
			return true;
		}
		return false;
	}

	public User updatePassword(User existingUser, String password) {
		User originalUserBean = (User)Utility.deepClone(existingUser);
		originalUserBean.setPassword(password);
		
		existingUser.setIsActive(1);
		existingUser.setPassword(bCryptPasswordEncoder.encode(password));
        User savedUser = userRepository.save(existingUser);
        if(savedUser != null) {
        	emailController.sendRegistrationEmail(originalUserBean);
        }
        return savedUser;
	}

	public User saveNewEmployee(User user) {
		if(user.getPassword() == null) {
			user.setPassword(Utility.generateRandomPassword(8));
		}
		user.setIsActive(1);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRole(roleRepository.findByRoleId(user.getRole().getRoleId()));		
        return userRepository.save(user);
	}
}
