package mx.gob.imss.dpes.entidadfinancieraback.repository;

import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.entidadfinancieraback.entity.*;
import mx.gob.imss.dpes.entidadfinancieraback.exception.EntidadFinancieraException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class MclcEntidadFinancieraEMRepository {
    protected final transient Logger log = Logger.getLogger(getClass().getName());

    private final static String BAJA_REGISTRO = "bajaRegistro";

    @PersistenceContext
    private EntityManager entityManager;

    public MclcEntidadFinanciera obtenerEFConCondicionesOfertaYBeneficios(Long id) throws BusinessException {
        try {
            EntityGraph entityGraph = entityManager.getEntityGraph("MclcEntidadFinanciera.ConCondicionesOfertaYBeneficios");
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<MclcEntidadFinanciera> criteriaQuery = criteriaBuilder.createQuery(MclcEntidadFinanciera.class);

            Root<MclcEntidadFinanciera> root = criteriaQuery.from(MclcEntidadFinanciera.class);
            Join<MclcEntidadFinanciera, MclcCondicionOfertaOldie> joinCondicionOferta = root.joinCollection(MclcEntidadFinanciera_.MCLC_CONDICION_OFERTA_COLLECTION);
            Join<MclcCondicionOfertaOldie, MclcBeneficio> joinBeneficios = joinCondicionOferta.joinCollection(MclcCondicionOfertaOldie_.MCLC_BENEFICIO_COLLECTION);

            criteriaQuery.where(
                    criteriaBuilder.and(
                            criteriaBuilder.equal(root.<Long>get(MclcEntidadFinanciera_.ID), id),
                            criteriaBuilder.isNull(root.<Boolean>get(BAJA_REGISTRO)),
                            criteriaBuilder.isNull(joinCondicionOferta.<Boolean>get(BAJA_REGISTRO)),
                            criteriaBuilder.isNull(joinBeneficios.<Boolean>get(BAJA_REGISTRO))
                    )
            );

            TypedQuery<MclcEntidadFinanciera> typedQuery = entityManager.createQuery(criteriaQuery);
            typedQuery.setHint("jakarta.persistence.loadgraph", entityGraph);

            MclcEntidadFinanciera entidadFinanciera = typedQuery.getSingleResult();

            return entidadFinanciera;
        } catch (Exception e) {
            log.log(Level.SEVERE, "ObtenerIngresaCondicionesService.load() - id[ " + id + " ]", e);
            throw new EntidadFinancieraException(EntidadFinancieraException.ERROR_DE_LECTURA_DE_BD);
        }
    }
}
