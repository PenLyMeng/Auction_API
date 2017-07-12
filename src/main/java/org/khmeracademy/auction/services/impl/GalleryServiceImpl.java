package org.khmeracademy.auction.services.impl;

import java.util.ArrayList;

import org.khmeracademy.auction.entities.Gallery;
import org.khmeracademy.auction.entities.GalleryInputUpdate;
import org.khmeracademy.auction.repositories.GalleryRepository;
import org.khmeracademy.auction.services.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GalleryServiceImpl implements GalleryService {

	@Autowired
	GalleryRepository gr;
	
	@Override
	public ArrayList<Gallery> findImageByProductName(String product_name) {
		// TODO Auto-generated method stub
		return gr.findImageByProductName(product_name);
	}

	@Override
	public boolean addImage(GalleryInputUpdate g) {
		// TODO Auto-generated method stub
		return true;//gr.addImage(g);
	}

	@Override
	public boolean updateImagePath(GalleryInputUpdate g) {
		// TODO Auto-generated method stub
		return true;//gr.updateImagePath(g);
	}

	@Override
	public boolean deleteImage(GalleryInputUpdate g) {
		// TODO Auto-generated method stub
		return gr.deleteImage(g);
	}

//	@Override
//	public boolean deleteImage(int image_id) {
//		// TODO Auto-generated method stub
//		return false;
//	}

}
