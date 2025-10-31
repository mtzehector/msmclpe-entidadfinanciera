/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.entidadfinancieraback.config;

import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method. It is automatically
     * populated with all resources defined in the project. If required, comment
     * out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(mx.gob.imss.dpes.common.exception.AlternateFlowMapper.class);
        resources.add(mx.gob.imss.dpes.common.exception.BusinessMapper.class);
        resources.add(mx.gob.imss.dpes.common.rule.MontoTotalRule.class);
        resources.add(mx.gob.imss.dpes.common.rule.PagoMensualRule.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.assembler.DocumentoAssembler.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.assembler.EntidadFinancieraAssembler.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.assembler.OfertaAssembler.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.assembler.OfertaCondCapturaAssembler.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.assembler.PrestadorServiciosEFAssembler.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.assembler.PrestadorServiciosEFBeanFRomModelAssembler.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.assembler.EstatusConciliacionAssembler.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.assembler.ResumenConciliacionAssembler.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.assembler.SolicitudTransferenciaConciliacionAssembler.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.assembler.RetencionPorDelegacionAssembler.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.assembler.PermisoItineranteAssembler.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.assembler.ImporteFallecidosAssembler.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.assembler.TramiteErogacionAssembler.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.endpoint.BeneficioEndPoint.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.endpoint.CartaReciboEndpoint.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.endpoint.CatMaximoEndPoint.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.endpoint.CondicionEntidadFederativaEndPoint.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.endpoint.CondicionOfertaEndPoint.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.endpoint.CondicionOfertaPersistenceEndPoint.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.endpoint.EntidadFinancieraBackEndPoint.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.endpoint.ObtenerEntidadFinanciera.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.endpoint.OfertaCondCapturaEndPoint.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.endpoint.OfertaEndPoint.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.endpoint.PlazosPorOfertaEndPoint.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.endpoint.RegistrosPatronalesEndpoint.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.endpoint.PrestadorServiciosEFEndPoint.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.endpoint.ConciliacionEndPoint.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.endpoint.ConciliacionReportesEndPoint.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.endpoint.CargaInformacionConciliacionEndPoint.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.rule.RNCalculaEdad.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.service.BeneficioService.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.service.BitacoraInterfazService.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.service.CartaReciboService.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.service.CatMaximoService.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.service.CondicionFederativaService.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.service.CondicionOfertaService.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.service.DocumentoPersistenceService.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.service.EntidadFinancieraPersistenceService.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.service.MclcBancosService.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.service.ObtenerCondCapturaService.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.service.ObtenerCondicionOfertaService.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.service.ObtenerEntidadFinancieraService.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.service.ObtenerIngresaCondicionesService.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.service.ObtenerOfertasService.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.service.ObtenerPlazosService.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.service.ObtenerRegistroPatronalEntidadFinancieraService.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.service.RegistrosPatronalesServices.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.service.PrestadorServiciosEFService.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.service.EstatusConciliacionService.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.service.RetencionPorDelegacionService.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.service.SolicitudTransferenciaService.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.service.ResumenConciliacionService.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.service.CargaInformacionConciliacionService.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.service.CartaReciboConciliacionService.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.service.PermisoItineranteService.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.service.TramiteErogacionService.class);
        resources.add(mx.gob.imss.dpes.entidadfinancieraback.service.BeneficiosService.class);
    }

}
