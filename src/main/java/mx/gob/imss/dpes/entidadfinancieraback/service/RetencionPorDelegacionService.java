package mx.gob.imss.dpes.entidadfinancieraback.service;

import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.entidadfinancieraback.exception.ConciliacionException;
import mx.gob.imss.dpes.entidadfinancieraback.model.ImporteFallecidos;
import mx.gob.imss.dpes.entidadfinancieraback.model.ReporteRetencionPorDelegacion;
import mx.gob.imss.dpes.entidadfinancieraback.repository.ImporteFallecidosRepository;
import mx.gob.imss.dpes.entidadfinancieraback.repository.RetencionPorDelegacionRepository;
import mx.gob.imss.dpes.interfaces.entidadfinanciera.model.ConciliacionRequest;
import mx.gob.imss.dpes.support.config.CustomSpringBeanAutowiringInterceptor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.interceptor.Interceptors;
import javax.ws.rs.ext.Provider;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Provider
@Interceptors(CustomSpringBeanAutowiringInterceptor.class)
public class RetencionPorDelegacionService {

    @Autowired
    private RetencionPorDelegacionRepository repository;
    @Autowired
    private ImporteFallecidosRepository importeFallecidosRepository;
    private Logger log = Logger.getLogger(this.getClass().getName());

    public List<ReporteRetencionPorDelegacion> obtenerRetencionPorDelegacion(ConciliacionRequest request) throws BusinessException {
        try {
            return repository.ejecutarConsultaSQL(
                    request.getPeriodo(),
                    request.getCveEntidadFinanciera()
            );
        }catch (Exception e){
            log.log(Level.SEVERE, "ERROR RetencionPorDelegacionService.obtenerRetencionPorDelegacion()",e);
            throw new ConciliacionException(ConciliacionException.ERROR_AL_EJECUTAR_CONSULTA);
        }
    }

    public ImporteFallecidos obtenerImporteFallecidos(ConciliacionRequest request) throws BusinessException {
        try {
            return importeFallecidosRepository.ejecutarConsultaSQL(
                    request.getPeriodo(),
                    request.getCveEntidadFinanciera()
            );
        }catch (Exception e){
            log.log(Level.SEVERE, "ERROR RetencionPorDelegacionService.obtenerImporteFallecidos()",e);
            throw new ConciliacionException(ConciliacionException.ERROR_AL_EJECUTAR_CONSULTA);
        }
    }

}
