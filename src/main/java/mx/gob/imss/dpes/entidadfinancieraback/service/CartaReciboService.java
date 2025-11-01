/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.entidadfinancieraback.service;

import java.util.Date;
import java.util.List;
import javax.interceptor.Interceptors;
import javax.ws.rs.ext.Provider;
import mx.gob.imss.dpes.baseback.service.BaseCRUDService;
import mx.gob.imss.dpes.entidadfinancieraback.entity.McltCartaRecibo;
import mx.gob.imss.dpes.entidadfinancieraback.repository.McltCartaReciboRepository;
import mx.gob.imss.dpes.support.config.CustomSpringBeanAutowiringInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * @author juanf.barragan
 */
@Provider
@Interceptors(CustomSpringBeanAutowiringInterceptor.class)
public class CartaReciboService extends BaseCRUDService<McltCartaRecibo,McltCartaRecibo,Long,Long> {
    
    @Autowired
    private McltCartaReciboRepository repository;

    public McltCartaRecibo guardarCarta(McltCartaRecibo carta){
        carta.setAltaRegistro(new Date());
        McltCartaRecibo saved = repository.save(carta);
        
        return saved;
    }
    
    public McltCartaRecibo recuperaCarta(Long id){
        
        McltCartaRecibo carta = repository.findOne(id);
        
        return carta;
    }
    
    public McltCartaRecibo validaPeriodo(Long ef, String periodo){
        
        //McltCartaRecibo carta = repository.findByCveEntFinandPeriodo(ef, periodo);
        
        return null;
    }
    
    public List<McltCartaRecibo> obtenPeriodo (McltCartaRecibo request) {
        return repository.findByPeriodo(request.getPeriodo());
    }
    
    
    @Override
    public JpaSpecificationExecutor<McltCartaRecibo> getRepository() {
        return repository;
    }

    @Override
    public JpaRepository<McltCartaRecibo, Long> getJpaRepository() {
        return repository;
    }
    
}
