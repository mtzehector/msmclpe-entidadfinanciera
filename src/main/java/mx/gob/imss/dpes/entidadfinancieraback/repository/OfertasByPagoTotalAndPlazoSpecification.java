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
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcEntidadFinanciera_;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcCondicionEntfed_;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcCondicionOfertaOldie_;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcEntidadFinanciera;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcEstadoEf_;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcPlazo_;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcSexo_;

/**
 *
 * @author Diego Velazquez
 */
public class OfertasByPagoTotalAndPlazoSpecification extends BaseSpecification<MclcCondicionOfertaOldie> {

    private final Pensionado pensionado;
    private final double monto;
    private final Long plazo;

    public OfertasByPagoTotalAndPlazoSpecification(Pensionado pensionado, double monto, Long plazo) {
        this.pensionado = pensionado;
        this.monto = monto;
        this.plazo = plazo;
    }

    @Override
    public Predicate toPredicate(Root<MclcCondicionOfertaOldie> root, CriteriaQuery<?> cq,
                                 CriteriaBuilder cb) {

        SetJoin<MclcEntidadFinanciera, MclcCondicionEntfed> join
                = root.join(MclcCondicionOfertaOldie_.mclcEntidadFinanciera)
                .join(MclcEntidadFinanciera_.mclcCondicionEntfedCollection);

        return cb.and(
                cb.isNotNull(root.get(MclcCondicionOfertaOldie_.porCat)),
                cb.isNotNull(root.get(MclcCondicionOfertaOldie_.porTasaAnual)),
                cb.le(root.get(MclcCondicionOfertaOldie_.porCat), root.get(MclcCondicionOfertaOldie_.porTasaAnual)),
                cb.ge(join.get(MclcCondicionEntfed_.monMaximo), monto),
                cb.le(join.get(MclcCondicionEntfed_.monMinimo), monto),
                cb.equal(join.get(MclcCondicionEntfed_.mclcSexo).get(MclcSexo_.id), pensionado.getSexo().getId()),
                cb.equal(join.get(MclcCondicionEntfed_.cveDelegacion), pensionado.getDelegacion().getCveDelegacion()),
                cb.ge(join.get(MclcCondicionEntfed_.numEdadLimite),pensionado.getEdad()),
                cb.equal(root.get(MclcCondicionOfertaOldie_.mclcPlazo).get(MclcPlazo_.id), plazo),
                cb.equal(root.get(MclcCondicionOfertaOldie_.mclcEntidadFinanciera).
                        get(MclcEntidadFinanciera_.mclcEstadoEf).get(MclcEstadoEf_.id), 1)
        );
    }
}
