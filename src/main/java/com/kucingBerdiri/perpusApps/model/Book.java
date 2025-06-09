package com.kucingBerdiri.perpusApps.model;

import java.util.List;

import com.kucingBerdiri.perpusApps.model.base.AbstractEntity;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Book extends AbstractEntity {
	

	@NotEmpty(message = "Judul tidak boleh kosong")
	private String title;
	@NotEmpty(message = "Author tidak boleh kosong")
	private String author;
	
	private String publisher;
	
	@NotNull(message = "Tahun tidak boleh kosong")
	@Min(value = 1900, message = "Tahun minimal 1900")
	private Integer year;
	
	@Column(unique = true)
	@NotNull(message = "ISBN tidak boleh kosong, Ganti dengan kode buku")
	private String isbn;
	
	@NotEmpty(message = "Thumbnail tidak boleh kosong")
	private String thumbnail;
	
	@NotNull(message = "Tahun tidak boleh kosong")
	@Min(value = 1, message = "Stok minimal 1")
	private Integer stocks;
	
	@Nullable
	private Boolean digitalAvail;
	
	private String jenisBuku;
	
	private Integer pages;
	
	public Integer getPages() {
		return pages;
	}
	public void setPages(Integer pages) {
		this.pages = pages;
	}
	public Boolean getDigitalAvail() {
		return digitalAvail;
	}
	public Boolean isDigitalAvail() {
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
	
	
	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<BorrowRec> borrowRecords;
	
	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
	private List<Review> reviews;
	
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
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
	public Integer getStocks() {
		return stocks;
	}
	public void setStocks(Integer stocks) {
		this.stocks = stocks;
	}
	
	

}
