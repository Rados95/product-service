package rs.radosacimovic.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.radosacimovic.productservice.model.ProductEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findByCode(String code);
    List<ProductEntity> findByProductType(String productType);
    List<ProductEntity> findBySeller(String seller);
}
