package mx.gob.imss.dpes.entidadfinancieraback.assembler;

import mx.gob.imss.dpes.common.assembler.BaseAssembler;
import mx.gob.imss.dpes.entidadfinancieraback.model.PermisoItineranteCostoAdministrativo;
import mx.gob.imss.dpes.interfaces.entidadfinanciera.model.PermisoItineranteYCostoAdministrativo;

import javax.ws.rs.ext.Provider;

@Provider
public class PermisoItineranteAssembler extends BaseAssembler<PermisoItineranteCostoAdministrativo, PermisoItineranteYCostoAdministrativo> {

    @Override
    public PermisoItineranteYCostoAdministrativo assemble(PermisoItineranteCostoAdministrativo source) {

        if (source == null)
            return null;

        PermisoItineranteYCostoAdministrativo permisoItinerante = new PermisoItineranteYCostoAdministrativo();
        permisoItinerante.setCveEntidadFinanciera(source.getCVE_ENTIDAD_FINANCIERA() == null? 0 : source.getCVE_ENTIDAD_FINANCIERA().longValue());
        permisoItinerante.setNombreComercial(source.getNOM_COMERCIAL() == null? "" : source.getNOM_COMERCIAL());
        permisoItinerante.setNumeroProveedor(source.getNUMERO_PROVEEDOR() == null? 0: source.getNUMERO_PROVEEDOR().intValue());
        permisoItinerante.setCveConvenioAdminFinanciera(source.getCVE_CONVENIO_ADMIN_FINANCIERA() == null? null : source.getCVE_CONVENIO_ADMIN_FINANCIERA().intValue());
        permisoItinerante.setPorcentajeAdministracion(source.getPORCENTAJE_ADMINISTRACION() == null? null : source.getPORCENTAJE_ADMINISTRACION().doubleValue());
        permisoItinerante.setPorcentajePermisoItinerante(source.getPORCENTAJE_PERMISO_ITINERANTE() == null? null : source.getPORCENTAJE_PERMISO_ITINERANTE().doubleValue());
        permisoItinerante.setPeriodoInicioVigencia(source.getPERIODO_INICIO_VIGENCIA() == null || source.getPERIODO_INICIO_VIGENCIA().isEmpty()? null : source.getPERIODO_INICIO_VIGENCIA());
        permisoItinerante.setPeriodoFinVigencia(source.getPERIODO_FIN_VIGENCIA() == null || source.getPERIODO_FIN_VIGENCIA().isEmpty()? null : source.getPERIODO_FIN_VIGENCIA());
        return permisoItinerante;
    }
}
