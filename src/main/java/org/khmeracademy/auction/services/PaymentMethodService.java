package org.khmeracademy.auction.services;

import java.util.ArrayList;
import org.khmeracademy.auction.entities.PaymentMethod;

public interface PaymentMethodService {
	
	public ArrayList<PaymentMethod> findAllPaymentMethods();
	public ArrayList<PaymentMethod> findPaymentMethodByName(String payment_method_name);
	public boolean addPaymentMethod(PaymentMethod p);
	public boolean updatePaymentMethod(PaymentMethod p);
	public boolean deletePaymentMethod(int payment_method_id);
}
