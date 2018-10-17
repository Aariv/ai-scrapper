/**
 * 
 */
package com.shab.artificon.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shab.artificon.model.User;
import com.shab.artificon.repository.IUserRepository;

/**
 * @author zentere
 *
 */
@RestController
@RequestMapping("/api")
public class AzureUsersController {

	@Autowired
	private IUserRepository userRepository;

	@SuppressWarnings("deprecation")
	@GetMapping("/users/{page}/{size}")
	public List<User> getUsers(@PathVariable int page, @PathVariable int size) {
		return userRepository.findAll(new PageRequest(page, size)).getContent();
	}

}
