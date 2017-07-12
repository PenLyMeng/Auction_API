package org.khmeracademy.auction.services.impl;

import java.sql.Date;
import java.util.ArrayList;

import org.khmeracademy.auction.entities.InvoiceInputUpdate;
import org.khmeracademy.auction.filtering.InvoiceFilter;
import org.khmeracademy.auction.repositories.InvoiceRepository;
import org.khmeracademy.auction.services.InvoiceService;
import org.khmeracademy.auction.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImpl implements InvoiceService{
	@Autowired
	private InvoiceRepository invoiceRepository;

	@Override
	public ArrayList<InvoiceInputUpdate> findAllInvoices() {
		return invoiceRepository.findAllInvoices();
	}

	@Override
	public ArrayList<InvoiceInputUpdate> findInvoiceByUserName(String user_name) {
		return invoiceRepository.findInvoiceByUserName(user_name);
	}

	@Override
	public ArrayList<InvoiceInputUpdate> findInvoiceByDate(Date invoice_date) {
		return invoiceRepository.findInvoiceByDate(invoice_date);
	}

	@Override
	public boolean addInvoice(InvoiceInputUpdate inv) {
		return invoiceRepository.addInvoice(inv);
	}

	@Override
	public boolean updateInvoice(InvoiceInputUpdate inv) {
		return invoiceRepository.updateInvoice(inv);
	}

	@Override
	public boolean deleteInvoice(int invoice_id) {
		return invoiceRepository.deleteInvoice(invoice_id);
	}
	
	
}
