package cl.store.ecomerce.config.utils;

import cl.store.ecomerce.config.exceptions.CustomException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableUtil {

    private PageableUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static Pageable createPageable(Integer page, Integer size, String order, String column) throws CustomException {
        if (page == null || size == null) {
            throw new CustomException("El número de página y el tamaño de la página no pueden estar vacíos");
        }
        if (order.equals("asc")) {
            return PageRequest.of(page, size, Sort.by(column.toLowerCase()).ascending());
        } else if (order.equals("desc")) {
            return PageRequest.of(page, size, Sort.by(column.toLowerCase()).descending());
        } else {
            throw new CustomException("La peticion no viene con un orden valido (asc o desc)");
        }
    }

}
