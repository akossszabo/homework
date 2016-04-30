package com.mycompany.ejb;

import com.mycompany.entity.AmusementPark;
import com.mycompany.entity.Visitor;
import com.mycompany.entity.VisitorBook;
import com.mycompany.exception.BadRequestException;
import com.mycompany.facade.AmusementParkFacade;
import com.mycompany.facade.VisitorBookFacade;
import com.mycompany.facade.VisitorFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class VisitorBookService {
    
    
    @Inject
    private VisitorBookFacade visitorBookFacade;
    
    @Inject
    private VisitorFacade visitorFacade;
    
    @Inject
    AmusementParkFacade amusementParkFacade;
    
    public VisitorBook addVisitorBookEntry(long parkId, long visitorId, VisitorBook vb){
        AmusementPark amPark = amusementParkFacade.getPark(parkId);
        Visitor visitor = visitorFacade.getVisitor(visitorId);
        if(visitor.getAmusementPark().getId() != amPark.getId()){
            throw new BadRequestException("The visitor is not in this park, he can't write in to this visitor book");
        }
        vb.setAmPark(amPark);
        vb.setVisitor(visitor);
        visitorBookFacade.addVisitorBook(vb);
        amusementParkFacade.updatePark(amPark);
        return vb;
    }
    
    public VisitorBook getEntry(long id ){
        return visitorBookFacade.getVisitorBook(id);
    }
    
    public VisitorBook updateVisitorBook(long id, VisitorBook visitorBook){
        visitorBook.setId(id);
        visitorBookFacade.update(visitorBook);
        return visitorBook;
    }
    
    public List<VisitorBook> getByParkAndVisitor(long visitorId, long parkId){
        return visitorBookFacade.getByVisitorIdAndParkId(visitorId, parkId);
    }
    
    public VisitorBook deleteVisitorBook(long id){
        VisitorBook v = visitorBookFacade.getVisitorBook(id);
        visitorBookFacade.deleteVisitorBook(v);
        return v;
    }
    
    public List<VisitorBook> getVisitorBooks(){
        return visitorBookFacade.getVisitorBooks();
    }
}
