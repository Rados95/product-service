package rs.radosacimovic.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRegistrationDTO {
    private String code;
    private String name;
    private String productType;
    private BigDecimal price;
}
