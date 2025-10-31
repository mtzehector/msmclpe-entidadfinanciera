package mx.gob.imss.dpes.entidadfinancieraback.assembler;

import mx.gob.imss.dpes.common.assembler.BaseAssembler;
import mx.gob.imss.dpes.entidadfinancieraback.model.ReporteRetencionPorDelegacion;
import mx.gob.imss.dpes.interfaces.entidadfinanciera.model.RetencionPorDelegacion;

import javax.ws.rs.ext.Provider;

@Provider
public class RetencionPorDelegacionAssembler extends BaseAssembler<ReporteRetencionPorDelegacion, RetencionPorDelegacion> {

    @Override
    public RetencionPorDelegacion assemble(ReporteRetencionPorDelegacion source) {

        if (source == null)
            return null;

        RetencionPorDelegacion reporte = new RetencionPorDelegacion();
        reporte.setNumeroDelegacion(source.getNUMERODELEGACION() == null? "" : source.getNUMERODELEGACION());
        reporte.setDelegacion(source.getDELEGACION() == null? "" : source.getDELEGACION());
        reporte.setTotal(source.getTOTAL() == null? 0 : source.getTOTAL().intValue());
        reporte.setNetoPagar(source.getNETOPAGAR() == null? 0.0 : source.getNETOPAGAR().doubleValue());
        reporte.setNumeroProveedor(source.getNUMEROPROVEEDOR() == null? 0 : source.getNUMEROPROVEEDOR().intValue());
        reporte.setNombreComercial(source.getNOMBRECOMERCIAL() == null? "" : source.getNOMBRECOMERCIAL());
        return reporte;
    }
}
