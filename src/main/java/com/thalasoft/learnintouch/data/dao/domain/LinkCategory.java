package com.thalasoft.learnintouch.data.dao.domain;

import java.util.HashSet;
import java.util.Set;

public class LinkCategory implements java.io.Serializable {

	private Long id;
	private int version;
	private String name;
	private String description;
	private int listOrder;
	private Set<Link> links = new HashSet<Link>();

	public LinkCategory() {
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
    public int getListOrder() {
        return listOrder;
    }
    
    public void setListOrder(int listOrder) {
        this.listOrder = listOrder;
    }

    public Set<Link> getLinks() {
		return this.links;
	}

	@SuppressWarnings("unused")
	private void setLinks(Set<Link> links) {
		this.links = links;
	}

	public void addLink(Link link) {
		if (link.getLinkCategory() != this) {
			if (link.getLinkCategory() != null) {
				link.getLinkCategory().links.remove(link);
			}
			link.setLinkCategory(this);
			this.links.add(link);
		}
	}

	public void removeLink(Link link) {
		if (link.getLinkCategory().getId() == this.getId() && this.getId() != null) {
			link.setLinkCategory(null);
			this.links.remove(link);
		}
	}

}
