package mx.gob.imss.dpes.entidadfinancieraback.model;

import lombok.Getter;
import lombok.Setter;
import mx.gob.imss.dpes.common.model.BaseModel;

import java.math.BigDecimal;
import java.util.Objects;

public class ReporteResumenConciliacion extends BaseModel {

    @Getter
    @Setter
    private String CVE_ENTIDAD;

    @Getter
    @Setter
    private String ENTIDAD_FINANCIERA;

    @Getter
    @Setter
    private BigDecimal NUMERO_PROVEEDOR;

    @Getter
    @Setter
    private BigDecimal CASOS_EMITIDOS_301_380;

    @Getter
    @Setter
    private BigDecimal IMPORTE_EMITIDOS_301_380;

    @Getter
    @Setter
    private BigDecimal CASOS_EMITIDOS_301;

    @Getter
    @Setter
    private BigDecimal IMPORTE_EMITIDOS_301;

    @Getter
    @Setter
    private BigDecimal CASOS_EMITIDOS_380;

    @Getter
    @Setter
    private BigDecimal IMPORTE_EMITIDOS_380;

    @Getter
    @Setter
    private BigDecimal CASOS_PAGADOS_301_380;

    @Getter
    @Setter
    private BigDecimal IMPORTE_PAGADO_301_380;

    @Getter
    @Setter
    private BigDecimal CASOS_NO_PAGADOS;

    @Getter
    @Setter
    private BigDecimal NO_PAGADO;

    @Getter
    @Setter
    private BigDecimal TOTAL_RETENCIONES;

    @Getter
    @Setter
    private BigDecimal COSTO_ADMINISTRATIVO;

    @Getter
    @Setter
    private BigDecimal IVA_COSTO_ADMINISTRATIVO;

    @Getter
    @Setter
    private BigDecimal PERMISO_ITINERANTE;

    @Getter
    @Setter
    private BigDecimal IVA_PERMISO_ITINERANTE;

    @Getter
    @Setter
    private BigDecimal NETO_FALLECIDOS;

    @Getter
    @Setter
    private BigDecimal CASOS_FALLECIDOS;

    @Getter
    @Setter
    private BigDecimal RECUPERACION_FALLECIDOS;

    @Getter
    @Setter
    private BigDecimal PAGO_REAL;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReporteResumenConciliacion that = (ReporteResumenConciliacion) o;
        return Objects.equals(CVE_ENTIDAD, that.CVE_ENTIDAD) && Objects.equals(NUMERO_PROVEEDOR, that.NUMERO_PROVEEDOR);
    }

    @Override
    public int hashCode() {
        return Objects.hash(CVE_ENTIDAD, NUMERO_PROVEEDOR);
    }
}
