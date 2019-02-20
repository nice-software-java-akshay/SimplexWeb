package com.nss.simplexweb.user.service;

import java.util.ArrayList;

import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nss.simplexweb.enums.ROLE;
import com.nss.simplexweb.user.model.Role;
import com.nss.simplexweb.user.model.User;
import com.nss.simplexweb.user.repository.RoleRepository;
import com.nss.simplexweb.user.repository.UserRepository;
import com.nss.simplexweb.user.service.intf.UserServiceInterface;
import com.nss.simplexweb.utility.Utility;
import com.nss.simplexweb.utility.mail.EmailController;

@Service("userService")
public class UserService implements UserServiceInterface {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DistributerService distributerService;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private EmailController emailController;
	
	@Value("${file.user.profile.image}")
	private String PROFILE_PICTURE_UPLOADED_FOLDER_PATH;
	
	@Autowired
	public UserService(UserRepository userRepository,
			RoleRepository roleRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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

	public void uploadMyProfilePictureDistributer(MultipartFile file, User currentUser) {
		//Set values to file attr
		//String UPLOADED_FOLDER_PATH = currentUser.getProfilePicFolderpath();
		String UPLOAD_FILE_NAME = currentUser.getFullName()
									.replaceAll("\\s", "_").concat("_")
									.concat(Utility.generateRandomPassword(10))
									.concat('.' + FilenameUtils.getExtension(file.getOriginalFilename()));
		
		//Set value to user bean
		currentUser.setProfilePicFolderpath(PROFILE_PICTURE_UPLOADED_FOLDER_PATH);
		currentUser.setProfilePicFilename(UPLOAD_FILE_NAME);
		
		//Save
		Utility.singleFileUpload(file, PROFILE_PICTURE_UPLOADED_FOLDER_PATH, UPLOAD_FILE_NAME);
		distributerService.updateMyProfilePictureDistributer(currentUser);
	}

	@Override
	public User findUserByUserId(Long userId) {
		// TODO Auto-generated method stub
		return userRepository.findByUserId(userId);
	}

	@Override
	public User findUserByEmailId(String emailId) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(emailId);
	}
	
	@Override
	public User findUserByEmailIdAndIsActive(String emailId) {
		// TODO Auto-generated method stub
		return userRepository.findByEmailAndIsActive(emailId, 1);
	}
	
	@Override
	public boolean checkIfUserExistsByUserId(Long userId) {
		// TODO Auto-generated method stub
		if(userRepository.findByUserId(userId) != null) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean checkIfUserExistsByEmailId(String emailId) {
		// TODO Auto-generated method stub
		if(userRepository.findByEmail(emailId) != null) {
			return true;
		}
		return false;
	}

	@Override
	public User findUserByEmailIdAndNonEncrPassword(String emailId, String password) {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmailAndIsActive(emailId, 1);
		if (user != null) {
			if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
				return user;
			}
		}
		return null;
	}

	@Override
	public User findUserByEmailIdAndEncrPassword(String emailId, String password) {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmailAndPasswordAndIsActive(emailId, password, 1);
		if (user != null) {
			return user;
		}
		return null;
	}

	@Override
	public ArrayList<User> findAllActiveUsersList() {
		// TODO Auto-generated method stub
		return userRepository.findByIsActive(1);
	}

	@Override
	public ArrayList<User> findAllInActiveUsersList() {
		// TODO Auto-generated method stub
		return userRepository.findByIsActive(0);
	}

	@Override
	public User deleteUserById(Long userId) {
		// TODO Auto-generated method stub
		User user = userRepository.findByUserId(userId);
		if (user != null) {
			userRepository.deleteById(userId);
		}
		return user;
	}

	@Override
	public User inactivateUserById(Long userId) {
		// TODO Auto-generated method stub
		User user = userRepository.findByUserId(userId);
		if (user != null) {
			user.setIsActive(0);
			return userRepository.save(user);
		}
		return null;
	}

	
	//Utility
	@Override
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

	public User resetUserPassword(User user) {
		user.setIsActive(1);
		
		User clonedUser = (User) Utility.deepClone(user);
		clonedUser.setPassword(Utility.generateRandomPassword(8));
		user.setPassword(bCryptPasswordEncoder.encode(clonedUser.getPassword()));

		emailController.sendRegistrationEmail(clonedUser);
		return userRepository.save(user);
	}

	@Override
	public ArrayList<User> findAllActiveInactiveUsersList() {
		// TODO Auto-generated method stub
		return (ArrayList<User>) userRepository.findAll();
	}

	@Override
	public boolean checkIfPasswordExists(User user, String password) {
		if(bCryptPasswordEncoder.matches(password, user.getPassword())) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public User changeUserPassword(User user, String newPassword) {
		user.setIsActive(1);
		
		User clonedUser = (User) Utility.deepClone(user);
		clonedUser.setPassword(newPassword);
		user.setPassword(bCryptPasswordEncoder.encode(clonedUser.getPassword()));

		emailController.sendRegistrationEmail(clonedUser);
		return userRepository.save(user);
	}
	
}
