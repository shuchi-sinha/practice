package com.embl.shuchi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.embl.shuchi.model.Person;

@Repository

public interface PersonDataRepository extends JpaRepository<Person, Long>{

}
