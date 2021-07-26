package com.dslearn.dscatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dslearn.dscatalog.models.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
