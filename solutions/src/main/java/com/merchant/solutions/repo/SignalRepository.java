package com.merchant.solutions.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.merchant.solutions.dto.Signal;

@Repository
public interface SignalRepository extends JpaRepository<Signal, Long>{

}
