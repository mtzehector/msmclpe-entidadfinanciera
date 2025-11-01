package mx.gob.imss.dpes.entidadfinancieraback.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import mx.gob.imss.dpes.common.entity.LogicDeletedEntity;
import mx.gob.imss.dpes.support.config.CustomDateDeserializer;
import mx.gob.imss.dpes.support.config.CustomDateSerializer;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
/**
 *
 *
 *
 * @author Diego
 *
 */
@Entity

@Table(name = "MCLC_ENTIDAD_FINANCIERA")
@NamedEntityGraph(
        name = "MclcEntidadFinanciera.ConCondicionesOfertaYBeneficios",
        attributeNodes = {
            @NamedAttributeNode("mclcCondicionOfertaCollection"),
            @NamedAttributeNode(value = "mclcCondicionOfertaCollection", subgraph = "beneficios-condicionoferta")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "beneficios-condicionoferta",
                        attributeNodes = {
                                @NamedAttributeNode("mclcBeneficioCollection")
                        }
                )
        }
)
public class MclcEntidadFinanciera extends LogicDeletedEntity<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CVE_ENTIDAD_FINANCIERA")
    //@SequenceGenerator(name = "SEQ_GEN_MCLS_PENSONA", sequenceName = "MCLS_PENSONA")
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MCLS_PENSONA")
    @GeneratedValue(generator = "SEQ_GEN_MCLS_ENTIDAD_FINANCIERA")
    @GenericGenerator(
            name = "SEQ_GEN_MCLS_ENTIDAD_FINANCIERA",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "MCLS_ENTIDAD_FINANCIERA"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
            }
    )
    @Getter
    @Setter
    private Long id;

    @Size(max = 200)
    @Column(name = "NOM_COMERCIAL")
    @Getter
    @Setter
    private String nombreComercial;

    @Size(max = 200)
    @Column(name = "NOM_RAZON_SOCIAL")
    @Getter
    @Setter
    private String razonSocial;

    @Size(max = 13)
    @Column(name = "REF_RFC")
    @Getter
    @Setter
    private String rfc;

    @Size(max = 25)
    @Column(name = "NUM_TELEFONO")
    @Getter
    @Setter
    private String numeroTelefono;

    @Size(max = 25)
    @Column(name = "NUM_TEL_ATENCION_CLIENTES")
    @Getter
    @Setter
    private String telefonoAtencionClientes;

    @Size(max = 200)
    @Column(name = "REF_PAGINA_WEB")
    @Getter
    @Setter
    private String paginaWeb;

    @Size(max = 50)
    @Column(name = "DES_CORREO_ELECTRONICO")
    @Getter
    @Setter
    private String CorreoElectronico;

    @Column(name = "NUM_CAT_PROMEDIO")
    @Getter
    @Setter
    private BigDecimal catPromedio;

    @Size(max = 2)
    @Column(name = "CVE_DELEGACION")
    @Getter
    @Setter
    private String idDelegacion;

    @Size(max = 2)
    @Column(name = "CVE_SUBDELEGACION")
    @Getter
    @Setter
    private String idSubdelegacion;

    @Size(max = 11)
    @Column(name = "CVE_REG_PATRONAL")
    @Getter
    @Setter
    private String registroPatronal;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "mclcEntidadFinanciera")
    @Getter
    @Setter
    private Set<MclcCondicionEntfed> mclcCondicionEntfedCollection;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "mclcEntidadFinanciera")
    @Getter
    @Setter
    private Set<MclcCondicionOfertaOldie> mclcCondicionOfertaCollection;

    @JoinColumn(name = "CVE_ESTADO_ENT_FINANCIERA", referencedColumnName = "CVE_ESTADO_EF")
    @ManyToOne(optional = false)
    @Getter
    @Setter
    private MclcEstadoEf mclcEstadoEf;

    @JoinColumn(name = "CVE_MOTIVO_SUSPENSION", referencedColumnName = "CVE_MOTIVO_SUSPENSION")
    @ManyToOne(optional = true)
    @Getter
    @Setter
    private MclcMotivoSuspension mclcMotivoSuspension;

    @Column(name = "CURP_REP_LEGAL")
    @Size(max = 20)
    @Getter
    @Setter
    private String curpRepresentateLegal;

    @Column(name = "NOM_NOMBRE_REP_LEGAL")
    @Size(max = 200)
    @Getter
    @Setter
    private String nombreLegal;

    @Column(name = "NOM_PRIM_APE_REP_LEGAL")
    @Size(max = 50)
    @Getter
    @Setter
    private String primerApeLegal;

    @Column(name = "NOM_SEG_APE_REP_LEGAL")
    @Size(max = 50)
    @Getter
    @Setter
    private String segundoApeLegal;

    @Column(name = "CORREO2_CONTACTO")
    @Size(max = 200)
    @Getter
    @Setter
    private String correo2;

    @Column(name = "CORREO3_CONTACTO")
    @Size(max = 200)
    @Getter
    @Setter
    private String correo3;

    @Column(name = "DIRECCION")
    @Size(max = 200)
    @Getter
    @Setter
    private String direccion;

    @Column(name = "FEC_FIRMA_CONTRATO", nullable = false)
    @Temporal(TemporalType.DATE)
    @Getter
    @Setter
    @JsonDeserialize(using = CustomDateDeserializer.class)
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date fecFirmaContra;

    @Column(name = "FEC_INICIO_CONTRATO", nullable = false)
    @Temporal(TemporalType.DATE)
    @Getter
    @Setter
    @JsonDeserialize(using = CustomDateDeserializer.class)
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date fecIniFirmaContra;

    @Column(name = "NUMERO_PROVEEDOR")
    @Getter
    @Setter
    private String numeroProveedor;

    @Column(name = "INST_BANCARIA")
    @Size(max = 20)
    @Getter
    @Setter
    private String instBancaria;

    @Column(name = "CLABE")
    @Size(max = 20)
    @Getter
    @Setter
    private String clabe;

    @Column(name = "CONF_CLABE")
    @Size(max = 20)
    @Getter
    @Setter
    private String confClabe;

    @Column(name = "CURP_ADMIN")
    @Size(max = 20)
    @Getter
    @Setter
    private String curpAdmin;

    @Column(name = "NOM_NOMBRE_ADMIN")
    @Size(max = 200)
    @Getter
    @Setter
    private String nombreAdmin;

    @Column(name = "NOM_PRIMER_APE_ADMIN")
    @Size(max = 47)
    @Getter
    @Setter
    private String primerApAdmin;

    @Column(name = "NOM_SEGUNDO_APE_ADMIN")
    @Size(max = 50)
    @Getter
    @Setter
    private String segundoApeAdmin;

    @Column(name = "CORREO_ADMIN")
    @Size(max = 200)
    @Getter
    @Setter
    private String correoAdmin;

    @Column(name = "CVE_ENTIDAD_FINANCIERA_SIPRE")
    @Getter
    @Setter
    private String idSipre;

    @Column(name = "CONVENIO")
    @Getter
    @Setter
    private Long sinConvenio;
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "mclcEntidadFinanciera")
    @Getter
    @Setter
    private Set<McltRegistrosPatronales> mcltRegistrosPatronalesCollection;

    @Override
    public int hashCode() {

        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;

    }

    @Override
    public boolean equals(Object object) {

        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MclcEntidadFinanciera)) {
            return false;
        }

        MclcEntidadFinanciera other = (MclcEntidadFinanciera) object;

        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
