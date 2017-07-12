package org.khmeracademy.auction.controllers.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.khmeracademy.auction.entities.InvoiceDetail;
import org.khmeracademy.auction.entities.InvoiceDetailInputUpdate;
import org.khmeracademy.auction.filtering.InvoiceFilter;
import org.khmeracademy.auction.services.InvoiceDetailService;
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

@RestController()
@RequestMapping("/api")
public class InvoiceDetailRController {
	@Autowired
	private InvoiceDetailService invoiceDetailService;
	
	/**
	 * Help method to get map object when select query
	 */
	public Map<String,Object> getMapObject(ArrayList<InvoiceDetail> arr){
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
	@RequestMapping(value="/find-all-invoice-details", method=RequestMethod.GET)
	@ApiImplicitParams({
		@ApiImplicitParam(name="page", paramType="query", defaultValue="1", dataType="int"),
		@ApiImplicitParam(name="limit", paramType="query", defaultValue="10", dataType="int"),
		@ApiImplicitParam(name="fullName", paramType="query", defaultValue="")
	})
	public ResponseEntity<Map<String,Object>> findAllInvoiceDetails(@ApiIgnore InvoiceFilter filter, @ApiIgnore Pagination pagination){
		ArrayList<InvoiceDetail> arr = invoiceDetailService.findAllInvoiceDetails(filter, pagination);
		Map<String,Object> map = getMapObject(arr);
		map.put("PAGINATION", pagination);
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
	@RequestMapping(value="/find-invoice-detail-by-invoice-id/{invoice_id}", method=RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> findInvoiceDetailByInvoiceId(@PathVariable int invoice_id){
		InvoiceDetail inv = invoiceDetailService.findInvoiceDetailByInvoiceId(invoice_id);
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			if(inv!=null){
				map.put("DATA", inv);
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
	
	@RequestMapping(value="/find-invoice-detail-by-auction-id/{auction_id}", method=RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> findInvoiceDetailByAuctionId(@PathVariable int auction_id){
		ArrayList<InvoiceDetail> arr = invoiceDetailService.findInvoiceDetailByAuctionId(auction_id);
		Map<String,Object> map = getMapObject(arr);
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
	@RequestMapping(value="/add-invoice-detail", method=RequestMethod.POST)
	public ResponseEntity<Map<String,Object>> addInvoiceDetail(@RequestBody InvoiceDetailInputUpdate invd){
		Map<String,Object> map = getMapObjectAfterTransaction(invoiceDetailService.addInvoiceDetail(invd));
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
	@RequestMapping(value="/update-invoice-detail", method=RequestMethod.PUT)
	public ResponseEntity<Map<String,Object>> updateInvoiceDetail(@RequestBody InvoiceDetailInputUpdate invd){
		Map<String,Object> map = getMapObjectAfterTransaction(invoiceDetailService.updateInvoiceDetail(invd));
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
//	@RequestMapping(value="/delete-invoice-detail/{invoice_id}/{auction_id}", method=RequestMethod.DELETE)
//	public ResponseEntity<Map<String,Object>> deleteInvoiceDetail(@PathVariable int invoice_id,@PathVariable  int auction_id){
//		Map<String,Object> map = getMapObjectAfterTransaction(invoiceDetailService.deleteInvoiceDetail(invoice_id, auction_id));
//		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
//	}
}
