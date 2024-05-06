package com.swiftbuy.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swiftbuy.admin.model.SubCategory;
import com.swiftbuy.subcrepository.SubCategoryRepository;


@Service
public class SubCategoryService {

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    public SubCategory createSubCategory(SubCategory subCategory) {
        return subCategoryRepository.save(subCategory);
    }

    public SubCategory getSubCategoryById(Long subCategoryId) {
        return subCategoryRepository.findById(subCategoryId).orElse(null);
    }

    public SubCategory updateSubCategory(Long subCategoryId, SubCategory subCategory) {
        SubCategory existingSubCategory = getSubCategoryById(subCategoryId);
        if (existingSubCategory != null) {
            subCategory.setSubcategory_id(subCategoryId);
            return subCategoryRepository.save(subCategory);
        }
        return null;
    }

    public boolean deleteSubCategory(Long subCategoryId) {
        SubCategory existingSubCategory = getSubCategoryById(subCategoryId);
        if (existingSubCategory != null) {
            subCategoryRepository.delete(existingSubCategory);
            return true;
        }
        return false;
    }

    public Iterable<SubCategory> getAllSubCategories() {
        return subCategoryRepository.findAll();
    }
}
