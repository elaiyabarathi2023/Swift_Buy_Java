package com.swiftbuy.admin.model;

import jakarta.persistence.*;

@Entity
@Table(name = "offer")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String offerName;
    private String offerDescription;
    private Double discountPercentage;
    private boolean isActive;

  
	@ManyToOne
    @JoinColumn(name = "categoryid", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "subcat_id", nullable = false)
    private SubCategory subcategory;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductDetails product;
    // Constructors, getters, and setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOfferName() {
		return offerName;
	}

	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}

	public String getOfferDescription() {
		return offerDescription;
	}

	public void setOfferDescription(String offerDescription) {
		this.offerDescription = offerDescription;
	}

	public Double getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(Double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public SubCategory getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(SubCategory subcategory) {
		this.subcategory = subcategory;
	}

	public ProductDetails getProduct() {
		return product;
	}

	public void setProduct(ProductDetails product) {
		this.product = product;
	}

   
}