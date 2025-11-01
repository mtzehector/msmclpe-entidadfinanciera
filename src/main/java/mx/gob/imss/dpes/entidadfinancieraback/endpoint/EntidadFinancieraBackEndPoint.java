/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.entidadfinancieraback.endpoint;

import java.util.logging.Level;

import javax.enterprise.context.ApplicationScoped;
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
import mx.gob.imss.dpes.common.enums.TipoDocumentoEnum;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.exception.ResourceNotFoundException;
import mx.gob.imss.dpes.common.model.BaseModel;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.common.model.ServiceStatusEnum;
import mx.gob.imss.dpes.entidadfinancieraback.assembler.DocumentoAssembler;
import mx.gob.imss.dpes.entidadfinancieraback.assembler.PermisoItineranteAssembler;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcEntidadFinancieraPersistencia;
import mx.gob.imss.dpes.entidadfinancieraback.entity.McltDocumento;
import mx.gob.imss.dpes.entidadfinancieraback.exception.ConciliacionException;
import mx.gob.imss.dpes.entidadfinancieraback.exception.EntidadFinancieraException;
import mx.gob.imss.dpes.entidadfinancieraback.model.PermisoItineranteCostoAdministrativo;
import mx.gob.imss.dpes.entidadfinancieraback.service.DocumentoPersistenceService;
import mx.gob.imss.dpes.entidadfinancieraback.service.EntidadFinancieraPersistenceService;
import mx.gob.imss.dpes.entidadfinancieraback.service.ObtenerEntidadFinancieraService;
import mx.gob.imss.dpes.entidadfinancieraback.service.ObtenerIngresaCondicionesService;
import mx.gob.imss.dpes.entidadfinancieraback.service.CondicionOfertaService;
import mx.gob.imss.dpes.entidadfinancieraback.service.PermisoItineranteService;
import mx.gob.imss.dpes.interfaces.documento.model.Documento;
import mx.gob.imss.dpes.interfaces.documento.model.TipoDocumentoFront;
import mx.gob.imss.dpes.interfaces.entidadfinanciera.ot2.model.EntidadFinanciera;
import mx.gob.imss.dpes.entidadfinancieraback.service.MclcBancosService;
import mx.gob.imss.dpes.entidadfinancieraback.service.RegistrosPatronalesServices;
import mx.gob.imss.dpes.entidadfinancieraback.exception.CondicionOfertaException;

/**
 *
 * @author Diego Velazquez
 */
@ApplicationScoped
@Path("/entidadfinanciera")
public class EntidadFinancieraBackEndPoint extends BaseGUIEndPoint<BaseModel, EntidadFinanciera, BaseModel> {

    @Context
    private UriInfo uriInfo;

    @Inject
    private ObtenerEntidadFinancieraService service;
    @Inject
    private EntidadFinancieraPersistenceService persistence;
    @Inject
    private ObtenerIngresaCondicionesService obtenerIngresaCondicionesService;
    @Inject
    private DocumentoPersistenceService documentoService;
    @Inject
    private DocumentoAssembler documentoAssembler;
    @Inject
    private MclcBancosService bancosService;
    @Inject
    private RegistrosPatronalesServices rpServices;
    @Inject
    private CondicionOfertaService condicionOfertaService;
    @Inject
    private PermisoItineranteService itineranteService;
    @Inject
    private PermisoItineranteAssembler permisoItineranteAssembler;

    /*@PathParam("id") Long id*/
    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEntidadFinanciera(@PathParam("id") Long id) throws BusinessException {

        return Response.ok(uriInfo.getAbsolutePath()).entity(service.findOne(id)).build();

    }

