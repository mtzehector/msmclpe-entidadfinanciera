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
import javax.persistence.criteria.SetJoin;

import mx.gob.imss.dpes.interfaces.pensionado.model.Pensionado;
import mx.gob.imss.dpes.baseback.persistence.BaseSpecification;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcCondicionEntfed;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcCondicionOfertaOldie;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcCondicionOferta_;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcEntidadFinanciera_;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcCondicionEntfed_;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcCondicionOfertaOldie_;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcEntidadFinanciera;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcSexo_;

/**
 *
 * @author antonio
 */
public class OfertasByPagoTotalSpecification extends BaseSpecification<MclcCondicionOfertaOldie> {

    private final Pensionado pensionado;
    private final double monto;

    public OfertasByPagoTotalSpecification(Pensionado pensionado, double monto) {
        this.pensionado = pensionado;
        this.monto = monto;
        log.info("ofertasPago pensionado "+ pensionado.toString());
        log.info("ofertasPago monto "+ monto);
    }

    @Override
    public Predicate toPredicate(Root<MclcCondicionOfertaOldie> root, CriteriaQuery<?> cq,
            CriteriaBuilder cb) {
        Predicate res;
        SetJoin<MclcEntidadFinanciera, MclcCondicionEntfed> join
                = root.join(MclcCondicionOfertaOldie_.mclcEntidadFinanciera)
                        .join(MclcEntidadFinanciera_.mclcCondicionEntfedCollection);
        res = cb.and(
                cb.ge(join.get(MclcCondicionEntfed_.monMaximo), monto),
                 cb.le(join.get(MclcCondicionEntfed_.monMinimo), monto),
                 cb.equal(join.get(MclcCondicionEntfed_.mclcSexo).get(MclcSexo_.id), pensionado.getSexo().getId()),
                cb.equal(join.get(MclcCondicionEntfed_.cveDelegacion), pensionado.getDelegacion().getCveDelegacion()),
                cb.isNotNull(root.get(MclcCondicionOfertaOldie_.POR_CAT)),
                cb.ge(join.get(MclcCondicionEntfed_.numEdadLimite),pensionado.getEdad())
        );

              return res;
       
    }

}
