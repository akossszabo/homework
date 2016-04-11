package com.mycompany.ee.service;

import com.mycompany.ee.dto.MobileDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;

@Stateful
@StatefulTimeout(value = 200, unit = TimeUnit.SECONDS)
public class CartService {
    
    private final List<MobileDTO> products = new ArrayList<>();
    
    
    public List<MobileDTO> getProducts(){
        return products;
    }
    
    public List<MobileDTO> addToCart(MobileDTO mobile){
        products.add(mobile);
        return products;
    }
    
    @Remove
    public void checkout(){
        //products.clear();
    }
    
    
}
