package org.khmeracademy.auction.controllers.rest;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.khmeracademy.auction.entities.TopUp;
import org.khmeracademy.auction.entities.TopUpInputUpdate;
import org.khmeracademy.auction.entities.UserCreditHistory;
import org.khmeracademy.auction.services.TopUpService;
import org.khmeracademy.auction.services.UserCreditHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api")
public class TopUpRController {
	@Autowired
	private TopUpService topUpService;
	
	@Autowired
	private UserCreditHistoryService userCreditHistoryService;
	
	/**
	 * Help method to get map object when select query
	 */
	public Map<String,Object> getMapObject(ArrayList<TopUp> arr){
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
	
	
	@RequestMapping(value="/find-all-top-up",method=RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> findAllTopUp(){
		ArrayList<TopUp> arr = topUpService.findAllTopUp();
		Map<String,Object> map = getMapObject(arr);
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
	@RequestMapping(value="/find-top-up-by-currency/{currency}",method=RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> findTopUpByCurrency(@PathVariable String currency){
		ArrayList<TopUp> arr = topUpService.findTopUpByCurrency(currency);
		Map<String,Object> map = getMapObject(arr);
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
	@RequestMapping(value="/find-top-up-by-user-name/{user_name}",method=RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> findTopUpByUserName(@PathVariable String user_name){
		ArrayList<TopUp> arr = topUpService.findTopUpByUserName(user_name);
		Map<String,Object> map = getMapObject(arr);
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
	@RequestMapping(value="/find-top-up-by-date/{top_up_date}",method=RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> findTopUpByDate(@PathVariable Date top_up_date){
		ArrayList<TopUp> arr = topUpService.findTopUpByDate(top_up_date);
		Map<String,Object> map = getMapObject(arr);
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
	//TODO: ADD TOP UP BALANCE AND CHECK ENDING AMOUNT CREDIT
	@RequestMapping(value="/add-top-up",method=RequestMethod.POST)
	public ResponseEntity<Map<String,Object>> addTopUp(@RequestBody TopUpInputUpdate t){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		//1. ADDING TO BID HISTORY
		if(topUpService.addTopUp(t)){
			UserCreditHistory userCreditHistory = userCreditHistoryService.findByUserId(t.getUser_id());
			map.put("CODE", "9999");
			map.put("MESSAGE", "SUCCESSFULLY");
			map.put("DATA", userCreditHistory);
		}else{
			map.put("CODE", "0000");
			map.put("MESSAGE", "FAILURE");
		}
		
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/update-top-up",method=RequestMethod.PUT)
	public ResponseEntity<Map<String,Object>> updateTopUp(@RequestBody TopUpInputUpdate t){
		Map<String,Object> map = getMapObjectAfterTransaction(topUpService.updateTopUp(t));
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
	@RequestMapping(value="/delete-top-up/{top_up_id}",method=RequestMethod.DELETE)
	public ResponseEntity<Map<String,Object>> deleteTopUp(@PathVariable int top_up_id){
		Map<String,Object> map = getMapObjectAfterTransaction(topUpService.deleteTopUp(top_up_id));
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
}
