package com.wisestep.linkshortener.serviceImpl;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.wisestep.linkshortener.dto.UrlMapping;
import com.wisestep.linkshortener.repository.UrlMappingRepo;
import com.wisestep.linkshortener.service.LinkShortenerService;

@Service
public class LinkShortenerSerivceImpl implements LinkShortenerService {

	@Autowired
	private UrlMappingRepo urlMappingRepo;
	 private final Duration expiry = Duration.ofMinutes(5);
	 
	@Override
	public ResponseEntity<String> shortenUrl(String originalUrl) {
		 if (!isValidUrl(originalUrl)) {
	            return ResponseEntity.badRequest().body("Invalid URL");
	        }

	        if (urlMappingRepo.findByOriginalUrl(originalUrl)!=null) {
	            return ResponseEntity.badRequest().body("URL already exists");
	        }

	        String shortId = generateShortId();
	        urlMappingRepo.save(new UrlMapping(shortId,originalUrl, LocalDateTime.now()));
	        return ResponseEntity.ok(shortId);
	}

	@Override
	public ResponseEntity<Object> redirect(String shortId) {
		 UrlMapping mapping = urlMappingRepo.findByShortId(shortId);

	        if (mapping == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("URL does not exist. <a href='/create'>Create a new one</a>");
	        }

	        if (Duration.between(mapping.getCreatedAt(), LocalDateTime.now()).compareTo(expiry) > 0) {
	            return ResponseEntity.status(HttpStatus.GONE).body("URL has expired.");
	        }

	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(URI.create(normalizeUrl(mapping.getOriginalUrl())));
	        return new ResponseEntity<>(headers, HttpStatus.FOUND);
	}

	private boolean isValidUrl(String url) {
        try {
        	if (!url.matches("^(https?://).*")) {
                url = "http://" + url;
            }
            
            URL checkUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) checkUrl.openConnection();
            connection.setRequestMethod("HEAD");
            connection.setConnectTimeout(2000); // 2 seconds timeout
            connection.setReadTimeout(2000);
            int responseCode = connection.getResponseCode();

            return (200 <= responseCode && responseCode < 400);
        } catch (Exception e) {
            return false;
        }
    }

    private String generateShortId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
    
    private String normalizeUrl(String url) {
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            return "https://" + url;
        }
        return url;
    }

}
