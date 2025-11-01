package mx.gob.imss.dpes.entidadfinancieraback.service;

import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.entidadfinancieraback.exception.ConciliacionException;
import mx.gob.imss.dpes.entidadfinancieraback.model.PermisoItineranteCostoAdministrativo;
import mx.gob.imss.dpes.entidadfinancieraback.repository.PermisoItineranteRepository;
import mx.gob.imss.dpes.support.config.CustomSpringBeanAutowiringInterceptor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.interceptor.Interceptors;
import javax.ws.rs.ext.Provider;
import java.util.logging.Level;
import java.util.logging.Logger;

@Provider
@Interceptors(CustomSpringBeanAutowiringInterceptor.class)
public class PermisoItineranteService {

    private Logger log = Logger.getLogger(this.getClass().getName());
    @Autowired
    private PermisoItineranteRepository repository;

    public PermisoItineranteCostoAdministrativo consultaTasasEntidadFinanciera(Long cveEntidadFinanciera, String periodo) throws BusinessException{
        try {
            return repository.consultaTasasEntidadFinanciera(cveEntidadFinanciera, periodo);
        }catch (Exception e){
            log.log(Level.SEVERE, "ERROR PermisoItineranteService.consultaTasasEntidadFinanciera()",e);
            throw new ConciliacionException(ConciliacionException.ERROR_AL_EJECUTAR_CONSULTA);
        }
    }

}
