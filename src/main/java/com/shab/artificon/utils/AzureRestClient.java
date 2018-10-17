/**
 * 
 */
package com.shab.artificon.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.shab.artificon.model.AuthDto;
import com.shab.artificon.model.User;
import com.shab.artificon.model.UserDto;
import com.shab.artificon.model.UserMasterDto;
import com.shab.artificon.repository.IUserRepository;

/**
 * @author zentere
 *
 */
@Component
public class AzureRestClient {

	private final RestTemplate restTemplate;
	private String token;

	@Autowired
	private IUserRepository userRepository;

	public AzureRestClient(final RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	private HttpHeaders getHttpHeaders() {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("Authorization", "Bearer " + token);
		return requestHeaders;
	}

	public void asureLoginAndToken() {
		MultiValueMap<String, String> loginRequest = new LinkedMultiValueMap<String, String>();
		loginRequest.add("grant_type", AzureConstants.GRANT_TYPE);
		loginRequest.add("client_id", AzureConstants.CLIENT_ID);
		loginRequest.add("client_secret", AzureConstants.SECRET);
		loginRequest.add("resource", AzureConstants.RESOURCE);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(loginRequest,
				headers);

		ResponseEntity<AuthDto> response = restTemplate.postForEntity(constructLoginURL(), request, AuthDto.class);
		System.out.println(response.getBody().getAccess_token());
		this.token = response.getBody().getAccess_token();
		//getUsersFromAzure();
	}

	private String constructLoginURL() {
		return "https://login.microsoftonline.com/" + AzureConstants.APP_ID + "/oauth2/token";
	}

	public UserMasterDto getUsersFromAzure() {
		HttpHeaders requestHeaders = getHttpHeaders();
		HttpEntity<?> httpEntity = new HttpEntity<Object>(requestHeaders);
		ResponseEntity<UserMasterDto> response = restTemplate.exchange("https://graph.microsoft.com/beta/users",
				HttpMethod.GET, httpEntity, new ParameterizedTypeReference<UserMasterDto>() {
				});
		UserMasterDto users = response.getBody();
		updateAzureUsersInMySQL(users);
		return users;
	}

	public void updateAzureUsersInMySQL(UserMasterDto userMasterDto) {
		List<User> data = userMasterDto.getValue().stream().map(userDto -> convertToEntity(userDto))
				.collect(Collectors.toList());
		userRepository.saveAll(data);
	}

	private User convertToEntity(UserDto userDto) {
		User user = new User();
		user.setAzureId(userDto.getId());
		user.setEmployeeId(userDto.getEmployeeId());
		user.setJobTitle(userDto.getJobTitle());
		user.setUserPrincipalName(userDto.getUserPrincipalName());
		user.setUserType(userDto.getUserType());
		user.setRegisteredMobile(userDto.getExtension_14b8a2d09aa64c28804c78276afbcd34_RegisteredMobile());
		return user;
	}
}
