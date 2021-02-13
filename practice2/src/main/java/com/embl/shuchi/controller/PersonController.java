package com.embl.shuchi.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.embl.shuchi.exception.ResourceNotFoundException;
import com.embl.shuchi.model.Person;
import com.embl.shuchi.repository.PersonDataRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class PersonController {
	@Autowired
	private PersonDataRepository personRepository;

	@GetMapping("/persons")
	public List<Person> getAllPersons() {
		List<Person> list= personRepository.findAll();
//		for (Person l : list) {
//			System.out.println(l.getFirstName());
//			System.out.println(l.getLastName());
//			System.out.println(l.getAge());
//			System.out.println(l.getFavourite_colour());	
//		}
		return list;
	}

	@GetMapping("/persons/{id}")
	public ResponseEntity<Person> getPersonById(@PathVariable(value = "id") Long personId)
			throws ResourceNotFoundException {
		Person person = personRepository.findById(personId)
				.orElseThrow(() -> new ResourceNotFoundException("Person not found for this id :: " + personId));
		
		return ResponseEntity.ok().body(person);
	}

	@PostMapping("/persons")
	public Person createPerson(@Valid @RequestBody Person person) {
//		System.out.println(person.getFirstName());
//		System.out.println(person.getLastName());
//		System.out.println(person.getAge());
//		System.out.println(person.getFavourite_colour());
		return personRepository.save(person);
	}
	@PutMapping("/persons/{id}")
	public ResponseEntity<Person> updatePerson(@PathVariable(value = "id") Long personId,
			@Valid @RequestBody Person personDetails) throws ResourceNotFoundException {
		Person person = personRepository.findById(personId)
				.orElseThrow(() -> new ResourceNotFoundException("Person not found for this id :: " + personId));
		
		person.setFavourite_colour(personDetails.getFavourite_colour());
		person.setAge(personDetails.getAge());
		person.setLastName(personDetails.getLastName());
		person.setFirstName(personDetails.getFirstName());
		
		final Person updatedPerson = personRepository.save(person);
		return ResponseEntity.ok(updatedPerson);
	}
	@DeleteMapping("/persons/{id}")
	public Map<String, Boolean> deletePerson(@PathVariable(value = "id") Long personId)
			throws ResourceNotFoundException {
		Person person = personRepository.findById(personId)
				.orElseThrow(() -> new ResourceNotFoundException("Person not found for this id :: " + personId));

		personRepository.delete(person);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}