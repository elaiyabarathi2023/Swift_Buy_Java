package com.swiftbuy.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.swiftbuy.user.model.ShoppingCart;
import com.swiftbuy.user.model.UserDetails;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {

	Iterable<ShoppingCart> findByUserUserId(Long userId);

	

}
