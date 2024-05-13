package com.swiftbuy.user.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swiftbuy.user.model.ShoppingCart;
import com.swiftbuy.user.repository.ShoppingCartRepository;
import com.swiftbuy.user.service.ShoppingCartService;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@Configuration
@SecurityScheme(   name = "Bearer Authentication",   type = SecuritySchemeType.HTTP,   bearerFormat = "JWT",   scheme = "bearer" )
@RequestMapping("/api/shoppingcart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> addItemsToCart(@RequestBody List<ShoppingCart> shoppingCarts, HttpServletRequest request) {
    	 Long userId = (Long) request.getAttribute("userId");
        List<ShoppingCart> savedShoppingCarts = shoppingCartService.saveShoppingCarts(shoppingCarts,userId);
        
        // Calculate the total price
        Double totalPrice = shoppingCartService.getTotalPrice(savedShoppingCarts);
        
        // Subtract the quantities from the product table
        shoppingCartService.updateProductQuantities(savedShoppingCarts);
        
        // Reset the total price to 0
        shoppingCartService.resetTotalPrice();
        
        // Construct the response
        Map<String, Object> response = new HashMap<>();
        response.put("shoppingCarts", savedShoppingCarts);
        response.put("totalPrice", totalPrice);
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getAllShoppingCartItems(@PathVariable Long userId) {
        Iterable<ShoppingCart> shoppingCartItems = shoppingCartService.getAllShoppingCartItemsByUserId(userId);

        if (shoppingCartItems != null && shoppingCartItems.iterator().hasNext()) {
            List<ShoppingCart> shoppingCarts = new ArrayList<>();
            shoppingCartItems.forEach(shoppingCarts::add);

            // Your existing logic for saving the shopping cart and calculating total price
            List<ShoppingCart> savedShoppingCarts = shoppingCartService.saveShoppingCarts(shoppingCarts, userId);
            Double totalPrice = shoppingCartService.getTotalPrice(savedShoppingCarts);

            // Construct the response
            Map<String, Object> response = new HashMap<>();
            response.put("shoppingCarts", savedShoppingCarts);
            response.put("totalPrice", totalPrice);

            return ResponseEntity.ok(response);
        } else {
            // Handle the case where there are no shopping cart items
            return ResponseEntity.noContent().build();
        }
    }
//    @GetMapping
//    public ResponseEntity<Iterable<ShoppingCart>> getAllShoppingCartItems() {
//        Iterable<ShoppingCart> shoppingCartItems = shoppingCartService.getAllShoppingCartItems();
//        return ResponseEntity.ok(shoppingCartItems);
//    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<Void> deleteShoppingCartItem(@PathVariable Long cartItemId) {
        shoppingCartService.deleteShoppingCartItem(cartItemId);
        return ResponseEntity.noContent().build();
    }

//    @PutMapping("/{cartItemId}")
//    public ResponseEntity<ShoppingCart> updateShoppingCartItem(@PathVariable Long cartItemId, @RequestBody ShoppingCart shoppingCart) {
//        ShoppingCart updatedShoppingCart = shoppingCartService.updateShoppingCartItem(cartItemId, shoppingCart);
//        if (updatedShoppingCart != null) {
//            return ResponseEntity.ok(updatedShoppingCart);
//        } else {
//            return ResponseEntity.badRequest().build();
//        }
//    }
}