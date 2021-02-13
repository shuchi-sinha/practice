package com.embl.shuchi;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.embl.shuchi.model.Person;


public class RESTClient {

	private static final String GET_PERSONS_ENDPOINT_URL = "http://localhost:8080/api/v1/PERSONS";
	private static final String GET_PERSON_ENDPOINT_URL = "http://localhost:8080/api/v1/PERSONS/{id}";
	private static final String CREATE_PERSON_ENDPOINT_URL = "http://localhost:8080/api/v1/PERSONS";
	private static final String UPDATE_PERSON_ENDPOINT_URL = "http://localhost:8080/api/v1/PERSONS/{id}";
	private static final String DELETE_PERSON_ENDPOINT_URL = "http://localhost:8080/api/v1/PERSONS/{id}";
	private static RestTemplate restTemplate = new RestTemplate();

	public static void main(String[] args) {
		RESTClient springRestClient = new RESTClient();
		
		// Step1: first create a new person
		springRestClient.createperson();
		
		// Step 2: get new created person from step1
		springRestClient.getpersonById();
		
		// Step3: get all PERSONS
		springRestClient.getPersons();
		
		// Step4: Update person with id = 1
		springRestClient.updateperson();
		
		// Step5: Delete person with id = 1
		springRestClient.deleteperson();
	}

	private void getPersons() {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<String> result = restTemplate.exchange(GET_PERSONS_ENDPOINT_URL, HttpMethod.GET, entity,
				String.class);

		System.out.println(result);
	}

	private void getpersonById() {

		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "1");
		RestTemplate restTemplate = new RestTemplate();
		Person result = restTemplate.getForObject(GET_PERSON_ENDPOINT_URL, Person.class, params);

		System.out.println(result);
	}

	private void createperson() {

		Person newperson = new Person("John", "Keynes", 29, "red");
		
		RestTemplate restTemplate = new RestTemplate();
		Person result = restTemplate.postForObject(CREATE_PERSON_ENDPOINT_URL, newperson, Person.class);

		System.out.println(result);
	}

	private void updateperson() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "1");
		Person updatedperson = new Person("Mark", "Riera", 29, "red");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(UPDATE_PERSON_ENDPOINT_URL, updatedperson, params);
	}

	private void deleteperson() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "1");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(DELETE_PERSON_ENDPOINT_URL, params);
	}
}
