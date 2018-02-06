package me.chronosliu.ws.rest_cxf;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import me.chronosliu.ws.entity.Product;
import me.chronosliu.ws.entity.WlwbCbEntity;
import me.chronosliu.ws.entity.WlwbCbList;
import me.chronosliu.ws.entity.WlwbCbListEntity;
import me.chronosliu.ws.util.JsonUtil;
import me.chronosliu.ws.util.WebServiceRequestUtil;





import org.apache.cxf.jaxrs.client.WebClient;
//import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

public class WebClientTest {
	private static final String baseAddress = "http://localhost:8080/tonghui_third_pay/service";
//	private static final String baseAddress = "http://localhost:8080/charge_tonghui/rs";
	
//    @Test
//    public void helloServiceSayTest() {
//        String result = WebClient.create(baseAddress)
//            .path("/hello/say")
//            .query("name", "world")
//            .get(String.class);
//        System.out.println(result);
//    }

    /**
     * cxf调用rest服务接口
     * 
     * @Date 2017年9月19日 上午10:12:52
     */
  /*  @Test
    public void productServiceRetrieveAllProductsTest() {
        List productList = WebClient.create(baseAddress, "")
            .path("/products")
            .accept(MediaType.APPLICATION_JSON)
            .get(List.class);
        for (Object product : productList) {
            System.out.println(JsonUtil.toJSON(product));
        }
    }*/

    /**
     * cxf调用rest服务接口2
     * 
     * @Date 2017年9月19日 上午10:13:07
     */
//    @Test
//    public void productServiceRetrieveAllProductsTest2() {
//        List<Product> productList = WebClient.create(baseAddress, providerList)
//            .path("/products")
//            .accept(MediaType.APPLICATION_JSON)
//            .get(new GenericType<List<Product>>() {});
//        for (Product product : productList) {
//            System.out.println(JsonUtil.toJSON(product));
//        }
//    }
//    @Test
//    public void productServiceRetrieveAllProductsTest3() {
//        List<Product> productList = WebClient.create(baseAddress, providerList)
//            .path("/product")
//            .accept(MediaType.APPLICATION_JSON)
//            .post(body);
//        for (Product product : productList) {
//            System.out.println(JsonUtil.toJSON(product));
//        }
//    }
	/**
	 * 请求soap协议的webservice测试
	 * 
	 * @Date 2018年1月5日 下午1:56:26
	 *//*
	@Test
	public void rqeuestSoapTest(){
		String url = "http://localhost:8080/tonghui_third_pay/service/ws/";
		String param="<ACCOUNTQUERY><CODE>01</CODE></ACCOUNTQUERY>";
		String paramName="arg0";
		String method="testSoap";
		String result = WebServiceRequestUtil.requestSoap11Service(url,
				method, paramName, param);
	
	}
	*/
    /**
     * httpUrlConnection调用rest服务接口 
     * 
     * @Date 2017年9月19日 上午10:09:44
     */
    @Test
    public void requestRestService(){
  
    	try {
    		//查询账单queryQfzdByxml
//    		String url = "http://58.18.36.34:5121/tonghui_third_pay/service/rs/queryQfzdByXml";
//    		String param = "<ACCOUNTQUERY><CODE>01</CODE><BANKCODE>GD</BANKCODE><YYQM></YYQM><GYH></GYH><YHKH></YHKH><YHBM>000456033020302</YHBM></ACCOUNTQUERY>";
    		//查询地址xml
//    		String url = "http://58.18.36.34:5121/tonghui_third_pay/service/rs/queryAddressByXml";
//    		String param = "<ADDRESSREQUEST><CODE>05</CODE><COMPANY>03551</COMPANY><ADDRESSTYPE>0</ADDRESSTYPE><NUMBER>1</NUMBER><QUERYTYPE>FGS</QUERYTYPE><NAME>北区运行部</NAME><GETVALUE>RLZ</GETVALUE></ADDRESSREQUEST>";
    		//查询用户xml
    		String url = "http://58.18.36.34:5121/tonghui_third_pay/service/rs/queryYhxxByXml";
    		String param = "<QUERYUSER><CODE>06</CODE><BANKCODE>GD</BANKCODE><YYQM></YYQM><GYH></GYH><USERNAME>丁文明(有车库）</USERNAME><QUERYTYPE></QUERYTYPE><QUERYVALUE></QUERYVALUE></QUERYUSER>";
    		
    		String result  = requestRestService(url, 
    				param,"POST","application/xml");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
	 * 请求rest服务接口 
	 * @param restAddress   rest接口地址
	 * @param requestParam	rest请求参数
	 * @param requestMethod rest请求方式   POST或Get
	 * @param requestType   rest请求类型   application/json  或application/xml
	 * @return
	 */
	public static String requestRestService(String restAddress,String requestParam,
			String requestMethod,String requestType){
	
	       System.out.println("请求地址："+restAddress+"请求参数："+requestParam);
	       
	       try{
				URL url = new URL(restAddress);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				//application/x-javascript text/xml->xml数据 
				//application/x-javascript->json对象
				//application/x-www-form-urlencoded->表单数据
				connection.setRequestProperty("Content-Type", requestType);//设置请求参数
				connection.setRequestProperty("Accept-Charset", "utf-8");//设置utf-8，解决中文乱码
				connection.setRequestProperty("contentType", "utf-8");  
				connection.setRequestMethod(requestMethod);//设置请求方式
				connection.setDoOutput(true);
				connection.setDoInput(true);
				connection.connect();//连接
				//post请求方式
				if ("POST".equals(requestMethod)){
					
					PrintWriter pw = new PrintWriter(new OutputStreamWriter(connection.getOutputStream(),"utf-8"));
					pw.print(requestParam);
					pw.flush();//关闭输出流
				}
				//请求失败,返回200请求成功
				if (connection.getResponseCode() != connection.HTTP_OK) {
					System.out.println("请求失败，错误码"+connection.getResponseCode());
			     
				}
	            BufferedReader responseBuffer = new BufferedReader(
	                    new InputStreamReader((connection.getInputStream()),"utf-8"));
	            String output;
	            StringBuffer sbf = new StringBuffer();
	            while ((output = responseBuffer.readLine()) != null) {
	            	sbf.append(output);      
	            }
	            responseBuffer.close();	//关闭
	            connection.disconnect();//关闭连接
	            
	            System.out.println("返回结果："+sbf.toString());
	            
	            return sbf.toString();
	            
			}catch(Exception e){
				e.printStackTrace();
			}
           return null;
			
	}
    
}
