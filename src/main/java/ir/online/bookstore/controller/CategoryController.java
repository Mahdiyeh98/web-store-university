package ir.online.bookstore.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.online.bookstore.domain.Categories;
import ir.online.bookstore.dto.CategoryDTO;
import ir.online.bookstore.service.CategoriesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoriesService categoriesService;
    private final ObjectMapper objectMapper;
    private static final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public CategoryController(CategoriesService categoriesService, ObjectMapper objectMapper) {
        this.categoriesService = categoriesService;
        this.objectMapper = objectMapper;
    }

    //------------------- read -----------------------
    @GetMapping("/all")
    public ResponseEntity<String> getAllCategories() {
        String authorListJson = null;
        try {
            authorListJson = objectMapper.writeValueAsString(categoriesService.getCategoriesList());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(authorListJson);
    }

    //------------------- create -----------------------
    @PostMapping("/create")
    public ResponseEntity<String> createCategory(@RequestBody final List<CategoryDTO> categoryDTOS) throws JsonProcessingException {
        ArrayList categories = new ArrayList();
        for (CategoryDTO categoryDTO : categoryDTOS
        ) {
            Categories map = modelMapper.map(categoryDTO, Categories.class);
            categories.add(map);
        }

        String toJson = objectMapper.writeValueAsString(categoriesService.addCategory(categories));
        return ResponseEntity.ok(toJson);
    }

    //------------------- update -----------------------
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCategoryById(@PathVariable final Long id,@RequestBody final CategoryDTO categoryDTO) {
        Categories map = modelMapper.map(categoryDTO, Categories.class);
        categoriesService.updateCategories(id, map);
        String categoriesForUpdateJson = null;
        try {
            categoriesForUpdateJson = objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(categoriesForUpdateJson);
    }

    //------------------- delete -----------------------
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable final Long id) {
        String categoriesForDeleteJson = categoriesService.deleteById(id);
        try {
            categoriesForDeleteJson = objectMapper.writeValueAsString(categoriesForDeleteJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(categoriesForDeleteJson);
    }
}
