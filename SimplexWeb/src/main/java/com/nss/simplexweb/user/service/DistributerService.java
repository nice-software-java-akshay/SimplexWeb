package com.nss.simplexweb.user.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nss.simplexweb.enums.ROLE;
import com.nss.simplexweb.user.model.User;
import com.nss.simplexweb.user.repository.UserRepository;
import com.nss.simplexweb.user.service.intf.DistributerServiceInterface;
import com.nss.simplexweb.utility.Utility;
import com.nss.simplexweb.utility.mail.EmailController;

// TODO: Auto-generated Javadoc
/**
 * The Class DistributerService.
 */
@Service("distributerService")
public class DistributerService implements DistributerServiceInterface {

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
	 * Instantiates a new distributer service.
	 *
	 * @param userRepository the user repository
	 * @param roleService the role service
	 * @param bCryptPasswordEncoder the b crypt password encoder
	 */
	public DistributerService(
			UserRepository userRepository,
			RoleService roleService,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		
		this.userRepository = userRepository;
		this.roleService = roleService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	/* (non-Javadoc)
	 * @see com.nss.simplexweb.user.service.intf.DistributerServiceInterface#saveNewDistributerAutoGeneratePassword(com.nss.simplexweb.user.model.User)
	 */
	@Override
	public User saveNewDistributerAutoGeneratePassword(User user) {
		// TODO Auto-generated method stub
		user.setIsActive(1);
		user.setEmpId(null);
		user.setRole(roleService.getRoleByRoleAbbr(ROLE.DIST.name()));
		
		User clonedUser = (User) Utility.deepClone(user);
		clonedUser.setPassword(Utility.generateRandomPassword(8));
		user.setPassword(bCryptPasswordEncoder.encode(clonedUser.getPassword()));
		
		emailController.sendRegistrationEmail(clonedUser);
		return userRepository.save(user);
	}

	/* (non-Javadoc)
	 * @see com.nss.simplexweb.user.service.intf.DistributerServiceInterface#saveNewDistributerWithoutAutoGeneratePassword(com.nss.simplexweb.user.model.User)
	 */
	@Override
	public User saveNewDistributerWithoutAutoGeneratePassword(User user) {
		// TODO Auto-generated method stub
		user.setIsActive(1);
		user.setEmpId(null);
		
		user.setRole(roleService.getRoleByRoleAbbr(ROLE.DIST.name()));
		User clonedUser = (User) Utility.deepClone(user);
		clonedUser.setPassword(user.getPassword());
		user.setPassword(bCryptPasswordEncoder.encode(clonedUser.getPassword()));
		
		emailController.sendRegistrationEmail(clonedUser);
		return userRepository.save(user);
	}

	/* (non-Javadoc)
	 * @see com.nss.simplexweb.user.service.intf.DistributerServiceInterface#findDistributerByUserId(java.lang.Long)
	 */
	@Override
	public User findDistributerByUserId(Long userId) {
		// TODO Auto-generated method stub
		return userRepository.findByUserId(userId);
	}

	/* (non-Javadoc)
	 * @see com.nss.simplexweb.user.service.intf.DistributerServiceInterface#findDistributerByEmailIdAndNonEncrPassword(java.lang.String, java.lang.String)
	 */
	@Override
	public User findDistributerByEmailIdAndNonEncrPassword(String emailId, String password) {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmailAndIsActive(emailId, 1);
		if (user != null) {
			if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
				return user;
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nss.simplexweb.user.service.intf.DistributerServiceInterface#findDistributerByEmailIdAndEncrPassword(java.lang.String, java.lang.String)
	 */
	@Override
	public User findDistributerByEmailIdAndEncrPassword(String emailId, String password) {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmailAndPasswordAndIsActive(emailId, password, 1);
		if (user != null) {
			return user;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nss.simplexweb.user.service.intf.DistributerServiceInterface#findAllActiveDistributersList()
	 */
	@Override
	public ArrayList<User> findAllActiveDistributersList() {
		// TODO Auto-generated method stub
		return userRepository.findByIsActiveAndEmpIdIsNull(1);
	}

	/* (non-Javadoc)
	 * @see com.nss.simplexweb.user.service.intf.DistributerServiceInterface#findAllInActiveDistributersList()
	 */
	@Override
	public ArrayList<User> findAllInActiveDistributersList() {
		// TODO Auto-generated method stub
		return userRepository.findByIsActiveAndEmpIdIsNull(0);
	}

	
	/* (non-Javadoc)
	 * @see com.nss.simplexweb.user.service.intf.DistributerServiceInterface#updateDistributerWithPassword(com.nss.simplexweb.user.model.User)
	 */
	//UPDATE
	@Override
	public User updateDistributerWithPassword(User user) {
		// TODO Auto-generated method stub
		User oldUserData = userRepository.findByUserId(user.getUserId());
		user.setIsActive(1);
		user.setEmpId(null);
		user.setProfilePicFilename(oldUserData.getProfilePicFilename());
		user.setProfilePicFolderpath(oldUserData.getProfilePicFolderpath());
		user.setRole(roleService.getRoleByRoleAbbr(ROLE.DIST.name()));
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	/* (non-Javadoc)
	 * @see com.nss.simplexweb.user.service.intf.DistributerServiceInterface#updateDistributerWithoutPassword(com.nss.simplexweb.user.model.User)
	 */
	@Override
	public User updateDistributerWithoutPassword(User user) {
		// TODO Auto-generated method stub
		User oldUserData = userRepository.findByUserId(user.getUserId());
		user.setIsActive(1);
		user.setEmpId(null);
		user.setProfilePicFilename(oldUserData.getProfilePicFilename());
		user.setProfilePicFolderpath(oldUserData.getProfilePicFolderpath());
		user.setRole(roleService.getRoleByRoleAbbr(ROLE.DIST.name()));
		user.setPassword(oldUserData.getPassword());
		return userRepository.save(user);
	}

	
	/* (non-Javadoc)
	 * @see com.nss.simplexweb.user.service.intf.DistributerServiceInterface#deleteDistributerById(java.lang.Long)
	 */
	//DELETE
	@Override
	public User deleteDistributerById(Long userId) {
		// TODO Auto-generated method stub
		User user = userRepository.findByUserId(userId);
		if (user != null) {
			userRepository.deleteById(userId);
		}
		return user;
	}

	/* (non-Javadoc)
	 * @see com.nss.simplexweb.user.service.intf.DistributerServiceInterface#inactivateDistributerById(java.lang.Long)
	 */
	@Override
	public User inactivateDistributerById(Long userId) {
		User user = userRepository.findByUserId(userId);
		if (user != null) {
			user.setIsActive(0);
			return userRepository.save(user);
		}
		return null;
	}

	@Override
	public User updateMyProfilePictureDistributer(User user) {
		// TODO Auto-generated method stub
		User oldUserData = userRepository.findByUserId(user.getUserId());
		user.setIsActive(1);
		user.setEmpId(null);
		user.setRole(roleService.getRoleByRoleAbbr(ROLE.DIST.name()));
		user.setPassword(oldUserData.getPassword());
		return userRepository.save(user);
	}
}
