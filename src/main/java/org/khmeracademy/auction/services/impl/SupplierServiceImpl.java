package org.khmeracademy.auction.services.impl;

import java.util.ArrayList;

import org.khmeracademy.auction.entities.Supplier;
import org.khmeracademy.auction.filtering.SupplierFilter;
import org.khmeracademy.auction.repositories.SupplierRepository;
import org.khmeracademy.auction.services.SupplierService;
import org.khmeracademy.auction.utils.Pagination;
//import org.khmeracademy.auction.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class SupplierServiceImpl implements SupplierService {

	@Autowired SupplierRepository suplrepo;
	
	@Override
	public ArrayList<Supplier> findAllSuppliers(SupplierFilter filter,Pagination pagination) {
		System.out.println(pagination);
		pagination.setTotalCount(suplrepo.count(filter));
		return suplrepo.findAllSuppliers(filter, pagination);
	}

	@Override
	public ArrayList<Supplier> findSupplierByName(String contact_name) {
		// TODO Auto-generated method stub
		return suplrepo.findSupplierByName(contact_name);
	}

	@Override
	public ArrayList<Supplier> findSupplierByEmail(String email) {
		// TODO Auto-generated method stub
		return suplrepo.findSupplierByEmail(email);
	}

	@Override
	public ArrayList<Supplier> findSupplierByAnyField(String str_search) {
		// TODO Auto-generated method stub
		return suplrepo.findSupplierByAnyField(str_search);
	}

	@Override
	public boolean addSupplier(Supplier s) {
		// TODO Auto-generated method stub
		return suplrepo.addSupplier(s);
	}

	@Override
	public boolean updateSupplier(Supplier s) {
		// TODO Auto-generated method stub
		return suplrepo.updateSupplier(s);
	}

	@Override
	public boolean deleteSupplier(int supplier_id) {
		// TODO Auto-generated method stub
		return suplrepo.deleteSupplier(supplier_id);
	}
	
	@Override
	public Supplier findSupplierById(int supplier_id){
		return suplrepo.findSupplierById(supplier_id);
	}

	@Override
	public ArrayList<Supplier> findSupplierInProducts() {
		// TODO Auto-generated method stub
		return suplrepo.findSupplierInProducts();
	}
}
