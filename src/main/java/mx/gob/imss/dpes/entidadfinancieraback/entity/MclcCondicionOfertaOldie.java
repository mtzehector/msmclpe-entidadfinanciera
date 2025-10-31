/*

* To change this license header, choose License Headers in Project Properties.

* To change this template file, choose Tools | Templates

* and open the template in the editor.

 */
package mx.gob.imss.dpes.entidadfinancieraback.entity;

import java.math.BigDecimal;

import java.util.Collection;
import java.util.Set;

import javax.persistence.Basic;

import javax.persistence.CascadeType;

import javax.persistence.Column;

import javax.persistence.Entity;

import javax.persistence.FetchType;

import javax.persistence.GeneratedValue;

import javax.persistence.Id;

import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;

import javax.persistence.OneToMany;

import javax.persistence.Table;

import javax.validation.constraints.NotNull;

import lombok.Getter;

import lombok.Setter;

import mx.gob.imss.dpes.common.entity.LogicDeletedEntity;

import org.hibernate.annotations.GenericGenerator;

import org.hibernate.annotations.Parameter;

/**
 *
 *
 *
 * @author Diego Velazquez
 *
 */
@Entity
@Table(name = "MCLC_CONDICION_OFERTA")
public class MclcCondicionOfertaOldie extends LogicDeletedEntity<Long> {

    private static final long serialVersionUID = 1L;

    @Id

    @Basic(optional = false)

    @NotNull

    @Column(name = "CVE_CONDICION_OFERTA")

    @GeneratedValue(generator = "SEQ_GEN_MCLS_CONDICION_OFERTA")

    @GenericGenerator(
            name = "SEQ_GEN_MCLS_CONDICION_OFERTA",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "MCLS_CONDICION_OFERTA")

                ,

        @Parameter(name = "initial_value", value = "1")

                ,

        @Parameter(name = "increment_size", value = "1")

            }
    )

    @Getter
    @Setter
    private Long id;

    @Column(name = "POR_TASA_ANUAL")

    @Getter
    @Setter
    private BigDecimal porTasaAnual;

    @Column(name = "POR_CAT")

    @Getter
    @Setter
    private BigDecimal porCat;

    @JoinColumn(name = "CVE_ENTIDAD_FINANCIERA", referencedColumnName = "CVE_ENTIDAD_FINANCIERA")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @Getter @Setter private MclcEntidadFinanciera mclcEntidadFinanciera;

    @JoinColumn(name = "CVE_PLAZO", referencedColumnName = "CVE_PLAZO")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @Getter @Setter private MclcPlazo mclcPlazo;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "mclcCondicionOferta")
    @Getter @Setter private Set<MclcBeneficio> mclcBeneficioCollection;

    @Override
    public int hashCode() {

        int hash = 0;

        hash += (id != null ? id.hashCode() : 0);

        return hash;

    }

    @Override

    public boolean equals(Object object) {

        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MclcCondicionOfertaOldie)) {

            return false;

        }

        MclcCondicionOfertaOldie other = (MclcCondicionOfertaOldie) object;

        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {

            return false;

        }

        return true;

    }

}
