package mx.gob.imss.dpes.entidadfinancieraback.model;

import lombok.Getter;
import lombok.Setter;
import mx.gob.imss.dpes.common.model.BaseModel;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class PermisoItineranteCostoAdministrativo extends BaseModel {

    @Getter
    @Setter
    private BigDecimal CVE_ENTIDAD_FINANCIERA;

    @Getter
    @Setter
    private String NOM_COMERCIAL;

    @Getter
    @Setter
    private BigDecimal NUMERO_PROVEEDOR;

    @Getter
    @Setter
    private BigDecimal CVE_CONVENIO_ADMIN_FINANCIERA;

    @Getter
    @Setter
    private BigDecimal PORCENTAJE_ADMINISTRACION;

    @Getter
    @Setter
    private BigDecimal PORCENTAJE_PERMISO_ITINERANTE;

    @Getter
    @Setter
    private String PERIODO_INICIO_VIGENCIA;

    @Getter
    @Setter
    private String PERIODO_FIN_VIGENCIA;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PermisoItineranteCostoAdministrativo that = (PermisoItineranteCostoAdministrativo) o;
        return Objects.equals(CVE_ENTIDAD_FINANCIERA, that.CVE_ENTIDAD_FINANCIERA);
    }

    @Override
    public int hashCode() {
        return Objects.hash(CVE_ENTIDAD_FINANCIERA);
    }
}
