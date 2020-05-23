package com.sqtec.takecontrol.resource.impl;

import java.util.Collection;
import java.util.Optional;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sqtec.takecontrol.domain.Spending;
import com.sqtec.takecontrol.exception.ApplicationException;
import com.sqtec.takecontrol.exception.TCNotFoundException;
import com.sqtec.takecontrol.resource.Resource;
import com.sqtec.takecontrol.service.IService;

@RestController
@RequestMapping("/rest/tc/spending")
@CrossOrigin(origins="*")
public class SpendingResourceImpl implements Resource<Spending> {
private static Logger log = LoggerFactory.getLogger(SpendingResourceImpl.class);
	
	@Autowired
	private IService<Spending> service;

	@Override
	public ResponseEntity<Collection<Spending>> findAll() {
		log.info("SpendingResourceImpl - findAll");
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Spending> findById(Long id) {
		log.info("SpendingResourceImpl - findById");
		Optional<Spending> spending = service.findById(id);
		if(!spending.isPresent()) {
			throw new TCNotFoundException("Spending not found");
		}
		return new ResponseEntity<>(spending.get(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Spending> save(Spending spending) {
		log.info("SpendingResourceImpl - save");
		if(spending.getId() != 0) {
			throw new ApplicationException("Spending ID found, ID is not required for save the data");
		}
		return new ResponseEntity<>(service.saveOrUpdate(spending), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Spending> update(Spending spending) {
		log.info("SpendingResourceImpl - update");
		if(spending.getId() == 0) {
			throw new ApplicationException("Spending ID not found, ID is required for update the data");
		}
		return new ResponseEntity<>(service.saveOrUpdate(spending), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> deleteById(Long id) {
		log.info("SpendingResourceImpl - deleteById");
		Optional<Spending> spending = service.findById(id);
		if(!spending.isPresent()) {
			throw new TCNotFoundException("Spending not found");
		}
		return new ResponseEntity<>(service.deleteById(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> invalid() {
		log.info("SpendingResourceImpl - invalid");
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("message", "something is missing, please check everything before sending the request!!!");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
	}
}
