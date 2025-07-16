package com.wisestep.linkshortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wisestep.linkshortener.dto.UrlMapping;


@Repository
public interface UrlMappingRepo extends JpaRepository<UrlMapping, String> {
	
	UrlMapping findByShortId(String shortId);
	UrlMapping findByOriginalUrl(String originalUrl);
}
