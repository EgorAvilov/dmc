package com.example.dmc.repository;

import com.example.dmc.entity.DataSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataSetRepository extends JpaRepository<DataSet, Long> {
}
