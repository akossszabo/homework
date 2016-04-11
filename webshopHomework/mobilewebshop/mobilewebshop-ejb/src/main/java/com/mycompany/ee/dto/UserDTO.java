package com.mycompany.ee.dto;

import com.mycompany.ee.annotation.Validate;
import com.mycompany.ee.constraint.BirthDayConstraint;
import com.mycompany.ee.dateformatter.DateFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Validate
@BirthDayConstraint
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
    @XmlJavaTypeAdapter(DateFormatter.class)    
    private LocalDate dateOfBirth;
    @XmlJavaTypeAdapter(DateFormatter.class)
    @NotNull
    private LocalDate registrationDate;
    private boolean admin;
    private List<MobileDTO> cart;

    public UserDTO(){
    }
    
    public UserDTO(String username,String password,LocalDate dateOfBirth, LocalDate registrationDate){
        this.username = username;
        this.password=password;
        this.dateOfBirth = dateOfBirth;
        this.registrationDate = registrationDate;
        this.setAdmin();
    }
    public UserDTO(String username,String password, LocalDate registrationDate){
        this.username = username;
        this.password=password;
        this.registrationDate = registrationDate;
        this.setAdmin();
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

    public void setAdmin() {
        admin = "admin".equals(this.getUsername());
    }

    public List<MobileDTO> getCart() {
        return cart;
    }

    public void setCart(List<MobileDTO> cart) {
        this.cart = cart;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.username);
        hash = 97 * hash + Objects.hashCode(this.password);
        hash = 97 * hash + Objects.hashCode(this.firstname);
        hash = 97 * hash + Objects.hashCode(this.lastname);
        hash = 97 * hash + Objects.hashCode(this.dateOfBirth);
        hash = 97 * hash + Objects.hashCode(this.registrationDate);
        hash = 97 * hash + (this.admin ? 1 : 0);
        hash = 97 * hash + Objects.hashCode(this.cart);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserDTO other = (UserDTO) obj;
        if (this.admin != other.admin) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.firstname, other.firstname)) {
            return false;
        }
        if (!Objects.equals(this.lastname, other.lastname)) {
            return false;
        }
        if (!Objects.equals(this.dateOfBirth, other.dateOfBirth)) {
            return false;
        }
        if (!Objects.equals(this.registrationDate, other.registrationDate)) {
            return false;
        }
        if (!Objects.equals(this.cart, other.cart)) {
            return false;
        }
        return true;
    }
    
    
    
}
