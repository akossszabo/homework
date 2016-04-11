package com.mycompany.ee.service;

import com.mycompany.ee.dto.UserDTO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class UserManagementService {
    
    private final Map<String,UserDTO> users = new HashMap<>();
    
    @PostConstruct
    private void init() {
        users.put("admin",new UserDTO("admin","Asd12+",LocalDate.now()));
        users.put("user",new UserDTO("user","Asd12+",LocalDate.now()));
    }
    
    @Lock(LockType.WRITE)
    public UserDTO addUser(UserDTO user){
        users.put(user.getUsername(),user);
        return user;
    }
    
    @Lock(LockType.WRITE)
    public UserDTO removeUser(String username){
        return users.remove(username);
    }
    @Lock(LockType.READ)
    public List<UserDTO> getUsers() {
        List<UserDTO> us = new ArrayList<>(users.values());
        return us;
    }
    @Lock(LockType.WRITE)
    public UserDTO editUser(UserDTO user){
        return addUser(user);
    }
    @Lock(LockType.READ)
    public UserDTO getUser(String username){
        return users.get(username);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.users);
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
        final UserManagementService other = (UserManagementService) obj;
        if (!Objects.equals(this.users, other.users)) {
            return false;
        }
        return true;
    }
    
}
