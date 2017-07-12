package org.khmeracademy.auction.services.impl;

import java.util.ArrayList;

import org.khmeracademy.auction.entities.PaymentMethod;
import org.khmeracademy.auction.repositories.PaymentMethodRepository;
import org.khmeracademy.auction.services.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentMethodServiceImpl implements PaymentMethodService{
	@Autowired
	private PaymentMethodRepository paymentMethodRepository; 

	@Override
	public ArrayList<PaymentMethod> findAllPaymentMethods() {
		return paymentMethodRepository.findAllPaymentMethods();
	}

	@Override
	public ArrayList<PaymentMethod> findPaymentMethodByName(String payment_method_name) {
		return paymentMethodRepository.findPaymentMethodByName(payment_method_name);
	}

	@Override
	public boolean addPaymentMethod(PaymentMethod p) {
		return paymentMethodRepository.addPaymentMethod(p);
	}

	@Override
	public boolean updatePaymentMethod(PaymentMethod p) {
		return paymentMethodRepository.updatePaymentMethod(p);
	}

	@Override
	public boolean deletePaymentMethod(int payment_method_id) {
		return paymentMethodRepository.deletePaymentMethod(payment_method_id);
	}
	
	
}
