package com.swiftbuy.admin.service;

import com.swiftbuy.admin.model.AdminDetails;

public interface AdminService {

    boolean authenticateAdmin(String username, String password);

    AdminDetails getAdminDetails(Long userId);

    AdminDetails updateAdminProfile(Long userId, AdminDetails updatedAdminDetails);
}
