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

import com.sqtec.takecontrol.domain.Income;
import com.sqtec.takecontrol.exception.ApplicationException;
import com.sqtec.takecontrol.exception.TCNotFoundException;
import com.sqtec.takecontrol.resource.Resource;
import com.sqtec.takecontrol.service.IService;

@RestController
@RequestMapping("/rest/tc/income")
@CrossOrigin(origins="*")
public class IncomeResourceImpl implements Resource<Income> {
private static Logger log = LoggerFactory.getLogger(IncomeResourceImpl.class);
	
	@Autowired
	private IService<Income> service;

	@Override
	public ResponseEntity<Collection<Income>> findAll() {
		log.info("IncomeResourceImpl - findAll");
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Income> findById(Long id) {
		log.info("IncomeResourceImpl - findById");
		Optional<Income> income = service.findById(id);
		if(!income.isPresent()) {
			throw new TCNotFoundException("Income not found");
		}
		return new ResponseEntity<>(income.get(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Income> save(Income income) {
		log.info("IncomeResourceImpl - save");
		if(income.getId() != 0) {
			throw new ApplicationException("Income ID found, ID is not required for save the data");
		}
		return new ResponseEntity<>(service.saveOrUpdate(income), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Income> update(Income income) {
		log.info("IncomeResourceImpl - update");
		if(income.getId() == 0) {
			throw new ApplicationException("Income ID not found, ID is required for update the data");
		}
		return new ResponseEntity<>(service.saveOrUpdate(income), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> deleteById(Long id) {
		log.info("IncomeResourceImpl - deleteById");
		Optional<Income> income = service.findById(id);
		if(!income.isPresent()) {
			throw new TCNotFoundException("Income not found");
		}
		return new ResponseEntity<>(service.deleteById(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> invalid() {
		log.info("IncomeResourceImpl - invalid");
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("message", "something is missing, please check everything before sending the request!!!");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
	}
}
