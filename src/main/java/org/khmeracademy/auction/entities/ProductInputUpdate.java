package org.khmeracademy.auction.entities;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class ProductInputUpdate {
	private int product_id;
	private String product_name;
	private String product_description;
	private int supplier_id;
	private int category_id;
	private int qty;
	private int brand_id;
	private int status;
	
	

	private List<MultipartFile> images;
	
	public List<MultipartFile> getImages() {
		return images;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setImages(List<MultipartFile> images) {
		this.images = images;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_description() {
		return product_description;
	}
	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}
	public int getSupplier_id() {
		return supplier_id;
	}
	public void setSupplier_id(int supplier_id) {
		this.supplier_id = supplier_id;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public int getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(int brand_id) {
		this.brand_id = brand_id;
	}
	
	@Override
	public String toString() {
		return "ProductInputUpdate [product_id=" + product_id + ", product_name=" + product_name
				+ ", product_description=" + product_description + ", supplier_id=" + supplier_id + ", category_id="
				+ category_id + ", qty=" + qty + ", brand_id=" + brand_id + ", status=" + status + ", images=" + images
				+ "]";
	}
	
	public static class ProductUpdate extends ProductInputUpdate{
		private List<Integer> deletedImageId;

		public List<Integer> getDeletedImageId() {
			return deletedImageId;
		}

		public void setDeletedImageId(List<Integer> deletedImageId) {
			this.deletedImageId = deletedImageId;
		}

		@Override
		public String toString() {
			return "ProductUpdate [deletedImageId=" + deletedImageId + "]";
		}
		
	}
	
	
	
	
}
