package org.khmeracademy.auction.repositories;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.khmeracademy.auction.entities.InvoiceDetail;
import org.khmeracademy.auction.entities.InvoiceDetailInputUpdate;
import org.khmeracademy.auction.filtering.InvoiceFilter;
import org.khmeracademy.auction.utils.Pagination;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceDetailRepository {
	final String FIND_ALL_INVOICE_DETAILS="SELECT * FROM v_find_all_invoice_details WHERE status<>'2'"
			+ "AND LOWER(first_name) LIKE LOWER('%' || #{filter.fullName} || '%')" + "ORDER BY invoice_id DESC "
			+ "LIMIT #{pagination.limit} " + "OFFSET #{pagination.offset} ";// status 2 is deleted
	@Select(FIND_ALL_INVOICE_DETAILS)
	@Results(value={
			// Invoice
			@Result(property="invoice.invoice_id", column="invoice_id"),
			@Result(property="buy_price", column="invoice_buy_price"),
			@Result(property="invoice.invoice_date", column="invoice_date"),
			@Result(property="invoice.status", column="status"),
			
			// user
			@Result(property="invoice.user.user_id", column="user_id"),
			@Result(property="invoice.user.user_name", column="user_name"),
			@Result(property="invoice.user.first_name", column="first_name"),
			@Result(property="invoice.user.last_name", column="last_name"),
			@Result(property="invoice.user.gender", column="gender"),
			@Result(property="invoice.user.dob", column="dob"),
			@Result(property="invoice.user.address", column="address"),
			@Result(property="invoice.user.email", column="email"),			
			@Result(property="invoice.user.contact", column="contact"),
			@Result(property="invoice.user.photo", column="photo"),
			@Result(property="invoice.user.type", column="type"),
			@Result(property="invoice.user.status", column="user_status"),
			
			// auction
			@Result(property="auction.auction_id", column="auction_id"),
			@Result(property="auction.product_condition", column="product_condition"),
			@Result(property="auction.start_price", column="start_price"),
			@Result(property="auction.buy_price", column="auction_buy_price"),
			@Result(property="auction.increment_price", column="increment_price"),
			@Result(property="auction.current_price", column="current_price"),
			@Result(property="auction.start_price", column="start_price"),
			@Result(property="auction.end_date", column="end_date"),
			@Result(property="auction.status", column="auction_status"),
			
			// product
			@Result(property="auction.product.product_id", column="product_id"),
			@Result(property="auction.product.product_name", column="product_name"),
			@Result(property="auction.product.product_description", column="product_description"),
			@Result(property="auction.product.qty", column="product_qty")
	})
	public ArrayList<InvoiceDetail> findAllInvoiceDetails(@Param("filter") InvoiceFilter filter, @Param("pagination") Pagination pagination);
	
	@Select("SELECT COUNT(invoice_id) FROM v_find_all_invoice_details WHERE LOWER(first_name) LIKE LOWER('%' || #{filter.fullName} || '%')")
	public Long count(@Param("filter") InvoiceFilter filter);
	
	final String FIND_INVOICE_DETAIL_BY_INVOICE_ID="SELECT * FROM v_find_all_invoice_details WHERE invoice_id = #{invoice_id}";
	@Select(FIND_INVOICE_DETAIL_BY_INVOICE_ID)
	@Results(value={
			// Invoice
			@Result(property="invoice.invoice_id", column="invoice_id"),
			@Result(property="buy_price", column="invoice_buy_price"),
			@Result(property="invoice.invoice_date", column="invoice_date"),
			@Result(property="invoice.status", column="status"),
			
			// user
			@Result(property="invoice.user.user_id", column="user_id"),
			@Result(property="invoice.user.user_name", column="user_name"),
			@Result(property="invoice.user.first_name", column="first_name"),
			@Result(property="invoice.user.last_name", column="last_name"),
			@Result(property="invoice.user.gender", column="gender"),
			@Result(property="invoice.user.dob", column="dob"),
			@Result(property="invoice.user.address", column="address"),
			@Result(property="invoice.user.email", column="email"),			
			@Result(property="invoice.user.contact", column="contact"),
			@Result(property="invoice.user.photo", column="photo"),
			@Result(property="invoice.user.type", column="type"),
			@Result(property="invoice.user.status", column="user_status"),
			
			// auction
			@Result(property="auction.auction_id", column="auction_id"),
			@Result(property="auction.product_condition", column="product_condition"),
			@Result(property="auction.start_price", column="start_price"),
			@Result(property="auction.buy_price", column="auction_buy_price"),
			@Result(property="auction.increment_price", column="increment_price"),
			@Result(property="auction.current_price", column="current_price"),
			@Result(property="auction.start_price", column="start_price"),
			@Result(property="auction.end_date", column="end_date"),
			@Result(property="auction.status", column="auction_status"),
			
			// product
			@Result(property="auction.product.product_id", column="product_id"),
			@Result(property="auction.product.product_name", column="product_name"),
			@Result(property="auction.product.product_description", column="product_description"),
			@Result(property="auction.product.qty", column="product_qty")	
	})
	public InvoiceDetail findInvoiceDetailByInvoiceId(int invoice_id);
	
	final String FIND_INVOICE_DETAIL_BY_AUCTION_ID="SELECT * FROM v_find_all_invoice_details WHERE auction_id = #{auction_id}";
	@Select(FIND_INVOICE_DETAIL_BY_AUCTION_ID)
	@Results(value={
			// Invoice
			@Result(property="invoice.invoice_id", column="invoice_id"),
			@Result(property="buy_price", column="invoice_buy_price"),
			@Result(property="invoice.invoice_date", column="invoice_date"),
			
			
			// user
			@Result(property="invoice.user.user_id", column="user_id"),
			@Result(property="invoice.user.user_name", column="user_name"),
			@Result(property="invoice.user.first_name", column="first_name"),
			@Result(property="invoice.user.last_name", column="last_name"),
			@Result(property="invoice.user.gender", column="gender"),
			@Result(property="invoice.user.dob", column="dob"),
			@Result(property="invoice.user.address", column="address"),
			@Result(property="invoice.user.email", column="email"),			
			@Result(property="invoice.user.contact", column="contact"),
			@Result(property="invoice.user.photo", column="photo"),
			@Result(property="invoice.user.type", column="type"),
			@Result(property="invoice.user.status", column="user_status"),
			
			// auction
			@Result(property="auction.auction_id", column="auction_id"),
			@Result(property="auction.product_condition", column="product_condition"),
			@Result(property="auction.start_price", column="start_price"),
			@Result(property="auction.buy_price", column="auction_buy_price"),
			@Result(property="auction.increment_price", column="increment_price"),
			@Result(property="auction.current_price", column="current_price"),
			@Result(property="auction.start_price", column="start_price"),
			@Result(property="auction.end_date", column="end_date"),
			@Result(property="auction.status", column="auction_status"),
			
			// product
			@Result(property="auction.product.product_id", column="product_id"),
			@Result(property="auction.product.product_name", column="product_name"),
			@Result(property="auction.product.product_description", column="product_description"),
			@Result(property="auction.product.qty", column="product_qty")		
	})
	public ArrayList<InvoiceDetail> findInvoiceDetailByAuctionId(int auction_id);
	
	final String ADD_INVOICE_DETAIL="INSERT INTO auc_invoice_detail(invoice_id,auction_id,buy_price,qty) VALUES(#{invoice_id},#{auction_id},#{buy_price},#{qty})";
	@Insert(ADD_INVOICE_DETAIL)
	public boolean addInvoiceDetail(InvoiceDetailInputUpdate invd);
	
	final String UPDATE_INVOICE_DETAIL="UPDATE auc_invoice_detail "+
									" SET buy_price = #{buy_price}, qty = #{qty} "+
									" WHERE invoice_id = #{invoice_id} AND auction_id = #{auction_id} ";
	@Update(UPDATE_INVOICE_DETAIL)
	public boolean updateInvoiceDetail(InvoiceDetailInputUpdate invd);
	
	/*final String DELETE_INVOICE_DETAIL="UPDATE auc_invoice_detail SET status = '2' "+
			" WHERE invoice_id = #{invoice_id} AND auction_id = #{auction_id}";
	@Delete(DELETE_INVOICE_DETAIL)
	public boolean deleteInvoiceDetail(int invoice_id, int auction_id);*/
}
