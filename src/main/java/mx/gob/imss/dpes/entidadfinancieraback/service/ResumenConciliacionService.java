package mx.gob.imss.dpes.entidadfinancieraback.service;

import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.entidadfinancieraback.exception.ConciliacionException;
import mx.gob.imss.dpes.entidadfinancieraback.model.ReporteResumenConciliacion;
import mx.gob.imss.dpes.entidadfinancieraback.repository.ResumenConciliacionRepository;
import mx.gob.imss.dpes.interfaces.entidadfinanciera.model.ConciliacionRequest;
import mx.gob.imss.dpes.support.config.CustomSpringBeanAutowiringInterceptor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.interceptor.Interceptors;
import javax.ws.rs.ext.Provider;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Provider
@Interceptors(CustomSpringBeanAutowiringInterceptor.class)
public class ResumenConciliacionService {

    @Autowired
    private ResumenConciliacionRepository repository;
    private Logger log = Logger.getLogger(this.getClass().getName());

    public List<ReporteResumenConciliacion> obtenerDatosResumenConciliacion(ConciliacionRequest request) throws BusinessException {
        try {
            return repository.ejecutarConsulta(request.getPeriodo());
        }catch (Exception e){
            log.log(Level.SEVERE, "ERROR ResumenConciliacionService.obtenerDatosResumenConciliacion()",e);
            throw new ConciliacionException(ConciliacionException.ERROR_AL_EJECUTAR_CONSULTA);
        }
    }

}
