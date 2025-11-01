package mx.gob.imss.dpes.entidadfinancieraback.assembler;

import mx.gob.imss.dpes.common.assembler.BaseAssembler;
import mx.gob.imss.dpes.entidadfinancieraback.model.ReporteResumenConciliacion;
import mx.gob.imss.dpes.interfaces.entidadfinanciera.model.ResumenConciliacion;

import javax.ws.rs.ext.Provider;

@Provider
public class ResumenConciliacionAssembler extends BaseAssembler<ReporteResumenConciliacion, ResumenConciliacion> {

    @Override
    public ResumenConciliacion assemble(ReporteResumenConciliacion source) {
        if (source == null)
            return null;

        ResumenConciliacion resumen = new ResumenConciliacion();
        resumen.setId(source.getCVE_ENTIDAD());
        resumen.setEntidadFinanciera(source.getENTIDAD_FINANCIERA());
        resumen.setNumeroProveedor(source.getNUMERO_PROVEEDOR() == null ? 0 : source.getNUMERO_PROVEEDOR().intValue());
        resumen.setCasosEmitidos301_380(source.getCASOS_EMITIDOS_301_380() == null ? 0 : source.getCASOS_EMITIDOS_301_380().longValue());
        resumen.setImporteEmitidos301_380(source.getIMPORTE_EMITIDOS_301_380() == null ? 0.0 : source.getIMPORTE_EMITIDOS_301_380().doubleValue());
        resumen.setCasosEmitidos301(source.getCASOS_EMITIDOS_301() == null ? 0 : source.getCASOS_EMITIDOS_301().longValue());
        resumen.setImporteEmitidos301(source.getIMPORTE_EMITIDOS_301() == null ? 0.0 : source.getIMPORTE_EMITIDOS_301().doubleValue());
        resumen.setCasosEmitidos380(source.getCASOS_EMITIDOS_380() == null ? 0 : source.getCASOS_EMITIDOS_380().longValue());
        resumen.setImporteEmitidos380(source.getIMPORTE_EMITIDOS_380() == null ? 0.0 : source.getIMPORTE_EMITIDOS_380().doubleValue());
        resumen.setCasosPagados301_380(source.getCASOS_PAGADOS_301_380() == null ? 0 : source.getCASOS_PAGADOS_301_380().longValue());
        resumen.setImportePagado301_380(source.getIMPORTE_PAGADO_301_380() == null ? 0.0 : source.getIMPORTE_PAGADO_301_380().doubleValue());
        resumen.setCasosNoPagados(source.getCASOS_NO_PAGADOS() == null ? 0 : source.getCASOS_NO_PAGADOS().longValue());
        resumen.setImporteNoPagado(source.getNO_PAGADO() == null ? 0.0 : source.getNO_PAGADO().doubleValue());
        resumen.setTotalRetenciones(source.getTOTAL_RETENCIONES() == null ? 0.0 : source.getTOTAL_RETENCIONES().doubleValue());
        resumen.setCostoAdministrativo(source.getCOSTO_ADMINISTRATIVO() == null ? 0.0 : source.getCOSTO_ADMINISTRATIVO().doubleValue());
        resumen.setIvaCostoAdministrativo(source.getIVA_COSTO_ADMINISTRATIVO() == null ? 0.0 : source.getIVA_COSTO_ADMINISTRATIVO().doubleValue());
        resumen.setPermisoItinerante(source.getPERMISO_ITINERANTE() == null ? 0.0 : source.getPERMISO_ITINERANTE().doubleValue());
        resumen.setIvaPermisoItinerante(source.getIVA_PERMISO_ITINERANTE() == null ? 0.0 : source.getIVA_PERMISO_ITINERANTE().doubleValue());
        resumen.setNetoFallecidos(source.getNETO_FALLECIDOS() == null ? 0.0 : source.getNETO_FALLECIDOS().doubleValue());
        resumen.setCasosFallecidos(source.getCASOS_FALLECIDOS() == null ? 0 : source.getCASOS_FALLECIDOS().longValue());
        resumen.setRecuperacionFallecidos(source.getRECUPERACION_FALLECIDOS() == null ? 0.0 : source.getRECUPERACION_FALLECIDOS().doubleValue());
        resumen.setPagoReal(source.getPAGO_REAL() == null ? 0.0 : source.getPAGO_REAL().doubleValue());
        return resumen;
    }


}
