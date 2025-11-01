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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import mx.gob.imss.dpes.common.entity.LogicDeletedEntity;

/**
 *
 * @author Diego Velazquez
 */
@Entity
@Table(name = "MCLC_ESTADO_EF")
public class MclcEstadoEf extends LogicDeletedEntity<Short> {
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CVE_ESTADO_EF")
    @Getter @Setter private Short id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DES_ESTADO_ENT_FINANCIERA")
    @Getter @Setter private String desEstadoEntFinanciera;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof MclcEstadoEf)) {
            return false;
        }
        MclcEstadoEf other = (MclcEstadoEf) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }  
}
