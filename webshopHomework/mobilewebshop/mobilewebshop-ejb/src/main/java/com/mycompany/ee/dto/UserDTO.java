package com.mycompany.ee.dto;

import java.time.LocalDate;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class UserDTO {
    
    @NotNull 
    @Size(min=3)
    private String username;
    @NotNull
    @Size(min = 6)
    @Pattern.List({
    @Pattern(regexp =".*[a-z].*"),
    @Pattern(regexp =".*[A-Z].*"),
    @Pattern(regexp =".*[0-9].*"),
    @Pattern(regexp =".*[=+<>.,].*")
    })
    private String password;
    private String firstname;
    private String lastname;
    
    private LocalDate dateOfBirth;
    private LocalDate registrationDate;
    private boolean admin;
    private List<MobileDTO> cart;

    public UserDTO(){
    }
    
    public UserDTO(String username,String password, LocalDate registrationDate, boolean admin){
        this.username = username;
        this.password=password;
        this.registrationDate = registrationDate;
        this.admin=admin;
    }
    
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public List<MobileDTO> getCart() {
        return cart;
    }

    public void setCart(List<MobileDTO> cart) {
        this.cart = cart;
    }
    
    
}
