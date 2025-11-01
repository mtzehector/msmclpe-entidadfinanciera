/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.gob.imss.dpes.entidadfinancieraback.endpoint;


import java.util.logging.Level;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.interceptor.Interceptors;
import mx.gob.imss.dpes.common.endpoint.BaseGUIEndPoint;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.entidadfinancieraback.model.RegistroPatronalPersistenceRequest;
import mx.gob.imss.dpes.entidadfinancieraback.service.RegistrosPatronalesServices;
import mx.gob.imss.dpes.entidadfinancieraback.entity.McltRegistrosPatronales;
import mx.gob.imss.dpes.support.config.CustomSpringBeanAutowiringInterceptor;

/**
 *
 * @author juanf.barragan
 */

@Path("/registrospatronales")
@Interceptors(CustomSpringBeanAutowiringInterceptor.class)
public class RegistrosPatronalesEndpoint extends BaseGUIEndPoint<McltRegistrosPatronales, McltRegistrosPatronales, McltRegistrosPatronales> {

    @Inject
    private RegistrosPatronalesServices registrosPatronalesServices;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(RegistroPatronalPersistenceRequest request) throws BusinessException{
        log.log(Level.INFO,">>>entidadFinancieraBack RegistrosPatronalesEndpoint create request:{0}", request);
        
        
        return Response.ok(registrosPatronalesServices.execute(request)).build();
    }
    
    @GET
    @Path("/entidadFinanciera/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response recuperaRPbyEF(@PathParam("id") Long id) throws BusinessException{
        log.log(Level.INFO,">>>entidadFinancieraBack RegistrosPatronalesEndpoint recuperaRP id:{0}", id);
        return Response.ok(registrosPatronalesServices.recuperaRPbyEF(id)).build();
    }
}
