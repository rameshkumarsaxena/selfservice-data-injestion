package com.techedusoln.selfserve.datainjestion.repository;

import java.util.List;

import com.techedusoln.selfserve.datainjestion.model.DbConnection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ConnectionRepository extends JpaRepository<DbConnection, Integer> {
  List<DbConnection> findByName(String name);

}
