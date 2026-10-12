package com.example.devcon.patent;

import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/patents")
public class PatentController {

	private static final Pattern PATENT_ID_PATTERN = Pattern.compile("[A-Za-z0-9-]{1,50}");

	@GetMapping("/original")
	public Map<String, String> showOriginal(@RequestParam String patentId) {
		if (!PATENT_ID_PATTERN.matcher(patentId).matches()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid patent ID");
		}

		String originalUrl = UriComponentsBuilder.fromPath("/original.html")
				.queryParam("patentId", patentId)
				.build()
				.encode()
				.toUriString();

		return Map.of("originalUrl", originalUrl);
	}
}
