package mx.gob.imss.dpes.entidadfinancieraback.endpoint;

import mx.gob.imss.dpes.common.constants.Constantes;
import mx.gob.imss.dpes.common.endpoint.BaseGUIEndPoint;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.BaseModel;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.common.model.PageRequestModel;
import mx.gob.imss.dpes.common.model.ServiceStatusEnum;
import mx.gob.imss.dpes.entidadfinancieraback.assembler.ImporteFallecidosAssembler;
import mx.gob.imss.dpes.entidadfinancieraback.assembler.ResumenConciliacionAssembler;
import mx.gob.imss.dpes.entidadfinancieraback.assembler.RetencionPorDelegacionAssembler;
import mx.gob.imss.dpes.entidadfinancieraback.assembler.SolicitudTransferenciaConciliacionAssembler;
import mx.gob.imss.dpes.entidadfinancieraback.assembler.TramiteErogacionAssembler;
import mx.gob.imss.dpes.entidadfinancieraback.exception.ConciliacionException;
import mx.gob.imss.dpes.entidadfinancieraback.model.ImporteFallecidos;
import mx.gob.imss.dpes.entidadfinancieraback.model.ReporteResumenConciliacion;
import mx.gob.imss.dpes.entidadfinancieraback.model.ReporteRetencionPorDelegacion;
import mx.gob.imss.dpes.entidadfinancieraback.model.SolicitudTransferenciaConciliacion;
import mx.gob.imss.dpes.entidadfinancieraback.model.TramiteErogacion;
import mx.gob.imss.dpes.entidadfinancieraback.service.CartaReciboConciliacionService;
import mx.gob.imss.dpes.entidadfinancieraback.service.ResumenConciliacionService;
import mx.gob.imss.dpes.entidadfinancieraback.service.RetencionPorDelegacionService;
import mx.gob.imss.dpes.entidadfinancieraback.service.SolicitudTransferenciaService;
import mx.gob.imss.dpes.entidadfinancieraback.service.TramiteErogacionService;
import mx.gob.imss.dpes.entidadfinancieraback.util.PaginationUtil;
import mx.gob.imss.dpes.entidadfinancieraback.validation.ValidaConciliacionRequest;
import mx.gob.imss.dpes.interfaces.entidadfinanciera.model.CartaRecibo;
import mx.gob.imss.dpes.interfaces.entidadfinanciera.model.ConciliacionRequest;
import mx.gob.imss.dpes.interfaces.entidadfinanciera.model.TramiteErogacionConciliacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;

@ApplicationScoped
@Path("/conciliacion")
public class ConciliacionReportesEndPoint extends BaseGUIEndPoint<BaseModel, BaseModel, BaseModel> {

    @Inject
    private ValidaConciliacionRequest validar;
    @Inject
    private CartaReciboConciliacionService cartaReciboService;
    @Inject
    private RetencionPorDelegacionService retencionPorDelegacionService;
    @Inject
    private SolicitudTransferenciaService transferenciaService;
    @Inject
    private ResumenConciliacionAssembler resumenAssembler;
    @Inject
    private ResumenConciliacionService resumenService;
    @Inject
    private TramiteErogacionService erogacionService;
    @Inject
    private TramiteErogacionAssembler erogacionAssembler;
    @Inject
    private SolicitudTransferenciaConciliacionAssembler solicitudAssembler;
    @Inject
    private RetencionPorDelegacionAssembler delegacionAssembler;
    @Inject
    private ImporteFallecidosAssembler importeFallecidosAssembler;

    @POST
    @Path("/cartaRecibo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerDatosCartaRecibo(ConciliacionRequest request) throws BusinessException {
        try {
            if (!validar.validaEntidadSipreYPeriodo(request))
                throw new ConciliacionException(ConciliacionException.MENSAJE_DE_SOLICITUD_INCORRECTO);

            CartaRecibo cartaRecibo = cartaReciboService.obtenerDatosCartaRecibo(
                    request.getCveEntidadFinancieraSipre(),
                    request.getPeriodo()
            );

            if (cartaRecibo == null)
                return Response.noContent().build();

            return Response.ok(cartaRecibo).build();
        }catch (BusinessException e){
            return toResponse(new Message<>(null, ServiceStatusEnum.EXCEPCION, e, null));
        }catch(Exception e){
            log.log(Level.SEVERE, "ERROR ConciliacionReportesEndPoint.obtenerDatosCartaRecibo()", e);
        }
        return toResponse(
                new Message<>(
                        null,
                        ServiceStatusEnum.EXCEPCION,
                        new ConciliacionException(ConciliacionException.ERROR_DESCONOCIDO_EN_EL_SERVICIO),
                        null
                ));
    }

    @POST
    @Path("/resumenConciliacion")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerDatosResumenConciliacion(ConciliacionRequest request) throws BusinessException {
        try {
            if (!validar.validaPeriodo(request))
                throw new ConciliacionException(ConciliacionException.MENSAJE_DE_SOLICITUD_INCORRECTO);

            List<ReporteResumenConciliacion> listResumen = resumenService.obtenerDatosResumenConciliacion(request);
            if (listResumen == null || listResumen.isEmpty())
                return Response.noContent().build();

            return Response.ok(resumenAssembler.assembleList(listResumen)).build();
        }catch (BusinessException e){
            return toResponse(new Message<>(null, ServiceStatusEnum.EXCEPCION, e, null));
        }catch(Exception e){
            log.log(Level.SEVERE, "ERROR ConciliacionReportesEndPoint.obtenerDatosResumenConciliacion()", e);
        }
        return toResponse(
                new Message<>(
                        null,
                        ServiceStatusEnum.EXCEPCION,
                        new ConciliacionException(ConciliacionException.ERROR_DESCONOCIDO_EN_EL_SERVICIO),
                        null
                ));
    }

