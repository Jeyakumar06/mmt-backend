package com.mmt.resort.dto;

import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VillaRequestDTO {

    @NotBlank(message = "Villa name is required")
    private String name;

    @NotBlank(message = "Location is required")
    private String location;

    @Min(value = 1, message = "BHK must be at least 1")
    private int bhk;

    @Min(value = 1, message = "Price must be greater than 0")
    private int price;

    @Min(value = 1, message = "Guest capacity must be at least 1")
    private int guestCapacity;

    @NotBlank(message = "Pool type is required")
    private String pool;

    private boolean beachView;

    @Size(max = 2000, message = "Description too long")
    private String description;

    @NotEmpty(message = "At least one amenity must be selected")
    private List<Long> amenityIds;

	public boolean isBeachView() {
		return beachView;
	}

	public void setBeachView(boolean beachView) {
		this.beachView = beachView;
	}
}
