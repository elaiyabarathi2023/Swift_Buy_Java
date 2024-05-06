package com.swiftbuy.user.model.AccountManangement;



import com.swiftbuy.CustomValidator.UserCustomValidator.AccountMangement.ValidAddressType;
import com.swiftbuy.user.model.UserDetails;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "addresses")
public class AddressDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    
    @NotNull(message = "Address type cannot be null")
//    @ValidAddressType
    private String addressType;
    
    
    @NotNull(message = "Permanent address cannot be null")
    private String permanentAddress;
    
   
    @NotNull(message = "Current address cannot be null")
    private String currentAddress;
    


    @NotNull(message = "Street address cannot be null")
    private String streetAddress;

 
    @NotNull(message = "City cannot be null")
    private String city;

    
    @NotNull(message = "State cannot be null")
    private String state;

    
    @NotNull(message = "Zip code cannot be null")
    private String zipCode;

   
    @NotNull(message = "Country cannot be null")
    private String country;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private UserDetails user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }
    
   
    
    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }
    
    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public UserDetails getUser() {
        return user;
    }

    public void setUser(UserDetails user) {
        this.user = user;
    }
}
