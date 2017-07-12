package org.khmeracademy.auction.repositories;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.khmeracademy.auction.entities.Role;
import org.khmeracademy.auction.entities.User;
import org.khmeracademy.auction.entities.UserInputUpdate;
import org.khmeracademy.auction.filtering.UserFilter;
import org.khmeracademy.auction.utils.Pagination;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
	// get all user
	String R_USERS = "SELECT * FROM auc_user WHERE status <>'2' "
			+ "AND LOWER (user_name) LIKE LOWER('%'|| #{filter.userName} ||'%') " + "ORDER BY user_id DESC "
			+ "LIMIT #{pagination.limit}" + "OFFSET #{pagination.offset}";

	@Select(R_USERS)
	public ArrayList<User> getAllUsers(@Param("filter") UserFilter filter, @Param("pagination") Pagination pagination);

	// get user by name(name | first | last)
	String R_USER_ByID = "SELECT * FROM auc_user WHERE user_id = #{user_id}";

	@Select(R_USER_ByID)
	public User findUserById(int user_id);

	// get user by name(name | first | last)
	String R_USER_ByNAME = "SELECT * FROM auc_user WHERE user_name = #{user_name} AND status = '1'";

	@Select(R_USER_ByNAME)
	@Results(value = { @Result(property = "roles", column = "role_id", many = @Many(select = "findUserRoleByUserId")) })
	public User getUserByName(@Param("user_name") String user_name);

	
	// Knongdai user integration. EAN SOKCHOMRERN. 07/10/2016	
	String R_USER_BY_USER_HASH = "SELECT * FROM auc_user WHERE user_hash = #{user_hash} ";

	@Select(R_USER_BY_USER_HASH)
	@Results(value = { @Result(property = "roles", column = "role_id", many = @Many(select = "findUserRoleByUserId")) })
	public User findUserByUserHash(@Param("user_hash") String user_hash);
	

	String R_ROLE_ByROLEID = "SELECT role_id, role_name FROM auc_role WHERE role_id = #{role_id}";

	@Select(R_ROLE_ByROLEID)
	public List<Role> findUserRoleByUserId(@Param("role_id") int role_id);

	// get user by email
	String R_USER_ByEMAIL = "SELECT * FROM auc_user WHERE email = #{email}";

	@Select(R_USER_ByEMAIL)
	public User getUserByEmail(String email);

	// get user by Any Field
	String R_USER_ByAnyFIELD = "select * from pr_find_user_by_any_field(#{user_name})";

	@Select(R_USER_ByAnyFIELD)
	public ArrayList<User> getUserByAnyField(String user_name);

	// String R_USER_ByAnyFIELD="SELECT * FROM auc_user WHERE " //#{user_name}
	// in (first_name, last_name, email, type)
	// + " user_name = #{user_name} or "
	// + " first_name = #{user_name} or "
	// + " last_name = #{user_name} or "
	// + " gender = #{user_name} or "
	// + " dob = #{user_name} or "
	// + " address = #{user_name} or "
	// + " email = #{user_name} or "
	// + " contact = #{user_name} or "
	// + " type = #{user_name} or "
	// + " status = #{user_name} or "
	// + " created_by = #{user_name} or "
	// + " created_date= #{user_name} or "
	// + " comment= #{user_name}";
	// Insert User
	/*
	 * String C_USER="INSERT INTO " + "auc_user(" + "		user_name, " +
	 * "		first_name, " + "		last_name, " + "		gender, " +
	 * "		dob, " + "		address, " + "		email, " +
	 * "		password, " + "		contact, " + "		photo, " +
	 * "		type , " + "		status, " + "		created_by," +
	 * "		created_date, " + "		comment) " + "VALUES (" +
	 * "		#{user_name}, " + "		#{first_name}, " +
	 * "		#{last_name}, " + " 	#{gender}," + "		#{dob}, " +
	 * " 	#{address}, " + " 	#{email}, " + " 	#{password}, " +
	 * " 	#{contact}, " + " 	#{photo}, " + " 	#{type}," +
	 * "		#{status}," + "		#{created_by}," + "		#{created_date}," +
	 * "		#{comment})";
	 */
	String C_USER = "INSERT INTO auc_user(user_name, first_name, last_name, gender,"
			+ "	dob, address, email, password, contact, photo, type, status, "
			+ " created_date, created_by, comment, role_id, verified_code, user_hash) "
			+ "VALUES(#{user_name}, #{first_name}, #{last_name}, #{gender}, "
			+ "	#{dob}, #{address}, #{email}, #{password}, #{contact}, #{photo}, #{type}, #{status},"
			+ " #{created_date}, #{created_by}, #{comment}, #{role_id}, #{verified_code}, #{user_hash})";

	@Insert(C_USER)
	public boolean addUser(UserInputUpdate u);

	// Update User
	String U_USER = "UPDATE auc_user SET " + "		user_name=#{user_name}, " + "		first_name=#{first_name}, "
			+ "		last_name=#{last_name}, " + "		gender=#{gender}," + "		dob=#{dob}, "
			+ "		address=#{address}," + "		email=#{email}, " + "		password=#{password}, "
			+ "		contact=#{contact}, " + "		photo=#{photo}, " + "		type=#{type},"
			+ "		status=#{status}," + "		created_by=#{created_by}," + "		created_date=#{created_date},"
			+ "		comment=#{comment}, " + "		role_id=CASE WHEN #{type} = 'admin' THEN 1 ELSE 2 END"
			+ "		WHERE user_id=#{user_id}";

	@Update(U_USER)
	public boolean updateUser(User user);

	// Delete User
	String D_USER = "UPDATE auc_user SET status = 2 WHERE user_id = #{user_id}";

	@Delete(D_USER)
	public boolean deleteUsers(int userId);

	// get user by user_name and password login
	String LOGIN = "SELECT count(user_name) FROM auc_user WHERE "
			+ " user_name = #{user_name} AND password = #{password}";

	@Select(LOGIN)
	public int userLogin(User user);

	@Select("SELECT COUNT(user_id) FROM auc_user WHERE LOWER(user_name) LIKE LOWER('%'|| #{filter.userName} ||'%')")
	public Long count(@Param("filter") UserFilter filter);

	// get user by verified_code for confirming email
	String R_USER_BY_VERIFIED_CODE = "SELECT * FROM auc_user WHERE verified_code = #{verified_code}";

	@Select(R_USER_BY_VERIFIED_CODE)
	public User findUserByVerifiedCode(String verified_code);

	// Update User status when confirming email
	String U_USER_CONFIRM_EMAIL = "UPDATE auc_user SET status = '1' WHERE verified_code = #{verified_code}";

	@Update(U_USER_CONFIRM_EMAIL)
	public boolean updateUserConfirmEmail(String verified_code);

	// Update User Detail in user profile (Front End)
	String U_USER_PROFILE = "UPDATE auc_user SET first_name=#{first_name}, last_name=#{last_name}, "
			+ " gender=#{gender}, dob=#{dob}, email=#{email}, contact=#{contact}, "
			+ " address=#{address} WHERE user_id=#{user_id} ";

	@Update(U_USER_PROFILE)
	public boolean updateUserProfile(User user);

	// Update User password in user profile (Front End)
	String U_USER_PASSWORD = "UPDATE auc_user SET password=#{password} WHERE user_id = #{user_id} ";

	@Update(U_USER_PASSWORD)
	public boolean updateUserPassword(User user);
	
	
	
}
