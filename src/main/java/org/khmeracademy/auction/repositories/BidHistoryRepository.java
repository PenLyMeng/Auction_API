package org.khmeracademy.auction.repositories;


import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.khmeracademy.auction.entities.BidHistory;

import org.khmeracademy.auction.entities.BidHistoryInputUpdate;
import org.khmeracademy.auction.entities.BidHistoryWithFirstProductImage;
import org.khmeracademy.auction.filtering.BidFilter;
import org.khmeracademy.auction.utils.Pagination;
import org.springframework.stereotype.Repository;

@Repository
public interface BidHistoryRepository {
	final String FIND_ALL_BID_HISTORY = "SELECT * FROM v_find_all_bid_history "
			+ "WHERE LOWER(user_name) LIKE LOWER('%' || #{filter.userName} || '%')" + "ORDER BY bid_id DESC "
			+ "LIMIT #{pagination.limit} " + "OFFSET #{pagination.offset} ";
	@Select(FIND_ALL_BID_HISTORY)
	@Results(value={
			// user
			@Result(property="user.user_id", column="user_id"),
			@Result(property="user.user_name", column="user_name"),
			@Result(property="user.first_name", column="first_name"),
			@Result(property="user.last_name", column="last_name"),			
			@Result(property="user.gender", column="gender"),
			@Result(property="user.dob", column="dob"),
			@Result(property="user.email", column="email"),
			@Result(property="user.photo", column="photo"),
			@Result(property="user.type", column="type"),
			@Result(property="user.status", column="status"),
			
			// auction
			@Result(property="auction.auction_id", column="auction_id"),
			@Result(property="auction.product.product_id", column="product_id"),
			@Result(property="auction.product_condition", column="product_condition"),
			@Result(property="auction.start_price", column="start_price"),
			@Result(property="auction.buy_price", column="buy_price"),
			@Result(property="auction.increment_price", column="increment_price"),
			@Result(property="auction.current_price", column="auction_current_price"),
			@Result(property="auction.start_date", column="start_date"),
			@Result(property="auction.end_date", column="end_date"),
			
			// product
			@Result(property="auction.product.product_id", column="product_id"),
			@Result(property="auction.product.product_name", column="product_name"),
			@Result(property="auction.product.product_description", column="product_description")
						
	})
	public ArrayList<BidHistory> findAllBidHistory(@Param("filter") BidFilter filter, @Param("pagination") Pagination pagination);
	
	@Select("SELECT COUNT(bid_id) FROM v_find_all_bid_history WHERE LOWER(user_name) LIKE LOWER('%' || #{filter.userName} || '%')")
	public Long count(@Param("filter") BidFilter filter);

	final String FIND_BID_HISTORY_BY_AUCTION_ID="SELECT * FROM v_find_all_bid_history WHERE auction_id = #{auction_id}";
	@Select(FIND_BID_HISTORY_BY_AUCTION_ID)
	@Results(value={
			// user
			@Result(property="user.user_id", column="user_id"),
			@Result(property="user.user_name", column="user_name"),
			@Result(property="user.first_name", column="first_name"),
			@Result(property="user.last_name", column="last_name"),			
			@Result(property="user.gender", column="gender"),
			@Result(property="user.dob", column="dob"),
			@Result(property="user.email", column="email"),
			@Result(property="user.photo", column="photo"),
			@Result(property="user.type", column="type"),
			@Result(property="user.status", column="status"),
			
			// auction
			@Result(property="auction.auction_id", column="auction_id"),
			@Result(property="auction.product.product_id", column="product_id"),
			@Result(property="auction.product_condition", column="product_condition"),
			@Result(property="auction.start_price", column="start_price"),
			@Result(property="auction.buy_price", column="buy_price"),
			@Result(property="auction.increment_price", column="increment_price"),
			@Result(property="auction.current_price", column="auction_current_price"),
			@Result(property="auction.start_date", column="start_date"),
			@Result(property="auction.end_date", column="end_date"),
			
			// product
			@Result(property="auction.product.product_id", column="product_id"),
			@Result(property="auction.product.product_name", column="product_name"),
			@Result(property="auction.product.product_description", column="product_description")
						
	})
	public ArrayList<BidHistory> findBidHistoryByAuctionId(int auction_id);
	
