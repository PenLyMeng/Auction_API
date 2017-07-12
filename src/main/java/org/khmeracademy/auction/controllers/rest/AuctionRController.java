package org.khmeracademy.auction.controllers.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.khmeracademy.auction.entities.Auction;
import org.khmeracademy.auction.entities.AuctionInputUpdate;
import org.khmeracademy.auction.entities.BiddingAuction;
import org.khmeracademy.auction.filtering.AuctionFilter;
import org.khmeracademy.auction.services.AuctionService;
import org.khmeracademy.auction.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api")
public class AuctionRController {
	
	@Autowired
	private AuctionService auctionService;
	
	/**
	 * Help method to get map object when select query
	 */
	public Map<String,Object> getMapObject(ArrayList<Auction> arr){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			if(!arr.isEmpty()){
				map.put("DATA", arr);
				map.put("MESSAGE", "SUCCESS");
				map.put("STATUS", true);
			}else{
				map.put("MESSAGE", "UNSUCCESS");
				map.put("STATUS", true);
			}				
		}catch(Exception e){
			map.put("MESSAGE", "ERROR");
			map.put("STATUS", false);
		}
		return map;
	}
	
	/**
	 * Help method to get map object when insert, update, delete 
	 */
	public Map<String,Object> getMapObjectAfterTransaction(boolean b){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			if(b){
				map.put("MESSAGE", "SUCCESS");
				map.put("STATUS", true);
			}else{
				map.put("MESSAGE", "UNSUCCESS");
				map.put("STATUS", true);
			}				
		}catch(Exception e){
			map.put("MESSAGE", "ERROR");
			map.put("STATUS", false);
		}
		return map;
	}
	
	
	@RequestMapping(value="/find-all-auctions",method=RequestMethod.GET)
	@ApiImplicitParams({
			@ApiImplicitParam(name="page", paramType="query", defaultValue="1", dataType="int" ) ,
			@ApiImplicitParam(name="limit", paramType="query", defaultValue="15", dataType="int"),
			@ApiImplicitParam(name="productName", paramType="query", defaultValue="")
	})
	
	public ResponseEntity<Map<String,Object>> findAllAuctions(@ApiIgnore AuctionFilter filter, @ApiIgnore Pagination pagination) {
		
		ArrayList<Auction> arr = auctionService.findAllAuctions(filter, pagination);
		Map<String,Object> map = getMapObject(arr);
		map.put("PAGINATION", pagination);
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
	@RequestMapping(value="/find-auction-by-id/{id}", method=RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> findAuctionByID(@PathVariable int id) {
		
		Auction a = auctionService.getAuctionByID(id);
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			if(a != null){
				map.put("DATA", a);
				map.put("MESSAGE", "SUCCESS");
				map.put("STATUS", true);
			}else{
				map.put("MESSAGE", "UNSUCCESS");
				map.put("STATUS", true);
			}				
		}catch(Exception e){
			map.put("MESSAGE", "ERROR");
			map.put("STATUS", false);
		}
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}

	@RequestMapping(value="/find-auction-by-product-name/{product_name}", method=RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> findAuctionByProductName(@PathVariable String product_name) {
		
		ArrayList<Auction> arr = auctionService.findAuctionByProductName(product_name);
		Map<String,Object> map = getMapObject(arr);
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}

	@RequestMapping(value="/find-auction-by-date/{trans_date}", method=RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> findAuctionByDate(@PathVariable String trans_date) {
		
		ArrayList<Auction> arr = auctionService.findAuctionByDate(trans_date);
		Map<String,Object> map = getMapObject(arr);
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
	@RequestMapping(value="/find-auction-product-by-category-id/{category_id}", method=RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> findAuctionProductByCategory(@PathVariable int category_id) {
		
		ArrayList<Auction> au = auctionService.findAuctionProductByCategory(category_id);
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			if(au != null){
				map.put("DATA", au);
				map.put("MESSAGE", "SUCCESS");
				map.put("STATUS", true);
			}else{
				map.put("MESSAGE", "UNSUCCESS");
				map.put("STATUS", true);
			}				
		}catch(Exception e){
			map.put("MESSAGE", "ERROR");
			map.put("STATUS", false);
		}
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	//TODO: Find Auction Brand By Category ID
	@RequestMapping(value="/find-auction-brand-by-category-id/{category_id}", method=RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> findAuctionBrandByCategory(@PathVariable int category_id) {
		
		ArrayList<Auction> au = auctionService.findAuctionBrandByCategory(category_id);
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			if(au != null){
				map.put("DATA", au);
				map.put("MESSAGE", "SUCCESS");
				map.put("STATUS", true);
			}else{
				map.put("MESSAGE", "UNSUCCESS");
				map.put("STATUS", true);
			}				
		}catch(Exception e){
			map.put("MESSAGE", "ERROR");
			map.put("STATUS", false);
		}
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}

	@RequestMapping(value="/add-auction", method=RequestMethod.POST)
	public ResponseEntity<Map<String,Object>> addAuction(@RequestBody AuctionInputUpdate a) {
		System.out.println(a);
		Map<String,Object> map = getMapObjectAfterTransaction(auctionService.addAuction(a));
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}

	
	@RequestMapping(value="/update-auction", method=RequestMethod.PUT)
	public ResponseEntity<Map<String,Object>> updateAuction(@RequestBody AuctionInputUpdate a) {
		Map<String,Object> map = getMapObjectAfterTransaction(auctionService.updateAuction(a));		
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}

	@RequestMapping(value="/delete-auction/{auction_id}", method=RequestMethod.DELETE)
	public ResponseEntity<Map<String,Object>> deleteAuction(@PathVariable int auction_id) {
		Map<String,Object> map = getMapObjectAfterTransaction(auctionService.deleteAuction(auction_id));		
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
	@RequestMapping(value="/find-bidding-auction-by-auction-id/{auction_id}", method=RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> findBiddingAuctionByAuctionId(@PathVariable int auction_id) {
		
		BiddingAuction ba = auctionService.findBiddingAuctionByAuctionId(auction_id);
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			if(ba != null){
				map.put("DATA", ba);
				map.put("MESSAGE", "SUCCESS");
				map.put("STATUS", true);
			}else{
				map.put("MESSAGE", "UNSUCCESS");
				map.put("STATUS", true);
			}				
		}catch(Exception e){
			map.put("MESSAGE", "ERROR");
			map.put("STATUS", false);
		}
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
	@RequestMapping(value="/update-status-and-winner-id-in-auction/{status}/{winner_id}/{comment}/{auction_id}", method=RequestMethod.PUT)
	public ResponseEntity<Map<String,Object>> updateStatusAndWinnerIdInAuction(
			@PathVariable String status, 
			@PathVariable int winner_id,
			@PathVariable String comment,
			@PathVariable int auction_id) {
		Map<String,Object> map = getMapObjectAfterTransaction(auctionService.updateStatusAndWinnerIdInAuction(status, winner_id,comment, auction_id));		
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/find-all-best-bidding-auctions",method=RequestMethod.GET)
	@ApiImplicitParams({
			@ApiImplicitParam(name="page", paramType="query", defaultValue="1", dataType="int" ) ,
			@ApiImplicitParam(name="limit", paramType="query", defaultValue="15", dataType="int"),
			@ApiImplicitParam(name="productName", paramType="query", defaultValue="")
	})
	public ResponseEntity<Map<String,Object>> findAllBestBiddingAuctions(@ApiIgnore AuctionFilter filter, @ApiIgnore Pagination pagination) {
		
		ArrayList<Auction> arr = auctionService.findAllBestBiddingAuctions(filter, pagination);
		Map<String,Object> map = getMapObject(arr);
		map.put("PAGINATION", pagination);
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
	
	// find expired auctions which have never been bidden
	@RequestMapping(value="/find-auction-end-date-is-expired-and-never-bidden",method=RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> findAuctionEndDateIsExpiredAndNeverBidden() {
		
		ArrayList<Auction> arr = auctionService.findAuctionEndDateIsExpiredAndNeverBidden();
		Map<String,Object> map = getMapObject(arr);
		
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
	//TODO: FIND ALL ACTION STATUS 1 ACTIVE FOR FRONT END
	@RequestMapping(value="/find-all-auctions-active",method=RequestMethod.GET)
	@ApiImplicitParams({
			@ApiImplicitParam(name="page", paramType="query", defaultValue="1", dataType="int" ) ,
			@ApiImplicitParam(name="limit", paramType="query", defaultValue="15", dataType="int"),
			@ApiImplicitParam(name="productName", paramType="query", defaultValue="")
	})
	public ResponseEntity<Map<String,Object>> findAllAuctionsActive(@ApiIgnore AuctionFilter filter, @ApiIgnore Pagination pagination) {
		ArrayList<Auction> arr = auctionService.findAllAuctionsActive(filter, pagination);
		Map<String,Object> map = getMapObject(arr);
		map.put("PAGINATION", pagination);
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
	// ==== Today's Auction Transactions ==== EAN SOKCHOMRERN === 06/10/2016
	@RequestMapping(value="/find-all-today-bid", method=RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> findAllTodayBid() {
		
		int num = auctionService.findAllTodayBid();
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			map.put("DATA", num);
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", true);
		}catch(Exception e){
			map.put("MESSAGE", "ERROR");
			map.put("STATUS", false);
		}
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/find-all-today-bidden-products", method=RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> findAllTodayBiddenProducts() {
		
		int num = auctionService.findAllTodayBiddenProducts();
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			map.put("DATA", num);
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", true);
		}catch(Exception e){
			map.put("MESSAGE", "ERROR");
			map.put("STATUS", false);
		}
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/find-all-today-bidders", method=RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> findAllTodayBidders() {
		
		int num = auctionService.findAllTodayBidders();
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			map.put("DATA", num);
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", true);
		}catch(Exception e){
			map.put("MESSAGE", "ERROR");
			map.put("STATUS", false);
		}
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/find-all-today-new-products", method=RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> findAllTodayNewProducts() {
		
		int num = auctionService.findAllTodayNewProducts();
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			map.put("DATA", num);
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", true);
		}catch(Exception e){
			map.put("MESSAGE", "ERROR");
			map.put("STATUS", false);
		}
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/find-today-total-revenue", method=RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> findTodayTotalRevenue() {
		
		int num = auctionService.findTodayTotalRevenue();
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			map.put("DATA", num);
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", true);
		}catch(Exception e){
			map.put("MESSAGE", "ERROR");
			map.put("STATUS", false);
		}
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		
	}

}
