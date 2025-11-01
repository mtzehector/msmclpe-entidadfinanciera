/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.entidadfinancieraback.endpoint;

import java.util.logging.Level;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
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
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.BaseModel;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.entidadfinancieraback.entity.EntidadFinanciera;
import mx.gob.imss.dpes.entidadfinancieraback.service.ObtenerEntidadFinancieraService;
import mx.gob.imss.dpes.entidadfinancieraback.service.ObtenerRegistroPatronalEntidadFinancieraService;
import mx.gob.imss.dpes.entidadfinancieraback.service.RegistrosPatronalesServices;
import mx.gob.imss.dpes.entidadfinancieraback.entity.McltRegistroPatronal;

/**
 *
 * @author eduardo.loyo
 */
@RequestScoped
@Path("/obtenerentidadfinanciera")
public class ObtenerEntidadFinanciera extends
        BaseGUIEndPoint<BaseModel, BaseModel, BaseModel> {

    @Context
    private UriInfo uriInfo;
    @Inject
    private ObtenerRegistroPatronalEntidadFinancieraService service;
    @Inject
    private RegistrosPatronalesServices rpService;
    @Inject
    private ObtenerEntidadFinancieraService efService;

    @GET
    @Path("/{regPatronal}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response load(@PathParam("regPatronal") String regPatronal) throws BusinessException {
        EntidadFinanciera entidad = new EntidadFinanciera();
        entidad.setRegistroPatronal(regPatronal);

        Message<EntidadFinanciera> response = service.load(new Message<>(entidad));
        if (response.getPayload() == null) {
            return Response.noContent().build();
        }
        return Response.ok(response.getPayload()).build();

    }
    
    @GET
    @Path("/asociados/{regPatronal}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerByAsociados(@PathParam("regPatronal") String regPatronal) throws BusinessException {
        EntidadFinanciera entidad = new EntidadFinanciera();
        entidad.setRegistroPatronal(regPatronal);

        Message<EntidadFinanciera> response = service.load(new Message<>(entidad));
        if (response.getPayload() == null) {
            McltRegistroPatronal mcltRegistroPatronalrp = rpService.getbyRP(regPatronal);
            if(mcltRegistroPatronalrp != null){
                Message<EntidadFinanciera> responseef = efService.loadAso(mcltRegistroPatronalrp.getMclcEntidadFinanciera().toString());
                if(responseef.getPayload() == null){
                    return Response.noContent().build();
                }else{
                    return Response.ok(responseef.getPayload()).build();
                }
            }else{
                return Response.ok(response.getPayload()).build();
            }
            //return Response.noContent().build();
        }
        return Response.ok(response.getPayload()).build();

    }

    @POST
    @Path("/consultaAdmin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerInfoEFAdmin(EntidadFinanciera request) throws BusinessException {
        log.log(Level.INFO, ">>>>BACK ENDPOINT obtenerInfoEFAdmin REQUEST: {0}", request);
        Message<EntidadFinanciera> response = service.obtenerInfoEFAdmin(new Message<>(request));
        return Response.ok(response.getPayload()).build();
    }
}
