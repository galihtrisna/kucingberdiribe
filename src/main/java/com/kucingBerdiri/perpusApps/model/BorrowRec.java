package com.kucingBerdiri.perpusApps.model;

import java.time.LocalDateTime;

import com.kucingBerdiri.perpusApps.model.base.AbstractEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class BorrowRec extends AbstractEntity {
	
	
	
	
	@ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

	@ManyToOne
    @JoinColumn(name = "bookId", nullable = false)
    private Book book;

    private LocalDateTime startBorrow;
    private LocalDateTime endBorrow;
    private String status;
    private String documentCode;
    private String jenis;
    public String getJenis() {
		return jenis;
	}
	public void setJenis(String jenis) {
		this.jenis = jenis;
	}
	public String getDocumentCode() {
		return documentCode;
	}
	public void setDocumentCode(String documentCode) {
		this.documentCode = documentCode;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public LocalDateTime getStartBorrow() {
		return startBorrow;
	}
	public void setStartBorrow(LocalDateTime startBorrow) {
		this.startBorrow = startBorrow;
	}
	public LocalDateTime getEndBorrow() {
		return endBorrow;
	}
	public void setEndBorrow(LocalDateTime endBorrow) {
		this.endBorrow = endBorrow;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
