package org.khmeracademy.auction.services;

import java.util.ArrayList;

import org.khmeracademy.auction.entities.InvoiceDetail;
import org.khmeracademy.auction.entities.InvoiceDetailInputUpdate;
import org.khmeracademy.auction.filtering.InvoiceFilter;
import org.khmeracademy.auction.utils.Pagination;

public interface InvoiceDetailService {
	
	public ArrayList<InvoiceDetail> findAllInvoiceDetails(InvoiceFilter filter, Pagination pagination);	
	public InvoiceDetail findInvoiceDetailByInvoiceId(int invoice_id);	
	public ArrayList<InvoiceDetail> findInvoiceDetailByAuctionId(int auction_id);	
	public boolean addInvoiceDetail(InvoiceDetailInputUpdate invd);	
	public boolean updateInvoiceDetail(InvoiceDetailInputUpdate invd);	
//	public boolean deleteInvoiceDetail(int invoice_id, int auction_id);
}
