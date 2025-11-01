package mx.gob.imss.dpes.entidadfinancieraback.assembler;

import mx.gob.imss.dpes.common.assembler.BaseAssembler;
import mx.gob.imss.dpes.entidadfinancieraback.model.SolicitudTransferenciaConciliacion;
import mx.gob.imss.dpes.interfaces.entidadfinanciera.model.SolicitudTransferencia;

import javax.ws.rs.ext.Provider;

@Provider
public class SolicitudTransferenciaConciliacionAssembler extends BaseAssembler<SolicitudTransferenciaConciliacion, SolicitudTransferencia> {

    @Override
    public SolicitudTransferencia assemble(SolicitudTransferenciaConciliacion source) {

        if (source == null)
            return null;

        SolicitudTransferencia totales = new SolicitudTransferencia();
        totales.setRetencionNacional(source.getRETENCION_NACIONAL() == null? 0.0 : source.getRETENCION_NACIONAL().doubleValue());
        totales.setCostoAdministrativo(source.getCOSTO_ADMINISTRATIVO() == null? 0.0 : source.getCOSTO_ADMINISTRATIVO().doubleValue());
        totales.setIvaCostoAdministrativo(source.getIVA_COSTO_ADMINISTRATIVO() == null? 0.0 : source.getIVA_COSTO_ADMINISTRATIVO().doubleValue());
        //totales.setPermisoItinerante(source.getPERMISO_ITINERANTE() == null? 0.0 : source.getPERMISO_ITINERANTE().doubleValue());
        //totales.setIvaPermisoItinerante(source.getIVA_PERMISO_ITINERANTE() == null? 0.0 : source.getIVA_PERMISO_ITINERANTE().doubleValue());
        totales.setImporteFallecidos(source.getIMPORTE_FALLECIDOS() == null? 0.0 : source.getIMPORTE_FALLECIDOS().doubleValue());
        totales.setNetoPagar(source.getNETO_PAGAR() == null? 0.0 : source.getNETO_PAGAR().doubleValue());
        return totales;
    }
}
