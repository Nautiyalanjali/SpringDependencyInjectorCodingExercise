package org.classes;

/* Book class with the following fields: "title", "author", and "price*/

import java.math.BigDecimal;

public class Book {

	private String title;
	private String author;
	private BigDecimal price;

	public Book(String title, String author, BigDecimal price) {

		this.title = title;
		this.author = author;
		this.price = price;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", price=" + price + ", author=" + author + "]";
	}

}
