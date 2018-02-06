package me.chronosliu.ws.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.ws.rs.POST;
import javax.xml.ws.BindingType;

import me.chronosliu.ws.entity.Product;
/**
 * 支持Soap协议的WebService接口
 * @author ly
 * @date 2018年2月6日下午1:18:20
 */
@WebService
//@SOAPBinding(style=SOAPBinding.Style.RPC)
public interface DemoSoapService {
	@POST
	@WebMethod(operationName="retrieveAllProducts")
	public List<Product>  retrieveAllProducts();

	
}
