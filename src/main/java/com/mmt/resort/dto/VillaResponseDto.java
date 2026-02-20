package com.mmt.resort.dto;

import java.util.List;

public class VillaResponseDto {

    private Long id;
    private String name;
    private String location;
    private int bhk;
    private String pool;
    private boolean beachView;
    private int price;
    private int guestCapacity;
    private String description;

    private List<String> amenities;
    private List<String> images;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getBhk() {
		return bhk;
	}
	public void setBhk(int bhk) {
		this.bhk = bhk;
	}
	public boolean isBeachView() {
		return beachView;
	}
	public void setBeachView(boolean beachView) {
		this.beachView = beachView;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getGuestCapacity() {
		return guestCapacity;
	}
	public void setGuestCapacity(int guestCapacity) {
		this.guestCapacity = guestCapacity;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<String> getAmenities() {
		return amenities;
	}
	public void setAmenities(List<String> amenities) {
		this.amenities = amenities;
	}
	public List<String> getImages() {
		return images;
	}
	public void setImages(List<String> images) {
		this.images = images;
	}
	public String getPool() {
		return pool;
	}
	public void setPool(String pool) {
		this.pool = pool;
	}
	 
	
}

