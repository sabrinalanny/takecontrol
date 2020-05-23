package com.sqtec.takecontrol.service;

import java.util.Collection;

public interface ICategoryService<T> extends IService<T> {
	Collection<T> findByType(String id);
}
