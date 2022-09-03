package rs.radosacimovic.productservice.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "product_table")
public class ProductEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String code;
    private String name;
    private String productType;
    private BigDecimal price;
    private String seller;
    private boolean sold;
}
