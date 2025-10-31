package mx.gob.imss.dpes.entidadfinancieraback.model;

import lombok.Getter;
import lombok.Setter;
import mx.gob.imss.dpes.common.model.BaseModel;

import java.math.BigDecimal;

public class ReporteRetencionPorDelegacion extends BaseModel {

    @Getter
    @Setter
    private String NUMERODELEGACION;

    @Getter
    @Setter
    private String DELEGACION;

    @Getter
    @Setter
    private BigDecimal TOTAL;

    @Getter
    @Setter
    private BigDecimal NETOPAGAR;

    @Getter
    @Setter
    private BigDecimal NUMEROPROVEEDOR;

    @Getter
    @Setter
    private String NOMBRECOMERCIAL;



}
