
package com.swiftbuy.user.service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
 
import com.swiftbuy.admin.model.CouponCodes;
import com.swiftbuy.admin.model.ProductCouponRequest;
import com.swiftbuy.admin.model.ProductDetails;
import com.swiftbuy.admin.repository.CouponCodeRepository;
import com.swiftbuy.product.repository.ProductRepository;
import com.swiftbuy.user.model.ShoppingCart;
import com.swiftbuy.user.model.UserDetails;
import com.swiftbuy.user.repository.ShoppingCartRepository;
import com.swiftbuy.user.repository.UserRepository;
 
@Service
public class ShoppingCartService {
    
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
 
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CouponCodeRepository couponRepository;
    Double totalPrice = 0.0;
    public List<ShoppingCart> saveShoppingCarts(List<ShoppingCart> shoppingCarts, Long userId) {
        UserDetails user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        for (ShoppingCart shoppingCart : shoppingCarts) {
            Long productId = shoppingCart.getProduct().getProductId();
            ProductDetails productDetails = productRepository.findById(productId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
            shoppingCart.setProduct(productDetails);
            shoppingCart.setUser(user);

            // Save the shopping cart
            shoppingCartRepository.save(shoppingCart);
        }
        return shoppingCarts;
    }
 
    public Double getTotalPrice(List<ShoppingCart> shoppingCarts) {
     

        // Create a set to store unique products
        Set<ProductDetails> uniqueProducts = new HashSet<>();

        for (ShoppingCart item : shoppingCarts) {
            ProductDetails product = item.getProduct();

            // Check if the product is already in the set
            if (uniqueProducts.contains(product)) {
                // If the product is already in the set, skip it
                continue;
            }

            // Add the product to the set
            uniqueProducts.add(product);

            Double productPrice = product.getProductPrice();
            int quantity = 0;
            Double couponDiscount = 0.0;

            // Iterate over the shopping cart items for the current product
            for (ShoppingCart cartItem : shoppingCarts) {
                if (cartItem.getProduct().equals(product)) {
                    quantity += cartItem.getQuantity();

                    // Check if a coupon is applied
                    Long selectedCouponId = cartItem.getSelectedCouponId();
                    if (selectedCouponId != null) {
                        // Check if the product has a ProductCouponRequest
                       ProductCouponRequest productCouponRequest = cartItem.getProductCouponRequest();
                        if (productCouponRequest != null) {
                            // Get the list of coupon IDs from the ProductCouponRequest
                            List<Long> couponIds = productCouponRequest.getCouponIds();

                            // Check if the selected coupon ID is present in the list
                            if (couponIds.contains(selectedCouponId)) {
                                CouponCodes selectedCoupon = fetchCouponCodes(Arrays.asList(selectedCouponId)).get(0);
                                couponDiscount = calculateCouponDiscount(selectedCoupon, productPrice);
                                // Break the loop as we only want to apply one coupon per product
                                break;
                            }
                        }
                    }
                }
            }

            Double priceAfterDiscount = productPrice - couponDiscount;
            totalPrice += (priceAfterDiscount * quantity);
        }

        return totalPrice;
    }
 
    private List<CouponCodes> fetchCouponCodes(List<Long> couponIds) {
        // Fetch coupon codes from the database using the provided coupon IDs
        List<CouponCodes> couponCodes = new ArrayList<>();
        for (Long couponId : couponIds) {
            CouponCodes couponCode = couponRepository.findById(couponId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Coupon code not found"));
            couponCodes.add(couponCode);
        }
        return couponCodes;
    }
 
    private Double calculateCouponDiscount(CouponCodes couponCode, Double productPrice) {
      
        double discountPercentage = couponCode.getDiscountPercentage();
        return productPrice * (discountPercentage / 100);
    }
 
 
//    public Iterable<ShoppingCart> getShoppingCartItemsByUserId(Long userId) {
//        return shoppingCartRepository.findByUserUserId(userId);
//    }
    public void updateProductQuantities(List<ShoppingCart> shoppingCarts) {
        for (ShoppingCart shoppingCart : shoppingCarts) {
            ProductDetails product = shoppingCart.getProduct();
            int quantityToSubtract = shoppingCart.getQuantity();
            
            // Update the product quantity in the database
            product.setProductQuantity(product.getProductQuantity()- quantityToSubtract);
            productRepository.save(product);
        }
    }

    public void resetTotalPrice() {
        totalPrice = 0.0;
    }
    
  
    public Iterable<ShoppingCart> getAllShoppingCartItemsByUserId(Long userId) {
        return shoppingCartRepository.findByUserUserId(userId);
    }
    public void deleteShoppingCartItem(Long cartItemId) {
        shoppingCartRepository.deleteById(cartItemId);
    }
}
 