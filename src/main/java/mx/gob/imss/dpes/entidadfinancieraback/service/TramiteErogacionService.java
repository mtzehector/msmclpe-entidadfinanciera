package mx.gob.imss.dpes.entidadfinancieraback.service;

import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.entidadfinancieraback.exception.ConciliacionException;
import mx.gob.imss.dpes.entidadfinancieraback.model.TramiteErogacion;
import mx.gob.imss.dpes.entidadfinancieraback.repository.TramiteErogacionRepository;
import mx.gob.imss.dpes.support.config.CustomSpringBeanAutowiringInterceptor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.interceptor.Interceptors;
import javax.ws.rs.ext.Provider;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Provider
@Interceptors(CustomSpringBeanAutowiringInterceptor.class)
public class TramiteErogacionService {

    @Autowired
    private TramiteErogacionRepository repository;
    private Logger log = Logger.getLogger(this.getClass().getName());

    public List<TramiteErogacion> obtenerListTramiteErogacion(String periodo, Long cveEntidadFinanciera) throws BusinessException {
        try {
            return repository.ejecutarConsultaSQL(periodo, cveEntidadFinanciera);
        }catch (Exception e){
            log.log(Level.SEVERE, "ERROR CartaReciboFirmaService.obtenerListCartaReciboFirma()", e);
            throw new ConciliacionException(ConciliacionException.ERROR_DE_LECTURA_DE_BD);
        }
    }
}
