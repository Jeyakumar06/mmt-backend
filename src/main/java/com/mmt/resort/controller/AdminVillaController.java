package com.mmt.resort.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.mmt.resort.model.Villa;
import com.mmt.resort.service.AdminVillaService;

@RestController
@RequestMapping("/api/admin/villas")
public class AdminVillaController {

    private final AdminVillaService adminVillaService;

    public AdminVillaController(AdminVillaService adminVillaService) {
        this.adminVillaService = adminVillaService;
    }

    @PostMapping
    public Villa create(@RequestBody Villa villa) {
        return adminVillaService.createVilla(villa);
    }

    @PutMapping("/{id}")
    public Villa update(@PathVariable Long id, @RequestBody Villa villa) {
        return adminVillaService.updateVilla(id, villa);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        adminVillaService.deleteVilla(id);
    }

    @GetMapping
    public List<Villa> listAll() {
    	System.out.println("hit");
        return adminVillaService.listAll();
    }

    @PostMapping("/{villaId}/images")
    public void uploadImages(
            @PathVariable Long villaId,
            @RequestParam("files") MultipartFile[] files
    ) {
        adminVillaService.uploadImages(villaId, files);
    }
    
    @PostMapping("/images/{imageId}")
    public ResponseEntity<String> updateImage(
            @PathVariable Long imageId,
            @RequestParam("file") MultipartFile file) {

        adminVillaService.updateImage(imageId, file);
        return ResponseEntity.ok("Image updated successfully");
    }

    @DeleteMapping("/images/{imageId}")
    public ResponseEntity<String> deleteImage(@PathVariable Long imageId) {
        adminVillaService.deleteImage(imageId);
        return ResponseEntity.ok("Image deleted successfully");
    }

}

