package com.prarms.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prarms.dto.PetDTO;
import com.prarms.entity.Pet;
import com.prarms.service.PetService;
import com.prarms.util.PetConverter;

@RestController
public class PetController {

	@Autowired
	PetService petService;
	
	@Autowired
	PetConverter petConverter;
	
	@PostMapping("/savePet")
	public PetDTO saveStudent(@Valid @RequestBody PetDTO pDto)
	{
		final Pet pet = petConverter.convertDtoToPetEntity(pDto);
		return petService.savePet(pet);
	}
	
	@GetMapping("/getAllPet")
	public List<PetDTO> getAllDetails(){
		return petService.getAllDetails();
	}
	
	@PutMapping("/updatePet/{id}")
	public PetDTO updatePet( @RequestBody PetDTO pDto,@PathVariable("id")int pId) {
		final Pet pet = petConverter.convertDtoToPetEntity(pDto);
		return petService.updatePet(pet, pId);
	}
	@GetMapping("/getPetById/{id}")
	public PetDTO findPetById(@PathVariable("id")int id){
		return petService.getPetById(id);
	}
	
	@GetMapping("/getPetByName/{n}")
	public List<PetDTO> findPetByName(@PathVariable("n")String name){
		return petService.getPetByName(name);
	}
	
	@GetMapping("/getPetByBreed/{b}")
	public List<PetDTO> findPetByBreed(@PathVariable("b")String breed){
		return petService.getPetByBreed(breed);
	}
	
	@GetMapping("/getPetByCategory/{c}")
	public List<PetDTO> findPetByCategory(@PathVariable("c")String category){
		return petService.getPetByCategory(category);
	}
	
	@GetMapping("/countOfPets")
	public String countOfPets() {
		String msg="Number of pets available are :"+petService.countOfPet();
		
		return msg;
	}
	
	@GetMapping("/sortPetByName")
	public List<PetDTO> findAllSortedByName(){
		return petService.sortPetByName();
	}
	

}
