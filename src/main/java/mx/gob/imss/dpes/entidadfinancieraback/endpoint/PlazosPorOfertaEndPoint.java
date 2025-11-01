/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.entidadfinancieraback.endpoint;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;
//import java.util.logging.Level;
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
import mx.gob.imss.dpes.common.enums.TipoServicioEnum;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.common.model.ServiceStatusEnum;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcPlazo;
import mx.gob.imss.dpes.entidadfinancieraback.exception.PlazosException;
import mx.gob.imss.dpes.entidadfinancieraback.model.PlazosRequest;
import mx.gob.imss.dpes.entidadfinancieraback.service.BitacoraInterfazService;
import mx.gob.imss.dpes.entidadfinancieraback.service.ObtenerPlazosService;
import mx.gob.imss.dpes.interfaces.bitacora.model.BitacoraInterfaz;
import mx.gob.imss.dpes.interfaces.entidadfinanciera.model.Plazo;
import mx.gob.imss.dpes.support.config.CustomSpringBeanAutowiringInterceptor;

/**
 * @author Diego Velazquez
 */
@ApplicationScoped
@Path("/plazosOferta")
@Interceptors(CustomSpringBeanAutowiringInterceptor.class)
public class PlazosPorOfertaEndPoint extends
        BaseGUIEndPoint<MclcPlazo, MclcPlazo, MclcPlazo> {

    @Context
    private UriInfo uriInfo;

    @Inject
    private ObtenerPlazosService service;

    @Inject
    private BitacoraInterfazService bitacoraInterfazService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response load(PlazosRequest request) {

        Response response = null;
        Long tiempoInicial = 0L;
        Long tiempoEjecucion = 0L;
        boolean exito = false;
        List<Plazo> lista = null;

        try {
            tiempoInicial = new Date().getTime();

            lista = service.obtenerOfertas(request);

            if (lista.size() > 0)
                response = Response.ok(lista).build();
            else
                response = Response.status(Response.Status.NO_CONTENT).entity(new PlazosException()).build();

            tiempoEjecucion = new Date().getTime() - tiempoInicial;
            exito = true;
        } catch (BusinessException be) {
            tiempoEjecucion = new Date().getTime() - tiempoInicial;
            response = toResponse(new Message(null, ServiceStatusEnum.EXCEPCION, be, null));
        } catch (Exception e) {
            tiempoEjecucion = new Date().getTime() - tiempoInicial;
            log.log(Level.SEVERE, "ERROR PlazosPorOfertaEndPoint.load request = [" + request + "]", e);
            response = toResponse(new Message(null, ServiceStatusEnum.EXCEPCION,
                    new PlazosException(PlazosException.ERROR_DESCONOCIDO_EN_EL_SERVICIO),
                    null));
        } finally {
            BitacoraInterfaz bitacoraInterfaz = new BitacoraInterfaz();
            bitacoraInterfaz.setIdTipoServicio(TipoServicioEnum.SIPRE2.getId());
            bitacoraInterfaz.setExito(exito ? 1 : 0);
            bitacoraInterfaz.setSesion(request.getSesion());
            bitacoraInterfaz.setEndpoint("/entidadFinancieraBack/webresources/plazosOferta");
            bitacoraInterfaz.setDescRequest(request.toString());
            bitacoraInterfaz.setReponseEndpoint(exito ? lista.toString() : null);
            bitacoraInterfaz.setNumTiempoResp(tiempoEjecucion);

            try {
                bitacoraInterfazService.execute(new Message<>(bitacoraInterfaz));
            } catch (BusinessException bei) {
                //Este escenario nunca debería pasar
                log.log(Level.SEVERE,
                        "ERROR PlazosPorOfertaEndPoint.load error al guardar la bitacoraInterfaz = [" +
                                bitacoraInterfaz + "]", bei);
            }
        }

        return response;
    }
}
