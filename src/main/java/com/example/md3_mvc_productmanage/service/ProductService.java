package com.example.md3_mvc_productmanage.service;

import com.example.md3_mvc_productmanage.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductService implements ProductServiceImpl {
    private static final Map<Integer, Product> prodductMap;

    static {
        prodductMap = new HashMap<>();
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(prodductMap.values());
    }

    @Override
    public void save(Product product) {
        prodductMap.put(product.getId(), product);
    }

    @Override
    public Product findByID(int id) {
        return prodductMap.get(id);
    }

    @Override
    public void update(int id, Product product) {
        prodductMap.put(id, product);
    }

    @Override
    public void remove(int id) {
        prodductMap.remove(id);
    }
}
