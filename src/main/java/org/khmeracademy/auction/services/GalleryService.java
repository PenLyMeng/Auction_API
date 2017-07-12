package org.khmeracademy.auction.services;

import java.util.ArrayList;

import org.khmeracademy.auction.entities.Gallery;
import org.khmeracademy.auction.entities.GalleryInputUpdate;

public interface GalleryService {

	public ArrayList<Gallery> findImageByProductName(String product_name);

	public boolean addImage(GalleryInputUpdate g);

	public boolean updateImagePath(GalleryInputUpdate g);

//	public boolean deleteImage(int image_id);

	boolean deleteImage(GalleryInputUpdate g);
}
