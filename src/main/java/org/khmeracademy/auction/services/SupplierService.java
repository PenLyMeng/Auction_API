package org.khmeracademy.auction.services;

import java.util.ArrayList;

import org.khmeracademy.auction.entities.Supplier;
import org.khmeracademy.auction.filtering.SupplierFilter;
import org.khmeracademy.auction.utils.Pagination;

public interface SupplierService {

	public ArrayList<Supplier> findAllSuppliers(SupplierFilter filter, Pagination pagination);
	
	public ArrayList<Supplier> findSupplierByName(String contact_name);
	
	public ArrayList<Supplier> findSupplierByEmail(String email);
	
	public ArrayList<Supplier> findSupplierByAnyField(String str_search);
	
	public boolean addSupplier(Supplier s);
	
	public boolean updateSupplier(Supplier s); 
	
	public boolean deleteSupplier(int supplier_id);
	
	public Supplier findSupplierById(int supplier_id);
	
	public ArrayList<Supplier> findSupplierInProducts();
}
