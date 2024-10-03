package com.example.demo.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="books")
public class Library {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="isbn")
	private int isbn;
	@Column(name="title")
	private String title;
	@Column(name="author")
	private String author;
	@Column(name="publication_year")
	private int publication_year;
	public int getIsbn() {
		return isbn;
	}
	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getPublication_year() {
		return publication_year;
	}
	public void setPublication_year(int publication_year) {
		this.publication_year = publication_year;
	}
	public Library(int isbn, String title, String author, int publication_year) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.publication_year = publication_year;
	}
	public Library() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Library [isbn=" + isbn + ", title=" + title + ", author=" + author + ", publication_year="
				+ publication_year + "]";
	}
	
	
}
