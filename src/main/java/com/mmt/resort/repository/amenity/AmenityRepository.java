package com.mmt.resort.repository.amenity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mmt.resort.model.Amenity;

public interface AmenityRepository extends JpaRepository<Amenity, Long> {
    Optional<Amenity> findByName(String name);
}

