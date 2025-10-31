/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.entidadfinancieraback.service;

import javax.interceptor.Interceptors;
import javax.ws.rs.ext.Provider;
import lombok.Setter;
import mx.gob.imss.dpes.baseback.service.BaseCRUDService;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcCondicionOfertaOldie;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcEntidadFinanciera;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcPlazo;
import mx.gob.imss.dpes.entidadfinancieraback.exception.CondicionOfertaException;
import mx.gob.imss.dpes.interfaces.entidadfinanciera.model.Oferta;
import mx.gob.imss.dpes.support.config.CustomSpringBeanAutowiringInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import mx.gob.imss.dpes.entidadfinancieraback.repository.MclcCondicionOfertaRepository_oldie;

import java.util.logging.Level;

/**
 *
 * @author Diego Velazquez
 */
@Provider
@Interceptors(CustomSpringBeanAutowiringInterceptor.class)
public class ObtenerCondicionOfertaService 
        extends BaseCRUDService<MclcCondicionOfertaOldie, MclcCondicionOfertaOldie, Long, Long>{

    @Autowired @Setter
    private MclcCondicionOfertaRepository_oldie repository;

    @Override
    public JpaSpecificationExecutor<MclcCondicionOfertaOldie> getRepository() {
        return repository;
    }

    @Override
    public JpaRepository<MclcCondicionOfertaOldie, Long> getJpaRepository() {
        return repository;
        
    }
    
    public Oferta obtenerOferta(Long clave) throws BusinessException {
        try {
            Oferta oferta = new Oferta();

            MclcCondicionOfertaOldie condicionOferta = super.findOne(clave);
            if (condicionOferta != null) {
                oferta.setCat(condicionOferta.getPorCat() != null ?
                    new Double(condicionOferta.getPorCat().toString()) : 0);

                MclcPlazo mclcPlazo = condicionOferta.getMclcPlazo();
                if (mclcPlazo != null && mclcPlazo.getId() != null) {
                    oferta.getPlazo().setId(mclcPlazo.getId());
                    oferta.getPlazo().setDescripcion(mclcPlazo.getDescripcion());
                    oferta.getPlazo().setNumPlazo(mclcPlazo.getNumMeses());
                }

                oferta.setTasaAnual(condicionOferta.getPorTasaAnual() != null ?
                        new Double(condicionOferta.getPorTasaAnual().toString()) : 0);

                MclcEntidadFinanciera mclcEntidadFinanciera = condicionOferta.getMclcEntidadFinanciera();
                if (mclcEntidadFinanciera != null && mclcEntidadFinanciera.getId() != null) {
                    oferta.getEntidadFinanciera().setId(mclcEntidadFinanciera.getId());
                    oferta.getEntidadFinanciera().setNombreComercial(mclcEntidadFinanciera.getNombreComercial());
                    oferta.getEntidadFinanciera().setNumTelefono(mclcEntidadFinanciera.getNumeroTelefono());
                    oferta.getEntidadFinanciera().setPaginaWeb(mclcEntidadFinanciera.getPaginaWeb());
                    oferta.getEntidadFinanciera().setRazonSocial(mclcEntidadFinanciera.getRazonSocial());
                }
            }

            return oferta;
        } catch (Exception e) {
            log.log(Level.SEVERE, "ERROR ObtenerCondicionOfertaService.obtenerOferta = {0}", e);
            throw new CondicionOfertaException(CondicionOfertaException.ERROR_DE_LECTURA_DE_BD);
        }
    }
}
