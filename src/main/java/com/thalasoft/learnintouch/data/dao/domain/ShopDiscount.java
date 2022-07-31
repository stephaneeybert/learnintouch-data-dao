package com.thalasoft.learnintouch.data.dao.domain;

public class ShopDiscount implements java.io.Serializable {

	private Long id;
	private int version;
	private String discountCode;
	private String discountRate;
	private ShopAffiliate shopAffiliate;

	public ShopDiscount() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getDiscountCode() {
		return discountCode;
	}

	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}

	public String getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(String discountRate) {
		this.discountRate = discountRate;
	}

	public ShopAffiliate getShopAffiliate() {
		return shopAffiliate;
	}

	public void setShopAffiliate(ShopAffiliate shopAffiliate) {
		this.shopAffiliate = shopAffiliate;
	}

}
