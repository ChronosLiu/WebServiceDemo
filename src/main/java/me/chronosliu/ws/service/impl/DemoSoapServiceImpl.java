package me.chronosliu.ws.service.impl;

import java.util.List;

import javax.jws.WebService;
import javax.xml.ws.BindingType;


import javax.xml.ws.soap.SOAPBinding;

import me.chronosliu.ws.entity.Product;
import me.chronosliu.ws.service.DemoSoapService;
@WebService
@BindingType(value=SOAPBinding.SOAP12HTTP_MTOM_BINDING)
public class DemoSoapServiceImpl implements DemoSoapService {

	@Override
	public List<Product> retrieveAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}

}
