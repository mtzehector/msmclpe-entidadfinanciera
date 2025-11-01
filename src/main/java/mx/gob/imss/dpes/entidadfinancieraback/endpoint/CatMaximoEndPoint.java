/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.entidadfinancieraback.endpoint;

import java.util.List;
import java.util.logging.Level;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import mx.gob.imss.dpes.common.endpoint.BaseGUIEndPoint;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.common.model.ServiceStatusEnum;
import mx.gob.imss.dpes.entidadfinancieraback.exception.CatMaximoException;
import mx.gob.imss.dpes.entidadfinancieraback.model.CatMaximoModel;
import mx.gob.imss.dpes.entidadfinancieraback.service.CatMaximoService;
import mx.gob.imss.dpes.entidadfinancieraback.service.ObtenerEntidadFinancieraService;

/**
 *
 * @author juanf.barragan
 */
@ApplicationScoped
@Path("/catMaximo")
public class CatMaximoEndPoint extends BaseGUIEndPoint<CatMaximoModel, CatMaximoModel, CatMaximoModel> {

    @Inject
    private CatMaximoService service;

    @Inject
    private ObtenerEntidadFinancieraService efService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(CatMaximoModel request) {
        try {
            Message<CatMaximoModel> response = service.actualizaCatalogo(new Message<>(request));
            return Response.ok(request).build();
        } catch (BusinessException e) {
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION, e, null));
        } catch (Exception e) {
            log.log(Level.SEVERE, "ERROR CatMaximoEndPoint.create ", e);
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION,
                    new CatMaximoException(CatMaximoException.ERROR_DESCONOCIDO_EN_EL_SERVICIO),
                    null));
        }
    }

    @GET
    @Path("/actual")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response read() {
        try {
            CatMaximoModel model = service.read();
            return Response.ok(model).build();
        } catch (BusinessException e) {
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION, e, null));
        } catch (Exception e) {
            log.log(Level.SEVERE, "ERROR CatMaximoEndPoint.read ", e);
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION,
                    new CatMaximoException(CatMaximoException.ERROR_DESCONOCIDO_EN_EL_SERVICIO),
                    null));
        }
    }

    @GET
    @Path("/AdminsEF")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response adminEF() {

        List<String> correos = efService.getCorreosAdmin();

        return Response.ok(correos).build();
    }
}
