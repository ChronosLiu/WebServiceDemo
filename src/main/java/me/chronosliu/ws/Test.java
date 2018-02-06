package me.chronosliu.ws;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
