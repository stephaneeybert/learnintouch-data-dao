package com.thalasoft.learnintouch.data.dao.domain;

import org.joda.time.LocalDateTime;

public class ShopItem implements java.io.Serializable {

	private Long id;
	private int version;
	private String name;
	private String shortDescription;
	private String longDescription;
	private String reference;
	private String weight;
	private String price;
	private String vatRate;
	private String shippingFee;
	private String url;
	private int listOrder;
	private boolean hide;
	private LocalDateTime added;
	private LocalDateTime lastModified;
	private LocalDateTime available;
	private ShopCategory shopCategory;

	public ShopItem() {
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

	public ShopCategory getShopCategory() {
		return this.shopCategory;
	}

	public void setShopCategory(ShopCategory shopCategory) {
		this.shopCategory = shopCategory;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortDescription() {
		return this.shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getLongDescription() {
		return this.longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public String getReference() {
		return this.reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getWeight() {
		return this.weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getVatRate() {
		return vatRate;
	}

	public void setVatRate(String vatRate) {
		this.vatRate = vatRate;
	}

	public String getShippingFee() {
		return this.shippingFee;
	}

	public void setShippingFee(String shippingFee) {
		this.shippingFee = shippingFee;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getListOrder() {
		return this.listOrder;
	}

	public void setListOrder(int listOrder) {
		this.listOrder = listOrder;
	}

	public boolean getHide() {
		return this.hide;
	}

	public void setHide(boolean hide) {
		this.hide = hide;
	}

	public LocalDateTime getAdded() {
		return this.added;
	}

	public void setAdded(LocalDateTime added) {
		this.added = added;
	}

	public LocalDateTime getLastModified() {
		return this.lastModified;
	}

	public void setLastModified(LocalDateTime lastModified) {
		this.lastModified = lastModified;
	}

	public LocalDateTime getAvailable() {
		return this.available;
	}

	public void setAvailable(LocalDateTime available) {
		this.available = available;
	}

}
