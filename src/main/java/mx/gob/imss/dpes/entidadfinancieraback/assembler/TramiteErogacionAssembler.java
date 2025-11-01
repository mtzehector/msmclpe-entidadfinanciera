package mx.gob.imss.dpes.entidadfinancieraback.assembler;

import mx.gob.imss.dpes.common.assembler.BaseAssembler;
import mx.gob.imss.dpes.common.enums.TipoDocumentoEnum;
import mx.gob.imss.dpes.entidadfinancieraback.model.TramiteErogacion;
import mx.gob.imss.dpes.interfaces.documento.model.Documento;
import mx.gob.imss.dpes.interfaces.documento.model.TipoDocumentoFront;
import mx.gob.imss.dpes.interfaces.entidadfinanciera.model.TramiteErogacionConciliacion;


import javax.ws.rs.ext.Provider;

@Provider
public class TramiteErogacionAssembler extends BaseAssembler<TramiteErogacion, TramiteErogacionConciliacion> {

    @Override
    public TramiteErogacionConciliacion assemble(TramiteErogacion source) {

        if (source == null)
            return null;

        TramiteErogacionConciliacion tramite = new TramiteErogacionConciliacion();
        tramite.setCveEntidadFinanciera(source.getCVE_ENTIDAD_FINANCIERA() == null? 0 : source.getCVE_ENTIDAD_FINANCIERA().longValue());
        tramite.setNombreComercial(source.getNOMBRE_COMERCIAL() == null? "" : source.getNOMBRE_COMERCIAL());
        tramite.setCveCartaRecibo(source.getCVE_DOCUMENTO_CR() == null? 0 : source.getCVE_DOCUMENTO_CR().longValue());
        tramite.setCveTipoDocumentoCartaRecibo(source.getTIPO_DOCUMENTO_CR() == null? 0: source.getTIPO_DOCUMENTO_CR().intValue());
        tramite.setFechaDescargaCartaRecibo(source.getFECHA_DESCARGA_CR());
        tramite.setFechaFirmaCartaRecibo(source.getFECHA_FIRMA_CR());
        Integer erogacion = source.getEROGACION() == null? 0 : source.getEROGACION().intValue();
        tramite.setErogacion(erogacion.equals(0)? false : true);

        if (source.getCVE_DOCUMENTO_ST() != null && source.getTIPO_DOCUMENTO_ST() != null) {
            Documento transferencia = new Documento();
            transferencia.setId(source.getCVE_DOCUMENTO_ST().longValue());
            transferencia.setTipoDocumentoEnum(
                    new TipoDocumentoFront(
                            TipoDocumentoEnum.forValue(source.getTIPO_DOCUMENTO_ST().longValue())
                    ));
            tramite.setTransferencia(transferencia);
        }
        if (source.getCVE_DOCUMENTO_STD() != null && source.getTIPO_DOCUMENTO_STD() != null) {
            Documento cuentaContable = new Documento();
            cuentaContable.setId(source.getCVE_DOCUMENTO_STD().longValue());
            cuentaContable.setTipoDocumentoEnum(
                    new TipoDocumentoFront(
                            TipoDocumentoEnum.forValue(source.getTIPO_DOCUMENTO_STD().longValue())
                    ));
            tramite.setCuentaContable(cuentaContable);
        }
        if (source.getCVE_DOCUMENTO_RRD() != null && source.getTIPO_DOCUMENTO_RRD() != null) {
            Documento delegacion = new Documento();
            delegacion.setId(source.getCVE_DOCUMENTO_RRD().longValue());
            delegacion.setTipoDocumentoEnum( new TipoDocumentoFront(
                    TipoDocumentoEnum.forValue(source.getTIPO_DOCUMENTO_RRD().longValue())
            ));
            tramite.setDelegacion(delegacion);
        }
        return tramite;
    }
}
