package com.mycompany.facade;

import com.mycompany.entity.Visitor;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class VisitorFacade extends EntityFacade{
    
    public Visitor addVisitor(Visitor visitor){
        create(visitor);
        return visitor;
    }
    
    public List<Visitor> getVisitors(){
        TypedQuery<Visitor> q = entityManager.createNamedQuery("Visitor.getAllVisitor", Visitor.class);
        return q.getResultList();
    }
    
    public Visitor getVisitor(long id){
        Visitor visitor = read(Visitor.class,id);
        if(null == visitor){
            throw new IllegalArgumentException("Visitor does not exist with this id");
        }
        return visitor;
    }
    
    public Visitor updateVisitor(Visitor visitor){
        update(visitor);
        return visitor;
    }
    
    public void deleteVisitor(Visitor visitor){
        delete(visitor);
    }
    
    public List<Visitor> getVisitorsofMachine(long machineId){
        Query q = entityManager.createQuery("SELECT v FROM Visitor v WHERE v.machine.id = :id",Visitor.class);
        q.setParameter("id", machineId);
        return q.getResultList();
    }
    
}
