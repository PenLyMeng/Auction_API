package org.khmeracademy.auction.services.impl;

import java.sql.Date;
import java.util.ArrayList;

import org.khmeracademy.auction.entities.TopUp;
import org.khmeracademy.auction.entities.TopUpInputUpdate;
import org.khmeracademy.auction.entities.UserCreditHistory;
import org.khmeracademy.auction.repositories.TopUpRepository;
import org.khmeracademy.auction.repositories.UserCreditHistoryRepository;
import org.khmeracademy.auction.services.TopUpService;
import org.khmeracademy.auction.services.UserCreditHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopUpServiceImpl implements TopUpService {
	@Autowired
	private TopUpRepository topUpRepository;
	
	@Autowired 
	private UserCreditHistoryRepository userCreditHistoryRepository;

	@Override
	public ArrayList<TopUp> findAllTopUp() {
		return topUpRepository.findAllTopUp();
	}

	@Override
	public ArrayList<TopUp> findTopUpByCurrency(String currency) {
		return topUpRepository.findTopUpByCurrency(currency);
	}

	@Override
	public ArrayList<TopUp> findTopUpByUserName(String user_name) {
		return topUpRepository.findTopUpByUserName(user_name);
	}

	@Override
	public ArrayList<TopUp> findTopUpByDate(Date top_up_date) {
		return topUpRepository.findTopUpByDate(top_up_date);
	}

	@Override
	public boolean addTopUp(TopUpInputUpdate t) {
		return topUpRepository.addTopUp(t);
		//return userCreditHistoryRepository.findUserCreditHistoryByUserId(t.getUser_id());
	}

	@Override
	public boolean updateTopUp(TopUpInputUpdate t) {
		return topUpRepository.updateTopUp(t);
	}

	@Override
	public boolean deleteTopUp(int top_up_id) {
		return topUpRepository.deleteTopUp(top_up_id);
	}
	
	
}
