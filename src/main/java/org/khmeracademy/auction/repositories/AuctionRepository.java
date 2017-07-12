package org.khmeracademy.auction.repositories;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.khmeracademy.auction.entities.Auction;
import org.khmeracademy.auction.entities.AuctionInputUpdate;
import org.khmeracademy.auction.entities.BiddingAuction;
import org.khmeracademy.auction.entities.Gallery;
import org.khmeracademy.auction.filtering.AuctionFilter;
import org.khmeracademy.auction.utils.Pagination;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionRepository {
	final String FIND_ALL_AUCTIONS = "SELECT * " + "FROM v_find_number_bid_in_auction_product_by_auction_id "
			+ "WHERE status<>'2' AND "  // excludes status 2 (deleted)
			+" LOWER(product_name) LIKE LOWER('%' || #{filter.productName} || '%')" + "ORDER BY auction_id DESC "
			+ "LIMIT #{pagination.limit} " + "OFFSET #{pagination.offset} ";

	@Select(FIND_ALL_AUCTIONS)
	@Results(value = {
			// Product
			@Result(property = "product.product_id", column = "product_id"),
			@Result(property = "product.product_name", column = "product_name"),
			@Result(property = "product.product_description", column = "product_description"),
			@Result(property = "product.status", column = "product_status"),

			// Brand
			@Result(property = "product.brand.brand_id", column = "brand_id"),
			@Result(property = "product.brand.brand_name", column = "brand_name"),
			@Result(property = "product.brand.brand_description", column = "brand_description"),
			@Result(property = "product.brand.status", column = "brand_status"),

			// Category
			@Result(property = "product.category.category_id", column = "category_id"),
			@Result(property = "product.category.category_name", column = "category_name"),
			@Result(property = "product.category.category_description", column = "category_description"),

			// Supplier
			@Result(property = "product.supplier.supplier_id", column = "supplier_id"),
			@Result(property = "product.supplier.contact_name", column = "contact_name"),
			@Result(property = "product.supplier.address", column = "address"),
			@Result(property = "product.supplier.phone", column = "phone"),
			@Result(property = "product.supplier.email", column = "email"),

			// gallery
			@Result(property = "product.product_id", column = "product_id"),
			@Result(property = "product.gallery", javaType = List.class, column = "product_id", many = @Many(select = "findAllGalleryByProductID")) 
	})	
	public ArrayList<Auction> findAllAuctions(@Param("filter") AuctionFilter filter,
			@Param("pagination") Pagination pagination);

	final String FIND_AUCTION_BY_ID = " SELECT * FROM v_find_number_bid_in_auction_product_by_auction_id WHERE auction_id = #{auction_id}";

	@Select(FIND_AUCTION_BY_ID)
	@Results(value = {
			// Product
			@Result(property = "product.product_id", column = "product_id"),
			@Result(property = "product.product_name", column = "product_name"),
			@Result(property = "product.product_description", column = "product_description"),
			@Result(property = "product.status", column = "product_status"),

			// Brand
			@Result(property = "product.brand.brand_id", column = "brand_id"),
			@Result(property = "product.brand.brand_name", column = "brand_name"),
			@Result(property = "product.brand.brand_description", column = "brand_description"),
			@Result(property = "product.brand.status", column = "brand_status"),

			// Category
			@Result(property = "product.category.category_id", column = "category_id"),
			@Result(property = "product.category.category_name", column = "category_name"),
			@Result(property = "product.category.category_description", column = "category_description"),

			// Supplier
			@Result(property = "product.supplier.supplier_id", column = "supplier_id"),
			@Result(property = "product.supplier.contact_name", column = "contact_name"),
			@Result(property = "product.supplier.address", column = "address"),
			@Result(property = "product.supplier.phone", column = "phone"),
			@Result(property = "product.supplier.email", column = "email"),

			// gallery
			@Result(property = "product.product_id", column = "product_id"),
			@Result(property = "product.gallery", javaType = List.class, column = "product_id", many = @Many(select = "findAllGalleryByProductID") ) })
	public Auction getAuctionByID(int id);

	String R_GALLERY_BY_ID = "SELECT * FROM auc_gallery WHERE product_id = #{product_id}";

	@Select(R_GALLERY_BY_ID)
	@Results(value = { @Result(property = "product.product_id", column = "product_id") })
	public ArrayList<Gallery> findAllGalleryByProductID(int product_id);

	final String FIND_AUCTION_BY_PRODUCT_NAME = " SELECT * FROM v_find_all_auctions WHERE product_name = #{product_name}";

	@Select(FIND_AUCTION_BY_PRODUCT_NAME)
	@Results(value = {
			// Product
			@Result(property = "product.product_id", column = "product_id"),
			@Result(property = "product.product_name", column = "product_name"),
			@Result(property = "product.product_description", column = "product_description"),
			@Result(property = "product.status", column = "product_status"),

			// Brand
			@Result(property = "product.brand.brand_id", column = "brand_id"),
			@Result(property = "product.brand.brand_name", column = "brand_name"),
			@Result(property = "product.brand.brand_description", column = "brand_description"),
			@Result(property = "product.brand.status", column = "brand_status"),

			// Category
			@Result(property = "product.category.category_id", column = "category_id"),
			@Result(property = "product.category.category_name", column = "category_name"),
			@Result(property = "product.category.category_description", column = "category_description"),

			// Supplier
			@Result(property = "product.supplier.supplier_id", column = "supplier_id"),
			@Result(property = "product.supplier.contact_name", column = "contact_name"),
			@Result(property = "product.supplier.address", column = "address"),
			@Result(property = "product.supplier.phone", column = "phone"),
			@Result(property = "product.supplier.email", column = "email")

	})
	public ArrayList<Auction> findAuctionByProductName(String product_name);

	final String FIND_AUCTION_BY_DATE = "SELECT * FROM v_find_all_auctions WHERE start_date:: TEXT = #{trans_date} OR end_date :: TEXT = #{trans_date}";

	@Select(FIND_AUCTION_BY_DATE)
	@Results(value = {
			// Product
			@Result(property = "product.product_id", column = "product_id"),
			@Result(property = "product.product_name", column = "product_name"),
			@Result(property = "product.product_description", column = "product_description"),
			@Result(property = "product.status", column = "product_status"),

			// Brand
			@Result(property = "product.brand.brand_id", column = "brand_id"),
			@Result(property = "product.brand.brand_name", column = "brand_name"),
			@Result(property = "product.brand.brand_description", column = "brand_description"),
			@Result(property = "product.brand.status", column = "brand_status"),

			// Category
			@Result(property = "product.category.category_id", column = "category_id"),
			@Result(property = "product.category.category_name", column = "category_name"),
			@Result(property = "product.category.category_description", column = "category_description"),

			// Supplier
			@Result(property = "product.supplier.supplier_id", column = "supplier_id"),
			@Result(property = "product.supplier.contact_name", column = "contact_name"),
			@Result(property = "product.supplier.address", column = "address"),
			@Result(property = "product.supplier.phone", column = "phone"),
			@Result(property = "product.supplier.email", column = "email")

	})
	public ArrayList<Auction> findAuctionByDate(String trans_date);

	final String FIND_AUCTION_PRODUCT_BY_CATEGORY = "SELECT * FROM v_find_all_auctions_with_num_bid WHERE category_id = #{category_id} AND status='1' ";

	@Select(FIND_AUCTION_PRODUCT_BY_CATEGORY)
	@Results(value = {
			// Product
			@Result(property = "product.product_id", column = "product_id"),
			@Result(property = "product.product_name", column = "product_name"),
			@Result(property = "product.product_description", column = "product_description"),
			@Result(property = "product.status", column = "product_status"),

			// Brand
			@Result(property = "product.brand.brand_id", column = "brand_id"),
			@Result(property = "product.brand.brand_name", column = "brand_name"),
			@Result(property = "product.brand.brand_description", column = "brand_description"),
			@Result(property = "product.brand.status", column = "brand_status"),

			// Category
			@Result(property = "product.category.category_id", column = "category_id"),
			@Result(property = "product.category.category_name", column = "category_name"),
			@Result(property = "product.category.category_description", column = "category_description"),

			// Supplier
			@Result(property = "product.supplier.supplier_id", column = "supplier_id"),
			@Result(property = "product.supplier.contact_name", column = "contact_name"),
			@Result(property = "product.supplier.address", column = "address"),
			@Result(property = "product.supplier.phone", column = "phone"),
			@Result(property = "product.supplier.email", column = "email"),

			// gallery
			@Result(property = "product.product_id", column = "product_id"),
			@Result(property = "product.gallery", javaType = List.class, column = "product_id", many = @Many(select = "findAllGalleryByProductID") ) })
			
			
	public ArrayList<Auction> findAuctionProductByCategory(int category_id);

	final String SLELECT_AUCTION_BRAND_BY_CATEGORY = "SELECT brand_name FROM v_find_all_auctions WHERE category_id= #{category_id}";
	@Select(SLELECT_AUCTION_BRAND_BY_CATEGORY)
	@Results(value = {
			// Brand
			@Result(property = "product.brand.brand_id", column = "brand_id"),
			@Result(property = "product.brand.brand_name", column = "brand_name"),
			@Result(property = "product.brand.brand_description", column = "brand_description"),
			@Result(property = "product.brand.status", column = "brand_status") })
	public ArrayList<Auction> findAuctionBrandByCategory(int category_id);

	final String ADD_AUCTION = "	INSERT INTO auc_auction(	" + "	product_id,	" + "	product_condition,	"
			+ "	start_price,	" + "	buy_price,	" + "	increment_price,	" + "	current_price,	"
			+ "	start_date,	" + "	end_date,	" + "	status,	" + "	created_by,	" + "	created_date,	"
			+ "	comment)	" + "	VALUES (	" + "	#{product_id},	" + "	#{product_condition},	"
			+ "	#{start_price}, " + " #{buy_price},	" + " #{increment_price},	" + "	#{current_price},	"
			+ "	to_timestamp(#{start_date}, 'DD/MM/YYYY HH24:MI:SS')	" + ",	to_timestamp(#{end_date}, 'DD/MM/YYYY HH24:MI:SS'),	" + "	#{status},	" + "	#{created_by},	"
			+ "	to_timestamp(#{created_date}, 'DD/MM/YYYY HH24:MI:SS'),	" + "	#{comment}	" + "	)	";

	@Insert(ADD_AUCTION)
	public boolean addAuction(AuctionInputUpdate a);

	final String UPDATE_AUCTION = "	UPDATE auc_auction	" + "	SET product_id = #{product_id},	"
			+ "	product_condition = #{product_condition},	" + "	start_price = #{start_price},	"
			+ "	buy_price = #{buy_price},	" + "	increment_price = #{increment_price},	"
			+ "	current_price = #{current_price},	" + "	start_date = to_timestamp(#{start_date}, 'DD/MM/YYYY HH24:MI:SS'),	"
			+ "	end_date = to_timestamp(#{end_date}, 'DD/MM/YYYY HH24:MI:SS'),	" + "	status = #{status},	" + "	comment = #{comment}	"
			+ "	WHERE auction_id = #{auction_id}	";

	@Update(UPDATE_AUCTION)
	public boolean updateAuction(AuctionInputUpdate a);

	final String DELETE_AUCTION = "UPDATE auc_auction SET status = 2 WHERE auction_id = #{auction_id}"; // 2.
																										// deleted

	@Delete(DELETE_AUCTION)
	public boolean deleteAuction(int auction_id);

	@Select("SELECT COUNT(auction_id) FROM v_find_all_auctions WHERE LOWER(product_name) LIKE LOWER('%' || #{filter.productName} || '%')")
	public Long count(@Param("filter") AuctionFilter filter);

	// ==== Additional function

	final String FIND_BIDDING_AUCTION_BY_AUCTION_ID = "SELECT * FROM v_auction_and_bid where auction_id=#{auction_id}";

	@Select(FIND_ALL_AUCTIONS)
	@Results(value = {
			// Product
			@Result(property = "product.product_id", column = "product_id"),
			@Result(property = "product.product_name", column = "product_name"),
			@Result(property = "product.product_description", column = "product_description"),
			@Result(property = "product.status", column = "product_status"),

			// Brand
			@Result(property = "product.brand.brand_id", column = "brand_id"),
			@Result(property = "product.brand.brand_name", column = "brand_name"),
			@Result(property = "product.brand.brand_description", column = "brand_description"),
			@Result(property = "product.brand.status", column = "brand_status"),

			// Category
			@Result(property = "product.category.category_id", column = "category_id"),
			@Result(property = "product.category.category_name", column = "category_name"),
			@Result(property = "product.category.category_description", column = "category_description"),

			// Supplier
			@Result(property = "product.supplier.supplier_id", column = "supplier_id"),
			@Result(property = "product.supplier.contact_name", column = "contact_name"),
			@Result(property = "product.supplier.address", column = "address"),
			@Result(property = "product.supplier.phone", column = "phone"),
			@Result(property = "product.supplier.email", column = "email")

			// num_bid and bid_current_parent are matched automatically

	})
	public BiddingAuction findBiddingAuctionByAuctionId(int auction_id);
	
	// === Update status, winner_id and comment. This function can be called by winner or expired auction
	final String UPDATE_STATUS_AND_WINNER_ID_IN_AUCTION = "UPDATE auc_auction SET status=#{status}, winner_id=#{winner_id}, comment=#{comment} WHERE auction_id=#{auction_id} ";
	@Update(UPDATE_STATUS_AND_WINNER_ID_IN_AUCTION)
	public boolean updateStatusAndWinnerIdInAuction(@Param("status") String status,@Param("winner_id") int winner_id, @Param("comment") String comment, @Param("auction_id") int auction_id);

	
	//FIND BEST BIDDING
	final String FIND_ALL_BEST_BIDDING_AUCTIONS = "SELECT * " + "FROM v_find_number_bid_in_auction_product_by_auction_id "
			+ "WHERE status='1' AND "  // excludes status 2 (deleted)
			+ " num_bid >=5 AND "
			+" LOWER(product_name) LIKE LOWER('%' || #{filter.productName} || '%')" + "ORDER BY num_bid DESC "
			+ "LIMIT #{pagination.limit} " + "OFFSET #{pagination.offset} ";

	@Select(FIND_ALL_BEST_BIDDING_AUCTIONS)
	@Results(value = {
			// Product
			@Result(property = "product.product_id", column = "product_id"),
			@Result(property = "product.product_name", column = "product_name"),
			@Result(property = "product.product_description", column = "product_description"),
			@Result(property = "product.status", column = "product_status"),

			// Brand
			@Result(property = "product.brand.brand_id", column = "brand_id"),
			@Result(property = "product.brand.brand_name", column = "brand_name"),
			@Result(property = "product.brand.brand_description", column = "brand_description"),
			@Result(property = "product.brand.status", column = "brand_status"),

			// Category
			@Result(property = "product.category.category_id", column = "category_id"),
			@Result(property = "product.category.category_name", column = "category_name"),
			@Result(property = "product.category.category_description", column = "category_description"),

			// Supplier
			@Result(property = "product.supplier.supplier_id", column = "supplier_id"),
			@Result(property = "product.supplier.contact_name", column = "contact_name"),
			@Result(property = "product.supplier.address", column = "address"),
			@Result(property = "product.supplier.phone", column = "phone"),
			@Result(property = "product.supplier.email", column = "email"),

			// gallery
			@Result(property = "product.product_id", column = "product_id"),
			@Result(property = "product.gallery", javaType = List.class, column = "product_id", many = @Many(select = "findAllGalleryByProductID")) 
	})	
	public ArrayList<Auction> findAllBestBiddingAuctions(@Param("filter") AuctionFilter filter,
			@Param("pagination") Pagination pagination);
	
	@Select("SELECT COUNT(auction_id) FROM v_find_number_bid_in_auction_product_by_auction_id WHERE NUM_BID>=5")
	public Long countNumBid(@Param("filter") AuctionFilter filter);
	
	
	
	// Find all expired auctions which have never been bidden. Then update status to inactive(0)
	// Criteria in view of database are: status = '1' AND end_date <= now() AND num_bid = 0 
	final String FIND_AUCTION_END_DATE_IS_EXPIRED_AND_NEVER_BIDDEN = " SELECT * FROM v_find_auction_end_date_is_expired_and_never_bidden ";

	@Select(FIND_AUCTION_END_DATE_IS_EXPIRED_AND_NEVER_BIDDEN)
	@Results(value = {
			// Product
			@Result(property = "product.product_id", column = "product_id"),
			@Result(property = "product.product_name", column = "product_name"),
			@Result(property = "product.product_description", column = "product_description"),
			@Result(property = "product.status", column = "product_status"),

			// Brand
			@Result(property = "product.brand.brand_id", column = "brand_id"),
			@Result(property = "product.brand.brand_name", column = "brand_name"),
			@Result(property = "product.brand.brand_description", column = "brand_description"),
			@Result(property = "product.brand.status", column = "brand_status"),

			// Category
			@Result(property = "product.category.category_id", column = "category_id"),
			@Result(property = "product.category.category_name", column = "category_name"),
			@Result(property = "product.category.category_description", column = "category_description"),

			// Supplier
			@Result(property = "product.supplier.supplier_id", column = "supplier_id"),
			@Result(property = "product.supplier.contact_name", column = "contact_name"),
			@Result(property = "product.supplier.address", column = "address"),
			@Result(property = "product.supplier.phone", column = "phone"),
			@Result(property = "product.supplier.email", column = "email"),

			// gallery
			@Result(property = "product.product_id", column = "product_id"),
			@Result(property = "product.gallery", javaType = List.class, column = "product_id", many = @Many(select = "findAllGalleryByProductID") ) })
	public ArrayList<Auction> findAuctionEndDateIsExpiredAndNeverBidden();
	
	//TODO: FIND ALL AUCTION STATUS 1 ACTIVE
	final String FIND_ALL_AUCTIONS_ACTIVE = "SELECT * " + "FROM v_find_number_bid_in_auction_product_by_auction_id "
			+ "WHERE status='1' AND "  // excludes status 2 (deleted)
			+" LOWER(product_name) LIKE LOWER('%' || #{filter.productName} || '%')" + "ORDER BY auction_id DESC "
			+ "LIMIT #{pagination.limit} " + "OFFSET #{pagination.offset} ";

	@Select(FIND_ALL_AUCTIONS_ACTIVE)
	@Results(value = {
			// Product
			@Result(property = "product.product_id", column = "product_id"),
			@Result(property = "product.product_name", column = "product_name"),
			@Result(property = "product.product_description", column = "product_description"),
			@Result(property = "product.status", column = "product_status"),

			// Brand
			@Result(property = "product.brand.brand_id", column = "brand_id"),
			@Result(property = "product.brand.brand_name", column = "brand_name"),
			@Result(property = "product.brand.brand_description", column = "brand_description"),
			@Result(property = "product.brand.status", column = "brand_status"),

			// Category
			@Result(property = "product.category.category_id", column = "category_id"),
			@Result(property = "product.category.category_name", column = "category_name"),
			@Result(property = "product.category.category_description", column = "category_description"),

			// Supplier
			@Result(property = "product.supplier.supplier_id", column = "supplier_id"),
			@Result(property = "product.supplier.contact_name", column = "contact_name"),
			@Result(property = "product.supplier.address", column = "address"),
			@Result(property = "product.supplier.phone", column = "phone"),
			@Result(property = "product.supplier.email", column = "email"),

			// gallery
			@Result(property = "product.product_id", column = "product_id"),
			@Result(property = "product.gallery", javaType = List.class, column = "product_id", many = @Many(select = "findAllGalleryByProductID")) 
	})	
	public ArrayList<Auction> findAllAuctionsActive(@Param("filter") AuctionFilter filter,
			@Param("pagination") Pagination pagination);
	
	@Select("SELECT COUNT(auction_id) FROM v_find_number_bid_in_auction_product_by_auction_id WHERE status = '1'")
	public Long countActive(@Param("filter") AuctionFilter filter);
	
	// ==== Today's Auction Transactions ==== EAN SOKCHOMRERN === 06/10/2016
	@Select("SELECT * FROM v_find_all_today_bid")
	public int findAllTodayBid();
	
	@Select("SELECT * FROM v_find_all_today_bidden_products")
	public int findAllTodayBiddenProducts();
	
	@Select("SELECT * FROM v_find_all_today_bidders")
	public int findAllTodayBidders();
	
	@Select("SELECT * FROM v_find_all_today_new_products")
	public int findAllTodayNewProducts();
	
	@Select("SELECT * FROM v_find_today_total_revenue")
	public int findTodayTotalRevenue();
	
	
	
	
	
	
	
	
	
	
	
	
	
}
