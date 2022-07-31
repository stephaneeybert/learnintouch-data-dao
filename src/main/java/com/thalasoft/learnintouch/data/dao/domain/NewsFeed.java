package com.thalasoft.learnintouch.data.dao.domain;

public class NewsFeed implements java.io.Serializable {

	private Long id;
	private int version;
	private NewsPaper newsPaper;
	private String image;
	private int maxDisplayNumber;
	private String imageAlign;
	private int imageWidth;
	private boolean withExcerpt;
	private boolean withImage;
	private boolean searchOptions;
	private boolean searchCalendar;
	private String searchTitle;
	private boolean searchDisplayAsPage;
	
	public NewsFeed() {
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

	public NewsPaper getNewsPaper() {
		return this.newsPaper;
	}

	public void setNewsPaper(NewsPaper newsPaper) {
		this.newsPaper = newsPaper;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getMaxDisplayNumber() {
		return this.maxDisplayNumber;
	}

	public void setMaxDisplayNumber(int maxDisplayNumber) {
		this.maxDisplayNumber = maxDisplayNumber;
	}

	public String getImageAlign() {
		return imageAlign;
	}

	public void setImageAlign(String imageAlign) {
		this.imageAlign = imageAlign;
	}

	public int getImageWidth() {
		return imageWidth;
	}

	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}

	public boolean isWithExcerpt() {
		return withExcerpt;
	}

	public void setWithExcerpt(boolean withExcerpt) {
		this.withExcerpt = withExcerpt;
	}

	protected boolean isWithImage() {
		return withImage;
	}

	protected void setWithImage(boolean withImage) {
		this.withImage = withImage;
	}

	protected boolean isSearchOptions() {
		return searchOptions;
	}

	protected void setSearchOptions(boolean searchOptions) {
		this.searchOptions = searchOptions;
	}

	protected boolean isSearchCalendar() {
		return searchCalendar;
	}

	protected void setSearchCalendar(boolean searchCalendar) {
		this.searchCalendar = searchCalendar;
	}

	public String getSearchTitle() {
		return searchTitle;
	}

	public void setSearchTitle(String searchTitle) {
		this.searchTitle = searchTitle;
	}

	protected boolean isSearchDisplayAsPage() {
		return searchDisplayAsPage;
	}

	protected void setSearchDisplayAsPage(boolean searchDisplayAsPage) {
		this.searchDisplayAsPage = searchDisplayAsPage;
	}

}
