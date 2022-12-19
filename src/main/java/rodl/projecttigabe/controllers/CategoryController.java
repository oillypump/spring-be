package rodl.projecttigabe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rodl.projecttigabe.entities.exam.Category;
import rodl.projecttigabe.services.CategoryService;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/category")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;
    
    //  add Category
    @PostMapping(value = "/")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        Category category1 = this.categoryService.addCategory(category);
        return ResponseEntity.ok(category1);
    }

    // get category
    @GetMapping(value ="/{categoryId}")
    public Category getCategory(@PathVariable("categoryId") Long categoryId) {
        return this.categoryService.getCategory(categoryId);
    }

    // get all categories
    @GetMapping(value ="/")
    public ResponseEntity<?> getCategories() {
        return ResponseEntity.ok(this.categoryService.getCategories());
    }

    // udpate category
    @PutMapping(value = "/")
    public Category updateCategory(@RequestBody Category category) {
        
        return this.categoryService.updateCategory(category);
    }

    // delete category
    @DeleteMapping(value ="/{categoryId}")
    public void deleteCategory(@PathVariable("categoryId") Long categoryId) {
        this.categoryService.deleteCategory(categoryId);
    }
}

