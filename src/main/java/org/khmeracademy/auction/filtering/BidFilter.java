package org.khmeracademy.auction.filtering;

public class BidFilter {
	String userName;
	
	public BidFilter(){
		userName = "";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
