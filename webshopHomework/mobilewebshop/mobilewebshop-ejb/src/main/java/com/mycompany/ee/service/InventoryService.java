package com.mycompany.ee.service;

import com.mycompany.ee.dto.MobileDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
@LocalBean
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class InventoryService {

    
    private final List<MobileDTO> mobiles = new ArrayList<>();
    
    @PostConstruct
    public void init(){
        mobiles.add(new MobileDTO(UUID.randomUUID().toString(),"p8","huawei",100,3));
        mobiles.add(new MobileDTO(UUID.randomUUID().toString(),"s7","samsung",120,12));
        mobiles.add(new MobileDTO(UUID.randomUUID().toString(),"6s","iphone",200,5));
        mobiles.add(new MobileDTO(UUID.randomUUID().toString(),"g3","lg",100,0));
    }
    
     
    @Lock(LockType.WRITE)
    public void addMobile(MobileDTO mobile){
        for(MobileDTO m: mobiles){
            if(m.equals(mobile)){
                return;
            }
        }
        mobiles.add(mobile);
    }
    
    public void buyMobile(MobileDTO mobile){
        for(MobileDTO m: mobiles){
            if(m.getId().equals(mobile.getId())){
                if(m.getPiece()>0){
                    m.setPiece(m.getPiece()-1);
                }else{
                    throw new IllegalArgumentException("There's no mobile in the inventory like this");
                }              
            }
            throw new IllegalArgumentException("There's no mobile in the inventory like this");
        }    
    }
    
    
    
    public List<MobileDTO> getMobiles() {
        return mobiles;
    }
    
    
}
