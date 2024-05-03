package com.swiftbuy.admin.controller;

import com.swiftbuy.admin.model.AdminDetails;
import com.swiftbuy.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Login admin
    @PostMapping("/login")
    public ResponseEntity<String> loginAdmin(@RequestParam String username, @RequestParam String password) {
        // Call service to authenticate admin
        boolean isAuthenticated = adminService.authenticateAdmin(username, password);
        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    // View admin profile
    @GetMapping("/{userId}")
    public ResponseEntity<AdminDetails> viewAdminProfile(@PathVariable Long userId) {
        AdminDetails admin = adminService.getAdminDetails(userId);
        if (admin != null) {
            return ResponseEntity.ok(admin);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update admin profile
    @PutMapping("/{userId}")
    public ResponseEntity<AdminDetails> updateAdminProfile(@PathVariable Long userId, @RequestBody AdminDetails updatedAdminDetails) {
        AdminDetails admin = adminService.updateAdminProfile(userId, updatedAdminDetails);
        if (admin != null) {
            return ResponseEntity.ok(admin);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
