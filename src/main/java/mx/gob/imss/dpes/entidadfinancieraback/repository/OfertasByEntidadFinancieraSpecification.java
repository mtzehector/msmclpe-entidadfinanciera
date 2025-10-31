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
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcCondicionOfertaOldie;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcCondicionOfertaOldie_;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcCondicionOferta_;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcEntidadFinanciera_;

/**
 *
 * @author Diego Velazquez
 */
public class OfertasByEntidadFinancieraSpecification extends BaseSpecification<MclcCondicionOfertaOldie> {

    private final Long idEntidadFinanciera;
    public OfertasByEntidadFinancieraSpecification(Long idEntidadFinanciera) {
        this.idEntidadFinanciera = idEntidadFinanciera;
    }

    @Override
    public Predicate toPredicate(Root<MclcCondicionOfertaOldie> root, CriteriaQuery<?> cq,
            CriteriaBuilder cb) {
      
      return cb.equal(root.join(MclcCondicionOfertaOldie_.mclcEntidadFinanciera).get(
             MclcEntidadFinanciera_.id ), this.idEntidadFinanciera );
      
    }

}
