package com.devsuperior.dscommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/{id}")
    public ProductDTO findById(@PathVariable Long id){
        ProductDTO dto = productService.findById(id);
        return dto;
    }

    @GetMapping
    public Page<ProductDTO> findAll(Pageable pageable){
        Page<ProductDTO> list = productService.findAll(pageable);
        return list;
    }

    @PostMapping
    public ProductDTO insert(@RequestBody ProductDTO dto){
        return productService.insert(dto);
    }

}
