package org.khmeracademy.auction.entities;



public class AuctionInputUpdate {
	private int product_id;
	private String product_condition;
	private double start_price;
	private double buy_price;
	private double increment_price;
	private double current_price;
	//@JsonFormat(pattern = "yyyy-mm-dd hh24:mi:ss")
	private String start_date;
	//@JsonFormat(pattern = "yyyy-mm-dd hh24:mi:ss")
	private String end_date;
	private String status;
	private String created_by;
	private String created_date;
	private String comment;
	
	public AuctionInputUpdate() {
		super();
		// TODO Auto-generated constructor stub
	}
	private int auction_id; 
	public AuctionInputUpdate(int auction_id, int product_id, String product_condition, double start_price,
			double buy_price, double increment_price, double current_price, String start_date, String end_date,
			String status, String created_by, String created_date, String comment) {
		super();
		this.auction_id = auction_id;
		this.product_id = product_id;
		this.product_condition = product_condition;
		this.start_price = start_price;
		this.buy_price = buy_price;
		this.increment_price = increment_price;
		this.current_price = current_price;
		this.start_date = start_date;
		this.end_date = end_date;
		this.status = status;
		this.created_by = created_by;
		this.created_date = created_date;
		this.comment = comment;
	}
	
	public int getAuction_id() {
		return auction_id;
	}
	public void setAuction_id(int auction_id) {
		this.auction_id = auction_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
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
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
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
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "AuctionInputUpdate [product_id=" + product_id + ", product_condition=" + product_condition
				+ ", start_price=" + start_price + ", buy_price=" + buy_price + ", increment_price=" + increment_price
				+ ", current_price=" + current_price + ", start_date=" + start_date + ", end_date=" + end_date
				+ ", status=" + status + ", created_by=" + created_by + ", created_date=" + created_date + ", comment="
				+ comment + ", auction_id=" + auction_id + "]";
	}
	
	
}
