package me.chronosliu.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import me.chronosliu.ws.entity.Product;
import me.chronosliu.ws.service.DemoRestService;

public class DemoRestServiceImpl implements DemoRestService {
	
	@Override
	public String testGet() {
		// TODO Auto-generated method stub
		System.out.println("get");
		return "";
	}
	@Override
	public String retrieveAllProducts(String paramJson) {
		// TODO Auto-generated method stub
		System.out.println(paramJson);
		List<Product> a = new ArrayList<Product>();
		return "{}";
	}

	@Override
	public Product createProduct(Product product) {
		// TODO Auto-generated method stub
		Product p = new Product();
		return p;
	}



}
