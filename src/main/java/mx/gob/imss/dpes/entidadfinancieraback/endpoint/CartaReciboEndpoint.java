/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.entidadfinancieraback.endpoint;

import java.util.List;
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
import mx.gob.imss.dpes.common.endpoint.BaseGUIEndPoint;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.entidadfinancieraback.entity.McltCartaRecibo;
import mx.gob.imss.dpes.entidadfinancieraback.model.CartaReciboRequest;
import mx.gob.imss.dpes.entidadfinancieraback.service.CartaReciboService;

/**
 *
 * @author juanf.barragan
 */

@ApplicationScoped
@Path("/cartaRecibo")
public class CartaReciboEndpoint extends BaseGUIEndPoint<CartaReciboRequest,CartaReciboRequest,CartaReciboRequest>{
    
    @Inject
    CartaReciboService service;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(McltCartaRecibo request) throws BusinessException {
       
      
        McltCartaRecibo response = service.guardarCarta(request);
        
        
        return Response.ok(response).build();
    }
    
    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response read(@PathParam("id") Long id) throws BusinessException{
    
         McltCartaRecibo model = service.recuperaCarta(id);
         
        return Response.ok(model).build();
    }
    
    @GET
    @Path("/{ef}/{periodo}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response read(@PathParam("ef") Long ef, @PathParam("periodo") String periodo) throws BusinessException{
    
         McltCartaRecibo model = service.validaPeriodo(ef, periodo);
         
        return Response.ok(model).build();
    }
    
    @POST
    @Path("/periodo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultaPeriodo(McltCartaRecibo request) throws BusinessException{
        
        List<McltCartaRecibo> lista = service.obtenPeriodo(request);
        
        return Response.ok(lista).build();
    }
}
