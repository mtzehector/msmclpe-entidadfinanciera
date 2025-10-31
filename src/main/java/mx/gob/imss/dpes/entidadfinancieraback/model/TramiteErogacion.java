package mx.gob.imss.dpes.entidadfinancieraback.model;

import lombok.Getter;
import lombok.Setter;
import mx.gob.imss.dpes.common.model.BaseModel;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class TramiteErogacion extends BaseModel {

    @Getter
    @Setter
    private BigDecimal CVE_ENTIDAD_FINANCIERA;

    @Getter
    @Setter
    private String NOMBRE_COMERCIAL;

    @Getter
    @Setter
    private BigDecimal CVE_DOCUMENTO_CR;

    @Getter
    @Setter
    private BigDecimal TIPO_DOCUMENTO_CR;

    @Getter
    @Setter
    private Date FECHA_DESCARGA_CR;

    @Getter
    @Setter
    private Date FECHA_FIRMA_CR;

    @Getter
    @Setter
    private BigDecimal EROGACION;

    @Getter
    @Setter
    private BigDecimal CVE_DOCUMENTO_ST;

    @Getter
    @Setter
    private BigDecimal TIPO_DOCUMENTO_ST;

    @Getter
    @Setter
    private BigDecimal CVE_DOCUMENTO_STD;

    @Getter
    @Setter
    private BigDecimal TIPO_DOCUMENTO_STD;

    @Getter
    @Setter
    private BigDecimal CVE_DOCUMENTO_RRD;

    @Getter
    @Setter
    private BigDecimal TIPO_DOCUMENTO_RRD;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TramiteErogacion that = (TramiteErogacion) o;
        return Objects.equals(CVE_ENTIDAD_FINANCIERA, that.CVE_ENTIDAD_FINANCIERA);
    }

    @Override
    public int hashCode() {
        return Objects.hash(CVE_ENTIDAD_FINANCIERA);
    }
}
