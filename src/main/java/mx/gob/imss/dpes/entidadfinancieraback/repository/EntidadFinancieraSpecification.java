package mx.gob.imss.dpes.entidadfinancieraback.repository;

import mx.gob.imss.dpes.baseback.persistence.BaseSpecification;
import mx.gob.imss.dpes.entidadfinancieraback.entity.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class EntidadFinancieraSpecification extends BaseSpecification<MclcEntidadFinanciera> {

    private final static String NOMBRE_ATRIBUTO = "bajaRegistro";
    private Long idEntidadFinanciera;

    public EntidadFinancieraSpecification(Long idEntidadFinanciera) {
        this.idEntidadFinanciera = idEntidadFinanciera;
    }

    @Override
    public Predicate toPredicate(Root<MclcEntidadFinanciera> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {


        Join<MclcEntidadFinanciera, MclcCondicionOfertaOldie> joinCondicionOferta = root.join(MclcEntidadFinanciera_.MCLC_CONDICION_OFERTA_COLLECTION);
        Join<MclcCondicionOfertaOldie, MclcBeneficio> joinBeneficio = joinCondicionOferta.join(MclcCondicionOfertaOldie_.MCLC_BENEFICIO_COLLECTION);
        Join<MclcCondicionOfertaOldie, MclcPlazo> joinPlazo = joinCondicionOferta.join(MclcCondicionOfertaOldie_.MCLC_PLAZO);
        Join<MclcEntidadFinanciera, MclcEstadoEf> joinEstadoEF = root.join(MclcEntidadFinanciera_.MCLC_ESTADO_EF);
        cq.orderBy(cb.desc(joinBeneficio.get(MclcBeneficio_.ID)));

        return cb.and(
                cb.equal(root.get(MclcEntidadFinanciera_.ID), this.idEntidadFinanciera),
                cb.isNull(root.<Boolean>get(NOMBRE_ATRIBUTO)),
                cb.isNull(joinCondicionOferta.<Boolean>get(NOMBRE_ATRIBUTO)),
                cb.isNull(joinBeneficio.<Boolean>get(NOMBRE_ATRIBUTO)),
                cb.isNull(joinPlazo.<Boolean>get(NOMBRE_ATRIBUTO)),
                cb.isNull(joinEstadoEF.<Boolean>get(NOMBRE_ATRIBUTO))
        );

    }
}
