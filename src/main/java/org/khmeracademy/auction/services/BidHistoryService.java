package org.khmeracademy.auction.services;



import java.util.ArrayList;

import org.khmeracademy.auction.entities.BidHistory;

import org.khmeracademy.auction.entities.BidHistoryInputUpdate;
import org.khmeracademy.auction.entities.BidHistoryWithFirstProductImage;
import org.khmeracademy.auction.filtering.BidFilter;
import org.khmeracademy.auction.utils.Pagination;


public interface BidHistoryService {
	public ArrayList<BidHistory> findAllBidHistory(BidFilter filter, Pagination pagination);
	public ArrayList<BidHistory> findBidHistoryByAuctionId(int auction_id);
	public ArrayList<BidHistory> findBidHistoryByUserName(String user_name);
	public ArrayList<BidHistory> findBidHistoryByDate(String bid_date);
	public boolean addBidHistory(BidHistoryInputUpdate b);
	public boolean updateBidHistory(BidHistoryInputUpdate b);
	public boolean deleteBidHistory(int bid_id);
	public ArrayList<BidHistory> findAllBidWinner();
	
	
	
	public ArrayList<BidHistory> findAllBidWinnerRealTime();
//	public ArrayList<BidHistoryWithFirstProductImage> findBidHistoryAndImageByUserName(String user_name);
	
	public ArrayList<BidHistoryWithFirstProductImage> findNumberBidByUserIdForEachAuctionInDetails(int user_id);
	
	public  ArrayList<BidHistory> findNumBidAndBidderInAuctionProductByAuctionId(int auction_id);
	
	public  ArrayList<BidHistory> findAllBidWinnersWithWinnerID(BidFilter filter, Pagination pagination);

	// FIND HIGH BIDDING. EAN SOKCHOMRERN. 06/10/2016
	public ArrayList<BidHistory> findHighBidding();
}
