package mx.gob.imss.dpes.entidadfinancieraback.entity;

import lombok.Getter;
import lombok.Setter;
import mx.gob.imss.dpes.common.entity.LogicDeletedEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "MCLC_BENEFICIO")
public class MclcBeneficios extends LogicDeletedEntity<Long> {

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CVE_BENEFICIO")
    @GeneratedValue(generator = "SEQ_GEN_MCLS_BENEFICIO")
    @GenericGenerator(
            name = "SEQ_GEN_MCLS_BENEFICIO",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "MCLS_BENEFICIO"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @Column(name = "CVE_CONDICION_OFERTA")
    private Long condicionOfertaId;

    @Size(max = 200)
    @Column(name = "DES_BENEFICIO")
    @Getter
    @Setter private String descripcion;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MclcBeneficios that = (MclcBeneficios) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
