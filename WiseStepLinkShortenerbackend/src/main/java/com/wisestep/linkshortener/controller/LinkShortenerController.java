package com.wisestep.linkshortener.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wisestep.linkshortener.service.LinkShortenerService;

@CrossOrigin
@RestController
public class LinkShortenerController {

	@Autowired
	private LinkShortenerService linkShortenerService;

	@PostMapping("/shorten")
	public ResponseEntity<String> shortenUrl(@RequestBody String originalUrl) {
		return linkShortenerService.shortenUrl(originalUrl);
	}

	@GetMapping("/{shortId}")
	public ResponseEntity<Object> redirect(@PathVariable("shortId") String shortId) {
		return linkShortenerService.redirect(shortId);
	}

}
