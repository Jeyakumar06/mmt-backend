package com.mmt.resort.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "villas")
public class Villa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    private int bhk;
    private String pool;         
    private boolean beachView;
    private int price;
    private int guestCapacity;

    @Column(columnDefinition = "TEXT")
    private String description;

    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "villa_amenities",
        joinColumns = @JoinColumn(name = "villa_id"),
        inverseJoinColumns = @JoinColumn(name = "amenity_id")
    )
    @JsonIgnoreProperties({"amenities"})
    private Set<Amenity> amenities = new HashSet<>();

 
    @OneToMany(
    	    mappedBy = "villa",
    	    cascade = CascadeType.ALL,
    	    orphanRemoval = true
    	)
    private List<VillaImage> images = new ArrayList<>();
    
    public void addImage(VillaImage image) {
        images.add(image);
        image.setVilla(this);
    }

    public void removeImage(VillaImage image) {
        images.remove(image);
        image.setVilla(null);
    }



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


	public String getPool() {
		return pool;
	}


	public void setPool(String pool) {
		this.pool = pool;
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


	public Set<Amenity> getAmenities() {
		return amenities;
	}


	public void setAmenities(Set<Amenity> amenities) {
		this.amenities = amenities;
	}


	public List<VillaImage> getImages() {
		return images;
	}


	public void setImages(List<VillaImage> images) {
		this.images = images;
	}
    
    
}
