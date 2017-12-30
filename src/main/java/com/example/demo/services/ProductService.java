package com.example.demo.services;

import com.example.demo.entities.Product;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.responses.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends BaseService<ProductRepository, Product, Integer> {
    /**
     * Controller
     *
     * @param repository repository
     */
    @Autowired
    ProductService(ProductRepository repository) {
        super(repository);
    }

    public Page<ProductResponse> listAll(Integer page, Integer size) {
        PageRequest pageRequest = new PageRequest(page, size);
        //List all
        Page<Product> products = findAll(pageRequest);
        return products.map(ProductResponse::new);
    }

    //CRUD method is provided by Base Service. Add another method as needed
}
