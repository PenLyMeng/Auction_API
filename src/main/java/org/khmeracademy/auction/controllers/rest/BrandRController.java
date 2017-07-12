package org.khmeracademy.auction.controllers.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.khmeracademy.auction.entities.Brand;
import org.khmeracademy.auction.filtering.BrandFilter;
import org.khmeracademy.auction.services.BrandService;
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
public class BrandRController {

	@Autowired 
	private BrandService bs;
//	* Help method to get map object when select query

	public Map<String,Object> getMapObject(ArrayList<Brand> arr){
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

	@RequestMapping(value="/find-all-brands",method=RequestMethod.GET)
	//get input box in swagger
		@ApiImplicitParams({
			@ApiImplicitParam(name="page", paramType="query", defaultValue="1", dataType="int" ) ,
			@ApiImplicitParam(name="limit", paramType="query", defaultValue="10", dataType="int"),
			@ApiImplicitParam(name="brandName", paramType="query", defaultValue="")
		})
	public ResponseEntity<Map<String, Object>> findAllBrands(@ApiIgnore BrandFilter filter, @ApiIgnore Pagination pagination) {
		ArrayList<Brand> arr = bs.findAllBrands(filter, pagination);
		Map<String, Object> map = getMapObject(arr);
		map.put("PAGINATION", pagination);
		return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
	}
	
	@RequestMapping(value="/find-brand-by-name/{brand_name}", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> findBrandByName(@PathVariable String brand_name){
		ArrayList<Brand> arr = bs.findBrandByName(brand_name);
		Map<String, Object> map = getMapObject(arr);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/find-brand-by-any-field/{str_search}", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> findBrandByAnyField(@PathVariable String str_search){
		ArrayList<Brand> arr=bs.findBrandByAnyField(str_search);
		Map<String, Object> map=getMapObject(arr);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/add-brand", method= RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> addBrand(@RequestBody Brand b){
		System.out.println("Brand =============>>>>>>"+b.getStatus());
		Map<String, Object> map= getMapObjectAfterTransaction(bs.addBrand(b));
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/update-brand", method=RequestMethod.PUT)
	public ResponseEntity<Map<String, Object>> updateBrand(@RequestBody Brand b ){
		Map<String, Object> map= getMapObjectAfterTransaction(bs.updateBrand(b));
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/delete-brand/{brand_id}", method=RequestMethod.DELETE)
	public ResponseEntity<Map<String, Object>> deleteBrand(@PathVariable int brand_id ){
		Map<String, Object> map= getMapObjectAfterTransaction(bs.deleteBrand(brand_id));
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/find-brand-by-id/{brand_id}", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> findBrandById(@PathVariable int brand_id){
		Brand b = bs.findBrandById(brand_id);
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			if(b!=null){
				map.put("DATA", b);
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
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
}
