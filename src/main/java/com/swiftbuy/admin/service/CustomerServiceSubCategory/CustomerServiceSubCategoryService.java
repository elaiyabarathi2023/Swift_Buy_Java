package com.swiftbuy.admin.service.CustomerServiceSubCategory;


 
//
// 
// 
//import com.swiftbuy.admin.model.CustomerServiceCategory.CustomerServiceCategory;
//
//import com.swiftbuy.admin.model.CustomerServiceSubCategory.CustomerServiceSubCategory;
//import com.swiftbuy.admin.repository.CustomerServiceCategory.CustomerServiceCategoryRepo;
//import com.swiftbuy.admin.repository.CustomerServiceSubCategory.CustomerServiceSubCategoryRepo;
// 
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.stereotype.Service;
// 
//import java.util.List;
// 
//@Service
//
//public class CustomerServiceSubCategoryService {
// 
//    @Autowired
//    private CustomerServiceSubCategoryRepo customerServiceSubCategoryRepository;
//    
//    
// 
//    public List<CustomerServiceSubCategory> getAllCustomerServiceSubCategories() {
//
//        return (List<CustomerServiceSubCategory>) customerServiceSubCategoryRepository.findAll();
//
//    }
// 
//    public CustomerServiceSubCategory getCustomerServiceSubCategoryById(Long id) {
//
//        return customerServiceSubCategoryRepository.findById(id)
//
//                .orElseThrow();
//
//    }
// 
//    public CustomerServiceSubCategory createCustomerServiceSubCategory(CustomerServiceSubCategory customerServiceSubCategory) throws RuntimeException {
//
//    	
//    	Long subCatId = customerServiceSubCategory.getCategory().getId();
//    	
//    	CustomerServiceCategory cat = CustomerServiceCategoryRepo.findById(subCatId).orElseThrow(() -> new RuntimeException("Category not found with id " + subCatId));
//    	customerServiceSubCategory.setCategory(cat);
//        return customerServiceSubCategoryRepository.save(customerServiceSubCategory);
//
//    }
// 
//    public CustomerServiceSubCategory updateCustomerServiceSubCategory(Long id, CustomerServiceSubCategory customerServiceSubCategory) {
//
//        CustomerServiceSubCategory existingCustomerServiceSubCategory = getCustomerServiceSubCategoryById(id);
//
//        existingCustomerServiceSubCategory.setName(customerServiceSubCategory.getName());
//
//        existingCustomerServiceSubCategory.setDescription(customerServiceSubCategory.getDescription());
//
//        existingCustomerServiceSubCategory.setCategory(customerServiceSubCategory.getCategory());
//
//        return customerServiceSubCategoryRepository.save(existingCustomerServiceSubCategory);
//
//    }
// 
//    public void deleteCustomerServiceSubCategory(Long id) {
//
//        CustomerServiceSubCategory customerServiceSubCategory = getCustomerServiceSubCategoryById(id);
//
//        customerServiceSubCategoryRepository.delete(customerServiceSubCategory);
//
//    }
//
//}






import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swiftbuy.admin.model.CustomerServiceCategory.CustomerServiceCategory;
import com.swiftbuy.admin.model.CustomerServiceSubCategory.CustomerServiceSubCategory;
import com.swiftbuy.admin.repository.CustomerServiceCategory.CustomerServiceCategoryRepo;
import com.swiftbuy.admin.repository.CustomerServiceSubCategory.CustomerServiceSubCategoryRepo;

import java.util.List;

@Service
public class CustomerServiceSubCategoryService {

    @Autowired
    private CustomerServiceSubCategoryRepo customerServiceSubCategoryRepository;

    @Autowired
    private CustomerServiceCategoryRepo customerServiceCategoryRepository;

    public List<CustomerServiceSubCategory> getAllCustomerServiceSubCategories() {
        return (List<CustomerServiceSubCategory>) customerServiceSubCategoryRepository.findAll();
    }

    public CustomerServiceSubCategory getCustomerServiceSubCategoryById(Long id) {
        return customerServiceSubCategoryRepository.findById(id)
                .orElseThrow();
    }

    public CustomerServiceSubCategory createCustomerServiceSubCategory(CustomerServiceSubCategory customerServiceSubCategory) {
        Long categoryId = customerServiceSubCategory.getCategory().getId();
        CustomerServiceCategory category = customerServiceCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with id " + categoryId));

        customerServiceSubCategory.setCategory(category);
        return customerServiceSubCategoryRepository.save(customerServiceSubCategory);
    }

    public CustomerServiceSubCategory updateCustomerServiceSubCategory(Long id, CustomerServiceSubCategory customerServiceSubCategory) {
        CustomerServiceSubCategory existingCustomerServiceSubCategory = getCustomerServiceSubCategoryById(id);

        existingCustomerServiceSubCategory.setName(customerServiceSubCategory.getName());
        existingCustomerServiceSubCategory.setDescription(customerServiceSubCategory.getDescription());

        Long categoryId = customerServiceSubCategory.getCategory().getId();
        CustomerServiceCategory category = customerServiceCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with id " + categoryId));

        existingCustomerServiceSubCategory.setCategory(category);

        return customerServiceSubCategoryRepository.save(existingCustomerServiceSubCategory);
    }

    public void deleteCustomerServiceSubCategory(Long id) {
        CustomerServiceSubCategory customerServiceSubCategory = getCustomerServiceSubCategoryById(id);
        customerServiceSubCategoryRepository.delete(customerServiceSubCategory);
    }
}