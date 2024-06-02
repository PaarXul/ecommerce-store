package cl.store.ecomerce.repository;


import cl.store.ecomerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAll(Specification<Product> spec, Pageable pageable);

    @Query(value = "SELECT column_name FROM information_schema.columns WHERE table_name = 'product'", nativeQuery = true)
    List<String> getColumns();

 }
