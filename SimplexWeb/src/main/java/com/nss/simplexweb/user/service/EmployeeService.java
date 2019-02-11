package com.nss.simplexweb.user.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nss.simplexweb.enums.ROLE;
import com.nss.simplexweb.user.model.Department;
import com.nss.simplexweb.user.model.Role;
import com.nss.simplexweb.user.model.User;
import com.nss.simplexweb.user.repository.UserRepository;
import com.nss.simplexweb.user.service.intf.EmployeeServiceInterface;
import com.nss.simplexweb.utility.Utility;
import com.nss.simplexweb.utility.mail.EmailController;

// TODO: Auto-generated Javadoc
/**
 * The Class EmployeeService.
 */
@Service("employeeService")
public class EmployeeService implements EmployeeServiceInterface {

	/** The user repository. */
	@Autowired
	private UserRepository userRepository;
	
	/** The role service. */
	@Autowired
	private RoleService roleService;

	/** The b crypt password encoder. */
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	/** The email controller. */
	@Autowired
	private EmailController emailController;

	/** The uploaded folder path. */
	@Value("${file.user.profile.image}")
	private String UPLOADED_FOLDER_PATH;

	/**
	 * Instantiates a new employee service.
	 *
	 * @param userRepository        the user repository
	 * @param bCryptPasswordEncoder the b crypt password encoder
	 */
	@Autowired
	public EmployeeService(
			UserRepository userRepository,
			RoleService roleService,
			BCryptPasswordEncoder bCryptPasswordEncoder) {

		this.userRepository = userRepository;
		this.roleService = roleService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nss.simplexweb.user.service.intf.EmployeeServiceInterface#
	 * saveNewEmployeeAutoGeneratePassword(com.nss.simplexweb.user.model.User)
	 */
	@Override
	public User saveNewEmployeeAutoGeneratePassword(User user) {
		// TODO Auto-generated method stub
		user.setIsActive(1);
		user.setPassword(bCryptPasswordEncoder.encode(Utility.generateRandomPassword(8)));
		emailController.sendRegistrationEmail(user);
		return userRepository.save(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nss.simplexweb.user.service.intf.EmployeeServiceInterface#
	 * saveNewEmployeeWithoutAutoGeneratePassword(com.nss.simplexweb.user.model.
	 * User)
	 */
	@Override
	public User saveNewEmployeeWithoutAutoGeneratePassword(User user) {
		// TODO Auto-generated method stub
		user.setIsActive(1);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		emailController.sendRegistrationEmail(user);
		return userRepository.save(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nss.simplexweb.user.service.intf.EmployeeServiceInterface#
	 * findEmployeeByEmployeeId(java.lang.Long)
	 */
	@Override
	public User findEmployeeByEmployeeId(Long employeeId) {
		// TODO Auto-generated method stub
		return userRepository.findByEmpId(employeeId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nss.simplexweb.user.service.intf.EmployeeServiceInterface#
	 * findEmployeeByEmailId(java.lang.String)
	 */
	@Override
	public User findEmployeeByEmailId(String emailId) {
		// TODO Auto-generated method stub
		return userRepository.findByEmailAndIsActive(emailId, 1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nss.simplexweb.user.service.intf.EmployeeServiceInterface#
	 * findEmployeeByEmailIdAndNonEncrPassword(java.lang.String, java.lang.String)
	 */
	@Override
	public User findEmployeeByEmailIdAndNonEncrPassword(String emailId, String password) {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmailAndIsActive(emailId, 1);
		if (user != null) {
			if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
				return user;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nss.simplexweb.user.service.intf.EmployeeServiceInterface#
	 * findEmployeeByEmailIdAndEncrPassword(java.lang.String, java.lang.String)
	 */
	@Override
	public User findEmployeeByEmailIdAndEncrPassword(String emailId, String password) {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmailAndPasswordAndIsActive(emailId, password, 1);
		if (user != null) {
			return user;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nss.simplexweb.user.service.intf.EmployeeServiceInterface#
	 * findAllActiveEmployeesList()
	 */
	@Override
	public ArrayList<User> findAllActiveEmployeesList() {
		// TODO Auto-generated method stub
		return userRepository.findByIsActive(1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nss.simplexweb.user.service.intf.EmployeeServiceInterface#
	 * findAllInActiveEmployeesList()
	 */
	@Override
	public ArrayList<User> findAllInActiveEmployeesList() {
		// TODO Auto-generated method stub
		return userRepository.findByIsActive(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nss.simplexweb.user.service.intf.EmployeeServiceInterface#
	 * findAllEmployeesListUnderUser(com.nss.simplexweb.user.model.User)
	 */
	@Override
	public ArrayList<User> findAllEmployeesListUnderUser(User user) {
		// TODO Auto-generated method stub
		return userRepository.findByManagerIdAndIsActiveAndEmpIdIsNotNull(user.getUserId(), 1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nss.simplexweb.user.service.intf.EmployeeServiceInterface#
	 * findAllEmployeesListByRole(com.nss.simplexweb.user.model.Role)
	 */
	@Override
	public ArrayList<User> findAllEmployeesListByRole(Role role) {
		// TODO Auto-generated method stub
		return userRepository.findByRoleAndIsActiveAndEmpIdIsNotNull(role, 1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nss.simplexweb.user.service.intf.EmployeeServiceInterface#
	 * findAllEmployeesListByDepartment(com.nss.simplexweb.user.model.Department)
	 */
	@Override
	public ArrayList<User> findAllEmployeesListByDepartment(Department department) {
		// TODO Auto-generated method stub
		// return
		// userRepository.findByRole(roleService.getRoleByDepartment(department));
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nss.simplexweb.user.service.intf.EmployeeServiceInterface#
	 * findAllEmployeesListByRolesList(java.util.ArrayList)
	 */
	@Override
	public ArrayList<User> findAllEmployeesListByRolesList(ArrayList<Role> roles) {
		// TODO Auto-generated method stub
		return userRepository.findByRoleInAndIsActiveAndEmpIdIsNotNull(roles, 1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nss.simplexweb.user.service.intf.EmployeeServiceInterface#
	 * findAllEmployeesListByDepartmentsList(java.util.ArrayList)
	 */
	@Override
	public ArrayList<User> findAllEmployeesListByDepartmentsList(ArrayList<Department> departments) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nss.simplexweb.user.service.intf.EmployeeServiceInterface#updateEmlpoyee(
	 * com.nss.simplexweb.user.model.User)
	 */
	@Override
	public User updateEmlpoyeeWithPassword(User user) {
		// TODO Auto-generated method stub
		User oldUserData = userRepository.findByUserId(user.getUserId());
		user.setIsActive(1);
		user.setProfilePicFilename(oldUserData.getProfilePicFilename());
		user.setProfilePicFolderpath(oldUserData.getProfilePicFolderpath());
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nss.simplexweb.user.service.intf.EmployeeServiceInterface#
	 * updateEmlpoyeeWithoutPassword(com.nss.simplexweb.user.model.User)
	 */
	@Override
	public User updateEmlpoyeeWithoutPassword(User user) {
		// TODO Auto-generated method stub
		User oldUserData = userRepository.findByUserId(user.getUserId());
		user.setIsActive(1);
		user.setProfilePicFilename(oldUserData.getProfilePicFilename());
		user.setProfilePicFolderpath(oldUserData.getProfilePicFolderpath());
		user.setPassword(oldUserData.getPassword());
		return userRepository.save(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nss.simplexweb.user.service.intf.EmployeeServiceInterface#
	 * deleteEmployeeById(java.lang.Long)
	 */
	@Override
	public User deleteEmployeeById(Long userId) {
		// TODO Auto-generated method stub
		User user = userRepository.findByUserId(userId);
		if (user != null) {
			userRepository.deleteById(userId);
		}
		return user;
	}
	
	/* (non-Javadoc)
	 * @see com.nss.simplexweb.user.service.intf.EmployeeServiceInterface#inactivateEmployeeById(java.lang.Long)
	 */
	@Override
	public User inActivateEmployeeById(Long userId) {
		User user = userRepository.findByUserId(userId);
		if (user != null) {
			user.setIsActive(0);
			return userRepository.save(user);
		}
		return null;
	}
	
	
	//Utility
	@Override
	public ArrayList<User> findAllllEmployeesUnderRoleId(Long roleId) {
		ArrayList<User> userList = null;
		ArrayList<Role> childRoles = roleService.getAllLevelChildRolesById(roleId);
		System.out.println("childRoles : " + childRoles);
		if(childRoles != null) {
			for(Role role : childRoles) {
				if(userList == null)
					userList = new ArrayList<>();
				userList.addAll(userRepository.findByRoleAndIsActiveAndEmpIdIsNotNull(role, 1));
			}
		}
		return userList;
	}
	
	@Override
	public User updateMyProfilePictureEmployee(User user) {
		// TODO Auto-generated method stub
		User oldUserData = userRepository.findByUserId(user.getUserId());
		user.setIsActive(1);
		user.setPassword(oldUserData.getPassword());
		return userRepository.save(user);
	}

}
