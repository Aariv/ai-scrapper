/**
 * 
 */
package com.shab.artificon.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shab.artificon.model.ArtificHelp;
import com.shab.artificon.model.UrlDto;
import com.shab.artificon.repository.IArtificHelpRepository;
import com.shab.artificon.utils.JSoupUtils;

/**
 * @author zentere
 *
 */
@RestController
@RequestMapping("/api")
public class ArtificHelpController {

	@Autowired
	private IArtificHelpRepository artificHelpRepository;

	@Autowired
	private JSoupUtils jSoupUtils;

	@SuppressWarnings("deprecation")
	@GetMapping("/helps/{page}/{size}")
	public List<ArtificHelp> getHelps(@PathVariable int page, @PathVariable int size) {
		return artificHelpRepository.findAll(new PageRequest(page, size)).getContent();
	}

	@PostMapping("/helps")
	public String scrapeHelp(@RequestBody UrlDto urlDto) {
		ArtificHelp artificHelp = jSoupUtils.convertUrlToHelp(urlDto.getUrl());
		if (artificHelp != null) {
			artificHelpRepository.save(artificHelp);
		}
		return "Success";
	}
}
