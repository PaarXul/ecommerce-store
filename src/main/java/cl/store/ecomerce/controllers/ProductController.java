package cl.store.ecomerce.controllers;

import cl.store.ecomerce.config.exceptions.CustomException;
import cl.store.ecomerce.config.utils.PageableUtil;
import cl.store.ecomerce.model.Product;
import cl.store.ecomerce.services.ProductService;
import cl.store.ecomerce.specifications.ProductSpecification;
import cl.store.ecomerce.specifications.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@CrossOrigin("*")
public class ProductController {

    private final ProductService productService;
    private final PagedResourcesAssembler<Product> assembler;

    @Autowired
    public ProductController(ProductService productService, PagedResourcesAssembler<Product> assembler) {
        this.productService = productService;
        this.assembler = assembler;
    }

    @GetMapping("/")
    public ResponseEntity<PagedModel<EntityModel<Product>>> obtenerTablas (
            @RequestParam Map<String, String> searchParams,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "5") Integer size,
            @RequestParam(value = "order", required = false) String order,
            @RequestParam(value = "column", required = false) String column
            ) throws CustomException {

        ProductSpecification spec = new ProductSpecification();
        for (Map.Entry<String, String> entry : searchParams.entrySet()) {
            if (!entry.getKey().equals("page") && !entry.getKey().equals("size") && !entry.getKey().equals("column") && !entry.getKey().equals("order")) {
                spec.add(new SearchCriteria(entry.getKey(), ":", entry.getValue()));
            }
        }

        Pageable pageable = PageableUtil.createPageable(page, size, order, column);
        Page<Product> productPage = productService.getProducts(spec, pageable);
        PagedModel<EntityModel<Product>> collModel = assembler.toModel(productPage);
        return ResponseEntity.ok(collModel);
    }
}
