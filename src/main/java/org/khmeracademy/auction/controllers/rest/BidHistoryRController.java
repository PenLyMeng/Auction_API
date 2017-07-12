package org.khmeracademy.auction.controllers.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.khmeracademy.auction.entities.BidHistory;

import org.khmeracademy.auction.entities.BidHistoryInputUpdate;
import org.khmeracademy.auction.entities.BidHistoryWithFirstProductImage;
import org.khmeracademy.auction.entities.UserCreditHistory;
import org.khmeracademy.auction.filtering.BidFilter;
import org.khmeracademy.auction.services.BidHistoryService;
import org.khmeracademy.auction.services.UserCreditHistoryService;
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

@RestController()
@RequestMapping("/api")
public class BidHistoryRController {
	@Autowired
	private BidHistoryService bidHistoryService;
	
	@Autowired
	private UserCreditHistoryService userCreditHistoryService;

	/**
	 * Help method to get map object when select query
	 */
	public Map<String, Object> getMapObject(ArrayList<BidHistory> arr) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (!arr.isEmpty()) {
				map.put("DATA", arr);
				map.put("MESSAGE", "SUCCESS");
				map.put("STATUS", true);
			} else {
				map.put("MESSAGE", "UNSUCCESS");
				map.put("STATUS", true);
			}
		} catch (Exception e) {
			map.put("MESSAGE", "ERROR");
			map.put("STATUS", false);
		}
		return map;
	}

	/**
	 * Help method to get map object when insert, update, delete
	 */
	public Map<String, Object> getMapObjectAfterTransaction(boolean b) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (b) {
				map.put("MESSAGE", "SUCCESS");
				map.put("STATUS", true);
			} else {
				map.put("MESSAGE", "UNSUCCESS");
				map.put("STATUS", true);
			}
		} catch (Exception e) {
			map.put("MESSAGE", "ERROR");
			map.put("STATUS", false);
		}
		return map;
	}

	@RequestMapping(value = "/find-all-bid-history", method = RequestMethod.GET)
	@ApiImplicitParams({
		@ApiImplicitParam(name="page", paramType="query", defaultValue="1", dataType="int"),
		@ApiImplicitParam(name="limit", paramType="query", defaultValue="10", dataType="int"),
		@ApiImplicitParam(name="userName", paramType="query", defaultValue="")
	})
	public ResponseEntity<Map<String, Object>> findAllBidHistory(@ApiIgnore BidFilter filter, @ApiIgnore Pagination pagination) {
		
		ArrayList<BidHistory> arr = bidHistoryService.findAllBidHistory(filter, pagination);
		Map<String, Object> map = getMapObject(arr);
		map.put("PAGINATION", pagination);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	
	
	
	@RequestMapping(value = "/find-bid-history-by-auction-id/{auction_id}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> findBidHistoryByAuctionId(@PathVariable int auction_id) {

		ArrayList<BidHistory> arr = bidHistoryService.findBidHistoryByAuctionId(auction_id);
		Map<String, Object> map = getMapObject(arr);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@RequestMapping(value = "/find-bid-history-by-user-name/{user_name}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> findBidHistoryByUserName(@PathVariable String user_name) {

		ArrayList<BidHistory> arr = bidHistoryService.findBidHistoryByUserName(user_name);
		Map<String, Object> map = getMapObject(arr);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@RequestMapping(value = "/find-bid-history-by-bid-date/{bid_date}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> findBidHistoryByDate(@PathVariable String bid_date) {

		ArrayList<BidHistory> arr = bidHistoryService.findBidHistoryByDate(bid_date);
		Map<String, Object> map = getMapObject(arr);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@RequestMapping(value = "/add-bid-history", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> addBidHistory(@RequestBody BidHistoryInputUpdate b) {

		Map<String, Object> map = new HashMap<String, Object>();
		UserCreditHistory userCredit = userCreditHistoryService.checkEndingAmound(b.getUser_id());
		if (userCredit != null) {
			if (userCredit.getEnding_amount() < 500) {
				map.put("MESSAGE", "YOU CANNOT BID MORE PLEASE TOPUP FIRST...");
				map.put("CODE", "0000");
				map.put("DATA", userCredit.getEnding_amount());
			} else {
				map.put("CODE", "9999");
				map = getMapObjectAfterTransaction(bidHistoryService.addBidHistory(b));
			}
		} else {
			map.put("MESSAGE", "USER NOT FOUND...");
			map.put("CODE", "1111");
		}

		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@RequestMapping(value = "/update-bid-history", method = RequestMethod.PUT)
	public ResponseEntity<Map<String, Object>> updateBidHistory(@RequestBody BidHistoryInputUpdate b) {
		Map<String, Object> map = getMapObjectAfterTransaction(bidHistoryService.updateBidHistory(b));
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@RequestMapping(value = "/delete-bid-history/{bid_id}", method = RequestMethod.DELETE)
	public ResponseEntity<Map<String, Object>> deleteBidHistory(@PathVariable int bid_id) {
		Map<String, Object> map = getMapObjectAfterTransaction(bidHistoryService.deleteBidHistory(bid_id));
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	// ----- additional functions
	@RequestMapping(value = "/find-all-bid-winner", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> findAllBidWinner() {

		ArrayList<BidHistory> arr = bidHistoryService.findAllBidWinner();
		Map<String, Object> map = getMapObject(arr);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@RequestMapping(value = "/find-all-bid-winner-real-time", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> findAllBidWinnerRealTime() {

		ArrayList<BidHistory> arr = bidHistoryService.findAllBidWinnerRealTime();
		Map<String, Object> map = getMapObject(arr);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

//	@RequestMapping(value = "/find-bid-history-and-image-by-user-name/{user_name}", method = RequestMethod.GET)
//	public ResponseEntity<Map<String, Object>> findBidHistoryAndImageByUserName(@PathVariable String user_name) {
//
//		ArrayList<BidHistoryWithFirstProductImage> arr = bidHistoryService.findBidHistoryAndImageByUserName(user_name);
//		Map<String, Object> map = new HashMap<String, Object>();
//		try {
//			if (!arr.isEmpty()) {
//				map.put("DATA", arr);
//				map.put("MESSAGE", "SUCCESS");
//				map.put("STATUS", true);
//			} else {
//				map.put("MESSAGE", "UNSUCCESS");
//				map.put("STATUS", true);
//			}
//		} catch (Exception e) {
//			map.put("MESSAGE", "ERROR");
//			map.put("STATUS", false);
//		}
//		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
//	}
	
	
	@RequestMapping(value = "/find-number-bid-by-user-id-for-each-auction-in-details/{user_id}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> findNumberBidByUserIdForEachAuctionInDetails(@PathVariable int user_id) {

		ArrayList<BidHistoryWithFirstProductImage> arr = bidHistoryService.findNumberBidByUserIdForEachAuctionInDetails(user_id);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (!arr.isEmpty()) {
				map.put("DATA", arr);
				map.put("MESSAGE", "SUCCESS");
				map.put("STATUS", true);
			} else {
				map.put("MESSAGE", "UNSUCCESS");
				map.put("STATUS", true);
			}
		} catch (Exception e) {
			map.put("MESSAGE", "ERROR");
			map.put("STATUS", false);
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/find-num-bid-and-bidder-in-auction-product-by-auc-id/{auction_id}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> findNumBidAndBidderInAuctionProductByAuctionId(@PathVariable int auction_id) {

		ArrayList<BidHistory> arr = bidHistoryService.findNumBidAndBidderInAuctionProductByAuctionId(auction_id);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (!arr.isEmpty()) {
				map.put("DATA", arr);
				map.put("MESSAGE", "SUCCESS");
				map.put("STATUS", true);
			} else {
				map.put("MESSAGE", "UNSUCCESS");
				map.put("STATUS", true);
			}
		} catch (Exception e) {
			map.put("MESSAGE", "ERROR");
			map.put("STATUS", false);
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	// find all bid winners - 04/10/2016 - Ean Sokchomrern
	@RequestMapping(value = "/find-all-bid-winners-with-winner-id", method = RequestMethod.GET)
	@ApiImplicitParams({
		@ApiImplicitParam(name="page", paramType="query", defaultValue="1", dataType="int"),
		@ApiImplicitParam(name="limit", paramType="query", defaultValue="10", dataType="int"),
		@ApiImplicitParam(name="userName", paramType="query", defaultValue="")
	})
	public ResponseEntity<Map<String, Object>> findAllBidWinnersWithWinnerID(@ApiIgnore BidFilter filter, @ApiIgnore Pagination pagination) {

		ArrayList<BidHistory> arr = bidHistoryService.findAllBidWinnersWithWinnerID(filter, pagination);
		Map<String, Object> map = getMapObject(arr);
		map.put("PAGINATION", pagination);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	// FIND HIGH BIDDING. EAN SOKCHOMRERN. 06/10/2016
	@RequestMapping(value = "/find-high-bidding", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> findHighBidding() {

		ArrayList<BidHistory> arr = bidHistoryService.findHighBidding();
		Map<String, Object> map = getMapObject(arr);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
}
