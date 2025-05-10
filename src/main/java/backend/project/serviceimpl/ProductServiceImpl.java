package backend.project.serviceimpl;

import backend.project.dtos.DTOProduct;
import backend.project.entities.Category;
import backend.project.entities.Product;
import backend.project.entities.User;
import backend.project.exceptions.ResourceNotFoundException;
import backend.project.repositories.ProductRepository;
import backend.project.services.CategoryService;
import backend.project.services.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryService categoryService;

    @Override
    @Transactional
    public Product insertProduct(DTOProduct dtoProduct) {
        Product product = new Product();
        validateAndAssignFields(dtoProduct, product);
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product updateProduct(DTOProduct dtoProduct) {
        Product product = productRepository.findById(dtoProduct.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Product with id: " + dtoProduct.getId() + " not found"));
        validateAndAssignFields(dtoProduct, product);
        return productRepository.save(product);
    }

    private void validateAndAssignFields(DTOProduct dtoProduct, Product product) {
        // Validar y asignar categoría
        Category category = dtoProduct.getCategoryId() != null
                ? categoryService.findById(dtoProduct.getCategoryId())
                : null;

        // Validar precio
        if (dtoProduct.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }

        // Validar imagen
        if (dtoProduct.getImage() != null && !dtoProduct.getImage().isEmpty()) {
            if (isValidUrl(dtoProduct.getImage()) || isBase64(dtoProduct.getImage())) {
                product.setImage(dtoProduct.getImage());
            } else {
                throw new IllegalArgumentException("Invalid image format. Must be a valid URL or Base64 encoded image.");
            }
        } else {
            product.setImage("https://via.placeholder.com/150"); // Imagen predeterminada
        }

        // Asignar valores a los atributos del producto
        product.setName(dtoProduct.getName());
        product.setDescription(dtoProduct.getDescription());
        product.setCategory(category);
        product.setPrice(dtoProduct.getPrice());
        product.setStock(dtoProduct.getStock());
        product.setOrigin(dtoProduct.getOrigin());
        product.setIntensity(dtoProduct.getIntensity());
        product.setWeightgram(dtoProduct.getWeightgram());
    }

    private boolean isValidUrl(String url) {
        try {
            new java.net.URL(url).toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isBase64(String str) {
        try {
            java.util.Base64.getDecoder().decode(str);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public Product findById(Long id)
    {
        Product productFound = productRepository.findById(id).orElse(null);
        if (productFound == null) {
            throw new ResourceNotFoundException("Product with id:" + id + "can not be found");
        }
        return productFound;
    }
    @Override
    public void deleteProduct(Long id)
    {
        Product productFound = productRepository.findById(id).orElse(null);
        if (productFound == null) {
            throw new ResourceNotFoundException("Product with id:" + id + "can not be found");
        }
        productRepository.delete(productFound);
    }
    @Override
    public List<Product> listAllProduct()
    {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findTop6ByPrice()
    {
        return productRepository.findTop6ByPrice();
    }

    @Override
    public List<Product> findByCategoryId(Long categoryId)
    {
        return productRepository.findByCategoryId(categoryId);
    }

    @Override
    public List<Product> findByIntensity(String intensity)
    {
        return productRepository.findByIntensity(intensity);
    }

}
