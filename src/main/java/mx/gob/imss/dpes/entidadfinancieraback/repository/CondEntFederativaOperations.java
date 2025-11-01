/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.entidadfinancieraback.repository;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mx.gob.imss.dpes.interfaces.entidadfinanciera.model.Delegacion;

import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;

/**
 *
 * @author gabriel.rios
 */
@Component
public class CondEntFederativaOperations {

    protected final transient Logger log = Logger.getLogger(getClass().getName());
    @PersistenceContext
    private EntityManager entityManager;

    public CondEntFederativaOperations() {
    }

    public LinkedList<Delegacion> findDelegacionesLista(Long cveEntidadFinanciera) {
        //log.log(Level.INFO, "	++++++++++   UserOperations.findUserData entityManager.isOpen()={}", entityManager.isOpen());
        List<Delegacion> delegaciones =  entityManager.createNativeQuery(
                " select DISTINCT MCLC_DELEGACION.CVE_DELEGACION, MCLC_DELEGACION.DES_DELEGACION " +
                " from MCLC_CONDICION_ENTFED  " +
                " INNER JOIN MCLC_DELEGACION ON MCLC_CONDICION_ENTFED.CVE_DELEGACION=MCLC_DELEGACION.CVE_DELEGACION " +
                " where MCLC_CONDICION_ENTFED.CVE_ENTIDAD_FINANCIERA = ?1 order by MCLC_DELEGACION.CVE_DELEGACION"
               )
                .setParameter(1, cveEntidadFinanciera)
                .unwrap(org.hibernate.Query.class).setResultTransformer(Transformers.aliasToBean(Delegacion.class)).getResultList();
        LinkedList<Delegacion> delegacionesLinked = new LinkedList();
        delegacionesLinked.addAll(delegaciones);
        return delegacionesLinked;
    }
    
    
    
    

    

}
