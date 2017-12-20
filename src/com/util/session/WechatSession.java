package com.util.session;

import java.io.Serializable;

/**
 * 微信Session，存储在redis中
 * @author gaoyf
 *
 */
public class WechatSession implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;//
	private int shop_id;
	private String cust_code;

	public int getShop_id() {
		return shop_id;
	}

	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "UserSession [id=" + id + ", shop_id=" + shop_id + ", cust_code=" + cust_code + "]";
	}

	public String getCust_code() {
		return cust_code;
	}

	public void setCust_code(String cust_code) {
		this.cust_code = cust_code;
	}

}
