package com.nss.simplexweb.po.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nss.simplexweb.po.model.POItems;

@Repository("poItemsRepository")
public interface PoItemsRepository extends JpaRepository<POItems, Long> {

	//Create
	/*ArrayList<POItems> savePoItmes(ArrayList<POItems> poItmesList);*/
	
	//Read
	ArrayList<POItems> findAll();
}
