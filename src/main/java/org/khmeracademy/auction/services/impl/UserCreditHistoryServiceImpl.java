package org.khmeracademy.auction.services.impl;

import java.util.ArrayList;

import org.khmeracademy.auction.entities.UserCreditHistory;
import org.khmeracademy.auction.entities.UserCreditHistoryInputUpdate;
import org.khmeracademy.auction.filtering.TopupFilter;
import org.khmeracademy.auction.repositories.UserCreditHistoryRepository;
import org.khmeracademy.auction.services.UserCreditHistoryService;
import org.khmeracademy.auction.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCreditHistoryServiceImpl implements UserCreditHistoryService {
	@Autowired
	private UserCreditHistoryRepository userCreditHistoryRepository;

	@Override
	public ArrayList<UserCreditHistory> findAllUserCreditHistory() {
		return userCreditHistoryRepository.findAllUserCreditHistory();
	}

	@Override
	public ArrayList<UserCreditHistory> findUserCreditHistoryByUserName(String user_name) {
		return userCreditHistoryRepository.findUserCreditHistoryByUserName(user_name);
	}

	@Override
	public boolean addUserCreditHistory(UserCreditHistoryInputUpdate uch) {
		return userCreditHistoryRepository.addUserCreditHistory(uch);
	}

	@Override
	public boolean updateUserCreditHistory(UserCreditHistoryInputUpdate uch) {
		return userCreditHistoryRepository.updateUserCreditHistory(uch);
	}

	@Override
	public boolean deleteUserCreditHistory(int credit_id) {
		return userCreditHistoryRepository.deleteUserCreditHistory(credit_id);
	}
	
	@Override
	public UserCreditHistory checkEndingAmound(int userId) {
		return userCreditHistoryRepository.checkingEndingAmount(userId);
	}
	
	@Override
	public UserCreditHistory findByUserId(int userId) {
		return userCreditHistoryRepository.findUserCreditHistoryByUserId(userId);
	}

	@Override
	public ArrayList<UserCreditHistory> findAllActiveUserCreditHistoryWithEndingAmount(TopupFilter filter, Pagination pagination) {
		// 23-08-2016
		pagination.setTotalCount(userCreditHistoryRepository.count(filter));
		return userCreditHistoryRepository.findAllActiveUserCreditHistoryWithEndingAmount(filter, pagination);
	}
	
	
	
	
}
