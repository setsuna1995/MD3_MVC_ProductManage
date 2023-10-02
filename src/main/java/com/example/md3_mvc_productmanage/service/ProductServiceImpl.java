package com.example.md3_mvc_productmanage.service;

import com.example.md3_mvc_productmanage.model.Product;

import java.util.List;

public interface ProductServiceImpl {
List<Product> findAll();
void save (Product product);
Product findByID (int id);
void update (int id, Product product);
void remove (int id);
}
