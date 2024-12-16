package com.luiscrsarmento.ecommerce.services;

import com.luiscrsarmento.ecommerce.dto.CategoryDTO;
import com.luiscrsarmento.ecommerce.entities.Category;
import com.luiscrsarmento.ecommerce.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll(){
        List<Category> result = categoryRepository.findAll();
        return result.stream().map(x -> new CategoryDTO(x)).toList();
    }
}
