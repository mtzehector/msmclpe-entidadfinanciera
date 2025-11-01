/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.entidadfinancieraback.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import javax.interceptor.Interceptors;
import javax.ws.rs.ext.Provider;
import mx.gob.imss.dpes.baseback.service.BaseCRUDService;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcCondicionOferta;
import mx.gob.imss.dpes.entidadfinancieraback.exception.CondicionOfertaException;
import mx.gob.imss.dpes.entidadfinancieraback.exception.PlazosException;
import mx.gob.imss.dpes.entidadfinancieraback.repository.MclcCondicionOfertaRepository;
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
public class CondicionOfertaService extends BaseCRUDService<MclcCondicionOferta, MclcCondicionOferta, Long, Long> {

    @Autowired
    private MclcCondicionOfertaRepository repository;

    public Message<MclcCondicionOferta> execute(Message<MclcCondicionOferta> request) throws
            BusinessException {
        log.log(Level.INFO, "*********Condición Oferta: {0}", request.getPayload());
        if (request.getPayload().getId() != null) {
            log.log(Level.INFO,"Flujo update");
            MclcCondicionOferta current = findOne(request.getPayload().getId());
            Long idEntidad = current.getMclcEntidadFinanciera();
            Long idPlazo = current.getMclcPlazo();
            Long id = current.getId();
            log.log(Level.INFO, "ID entidadfinanciera:{0}", idEntidad);
            log.log(Level.INFO, "ID plazo: {0}", idPlazo);
            log.log(Level.INFO,"ID condicion: {0}",id);
            current.setPorCat(request.getPayload().getPorCat());
            current.setBajaRegistro(request.getPayload().getBajaRegistro());
            MclcCondicionOferta saved = save(current);
            return new Message<>(saved);
        }else if(request.getPayload() != null){
            log.log(Level.INFO,"Flujo save");
            MclcCondicionOferta saved = save(request.getPayload());
            return new Message<>(saved);
        }

        throw new PlazosException(PlazosException.NOTFOUND);
    }

    public List<MclcCondicionOferta> obtieneCondicionesPorIdEntidadFinanciera(Long idEntidadFinanciera)
        throws BusinessException {

        try {
            return repository.obtieneCondicionesPorMclcEntidadFinanciera(idEntidadFinanciera);
        }
        catch (Exception e) {
            log.log(Level.SEVERE, "ERROR CondicionOfertaService.obtieneCondicionesPorIdEntidadFinanciera {0}", e);
        }

        throw new CondicionOfertaException(CondicionOfertaException.ERROR_DE_LECTURA_DE_BD);
    }

    @Override
    public JpaSpecificationExecutor<MclcCondicionOferta> getRepository() {
        return repository;
    }

    @Override
    public JpaRepository<MclcCondicionOferta, Long> getJpaRepository() {
        return repository;
    }

}
