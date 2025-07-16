package com.wisestep.linkshortener.dto;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UrlMapping {

	 @Id
	 private String shortId;
	 private String originalUrl;
	 private LocalDateTime createdAt;

}
