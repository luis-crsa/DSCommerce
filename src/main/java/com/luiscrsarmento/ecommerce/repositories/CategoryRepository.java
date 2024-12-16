package com.luiscrsarmento.ecommerce.repositories;

import com.luiscrsarmento.ecommerce.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long>{
}
