package org.khmeracademy.auction.controllers.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.khmeracademy.auction.entities.User;
import org.khmeracademy.auction.entities.UserInputUpdate;
import org.khmeracademy.auction.filtering.UserFilter;
import org.khmeracademy.auction.services.UserService;
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
public class UserRController {
	@Autowired
	private UserService userService;

	// Request All Users
	@RequestMapping(value = "/get-all-users", method = RequestMethod.GET)
	@ApiImplicitParams({ @ApiImplicitParam(name = "page", dataType = "int", paramType = "query", defaultValue = "1"),
			@ApiImplicitParam(name = "limit", dataType = "int", paramType = "query", defaultValue = "10"),
			@ApiImplicitParam(name = "userName", paramType = "query", defaultValue = "") })
	public ResponseEntity<Map<String, Object>> getAllUsers(@ApiIgnore UserFilter filter,
			@ApiIgnore Pagination pagination) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			ArrayList<User> user = userService.getAllUsers(filter, pagination);
			if (!user.isEmpty()) {
				map.put("DATA", user);
				map.put("STATUS", true);
				map.put("MESSAGE", "DATA FOUND!");
				map.put("PAGINATION", pagination);
			} else {
				map.put("STATUS", true);
				map.put("MESSAGE", "DATA NOT FOUND!");
				map.put("PAGINATION", pagination);
			}
		} catch (Exception e) {
			map.put("STATUS", false);
			map.put("MESSAGE", "Error!");
			e.printStackTrace();
		}

		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	// Request User by id
	@RequestMapping(value = "/get-user-by-id/{user_id}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> findUserById(@PathVariable int user_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			User user = userService.findUserById(user_id);
			if (user != null) {
				map.put("DATA", user);
				map.put("STATUS", true);
				map.put("MESSAGE", "DATA FOUND!");
			} else {
				map.put("STATUS", true);
				map.put("MESSAGE", "DATA NOT FOUND!");
			}
		} catch (Exception e) {
			map.put("STATUS", false);
			map.put("MESSAGE", "Error!");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	// Request User by Name
	@RequestMapping(value = "/get-user-by-name/{user_name}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getUserByName(@PathVariable String user_name) {
		Map<String, Object> map = new HashMap<String, Object>();
		User user = userService.getUserByName(user_name);
		try {
			if (user != null) {
				map.put("DATA", user);
				map.put("MESSAGE", "GET USER SUCCESSFULLY");
				map.put("STATUS", true);
			} else {
				map.put("MESSAGE", "GET USER FAIL!");
				map.put("STATE", true);
			}
		} catch (Exception e) {
			map.put("MESSAGE", "Error!");
			map.put("STATUS", false);
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	// ================= Login User By user_name and password
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> userLogin(@RequestBody User user) {
		Map<String, Object> map = new HashMap<String, Object>();
		Boolean userLogin = userService.userLogin(user);
		try {
			if (userLogin == true) {
				map.put("DATA", 1);
				map.put("MESSAGE", "LOGIN SUCCESSFULLY");
				map.put("STATUS", true);
			} else {
				map.put("MESSAGE", "LOGIN FAIL!");
				map.put("STATE", true);
			}
		} catch (Exception e) {
			map.put("MESSAGE", "Error!");
			map.put("STATUS", false);
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	// Request User by Email
	@RequestMapping(value = "/get-user-by-email/{email:.+}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getUserByEmail(@PathVariable String email) {
		Map<String, Object> map = new HashMap<String, Object>();
		User user = userService.getUserByEmail(email);
		try {
			if (user != null) {
				map.put("DATA", user);
				map.put("MESSAGE", "GET USER SUCCESSFULLY");
				map.put("STATUS", true);
			} else {
				map.put("MESSAGE", "GET USER FAIL!");
				map.put("STATE", true);
			}
		} catch (Exception e) {
			map.put("MESSAGE", "Error!");
			map.put("STATUS", false);
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	// Request User By Any Field
	@RequestMapping(value = "/get-user-by-any-field/{str_search:.+}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getUserByAnyField(@PathVariable String str_search) {
		Map<String, Object> map = new HashMap<String, Object>();
		ArrayList<User> user = userService.getUserByAnyField(str_search);
		try {
			if (user != null) {
				map.put("DATA", user);
				map.put("MESSAGE", "GET USER SUCCESSFULLY");
				map.put("STATUS", true);
			} else {
				map.put("MESSAGE", "GET USER FAIL!");
				map.put("STATE", true);
			}
		} catch (Exception e) {
			map.put("MESSAGE", "Error!");
			map.put("STATUS", false);
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	// @RequestMapping(value="/add-user", method= RequestMethod.POST)
	// public ResponseEntity<Map<String, Object>> addUser(@RequestBody
	// UserInputUpdate u){
	// Map<String, Object> map= new HashMap<String, Object>();
	// try {
	// if(userService.addUser(u)){
	// map.put("MESSAGE", "USER SUCCESSFULLY ADDED");
	// map.put("STATUS", true);
	// }else{
	// map.put("MESSAGE", "ERROR!");
	// map.put("STATUS", false);
	// }
	// } catch (Exception e) {
	// map.put("MESSAGE", "ERROR!");
	// map.put("STATUS", false);
	// e.printStackTrace();
	// }
	// return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	// }
	@RequestMapping(value = "/add-user", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> addUser(@RequestBody UserInputUpdate u) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (userService.addUser(u)) {
				map.put("MASSAGE", "USER HAS BEEN INSERTED!");
				map.put("CODE", "9999");
			} else {
				map.put("MASSAGE", "USER NOT FOUND!");
				map.put("CODE", "0000");
			}
		} catch (Exception e) {
			map.put("MASSAGE", "ERROR!");
			map.put("CODE", "1111");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@RequestMapping(value = "/update-user", method = RequestMethod.PUT)
	public ResponseEntity<Map<String, Object>> updateUser(@RequestBody User user) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (userService.updateUser(user)) {
				map.put("MESSAGE", "SUCCESSFULLY UPDATED!");
				map.put("STATUS", true);
			} else {
				map.put("MESSAGE", "UPDATE FAIL!!");
				map.put("STATUS", true);
			}
		} catch (Exception e) {
			map.put("MESSAGE", "ERROR!");
			map.put("STATUS", false);
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@RequestMapping(value = "/delete-user/{userId}", method = RequestMethod.DELETE)
	public ResponseEntity<Map<String, Object>> deleteUsers(@PathVariable int userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (userService.deleteUsers(userId)) {
				map.put("MESSAGE", "SUCCESSFULLY DELETED");
				map.put("STATUS", true);
			} else {
				map.put("MESSAGE", true);
				map.put("STATUS", true);
			}
		} catch (Exception e) {
			map.put("MESSAGE", "ERROR!");
			map.put("STATUS", false);
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	// Find user by verified code for confirming email in registration
	@RequestMapping(value = "/get-user-by-verified-code/{verified_code}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> findUserByVerifiedCode(@PathVariable String verified_code) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			User user = userService.findUserByVerifiedCode(verified_code);
			if (user != null) {
				map.put("DATA", user);
				map.put("STATUS", true);
				map.put("MESSAGE", "DATA FOUND!");
			} else {
				map.put("STATUS", true);
				map.put("MESSAGE", "DATA NOT FOUND!");
			}
		} catch (Exception e) {
			map.put("STATUS", false);
			map.put("MESSAGE", "Error!");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update-user-confirm-email/{verified_code}", method = RequestMethod.PUT)
	public ResponseEntity<Map<String, Object>> updateUserConfirmEmail(@PathVariable String verified_code) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (userService.updateUserConfirmEmail(verified_code)) {
				map.put("MESSAGE", "SUCCESSFULLY UPDATED!");
				map.put("STATUS", true);
			} else {
				map.put("MESSAGE", "UPDATE FAIL!!");
				map.put("STATUS", true);
			}
		} catch (Exception e) {
			map.put("MESSAGE", "ERROR!");
			map.put("STATUS", false);
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update-user-profile", method = RequestMethod.PUT)
	public ResponseEntity<Map<String, Object>> updateUserProfile(@RequestBody User user) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (userService.updateUserProfile(user)) {
				map.put("MESSAGE", "SUCCESSFULLY UPDATED!");
				map.put("STATUS", true);
			} else {
				map.put("MESSAGE", "UPDATE FAIL!!");
				map.put("STATUS", true);
			}
		} catch (Exception e) {
			map.put("MESSAGE", "ERROR!");
			map.put("STATUS", false);
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@RequestMapping(value = "/update-user-password", method = RequestMethod.PUT)
	public ResponseEntity<Map<String, Object>> updateUserPassword(@RequestBody User user) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (userService.updateUserPassword(user)) {
				map.put("MESSAGE", "SUCCESSFULLY UPDATED!");
				map.put("STATUS", true);
			} else {
				map.put("MESSAGE", "UPDATE FAIL!!");
				map.put("STATUS", true);
			}
		} catch (Exception e) {
			map.put("MESSAGE", "ERROR!");
			map.put("STATUS", false);
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	
	// Knongdai user integration. EAN SOKCHOMRERN. 07/10/2016
	@RequestMapping(value = "/find-user-by-user-hash/{user_hash}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> findUserByUserHash(@PathVariable String user_hash) {
		Map<String, Object> map = new HashMap<String, Object>();
		User user = userService.findUserByUserHash(user_hash);
		try {
			if (user != null) {
				map.put("DATA", user);
				map.put("MESSAGE", "GET USER SUCCESSFULLY");
				map.put("STATUS", true);
			} else {
				map.put("MESSAGE", "GET USER FAIL!");
				map.put("STATE", true);
			}
		} catch (Exception e) {
			map.put("MESSAGE", "Error!");
			map.put("STATUS", false);
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
}
