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

import com.sqtec.takecontrol.domain.Category;
import com.sqtec.takecontrol.exception.ApplicationException;
import com.sqtec.takecontrol.exception.TCNotFoundException;
import com.sqtec.takecontrol.resource.CategoryResource;
import com.sqtec.takecontrol.service.ICategoryService;

@RestController
@RequestMapping("/rest/tc/category")
@CrossOrigin(origins="*")
public class CategoryResourceImpl implements CategoryResource<Category> {
private static Logger log = LoggerFactory.getLogger(CategoryResourceImpl.class);
	
	@Autowired
	private ICategoryService<Category> service;

	@Override
	public ResponseEntity<Collection<Category>> findAll() {
		log.info("CategoryResourceImpl - findAll");
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Category> findById(Long id) {
		log.info("CategoryResourceImpl - findById");
		Optional<Category> category = service.findById(id);
		if(!category.isPresent()) {
			throw new TCNotFoundException("Category not found");
		}
		return new ResponseEntity<>(category.get(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Category> save(Category category) {
		log.info("CategoryResourceImpl - save");
		if(category.getId() != 0) {
			throw new ApplicationException("Category ID found, ID is not required for save the data");
		}
		return new ResponseEntity<>(service.saveOrUpdate(category), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Category> update(Category category) {
		log.info("CategoryResourceImpl - update");
		if(category.getId() == 0) {
			throw new ApplicationException("Category ID not found, ID is required for update the data");
		}
		return new ResponseEntity<>(service.saveOrUpdate(category), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> deleteById(Long id) {
		log.info("CategoryResourceImpl - deleteById");
		Optional<Category> category = service.findById(id);
		if(!category.isPresent()) {
			throw new TCNotFoundException("Category not found");
		}
		return new ResponseEntity<>(service.deleteById(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> invalid() {
		log.info("CategoryResourceImpl - invalid");
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("message", "something is missing, please check everything before sending the request!!!");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Collection<Category>> findAByType(String id) {
		log.info("CategoryResourceImpl - findByType");
		return new ResponseEntity<>(service.findByType(id), HttpStatus.OK);
	}
}
