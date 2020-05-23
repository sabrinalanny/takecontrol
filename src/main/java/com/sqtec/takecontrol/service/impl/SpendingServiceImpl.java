package com.sqtec.takecontrol.service.impl;

import java.util.Collection;
import java.util.Optional;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sqtec.takecontrol.domain.Spending;
import com.sqtec.takecontrol.repository.SpendingRepository;
import com.sqtec.takecontrol.service.IService;

@Service
public class SpendingServiceImpl implements IService<Spending> {

	@Autowired
	private SpendingRepository repository;

	@Override
	public Collection<Spending> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Spending> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public Spending saveOrUpdate(Spending spending) {
		return repository.saveAndFlush(spending);
	}

	@Override
	public String deleteById(Long id) {
		JSONObject jsonObject = new JSONObject();
		try {
			repository.deleteById(id);
			jsonObject.put("message", "Spending deleted successfully");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

}
