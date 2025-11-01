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
 * @author edgar.arenas
 */
public class EntidadFinancieraByAdmin extends BaseSpecification<EntidadFinanciera>{
    
    private final String curpAdmin;
    private final String correoAdminEF;
    
    public EntidadFinancieraByAdmin(String correoAdminEF, String curpAdmin) {
        this.curpAdmin = curpAdmin;
        this.correoAdminEF = correoAdminEF;
    }
    
    @Override
    public Predicate toPredicate(Root<EntidadFinanciera> root, CriteriaQuery<?> cq,
            CriteriaBuilder cb) {
      
      return cb.and(cb.equal(root.get(EntidadFinanciera_.correoAdminEF), this.correoAdminEF),cb.equal(root.get(EntidadFinanciera_.curpAdmin), this.curpAdmin));
      
    }
}
