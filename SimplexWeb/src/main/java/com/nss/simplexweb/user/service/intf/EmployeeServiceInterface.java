package com.nss.simplexweb.user.service.intf;

import java.util.ArrayList;

import com.nss.simplexweb.user.model.Department;
import com.nss.simplexweb.user.model.Role;
import com.nss.simplexweb.user.model.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface EmployeeServiceInterface.
 */
public interface EmployeeServiceInterface {
	
	/**
	 * Save new employee auto generate password.
	 *
	 * @param user the user
	 * @return the user
	 */
	//Create
	User saveNewEmployeeAutoGeneratePassword(User user);
	
	/**
	 * Save new employee without auto generate password.
	 *
	 * @param user the user
	 * @return the user
	 */
	User saveNewEmployeeWithoutAutoGeneratePassword(User user);
	
	
	/**
	 * Find employee by employee id.
	 *
	 * @param employeeId the employee id
	 * @return the user
	 */
	//Read
	User findEmployeeByEmployeeId(Long employeeId);
	
	/**
	 * Find employee by email id.
	 *
	 * @param emailId the email id
	 * @return the user
	 */
	User findEmployeeByEmailId(String emailId);
	
	/**
	 * Find employee by email id and non encr password.
	 *
	 * @param emailId the email id
	 * @param password the password
	 * @return the user
	 */
	User findEmployeeByEmailIdAndNonEncrPassword(String emailId, String password);
	
	/**
	 * Find employee by email id and encr password.
	 *
	 * @param emailId the email id
	 * @param password the password
	 * @return the user
	 */
	User findEmployeeByEmailIdAndEncrPassword(String emailId, String password);
	
	/**
	 * Find all active employees list.
	 *
	 * @return the array list
	 */
	ArrayList<User> findAllActiveEmployeesList();
	
	/**
	 * Find all in active employees list.
	 *
	 * @return the array list
	 */
	ArrayList<User> findAllInActiveEmployeesList();
	
	/**
	 * Find all employees list under user.
	 *
	 * @param user the user
	 * @return the array list
	 */
	ArrayList<User> findAllEmployeesListUnderUser(User user);
	
	/**
	 * Find all employees list by role.
	 *
	 * @param role the role
	 * @return the array list
	 */
	ArrayList<User> findAllEmployeesListByRole(Role role);
	
	/**
	 * Find all employees list by department.
	 *
	 * @param department the department
	 * @return the array list
	 */
	ArrayList<User> findAllEmployeesListByDepartment(Department department);
	
	/**
	 * Find all employees list by roles list.
	 *
	 * @param roles the roles
	 * @return the array list
	 */
	ArrayList<User> findAllEmployeesListByRolesList(ArrayList<Role> roles);
	
	/**
	 * Find all employees list by departments list.
	 *
	 * @param departments the departments
	 * @return the array list
	 */
	ArrayList<User> findAllEmployeesListByDepartmentsList(ArrayList<Department> departments);
	
	
	/**
	 * Update emlpoyee with password.
	 *
	 * @param user the user
	 * @return the user
	 */
	//Update
	User updateEmlpoyeeWithPassword(User user);
	
	/**
	 * Update emlpoyee without password.
	 *
	 * @param user the user
	 * @return the user
	 */

	User updateEmlpoyeeWithoutPassword(User user);
	
	/**
	 * Update my profile picture employee.
	 *
	 * @param user the user
	 * @return the user
	 */
	User updateMyProfilePictureEmployee(User user);

	/**
	 * Delete employee by id.
	 *
	 * @param userId the user id
	 * @return the user
	 */
	User deleteEmployeeById(Long userId);
	
	
	/**
	 * Inactivate employee by id.
	 *
	 * @param userId the user id
	 * @return the user
	 */
	User inActivateEmployeeById(Long userId);
	
	
	//Utility Methods
	ArrayList<User> findAllllEmployeesUnderRoleId(Long roleId);
}
