package com.microservpractice.microservicesprac.beans;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.io.Resources;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;



@Component
@Scope("singleton")
public class MockData {

	private final static Logger LOGGER = Logger.getLogger(MockData.class.getName());
	
	public List<Person> peopleList = new ArrayList<>();
	public List<Car> carList = new ArrayList<>();

	public MockData() {
		try {
			InputStream inputStream1 = Resources.getResource("people.json").openStream();
			String json1 = IOUtils.toString(inputStream1);
			Type listType1 = new TypeToken<ArrayList<Person>>() {
			}.getType();
			peopleList = new Gson().fromJson(json1, listType1);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Error", e);
		}
		
		String json2;
		Type listType2;
		try {
			InputStream inputStream2 = Resources.getResource("cars.json").openStream();
			json2 = IOUtils.toString(inputStream2);
			listType2 = new TypeToken<ArrayList<Car>>() {
			}.getType();
			carList = new Gson().fromJson(json2, listType2);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Error", e);
		}
		
		
	}

	public List<Person> getPeople() {
		return peopleList;
	}

	public List<Car> getCars() {
		return carList;
	}

	public void addCar(Car car) {
		carList.add(car);
	}
	
	public void addPerson(Person person) {
		peopleList.add(person);
	}
}
