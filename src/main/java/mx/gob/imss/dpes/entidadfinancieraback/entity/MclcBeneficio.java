/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.entidadfinancieraback.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import mx.gob.imss.dpes.common.entity.LogicDeletedEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author Diego Velazquez
 */
@Entity
@Table(name = "MCLC_BENEFICIO")
public class MclcBeneficio extends LogicDeletedEntity<Long> {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CVE_BENEFICIO")
    @GeneratedValue(generator = "SEQ_GEN_MCLS_BENEFICIO")
    @GenericGenerator(
            name = "SEQ_GEN_MCLS_BENEFICIO",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "MCLS_BENEFICIO")
                ,
        @Parameter(name = "initial_value", value = "1")
                ,
        @Parameter(name = "increment_size", value = "1")
            }
    )
    @Getter @Setter private Long id;
    @Size(max = 200)
    @Column(name = "DES_BENEFICIO")
    @Getter @Setter private String desBeneficio;
    
     @JoinColumn(name = "CVE_CONDICION_OFERTA", referencedColumnName = "CVE_CONDICION_OFERTA")
     @ManyToOne (optional = false)
     @Getter @Setter private MclcCondicionOfertaOldie mclcCondicionOferta;
     
     @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MclcBeneficio)) {
            return false;
        }
        MclcBeneficio other = (MclcBeneficio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
