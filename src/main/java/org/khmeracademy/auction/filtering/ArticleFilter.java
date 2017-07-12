package org.khmeracademy.auction.filtering;

public class ArticleFilter {

	private String title;
	private String id;
	private String date;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "ArticleFilter [title=" + title + ", id=" + id + ", date=" + date + ", getTitle()=" + getTitle()
				+ ", getId()=" + getId() + ", getDate()=" + getDate() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
}
