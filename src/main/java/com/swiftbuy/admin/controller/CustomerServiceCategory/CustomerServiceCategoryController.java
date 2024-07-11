package com.swiftbuy.admin.controller.CustomerServiceCategory;



//
//import com.swiftbuy.admin.model.CustomerServiceCategory.CustomerServiceCategory;
//import com.swiftbuy.admin.service.CustomerServiceCategory.CustomerServiceCategoryService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/categories")
//public class CustomerServiceCategoryController {
//
//    @Autowired
//    private CustomerServiceCategoryService customerServiceCategoryService;
//
//    @GetMapping
//    public ResponseEntity<List<CustomerServiceCategory>> getAllCategories() {
//        List<CustomerServiceCategory> categories = customerServiceCategoryService.getAllCustomerServiceCategories();
//        return ResponseEntity.ok(categories);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<CustomerServiceCategory> getCategoryById(@PathVariable Long id) {
//        CustomerServiceCategory category = customerServiceCategoryService.getCustomerServiceCategoryById(id);
//        return category != null ? ResponseEntity.ok(category) : ResponseEntity.notFound().build();
//    }
//
//    @PostMapping
//    public ResponseEntity<CustomerServiceCategory> createCategory(@RequestBody CustomerServiceCategory category) {
//        CustomerServiceCategory createdCategory = customerServiceCategoryService.createCustomerServiceCategory(category);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<CustomerServiceCategory> updateCategory(@PathVariable Long id, @RequestBody CustomerServiceCategory updatedCategory) {
//        CustomerServiceCategory updatedCategoryEntity = customerServiceCategoryService.updateCustomerServiceCategory(id, updatedCategory);
//        return updatedCategoryEntity != null ? ResponseEntity.ok(updatedCategoryEntity) : ResponseEntity.notFound().build();
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
//        customerServiceCategoryService.deleteCustomerServiceCategory(id);
//        return ResponseEntity.noContent().build();
//    }
//}



import com.swiftbuy.admin.model.CustomerServiceCategory.CustomerServiceCategory;
import com.swiftbuy.admin.service.CustomerServiceCategory.CustomerServiceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categories")
public class CustomerServiceCategoryController {

    @Autowired
    private CustomerServiceCategoryService customerServiceCategoryService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllCategories() {
        List<CustomerServiceCategory> categories = customerServiceCategoryService.getAllCustomerServiceCategories();
        Map<String, Object> response = new HashMap<>();
        response.put("categories", categories);
        response.put("message", "Categories fetched successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getCategoryById(@PathVariable Long id) {
        CustomerServiceCategory category = customerServiceCategoryService.getCustomerServiceCategoryById(id);
        Map<String, Object> response = new HashMap<>();
        if (category != null) {
            response.put("category", category);
            response.put("message", "Category fetched successfully");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Category not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createCategory(@RequestBody CustomerServiceCategory category) {
        CustomerServiceCategory createdCategory = customerServiceCategoryService.createCustomerServiceCategory(category);
        Map<String, Object> response = new HashMap<>();
        response.put("category", createdCategory);
        response.put("message", "Category created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateCategory(@PathVariable Long id, @RequestBody CustomerServiceCategory updatedCategory) {
        CustomerServiceCategory updatedCategoryEntity = customerServiceCategoryService.updateCustomerServiceCategory(id, updatedCategory);
        Map<String, Object> response = new HashMap<>();
        if (updatedCategoryEntity != null) {
            response.put("category", updatedCategoryEntity);
            response.put("message", "Category updated successfully");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Category not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteCategory(@PathVariable Long id) {
        boolean deleted = customerServiceCategoryService.deleteCustomerServiceCategory(id);
        Map<String, Object> response = new HashMap<>();
        if (deleted) {
            response.put("message", "Category deleted successfully");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Category not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}