package com.microservpractice.microservicesprac.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.microservpractice.microservicesprac.beans.Car;
import com.microservpractice.microservicesprac.beans.MockData;
import com.microservpractice.microservicesprac.exception.CarNotFoundException;

@RestController
public class CarResource {

	@Autowired
	private MockData mockData;

	@GetMapping(path = "/cars")
	public List<Car> getCars() {
		return mockData.getCars();
	}

	// HATEOAS - Hypermedia as the engine of application state
	@GetMapping(path = "/cars/{id}")
	public EntityModel<Car> getCar(@PathVariable Integer id) {
		Car resultcar = mockData.getCars().stream().filter(car -> car.getId().equals(id)).findAny().orElse(null);
		if(resultcar == null) {
			throw new CarNotFoundException("Id :" + id);
		}

		EntityModel<Car> carEntityModel = EntityModel.of(resultcar);
		
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getCars());
		carEntityModel.add(linkTo.withRel("Get-All Cars"));
		
		return carEntityModel;
	}

	@PostMapping(path = "/cars")
	public ResponseEntity<Car> createCar(@Valid @RequestBody Car newCar) {
		if(newCar.getId() == null) {
			newCar.setId(mockData.getCars().size() + 1);
			mockData.addCar(newCar);
		}else if(mockData.getCars().stream().anyMatch(car -> car.getId().equals(newCar.getId()))) {
			EntityModel<Car> modifyCar = getCar(newCar.getId());
			modifyCar.getContent().setColor(newCar.getColor());
			modifyCar.getContent().setMake(newCar.getMake());
			modifyCar.getContent().setModel(newCar.getModel());
			modifyCar.getContent().setPrice(newCar.getPrice());
			modifyCar.getContent().setYear(newCar.getYear());
		}else {
			mockData.addCar(newCar);
		}

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newCar.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping(path = "/cars/{id}")
	public void deleteCar(@PathVariable Integer id) {
		mockData.getCars().removeIf(car -> car.getId().equals(id));
	}

}
