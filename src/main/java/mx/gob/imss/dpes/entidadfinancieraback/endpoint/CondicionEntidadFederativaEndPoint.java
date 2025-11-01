/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.entidadfinancieraback.endpoint;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.TransactionRequiredException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import mx.gob.imss.dpes.common.endpoint.BaseGUIEndPoint;
//import static mx.gob.imss.dpes.common.entity.LogicDeletedEntity_.id;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.interfaces.entidadfinanciera.model.Delegacion;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcCondicionEntfed;
import mx.gob.imss.dpes.entidadfinancieraback.model.CondicionFederativaRequest;
import mx.gob.imss.dpes.entidadfinancieraback.service.CondicionFederativaService;
import mx.gob.imss.dpes.interfaces.entidadfinanciera.model.EntidadFinanciera;

/**
 *
 * @author eduardo.loyo
 */
@ApplicationScoped
@Path("/condicionfederativa")
public class CondicionEntidadFederativaEndPoint
        extends BaseGUIEndPoint<CondicionFederativaRequest, CondicionFederativaRequest, CondicionFederativaRequest> {

    @Context
    private UriInfo uriInfo;

    @Inject
    private CondicionFederativaService service;
   

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(CondicionFederativaRequest request)
            throws BusinessException,TransactionRequiredException {
        log.log(Level.INFO, ">>> entidadFinancieraBack CondicionEntidadFederativaEndPoint Request Condicion Entidad Federativa={0}", request.getCondicionFederativa());
        Collection<MclcCondicionEntfed> condicionFederativa = new ArrayList();
        Iterator<MclcCondicionEntfed> iterator = request.getCondicionFederativa().iterator();
        boolean primeraVez = true;
        int i=-1;
        while (iterator.hasNext()) {
            i++;
           MclcCondicionEntfed requestEnvio =iterator.next();
            if(primeraVez){
                service.del(requestEnvio.getMclcEntidadFinanciera());
                primeraVez=false;
            }
            Message<MclcCondicionEntfed> oferta = service.execute(new Message<>(requestEnvio));
            log.info("      >>> entidadFinancieraBack ["+i+"] CondicionEntidadFederativaEndPoint condicion entidad federativa:" + oferta);
            condicionFederativa.add(oferta.getPayload());
        }
        return Response.ok(condicionFederativa).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/delegaciones")
    public Response findDelegaciones(EntidadFinanciera entidadFinanciera)  throws BusinessException,TransactionRequiredException {
        //log.log(Level.INFO, ">>> entidadFinancieraBack CondicionEntidadFederativaEndPoint findDelegaciones id={0}", id);
        LinkedList<Delegacion> delegacionesLista = service.findDelegaciones(entidadFinanciera.getId());
        log.log(Level.INFO, ">>> entidadFinancieraBack CondicionEntidadFederativaEndPoint delegacionesLista={0}", delegacionesLista);
        
        return toResponse(new Message<>(delegacionesLista));
        
    }
    
    
}
