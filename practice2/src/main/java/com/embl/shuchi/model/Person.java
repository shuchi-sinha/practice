package com.embl.shuchi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="persons")
public class Person {
	private long id;
	
	private String first_name;
	private String last_name;
	private int age;
	private String favourite_colour;
	public Person() {
		super();
		
	}
	public Person(String first_name, String last_name, int age, String favourite_colour) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.age = age;
		this.favourite_colour = favourite_colour;
	}
  
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return first_name;
    }
    public void setFirstName(String firstName) {
        this.first_name = firstName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return last_name;
    }
    public void setLastName(String lastName) {
        this.last_name = lastName;
    }
    @Column(name = "age", nullable = false)
    public int getAge() {
  		return age;
  	}
  	public void setAge(int age) {
  		this.age = age;
  	}
  	 @Column(name = "favourite_colour", nullable = false)
  	public String getFavourite_colour() {
  		return favourite_colour;
  	}
  	public void setFavourite_colour(String favourite_colour) {
  		this.favourite_colour = favourite_colour;
  	}
	}