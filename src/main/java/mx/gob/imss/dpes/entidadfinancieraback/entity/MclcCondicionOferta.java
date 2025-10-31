/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.entidadfinancieraback.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.util.Collection;
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
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
/**
 *
 * @author Diego Velazquez
 */
@Entity
@Table(name = "MCLC_CONDICION_OFERTA")
public class MclcCondicionOferta extends LogicDeletedEntity<Long> {
    
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
    @Getter @Setter private Long id;
    @Column(name = "POR_TASA_ANUAL")
    @Getter @Setter private BigDecimal porTasaAnual;
    @Column(name = "POR_CAT")
    @Getter @Setter private BigDecimal porCat;
    
    @Column(name = "CVE_ENTIDAD_FINANCIERA")
    @Getter @Setter private Long mclcEntidadFinanciera;
    
    @Column(name = "CVE_PLAZO")
    @Getter @Setter private Long mclcPlazo;
    
     @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
     
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MclcCondicionOferta)) {
            return false;
        }
        MclcCondicionOferta other = (MclcCondicionOferta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }    
}
