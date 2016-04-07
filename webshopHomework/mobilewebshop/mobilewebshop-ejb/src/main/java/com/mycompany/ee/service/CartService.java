package com.mycompany.ee.service;

import com.mycompany.ee.dto.MobileDTO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Remove;
import javax.ejb.Stateful;

@Stateful
@LocalBean
public class CartService {
    
    private final List<MobileDTO> products = new ArrayList<>();
    
    
    public void addToCart(MobileDTO mobile){
        products.add(mobile);
    }
    
    @Remove
    public void checkout(){
        products.clear();
    }
    
}
