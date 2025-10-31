/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.entidadfinancieraback.endpoint;

import java.util.logging.Level;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import mx.gob.imss.dpes.common.endpoint.BaseGUIEndPoint;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.BaseModel;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcCondicionOfertaOldie;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcEntidadFinanciera;
import mx.gob.imss.dpes.entidadfinancieraback.service.ObtenerCondCapturaService;
import mx.gob.imss.dpes.support.config.CustomSpringBeanAutowiringInterceptor;
/**
 *
 * @author Diego Velazquez
 */
@ApplicationScoped
@Path("/plazosCondicion")
@Interceptors(CustomSpringBeanAutowiringInterceptor.class)
public class OfertaCondCapturaEndPoint extends
        BaseGUIEndPoint<BaseModel, MclcCondicionOfertaOldie, BaseModel> {

    @Context
    private UriInfo uriInfo;

    @Inject
    private ObtenerCondCapturaService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)

    public Response load(MclcEntidadFinanciera request) throws BusinessException {
            log.log(Level.INFO,"OfertaCondCapturaEndPoint BACK {0}",request.getId());
        return Response.ok(service.obtenerOfertaCondCaptura(request)).build();

    }
}
