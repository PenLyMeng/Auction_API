package org.khmeracademy.auction.services.impl;

import java.util.ArrayList;

import org.khmeracademy.auction.entities.User;
import org.khmeracademy.auction.entities.UserInputUpdate;
import org.khmeracademy.auction.filtering.UserFilter;
import org.khmeracademy.auction.repositories.UserRepository;
import org.khmeracademy.auction.services.UserService;
import org.khmeracademy.auction.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired 
	private UserRepository userRepository;
	
	@Override
	public ArrayList<User> getAllUsers(UserFilter filter, Pagination pagination) {
		
		pagination.setTotalCount(userRepository.count(filter));
		return userRepository.getAllUsers(filter, pagination);
	}
	
	@Override
	public User findUserById(int user_id) {
		// TODO Auto-generated method stub
		return userRepository.findUserById(user_id);
	}

	@Override

	public User getUserByName( String user_name) {
		// TODO Auto-generated method stub
		return userRepository.getUserByName(user_name);
	}
	@Override
	public User getUserByEmail( String email) {
		// TODO Auto-generated method stub
		return userRepository.getUserByEmail(email);
	}

	@Override
	public ArrayList<User> getUserByAnyField( String user_name) {
		// TODO Auto-generated method stub
		return userRepository.getUserByAnyField(user_name);
	} 
	

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return userRepository.updateUser(user);
	}

	@Override
	public boolean deleteUsers(int userId) {
		// TODO Auto-generated method stub
		return userRepository.deleteUsers(userId);
	}

	@Override
	public boolean userLogin(User user) {
		
		if( userRepository.userLogin(user) > 0){
			return true;
		}
		return false;
	}

	@Override
	public boolean addUser(UserInputUpdate u) {
		// TODO Auto-generated method stub
		return userRepository.addUser(u);
	}

	@Override
	public User findUserByVerifiedCode(String verified_code) {
		// TODO Auto-generated method stub
		return userRepository.findUserByVerifiedCode(verified_code);
	}

	@Override
	public boolean updateUserConfirmEmail(String verified_code) {
		// TODO Auto-generated method stub
		return userRepository.updateUserConfirmEmail(verified_code);
	}

	@Override
	public boolean updateUserProfile(User user) {
		// TODO Auto-generated method stub
		return userRepository.updateUserProfile(user);
	}

	@Override
	public boolean updateUserPassword(User user) {
		// TODO Auto-generated method stub
		return userRepository.updateUserPassword(user);
	}

	// Knongdai user integration. EAN SOKCHOMRERN. 07/10/2016
	@Override
	public User findUserByUserHash(String user_hash) {
		// TODO Auto-generated method stub
		return userRepository.findUserByUserHash(user_hash);
	}
	
	
	
	

}
