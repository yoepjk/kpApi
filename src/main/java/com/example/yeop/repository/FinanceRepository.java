package com.example.yeop.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.yeop.entity.Finance;


@Repository
public interface FinanceRepository extends JpaRepository<Finance, Long>{
	@Query(value = "SELECT * FROM finance WHERE id = :id", nativeQuery = true)
	List<Finance> findAllById(@Param("id") Long id);
	

}
