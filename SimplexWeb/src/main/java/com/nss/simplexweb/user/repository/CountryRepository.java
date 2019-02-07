package com.nss.simplexweb.user.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nss.simplexweb.user.model.Country;

@Repository("countryRepository")
public interface CountryRepository extends JpaRepository<Country, Long>{
	ArrayList<Country> findAllByOrderByCountryNameAsc();
}
