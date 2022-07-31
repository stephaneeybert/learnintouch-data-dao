package com.thalasoft.learnintouch.data.dao.domain;

import org.joda.time.LocalDateTime;

public class PhotoAlbum implements java.io.Serializable {

	private Long id;
	private int version;
	private String name;
	private String folderName;
	private String event;
	private String location;
	private int price;
	private int listOrder;
	private LocalDateTime publicationDate;
	private boolean hide;
	private boolean no_slide_show;
	private boolean no_zoom;

	public PhotoAlbum() {
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public String getEvent() {
		return this.event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public LocalDateTime getPublicationDate() {
		return this.publicationDate;
	}

	public void setPublicationDate(LocalDateTime publicationDate) {
		this.publicationDate = publicationDate;
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

	public boolean getHide() {
		return hide;
	}

	public void setHide(boolean hide) {
		this.hide = hide;
	}

    public boolean getNoSlideShow() {
        return no_slide_show;
    }

    public void setNoSlideShow(boolean no_slide_show) {
        this.no_slide_show = no_slide_show;
    }

    public boolean getNoZoom() {
        return no_zoom;
    }

    public void setNoZoom(boolean no_zoom) {
        this.no_zoom = no_zoom;
    }

}
