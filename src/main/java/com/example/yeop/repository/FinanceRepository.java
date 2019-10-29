package com.example.yeop.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.yeop.entity.Finance;
import com.example.yeop.vo.TotalByYear;


@Repository
public interface FinanceRepository extends JpaRepository<Finance, Long>, FinanceRepositoryCustom {
}

