package mx.gob.imss.dpes.entidadfinancieraback.entity;

import lombok.Getter;
import lombok.Setter;
import mx.gob.imss.dpes.common.entity.LogicDeletedEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "MCLC_ESTATUS_CONCILIACION")
public class MclcEstatusConciliacion extends LogicDeletedEntity<Long> {

    @Id
    @NotNull
    @Column(name = "CVE_EST_CONCILIACION")
    @GeneratedValue(generator = "SEQ_GEN_ESTATUS_CONCILIACION")
    @GenericGenerator(
            name = "SEQ_GEN_ESTATUS_CONCILIACION",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "MCLS_ESTATUS_CONCILIACION"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Getter
    @Setter
    private Long id;

    @Column(name = "PERIODO")
    @Getter
    @Setter
    private String periodo;

    @Column(name = "ACTIVO")
    @Getter
    @Setter
    private Boolean activo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MclcEstatusConciliacion that = (MclcEstatusConciliacion) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
