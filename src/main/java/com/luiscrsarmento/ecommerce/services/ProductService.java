package com.luiscrsarmento.ecommerce.services;

import com.luiscrsarmento.ecommerce.dto.CategoryDTO;
import com.luiscrsarmento.ecommerce.dto.ProductDTO;
import com.luiscrsarmento.ecommerce.dto.ProductMinDTO;
import com.luiscrsarmento.ecommerce.entities.Category;
import com.luiscrsarmento.ecommerce.entities.Product;
import com.luiscrsarmento.ecommerce.repositories.ProductRepository;
import com.luiscrsarmento.ecommerce.services.exceptions.DatabaseException;
import com.luiscrsarmento.ecommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){
        Optional<Product> result = productRepository.findById(id);
        Product product = result.orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        ProductDTO dto = new ProductDTO(product);
        return dto;
    }

    @Transactional(readOnly = true)
    public Page<ProductMinDTO> findAll(String name, Pageable pageable){
        Page<Product> result = productRepository.searchByName(name, pageable);
        Page<ProductMinDTO> list = result.map(x -> new ProductMinDTO(x));
        return list;
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto){
        Product entity = new Product();
        copyDtoToEntity(dto, entity);
        entity = productRepository.save(entity);
        return new ProductDTO(entity);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto){
        try {
            Product entity = productRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = productRepository.save(entity);
            return new ProductDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Resource not found");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Resource not found");
        }
        try {
            productRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Referential integrity failure");
        }
    }

    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());

        entity.getCategories().clear();
        for(CategoryDTO catDTO : dto.getCategories()){
            Category cat = new Category();
            cat.setId(catDTO.getId());
            entity.getCategories().add(cat);
        }
    }
}
