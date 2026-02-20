package com.mmt.resort.service.impl;

import org.springframework.stereotype.Service;

import com.mmt.resort.model.Villa;
import com.mmt.resort.repository.VillaRepository;
import com.mmt.resort.service.VillaService;

@Service
public class VillaServiceImpl implements VillaService {

    private final VillaRepository villaRepository;

    public VillaServiceImpl(VillaRepository villaRepository) {
        this.villaRepository = villaRepository;
    }

    @Override
    public Villa create(Villa villa) {
        return villaRepository.save(villa);
    }

    @Override
    public Villa update(Long id, Villa villa) {
        Villa existing = villaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Villa not found"));

        existing.setName(villa.getName());
        existing.setLocation(villa.getLocation());
        existing.setBhk(villa.getBhk());
        existing.setPrice(villa.getPrice());
        existing.setGuestCapacity(villa.getGuestCapacity());
        existing.setDescription(villa.getDescription());

        return villaRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        villaRepository.deleteById(id);
    }


}

