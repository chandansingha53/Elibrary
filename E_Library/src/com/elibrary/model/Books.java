package com.elibrary.model;

public class Books {
	
	private int id;
	private String title ;
	private String author;
	private String genre;
	private String publisher;
	private String description;
	
	private String contentType;
	private byte[] pdfContent;
	private byte[] image;
	public Books() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Books(String title, String author, String genre,String publisher,
			String description,byte[] image) {
		super();
		
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.publisher = publisher;
		this.description = description;
		this.image = image;
	}
	
	public Books(int id, String title, String author,  String genre,String publisher,
			String description,byte[] image) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.publisher = publisher;
		this.description = description;
		this.image = image;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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


	public String getPublisher() {
		return publisher;
	}


	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getGenre() {
		return genre;
	}


	public void setGenre(String genre) {
		this.genre = genre;
	}


	public String getContentType() {
		return contentType;
	}


	public void setContentType(String contentType) {
		this.contentType = contentType;
	}


	public byte[] getPdfContent() {
		return pdfContent;
	}


	public void setPdfContent(byte[] pdfContent) {
		this.pdfContent = pdfContent;
	}
	
	public void setImage(byte[] image) {
		this.image = image;
	}
	
	public byte[] getImage() {
		return image;
	}
		
	

}
