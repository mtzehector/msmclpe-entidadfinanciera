package mx.gob.imss.dpes.entidadfinancieraback.service;

import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.entidadfinancieraback.exception.ConciliacionException;
import mx.gob.imss.dpes.entidadfinancieraback.repository.CartaReciboConciliacionRepository;
import mx.gob.imss.dpes.interfaces.entidadfinanciera.model.CartaRecibo;
import mx.gob.imss.dpes.support.config.CustomSpringBeanAutowiringInterceptor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.interceptor.Interceptors;
import javax.ws.rs.ext.Provider;
import java.util.logging.Level;
import java.util.logging.Logger;

@Provider
@Interceptors(CustomSpringBeanAutowiringInterceptor.class)
public class CartaReciboConciliacionService {

    private Logger log = Logger.getLogger(this.getClass().getName());

    @Autowired
    private CartaReciboConciliacionRepository repository;

    public CartaRecibo obtenerDatosCartaRecibo(String cveEntidadFinancieraSipre, String periodo)throws BusinessException{
        try {
            return repository.ejecutarConsulta(cveEntidadFinancieraSipre, periodo);
        }catch (Exception e){
            log.log(Level.SEVERE, "ERROR CartaReciboConciliacionService.obtenerDatosCartaRecibo()",e);
            throw new ConciliacionException(ConciliacionException.ERROR_AL_EJECUTAR_CONSULTA);
        }
    }

}
