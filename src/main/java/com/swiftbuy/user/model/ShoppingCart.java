package com.swiftbuy.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.swiftbuy.admin.model.ProductCouponRequest;
import com.swiftbuy.admin.model.ProductDetails;
import jakarta.persistence.*;

@Entity
@Table(name = "cart")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartItemId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductDetails product;

    private int quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties(value = {"shoppingCarts"})
    private UserDetails user;

    private Long selectedCouponId;

    // Add a reference to ProductCouponRequest
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "product_coupon_request_id", nullable = true)
    private ProductCouponRequest productCouponRequest;

    // Getters and Setters

    public Long getSelectedCouponId() {
        return selectedCouponId;
    }

    public void setSelectedCouponId(Long selectedCouponId) {
        this.selectedCouponId = selectedCouponId;
    }

    public UserDetails getUser() {
        return user;
    }

    public void setUser(UserDetails user) {
        this.user = user;
    }

    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public ProductDetails getProduct() {
        return product;
    }

    public void setProduct(ProductDetails product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Getter and setter for ProductCouponRequest
    public ProductCouponRequest getProductCouponRequest() {
        return productCouponRequest;
    }

    public void setProductCouponRequest(ProductCouponRequest productCouponRequest) {
        this.productCouponRequest = productCouponRequest;
    }
}
