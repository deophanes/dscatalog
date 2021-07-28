package com.dslearn.dscatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dslearn.dscatalog.models.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
