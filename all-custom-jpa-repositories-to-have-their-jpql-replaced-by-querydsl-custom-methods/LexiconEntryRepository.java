package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.LexiconEntry;

public interface LexiconEntryRepository extends GenericRepository<LexiconEntry, Long> {

	public LexiconEntry findByName(String name);
	
	@Query("SELECT l FROM LexiconEntry l ORDER BY l.name")
	public Page<LexiconEntry> findAllOrderByName(Pageable page);

	@Query("SELECT l FROM LexiconEntry l WHERE LOWER(l.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(l.explanation) LIKE LOWER(CONCAT('%', :searchTerm, '%')) ORDER BY l.name")
	public Page<LexiconEntry> search(@Param("searchTerm") String searchTerm, Pageable page);

	public List<LexiconEntry> findByImage(String image);

}
