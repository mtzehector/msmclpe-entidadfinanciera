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
import mx.gob.imss.dpes.entidadfinancieraback.repository.EntidadFinancieraRepository;
import mx.gob.imss.dpes.entidadfinancieraback.repository.EntidadesByConvenioSpecification;
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
public class ObtenerEntidadFinancieraService extends BaseCRUDService<EntidadFinanciera, EntidadFinanciera, Long, Long> {

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

        EntidadFinanciera load = findOne(request.getPayload().getId());
        //log.log(Level.SEVERE, ">>>>>ENTIDAD FINANCIERA BACK", load);
        log.log(Level.SEVERE, ">>>>>ENTIDAD FINANCIERA BACK load(Message<EntidadFinanciera> request");
        return new Message<>(load);
    }

    public Message<EntidadFinanciera> load(String id)
            throws BusinessException {
        EntidadFinanciera load = new EntidadFinanciera();
        try {
            load = (EntidadFinanciera) repository.findByCveEntidadFinancieraSipre(id).get(0);
        } catch (Exception e) {
            load = new EntidadFinanciera();
            load.setNombreComercial("");
            load.setCveEntidadFinancieraSipre("0");
        }
        log.log(Level.SEVERE, ">>>>>ENTIDAD FINANCIERA BACK load(String id)");
        return new Message<>(load);
    }
    public Message<EntidadFinanciera> loadAso(String id)
            throws BusinessException {
        EntidadFinanciera load = new EntidadFinanciera();
        try {
            load = (EntidadFinanciera) repository.findOne(Long.parseLong(id));
        } catch (Exception e) {
            load = new EntidadFinanciera();
            load.setNombreComercial("");
            load.setCveEntidadFinancieraSipre("0");
        }
        log.log(Level.SEVERE, ">>>>>ENTIDAD FINANCIERA BACK load(String id)");
        return new Message<>(load);
    }
    
    public String registroPatronalPadre(Long id){
        EntidadFinanciera load = findOne(id);
        
        return load.getRegistroPatronal();
    }
    public List<String> getCorreosAdmin(){
        
       EntidadesByConvenioSpecification esp = new EntidadesByConvenioSpecification(1L);
       
       List<EntidadFinanciera> entidades = repository.findAll(esp);
       
       List<String> correos = new ArrayList();
       for (EntidadFinanciera ef : entidades){
       correos.add(ef.getCorreoAdminEF());
       }
        return correos;
    }
}