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
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcEntidadFinancieraPersistencia;
import mx.gob.imss.dpes.entidadfinancieraback.repository.MclcEntidadFinancieraPersistenciaRepository;
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
public class EntidadFinancieraPersistenceService extends BaseCRUDService<MclcEntidadFinancieraPersistencia, MclcEntidadFinancieraPersistencia, Long, Long> {

    @Autowired
    private MclcEntidadFinancieraPersistenciaRepository repository;

    public Message<MclcEntidadFinancieraPersistencia> execute(Message<MclcEntidadFinancieraPersistencia> request) throws
            BusinessException {
        log.log(Level.INFO, "Iniciando back");
        MclcEntidadFinancieraPersistencia saved = save(request.getPayload());
        log.log(Level.INFO, "Response Back: {0}", saved);
        return new Message<>(saved);
    }

    @Override
    public JpaSpecificationExecutor<MclcEntidadFinancieraPersistencia> getRepository() {
        return repository;
    }

    @Override
    public JpaRepository<MclcEntidadFinancieraPersistencia, Long> getJpaRepository() {
        return repository;
    }

}
