package com.kucingBerdiri.perpusApps.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class AddBookRequest {
    
    @NotEmpty(message = "Judul tidak boleh kosong")
    private String title;

    @NotEmpty(message = "Author tidak boleh kosong")
    private String author;

    private String publisher;

    @NotNull(message = "Tahun tidak boleh kosong")
    @Min(value = 1900, message = "Tahun minimal 1900")
    private Integer year;

    @NotNull(message = "ISBN tidak boleh kosong")
    private String isbn;

    @NotEmpty(message = "Thumbnail tidak boleh kosong")
    private String thumbnail;

    @NotNull(message = "Stok tidak boleh kosong")
    @Min(value = 1, message = "Stok minimal 1")
    private Integer stocks;

    private Boolean digitalAvail;

    private String jenisBuku;

    private Integer pages;

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

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public Integer getStocks() {
		return stocks;
	}

	public void setStocks(Integer stocks) {
		this.stocks = stocks;
	}

	public Boolean getDigitalAvail() {
		return digitalAvail;
	}

	public void setDigitalAvail(Boolean digitalAvail) {
		this.digitalAvail = digitalAvail;
	}

	public String getJenisBuku() {
		return jenisBuku;
	}

	public void setJenisBuku(String jenisBuku) {
		this.jenisBuku = jenisBuku;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

    
}