package org.khmeracademy.auction.services;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.khmeracademy.auction.entities.Product;
import org.khmeracademy.auction.entities.ProductInputUpdate;
import org.khmeracademy.auction.entities.ProductInputUpdate.ProductUpdate;
import org.khmeracademy.auction.utils.Pagination;


public interface ProductService {

	public ArrayList<Product> findAllProducts(Pagination pagination);

	public ArrayList<Product> findProductByName(String product_name);

	public ArrayList<Product> findProductBySupplier(String supplier_name);
 
	public ArrayList<Product> findProductByCategory(String category_name );
	
	public ArrayList<Product> findProductsHasSupplier(int supplier_id);

	public int addProduct(ProductInputUpdate p, HttpServletRequest request);

	public boolean updateProduct(ProductUpdate p, HttpServletRequest request);

	public boolean deleteProduct(int  product_id);
	
	
	public ArrayList<Product> findProductsHasSupplierForUpdate(int supplier_id);
}
