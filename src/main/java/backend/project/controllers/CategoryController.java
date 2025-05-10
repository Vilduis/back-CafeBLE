package backend.project.controllers;

import backend.project.dtos.DTOCategory;
import backend.project.entities.Category;
import backend.project.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories()
    {
        return ResponseEntity.ok(categoryService.listAllCategory());
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable("id") Long id)
    {
        Category category = categoryService.findById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping("/categories/register")
    public ResponseEntity<Category> register(@RequestBody DTOCategory dtoCategory)
    {
        Category category = categoryService.insertCategory(dtoCategory);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") Long id, @RequestBody DTOCategory dtoCategory)
    {
        Category category = categoryService.updateCategory(dtoCategory);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") Long id)
    {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
