package mx.gob.imss.dpes.entidadfinancieraback.endpoint;

import mx.gob.imss.dpes.common.endpoint.BaseGUIEndPoint;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.BaseModel;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.common.model.ServiceStatusEnum;
import mx.gob.imss.dpes.entidadfinancieraback.exception.CargaInformacionException;
import mx.gob.imss.dpes.entidadfinancieraback.service.CargaInformacionConciliacionService;
import mx.gob.imss.dpes.interfaces.entidadfinanciera.model.CargaInformacionRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Level;


@ApplicationScoped
@Path("/conciliacion")
public class CargaInformacionConciliacionEndPoint extends BaseGUIEndPoint<BaseModel, BaseModel, BaseModel> {

    @Inject
    private CargaInformacionConciliacionService cargaInformacionService;


    @POST
    @Path("/cargaInformacion")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cargaInformacionResumen(CargaInformacionRequest request) throws BusinessException {
        try {
            if (request == null || (request.getPeriodo() == null || request.getPeriodo().isEmpty()) )
                throw new CargaInformacionException(CargaInformacionException.MENSAJE_DE_SOLICITUD_INCORRECTO);

            boolean resultado = cargaInformacionService.cargaInformacionResumen(request);
            if (resultado)
                return Response.ok(resultado).build();
            else
                throw new CargaInformacionException(CargaInformacionException.ERROR_AL_EJECUTAR_INSERT_UPDATE);
        }catch (BusinessException e){
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION, e, null));
        }catch (Exception e){
            log.log(Level.SEVERE, "ERROR ConciliacionCargaInformacionEndPoint.cargaInformacionResumen()",e);
            return toResponse(new Message(
                    null,
                    ServiceStatusEnum.EXCEPCION,
                    new CargaInformacionException(CargaInformacionException.ERROR_DESCONOCIDO_EN_EL_SERVICIO),
                    null
            ));
        }
    }

}
