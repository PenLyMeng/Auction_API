package org.khmeracademy.auction.repositories;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.khmeracademy.auction.entities.PaymentMethod;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodRepository {
	final String FIND_ALL_PAYMENT_METHODS = "SELECT * FROM auc_payment_method";
	@Select(FIND_ALL_PAYMENT_METHODS)
	public ArrayList<PaymentMethod> findAllPaymentMethods();
	
	final String FIND_PAYMENT_METHOD_BY_NAME = "SELECT * FROM auc_payment_method WHERE payment_method_name = #{payment_method_name}";
	@Select(FIND_PAYMENT_METHOD_BY_NAME)
	public ArrayList<PaymentMethod> findPaymentMethodByName(String payment_method_name);
	
	final String ADD_PAYMENT_METHOD="INSERT INTO auc_payment_method(payment_method_name) VALUES(#{payment_method_name})";
	@Insert(ADD_PAYMENT_METHOD)
	public boolean addPaymentMethod(PaymentMethod p);
	
	final String UPDATE_PAYMENT_METHOD="UPDATE auc_payment_method SET payment_method_name = #{payment_method_name} WHERE payment_method_id = #{payment_method_id}";
	@Update(UPDATE_PAYMENT_METHOD)
	public boolean updatePaymentMethod(PaymentMethod p);
	
	final String DELETE_PAYMENT_METHOD="DELETE FROM auc_payment_method WHERE payment_method_id = #{payment_method_id}";
	@Delete(DELETE_PAYMENT_METHOD)
	public boolean deletePaymentMethod(int payment_method_id);
	
	
	
	
	
	
}
