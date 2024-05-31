package cl.store.ecomerce.services.impl;

import cl.store.ecomerce.config.exceptions.CustomException;
import cl.store.ecomerce.model.Product;
import cl.store.ecomerce.repository.ProductRepository;
import cl.store.ecomerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<Product> getProducts(Specification<Product> spec, Pageable pageable) throws CustomException {
        return productRepository.findAll(spec, pageable);
    }
}