package org.khmeracademy.auction.entities;

import java.sql.Date;

public class TopUp {
	private int top_up_id;
	private User user;     // user object
	private String currency;
	private double amount;
	private Date top_up_date;
	private PaymentMethod paymentMethod; // paymentMethod object
	private String status;
	public int getTop_up_id() {
		return top_up_id;
	}
	public void setTop_up_id(int top_up_id) {
		this.top_up_id = top_up_id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
		
}
