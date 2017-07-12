package org.khmeracademy.auction.controllers.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.khmeracademy.auction.entities.Translate;
import org.khmeracademy.auction.services.TranslateService;
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
public class TranslateRController {
	@Autowired
	private TranslateService translateService;
	
	/**
	 * Help method to get map object when select query
	 */
	public Map<String,Object> getMapObject(ArrayList<Translate> arr){
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
	@RequestMapping(value="/find-all-translate", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> findAllTranslate(){
		ArrayList<Translate> arr = translateService.findAllTranslate();
		Map<String,Object> map = getMapObject(arr);
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	@RequestMapping(value="/find-translate-by-base-word/{base_word}", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> findTranslateByBaseWord(@PathVariable String base_word){
		Translate t = translateService.findTranslateByBaseWord(base_word); 
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			if(t!=null){
				map.put("DATA", t);
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
	@RequestMapping(value="/add-translate", method=RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> addTranslate(@RequestBody Translate t){
		Map<String,Object> map = getMapObjectAfterTransaction(translateService.addTranslate(t));
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	@RequestMapping(value="/update-translate", method=RequestMethod.PUT)
	public ResponseEntity<Map<String, Object>> updateTranslate(@RequestBody Translate t){
		Map<String,Object> map = getMapObjectAfterTransaction(translateService.updateTranslate(t));
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	@RequestMapping(value="/delete-translate", method=RequestMethod.DELETE)
	public ResponseEntity<Map<String, Object>> deleteTranslate(@PathVariable int id){
		Map<String,Object> map = getMapObjectAfterTransaction(translateService.deleteTranslate(id));
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
}
