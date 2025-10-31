/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.entidadfinancieraback.service;

import java.util.LinkedList;
import java.util.logging.Level;
import javax.interceptor.Interceptors;
import javax.ws.rs.ext.Provider;
import mx.gob.imss.dpes.baseback.service.BaseCRUDService;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.interfaces.entidadfinanciera.model.Delegacion;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcCondicionEntfed;
import mx.gob.imss.dpes.entidadfinancieraback.repository.CondEntFederativaOperations;
import mx.gob.imss.dpes.entidadfinancieraback.repository.MclcCondicionEntfedRepository;
import mx.gob.imss.dpes.entidadfinancieraback.repository.MclcContEntDelRepository;
import mx.gob.imss.dpes.support.config.CustomSpringBeanAutowiringInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * @author eduardo.loyo
 */
@Provider
@Interceptors(CustomSpringBeanAutowiringInterceptor.class)
public class CondicionFederativaService extends BaseCRUDService<MclcCondicionEntfed, MclcCondicionEntfed, Long, Long> {

    @Autowired
    private MclcCondicionEntfedRepository repository;
    @Autowired
    private MclcContEntDelRepository repositoryDel;
    @Autowired
    CondEntFederativaOperations condEntFederativaDAO;
    
    public Message<MclcCondicionEntfed> execute(Message<MclcCondicionEntfed> request) throws
            BusinessException {
        log.log(Level.INFO, ">>> entidadFinancieraBack CondicionFederativaService ENT Federativa service: {0}", request.getPayload());

        MclcCondicionEntfed saved = save(request.getPayload());
        return new Message<>(saved);
    }

    public void del(Long id){
        getRepositoryDel().delConEnt(id);
    
    }
    
     
    public MclcContEntDelRepository getRepositoryDel() {
        return repositoryDel;
    }
    @Override
    public JpaSpecificationExecutor<MclcCondicionEntfed> getRepository() {
        return repository;
    }

    @Override
    public JpaRepository<MclcCondicionEntfed, Long> getJpaRepository() {
        return repository;
    }
    
    public LinkedList<Delegacion> findDelegaciones(Long cveEntidadFinanciera){
        LinkedList<Delegacion> delegacionesLista = condEntFederativaDAO.findDelegacionesLista(cveEntidadFinanciera);
        return delegacionesLista;
    }
    

}
