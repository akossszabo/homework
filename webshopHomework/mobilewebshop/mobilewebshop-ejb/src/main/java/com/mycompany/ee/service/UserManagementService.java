package com.mycompany.ee.service;

import com.mycompany.ee.dto.UserDTO;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
@LocalBean

public class UserManagementService {
    
    private final Map <String, UserDTO> users = new HashMap<>();
    
    @PostConstruct
    private void init() {
        users.put("adminUser",new UserDTO("adminUser","Asd12+",LocalDate.now(),true));
        users.put("userUser",new UserDTO("userUser","Asd12+",LocalDate.now(),false));
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

    public Map<String, UserDTO> getUsers() {
        return users;
    }
    @Lock(LockType.WRITE)
    public UserDTO editUser(UserDTO user){
        return addUser(user);
    }
    @Lock(LockType.READ)
    public UserDTO getUser(String username){
        return users.get(username);
    }
    
}
