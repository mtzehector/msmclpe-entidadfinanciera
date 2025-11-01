package mx.gob.imss.dpes.entidadfinancieraback.service;

import mx.gob.imss.dpes.baseback.service.BaseCRUDService;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcEstatusConciliacion;
import mx.gob.imss.dpes.entidadfinancieraback.exception.EstatusConciliacionException;
import mx.gob.imss.dpes.entidadfinancieraback.repository.EstatusConciliacionRepository;
import mx.gob.imss.dpes.support.config.CustomSpringBeanAutowiringInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.interceptor.Interceptors;
import javax.ws.rs.ext.Provider;
import java.util.logging.Level;

@Provider
@Interceptors(CustomSpringBeanAutowiringInterceptor.class)
public class EstatusConciliacionService extends BaseCRUDService<MclcEstatusConciliacion, MclcEstatusConciliacion, Long, Long> {

    @Autowired
    private EstatusConciliacionRepository repository;

    public MclcEstatusConciliacion obtenerEstatusConciliacionPorPeriodo (String periodo) throws BusinessException {
        try {
            return repository.findByPeriodo(periodo);
        }catch (Exception e){
            log.log(Level.SEVERE, "ERROR EstatusConciliacionService.obtenerEstatusConciliacionPorPeriodo()",e);
            throw new EstatusConciliacionException(EstatusConciliacionException.ERROR_DE_LECTURA_DE_BD);
        }
    }

    public MclcEstatusConciliacion guardarEstatusConciliacion(MclcEstatusConciliacion estatusConciliacion) throws BusinessException{
        try {
            return save(estatusConciliacion);
        }catch (Exception e){
            log.log(Level.SEVERE, "ERROR EstatusConciliacionService.guardarEstatusConciliacion()",e);
            throw new EstatusConciliacionException(EstatusConciliacionException.ERROR_DE_ESCRITURA_EN_BD);
        }
    }

    @Override
    public JpaSpecificationExecutor<MclcEstatusConciliacion> getRepository() {
        return repository;
    }

    @Override
    public JpaRepository<MclcEstatusConciliacion, Long> getJpaRepository() {
        return repository;
    }
}
