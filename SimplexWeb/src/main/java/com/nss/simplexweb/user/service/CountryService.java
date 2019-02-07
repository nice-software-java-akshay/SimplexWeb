package com.nss.simplexweb.user.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nss.simplexweb.user.model.Country;
import com.nss.simplexweb.user.repository.CountryRepository;

@Service("countryService")
public class CountryService {

	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	public CountryService(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}
	
	public ArrayList<Country> getAllCountryList(){
		return countryRepository.findAllByOrderByCountryNameAsc();
	}
	
}
