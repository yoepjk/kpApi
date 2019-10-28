package com.example.yeop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.yeop.entity.Institute;


@Repository
public interface InstituteRepository extends JpaRepository<Institute, Long>{

}
