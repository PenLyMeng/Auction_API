package org.khmeracademy.auction.repositories;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.khmeracademy.auction.entities.Gallery;
import org.khmeracademy.auction.entities.GalleryInputUpdate;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleryRepository {
	//READ
	String R_GALLERY="SELECT * FROM v_find_all_gallery WHERE product_name = #{product_name}";
	@Select(R_GALLERY)
	@Results(value={
			// product
			@Result(property="product.product_id", column="product_id"),
			@Result(property="product.product_name", column="product_name"),
			@Result(property="product.product_description", column="product_description"),
			@Result(property="product.qty", column="qty"),
			@Result(property="product.status", column="status"),
			@Result(property="product.supplier.supplier_id", column="supplier_id"),
			
			// supplier
			@Result(property="product.supplier.contact_name", column="contact_name"),
			@Result(property="product.supplier.address", column="address"),
			@Result(property="product.supplier.phone", column="phone"),
			@Result(property="product.supplier.email", column="email"),
			
			// category
			@Result(property="product.category.category_id", column="category_id"),
			@Result(property="product.category.category_name", column="category_name"),
			@Result(property="product.category.category_description", column="category_description")
			
	})
	public ArrayList<Gallery> findImageByProductName(String product_name);
	
	//CREATE
//	String C_GALLERY=" INSERT INTO "
//			+ " auc_gallery( "
//			+ " 	product_id, "
//			+ "		image_path ) "
//			+ " VALUES("
//			+ "		#{product_id}, "
//			+ "		#{image_path}) ";
//	@Insert(C_GALLERY)
//	public boolean addImage(GalleryInputUpdate g);
	
	final String C_BATCH_GALLERY  = ""
			+ "<script> INSERT INTO auc_gallery(product_id, image_path) VALUES "
			+ 	"<foreach  collection='image_path' item='path' separator=','>"
			+ 		"(#{product_id}, #{path})"
			+ 	"</foreach>"
			+ "</script>";
	@Insert(C_BATCH_GALLERY)
	public boolean addImage(GalleryInputUpdate g);
	
/*	final String U_BATCH_GALLERY  =""
			+ "<script>	"
			+ "	<foreach  collection='g.image_path' item='path' separator=';'>"
			+ " 	UPDATE auc_gallery SET image_path = #{path}"
			+ 		"WHERE product_id= #{g.product_id}"
			+ 	"</foreach>"
			+ "</script>";
	@Insert(U_BATCH_GALLERY)
	public boolean updateImage(GalleryInputUpdate g);*/
	
/*	String U_GALLERY=" UPDATE auc_gallery "
			+ "SET "
			+ " 	product_id= #{product_id}, "
			+ " 	image_path= #{image_path} "
			+ "WHERE "
			+ "		image_id= #{image_id}";
	@Update(U_GALLERY)
	public boolean updateImagePath(GalleryInputUpdate g);*/
	
	String D_GALLERY=""
			+ "<script>"
			+ "DELETE FROM auc_gallery WHERE image_id IN " 
			+ "	<foreach collection='image_delete' item='id' open='(' separator=',' close=')'>"
			+ " 	#{id}"
			+ 	"</foreach>"
			+ "</script>";
	@Delete(D_GALLERY)
	public boolean deleteImage(GalleryInputUpdate g);
	
}
