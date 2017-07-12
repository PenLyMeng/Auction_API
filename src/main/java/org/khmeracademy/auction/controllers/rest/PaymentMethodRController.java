package org.khmeracademy.auction.controllers.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.khmeracademy.auction.entities.PaymentMethod;
import org.khmeracademy.auction.services.PaymentMethodService;
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
public class PaymentMethodRController {
	@Autowired
	private PaymentMethodService paymentMethodService;
	
	/**
	 * Help method to get map object when select query
	 */
	public Map<String,Object> getMapObject(ArrayList<PaymentMethod> arr){
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
	
	@RequestMapping(value="/find-all-payment-methods", method=RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> findAllPaymentMethods(){
		ArrayList<PaymentMethod> arr = paymentMethodService.findAllPaymentMethods();
		Map<String,Object> map = getMapObject(arr);
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
	@RequestMapping(value="/find-payment-method-by-name/{payment_method_name}", method=RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> findPaymentMethodByName(@PathVariable String payment_method_name){
		ArrayList<PaymentMethod> arr = paymentMethodService.findPaymentMethodByName(payment_method_name);
		Map<String,Object> map = getMapObject(arr);
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
	@RequestMapping(value="/add-payment-method", method=RequestMethod.POST)
	public ResponseEntity<Map<String,Object>> addPaymentMethod(@RequestBody PaymentMethod p){
		Map<String,Object> map = getMapObjectAfterTransaction(paymentMethodService.addPaymentMethod(p));
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
	@RequestMapping(value="/update-payment-method", method=RequestMethod.PUT)
	public ResponseEntity<Map<String,Object>> updatePaymentMethod(PaymentMethod p){
		Map<String,Object> map = getMapObjectAfterTransaction(paymentMethodService.updatePaymentMethod(p));
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	@RequestMapping(value="/delete-payment-method/{payment_method_id}", method=RequestMethod.DELETE)
	public ResponseEntity<Map<String,Object>> deletePaymentMethod(@PathVariable int payment_method_id){
		Map<String,Object> map = getMapObjectAfterTransaction(paymentMethodService.deletePaymentMethod(payment_method_id));
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
}
