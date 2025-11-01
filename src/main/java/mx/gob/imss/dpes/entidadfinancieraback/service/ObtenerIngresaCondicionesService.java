/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.entidadfinancieraback.service;

import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.ws.rs.ext.Provider;

import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcBeneficio;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcCondicionOfertaOldie;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcEntidadFinanciera_;
import mx.gob.imss.dpes.entidadfinancieraback.exception.EntidadFinancieraException;
import mx.gob.imss.dpes.entidadfinancieraback.repository.EntidadFinancieraSpecification;
import mx.gob.imss.dpes.entidadfinancieraback.repository.MclcEntidadFinancieraEMRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import lombok.Setter;
import mx.gob.imss.dpes.baseback.service.BaseCRUDService;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.entidadfinancieraback.assembler.EntidadFinancieraAssembler;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcEntidadFinanciera;
import mx.gob.imss.dpes.entidadfinancieraback.repository.MclcEntidadFinancieraRepository;
import mx.gob.imss.dpes.interfaces.entidadfinanciera.ot2.model.EntidadFinanciera;
import mx.gob.imss.dpes.support.config.CustomSpringBeanAutowiringInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@Provider
@Interceptors(CustomSpringBeanAutowiringInterceptor.class)
public class ObtenerIngresaCondicionesService {
    Logger log = Logger.getLogger(getClass().getName());
    @Autowired
    private MclcEntidadFinancieraEMRepository mclcEntidadFinancieraEMRepository;

    @Autowired
    private MclcEntidadFinancieraRepository mclcEntidadFinancieraRepository;

    @Inject
    @Setter
    EntidadFinancieraAssembler assembler;

    public EntidadFinanciera obtenerConCondicionesOfertaYBeneficios(Long id) throws BusinessException {
        try {
            Set<MclcCondicionOfertaOldie> setCondicionOferta = this.obtenerColeccionCondicionesOferta(id);
			MclcEntidadFinanciera entidadFinanciera = this.obtenerBeneficios(id, setCondicionOferta);

			if (entidadFinanciera == null)
                entidadFinanciera = this.obtenerEF(id);

            if (entidadFinanciera == null)
                throw new Exception();

			return assembler.assemble(entidadFinanciera);
        } catch (Exception e) {
            log.log(Level.SEVERE, "ObtenerIngresaCondicionesService.obtenerConCondicionesOfertaYBeneficios() - id[ " + id + " ]", e);
            throw new EntidadFinancieraException(EntidadFinancieraException.ERROR_DE_LECTURA_DE_BD);
        }
    }
    private MclcEntidadFinanciera obtenerCondicionesOfertaActivas(Long idEntidadFinanciera) throws Exception {
        try {
            return mclcEntidadFinancieraRepository.obtenerEFConCondicionesOferta(idEntidadFinanciera);
        } catch (Exception e) {
            throw e;
        }
    }
    private Set<MclcCondicionOfertaOldie> obtenerColeccionCondicionesOferta(Long id) throws Exception {
        try {
            Set<MclcCondicionOfertaOldie> universoDeCondicionesOfertas = new HashSet<>();
            MclcEntidadFinanciera entidadFinancieraCondifionOferta = this.obtenerCondicionesOfertaActivas(id);

            if (entidadFinancieraCondifionOferta != null) {
                Collection<MclcCondicionOfertaOldie> condicionesOferta =
                        entidadFinancieraCondifionOferta.getMclcCondicionOfertaCollection();

                if (condicionesOferta != null && !condicionesOferta.isEmpty()) {
                    MclcCondicionOfertaOldie aux = null;

                    for (MclcCondicionOfertaOldie condicion : condicionesOferta) {
                        aux = new MclcCondicionOfertaOldie();
                        aux.setId(condicion.getId());
                        aux.setPorTasaAnual(condicion.getPorTasaAnual());
                        aux.setPorCat(condicion.getPorCat());
                        aux.setMclcPlazo(condicion.getMclcPlazo());
                        aux.setMclcBeneficioCollection(null);
                        universoDeCondicionesOfertas.add(aux);
                    }
                }
            }
            return universoDeCondicionesOfertas;
        } catch (Exception e) {
            throw e;
        }
    }

    private MclcEntidadFinanciera obtenerCondicionesYBeneficios(Long idEntidadFinanciera) throws Exception {
        try {
            return mclcEntidadFinancieraRepository.obtenerEFConCondicionesOfertaYBeneficios(idEntidadFinanciera);
        } catch (Exception e) {
            throw e;
        }
    }

    private MclcEntidadFinanciera obtenerBeneficios(Long id, Set<MclcCondicionOfertaOldie> collectionCondicionesOferta) throws Exception {
        try {
            MclcEntidadFinanciera entidadFinanciera = this.obtenerCondicionesYBeneficios(id);
            if (entidadFinanciera == null)
                return null;

            Collection<MclcCondicionOfertaOldie> condicionesOferta =
                    entidadFinanciera.getMclcCondicionOfertaCollection();

            if (condicionesOferta != null && !condicionesOferta.isEmpty()) {
                for (MclcCondicionOfertaOldie condicionOferta : condicionesOferta) {
                    collectionCondicionesOferta.remove(condicionOferta);
                }
                if (!collectionCondicionesOferta.isEmpty())
                    entidadFinanciera.getMclcCondicionOfertaCollection().addAll(collectionCondicionesOferta);
            }
			return entidadFinanciera;
        } catch (Exception e) {
            throw e;
        }
    }

    private MclcEntidadFinanciera obtenerEF(Long id) throws Exception {
        try {
            return mclcEntidadFinancieraRepository.obtenerEF(id);
        } catch (Exception e) {
            throw e;
        }
    }

}
