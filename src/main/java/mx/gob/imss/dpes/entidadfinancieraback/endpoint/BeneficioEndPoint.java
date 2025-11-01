/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.entidadfinancieraback.endpoint;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import mx.gob.imss.dpes.common.endpoint.BaseGUIEndPoint;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.common.model.ServiceStatusEnum;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcBeneficio;
import mx.gob.imss.dpes.entidadfinancieraback.exception.BeneficiosException;
import mx.gob.imss.dpes.entidadfinancieraback.model.BeneficioRequest;
import mx.gob.imss.dpes.entidadfinancieraback.service.BeneficiosService;

/**
 *
 * @author eduardo.loyo
 */
@ApplicationScoped
@Path("/beneficio")
public class BeneficioEndPoint extends BaseGUIEndPoint<BeneficioRequest, BeneficioRequest, BeneficioRequest> {

    @Inject
    private BeneficiosService beneficiosService;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(BeneficioRequest request) {
        try {
            if (request == null || request.getBeneficio() == null || request.getBeneficio().isEmpty())
                throw new BeneficiosException(BeneficiosException.MENSAJE_DE_SOLICITUD_INCORRECTO);

            List<MclcBeneficio> listBeneficios = beneficiosService.crearActualizarBeneficios(
                    new ArrayList(request.getBeneficio())
            );
            return Response.ok(listBeneficios).build();
        }catch (BusinessException e){
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION, e, null));
        }catch (Exception e) {
            log.log(Level.SEVERE, "BeneficioEndPoint.create()", e);
            return toResponse(new Message(
                    null,
                    ServiceStatusEnum.EXCEPCION,
                    new BeneficiosException(BeneficiosException.ERROR_DESCONOCIDO_EN_EL_SERVICIO),
                    null
            ));
        }
    }
    
}
