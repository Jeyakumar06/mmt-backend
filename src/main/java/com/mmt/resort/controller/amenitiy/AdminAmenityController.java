package com.mmt.resort.controller.amenitiy;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mmt.resort.model.Amenity;
import com.mmt.resort.service.amenity.AmenityService;

@RestController
@RequestMapping("/api/admin/amenities")
public class AdminAmenityController {

    private final AmenityService amenityService;

    public AdminAmenityController(AmenityService amenityService) {
        this.amenityService = amenityService;
    }

    @PostMapping
    public ResponseEntity<Amenity> create(@RequestBody Amenity amenity) {
        return ResponseEntity.ok(
            amenityService.createAmenity(amenity.getName())
        );
    }

    @GetMapping
    public ResponseEntity<List<Amenity>> list() {
        return ResponseEntity.ok(
            amenityService.getAllAmenities()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Amenity> update(
            @PathVariable Long id,
            @RequestBody Amenity amenity) {

        return ResponseEntity.ok(
            amenityService.updateAmenity(id, amenity.getName())
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        amenityService.deleteAmenity(id);
        return ResponseEntity.noContent().build();
    }
}