	final String FIND_BID_HISTORY_BY_USER_NAME="SELECT * FROM v_find_all_bid_history WHERE user_name = #{user_name} ";
	@Select(FIND_BID_HISTORY_BY_USER_NAME)
	@Results(value={
			// user
			@Result(property="user.user_id", column="user_id"),
			@Result(property="user.user_name", column="user_name"),
			@Result(property="user.first_name", column="first_name"),
			@Result(property="user.last_name", column="last_name"),			
			@Result(property="user.gender", column="gender"),
			@Result(property="user.dob", column="dob"),
			@Result(property="user.email", column="email"),
			@Result(property="user.photo", column="photo"),
			@Result(property="user.type", column="type"),
			@Result(property="user.status", column="status"),
			
			// auction
			@Result(property="auction.auction_id", column="auction_id"),
			@Result(property="auction.product.product_id", column="product_id"),
			@Result(property="auction.product_condition", column="product_condition"),
			@Result(property="auction.start_price", column="start_price"),
			@Result(property="auction.buy_price", column="buy_price"),
			@Result(property="auction.increment_price", column="increment_price"),
			@Result(property="auction.current_price", column="auction_current_price"),
			@Result(property="auction.start_date", column="start_date"),
			@Result(property="auction.end_date", column="end_date"),
			
			// product
			@Result(property="auction.product.product_id", column="product_id"),
			@Result(property="auction.product.product_name", column="product_name"),
			@Result(property="auction.product.product_description", column="product_description")
						
	})
	public ArrayList<BidHistory> findBidHistoryByUserName(String user_name);

	final String FIND_BID_HISTORY_BY_DATE="SELECT * FROM v_find_all_bid_history WHERE SUBSTRING(bid_date,1,10) = #{bid_date} ORDER BY bid_id DESC LIMIT 10";
	@Select(FIND_BID_HISTORY_BY_DATE)
	@Results(value={
			// user
			@Result(property="user.user_id", column="user_id"),
			@Result(property="user.user_name", column="user_name"),
			@Result(property="user.first_name", column="first_name"),
			@Result(property="user.last_name", column="last_name"),			
			@Result(property="user.gender", column="gender"),
			@Result(property="user.dob", column="dob"),
			@Result(property="user.email", column="email"),
			@Result(property="user.photo", column="photo"),
			@Result(property="user.type", column="type"),
			@Result(property="user.status", column="status"),
			
			// auction
			@Result(property="auction.auction_id", column="auction_id"),
			@Result(property="auction.product.product_id", column="product_id"),
			@Result(property="auction.product_condition", column="product_condition"),
			@Result(property="auction.start_price", column="start_price"),
			@Result(property="auction.buy_price", column="buy_price"),
			@Result(property="auction.increment_price", column="increment_price"),
			@Result(property="auction.current_price", column="auction_current_price"),
			@Result(property="auction.start_date", column="start_date"),
			@Result(property="auction.end_date", column="end_date"),
			
			// product
			@Result(property="auction.product.product_id", column="product_id"),
			@Result(property="auction.product.product_name", column="product_name"),
			@Result(property="auction.product.product_description", column="product_description")
						
	})
	public ArrayList<BidHistory> findBidHistoryByDate(String bid_date);

	final String ADD_BID_HISTORY="INSERT INTO auc_bid_history(auction_id,user_id,current_price,bid_date) " +
								"VALUES(#{auction_id},#{user_id},#{current_price}, now())";
	@Insert(ADD_BID_HISTORY)
	public boolean addBidHistory(BidHistoryInputUpdate b);
	
	final String UPDATE_BID_HISTORY=
					"	UPDATE auc_bid_history	 "+
					"	SET auction_id = #{auction_id},	 "+
					"	user_id = #{user_id},	 "+
					"	current_price = #{current_price},	 "+
					"	bid_date=#{bid_date}	 "+
					"	WHERE bid_id = #{bid_id}	 ";
	@Update(UPDATE_BID_HISTORY)
	public boolean updateBidHistory(BidHistoryInputUpdate b);


	final String DELETE_BID_HISTORY="DELETE FROM auc_bid_history WHERE bid_id = #{bid_id}";
	@Delete(DELETE_BID_HISTORY)
	
	public boolean deleteBidHistory(int bid_id);	

	
	

