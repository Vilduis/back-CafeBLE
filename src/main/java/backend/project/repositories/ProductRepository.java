package backend.project.repositories;

import backend.project.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    //listar productos mejor valorados
    @Query(value = "SELECT * FROM products ORDER BY price DESC LIMIT 6", nativeQuery = true)
    List<Product> findTop6ByPrice();

    //Listar productos por categoria
    @Query(value = "SELECT * FROM products WHERE category_id = ?1", nativeQuery = true)
    List<Product> findByCategoryId(Long categoryId);

    //Listar productos por intensidad(intensity)
    @Query(value = "SELECT * FROM products WHERE intensity = ?1", nativeQuery = true)
    List<Product> findByIntensity(String intensity);
}
