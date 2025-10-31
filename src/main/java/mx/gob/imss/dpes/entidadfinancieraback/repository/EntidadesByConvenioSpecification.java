/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.entidadfinancieraback.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import mx.gob.imss.dpes.baseback.persistence.BaseSpecification;
import mx.gob.imss.dpes.entidadfinancieraback.entity.EntidadFinanciera;
import mx.gob.imss.dpes.entidadfinancieraback.entity.EntidadFinanciera_;

/**
 *
 * @author helio.sampe
 */
public class EntidadesByConvenioSpecification extends BaseSpecification<EntidadFinanciera>{
    
    private final Long sinConvenio; 
    
    public EntidadesByConvenioSpecification(Long sinConvenio){
        this.sinConvenio = sinConvenio;
    }

    @Override
    public Predicate toPredicate(Root<EntidadFinanciera> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        return cb.equal(root.get(EntidadFinanciera_.sinConvenio),this.sinConvenio);
    }
}
