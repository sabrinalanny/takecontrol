package com.sqtec.takecontrol.service.impl;

import java.util.Collection;
import java.util.Optional;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sqtec.takecontrol.domain.Income;
import com.sqtec.takecontrol.repository.IncomeRepository;
import com.sqtec.takecontrol.service.IService;

@Service
public class IncomeServiceImpl implements IService<Income> {

	@Autowired
	private IncomeRepository repository;

	@Override
	public Collection<Income> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Income> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public Income saveOrUpdate(Income income) {
		return repository.saveAndFlush(income);
	}

	@Override
	public String deleteById(Long id) {
		JSONObject jsonObject = new JSONObject();
		try {
			repository.deleteById(id);
			jsonObject.put("message", "Income deleted successfully");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

}
