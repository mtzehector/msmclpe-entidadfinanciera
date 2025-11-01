/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.entidadfinancieraback.repository;

import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;
import javax.transaction.Transactional;
import mx.gob.imss.dpes.interfaces.userdata.model.UserData;
import org.hibernate.transform.Transformers;
import org.springframework.data.jpa.repository.Modifying;

import org.springframework.stereotype.Component;

/**
 *
 * @author ernesto.palacios
 */
@Component
public class MclcContEntDelRepository {
    
     protected final transient Logger log = Logger.getLogger(getClass().getName());
    @PersistenceContext
    private EntityManager entityManager;
    
    public MclcContEntDelRepository() {
    }

    public void delConEnt(Long id)throws TransactionRequiredException{
        try{
         entityManager.createNativeQuery("delete from MCLC_CONDICION_ENTFED where CVE_ENTIDAD_FINANCIERA= ?1").setParameter(1,id).unwrap(org.hibernate.Query.class).getResultList();
        }catch(Exception e){}
    }
}
