package com.sqtec.takecontrol.resource;

import java.util.Collection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface CategoryResource<T> extends Resource<T> {
	@GetMapping("/findAByType/{id}")
	ResponseEntity<Collection<T>> findAByType(@PathVariable String id);
	
	
}
