package ir.online.bookstore.service;

import ir.online.bookstore.dao.CategoryDAO;
import ir.online.bookstore.domain.Categories;
import ir.online.bookstore.exception.CategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriesService {


    private final CategoryDAO categoryDAO;

    @Autowired
    public CategoriesService(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    //------------------- create -----------------------
    public List<Categories> addCategory(List<Categories> category) {
        for (Categories categories : category) {
            categoryDAO.save(categories);
        }
        return categoryDAO.findAll();
    }

    //------------------- read -----------------------
    public List<Categories> getCategoriesList() {
        return categoryDAO.findAll();
    }

    public Optional<Categories> getCategoriesById(Long id) {
        return Optional.ofNullable(categoryDAO.findById(id).orElse(null));
    }

    public List<Categories> getCategoriesList(String name){
        return categoryDAO.findByName(name);
    }

    //------------------- delete -----------------------
    public String deleteById(Long id) {
        if (categoryDAO.findById(id).get().getName() == null ||
                categoryDAO.findById(id).get().getName().trim().isEmpty()) {
            return "id not exist";
        }
        categoryDAO.deleteById(id);
        return "category is deleted";
    }

    //------------------- update -----------------------
    public Optional<Categories> updateCategories(Long id, Categories category) {
        Categories categoryToEdit = categoryDAO.findById(id).get();

        if (categoryToEdit.getName() != null) {
            categoryToEdit.setName(category.getName());
        }

        return Optional.of(categoryDAO.save(categoryToEdit));
    }
}
