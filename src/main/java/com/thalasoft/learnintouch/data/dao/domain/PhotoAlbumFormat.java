package com.thalasoft.learnintouch.data.dao.domain;

public class PhotoAlbumFormat implements java.io.Serializable {

	private Long id;
	private int version;
	private int price;
	private PhotoFormat photoFormat;
	private PhotoAlbum photoAlbum;

	public PhotoAlbumFormat() {
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

	public PhotoFormat getPhotoFormat() {
		return this.photoFormat;
	}

	public void setPhotoFormat(PhotoFormat photoFormat) {
		this.photoFormat = photoFormat;
	}

	public PhotoAlbum getPhotoAlbum() {
		return this.photoAlbum;
	}

	public void setPhotoAlbum(PhotoAlbum photoAlbum) {
		this.photoAlbum = photoAlbum;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
