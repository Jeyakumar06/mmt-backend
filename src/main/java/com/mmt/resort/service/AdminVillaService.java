package com.mmt.resort.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mmt.resort.model.Villa;

public interface AdminVillaService {

    Villa createVilla(Villa villa);

    Villa updateVilla(Long id, Villa villa);

    void deleteVilla(Long id);

    List<Villa> listAll();
    
    void uploadImages(Long villaId, MultipartFile[] files);

	void updateImage(Long imageId, MultipartFile file);

	void deleteImage(Long imageId);
}
