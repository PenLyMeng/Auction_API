package org.khmeracademy.auction.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Image {

	@JsonProperty("ORIGINAL_IMAGE")
	private String originalImage;
	
	@JsonProperty("THUMBNAIL_IMAGE")
	private String thumbnailImage;
	
	public String getOriginalImage() {
		return originalImage;
	}
	public void setOriginalImage(String originalImage) {
		this.originalImage = originalImage;
	}
	public String getThumbnailImage() {
		return thumbnailImage;
	}
	public void setThumbnailImage(String thumbnailImage) {
		this.thumbnailImage = thumbnailImage;
	}
	
}
