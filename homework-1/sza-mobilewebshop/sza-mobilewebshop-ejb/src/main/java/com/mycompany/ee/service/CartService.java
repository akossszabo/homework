package com.mycompany.ee.service;

import com.mycompany.ee.dto.MobileDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.ejb.Remove;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.inject.Inject;

@Stateful
@StatefulTimeout(value = 200, unit = TimeUnit.SECONDS)
public class CartService {
    
    private final List<MobileDTO> products = new ArrayList<>();
    
    @Inject
    private InventoryService inventoryService;
    
    public List<MobileDTO> getProducts(){
        return products;
    }
    
    public List<MobileDTO> addToCart(MobileDTO mobile){
        products.add(mobile);
        return products;
    }
    
    @Remove
    public void checkout(){
        products.stream().forEach(inventoryService::buyMobile);
        products.clear();
    }
    
    
}
