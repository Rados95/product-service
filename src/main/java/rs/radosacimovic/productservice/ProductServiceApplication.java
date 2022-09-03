package rs.radosacimovic.productservice;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import rs.radosacimovic.productservice.model.ProductEntity;
import rs.radosacimovic.productservice.service.ProductService;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@EnableEurekaClient
@SpringBootApplication
@RequiredArgsConstructor
public class ProductServiceApplication {

    private final ProductService productService;

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @PostConstruct
    public void initializeData() {
        productService.save(ProductEntity.builder()
                .code("FD1278")
                .name("chocolate")
                .productType("FOOD")
                .seller("marija")
                .price(new BigDecimal("120.12"))
                .sold(true)
                .build()
        );
        productService.save(ProductEntity.builder()
                .code("DR4398")
                .name("orange juice")
                .productType("DRINK")
                .seller("marija")
                .price(new BigDecimal("87.34"))
                .sold(true)
                .build()
        );
        productService.save(ProductEntity.builder()
                .code("FD6589")
                .name("bananas")
                .productType("FOOD")
                .seller("milos")
                .price(new BigDecimal("149.79"))
                .sold(true)
                .build()
        );
        productService.save(ProductEntity.builder()
                .code("FD9053")
                .name("beans")
                .productType("FOOD")
                .seller("natasa")
                .price(new BigDecimal("45.23"))
                .sold(true)
                .build()
        );
        productService.save(ProductEntity.builder()
                .code("FD1063")
                .name("strawberries")
                .productType("FOOD")
                .price(new BigDecimal("45.23"))
                .sold(false)
                .build()
        );
    }

}
