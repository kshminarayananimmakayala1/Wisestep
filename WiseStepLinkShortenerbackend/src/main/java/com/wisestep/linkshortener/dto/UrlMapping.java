package com.wisestep.linkshortener.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlMapping {

	 private String originalUrl;
	 private LocalDateTime createdAt;

}
