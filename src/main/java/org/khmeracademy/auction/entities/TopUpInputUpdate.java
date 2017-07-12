package org.khmeracademy.auction.entities;

import java.sql.Date;

public class TopUpInputUpdate {
	private int top_up_id;
	private int user_id;
	private String currency;
	private double amount;
	private Date top_up_date;
	private int payment_method_id;
	private String status;
	public int getTop_up_id() {
		return top_up_id;
	}
	public void setTop_up_id(int top_up_id) {
		this.top_up_id = top_up_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getTop_up_date() {
		return top_up_date;
	}
	public void setTop_up_date(Date top_up_date) {
		this.top_up_date = top_up_date;
	}
	public int getPayment_method_id() {
		return payment_method_id;
	}
	public void setPayment_method_id(int payment_method_id) {
		this.payment_method_id = payment_method_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

		
}
