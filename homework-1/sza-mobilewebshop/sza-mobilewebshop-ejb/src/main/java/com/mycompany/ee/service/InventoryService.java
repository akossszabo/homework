package com.mycompany.ee.service;

import com.mycompany.ee.dto.MobileDTO;
import com.mycompany.ee.exception.BadRequestException;
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
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class InventoryService {

    private final List<MobileDTO> mobiles = new ArrayList<>();
    
    @PostConstruct
    public void init(){
        mobiles.add(new MobileDTO(UUID.randomUUID().toString(),"iphone 6s","apple",100,3));
        mobiles.add(new MobileDTO(UUID.randomUUID().toString(),"galaxy s6","samsung",120,12));
        mobiles.add(new MobileDTO(UUID.randomUUID().toString(),"ascend p7","huawei",200,5));
        mobiles.add(new MobileDTO(UUID.randomUUID().toString(),"nexus","google",100,0));
    }
     
    @Lock(LockType.WRITE)
    public MobileDTO addMobile(MobileDTO mobile){
        for(MobileDTO m: mobiles){
            if(m.equals(mobile)){
                throw new BadRequestException("Already exist phone with this ID");
            }
        }
        mobiles.add(mobile);
        return mobile;
    }
    
    @Lock(LockType.WRITE)
    public MobileDTO buyMobile(MobileDTO mobile){
        for(MobileDTO m: mobiles){
            if(m.getType().equals(mobile.getType())){
                if(m.getPiece()>0){
                    m.setPiece(m.getPiece()-1);
                    return m;
                }else{
                    throw new BadRequestException("There's no mobile in the inventory like this");
                }
            }
        }
        return mobile;
    }
    @Lock(LockType.READ)
    public List<MobileDTO> getMobiles() {
        return mobiles;
    } 
}
