package org.khmeracademy.auction.controllers.rest;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.khmeracademy.auction.entities.InvoiceInputUpdate;
import org.khmeracademy.auction.filtering.InvoiceFilter;
import org.khmeracademy.auction.services.InvoiceService;
import org.khmeracademy.auction.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api")
public class InvoiceRController {
	@Autowired
	private InvoiceService invoiceService;
	
	/**
	 * Help method to get map object when select query
	 */
	public Map<String,Object> getMapObject(ArrayList<InvoiceInputUpdate> arr){
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
	@RequestMapping(value="/find-all-invoices",method=RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> findAllInvoices(){
		ArrayList<InvoiceInputUpdate> arr = invoiceService.findAllInvoices();
		Map<String,Object> map = getMapObject(arr);
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);	
	}
	
	@RequestMapping(value="/find-invoice-by-user-name/{user_name}",method=RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> findInvoiceByUserName(@PathVariable String user_name){
		ArrayList<InvoiceInputUpdate> arr = invoiceService.findInvoiceByUserName(user_name);
		Map<String,Object> map = getMapObject(arr);
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
	@RequestMapping(value="/find-invoice-by-date/{invoice_date}", method=RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> findInvoiceByDate(@PathVariable Date invoice_date){
		ArrayList<InvoiceInputUpdate> arr = invoiceService.findInvoiceByDate(invoice_date);
		Map<String,Object> map = getMapObject(arr);
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
	@RequestMapping(value="/add-invoice", method=RequestMethod.POST)
	public ResponseEntity<Map<String,Object>> addInvoice(@RequestBody InvoiceInputUpdate inv){
		Map<String,Object> map = getMapObjectAfterTransaction(invoiceService.addInvoice(inv));
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
	@RequestMapping(value="/update-invoice", method=RequestMethod.PUT)
	public ResponseEntity<Map<String,Object>> updateInvoice(@RequestBody InvoiceInputUpdate inv){
		Map<String,Object> map = getMapObjectAfterTransaction(invoiceService.updateInvoice(inv));
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	@RequestMapping(value="/delete-invoice/{invoice_id}", method=RequestMethod.DELETE)
	public ResponseEntity<Map<String,Object>> deleteInvoice(@PathVariable int invoice_id){
		Map<String,Object> map = getMapObjectAfterTransaction(invoiceService.deleteInvoice(invoice_id));
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
}
