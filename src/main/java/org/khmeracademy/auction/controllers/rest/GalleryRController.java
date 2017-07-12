package org.khmeracademy.auction.controllers.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.khmeracademy.auction.entities.Gallery;
import org.khmeracademy.auction.entities.GalleryInputUpdate;
import org.khmeracademy.auction.services.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GalleryRController {
	@Autowired 
	private GalleryService gs;
//	* Help method to get map object when select query

	public Map<String,Object> getMapObject(ArrayList<Gallery> arr){
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
	
//	 * Help method to get map object when insert, update, delete 

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

	@RequestMapping(value="/find-image-by-product-name/{product_name}",method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> findImageByProductName(@PathVariable String product_name) {
		ArrayList<Gallery> arr = gs.findImageByProductName(product_name);
		Map<String, Object> map = getMapObject(arr);
		return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
	}
	
	@RequestMapping(value="/add-image", method= RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> addImage(@RequestBody GalleryInputUpdate g){
		Map<String, Object> map= getMapObjectAfterTransaction(gs.addImage(g));
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/update-image-path", method=RequestMethod.PUT)
	public ResponseEntity<Map<String, Object>> updateImagePath(@RequestBody GalleryInputUpdate g){
		Map<String, Object> map= getMapObjectAfterTransaction(gs.updateImagePath(g));
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
//	@RequestMapping(value="/delete-image/{image_id}", method=RequestMethod.DELETE)
//	public ResponseEntity<Map<String, Object>> deleteImage(@PathVariable int  image_id ){
//		Map<String, Object> map= getMapObjectAfterTransaction(gs.deleteImage(image_id));
//		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
//	}

}
