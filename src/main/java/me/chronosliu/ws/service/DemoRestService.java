package me.chronosliu.ws.service;

import java.util.List;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import me.chronosliu.ws.entity.Product;

@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})   //请求支持的  MIME 类型
@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})	//响应支持的  MIME 类型
public interface DemoRestService {
	@GET
	@Path("/testGet")
	String testGet();
	
	@POST
	@Path("/products")
	String  retrieveAllProducts(String paramJson);
	
	@POST
	@Path("/createProduct")
	Product createProduct(Product product);
	
	
}
