package cl.store.ecomerce.config;

import cl.store.ecomerce.model.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class WebConfig {

    @Bean
    public PagedResourcesAssembler<Product> pagedResourcesAssembler() {
        return new PagedResourcesAssembler<>(null, null);
    }
}