package com.thalasoft.learnintouch.data.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thalasoft.learnintouch.data.jpa.domain.Flash;

public interface FlashRepository extends GenericRepository<Flash, Long> {

	public Page<Flash> findAllOrderByFilename(Pageable page);

	public Page<Flash> findByFilename(String filename, Pageable page);

	public Page<Flash> findByWddx(String wddx, Pageable page);

}
