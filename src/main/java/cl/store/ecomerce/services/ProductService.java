package cl.store.ecomerce.services;

import cl.store.ecomerce.config.exceptions.CustomException;
import cl.store.ecomerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ProductService {

    Page<Product> getProducts(Specification<Product> spec, Pageable pageable) throws CustomException;

    List<String> getColumnsProduct();

}
