package org.khmeracademy.auction.entities;

import java.sql.Date;

public class BidHistoryWithFirstProductImage {
	private int bid_id;
	private Auction auction;  // Auction Object
	private User user;		  // User Object
	
	private Date bid_date;
	private int image_id;
	private String image_path;
	private int num_bid;  // number of bid by user_id in each auction
	private double user_latest_current_price; // the latest bid_current_price of user on each auction
	public int getBid_id() {
		return bid_id;
	}
	public void setBid_id(int bid_id) {
		this.bid_id = bid_id;
	}
	public Auction getAuction() {
		return auction;
	}
	public void setAuction(Auction auction) {
		this.auction = auction;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getBid_date() {
		return bid_date;
	}
	public void setBid_date(Date bid_date) {
		this.bid_date = bid_date;
	}
	public int getImage_id() {
		return image_id;
	}
	public void setImage_id(int image_id) {
		this.image_id = image_id;
	}
	public String getImage_path() {
		return image_path;
	}
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
	public int getNum_bid() {
		return num_bid;
	}
	public void setNum_bid(int num_bid) {
		this.num_bid = num_bid;
	}
	public double getUser_latest_current_price() {
		return user_latest_current_price;
	}
	public void setUser_latest_current_price(double user_latest_current_price) {
		this.user_latest_current_price = user_latest_current_price;
	}
	
	
	
	
	
	
	
}
