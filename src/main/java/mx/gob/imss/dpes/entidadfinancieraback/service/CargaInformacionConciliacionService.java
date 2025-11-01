package mx.gob.imss.dpes.entidadfinancieraback.service;

import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.entidadfinancieraback.exception.CargaInformacionException;
import mx.gob.imss.dpes.entidadfinancieraback.repository.CargaInformacionConciliacionRepository;
import mx.gob.imss.dpes.interfaces.entidadfinanciera.model.CargaInformacionRequest;
import mx.gob.imss.dpes.support.config.CustomSpringBeanAutowiringInterceptor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.interceptor.Interceptors;
import javax.ws.rs.ext.Provider;
import java.util.logging.Level;
import java.util.logging.Logger;

@Provider
@Interceptors(CustomSpringBeanAutowiringInterceptor.class)
public class CargaInformacionConciliacionService {

    @Autowired
    private CargaInformacionConciliacionRepository repository;
    private Logger log = Logger.getLogger(this.getClass().getName());

    public boolean cargaInformacionResumen(CargaInformacionRequest request)throws BusinessException{
        Integer totalEliminados = null;
        Integer totalInserts = null;
        Integer totalPorcentajeCosto = null;
        Integer totalIvaCosto = null;
        //Integer totalPorcentajePermisoItinerante = null;
        Integer totalIvaPermisoItinerante = null;
        Integer totalNetoPagar = null;
        try {
            totalEliminados = repository.deleteResumenConciliacion(request.getPeriodo());

            if(totalEliminados != null)
                totalInserts = repository.insertsInResumenConcilicacion(request.getPeriodo());
            if (totalInserts != null)
                totalPorcentajeCosto = repository.updatePorcentajeCostoAdminAndPorcentajeCostoResumen(request.getPeriodo());
            if (totalPorcentajeCosto != null)
                totalIvaCosto = repository.updateIvaCostoAdminAndIvaCostoResumen(request.getPeriodo());
            if (totalIvaCosto != null)
                totalIvaPermisoItinerante = repository.porcentajeIvaPermisoItineranteCero(request.getPeriodo());
                //totalPorcentajePermisoItinerante = repository.updatePorcentajePermisoItineranteAndPorcentajeItineranteResumen(request.getPeriodo());
            //if (totalPorcentajePermisoItinerante != null)
                //totalIvaPermisoItinerante = repository.updateIvaPermisoItineranteAndIvaPermisoItineranteResumen(request.getPeriodo());
            if (totalIvaPermisoItinerante != null)
                totalNetoPagar = repository.updateNetoPagarAndNetoSinDescuentoFallecidosAndPagoReal(request.getPeriodo());
            if (totalNetoPagar != null)
                return true;

        }catch (Exception e){
            log.log(Level.SEVERE, "ERROR CargaInformacionConciliacionService.cargaInformacionResumen()",e);
            throw new CargaInformacionException(CargaInformacionException.ERROR_AL_EJECUTAR_INSERT_UPDATE);
        }
        return false;
    }


}
