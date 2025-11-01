/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.entidadfinancieraback.repository;

import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TransactionRequiredException;
import org.springframework.stereotype.Component;

/**
 *
 * @author ernesto.palacios
 */
@Component
public class MclcBeneficioDelRepository {
    
     protected final transient Logger log = Logger.getLogger(getClass().getName());
    @PersistenceContext
    private EntityManager entityManager;
    
    public MclcBeneficioDelRepository() {
    }

    public void delConEnt(Long id)throws TransactionRequiredException{
        try{
         entityManager.createNativeQuery("delete from MCLC_BENEFICIO where CVE_BENEFICIO= ?1").setParameter(1,id).unwrap(org.hibernate.Query.class).getResultList();
        }catch(Exception e){}
    }
}


