package rs.radosacimovic.productservice.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.radosacimovic.productservice.dto.ProductDTO;
import rs.radosacimovic.productservice.dto.ProductRegistrationDTO;
import rs.radosacimovic.productservice.model.ProductEntity;
import rs.radosacimovic.productservice.service.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper;

    @PostMapping("/products")
    private ResponseEntity<ProductDTO> save(@RequestBody ProductRegistrationDTO productRegistrationDTO) {
        ProductEntity productEntity = productService.save(this.convertToEntity(productRegistrationDTO));
        return new ResponseEntity<>(this.convertToDTO(productEntity), HttpStatus.CREATED);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getProducts(@RequestParam(required = false) String productType,
                                                        @RequestParam(required = false) String seller) {
        List<ProductEntity> productEntities;
        if (productType != null) {

            productEntities = productService.getProductByProductType(productType);
        } else if (seller != null) {
            productEntities = productService.getProductBySeller(seller);
        } else {
            productEntities = productService.getProducts();
        }
        List<ProductDTO> productDTOs =
                productEntities.stream().map(this::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(productDTOs);
    }

    @GetMapping("/products/{code}")
    public ResponseEntity<ProductDTO> getProductByCode(@PathVariable String code) {
        Optional<ProductEntity> productEntity = productService.getProductByCode(code);
        return productEntity.map(entity -> ResponseEntity.ok(this.convertToDTO(entity))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/products/{code}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String code) {
        boolean deletionSuccessful = productService.deleteProduct(code);
        if (deletionSuccessful) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/products/{code}")
    private ResponseEntity<Void> sellProduct(@PathVariable String code, @RequestParam String seller) {
        boolean sellingSuccessful = productService.sellProduct(code, seller);
        if (sellingSuccessful) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private ProductDTO convertToDTO(ProductEntity productEntity) {
        return modelMapper.map(productEntity, ProductDTO.class);
    }

    private ProductEntity convertToEntity(ProductRegistrationDTO productRegistrationDTO) {
        return modelMapper.map(productRegistrationDTO, ProductEntity.class);
    }


}
