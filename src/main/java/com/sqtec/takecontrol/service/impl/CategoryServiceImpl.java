package com.sqtec.takecontrol.service.impl;

import java.util.Collection;
import java.util.Optional;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sqtec.takecontrol.domain.Category;
import com.sqtec.takecontrol.repository.CategoryRepository;
import com.sqtec.takecontrol.service.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService<Category> {

	@Autowired
	private CategoryRepository repository;

	@Override
	public Collection<Category> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Category> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public Category saveOrUpdate(Category category) {
		return repository.saveAndFlush(category);
	}

	@Override
	public String deleteById(Long id) {
		JSONObject jsonObject = new JSONObject();
		try {
			repository.deleteById(id);
			jsonObject.put("message", "Category deleted successfully");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	@Override
	public Collection<Category> findByType(String id) {
		return repository.findByType(id);
	}

}
