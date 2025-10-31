/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.entidadfinancieraback.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import mx.gob.imss.dpes.common.entity.LogicDeletedEntity;

/**
 *
 * @author Diego Velazquez
 */
@Entity
@Table(name = "MCLC_MOTIVO_SUSPENSION")
public class MclcMotivoSuspension extends LogicDeletedEntity<Short> {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CVE_MOTIVO_SUSPENSION")
    @Getter @Setter private Short id;
    @Size(max = 100)
    @Column(name = "DES_MOTIVO_SUSPENSION")
    @Getter @Setter private String desMotivoSuspension;
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof MclcMotivoSuspension)) {
            return false;
        }
        MclcMotivoSuspension other = (MclcMotivoSuspension) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }
}
