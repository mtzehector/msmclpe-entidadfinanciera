package mx.gob.imss.dpes.entidadfinancieraback.endpoint;

import mx.gob.imss.dpes.common.endpoint.BaseGUIEndPoint;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.common.model.ServiceStatusEnum;
import mx.gob.imss.dpes.entidadfinancieraback.assembler.PrestadorServiciosEFAssembler;
import mx.gob.imss.dpes.entidadfinancieraback.assembler.PrestadorServiciosEFBeanFRomModelAssembler;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcPrestadorServiciosEF;
import mx.gob.imss.dpes.entidadfinancieraback.exception.PrestadorServiciosEFException;
import mx.gob.imss.dpes.entidadfinancieraback.service.PrestadorServiciosEFService;
import mx.gob.imss.dpes.entidadfinancieraback.validation.ValidacionEmail;
import mx.gob.imss.dpes.entidadfinancieraback.validation.ValidacionPrestadorServiciosEF;
import mx.gob.imss.dpes.interfaces.entidadfinanciera.model.PrestadorServiciosEF;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

@ApplicationScoped
@Path("/prestadorServicios")
public class PrestadorServiciosEFEndPoint extends BaseGUIEndPoint<PrestadorServiciosEF, PrestadorServiciosEF, PrestadorServiciosEF>{

    @Inject
    private ValidacionPrestadorServiciosEF validaPrestadorServiciosEF;

    @Inject
    private ValidacionEmail validaEmail;
    @Inject
    private PrestadorServiciosEFAssembler prestadorServiciosAssempler;
    @Inject
    private PrestadorServiciosEFBeanFRomModelAssembler beanFromModelAssembler;
    @Inject
    private PrestadorServiciosEFService prestadorServiciosEFService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearPrestadorServiciosEF(PrestadorServiciosEF request) throws BusinessException {
        try {
            if (!validaPrestadorServiciosEF.esPrestadorServiciosEFValido(request)){
                return toResponse(
                        new Message(
                                null,
                                ServiceStatusEnum.EXCEPCION,
                                new PrestadorServiciosEFException(PrestadorServiciosEFException.MENSAJE_DE_SOLICITUD_INCORRECTO),
                                null
                        ));
            }
            if (!validaEmail.esValidoEmail(request.getCorreoElectronico())){
                return toResponse(
                        new Message(
                                null,
                                ServiceStatusEnum.EXCEPCION,
                                new PrestadorServiciosEFException(PrestadorServiciosEFException.EMAIL_INCORRECTO),
                                null
                        ));
            }
            MclcPrestadorServiciosEF saved = prestadorServiciosEFService.crearPrestadorServiciosEF(
                    prestadorServiciosAssempler.assemble(request)
            );
            request.setId(saved.getId());
            return toResponse(new Message<>(request));
        }catch (BusinessException e){
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION, e, null));
        }catch (Exception e){
            log.log(Level.SEVERE, "ERROR PrestadorServiciosEFEndPoint.create()", e);
            return toResponse(new Message(
                    null,
                    ServiceStatusEnum.EXCEPCION,
                    new PrestadorServiciosEFException(PrestadorServiciosEFException.ERROR_DESCONOCIDO_EN_EL_SERVICIO),
                    null
            ));
        }

    }

    @GET
    @Path("/{idEntidadFinanciera}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerListaPrestadorServiciosEF(@PathParam("idEntidadFinanciera") Long cveEntidadFinanciera) {
        try {
            List<MclcPrestadorServiciosEF> mclcPrestadorServiciosEFList = prestadorServiciosEFService.obtenerListaPrestadorServiciosEF(cveEntidadFinanciera);
            if (mclcPrestadorServiciosEFList != null && !mclcPrestadorServiciosEFList.isEmpty()){
                return toResponse(new Message<>(
                        (Serializable) beanFromModelAssembler.assembleList(mclcPrestadorServiciosEFList)
                ));
            }else{
                return Response.noContent().build();
            }
        }catch (BusinessException e){
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION, e, null));
        }catch (Exception e){
            log.log(Level.SEVERE, "ERROR PrestadorServiciosEFEndPoint.getListPrestadorServiciosEF ", e);
            return toResponse(new Message(
                    null,
                    ServiceStatusEnum.EXCEPCION,
                    new PrestadorServiciosEFException(PrestadorServiciosEFException.ERROR_DESCONOCIDO_EN_EL_SERVICIO),
                    null
            ));
        }

    }

    @POST
    @Path("/guardarLista")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearPrestadoresServiciosEF(List<PrestadorServiciosEF> listaPrestadorServiciosEF) throws BusinessException {
        try {
            if (!validaPrestadorServiciosEF.sonPrestadoresServiciosEFValidos(listaPrestadorServiciosEF)){
                return toResponse(
                        new Message(
                                null,
                                ServiceStatusEnum.EXCEPCION,
                                new PrestadorServiciosEFException(PrestadorServiciosEFException.MENSAJE_DE_SOLICITUD_INCORRECTO),
                                null
                        ));
            }

            List<MclcPrestadorServiciosEF> listaGuardados = new ArrayList<>();
            for (PrestadorServiciosEF ps: listaPrestadorServiciosEF) {
                listaGuardados.add(
                        prestadorServiciosEFService.crearPrestadorServiciosEF(prestadorServiciosAssempler.assemble(ps)
                ));
            }
            return toResponse(new Message<>(
                    (Serializable) beanFromModelAssembler.assembleList(listaGuardados)
            ));
        }catch (BusinessException e){
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION, e, null));
        }catch (Exception e){
            log.log(Level.SEVERE, "ERROR PrestadorServiciosEFEndPoint.create()", e);
            return toResponse(new Message(
                    null,
                    ServiceStatusEnum.EXCEPCION,
                    new PrestadorServiciosEFException(PrestadorServiciosEFException.ERROR_DESCONOCIDO_EN_EL_SERVICIO),
                    null
            ));
        }
    }
}
