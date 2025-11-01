/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.entidadfinancieraback.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import javax.interceptor.Interceptors;
import javax.ws.rs.ext.Provider;
import mx.gob.imss.dpes.baseback.persistence.BaseSpecification;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.baseback.service.BaseCRUDService;
import mx.gob.imss.dpes.entidadfinancieraback.entity.EntidadFinanciera;
import mx.gob.imss.dpes.entidadfinancieraback.repository.EntidadFinancieraByAdmin;
import mx.gob.imss.dpes.entidadfinancieraback.repository.EntidadFinancieraRepository;
import mx.gob.imss.dpes.support.config.CustomSpringBeanAutowiringInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * @author Diego Velazquez
 */
@Provider
@Interceptors(CustomSpringBeanAutowiringInterceptor.class)
public class ObtenerRegistroPatronalEntidadFinancieraService
        extends BaseCRUDService<EntidadFinanciera, EntidadFinanciera, Long, Long> {

    @Autowired
    private EntidadFinancieraRepository repository;

    @Override
    public JpaSpecificationExecutor<EntidadFinanciera> getRepository() {
        return repository;
    }

    @Override
    public JpaRepository<EntidadFinanciera, Long> getJpaRepository() {
        return repository;
    }

    public Message<EntidadFinanciera> load(Message<EntidadFinanciera> request)
            throws BusinessException {
        
        List<EntidadFinanciera> load
                = repository.findByRegistroPatronal(request.getPayload().getRegistroPatronal());
        if(load.size() >0)
            return new Message<>(load.get(0));
        else
            return new Message<>();
    }
    
    public Message<EntidadFinanciera> obtenerInfoEFAdmin(Message<EntidadFinanciera> request)
            throws BusinessException {
        log.log(Level.INFO, ">>>>BACK SERVICE obtenerInfoEFAdmin REQUEST: {0}", request.getPayload());
         Collection<BaseSpecification> constraints = new ArrayList<>();
        constraints.add(new EntidadFinancieraByAdmin(request.getPayload().getCorreoAdminEF(), request.getPayload().getCurpAdmin()));
        EntidadFinanciera entidad = findOne(constraints);
        log.log(Level.INFO, ">>>>BACK SERVICE obtenerInfoEFAdmin RESPONSE: {0}", entidad);
        return new Message<>(entidad);
    }
}
