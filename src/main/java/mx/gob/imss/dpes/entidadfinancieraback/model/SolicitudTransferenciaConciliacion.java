package mx.gob.imss.dpes.entidadfinancieraback.model;

import lombok.Getter;
import lombok.Setter;
import mx.gob.imss.dpes.common.model.BaseModel;

import java.math.BigDecimal;

public class SolicitudTransferenciaConciliacion extends BaseModel {

    @Getter
    @Setter
    private BigDecimal RETENCION_NACIONAL;

    @Getter
    @Setter
    private BigDecimal COSTO_ADMINISTRATIVO;

    @Getter
    @Setter
    private BigDecimal IVA_COSTO_ADMINISTRATIVO;

    //@Getter
    //@Setter
    //private BigDecimal PERMISO_ITINERANTE;

    //@Getter
    //@Setter
    //private BigDecimal IVA_PERMISO_ITINERANTE;

    @Getter
    @Setter
    private BigDecimal IMPORTE_FALLECIDOS;

    @Getter
    @Setter
    private BigDecimal NETO_PAGAR;

}
