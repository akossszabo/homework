package com.mycompany.facade;

import com.mycompany.entity.AmusementPark;
import java.util.List;
import javax.ejb.Singleton;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Singleton
public class AmusementParkFacade extends EntityFacade {

    public AmusementPark addPark(AmusementPark amPark) {
        create(amPark);
        return amPark;
    }

    public void deletePark(AmusementPark amPark) {
        delete(amPark);
    }

    public List<AmusementPark> getParks() {
        TypedQuery<AmusementPark> q = entityManager.createNamedQuery("AmusementPark.getAllParks", AmusementPark.class);
        return q.getResultList();
    }

    public AmusementPark getPark(long id) {
        AmusementPark amPark = read(AmusementPark.class, id);
        if (null == amPark) {
            throw new IllegalArgumentException("AmusementPark does not exist with this id.");
        }
        return amPark;
    }

    public AmusementPark updatePark(AmusementPark amPark) {
        update(amPark);
        return amPark;
    }
    
}
