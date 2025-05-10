package backend.project.controllers;

import backend.project.dtos.DTOProduct;
import backend.project.entities.Product;
import backend.project.services.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProduct(){
        return ResponseEntity.ok(productService.listAllProduct());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id){
        Product product = productService.findById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/products/register")
    public ResponseEntity<Product> register(@RequestBody DTOProduct dtoProduct){
        Product product = productService.insertProduct(dtoProduct);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }
    @Transactional
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody DTOProduct dtoProduct) {
        Product product = productService.updateProduct(dtoProduct);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/products/top6")
    public ResponseEntity<List<Product>> getTop6ByPrice(){
        return ResponseEntity.ok(productService.findTop6ByPrice());
    }

    @GetMapping("/products/category/{id}")
    public ResponseEntity<List<Product>> getByCategoryId(@PathVariable("id") Long id){
        return ResponseEntity.ok(productService.findByCategoryId(id));
    }

    //por intensidad
    @GetMapping("/products/intensity/{intensity}")
    public ResponseEntity<List<Product>> getByIntensity(@PathVariable("intensity") String intensity){
        return ResponseEntity.ok(productService.findByIntensity(intensity));
    }
}
