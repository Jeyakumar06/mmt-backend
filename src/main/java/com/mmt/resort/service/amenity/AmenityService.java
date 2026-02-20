package com.mmt.resort.service.amenity;

import java.util.List;

import com.mmt.resort.model.Amenity;

public interface AmenityService {

    Amenity createAmenity(String name);

    List<Amenity> getAllAmenities();

    Amenity updateAmenity(Long id, String name);

    void deleteAmenity(Long id);
}

