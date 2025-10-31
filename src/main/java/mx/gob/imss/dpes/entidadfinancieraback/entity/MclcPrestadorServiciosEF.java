package mx.gob.imss.dpes.entidadfinancieraback.entity;

import lombok.Getter;
import lombok.Setter;
import mx.gob.imss.dpes.common.entity.LogicDeletedEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "MCLT_PRESTADOR_SERVICIOS_EF")
public class MclcPrestadorServiciosEF extends LogicDeletedEntity<Long>  {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CVE_PRESTADOR_SERVICIOS")
    @GeneratedValue(generator = "SEQ_GEN_PRESTADOR_SERVICIOS_EF")
    @GenericGenerator(
            name = "SEQ_GEN_PRESTADOR_SERVICIOS_EF",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "MCLS_PRESTADOR_SERVICIOS_EF"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Getter
    @Setter
    private Long id;

    @Column(name = "CVE_ENTIDAD_FINANCIERA")
    @Getter
    @Setter
    private Long cveEntidadFinanciera;

    @Column(name = "CVE_TIPO_PRESTADOR_SERVICIOS")
    @Getter
    @Setter
    private Integer cveTipoPrestadorServicios;

    @Column(name = "REGISTRO_PATRONAL")
    @Getter
    @Setter
    private String registroPatronal;

    @Column(name = "RFC")
    @Getter
    @Setter
    private String rfc;

    @Column(name = "RAZON_SOCIAL")
    @Getter
    @Setter
    private String razonSocial;

    @Column(name = "DIRECCION")
    @Getter
    @Setter
    private String direccion;

    @Column(name = "CORREO_ELECTRONICO")
    @Getter
    @Setter
    private String correoElectronico;

    @Column(name = "PAGINA_WEB")
    @Getter
    @Setter
    private String paginaWeb;


    @Override
    public boolean equals(Object object) {
        if (!(object instanceof MclcPrestadorServiciosEF)) {
            return false;
        }
        MclcPrestadorServiciosEF other = (MclcPrestadorServiciosEF) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }


}
