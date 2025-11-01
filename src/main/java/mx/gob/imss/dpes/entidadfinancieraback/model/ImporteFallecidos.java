package mx.gob.imss.dpes.entidadfinancieraback.model;

import lombok.Getter;
import lombok.Setter;
import mx.gob.imss.dpes.common.model.BaseModel;

import java.math.BigDecimal;

public class ImporteFallecidos extends BaseModel {

    @Getter
    @Setter
    private BigDecimal IMPORTE_FALLECIDOS;

}
