package me.chronosliu.ws.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * web服务接口请求工具类
 * 
 * @author ChronosLiu
 * @date 2017年12月19日 上午10:55:02
 */
public class WebServiceRequestUtil {
	
//	public static String Return_Code="returnCode";	//返回结果码  成功success,失败error
//	public static String Return_Msg="returnMsg";	//返回信息
//	public static String Return_Code_Success="success";	//成功
//	public static String Return_Code_Error="error";	//错误
	
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
		try {
	       System.out.println("请求地址："+restAddress+"请求参数："+requestParam);
	       
	       String reponseResult = sendHttpRequest(restAddress,requestMethod,
	    		   requestParam,requestType);

           return reponseResult;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return null;
	}
	/**
	 * 请求soap1.1协议的webservice
	 * 
	 * @Date 2018年1月5日 上午9:40:29
	 * @param rquestAddress    webservice发布地址
	 * @param requestMethod    请求的方法
	 * @param paramName       参数名称
	 * @param requestParam	    请求参数

	 * @return
	 */
	public static String requestSoap11Service(String rquestAddress,String requestMethod,
			String paramName,String requestParam){
		try{
			// soap协议请求体
			String soapContext = ""
					+ "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" "
					+ "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" "
					+ "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
					+ "xmlns:q0=\"http://soap.service.cxf.bdxc.com/\"> "	//webservice命名空间
					+ "<soapenv:Header/>  "
					+ "<soapenv:Body> "
					+ "<q0:"+ requestMethod+ ">" ;	  //方法名称
			
			if(paramName!=null){
				String urlParam = URLEncoder.encode(requestParam, "utf-8");
				soapContext=soapContext+"<"+ paramName+ ">"+ urlParam+ "</"+ paramName+ ">";
			}
			
			soapContext=soapContext	+"</q0:"+ requestMethod+ "></soapenv:Body> </soapenv:Envelope>";
//			String soapContext = "";
			
			System.out.println("soap请求内容："+soapContext);
			//发送http网络请求
			String reponseResult = sendHttpRequest(rquestAddress,"POST",
					soapContext,"text/xml;charset=UTF-8");
           
			return reponseResult;
            
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	/**
	 * 发送http网络请求
	 * 
	 * @Date 2018年1月5日 上午10:18:45
	 * @param rquestAddress  请求地址url
	 * @param requestMethod  请求方式 GET/POST
	 * @param requestParam   请求参数
	 * @param requestType    请求类型
	 * @return
	 */
	public static String sendHttpRequest(String rquestAddress,String requestMethod,
			String requestParam,String requestType){
		try{
			URL url = new URL(rquestAddress);
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
