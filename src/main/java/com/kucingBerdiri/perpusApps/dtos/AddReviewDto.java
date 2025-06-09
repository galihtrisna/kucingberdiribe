package com.kucingBerdiri.perpusApps.dtos;

public class AddReviewDto {
    private String content;
    private Integer rating;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
}
