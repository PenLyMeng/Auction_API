package org.khmeracademy.auction.entities;

import java.sql.Date;

public class BiddingAuction {
	
	private int auction_id; 
	private Product product;  // Object
	private String product_condition;
	private double start_price;
	private double buy_price;
	private double increment_price;
	private double current_price;
	private Date start_date;
	private Date end_date;
	private String status;
	private String created_by;
	private Date created_date;
	private String comment;
	private int num_bid;
	private double bid_current_price;
	public int getAuction_id() {
		return auction_id;
	}
	public void setAuction_id(int auction_id) {
		this.auction_id = auction_id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getProduct_condition() {
		return product_condition;
	}
	public void setProduct_condition(String product_condition) {
		this.product_condition = product_condition;
	}
	public double getStart_price() {
		return start_price;
	}
	public void setStart_price(double start_price) {
		this.start_price = start_price;
	}
	public double getBuy_price() {
		return buy_price;
	}
	public void setBuy_price(double buy_price) {
		this.buy_price = buy_price;
	}
	public double getIncrement_price() {
		return increment_price;
	}
	public void setIncrement_price(double increment_price) {
		this.increment_price = increment_price;
	}
	public double getCurrent_price() {
		return current_price;
	}
	public void setCurrent_price(double current_price) {
		this.current_price = current_price;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getNum_bid() {
		return num_bid;
	}
	public void setNum_bid(int num_bid) {
		this.num_bid = num_bid;
	}
	public double getBid_current_price() {
		return bid_current_price;
	}
	public void setBid_current_price(double bid_current_price) {
		this.bid_current_price = bid_current_price;
	}
	
	
}