	//==== Additional functions
	final String FIND_BID_WINNER = "select * from pr_find_bid_winner()";
	@Select(FIND_BID_WINNER)
	@Results(value={
			// auction
			@Result(property="auction.auction_id", column="auction_id"),
			@Result(property="auction.product_condition", column="product_condition"),
			@Result(property="auction.start_price", column="start_price"),
			@Result(property="auction.buy_price", column="buy_price"),
			@Result(property="auction.increment_price", column="increment_price"),
			@Result(property="auction.current_price", column="auction_current_price"),
			@Result(property="auction.start_date", column="start_date"),
			@Result(property="auction.end_date", column="end_date"),
			@Result(property="auction.status", column="auction_status"),
			
			// product
			@Result(property="auction.product.product_id", column="product_id"),
			@Result(property="auction.product.product_name", column="product_name"),
			@Result(property="auction.product.product_description", column="product_description"),
			@Result(property="auction.product.qty", column="qty"),
			@Result(property="auction.product.status", column="product_status"),
			
			// user
			@Result(property="user.user_id", column="user_id"),
			@Result(property="user.user_name", column="user_name"),
			@Result(property="user.first_name", column="first_name"),
			@Result(property="user.last_name", column="last_name"),
			@Result(property="user.gender", column="gender"),
			@Result(property="user.dob", column="dob"),
			@Result(property="user.address", column="address"),
			@Result(property="user.email", column="email"),
			@Result(property="user.contact", column="contact"),
			@Result(property="user.photo", column="photo"),
			@Result(property="user.type", column="type"),
			@Result(property="user.status", column="user_status"),
			
			// category
			@Result(property="auction.product.category.category_id", column="category_id"),
			@Result(property="auction.product.category.category_name", column="category_name"),
			@Result(property="auction.product.category.category_description", column="category_description"),
			
			// supplier
			@Result(property="auction.product.supplier.supplier_id", column="supplier_id"),
			@Result(property="auction.product.supplier.contact_name", column="contact_name"),
			@Result(property="auction.product.supplier.address", column="supplier_address"),
			@Result(property="auction.product.supplier.email", column="supplier_email"),
			@Result(property="auction.product.supplier.phone", column="supplier_phone"),
			
			// brand
			@Result(property="auction.product.brand.brand_id", column="brand_id"),
			@Result(property="auction.product.brand.brand_name", column="brand_name"),
			@Result(property="auction.product.brand.brand_description", column="brand_description"),
			@Result(property="auction.product.brand.status", column="brand_status")
	})
		
	public ArrayList<BidHistory> findAllBidWinner();
	
	
	
	
	
