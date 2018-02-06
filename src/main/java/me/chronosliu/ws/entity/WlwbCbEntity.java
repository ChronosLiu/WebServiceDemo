package me.chronosliu.ws.entity;

public class WlwbCbEntity {

	private String customer_code;	//用户编号,仪表编号，唯一标识
	private String read_date;	//抄表日期（yyyy-MM-ddHH:mm:ss）
	private String read_val;		//抄表读数
	private String read_jval;	//表计剩余余额（获取量测数据表中金额量测，抄表日期同累计量量测）
	private String sign;	//
	public String getCustomer_code() {
		return customer_code;
	}
	public void setCustomer_code(String customer_code) {
		this.customer_code = customer_code;
	}
	public String getRead_date() {
		return read_date;
	}
	public void setRead_date(String read_date) {
		this.read_date = read_date;
	}
	public String getRead_val() {
		return read_val;
	}
	public void setRead_val(String read_val) {
		this.read_val = read_val;
	}
	public String getRead_jval() {
		return read_jval;
	}
	public void setRead_jval(String read_jval) {
		this.read_jval = read_jval;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
}
