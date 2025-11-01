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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import mx.gob.imss.dpes.common.entity.LogicDeletedEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author juanf.barragan
 */
@Entity
@Table(name = "MCLT_REGISTROS_PATRONALES")
public class McltRegistroPatronal extends LogicDeletedEntity<Long>{
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CVE_REG_PAT")
    @GeneratedValue(generator = "SEQ_GEN_MCLS_REGISTROS_PATRONALES")
    @GenericGenerator(
            name = "SEQ_GEN_MCLS_REGISTROS_PATRONALES",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "MCLS_REGISTROS_PATRONALES"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
            }
    )
    @Getter
    @Setter
    private Long id;

    @Column(name = "CVE_ENTIDAD_FINANCIERA")
    @Getter 
    @Setter 
    private Long mclcEntidadFinanciera;

    @Column(name = "REGISTO_PATRONAL")
    @Getter
    @Setter
    private String registroPatronal;

    @Override
    public int hashCode() {

        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;

    }

    @Override
    public boolean equals(Object object) {

        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof McltRegistroPatronal)) {
            return false;
        }

        McltRegistroPatronal other = (McltRegistroPatronal) object;

        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    
}
