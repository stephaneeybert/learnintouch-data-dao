package com.thalasoft.learnintouch.data.dao.domain;

public class Photo implements java.io.Serializable {

	private Long id;
	private int version;
	private String name;
	private String description;
    private String tags;
	private String textComment;
	private String image;
	private String reference;
	private String url;
	private int price;
	private int listOrder;
	private PhotoFormat photoFormat;
	private PhotoAlbum photoAlbum;

	public Photo() {
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

	public String getReference() {
		return this.reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
    public String getTags() {
        return tags;
    }
    
    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTextComment() {
		return this.textComment;
	}

	public void setTextComment(String textComment) {
		this.textComment = textComment;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getListOrder() {
		return this.listOrder;
	}

	public void setListOrder(int listOrder) {
		this.listOrder = listOrder;
	}

}
