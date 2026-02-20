package com.mmt.resort.service.amenity.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mmt.resort.model.Amenity;
import com.mmt.resort.repository.amenity.AmenityRepository;
import com.mmt.resort.service.amenity.AmenityService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AmenityServiceImpl implements AmenityService {

    private final AmenityRepository amenityRepository;

    public AmenityServiceImpl(AmenityRepository amenityRepository) {
        this.amenityRepository = amenityRepository;
    }

    @Override
    public Amenity createAmenity(String name) {

        if (amenityRepository.findByName(name).isPresent()) {
            throw new RuntimeException("Amenity already exists");
        }

        Amenity amenity = new Amenity();
        amenity.setName(name);

        return amenityRepository.save(amenity);
    }

    @Override
    public List<Amenity> getAllAmenities() {
        return amenityRepository.findAll();
    }

    @Override
    public Amenity updateAmenity(Long id, String name) {

        Amenity amenity = amenityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Amenity not found"));

        amenity.setName(name);

        return amenityRepository.save(amenity);
    }

    @Override
    public void deleteAmenity(Long id) {
        amenityRepository.deleteById(id);
    }
}

