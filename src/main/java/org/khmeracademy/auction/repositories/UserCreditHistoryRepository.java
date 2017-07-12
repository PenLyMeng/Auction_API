package org.khmeracademy.auction.repositories;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.khmeracademy.auction.entities.UserCreditHistory;
import org.khmeracademy.auction.entities.UserCreditHistoryInputUpdate;
import org.khmeracademy.auction.filtering.BidFilter;
import org.khmeracademy.auction.filtering.TopupFilter;
import org.khmeracademy.auction.utils.Pagination;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCreditHistoryRepository {
	final String FIND_ALL_USER_CREDIT_HISTORY="SELECT * FROM v_find_all_user_credit_history";
	@Select(FIND_ALL_USER_CREDIT_HISTORY)
	@Results(value={
			@Result(property="user.user_id", column="user_id"),
			@Result(property="user.user_name", column="user_name"),
			@Result(property="user.first_name", column="first_name"),
			@Result(property="user.last_name", column="last_name"),
			@Result(property="user.gender", column="gender"),
			@Result(property="user.dob", column="dob"),
			@Result(property="user.address", column="address"),
			@Result(property="user.email", column="email"),
			@Result(property="user.contact", column="contact"),
			@Result(property="user.photo", column="photo"),
			@Result(property="user.type", column="type"),
			@Result(property="user.status", column="status")
	})
	public ArrayList<UserCreditHistory> findAllUserCreditHistory();
	
	final String FIND_USER_CREDIT_HISTORY_BY_USER_NAME=
					" SELECT * FROM v_find_all_user_credit_history WHERE u.user_name = #{user_name}	";
	@Select(FIND_USER_CREDIT_HISTORY_BY_USER_NAME)
	@Results(value={
			@Result(property="user.user_id", column="user_id"),
			@Result(property="user.user_name", column="user_name"),
			@Result(property="user.first_name", column="first_name"),
			@Result(property="user.last_name", column="last_name"),
			@Result(property="user.gender", column="gender"),
			@Result(property="user.dob", column="dob"),
			@Result(property="user.address", column="address"),
			@Result(property="user.email", column="email"),
			@Result(property="user.contact", column="contact"),
			@Result(property="user.photo", column="photo"),
			@Result(property="user.type", column="type"),
			@Result(property="user.status", column="status")
	})
	public ArrayList<UserCreditHistory> findUserCreditHistoryByUserName(String user_name);
		
	final String ADD_USER_CREDIT_HISTORY=
					"	INSERT INTO auc_user_credit_history(	"+
					"	credit_date,	"+
					"	user_id,	"+
					"	beginning_amount,	"+
					"	transaction_type,	"+
					"	amount,	"+
					"	ending_amount)	"+
					"	VALUES (	"+
					"	#{credit_date},	"+
					"	#{user_id},	"+
					"	#{beginning_amount},	"+
					"	#{transaction_type},	"+
					"	#{amount},	"+
					"	#{ending_amount})	";
	@Insert(ADD_USER_CREDIT_HISTORY)
	public boolean addUserCreditHistory(UserCreditHistoryInputUpdate uch);
	
	final String UPDATE_USER_CREDIT_HISTORY=
					"	UPDATE auc_user_credit_history	"+
					"	SET credit_date = #{credit_date},	"+
					"	user_id = #{user_id},	"+
					"	beginning_amount = #{beginning_amount},	"+
					"	transaction_type = #{transaction_type},	"+
					"	amount = #{amount},	"+
					"	ending_amount = #{ending_amount}	"+
					"	WHERE credit_id = #{credit_id}	";
	@Update(UPDATE_USER_CREDIT_HISTORY)
	public boolean updateUserCreditHistory(UserCreditHistoryInputUpdate uch);
	
	final String DELETE_USER_CREDIT_HISTORY="DELETE FROM auc_user_credit_history WHERE credit_id = #{credit_id}";
	@Delete(DELETE_USER_CREDIT_HISTORY)
	public boolean deleteUserCreditHistory(int credit_id);	
	
	@Select("SELECT ending_amount FROM v_find_bid_ending_amount WHERE user_id = #{user_id}")
	public UserCreditHistory checkingEndingAmount(int user_id);
	
	final String FIND_USER_CREDIT_HISTORY_BY_USER_ID=
			" SELECT * FROM v_find_bid_ending_amount WHERE user_id = #{userId}";
	@Select(FIND_USER_CREDIT_HISTORY_BY_USER_ID)
	@Results(value={
			@Result(property="user.user_id", column="user_id"),
			@Result(property="user.user_name", column="user_name"),
			@Result(property="user.first_name", column="first_name"),
			@Result(property="user.last_name", column="last_name"),
			@Result(property="user.status", column="user_status")
	})
	public UserCreditHistory findUserCreditHistoryByUserId(int userId);

	final String FIND_ALL_ACTIVE_USER_CREDIT_HISTORY_WITH_ENDING_AMOUNT=" SELECT * FROM v_find_bid_ending_amount "
			+ "WHERE LOWER(first_name) LIKE LOWER('%' || #{filter.fullName} || '%')" + "ORDER BY credit_id DESC "
			+ "LIMIT #{pagination.limit} " + "OFFSET #{pagination.offset} ";
	@Select(FIND_ALL_ACTIVE_USER_CREDIT_HISTORY_WITH_ENDING_AMOUNT)
	@Results(value={
			@Result(property="user.user_id", column="user_id"),
			@Result(property="user.user_name", column="user_name"),
			@Result(property="user.first_name", column="first_name"),
			@Result(property="user.last_name", column="last_name"),
			@Result(property="user.status", column="user_status")
	})
	public ArrayList<UserCreditHistory> findAllActiveUserCreditHistoryWithEndingAmount(@Param("filter") TopupFilter filter,@Param("pagination") Pagination pagination);
	
	@Select("SELECT COUNT(credit_id) FROM v_find_bid_ending_amount WHERE LOWER(first_name) LIKE LOWER('%' || #{filter.fullName} || '%')")
	public Long count(@Param("filter") TopupFilter filter);
}
