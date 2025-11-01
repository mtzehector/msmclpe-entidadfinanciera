/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.entidadfinancieraback.endpoint;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
//import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
//import javax.ws.rs.core.UriInfo;
import mx.gob.imss.dpes.common.endpoint.BaseGUIEndPoint;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.common.model.ServiceStatusEnum;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcCondicionOfertaOldie;
import mx.gob.imss.dpes.entidadfinancieraback.exception.CondicionOfertaException;
import mx.gob.imss.dpes.entidadfinancieraback.model.CondicionOfertaRequest;
import mx.gob.imss.dpes.interfaces.entidadfinanciera.model.Oferta;
import mx.gob.imss.dpes.entidadfinancieraback.service.ObtenerCondicionOfertaService;
import mx.gob.imss.dpes.support.config.CustomSpringBeanAutowiringInterceptor;

import java.util.logging.Level;

/**
 *
 * @author Diego Velazquez
 */
@ApplicationScoped
@Path("/condicionOferta")
@Interceptors(CustomSpringBeanAutowiringInterceptor.class)
public class CondicionOfertaEndPoint extends
        BaseGUIEndPoint<MclcCondicionOfertaOldie, MclcCondicionOfertaOldie, MclcCondicionOfertaOldie> {

    //@Context
    //private UriInfo uriInfo;

    @Inject
    private ObtenerCondicionOfertaService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response load(CondicionOfertaRequest request) {
        try {
            Oferta oferta = service.obtenerOferta(request.getClave());
            //log.info("cat:" + oferta.getCat());
            return Response.ok(oferta).build();
        } catch (BusinessException e) {
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION, e, null));
        } catch (Exception e) {
            log.log(Level.SEVERE, "ERROR CondicionOfertaEndPoint.load = {0}", e);
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION,
                new CondicionOfertaException(CondicionOfertaException.ERROR_DESCONOCIDO_EN_EL_SERVICIO),
                null));
        }
    }
}
