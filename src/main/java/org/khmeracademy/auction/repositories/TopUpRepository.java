package org.khmeracademy.auction.repositories;

import java.sql.Date;
import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.khmeracademy.auction.entities.TopUp;
import org.khmeracademy.auction.entities.TopUpInputUpdate;
import org.springframework.stereotype.Repository;

@Repository
public interface TopUpRepository {
	final String FIND_ALL_TOP_UP="SELECT * FROM v_find_all_top_up where status <> '2'";
	@Select(FIND_ALL_TOP_UP)
	@Results(value={
			// user
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
			@Result(property="user.status", column="user_status"),
			
			// payment method
			@Result(property="paymentMethod.payment_method_id", column="payment_method_id"),
			@Result(property="paymentMethod.payment_method_name", column="payment_method_name")
	})	
	public ArrayList<TopUp> findAllTopUp();
	
	final String FIND_TOP_UP_BY_CURRENCY="SELECT * FROM v_find_all_top_up WHERE currency = #{currency}";
	@Select(FIND_TOP_UP_BY_CURRENCY)
	@Results(value={
			// user
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
			@Result(property="user.status", column="user_status"),
			
			// payment method
			@Result(property="paymentMethod.payment_method_id", column="payment_method_id"),
			@Result(property="paymentMethod.payment_method_name", column="payment_method_name")
	})	
	public ArrayList<TopUp> findTopUpByCurrency(String currency);

	final String FIND_TOP_UP_BY_USER_NAME=
						"	SELECT * FROM v_find_all_top_up WHERE user_name = #{user_name} ";
	@Select(FIND_TOP_UP_BY_USER_NAME)
	@Results(value={
			// user
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
			@Result(property="user.status", column="user_status"),
			
			// payment method
			@Result(property="paymentMethod.payment_method_id", column="payment_method_id"),
			@Result(property="paymentMethod.payment_method_name", column="payment_method_name")
	})	
	public ArrayList<TopUp> findTopUpByUserName(String user_name);

	final String FIND_TOP_UP_BY_DATE="SELECT * FROM v_find_all_top_up WHERE top_up_date = #{top_up_date} ";
	@Select(FIND_TOP_UP_BY_DATE)
	@Results(value={
			// user
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
			@Result(property="user.status", column="user_status"),
			
			// payment method
			@Result(property="paymentMethod.payment_method_id", column="payment_method_id"),
			@Result(property="paymentMethod.payment_method_name", column="payment_method_name")
	})	
	public ArrayList<TopUp> findTopUpByDate(Date top_up_date);
	
	final String ADD_TOP_UP=
			" INSERT INTO auc_top_up(user_id,currency,amount) "+
			" VALUES(#{user_id},#{currency},#{amount}) ";
	@Insert(ADD_TOP_UP)
	public boolean addTopUp(TopUpInputUpdate t);
	
	final String UPDATE_TOP_UP=
					"	UPDATE auc_top_up	"+
					"	SET user_id = #{user_id}, 	"+
					"	currency = #{currency}, 	"+
					"	amount = #{amount}, 	"+
					"	top_up_date = #{top_up_date}, 	"+
					"	payment_method_id = #{payment_method_id},	"+
					"	status = #{status}	"+
					"	WHERE	"+
					"	top_up_id = #{top_up_id}	";
	@Update(UPDATE_TOP_UP)
	public boolean updateTopUp(TopUpInputUpdate t);
	
	final String DELETE_TOP_UP="UPDATE auc_top_up SET status='2' WHERE top_up_id = #{top_up_id}";
	@Delete(DELETE_TOP_UP)
	public boolean deleteTopUp(int top_up_id);

	
}
