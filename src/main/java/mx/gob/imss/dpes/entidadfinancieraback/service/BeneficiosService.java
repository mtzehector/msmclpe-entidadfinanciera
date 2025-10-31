package mx.gob.imss.dpes.entidadfinancieraback.service;

import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcBeneficio;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcBeneficios;
import mx.gob.imss.dpes.entidadfinancieraback.exception.BeneficiosException;
import mx.gob.imss.dpes.entidadfinancieraback.repository.BeneficiosRepository;
import mx.gob.imss.dpes.support.config.CustomSpringBeanAutowiringInterceptor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.interceptor.Interceptors;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Provider
@Interceptors(CustomSpringBeanAutowiringInterceptor.class)
public class BeneficiosService {

   @Autowired
   private BeneficiosRepository repository;

   private Logger log = Logger.getLogger(this.getClass().getName());

    public List<MclcBeneficio> crearActualizarBeneficios(List<MclcBeneficio> listBeneficios) throws BusinessException {
        try {
            List<MclcBeneficio> lisBeneficiosActualizada = new ArrayList<>();
            for (MclcBeneficio request: listBeneficios) {
                if (request.getId() != null)
                    this.actualizarBeneficio(request);
                else
                    this.guardarBeneficio(request);

                lisBeneficiosActualizada.add(request);
            }
            return lisBeneficiosActualizada;
        }catch (Exception e){
            log.log(Level.SEVERE, "BeneficioService.crearActualizarBeneficios() - listBeneficios[" + listBeneficios + "]",e);
        }
        throw new BeneficiosException(BeneficiosException.ERROR_DE_ESCRITURA_EN_BD);
    }

    private void actualizarBeneficio(MclcBeneficio req) throws Exception {
        try {
            String descripcionRequest = req.getDesBeneficio() != null ? req.getDesBeneficio() : "";
            String descripcionBD = "";
            MclcBeneficios mclcBeneficio = repository.findOne(req.getId());
            if (mclcBeneficio != null &&
                    !(descripcionBD = mclcBeneficio.getDescripcion() != null ? mclcBeneficio.getDescripcion() : "").equals(descripcionRequest)) {

                mclcBeneficio.setDescripcion(req.getDesBeneficio());
                mclcBeneficio.setAltaRegistro(
                        mclcBeneficio.getAltaRegistro() == null?
                                new Date() :
                                mclcBeneficio.getAltaRegistro()
                );
                req.setAltaRegistro(mclcBeneficio.getAltaRegistro());
                mclcBeneficio.setActualizacionRegistro(new Date());
                repository.save(mclcBeneficio);
            }
        }catch (Exception e){
            throw e;
        }
    }

    private void guardarBeneficio(MclcBeneficio req) throws Exception {
        try {
            if (req != null) {
                MclcBeneficios mclcBeneficioNuevo = new MclcBeneficios();
                mclcBeneficioNuevo.setCondicionOfertaId(req.getMclcCondicionOferta().getId());
                mclcBeneficioNuevo.setDescripcion(req.getDesBeneficio());
                mclcBeneficioNuevo.setAltaRegistro(new Date());
                repository.save(mclcBeneficioNuevo);
                req.setId(mclcBeneficioNuevo.getId());
                req.setAltaRegistro(mclcBeneficioNuevo.getAltaRegistro());
            }
        }catch (Exception e){
            throw e;
        }
    }

}