	//==== Core function - used to find winner by the schedule
	final String FIND_BID_WINNER_REAL_TIME = "select * from v_find_bid_winner_every_second";
	@Select(FIND_BID_WINNER_REAL_TIME)
	@Results(value={
			// auction
			@Result(property="auction.auction_id", column="auction_id"),
			@Result(property="auction.product_condition", column="product_condition"),
			@Result(property="auction.start_price", column="start_price"),
			@Result(property="auction.buy_price", column="buy_price"),
			@Result(property="auction.increment_price", column="increment_price"),
			@Result(property="auction.current_price", column="auction_current_price"),
			@Result(property="auction.start_date", column="start_date"),
			@Result(property="auction.end_date", column="end_date"),
			@Result(property="auction.status", column="auction_status"),
			
			// product
			@Result(property="auction.product.product_id", column="product_id"),
			@Result(property="auction.product.product_name", column="product_name"),
			@Result(property="auction.product.product_description", column="product_description"),
			@Result(property="auction.product.qty", column="qty"),
			@Result(property="auction.product.status", column="product_status"),
			
			// user
			@Result(property="user.user_id", column="user_id"),
			@Result(property="user.user_name", column="user_name"),
			@Result(property="user.first_name", column="first_name"),
			@Result(property="user.last_name", column="last_name"),
			@Result(property="user.gender", column="gender"),
			@Result(property="user.dob", column="dob"),
			@Result(property="user.address", column="address"),
			@Result(property="user.email", column="email"),
			@Result(property="user.contact", column="contact"),
			@Result(property="user.photo", column="photo"),
			@Result(property="user.type", column="type"),
			@Result(property="user.status", column="user_status"),
			
			// category
			@Result(property="auction.product.category.category_id", column="category_id"),
			@Result(property="auction.product.category.category_name", column="category_name"),
			@Result(property="auction.product.category.category_description", column="category_description"),
			
			// supplier
			@Result(property="auction.product.supplier.supplier_id", column="supplier_id"),
			@Result(property="auction.product.supplier.contact_name", column="contact_name"),
			@Result(property="auction.product.supplier.address", column="supplier_address"),
			@Result(property="auction.product.supplier.email", column="supplier_email"),
			@Result(property="auction.product.supplier.phone", column="supplier_phone"),
			
			// brand
			@Result(property="auction.product.brand.brand_id", column="brand_id"),
			@Result(property="auction.product.brand.brand_name", column="brand_name"),
			@Result(property="auction.product.brand.brand_description", column="brand_description"),
			@Result(property="auction.product.brand.status", column="brand_status")
	})
	public ArrayList<BidHistory> findAllBidWinnerRealTime();
	
	
	/*//===== Find all bid history and product image by username
	final String FIND_BID_HISTORY_AND_PRODUCT_IMAGE_BY_USER_NAME="SELECT * FROM v_find_all_bid_history_and_product_image WHERE user_name = #{user_name} ";
	@Select(FIND_BID_HISTORY_AND_PRODUCT_IMAGE_BY_USER_NAME)
	@Results(value={
			// user
			@Result(property="user.user_id", column="user_id"),
			@Result(property="user.user_name", column="user_name"),
			@Result(property="user.first_name", column="first_name"),
			@Result(property="user.last_name", column="last_name"),			
			@Result(property="user.gender", column="gender"),
			@Result(property="user.dob", column="dob"),
			@Result(property="user.email", column="email"),
			@Result(property="user.photo", column="photo"),
			@Result(property="user.type", column="type"),
			@Result(property="user.status", column="status"),
			
			// auction
			@Result(property="auction.auction_id", column="auction_id"),
			@Result(property="auction.product.product_id", column="product_id"),
			@Result(property="auction.product_condition", column="product_condition"),
			@Result(property="auction.start_price", column="start_price"),
			@Result(property="auction.buy_price", column="buy_price"),
			@Result(property="auction.increment_price", column="increment_price"),
			@Result(property="auction.current_price", column="auction_current_price"),
			@Result(property="auction.start_date", column="start_date"),
			@Result(property="auction.end_date", column="end_date"),
			
			// product
			@Result(property="auction.product.product_id", column="product_id"),
			@Result(property="auction.product.product_name", column="product_name"),
			@Result(property="auction.product.product_description", column="product_description"),
			
			//Gallery (first image)
			@Result(property="image_id", column="image_id"),
			@Result(property="image_path", column="image_path")
						
	})
	public ArrayList<BidHistoryWithFirstProductImage> findBidHistoryAndImageByUserName(String user_name);
	*/
	
	
	//===== Find number of bid of user on each auction with product image
		final String FIND_NUMBER_BID_BY_USER_ID_FOR_EACH_AUCTION_IN_DETAILS="SELECT * FROM v_find_number_bid_by_user_id_for_each_auction_in_details WHERE user_id = #{user_id} ";
		@Select(FIND_NUMBER_BID_BY_USER_ID_FOR_EACH_AUCTION_IN_DETAILS)
		@Results(value={
				// user
				@Result(property="user.user_id", column="user_id"),
				@Result(property="user.user_name", column="user_name"),
				@Result(property="user.first_name", column="first_name"),
				@Result(property="user.last_name", column="last_name"),			
				@Result(property="user.gender", column="gender"),
				@Result(property="user.dob", column="dob"),
				@Result(property="user.address", column="address"),
				@Result(property="user.email", column="email"),
				@Result(property="user.photo", column="photo"),
				@Result(property="user.type", column="type"),
				@Result(property="user.status", column="status"),
				
				// auction
				@Result(property="auction.auction_id", column="auction_id"),
				@Result(property="auction.product_condition", column="product_condition"),
				@Result(property="auction.start_price", column="start_price"),
				@Result(property="auction.buy_price", column="buy_price"),
				@Result(property="auction.increment_price", column="increment_price"),
				@Result(property="auction.current_price", column="auction_current_price"),
				@Result(property="auction.start_date", column="start_date"),
				@Result(property="auction.end_date", column="end_date"),
				@Result(property="auction.status", column="auction_status"),
				
				// product
				@Result(property="auction.product.product_id", column="product_id"),
				@Result(property="auction.product.product_name", column="product_name"),
				@Result(property="auction.product.product_description", column="product_description"),
				
				// category
				@Result(property="auction.product.category.category_id", column="category_id"),
				@Result(property="auction.product.category.category_name", column="category_name"),
				@Result(property="auction.product.category.category_description", column="category_description"),
				
								
				// brand
				@Result(property="auction.product.brand.brand_id", column="brand_id"),
				@Result(property="auction.product.brand.brand_name", column="brand_name"),
				@Result(property="auction.product.brand.brand_description", column="brand_description"),
				@Result(property="auction.product.brand.status", column="brand_status"),
				
				//Gallery (first image)
				@Result(property="image_id", column="image_id"),
				@Result(property="image_path", column="image_path"),
				
				// number of bid & latest current price which user bids on each auction
				@Result(property="num_bid", column="num_bid"),
				@Result(property="user_latest_current_price", column="user_latest_current_price")
							
		})
		public ArrayList<BidHistoryWithFirstProductImage> findNumberBidByUserIdForEachAuctionInDetails(int user_id);
		
		
		// Function is used in viewbidhistory - 20160823 - BY EAN SOKCHOMRERN
		final String FIND_NUM_BID_AND_BIDDER_IN_AUCTION_PRODUCT_BY_AUC_ID = " SELECT * FROM v_find_num_bid_and_bidder_in_auction_product_by_auc_id  WHERE auction_id = #{auction_id}";

