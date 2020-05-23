package com.sqtec.takecontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sqtec.takecontrol.domain.Spending;

@Repository
public interface SpendingRepository  extends JpaRepository<Spending, Long> {

}
