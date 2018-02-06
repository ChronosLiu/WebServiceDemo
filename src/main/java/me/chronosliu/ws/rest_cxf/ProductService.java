package me.chronosliu.ws.rest_cxf;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import me.chronosliu.ws.entity.Product;

public interface ProductService {
	
	@GET
	@Path("/products")
	@Produces(MediaType.APPLICATION_JSON)
	List<Product>  retrieveAllProducts();
	
	@GET
    @Path("/product/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Product retrieveProductById(@PathParam("id") long id);
	
	@POST
	@Path("/product")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Product createProduct(Product product);
}
