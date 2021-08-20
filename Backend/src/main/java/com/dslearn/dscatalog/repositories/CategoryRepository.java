package com.dslearn.dscatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dslearn.dscatalog.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>  {

}
