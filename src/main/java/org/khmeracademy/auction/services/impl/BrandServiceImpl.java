package org.khmeracademy.auction.services.impl;

import java.util.ArrayList;

import org.khmeracademy.auction.entities.Brand;
import org.khmeracademy.auction.filtering.BrandFilter;
import org.khmeracademy.auction.repositories.BrandRepository;
import org.khmeracademy.auction.services.BrandService;
import org.khmeracademy.auction.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	BrandRepository br;
	
	@Override
	public ArrayList<Brand> findAllBrands(BrandFilter filter, Pagination pagination) {
		pagination.setTotalCount(br.count(filter));
		return br.findAllBrands(filter, pagination);
	}

	@Override
	public ArrayList<Brand> findBrandByName(String brand_name) {
		// TODO Auto-generated method stub
		return br.findBrandByName(brand_name);
	}

	@Override
	public ArrayList<Brand> findBrandByAnyField(String str_search) {
		// TODO Auto-generated method stub
		return br.findBrandByAnyField(str_search);
	}

	@Override
	public boolean addBrand(Brand b) {
		// TODO Auto-generated method stub
		return br.addBrand(b);
	}

	@Override
	public boolean updateBrand(Brand b) {
		// TODO Auto-generated method stub
		return br.updateBrand(b);
	}

	@Override
	public boolean deleteBrand(int brand_id) {
		// TODO Auto-generated method stub
		return br.deleteBrand(brand_id);
	}
	
	public Brand findBrandById(int brand_id){
		return br.findBrandById(brand_id);
	}

}
