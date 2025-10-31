/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.entidadfinancieraback.repository;

import javax.inject.Inject;
import javax.persistence.criteria.CollectionJoin;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;

import lombok.Setter;
import mx.gob.imss.dpes.interfaces.pensionado.model.Pensionado;
import mx.gob.imss.dpes.baseback.persistence.BaseSpecification;
import mx.gob.imss.dpes.common.rule.MontoTotalRule;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcCondicionEntfed;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcCondicionOfertaOldie;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcCondicionOferta_;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcEntidadFinanciera_;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcCondicionEntfed_;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcCondicionOfertaOldie_;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcEntidadFinanciera;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcPlazo_;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcSexo_;

/**
 *
 * @author antonio
 */
public class OfertasByPagoMensualSpecification extends BaseSpecification<MclcCondicionOfertaOldie> {

    private final Pensionado pensionado;  
    private final double monto;
    private final double saldoCapital;
    @Inject
    @Setter
    MontoTotalRule montoTotalRule;


        public OfertasByPagoMensualSpecification(Pensionado pensionado, double monto, double saldoCapital) {
        this.pensionado = pensionado;      
        this.monto = monto;
        this.saldoCapital=saldoCapital;
    }

    @Override
    public Predicate toPredicate(Root<MclcCondicionOfertaOldie> root, CriteriaQuery<?> cq,
            CriteriaBuilder cb) {

        SetJoin<MclcEntidadFinanciera, MclcCondicionEntfed> join
                = root.join(MclcCondicionOfertaOldie_.mclcEntidadFinanciera)
                        .join(MclcEntidadFinanciera_.mclcCondicionEntfedCollection);
        return cb.and(
               cb.ge(join.get(MclcCondicionEntfed_.monMaximo), 
                        cb.prod( monto  ,  root.get(MclcCondicionOfertaOldie_.mclcPlazo).get(MclcPlazo_.numMeses) )),
               cb.le(join.get(MclcCondicionEntfed_.monMinimo), 
                  cb.prod( monto , root.get(MclcCondicionOfertaOldie_.mclcPlazo).get(MclcPlazo_.numMeses) ) ),
                 cb.equal(join.get(MclcCondicionEntfed_.mclcSexo).get(MclcSexo_.id), pensionado.getSexo().getId()),
                cb.equal(join.get(MclcCondicionEntfed_.cveDelegacion), pensionado.getDelegacion().getCveDelegacion()),
  				cb.isNotNull(root.get(MclcCondicionOfertaOldie_.POR_CAT)),              
                cb.ge(join.get(MclcCondicionEntfed_.numEdadLimite),pensionado.getEdad()),
 cb.ge(
       cb.diff(cb.prod( monto ,  root.get(MclcCondicionOfertaOldie_.mclcPlazo).get(MclcPlazo_.numMeses)),
                 cb.prod(cb.prod( monto,root.get(MclcCondicionOfertaOldie_.mclcPlazo).get(MclcPlazo_.numMeses)), 
                         cb.quot(root.get(MclcCondicionOfertaOldie_.porCat),100))),cb.quot(
		(cb.prod(saldoCapital , root.get(MclcCondicionOfertaOldie_.mclcPlazo).get(MclcPlazo_.numMeses))),root.get(MclcCondicionOfertaOldie_.mclcPlazo).get(MclcPlazo_.numMeses)   ))
        );
       
    }

}
