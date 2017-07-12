package org.khmeracademy.auction.services.impl;

import java.util.ArrayList;

import org.khmeracademy.auction.entities.InvoiceDetail;
import org.khmeracademy.auction.entities.InvoiceDetailInputUpdate;
import org.khmeracademy.auction.filtering.InvoiceFilter;
import org.khmeracademy.auction.repositories.InvoiceDetailRepository;
import org.khmeracademy.auction.services.InvoiceDetailService;
import org.khmeracademy.auction.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceDetailServiceImpl implements InvoiceDetailService {
	@Autowired
	private InvoiceDetailRepository invoiceDetailRepository;

	@Override
	public ArrayList<InvoiceDetail> findAllInvoiceDetails(InvoiceFilter filter, Pagination pagination) {
		pagination.setTotalCount(invoiceDetailRepository.count(filter));
		return invoiceDetailRepository.findAllInvoiceDetails(filter, pagination);
	}

	@Override
	public InvoiceDetail findInvoiceDetailByInvoiceId(int invoice_id) {
		return invoiceDetailRepository.findInvoiceDetailByInvoiceId(invoice_id);
	}

	@Override
	public ArrayList<InvoiceDetail> findInvoiceDetailByAuctionId(int auction_id) {
		return invoiceDetailRepository.findInvoiceDetailByAuctionId(auction_id);
	}

	@Override
	public boolean addInvoiceDetail(InvoiceDetailInputUpdate invd) {
		return invoiceDetailRepository.addInvoiceDetail(invd);
	}

	@Override
	public boolean updateInvoiceDetail(InvoiceDetailInputUpdate invd) {
		return invoiceDetailRepository.updateInvoiceDetail(invd);
	}

//	@Override
//	public boolean deleteInvoiceDetail(int invoice_id, int auction_id) {
//		return invoiceDetailRepository.deleteInvoiceDetail(invoice_id, auction_id);
//	}	
	
}
