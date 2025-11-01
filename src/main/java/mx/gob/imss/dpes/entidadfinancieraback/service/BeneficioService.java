/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.entidadfinancieraback.service;

import java.util.logging.Level;
import javax.interceptor.Interceptors;
import javax.ws.rs.ext.Provider;
import mx.gob.imss.dpes.baseback.service.BaseCRUDService;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcBeneficio;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcCondicionEntfed;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcCondicionOferta;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcCondicionOfertaOldie;
import mx.gob.imss.dpes.entidadfinancieraback.exception.PlazosException;
import mx.gob.imss.dpes.entidadfinancieraback.repository.BeneficioRepository;
import mx.gob.imss.dpes.entidadfinancieraback.repository.MclcBeneficioDelRepository;
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
public class BeneficioService extends BaseCRUDService<MclcBeneficio, MclcBeneficio, Long, Long> {

    @Autowired
    private BeneficioRepository repository;
    
        @Autowired
    private MclcBeneficioDelRepository repositoryDel;
        
    public Message<MclcBeneficio> execute(Message<MclcBeneficio> request) throws
            BusinessException {
        log.log(Level.INFO, "Beneficio service: {0}", request.getPayload());

        if (request.getPayload().getId() != null) {
            log.log(Level.INFO,"Flujo update");
            Long id =request.getPayload().getId();
            request.getPayload().setId(null);
            getRepositoryDel().delConEnt(id);
                      MclcBeneficio saved = save(request.getPayload());

            return new Message<>(saved);
        }else if(request.getPayload() != null){
            log.log(Level.INFO,"Flujo save");
                                  MclcBeneficio saved = save(request.getPayload());

            return new Message<>(saved);
        }
 throw new PlazosException(PlazosException.NOTFOUND);
      
    }

    
     public MclcBeneficioDelRepository getRepositoryDel() {
        return repositoryDel;
    }
    @Override
    public JpaSpecificationExecutor<MclcBeneficio> getRepository() {
        return repository;
    }

    @Override
    public JpaRepository<MclcBeneficio, Long> getJpaRepository() {
        return repository;
    }

}
