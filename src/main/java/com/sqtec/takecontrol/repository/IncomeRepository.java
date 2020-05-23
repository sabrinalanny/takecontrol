package com.sqtec.takecontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sqtec.takecontrol.domain.Income;

@Repository
public interface IncomeRepository  extends JpaRepository<Income, Long> {

}
