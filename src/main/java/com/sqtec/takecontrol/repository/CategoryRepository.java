package com.sqtec.takecontrol.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sqtec.takecontrol.domain.Category;

@Repository
public interface CategoryRepository  extends JpaRepository<Category, Long> {
	@Query(value = " FROM Category WHERE type=?1 and status=1")
	Collection<Category> findByType(String id);

}
