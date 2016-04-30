package com.mycompany.facade;

import com.mycompany.entity.VisitorBook;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class VisitorBookFacade extends EntityFacade{
    
    public VisitorBook addVisitorBook(VisitorBook visitorBook){
        create(visitorBook);
        return visitorBook;
    }
    
    public List<VisitorBook> getVisitorBooks(){
        TypedQuery<VisitorBook> q = entityManager.createNamedQuery("VisitorBook.getAllVisitorBook", VisitorBook.class);
        return q.getResultList();
    }
    
    public VisitorBook getVisitorBook(long id){
        VisitorBook visitorBook = read(VisitorBook.class,id);
        if(null == visitorBook){
            throw new IllegalArgumentException("VisitorBook does not exist with this id");
        }
        return visitorBook;
    }
    
    public VisitorBook updateVisitorBook(VisitorBook visitorBook){
        update(visitorBook);
        return visitorBook;
    }
    
    public void deleteVisitorBook(VisitorBook visitorBook){
        delete(visitorBook);
    }
    
    public List<VisitorBook> getByVisitorIdAndParkId(long visitorId, long parkId) {
        Query q = entityManager.createQuery("SELECT vb FROM VisitorBook vb WHERE vb.amPark.id = :vid AND vb.amPark.id = :apid",VisitorBook.class);
        q.setParameter("vid",visitorId);
        q.setParameter("apid", parkId);
        return q.getResultList();
    }
}
