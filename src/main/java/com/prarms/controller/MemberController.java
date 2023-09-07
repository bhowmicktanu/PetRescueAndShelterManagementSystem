package com.prarms.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.prarms.dto.MemberDTO;

import com.prarms.entity.Member;
import com.prarms.service.MemberService;
import com.prarms.util.MemberConverter;



@RestController
public class MemberController {

	@Autowired
	MemberService memberService;
	
	@Autowired
	MemberConverter memberConverter;
	//save members to the database
	@PostMapping("/saveMember")
	public MemberDTO saveMember(@RequestBody MemberDTO membrDto) 
	{
		final Member membr = memberConverter.convertDtoToMembrEntity(membrDto);
		return memberService.saveMember(membr);
	}
	
	@GetMapping("/getAllMembers")
	public List<MemberDTO> getAllDetails(){
		return memberService.getAllDetails();
	}
	
	@PutMapping("/updateMember/{id}")
	public MemberDTO updateMember( @RequestBody MemberDTO mDto,@PathVariable("id")int mId) {
		final Member mbr = memberConverter.convertDtoToMembrEntity(mDto);
		return memberService.updateMember(mbr, mId);
	}
	
	@GetMapping("/getMemberById/{mId}")
	public MemberDTO getMemberById(@PathVariable("mId") int id) {
		return memberService.getmemberById(id);
	}
	
	@GetMapping("/getMemberByName/{name}")
	public List<MemberDTO> findMemberByName(@PathVariable("name") String name) {
		return (memberService.getMemberByName(name));
	}

	@GetMapping("/getMemberByEmail/{email}")
	public MemberDTO findMemberByEmail(@PathVariable("email") String email) {
		return (memberService.getMemberByEmail(email));
	}
	
	@GetMapping("/getMemberByPosition/{Position}")
	public List<MemberDTO> findMemberByPosition(@PathVariable("position") String position) {
		return (memberService.getMemberByPosition(position));
	}
}
	
