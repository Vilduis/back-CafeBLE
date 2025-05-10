package backend.project.services;

import backend.project.dtos.DTOProduct;
import backend.project.entities.Product;

import java.util.List;

public interface ProductService {
    public Product insertProduct(DTOProduct dtoProduct);
    public Product updateProduct(DTOProduct dtoProduct);
    public Product findById(Long id);
    public void deleteProduct(Long id);
    public List<Product> listAllProduct();

    //public List<Product> findTop6ByPrice();
    public List<Product> findTop6ByPrice();

    // Listar productos por categoria
    public List<Product> findByCategoryId(Long categoryId);

    //Listar por intensidad
    public List<Product> findByIntensity(String intensity);
}
