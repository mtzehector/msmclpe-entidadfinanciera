/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.entidadfinancieraback.entity;

import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Getter;
import lombok.Setter;
import mx.gob.imss.dpes.common.entity.LogicDeletedEntity;

/**
 *
 * @author Diego Velazquez
 */
@Entity
@Table(name = "MCLC_CONDICION_ENTFED")
public class MclcCondicionEntfed extends LogicDeletedEntity<Long> {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CVE_CONDICION_ENTFED")
    @GeneratedValue(generator = "SEQ_GEN_MCLS_CONDICION_ENTFED")
    @GenericGenerator(
            name = "SEQ_GEN_MCLS_CONDICION_ENTFED",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "MCLS_CONDICION_ENTFED")
                ,
        @Parameter(name = "initial_value", value = "1")
                ,
        @Parameter(name = "increment_size", value = "1")
            }
    )
    @Getter
    @Setter
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "CVE_DELEGACION")
    @Getter
    @Setter
    private Long cveDelegacion;
    
    
    @Column(name = "NUM_EDAD_LIMITE")
    @Getter
    @Setter
    private Long numEdadLimite;
    @Column(name = "MON_MINIMO")
    @Getter
    @Setter
    private BigDecimal monMinimo;
    @Column(name = "MON_MAXIMO")
    @Getter
    @Setter
    private BigDecimal monMaximo;

    @Getter
    @Setter
    @Column (name="CVE_ENTIDAD_FINANCIERA")
    private Long mclcEntidadFinanciera;
    
    

    @JoinColumn(name = "CVE_SEXO", referencedColumnName = "CVE_SEXO")
    @ManyToOne(optional = false)
    @Getter
    @Setter
    private MclcSexo mclcSexo = new MclcSexo();

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MclcCondicionEntfed)) {
            return false;
        }
        MclcCondicionEntfed other = (MclcCondicionEntfed) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
