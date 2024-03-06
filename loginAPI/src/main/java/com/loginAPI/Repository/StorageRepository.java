package com.loginAPI.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loginAPI.Entity.ImageData;

public interface StorageRepository extends JpaRepository<ImageData, Long>{
	 Optional<ImageData> findByName(String fileName);
}
