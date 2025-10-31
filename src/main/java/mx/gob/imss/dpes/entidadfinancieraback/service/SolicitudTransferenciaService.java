package mx.gob.imss.dpes.entidadfinancieraback.service;

import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.entidadfinancieraback.exception.ConciliacionException;
import mx.gob.imss.dpes.entidadfinancieraback.model.SolicitudTransferenciaConciliacion;
import mx.gob.imss.dpes.entidadfinancieraback.repository.SolicitudTransferenciaRepository;
import mx.gob.imss.dpes.interfaces.entidadfinanciera.model.ConciliacionRequest;
import mx.gob.imss.dpes.support.config.CustomSpringBeanAutowiringInterceptor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.interceptor.Interceptors;
import javax.ws.rs.ext.Provider;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

@Provider
@Interceptors(CustomSpringBeanAutowiringInterceptor.class)
public class SolicitudTransferenciaService {

    @Autowired
    private SolicitudTransferenciaRepository repository;
    private Logger log = Logger.getLogger(this.getClass().getName());

    public SolicitudTransferenciaConciliacion obtenerDatosReporte(ConciliacionRequest request) throws BusinessException {
        try {
            return repository.ejecutarConsulta(
                    Arrays.asList(request.getArregloIdEntidadFinanciera()),
                    request.getPeriodo()
            );
        }catch (Exception e){
            log.log(Level.SEVERE, "ERROR SolicitudTransferenciaService.obtenerDatosReporte()",e);
            throw new ConciliacionException(ConciliacionException.ERROR_AL_EJECUTAR_CONSULTA);
        }
    }


}
