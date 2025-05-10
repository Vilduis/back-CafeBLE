package backend.project.services;

import backend.project.dtos.DTOCategory;
import backend.project.entities.Category;

import java.util.List;

public interface CategoryService {
    public Category insertCategory(DTOCategory dtoCategory);
    public Category updateCategory(DTOCategory dtoCategory);
    public Category findById(Long id);
    public void deleteCategory(Long id);
    public List<Category> listAllCategory();
}
