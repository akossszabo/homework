package com.mycompany.ee.service;

import com.mycompany.ee.dto.UserDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class UserManagementService {
    
    private final Map<String,UserDTO> users = new HashMap<>();
    
    @PostConstruct
    private void init() {
        users.put("admin",new UserDTO("admin","Asd12+","2000-05-20","2010-05-10"));
        users.put("user",new UserDTO("user","Asd12+","2000-05-20","2010-05-10"));
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
    
}