		@Select(FIND_NUM_BID_AND_BIDDER_IN_AUCTION_PRODUCT_BY_AUC_ID)
		@Results(value = {
				
				//bidhistory
				@Result(property="current_price", column="user_current_price"), // for each user, not for the whole auction
				
				// auction
				@Result(property="auction.auction_id", column="auction_id"),
				@Result(property="auction.product_condition", column="product_condition"),
				@Result(property="auction.start_price", column="start_price"),
				@Result(property="auction.buy_price", column="buy_price"),
				@Result(property="auction.increment_price", column="increment_price"),
				@Result(property="auction.current_price", column="auction_current_price"),
				@Result(property="auction.start_date", column="start_date"),
				@Result(property="auction.end_date", column="end_date"),
				@Result(property="auction.status", column="auction_status"),
				@Result(property="auction.created_by", column="created_by"),
				@Result(property="auction.created_date", column="created_date"),
				@Result(property="auction.comment", column="comment"),
				@Result(property="auction.winner_id", column="winner_id"),
				@Result(property="auction.num_bid", column="num_bid"),  // number_bid of auction
				@Result(property="auction.bid_current_price", column="bid_current_price"), // bid_current_price of auction
				
				
				// product
				@Result(property="auction.product.product_id", column="product_id"),
				@Result(property="auction.product.product_name", column="product_name"),
				@Result(property="auction.product.product_description", column="product_description"),
				@Result(property="auction.product.qty", column="qty"),
				@Result(property="auction.product.status", column="product_status"),
				
				// supplier
				@Result(property="auction.product.supplier.supplier_id", column="supplier_id"),
				
				// category
				@Result(property="auction.product.category.category_id", column="category_id"),
				@Result(property="auction.product.category.category_name", column="category_name"),
				
				
								
				// brand
				@Result(property="auction.product.brand.brand_id", column="brand_id"),
			
								
				// user
				// number of bid & latest current price which user bids on each auction
				@Result(property="user.user_id", column="user_id"),
				@Result(property="user.user_name", column="user_name"),
				@Result(property="user.first_name", column="first_name"),
				@Result(property="user.last_name", column="last_name"),	
				@Result(property="user.gender", column="gender"),
				@Result(property="user.dob", column="dob"),
				@Result(property="user.address", column="address"),
				@Result(property="user.email", column="email"),
				@Result(property="user.contact", column="contact"),
				@Result(property="user.photo", column="photo"),
				@Result(property="user.type", column="type"),
				@Result(property="user.status", column="user_status"),
				
		})
		public  ArrayList<BidHistory> findNumBidAndBidderInAuctionProductByAuctionId(int auction_id);
		
		// Function is used to find all bid winners - 20161004 - BY EAN SOKCHOMRERN
				final String FIND_ALL_BID_WINNERS_WITH_WINNER_ID = " SELECT * FROM v_all_bid_winners "
				+ " WHERE LOWER(user_name) LIKE LOWER('%' || #{filter.userName} || '%')" + "ORDER BY bid_id DESC "
				+ " LIMIT #{pagination.limit} " + "OFFSET #{pagination.offset} ";
				
