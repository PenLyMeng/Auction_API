package org.khmeracademy.auction.controllers.rest;

import java.util.HashMap;
import java.util.Map;

import org.khmeracademy.auction.filtering.ArticleFilter;
import org.khmeracademy.auction.utils.Pagination;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping(value="/v1/api/articles")
public class TestRestController {
	
	
	@RequestMapping(method=RequestMethod.GET)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "title", dataType = "string", paramType = "query", defaultValue="",
	            value = "Title of the news"),
		@ApiImplicitParam(name = "id", dataType = "string", paramType = "query", defaultValue="",
        		value = "Title of the news"),
		@ApiImplicitParam(name = "date", dataType = "string", paramType = "query", defaultValue="",
				value = "Title of the news"),
	    @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", defaultValue="1",
	            value = "Results page you want to retrieve (1..N)"),
	    @ApiImplicitParam(name = "limit", dataType = "integer", paramType = "query", defaultValue="15",
	            value = "Number of records per page."),
	})
	public ResponseEntity<Map<String, Object>> findAllArticles(@ApiIgnore ArticleFilter filter, @ApiIgnore Pagination pagination){
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println(filter);
		System.out.println("LIMIT ==> " + pagination.getLimit() + " OFFSET==>" + pagination.offset());
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

}
