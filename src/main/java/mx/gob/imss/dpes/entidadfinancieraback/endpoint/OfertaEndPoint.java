/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.entidadfinancieraback.endpoint;

import java.util.List;
import java.util.logging.Level;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import mx.gob.imss.dpes.common.endpoint.BaseGUIEndPoint;
import mx.gob.imss.dpes.common.exception.AlternateFlowException;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.exception.PartialContentFlowException;
import mx.gob.imss.dpes.common.exception.VariableMessageException;
import mx.gob.imss.dpes.common.model.BaseModel;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.common.model.PageRequestModel;
import mx.gob.imss.dpes.common.model.ServiceStatusEnum;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcCondicionOfertaOldie;
import mx.gob.imss.dpes.entidadfinancieraback.exception.CatMaximoException;
import mx.gob.imss.dpes.entidadfinancieraback.exception.CondicionOfertaException;
import mx.gob.imss.dpes.entidadfinancieraback.model.OfertasRequest;
import mx.gob.imss.dpes.entidadfinancieraback.service.ObtenerOfertasService;
import mx.gob.imss.dpes.interfaces.entidadfinanciera.model.Oferta;

/**
 * @author Diego Velazquez
 */
@RequestScoped
@Path("/oferta")
public class OfertaEndPoint extends BaseGUIEndPoint<BaseModel, MclcCondicionOfertaOldie, BaseModel> {

    @Context
    private UriInfo uriInfo;

    @Inject
    private ObtenerOfertasService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)

    public Response load(PageRequestModel<OfertasRequest> request) {
        //throws BusinessException, AlternateFlowException {
        try {
            return Response.ok(service.obtenerOfertas(request)).build();
        } catch (AlternateFlowException e) {
            return toResponse(new Message(null, ServiceStatusEnum.PARTIAL_CONTENT,
                new PartialContentFlowException(e.getMessage()), null));
        } catch (BusinessException e) {
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION, e, null));
        } catch (Exception e) {
            log.log(Level.SEVERE, "ERROR OfertaEndPoint.load ", e);
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION,
                    new CondicionOfertaException(CondicionOfertaException.ERROR_DESCONOCIDO_EN_EL_SERVICIO),
                    null));
        }
    }

    @GET
    @Path("/{id}")
    public Response load(@PathParam("id") Long id) {
        try {
            return Response.ok(service.ofertaById(id)).build();
        } catch (Exception e) {
            log.log(Level.SEVERE, "OfertaEndPoint.load - No fue posible obtener la oferta ", e);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/correo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loadOfertas(PageRequestModel<OfertasRequest> request) {
            //throws BusinessException, AlternateFlowException {
        try {
            List<Oferta> ofertas = service.obtenerOfertas(request).getContent();
            return Response.ok(ofertas).build();
        } catch (BusinessException e) {
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION, e, null));
        } catch (Exception e) {
            log.log(Level.SEVERE, "ERROR OfertaEndPoint.loadOfertas ", e);
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION,
                    new CondicionOfertaException(CondicionOfertaException.ERROR_DESCONOCIDO_EN_EL_SERVICIO),
                    null));
        }
    }

}
