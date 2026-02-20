package com.mmt.resort.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.stream.Collectors;

import com.mmt.resort.dto.VillaResponseDto;
import com.mmt.resort.model.Villa;
import com.mmt.resort.repository.VillaRepository;

@RestController
@RequestMapping("/api/villas")
public class PublicVillaController {

    private final VillaRepository villaRepository;

    public PublicVillaController(VillaRepository villaRepository) {
        this.villaRepository = villaRepository;
    }

    @GetMapping
    public Page<VillaResponseDto> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);

        return villaRepository.findAll(pageable)
                .map(this::mapToDto);
    }

    @GetMapping("/{id}")
    public VillaResponseDto getOne(@PathVariable Long id) {
        Villa villa = villaRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Villa not found"
                        )
                );

        return mapToDto(villa);
    }

    private VillaResponseDto mapToDto(Villa villa) {

        VillaResponseDto dto = new VillaResponseDto();
        dto.setId(villa.getId());
        dto.setName(villa.getName());
        dto.setLocation(villa.getLocation());
        dto.setBhk(villa.getBhk());
        dto.setPool(villa.getPool());
        dto.setBeachView(villa.isBeachView());
        dto.setPrice(villa.getPrice());
        dto.setGuestCapacity(villa.getGuestCapacity());
        dto.setDescription(villa.getDescription());
        dto.setAmenities(
                villa.getAmenities()
                        .stream()
                        .map(a -> a.getName())
                        .collect(Collectors.toList())
        );

        dto.setImages(
                villa.getImages()
                        .stream()
                        .map(i -> i.getImageUrl())
                        .collect(Collectors.toList())
        );

        return dto;
    }
}

