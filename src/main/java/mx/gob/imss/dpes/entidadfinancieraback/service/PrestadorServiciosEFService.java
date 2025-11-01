package mx.gob.imss.dpes.entidadfinancieraback.service;

import mx.gob.imss.dpes.baseback.service.BaseCRUDService;
import mx.gob.imss.dpes.common.entity.LogicDeletedEntity;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcPrestadorServiciosEF;
import mx.gob.imss.dpes.entidadfinancieraback.exception.PrestadorServiciosEFException;
import mx.gob.imss.dpes.entidadfinancieraback.repository.PrestadorServiciosEFRepository;
import mx.gob.imss.dpes.support.config.CustomSpringBeanAutowiringInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.interceptor.Interceptors;
import javax.ws.rs.ext.Provider;
import java.util.List;
import java.util.logging.Level;

@Provider
@Interceptors(CustomSpringBeanAutowiringInterceptor.class)
public class PrestadorServiciosEFService extends BaseCRUDService<MclcPrestadorServiciosEF, MclcPrestadorServiciosEF, Long, Long>{

    @Autowired
    private PrestadorServiciosEFRepository repository;

    @Override
    public JpaSpecificationExecutor<MclcPrestadorServiciosEF> getRepository() {
        return repository;
    }

    @Override
    public JpaRepository<MclcPrestadorServiciosEF, Long> getJpaRepository() {
        return repository;
    }

    public MclcPrestadorServiciosEF crearPrestadorServiciosEF(MclcPrestadorServiciosEF request) throws BusinessException {
        try{
            if (request.getBajaRegistro() != null){
                delete(request.getId());
                return request;
            }else{
                MclcPrestadorServiciosEF prestadorServiciosEF = save(request);
                prestadorServiciosEF.setBajaRegistro(((LogicDeletedEntity) prestadorServiciosEF).getBajaRegistro());
                return prestadorServiciosEF;
            }
        }catch(Exception e){
            log.log(Level.SEVERE, "ERROR PrestadorServiciosEFService.crearPrestadorServiciosEF()",e);
            throw new PrestadorServiciosEFException(PrestadorServiciosEFException.ERROR_DE_ESCRITURA_EN_BD);
        }
    }

    public List<MclcPrestadorServiciosEF> obtenerListaPrestadorServiciosEF(Long cveEntidadFinanciera) throws BusinessException{
        try {
            List<MclcPrestadorServiciosEF> lista = repository.buscarPorCveEntidadFinanciera(cveEntidadFinanciera);
            for (MclcPrestadorServiciosEF ps: lista) {
                ps.setBajaRegistro(((LogicDeletedEntity) ps).getBajaRegistro());
            }
            return lista;
        }catch (Exception e){
            log.log(Level.SEVERE, "ERROR PrestadorServiciosEFService.getPrestadorServiciosEF()",e);
            throw new PrestadorServiciosEFException(PrestadorServiciosEFException.ERROR_DE_LECTURA_DE_BD);
        }

    }
}
