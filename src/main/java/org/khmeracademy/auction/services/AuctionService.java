package org.khmeracademy.auction.services;

import java.util.ArrayList;

import org.khmeracademy.auction.entities.Auction;
import org.khmeracademy.auction.entities.AuctionInputUpdate;

import org.khmeracademy.auction.entities.BiddingAuction;

import org.khmeracademy.auction.filtering.AuctionFilter;
import org.khmeracademy.auction.utils.Pagination;

public interface AuctionService {
	
	public ArrayList<Auction> findAllAuctions(AuctionFilter filter, Pagination pagination);
	public Auction getAuctionByID(int id);
	public ArrayList<Auction> findAuctionByProductName(String product_name);
	public ArrayList<Auction> findAuctionByDate(String trans_date);
	public ArrayList<Auction> findAuctionProductByCategory(int category_id);
	public boolean addAuction(AuctionInputUpdate a);	
	public boolean updateAuction(AuctionInputUpdate a);
	public boolean deleteAuction(int auction_id);	
	
	public BiddingAuction findBiddingAuctionByAuctionId(int auction_id);
	public ArrayList<Auction> findAuctionBrandByCategory(int category_id);
	
	public boolean updateStatusAndWinnerIdInAuction(String status, int winner_id, String comment, int auction_id);
	
	public ArrayList<Auction> findAllBestBiddingAuctions(AuctionFilter filter, Pagination pagination);
	
	public ArrayList<Auction> findAuctionEndDateIsExpiredAndNeverBidden();
	
	//TODO: FIND ALL ACTION STATUS 1 ACITVE
	public ArrayList<Auction> findAllAuctionsActive(AuctionFilter filter, Pagination pagination);

	// ==== Today's Auction Transactions ==== EAN SOKCHOMRERN === 06/10/2016
	public int findAllTodayBid();
	
	public int findAllTodayBiddenProducts();
	
	public int findAllTodayBidders();
	
	public int findAllTodayNewProducts();
	
	public int findTodayTotalRevenue();
}
