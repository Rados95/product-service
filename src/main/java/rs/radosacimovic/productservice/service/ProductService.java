package rs.radosacimovic.productservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.radosacimovic.productservice.model.ProductEntity;
import rs.radosacimovic.productservice.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductEntity save(ProductEntity productEntity) {
        return productRepository.save(productEntity);
    }

    public List<ProductEntity> getProducts() {
        return productRepository.findAll();
    }

    public Optional<ProductEntity> getProductByCode(String code) {
        return productRepository.findByCode(code);
    }

    public List<ProductEntity> getProductByProductType(String productType) {
        return productRepository.findByProductType(productType);
    }

    public List<ProductEntity> getProductBySeller(String seller) {
        return productRepository.findBySeller(seller);
    }

    public boolean deleteProduct(String code) {
        Optional<ProductEntity> product = productRepository.findByCode(code);
        if (product.isPresent()) {
            productRepository.deleteById(product.get().getId());
            return true;
        }
        return false;
    }

    public boolean sellProduct(String code, String seller) {
        Optional<ProductEntity> product = productRepository.findByCode(code);
        if (product.isPresent()) {
            product.get().setSold(true);
            product.get().setSeller(seller);
            productRepository.save(product.get());
            return true;
        }
        return false;
    }
}
