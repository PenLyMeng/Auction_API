package org.khmeracademy.auction.repositories;

import java.sql.Date;
import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.khmeracademy.auction.entities.InvoiceInputUpdate;
import org.khmeracademy.auction.filtering.InvoiceFilter;
import org.khmeracademy.auction.filtering.TopupFilter;
import org.khmeracademy.auction.utils.Pagination;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository {
	final String FIND_ALL_INVOICES ="SELECT * FROM v_find_all_invoices where status<>'2'";// Status 2 is deleted
	@Select(FIND_ALL_INVOICES)
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
			@Result(property="user.status", column="user_status")
	})
	public ArrayList<InvoiceInputUpdate> findAllInvoices();

	
	final String FIND_INVOICE_BY_USER_NAME=
					" SELECT * FROM v_find_all_invoices WHERE user_name = #{user_name} ";
	@Select(FIND_INVOICE_BY_USER_NAME)
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
			@Result(property="user.status", column="user_status")
	})
	public ArrayList<InvoiceInputUpdate> findInvoiceByUserName(String user_name);
	
	final String FIND_INVOICE_BY_DATE="SELECT * FROM v_find_all_invoices WHERE invoice_date = #{invoice_date} ";
	@Select(FIND_INVOICE_BY_DATE)
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
			@Result(property="user.status", column="user_status")
	})
	public ArrayList<InvoiceInputUpdate> findInvoiceByDate(Date invoice_date);
	
	final String ADD_INVOICE=" INSERT INTO auc_invoice(user_id,invoice_date) VALUES(#{user_id}, now()); " + 
							" INSERT INTO auc_invoice_detail(invoice_id, auction_id, buy_price, qty) " +
							" SELECT MAX(invoice_id), #{auction_id},#{buy_price},#{qty} " + 
							" FROM auc_invoice; ";
	@Insert(ADD_INVOICE)
	public boolean addInvoice(InvoiceInputUpdate inv);
	
	final String UPDATE_INVOICE=
					"	UPDATE auc_invoice	"+
					"	SET user_id = #{user_id}, 	"+	
					"   status = #{status} "+		
					"	WHERE invoice_id = #{invoice_id}	";
	@Update(UPDATE_INVOICE)
	public boolean updateInvoice(InvoiceInputUpdate inv);
	
	final String DELETE_INVOICE="UPDATE auc_invoice SET status='2' WHERE invoice_id = #{invoice_id}";
	@Delete(DELETE_INVOICE)
	public boolean deleteInvoice(int invoice_id);

}
