package com.swiftbuy.user.config;

import java.util.Map;

import com.swiftbuy.user.model.UserDetails;

public interface TokenGenerator {
Map<String,String>generateToken(UserDetails user);

}