    @POST
    @Path("/tramiteErogacion")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerListTramiteErogacion(PageRequestModel<ConciliacionRequest> request) throws BusinessException {
        try {
            if (!(
                request != null && request.getModel() != null &&
                request.getModel().getPeriodo() != null && !request.getModel().getPeriodo().trim().isEmpty() &&
                request.getModel().getCveEntidadFinanciera() != null && request.getModel().getCveEntidadFinanciera() >= 0
            ))
                throw new ConciliacionException(ConciliacionException.MENSAJE_DE_SOLICITUD_INCORRECTO);

            List<TramiteErogacion> listaErogacion = erogacionService.obtenerListTramiteErogacion(
                    request.getModel().getPeriodo(),
                    request.getModel().getCveEntidadFinanciera()
            );
            if (listaErogacion == null || listaErogacion.isEmpty())
                return Response.noContent().build();

            Pageable pageable = new PageRequest((request.getPage() - 1), Constantes.DIEZ);
            Page<TramiteErogacionConciliacion> page = PaginationUtil.paginateList(
                    pageable,
                    erogacionAssembler.assembleList(listaErogacion)
            );
            return Response.ok(page).build();
        }catch (BusinessException e){
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION, e, null));
        }catch (Exception e){
            log.log(Level.SEVERE, "ERROR ConciliacionReportesEndPoint.obtenerListTramiteErogacion() ", e);
        }
        return toResponse(new Message(
                null,
                ServiceStatusEnum.EXCEPCION,
                new ConciliacionException(ConciliacionException.ERROR_DESCONOCIDO_EN_EL_SERVICIO),
                null
        ));
    }
    @POST
    @Path("/solicitudTransferencia")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerDatosReporteSolicitudTransferencia(ConciliacionRequest request) throws BusinessException {
        try {
            if (!validar.validaPeriodoYEntidades(request))
                throw new ConciliacionException(ConciliacionException.MENSAJE_DE_SOLICITUD_INCORRECTO);

            SolicitudTransferenciaConciliacion totales = transferenciaService.obtenerDatosReporte(request);
            if (totales == null)
                return Response.noContent().build();

            return Response.ok(solicitudAssembler.assemble(totales)).build();
        }catch (BusinessException e){
            return toResponse(new Message<>(null, ServiceStatusEnum.EXCEPCION, e, null));
        }catch(Exception e){
            log.log(Level.SEVERE, "ERROR ConciliacionReportesEndPoint.obtenerDatosReporteSolicitudTransferencia()", e);
        }
        return toResponse(
                new Message<>(
                        null,
                        ServiceStatusEnum.EXCEPCION,
                        new ConciliacionException(ConciliacionException.ERROR_DESCONOCIDO_EN_EL_SERVICIO),
                        null
                ));
    }

    @POST
    @Path("/reportePorDelegacion")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerDatosReportePorDelegacion(ConciliacionRequest request){
        try {
            if (!validar.validaRequest(request))
                throw new ConciliacionException(ConciliacionException.MENSAJE_DE_SOLICITUD_INCORRECTO);

            List<ReporteRetencionPorDelegacion> lista = retencionPorDelegacionService.obtenerRetencionPorDelegacion(request);
            if (lista == null || lista.isEmpty())
                return Response.noContent().build();

            return Response.ok(
                    delegacionAssembler.assembleList(lista)
            ).build();
        }catch (BusinessException e){
            return toResponse(new Message<>(null, ServiceStatusEnum.EXCEPCION, e, null));
        }catch(Exception e){
            log.log(Level.SEVERE, "ERROR ConciliacionReportesEndPoint.obtenerDatosReportePorDelegacion()", e);
        }
        return toResponse(
                new Message<>(
                        null,
                        ServiceStatusEnum.EXCEPCION,
                        new ConciliacionException(ConciliacionException.ERROR_DESCONOCIDO_EN_EL_SERVICIO),
                        null
                ));
    }

    @POST
    @Path("/obtenerImporteFallecidos")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerImporteFallecidos(ConciliacionRequest request){
        try {
            if (!validar.validaRequest(request))
                throw new ConciliacionException(ConciliacionException.MENSAJE_DE_SOLICITUD_INCORRECTO);

            ImporteFallecidos importeFallecidos = retencionPorDelegacionService.obtenerImporteFallecidos(request);
            if (importeFallecidos == null)
                return Response.noContent().build();

            return Response.ok(
                    importeFallecidosAssembler.assemble(importeFallecidos)
            ).build();
        }catch (BusinessException e){
            return toResponse(new Message<>(null, ServiceStatusEnum.EXCEPCION, e, null));
        }catch(Exception e){
            log.log(Level.SEVERE, "ERROR ConciliacionReportesEndPoint.obtenerImporteFallecidos()", e);
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
