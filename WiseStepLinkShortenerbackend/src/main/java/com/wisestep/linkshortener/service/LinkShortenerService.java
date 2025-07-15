package com.wisestep.linkshortener.service;

import org.springframework.http.ResponseEntity;

public interface LinkShortenerService {

	public ResponseEntity<String> shortenUrl(String originalUrl);
	public ResponseEntity<Object> redirect(String shortId);
}
