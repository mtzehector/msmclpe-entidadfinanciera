/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.entidadfinancieraback.endpoint;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Level;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.transaction.Transactional;
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
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcCondicionOferta;
import mx.gob.imss.dpes.entidadfinancieraback.model.CondicionOfertaPersistenceRequest;
import mx.gob.imss.dpes.entidadfinancieraback.service.CondicionOfertaService;
import mx.gob.imss.dpes.support.config.CustomSpringBeanAutowiringInterceptor;

/**
 *
 * @author eduardo.loyo
 */
@Path("/condicion")
@Interceptors(CustomSpringBeanAutowiringInterceptor.class)
public class CondicionOfertaPersistenceEndPoint
        extends BaseGUIEndPoint<MclcCondicionOferta, MclcCondicionOferta, MclcCondicionOferta> {

    @Context
    private UriInfo uriInfo;

    @Inject
    private CondicionOfertaService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(CondicionOfertaPersistenceRequest request)
            throws BusinessException {
        log.log(Level.INFO,"CondicionesBack: {0}",request.getCondicionOferta());
        Collection<MclcCondicionOferta> condicionOferta = new ArrayList();
        Iterator<MclcCondicionOferta> iterator = request.getCondicionOferta().iterator();
        while (iterator.hasNext()) {
            MclcCondicionOferta current = iterator.next();
            log.log(Level.INFO,"Iterator: {0}", current);
            Message<MclcCondicionOferta> oferta = service.execute(new Message<>(current));
            log.info("cat:" + oferta);
            condicionOferta.add(oferta.getPayload());
        }
        return Response.ok(condicionOferta).build();
    }

}
