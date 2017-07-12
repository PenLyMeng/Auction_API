package org.khmeracademy.auction.services.impl;

import java.util.ArrayList;

import org.khmeracademy.auction.entities.Auction;
import org.khmeracademy.auction.entities.AuctionInputUpdate;
import org.khmeracademy.auction.entities.BiddingAuction;
import org.khmeracademy.auction.filtering.AuctionFilter;
import org.khmeracademy.auction.repositories.AuctionRepository;
import org.khmeracademy.auction.services.AuctionService;
import org.khmeracademy.auction.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuctionServiceImpl implements AuctionService{
	
	@Autowired
	private AuctionRepository auctionRepository;
	
	@Override
	public ArrayList<Auction> findAllAuctions(AuctionFilter filter, Pagination pagination) {
		System.out.println(pagination);
		pagination.setTotalCount(auctionRepository.count(filter));
		return auctionRepository.findAllAuctions(filter, pagination);
	}
	
	@Override
	public Auction getAuctionByID(int id) {
		return auctionRepository.getAuctionByID(id);
	}

	@Override
	public ArrayList<Auction> findAuctionByProductName(String product_name) {
		return auctionRepository.findAuctionByProductName(product_name);
	}

	@Override
	public ArrayList<Auction> findAuctionByDate(String trans_date) {
		return auctionRepository.findAuctionByDate(trans_date);
	}

	@Override
	public boolean addAuction(AuctionInputUpdate a) {
		return auctionRepository.addAuction(a);
	}

	@Override
	public boolean updateAuction(AuctionInputUpdate a) {
		return auctionRepository.updateAuction(a);
	}

	@Override
	public boolean deleteAuction(int auction_id) {
		return auctionRepository.deleteAuction(auction_id);
	}

	@Override
	public ArrayList<Auction> findAuctionProductByCategory(int category_id) {
		// TODO Auto-generated method stub
		return auctionRepository.findAuctionProductByCategory(category_id);
	}

	@Override
	public BiddingAuction findBiddingAuctionByAuctionId(int auction_id) {
		
		return auctionRepository.findBiddingAuctionByAuctionId(auction_id);
	}

	@Override
	public ArrayList<Auction> findAuctionBrandByCategory(int category_id) {
		// TODO Auto-generated method stub
		System.out.println(AuctionRepository.ADD_AUCTION);
		return auctionRepository.findAuctionBrandByCategory(category_id);
	}

	@Override
	public boolean updateStatusAndWinnerIdInAuction(String status, int winner_id, String comment, int auction_id) {
		// TODO Auto-generated method stub
		return auctionRepository.updateStatusAndWinnerIdInAuction(status, winner_id, comment, auction_id);
	}

	@Override
	public ArrayList<Auction> findAllBestBiddingAuctions(AuctionFilter filter, Pagination pagination) {
		pagination.setTotalCount(auctionRepository.countNumBid(filter));
		return auctionRepository.findAllBestBiddingAuctions(filter, pagination);
	}

	@Override
	public ArrayList<Auction> findAuctionEndDateIsExpiredAndNeverBidden() {
		
		return auctionRepository.findAuctionEndDateIsExpiredAndNeverBidden();
	}
	
	
	//TODO: FIND AUCTION STATUS 1 ACTIVE
	@Override
	public ArrayList<Auction> findAllAuctionsActive(AuctionFilter filter, Pagination pagination) {
		pagination.setTotalCount(auctionRepository.countActive(filter));
		return auctionRepository.findAllAuctionsActive(filter, pagination);
	}

	// ==== Today's Auction Transactions ==== EAN SOKCHOMRERN === 06/10/2016
	@Override
	public int findAllTodayBid() {
		// TODO Auto-generated method stub
		return auctionRepository.findAllTodayBid();
	}

	@Override
	public int findAllTodayBiddenProducts() {
		// TODO Auto-generated method stub
		return auctionRepository.findAllTodayBiddenProducts();
	}

	@Override
	public int findAllTodayBidders() {
		// TODO Auto-generated method stub
		return auctionRepository.findAllTodayBidders();
	}

	@Override
	public int findAllTodayNewProducts() {
		// TODO Auto-generated method stub
		return auctionRepository.findAllTodayNewProducts();
	}

	@Override
	public int findTodayTotalRevenue() {
		// TODO Auto-generated method stub
		return auctionRepository.findTodayTotalRevenue();
	}


}
