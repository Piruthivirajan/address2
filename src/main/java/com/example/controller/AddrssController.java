package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Address;
import com.example.repository.AddressRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/addressbook")
public class AddrssController {

	
	@Autowired
	private AddressRepository addressRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Address> getAll(){
		return addressRepository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String create(@RequestBody Address address) {
		String result="";
		try{
			address.setId(null);
			addressRepository.saveAndFlush(address);
			result= "success";
		}
		catch(Exception e)
		{
			result="fail";
			throw e;	
		}
		return result;
		
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Address findone(@PathVariable Long id) {
		return addressRepository.findOne(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Long id) {
		String result="";
		try{
			result="success";
			addressRepository.delete(id);
		}
		catch(Exception e){
			result="fail";
		}
		
		return result;

	}
	@RequestMapping(method = RequestMethod.PUT)
	public String update(@RequestBody Address address,@PathVariable Long id) {
		String result="";
		try{
			address.setId(id);
			addressRepository.saveAndFlush(address);
			result= "success";
		}
		catch(Exception e)
		{
			result="fail";
			throw e;	
		}
		return result;
		
	}
	
}
