package backend.project.serviceimpl;

import backend.project.dtos.DTOCategory;
import backend.project.entities.Category;
import backend.project.entities.Client;
import backend.project.exceptions.ResourceNotFoundException;
import backend.project.repositories.CategoryRepository;
import backend.project.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category insertCategory(DTOCategory dtoCategory)
    {
        Category category = new Category();
        category.setName(dtoCategory.getName());
        category.setDescription(dtoCategory.getDescription());
        return categoryRepository.save(category);
    }
    @Override
    public Category updateCategory(DTOCategory dtoCategory)
    {
        Category categoryFound = categoryRepository.findById(dtoCategory.getId()).orElse(null);
        if (categoryFound == null) {
            throw new ResourceNotFoundException("Category with id:" + dtoCategory.getId() + "can not be found");
        }
        categoryFound.setName(dtoCategory.getName());
        categoryFound.setDescription(dtoCategory.getDescription());
        return categoryRepository.save(categoryFound);
    }
    @Override
    public Category findById(Long id)
    {
        Category categoryFound = categoryRepository.findById(id).orElse(null);
        if (categoryFound == null) {
            throw new ResourceNotFoundException("Category with id:" + id + "can not be found");
        }
        return categoryFound;
    }
    @Override
    public void deleteCategory(Long id)
    {
        Category categoryFound = categoryRepository.findById(id).orElse(null);
        if (categoryFound == null) {
            throw new ResourceNotFoundException("Category with id:" + id + "can not be found");
        }
        categoryRepository.delete(categoryFound);
    }
    @Override
    public List<Category> listAllCategory()
    {
        return categoryRepository.findAll();
    }
}
