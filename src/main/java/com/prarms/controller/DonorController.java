package com.prarms.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prarms.dto.DonorDTO;
import com.prarms.entity.Donor;
import com.prarms.service.DonorService;
import com.prarms.util.DonorConverter;

@RestController
public class DonorController {

	@Autowired
	DonorService dnrService;

	@Autowired
	DonorConverter dnrConverter;

	@PostMapping("/saveDonor")
	public DonorDTO saveDonor(@RequestBody @Valid DonorDTO dnrDto) {
		final Donor dnr = dnrConverter.convertDtoToDnrEntity(dnrDto);
		return dnrService.saveDonor(dnr);
	}

	@PutMapping("/updateDonor/{dnrId}")
	public DonorDTO updateDonorPanel(@PathVariable("dnrId") int dnrId, @Valid @RequestBody DonorDTO dnrDto) {
		final Donor dnr = dnrConverter.convertDtoToDnrEntity(dnrDto);
		return dnrService.updateDonor(dnrId, dnr);
	}
	
	@GetMapping("/getAllDonor")
	public List<DonorDTO> getAllDonors() {
		return dnrService.getAllDonor();
	}
	
	@GetMapping("/getDonorById/{dnrId}")
	public DonorDTO getDonorById(@PathVariable("dnrId") int id) {
		return dnrService.getDonorById(id);
	}
	
	@GetMapping("/getDonorByName/{name}")
	public List<DonorDTO> findDonorByName(@PathVariable("name") String name) {
		return (dnrService.getDonorByName(name));
	}

	@GetMapping("/getDonorByEmail/{email}")
	public DonorDTO findDonorByEmail(@PathVariable("email") String email) {
		return (dnrService.getDonorByEmail(email));
	}
	
	@GetMapping("/getTotalDonationAmount")
	public String getTotalDonationAmount(){
		return "Total donation :"+ dnrService.getTotalDonationAmount();
	}




}