				@Select(FIND_ALL_BID_WINNERS_WITH_WINNER_ID)
				@Results(value = {
						
						//bidhistory
						@Result(property="current_price", column="current_price"), 
						@Result(property="bid_date", column="bid_date"),
						
						// auction
						@Result(property="auction.auction_id", column="auction_id"),
						@Result(property="auction.product_condition", column="product_condition"),
						@Result(property="auction.start_price", column="start_price"),
						@Result(property="auction.buy_price", column="buy_price"),
						@Result(property="auction.increment_price", column="increment_price"),
						@Result(property="auction.current_price", column="auction_current_price"),
						@Result(property="auction.start_date", column="start_date"),
						@Result(property="auction.end_date", column="end_date"),
						@Result(property="auction.status", column="auction_status"),
//						@Result(property="auction.created_by", column="created_by"),
//						@Result(property="auction.created_date", column="created_date"),
//						@Result(property="auction.comment", column="comment"),
						@Result(property="auction.winner_id", column="winner_id"),
						//@Result(property="auction.num_bid", column="num_bid"),  // number_bid of auction
						//@Result(property="auction.bid_current_price", column="bid_current_price"), // bid_current_price of auction
						
						
						// product
						@Result(property="auction.product.product_id", column="product_id"),
						@Result(property="auction.product.product_name", column="product_name"),
						@Result(property="auction.product.product_description", column="product_description"),
						@Result(property="auction.product.qty", column="qty"),
						@Result(property="auction.product.status", column="product_status"),
						
						// supplier
						@Result(property="auction.product.supplier.supplier_id", column="supplier_id"),
						@Result(property="auction.product.supplier.contact_name", column="contact_name"),
						@Result(property="auction.product.supplier.address", column="supplier_address"),
						@Result(property="auction.product.supplier.email", column="supplier_email"),
						@Result(property="auction.product.supplier.phone", column="supplier_phone"),
						
						
						// category
						@Result(property="auction.product.category.category_id", column="category_id"),
						@Result(property="auction.product.category.category_name", column="category_name"),
						@Result(property="auction.product.category.category_description", column="category_description"),
						
										
						// brand
						@Result(property="auction.product.brand.brand_id", column="brand_id"),
						@Result(property="auction.product.brand.brand_name", column="brand_name"),
						@Result(property="auction.product.brand.brand_description", column="brand_description"),
						@Result(property="auction.product.brand.status", column="brand_status"),
					
										
						// user
						// number of bid & latest current price which user bids on each auction
						@Result(property="user.user_id", column="user_id"),
						@Result(property="user.user_name", column="user_name"),
						@Result(property="user.first_name", column="first_name"),
						@Result(property="user.last_name", column="last_name"),	
						@Result(property="user.gender", column="gender"),
						@Result(property="user.dob", column="dob"),
						@Result(property="user.address", column="address"),
						@Result(property="user.email", column="email"),
						@Result(property="user.contact", column="contact"),
						@Result(property="user.photo", column="photo"),
						@Result(property="user.type", column="type"),
						@Result(property="user.status", column="user_status"),
						
				})
		public  ArrayList<BidHistory> findAllBidWinnersWithWinnerID(@Param("filter") BidFilter filter, @Param("pagination") Pagination pagination);
		
		@Select("SELECT COUNT(bid_id) FROM v_all_bid_winners WHERE LOWER(user_name) LIKE LOWER('%' || #{filter.userName} || '%')")
		public Long count_winner(@Param("filter") BidFilter filter);		
		
		// FIND HIGH BIDDING. EAN SOKCHOMRERN. 06/10/2016
		final String FIND_HIGH_BIDDING="SELECT * FROM v_find_high_bidding ORDER BY bid_date DESC ";
		@Select(FIND_HIGH_BIDDING)
		@Results(value={
				// user
				@Result(property="user.user_name", column="user_name"),
								
				// auction
				@Result(property="auction.auction_id", column="auction_id"),
								
				// product
				@Result(property="auction.product.product_name", column="product_name"),
				
				//bidhistory
				@Result(property="current_price", column="current_price"), 
				@Result(property="bid_date", column="bid_date")			
							
		})
		public ArrayList<BidHistory> findHighBidding();
		
}
