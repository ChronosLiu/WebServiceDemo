package me.chronosliu.ws.rest_cxf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import me.chronosliu.ws.entity.Product;

public class ProductServiceImpl implements ProductService{

	 private static final List<Product> productList = new ArrayList<Product>();

	    static {
	        productList.add(new Product(1, "iphone 5s", 5000));
	        productList.add(new Product(2, "ipad mini", 2500));
	   }

	 
	@Override
	public List<Product> retrieveAllProducts() {
		 Collections.sort(productList, new Comparator<Product>() {
	            @Override
	            public int compare(Product product1, Product product2) {
	                return (product2.getId() > product1.getId()) ? 1 : -1;
	            }
	        });
	     return productList;
	}

	@Override
	public Product retrieveProductById(long id) {
		 Product targetProduct = null;
	        for (Product product : productList) {
	            if (product.getId() == id) {
	                targetProduct = product;
	                break;
	            }
	        }
	        return targetProduct;
	}

	@Override
	public Product createProduct(Product product) {
		// TODO Auto-generated method stub
		System.out.println("====111=="+product.getName());
		return product;
	}

}
