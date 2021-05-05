package com.MRSISA2021_T15.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.MRSISA2021_T15.model.Category;
import com.MRSISA2021_T15.model.CategoryName;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
	
	Category findByCategoryName(CategoryName categoryName);

}