    @GET
    @Path("/{id}/ot2")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findEntidadFinancieraById(@PathParam("id") Long id) throws BusinessException {
        try {
            EntidadFinanciera entidadFinanciera = obtenerIngresaCondicionesService.obtenerConCondicionesOfertaYBeneficios(id);
            McltDocumento mcltDocumento = documentoService.findByCveEntidadFinancieraAndTipoDocumento(entidadFinanciera.getId(), TipoDocumentoEnum.LOGO.getTipo());
            if (mcltDocumento != null) {
                Documento documento = documentoAssembler.assemble(mcltDocumento);
                documento.setTipoDocumento(TipoDocumentoEnum.LOGO);
                documento.setTipoDocumentoEnum(new TipoDocumentoFront(TipoDocumentoEnum.LOGO));
                entidadFinanciera.setLogo(documento);
            }
            entidadFinanciera.setDescBanco(bancosService.getByClave(entidadFinanciera.getInstBancaria()).get(0).getDescripcion());
            return Response.ok(entidadFinanciera).build();
        } catch (BusinessException e){
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION, e, null));
        }catch (Exception e){
            log.log(Level.SEVERE, "EntidadFinancieraBackEndPoint.findEntidadFinancieraById() - id[ " + id + " ]", e);
            return toResponse(new Message(
                    null,
                    ServiceStatusEnum.EXCEPCION,
                    new EntidadFinancieraException(EntidadFinancieraException.ERROR_DESCONOCIDO_EN_EL_SERVICIO),
                    null
            ));
        }
    }

    @POST
    @Path("/logo/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogo(@PathParam("id") Long id) throws BusinessException {
        log.log(Level.SEVERE, ">>>EntidadFinancieraBackEndPoint.getLogo id={0}", id);

        try {
            Documento documento = null;
            McltDocumento mcltDocumento = documentoService.findByCveEntidadFinancieraAndTipoDocumento(id, TipoDocumentoEnum.LOGO.getTipo());
            log.log(Level.SEVERE, ">>>EntidadFinancieraBackEndPoint.getLogo mcltDocumento=={0}", mcltDocumento);
            if (mcltDocumento != null) {
                documento = documentoAssembler.assemble(mcltDocumento);
                documento.setTipoDocumento(TipoDocumentoEnum.LOGO);
                documento.setTipoDocumentoEnum(new TipoDocumentoFront(TipoDocumentoEnum.LOGO));
                return toResponse(new Message(documento));
            } 
            else {
                throw new ResourceNotFoundException();
            }


        } catch (Exception e) {
            log.log(Level.SEVERE, ">>>EntidadFinancieraBackEndPoint.getLogo No fue posible obtener logo", e);
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(MclcEntidadFinancieraPersistencia financiera) throws BusinessException {
        Message<MclcEntidadFinancieraPersistencia> execute
                = persistence.execute(new Message<>(financiera));
        return toResponse(execute);
    }

    @GET
    @Path("/efByCveSipre/{cveSipreEf}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEntidadFinancieraByCveSipre(@PathParam("cveSipreEf") String cveSipreEf) throws BusinessException {

        log.log(Level.INFO, ">>>Clave Sipre : ", cveSipreEf);

        return Response.ok(
                service.load(cveSipreEf).getPayload()
        ).build();

    }
    
    @GET
    @Path("/RegistrosPatronales/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRegistrosPatronales(@PathParam("id") Long idEF) throws BusinessException{
        log.log(Level.INFO, "getRegistrosPatronales : {0}", idEF);
        return Response.ok(rpServices.listaRegistrosPatronales(idEF, service.registroPatronalPadre(idEF))).build();
    }

    @GET
    @Path("/{id}/ofertas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findOfertasByIdEntidadFinanciera(@PathParam("id") Long idEntidadFinanciera) {
        try {
            return Response.ok(condicionOfertaService.
                obtieneCondicionesPorIdEntidadFinanciera(idEntidadFinanciera)).build();
        }
        catch (CondicionOfertaException e) {
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION, e, null));
        }
        catch (Exception e) {
            log.log(Level.SEVERE, "ERROR EntidadFinancieraBackEndPoint.findOfertasByIdEntidadFinanciera {0}", e);
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION,
                    new CondicionOfertaException(CondicionOfertaException.ERROR_DESCONOCIDO_EN_EL_SERVICIO),
                    null));
        }
    }

    @GET
    @Path("/tasas/{cveEntidadFinanciera}/{periodo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerTasasPorEntidadFinancieraPeriodo(
            @PathParam("cveEntidadFinanciera") Long cveEntidadFinanciera,
            @PathParam("periodo") String periodo
    ) {
        try {
            PermisoItineranteCostoAdministrativo permisoItinerante = itineranteService.consultaTasasEntidadFinanciera(
                    cveEntidadFinanciera, periodo);

            if (permisoItinerante == null)
                return Response.noContent().build();

            return Response.ok(
                    permisoItineranteAssembler.assemble(permisoItinerante)
            ).build();
        } catch (BusinessException e) {
            log.log(Level.SEVERE,
                "ERROR EntidadFinancieraBackEndPoint.obtenerTasasPorEntidadFinancieraPeriodo() - " +
                "cveEntidadFinanciera = [" + cveEntidadFinanciera + "], periodo = [" + periodo + "]", e);
            return toResponse(new Message<>(null, ServiceStatusEnum.EXCEPCION, e, null));
        } catch(Exception e) {
            log.log(Level.SEVERE,
                "ERROR EntidadFinancieraBackEndPoint.obtenerTasasPorEntidadFinancieraPeriodo() - " +
                "cveEntidadFinanciera = [" + cveEntidadFinanciera + "], periodo = [" + periodo + "]", e);
        }

        return toResponse(
                new Message<>(
                        null,
                        ServiceStatusEnum.EXCEPCION,
                        new ConciliacionException(ConciliacionException.ERROR_DESCONOCIDO_EN_EL_SERVICIO),
                        null
                ));

    }

}
