package mx.gob.imss.dpes.entidadfinancieraback.endpoint;

import mx.gob.imss.dpes.common.endpoint.BaseGUIEndPoint;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.common.model.ServiceStatusEnum;
import mx.gob.imss.dpes.entidadfinancieraback.assembler.EstatusConciliacionAssembler;
import mx.gob.imss.dpes.entidadfinancieraback.assembler.EstatusConciliacionAssemblerReqToEntity;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcEstatusConciliacion;
import mx.gob.imss.dpes.entidadfinancieraback.exception.EstatusConciliacionException;
import mx.gob.imss.dpes.entidadfinancieraback.service.EstatusConciliacionService;
import mx.gob.imss.dpes.entidadfinancieraback.validation.ValidacionEstatusConciliacion;
import mx.gob.imss.dpes.interfaces.estatusConciliacion.model.EstatusConciliacionRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Level;

@ApplicationScoped
@Path("/conciliacion")
public class ConciliacionEndPoint extends BaseGUIEndPoint<EstatusConciliacionRequest, EstatusConciliacionRequest, EstatusConciliacionRequest> {

    @Inject
    private EstatusConciliacionService conciliacionService;
    @Inject
    private EstatusConciliacionAssembler conciliacionAssembler;
    @Inject
    private ValidacionEstatusConciliacion validaConciliacion;
    @Inject
    private EstatusConciliacionAssemblerReqToEntity estatusAssembler;

    @GET
    @Path("/estatusConciliacion/{periodo}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerEstatusConciliacionPorPeriodo(@PathParam("periodo") String periodo) throws BusinessException{
        try {
            MclcEstatusConciliacion estatusConciliacion = conciliacionService.obtenerEstatusConciliacionPorPeriodo(periodo);
            if (estatusConciliacion == null)
                return Response.noContent().build();

            return Response.ok(
                    conciliacionAssembler.assemble(estatusConciliacion)
            ).build();
        }catch (BusinessException e){
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION, e, null));
        } catch (Exception e){
            log.log(Level.SEVERE, "ERROR EstatusConciliacionEndPoint.obtenerEstatusConciliacionPorPeriodo()",e);
            return toResponse(new Message(
                    null,
                    ServiceStatusEnum.EXCEPCION,
                    new EstatusConciliacionException(EstatusConciliacionException.ERROR_DESCONOCIDO_EN_EL_SERVICIO),
                    null
            ));
        }
    }

    @POST
    @Path("/estatusConciliacion")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardarEstatusConciliacion(EstatusConciliacionRequest request) throws BusinessException{
        if (!validaConciliacion.isEstatusValido(request))
            return toResponse(
                    new Message(
                            null,
                            ServiceStatusEnum.EXCEPCION,
                            new EstatusConciliacionException(EstatusConciliacionException.MENSAJE_DE_SOLICITUD_INCORRECTO),
                            null
                    ));
        try {
            MclcEstatusConciliacion estatusGuardado = conciliacionService.guardarEstatusConciliacion(
                    estatusAssembler.assemble(request)
            );
            request.setId(estatusGuardado.getId());
            request.setPeriodo(estatusGuardado.getPeriodo());
            request.setActivo(estatusGuardado.getActivo());
            return toResponse(new Message<>(request));
        }catch (BusinessException e){
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION, e, null));
        }catch (Exception e){
            log.log(Level.SEVERE, "ERROR EstatusConciliacionEndPoint.guardarEstatusConciliacion()", e);
            return toResponse(new Message(
                    null,
                    ServiceStatusEnum.EXCEPCION,
                    new EstatusConciliacionException(EstatusConciliacionException.ERROR_DESCONOCIDO_EN_EL_SERVICIO),
                    null
            ));
        }
    }

}
