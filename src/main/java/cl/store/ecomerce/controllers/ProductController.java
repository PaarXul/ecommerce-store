package cl.store.ecomerce.controllers;


import cl.store.ecomerce.config.exceptions.CustomException;
import cl.store.ecomerce.model.Product;
import cl.store.ecomerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@CrossOrigin("*")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Funcion para obtener todos los productos
    @GetMapping("/")
    public ResponseEntity<Page<Product>> obtenerTablas (
            @RequestParam(value = "find", required = false) String find,
            @RequestParam(value = "order", defaultValue = "asc") String order,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "column", defaultValue = "id") String column,
            @RequestParam(value = "size", defaultValue = "5") Integer size) throws CustomException {
        return ResponseEntity.ok(getTableParams(find, order, page, column, size));
    }

    private Page<Product> getTableParams(String find, String order, Integer page, String column, Integer size) throws CustomException {
        return productService.getProducts(find, order, column, page, size);
    }
}
