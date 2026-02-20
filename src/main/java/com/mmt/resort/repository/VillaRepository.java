package com.mmt.resort.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mmt.resort.model.Villa;

public interface VillaRepository extends JpaRepository<Villa, Long> {
}
