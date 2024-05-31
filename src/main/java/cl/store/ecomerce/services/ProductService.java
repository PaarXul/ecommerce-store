package cl.store.ecomerce.services;

import cl.store.ecomerce.config.exceptions.CustomException;
import cl.store.ecomerce.model.Product;
import org.springframework.data.domain.Page;

public interface ProductService {

    Page<Product> getProducts(String find, String order, String column, Integer page, Integer size) throws CustomException;

}
