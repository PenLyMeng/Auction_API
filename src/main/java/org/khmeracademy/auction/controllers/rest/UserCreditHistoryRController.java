package org.khmeracademy.auction.controllers.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.khmeracademy.auction.entities.UserCreditHistory;
import org.khmeracademy.auction.entities.UserCreditHistoryInputUpdate;
import org.khmeracademy.auction.filtering.TopupFilter;
import org.khmeracademy.auction.services.UserCreditHistoryService;
import org.khmeracademy.auction.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import springfox.documentation.annotations.ApiIgnore;

@RestController()
@RequestMapping("/api")
public class UserCreditHistoryRController {
	@Autowired
	private UserCreditHistoryService userCreditHistoryService;
	
	/**
	 * Help method to get map object when select query
	 */
	public Map<String,Object> getMapObject(ArrayList<UserCreditHistory> arr){
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
	@RequestMapping(value="/find-all-user-credit-history", method=RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> findAllUserCreditHistory(){
		ArrayList<UserCreditHistory> arr = userCreditHistoryService.findAllUserCreditHistory();
		Map<String,Object> map = getMapObject(arr);
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	@RequestMapping(value="/find-user-credit-history-by-user-name/{user_name}", method=RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> findUserCreditHistoryByUserName(String user_name){
		ArrayList<UserCreditHistory> arr = userCreditHistoryService.findUserCreditHistoryByUserName(user_name);
		Map<String,Object> map = getMapObject(arr);
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	@RequestMapping(value="/add-user-credit-history", method=RequestMethod.POST)
	public ResponseEntity<Map<String,Object>> addUserCreditHistory(UserCreditHistoryInputUpdate uch){
		Map<String,Object> map = getMapObjectAfterTransaction(userCreditHistoryService.addUserCreditHistory(uch));
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	@RequestMapping(value="/update-user-credit-history", method=RequestMethod.PUT)
	public ResponseEntity<Map<String,Object>> updateUserCreditHistory(UserCreditHistoryInputUpdate uch){
		Map<String,Object> map = getMapObjectAfterTransaction(userCreditHistoryService.updateUserCreditHistory(uch));
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);	
	}
	@RequestMapping(value="/delete-user-credit-history/{credit_id}", method=RequestMethod.DELETE)
	public ResponseEntity<Map<String,Object>> deleteUserCreditHistory(@PathVariable int credit_id){
		Map<String,Object> map = getMapObjectAfterTransaction(userCreditHistoryService.deleteUserCreditHistory(credit_id));
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
	@RequestMapping(value="/{userId}/checking-ending-amount/", method= RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> checkingEndingAmount(@PathVariable int userId){
		Map<String, Object> map = new HashMap<String, Object>();
		UserCreditHistory user = userCreditHistoryService.checkEndingAmound(userId);
		if(user!=null){
			if(user.getEnding_amount()<500){
				map.put("MESSAGE", "YOU CANNOT BID MORE PLEASE TOPUP FIRST...");
				map.put("CODE", "0000");
				map.put("DATA", user.getEnding_amount());
			}else{
				map.put("MESSAGE", "YOU CAN BID MORE...");
				map.put("CODE", "9999");
				map.put("DATA", user.getEnding_amount());
			}
		}else{
			map.put("MESSAGE", "USER NOT FOUND...");
			map.put("CODE", "1111");
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{userId}/find-user-credit-by-id/", method= RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> findUserCreditHistoryByUserId(@PathVariable int userId){
		Map<String, Object> map = new HashMap<String, Object>();
		UserCreditHistory user = userCreditHistoryService.findByUserId(userId);
		try{
			if(user != null){
				map.put("DATA", user);
				map.put("MESSAGE", "SUCCESS");
				map.put("CODE", "9999");
			}else{
				map.put("MESSAGE", "UNSUCCESS");
				map.put("CODE", "9999");
			}				
		}catch(Exception e){
			map.put("MESSAGE", "ERROR");
			map.put("CODE", "1111");
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	// Find all active user credit history with ending amount - for Chanthem (23/08/2016)
	@RequestMapping(value="/find-all-active-user-credit-history-with-ending-amount", method=RequestMethod.GET)
	@ApiImplicitParams({
		@ApiImplicitParam(name="page", paramType="query", defaultValue="1", dataType="int"),
		@ApiImplicitParam(name="limit", paramType="query", defaultValue="10", dataType="int"),
		@ApiImplicitParam(name="fullName", paramType="query", defaultValue="")
	})
	public ResponseEntity<Map<String,Object>> findAllActiveUserCreditHistoryWithEndingAmount(@ApiIgnore TopupFilter filter, @ApiIgnore Pagination pagination){
		ArrayList<UserCreditHistory> arr = userCreditHistoryService.findAllActiveUserCreditHistoryWithEndingAmount(filter, pagination);
		Map<String,Object> map = getMapObject(arr);
		map.put("PAGINATION", pagination);
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
	
}
