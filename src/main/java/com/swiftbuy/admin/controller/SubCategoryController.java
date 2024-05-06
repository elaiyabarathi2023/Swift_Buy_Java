package com.swiftbuy.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.swiftbuy.admin.model.SubCategory;
import com.swiftbuy.admin.service.SubCategoryService;

@RestController
@RequestMapping("/admin/dashboard/subcategories")
public class SubCategoryController {

    @Autowired
    private SubCategoryService subCategoryService;

    // Create SubCategory
    @PostMapping("/add")
    public ResponseEntity<SubCategory> createSubCategory(@RequestBody SubCategory subCategory) {
        SubCategory createdSubCategory = subCategoryService.createSubCategory(subCategory);
        return new ResponseEntity<>(createdSubCategory, HttpStatus.CREATED);
    }

    // Read SubCategory
    @GetMapping("/{subCategoryId}")
    public ResponseEntity<SubCategory> getSubCategoryById(@PathVariable Long subCategoryId) {
        SubCategory subCategory = subCategoryService.getSubCategoryById(subCategoryId);
        if (subCategory != null) {
            return new ResponseEntity<>(subCategory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update SubCategory
    @PutMapping("/{subCategoryId}")
    public ResponseEntity<SubCategory> updateSubCategory(@PathVariable Long subCategoryId, @RequestBody SubCategory subCategory) {
        SubCategory updatedSubCategory = subCategoryService.updateSubCategory(subCategoryId, subCategory);
        if (updatedSubCategory != null) {
            return new ResponseEntity<>(updatedSubCategory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete SubCategory
    @DeleteMapping("/{subCategoryId}")
    public ResponseEntity<Void> deleteSubCategory(@PathVariable Long subCategoryId) {
        boolean deleted = subCategoryService.deleteSubCategory(subCategoryId);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Read All SubCategories
    @GetMapping
    public ResponseEntity<Iterable<SubCategory>> getAllSubCategories() {
        Iterable<SubCategory> subCategories = subCategoryService.getAllSubCategories();
        return new ResponseEntity<>(subCategories, HttpStatus.OK);
    }
}
