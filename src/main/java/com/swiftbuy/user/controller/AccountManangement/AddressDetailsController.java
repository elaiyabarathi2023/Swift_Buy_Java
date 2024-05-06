


package com.swiftbuy.user.controller.AccountManangement;



import com.swiftbuy.user.model.AccountManangement.AddressDetails;
import com.swiftbuy.user.service.AccountManangement.AddressDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressDetailsController {

    @Autowired
    private AddressDetailsService addressDetailsService;

    @GetMapping
    public ResponseEntity<List<AddressDetails>> getAllAddressDetails() {
        List<AddressDetails> addressDetails = addressDetailsService.getAllAddressDetails();
        return ResponseEntity.ok(addressDetails);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDetails> getAddressDetailsById(@PathVariable Long id) {
        AddressDetails addressDetails = addressDetailsService.getAddressDetailsById(id);
        return ResponseEntity.ok(addressDetails);
    }

    @PostMapping
    public ResponseEntity<AddressDetails> createAddressDetails(@RequestBody AddressDetails addressDetails) {
        AddressDetails createdAddressDetails = addressDetailsService.createAddressDetails(addressDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAddressDetails);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDetails> updateAddressDetails(@PathVariable Long id, @RequestBody AddressDetails addressDetails) {
        AddressDetails updatedAddressDetails = addressDetailsService.updateAddressDetails(id, addressDetails);
        return ResponseEntity.ok(updatedAddressDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddressDetails(@PathVariable Long id) {
        addressDetailsService.deleteAddressDetails(id);
        return ResponseEntity.noContent().build();
    }
}