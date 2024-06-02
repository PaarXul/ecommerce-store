package cl.store.ecomerce.specifications;

import cl.store.ecomerce.model.Product;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.*;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecification implements Specification<Product> {

    private final List<SearchCriteria> list;

    public ProductSpecification() {
        this.list = new ArrayList<>();
    }

    public void add(SearchCriteria criteria) {
        list.add(criteria);
    }

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        //create a new predicate list
        List<Predicate> predicates = new ArrayList<>();

        //add add criteria to predicates
        for (SearchCriteria criteria : list) {
            if (criteria.getOperation().equalsIgnoreCase(":")) {
                if ("category".equals(criteria.getKey())) {
                    // If the key is "category", compare the "id" field of the category with the provided value
                    predicates.add(builder.equal(root.get("category").get("id"), criteria.getValue()));
                } else if ("price".equals(criteria.getKey())) {
                    if (root.get(criteria.getKey()).getJavaType() == float.class || root.get(criteria.getKey()).getJavaType() == Float.class) {
                        // Instead of looking for an exact match, look for prices within a range
                        if (!criteria.getValue().toString().isEmpty()) {
                            float price = Float.parseFloat(criteria.getValue().toString());
                            predicates.add(builder.between(root.get(criteria.getKey()), price - 0.01f, price + 0.01f));
                        }
                    }
                } else if (root.get(criteria.getKey()).getJavaType() == float.class || root.get(criteria.getKey()).getJavaType() == Float.class) {
                    if (!criteria.getValue().toString().isEmpty()) {
                        predicates.add(builder.equal(root.<Float>get(criteria.getKey()), Float.parseFloat(criteria.getValue().toString())));
                    }
                } else if (root.get(criteria.getKey()).getJavaType() == String.class) {
                    predicates.add(builder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%"));
                } else {
                    predicates.add(builder.equal(root.get(criteria.getKey()), criteria.getValue()));
                }
            }
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}