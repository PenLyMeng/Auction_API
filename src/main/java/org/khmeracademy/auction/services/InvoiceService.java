package org.khmeracademy.auction.services;

import java.sql.Date;
import java.util.ArrayList;

import org.khmeracademy.auction.entities.InvoiceInputUpdate;
import org.khmeracademy.auction.filtering.InvoiceFilter;
import org.khmeracademy.auction.utils.Pagination;

public interface InvoiceService {
	
	public ArrayList<InvoiceInputUpdate> findAllInvoices();
	public ArrayList<InvoiceInputUpdate> findInvoiceByUserName(String user_name);
	public ArrayList<InvoiceInputUpdate> findInvoiceByDate(Date invoice_date);
	public boolean addInvoice(InvoiceInputUpdate inv);
	public boolean updateInvoice(InvoiceInputUpdate inv);
	public boolean deleteInvoice(int invoice_id);
	
}
