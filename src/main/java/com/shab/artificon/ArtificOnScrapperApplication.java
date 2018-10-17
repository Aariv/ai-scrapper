package com.shab.artificon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.shab.artificon.utils.AzureRestClient;

@SpringBootApplication
public class ArtificOnScrapperApplication implements CommandLineRunner {
	
	@Autowired
	private AzureRestClient azureRestClient;

	public static void main(String[] args) {
		SpringApplication.run(ArtificOnScrapperApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Override
	public void run(String... args) throws Exception {
		azureRestClient.asureLoginAndToken();
		azureRestClient.getUsersFromAzure();
	}
}
