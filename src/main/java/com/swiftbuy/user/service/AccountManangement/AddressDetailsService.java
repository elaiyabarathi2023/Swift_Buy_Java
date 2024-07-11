// Interface
package com.swiftbuy.user.service.AccountManangement;

import com.swiftbuy.user.model.AccountManangement.AddressDetails;
import com.swiftbuy.user.repository.UserRepository;
import com.swiftbuy.user.repository.AccountManangement.AddressDetailsRepo;
import com.swiftbuy.user.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressDetailsService {

    @Autowired
    private AddressDetailsRepo addressDetailsRepository;

    @Autowired
    private UserRepository userDetailsRepository;

    public Iterable<AddressDetails> getAllAddressDetails() {
        return addressDetailsRepository.findAll();
    }

    public AddressDetails getAddressDetailsById(Long id) {
        return addressDetailsRepository.findById(id)
                .orElseThrow();
    }

    public AddressDetails createAddressDetails(AddressDetails addressDetails, Long userId) {
        // Set the user ID directly without fetching the UserDetails entity
        addressDetails.setUserId(userId);
        return addressDetailsRepository.save(addressDetails);
    }

//    public AddressDetails updateAddressDetails(Long id, AddressDetails addressDetails) {
//        AddressDetails existingAddressDetails = getAddressDetailsById(id);
//        existingAddressDetails.setAddressType(addressDetails.getAddressType());
//        existingAddressDetails.setPermanentAddress(addressDetails.getPermanentAddress());
//        existingAddressDetails.setCurrentAddress(addressDetails.getCurrentAddress());
//        existingAddressDetails.setStreetAddress(addressDetails.getStreetAddress());
//        existingAddressDetails.setCity(addressDetails.getCity());
//        existingAddressDetails.setState(addressDetails.getState());
//        existingAddressDetails.setZipCode(addressDetails.getZipCode());
//        existingAddressDetails.setCountry(addressDetails.getCountry());
//        UserDetails user = userDetailsRepository.findById(addressDetails.getUser().getUserId())
//                .orElseThrow();
//        existingAddressDetails.setUser(user);
//        return addressDetailsRepository.save(existingAddressDetails);
//    }

    public void deleteAddressDetails(Long id) {
        AddressDetails addressDetails = getAddressDetailsById(id);
        addressDetailsRepository.delete(addressDetails);
    }
}
