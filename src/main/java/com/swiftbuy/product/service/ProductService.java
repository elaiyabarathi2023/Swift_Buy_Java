package com.swiftbuy.product.service;

import com.swiftbuy.admin.model.*;
import com.swiftbuy.product.repository.ProductRepository;
import com.swiftbuy.repository.CategoryRepository;
import com.swiftbuy.subcrepository.SubCategoryRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private SubCategoryRepository subCategoryRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;
   
    public ProductDetails createProduct(ProductDetails product) {
    	Long subCategoryId = product.getSubcategory().getId();
        SubCategory subCategory = subCategoryRepository.findById(subCategoryId)
                .orElseThrow(() -> new RuntimeException("Sub Category not found with id " + subCategoryId ));
        
        Long categoryId = product.getCategory().getCategory_id();
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with id " + categoryId));

        
        product.setSubcategory(subCategory);
        product.setCategory(category);
        return productRepository.save(product);
    }
   
    public Iterable<ProductDetails> getAllProducts() {
        return productRepository.findAll();
    }

//    public List<ProductDetails> getAllActiveProducts() {
//        return productRepository.findByProductStatus(ProductStatus.ACTIVE);
//    }
    
    public ProductDetails getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + productId));
    }
    public ProductDetails saveProduct(ProductDetails product) {
        return productRepository.save(product);
    }
    
    

    public ProductDetails getProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + productId));
    }

    public ProductDetails updateProduct(Long productId, ProductDetails product) {
        ProductDetails existingProduct = getProduct(productId);
        existingProduct.setProductName(product.getProductName());
        existingProduct.setProductDescription(product.getProductDescription());
        existingProduct.setProductImage(product.getProductImage());
        existingProduct.setProductPrice(product.getProductPrice());
        existingProduct.setProductQuantity(product.getProductQuantity());
        existingProduct.setProductOffers(product.getProductOffers());
        existingProduct.setEstimatedDelivery(product.getEstimatedDelivery());
        
        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Long productId) {
        ProductDetails product = getProduct(productId);
        productRepository.delete(product);
    }

    public ProductDetails updateProductStatus(Long productId, ProductDetails product) {
        ProductDetails existingProduct = getProduct(productId);
        existingProduct.setProductStatus(product.getProductStatus());
        return productRepository.save(existingProduct);
    }

    // Product Description methods (no separate entity, part of ProductDetails)
    public ProductDetails createProductDescription(ProductDetails productDetails) {
        return productRepository.save(productDetails);
    }

    public ProductDetails getProductDescription(Long productId) {
        return getProduct(productId);
    }

    public ProductDetails updateProductDescription(Long productId, ProductDetails productDetails) {
        ProductDetails existingProduct = getProduct(productId);
        existingProduct.setProductDescription(productDetails.getProductDescription());
        return productRepository.save(existingProduct);
    }

    public void deleteProductDescription(Long productId) {
        ProductDetails product = getProduct(productId);
        productRepository.delete(product);
    }

    // Product Image methods (no separate entity, part of ProductDetails)
    public ProductDetails createProductImage(ProductDetails productDetails) {
        return productRepository.save(productDetails);
    }

    public ProductDetails getProductImage(Long productId) {
        return getProduct(productId);
    }

    public ProductDetails updateProductImage(Long productId, ProductDetails productDetails) {
        ProductDetails existingProduct = getProduct(productId);
        existingProduct.setProductImage(productDetails.getProductImage());
        return productRepository.save(existingProduct);
    }

    public void deleteProductImage(Long productId) {
        ProductDetails product = getProduct(productId);
        productRepository.delete(product);
    }

    // Product Quantity methods (no separate entity, part of ProductDetails)
    public ProductDetails createProductQuantity(ProductDetails productDetails) {
        return productRepository.save(productDetails);
    }

    public ProductDetails getProductQuantity(Long productId) {
        return getProduct(productId);
    }

    public ProductDetails updateProductQuantity(Long productId, ProductDetails productDetails) {
        ProductDetails existingProduct = getProduct(productId);
        existingProduct.setProductQuantity(productDetails.getProductQuantity());
        return productRepository.save(existingProduct);
    }

    public void deleteProductQuantity(Long productId) {
        ProductDetails product = getProduct(productId);
        productRepository.delete(product);
    }

    // Estimated Delivery methods (no separate entity, part of ProductDetails)
    public ProductDetails createEstimatedDelivery(ProductDetails productDetails) {
        return productRepository.save(productDetails);
    }

    public ProductDetails getEstimatedDelivery(Long productId) {
        return getProduct(productId);
    }

    public ProductDetails updateEstimatedDelivery(Long productId, ProductDetails productDetails) {
        ProductDetails existingProduct = getProduct(productId);
        existingProduct.setEstimatedDelivery(productDetails.getEstimatedDelivery());
        return productRepository.save(existingProduct);
    }

    public void deleteEstimatedDelivery(Long productId) {
        ProductDetails product = getProduct(productId);
        productRepository.delete(product);
    }

    // Product Offer methods (no separate entity, part of ProductDetails)
    public ProductDetails createProductOffer(ProductDetails productDetails) {
        return productRepository.save(productDetails);
    }

    public ProductDetails getProductOffer(Long productId) {
        return getProduct(productId);
    }

    public ProductDetails updateProductOffer(Long productId, ProductDetails productDetails) {
        ProductDetails existingProduct = getProduct(productId);
        existingProduct.setProductOffers(productDetails.getProductOffers());
        return productRepository.save(existingProduct);
    }

    public void deleteProductOffer(Long productId) {
        ProductDetails product = getProduct(productId);
        productRepository.delete(product);
    }

   
}