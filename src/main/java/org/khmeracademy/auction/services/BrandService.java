package org.khmeracademy.auction.services;

import java.util.ArrayList;

import org.khmeracademy.auction.entities.Brand;
import org.khmeracademy.auction.filtering.BrandFilter;
import org.khmeracademy.auction.utils.Pagination;

public interface BrandService {

	public ArrayList<Brand> findAllBrands(BrandFilter filter, Pagination pagination);

	public ArrayList<Brand> findBrandByName(String brand_name);

	public ArrayList<Brand> findBrandByAnyField (String str_search);

	public boolean addBrand(Brand b);

	public boolean updateBrand(Brand b);
	
	public boolean deleteBrand(int brand_id);
	
	public Brand findBrandById(int brand_id);

}
