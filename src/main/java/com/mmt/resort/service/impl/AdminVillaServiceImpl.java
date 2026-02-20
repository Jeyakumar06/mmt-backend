package com.mmt.resort.service.impl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mmt.resort.model.Villa;
import com.mmt.resort.model.VillaImage;
import com.mmt.resort.repository.VillaImageRepository;
import com.mmt.resort.repository.VillaRepository;
import com.mmt.resort.service.AdminVillaService;

import jakarta.transaction.Transactional;

@Service
public class AdminVillaServiceImpl implements AdminVillaService {

	private static final String UPLOAD_DIR =
	        System.getProperty("user.dir") + "/uploads/villas/";


	private final VillaRepository villaRepository;
	private final VillaImageRepository villaImageRepository;

	public AdminVillaServiceImpl(VillaRepository villaRepository,
	                             VillaImageRepository villaImageRepository) {
	    this.villaRepository = villaRepository;
	    this.villaImageRepository = villaImageRepository;
	}

        

    @Override
    public Villa createVilla(Villa villa) {
        return villaRepository.save(villa);
    }

    @Override
    public Villa updateVilla(Long id, Villa villa) {
        Villa existing = villaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Villa not found"));

        existing.setName(villa.getName());
        existing.setLocation(villa.getLocation());
        existing.setBhk(villa.getBhk());
        existing.setPool(villa.getPool());
        existing.setBeachView(villa.isBeachView());
        existing.setPrice(villa.getPrice());
        existing.setGuestCapacity(villa.getGuestCapacity());
        existing.setDescription(villa.getDescription());
        existing.setAmenities(villa.getAmenities());

        return villaRepository.save(existing);
    }

    @Override
    public void deleteVilla(Long id) {
        villaRepository.deleteById(id);
    }

    @Override
    public List<Villa> listAll() {
        return villaRepository.findAll();
    }

    @Override
    @Transactional
    public void uploadImages(Long villaId, MultipartFile[] files) {

        Villa villa = villaRepository.findById(villaId)
                .orElseThrow(() -> new RuntimeException("Villa not found"));

        String uploadDir = "uploads/villas/";

        for (MultipartFile file : files) {

            if (file.isEmpty()) continue;

            try {
                String originalName = file.getOriginalFilename();
                String extension = "";

                if (originalName != null && originalName.contains(".")) {
                    extension = originalName.substring(originalName.lastIndexOf("."));
                }

                String fileName =
                        "villa_" + villaId + "_" + System.currentTimeMillis() + extension;

                Path path = Paths.get(uploadDir, fileName);
                Files.createDirectories(path.getParent());
                Files.write(path, file.getBytes());

                VillaImage image = new VillaImage();
                image.setImageUrl("/uploads/villas/" + fileName);

                
                villa.addImage(image);

            } catch (Exception e) {
                throw new RuntimeException("Image upload failed", e);
            }
        }

        
        villaRepository.save(villa);
    }
    
    @Override
    @Transactional
    public void deleteImage(Long imageId) {

        VillaImage image = villaImageRepository.findById(imageId)
                .orElseThrow(() -> new RuntimeException("Image not found"));

        try {
            Path path = Paths.get("uploads", image.getImageUrl().replace("/uploads/", ""));
            Files.deleteIfExists(path);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete image file", e);
        }

        villaImageRepository.delete(image);
    }
    
    
    @Override
    @Transactional
    public void updateImage(Long imageId, MultipartFile file) {

        VillaImage image = villaImageRepository.findById(imageId)
                .orElseThrow(() -> new RuntimeException("Image not found"));

        try {
            // Delete old file
            Path oldPath = Paths.get("uploads", image.getImageUrl().replace("/uploads/", ""));
            Files.deleteIfExists(oldPath);

            // Save new file
            String extension = "";
            String originalName = file.getOriginalFilename();

            if (originalName != null && originalName.contains(".")) {
                extension = originalName.substring(originalName.lastIndexOf("."));
            }

            String newFileName = "villa_" + image.getVilla().getId()
                    + "_" + java.util.UUID.randomUUID() + extension;

            Path newPath = Paths.get(UPLOAD_DIR, newFileName);
            Files.copy(file.getInputStream(), newPath);

            image.setImageUrl("/uploads/villas/" + newFileName);

            villaImageRepository.save(image);

        } catch (Exception e) {
            throw new RuntimeException("Image update failed", e);
        }
    }


}

