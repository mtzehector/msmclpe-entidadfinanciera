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
import lombok.Getter;
import lombok.Setter;
import mx.gob.imss.dpes.common.entity.LogicDeletedEntity;

/**
 *
 * @author juanf.barragan
 */
@Entity
@Table(name = "MCLC_BANCOS")
public class MclcBancos extends LogicDeletedEntity<Long> {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CVE_BANCO")
    @Getter
    @Setter
    private Long id;

    @Column(name = "CLAVE")
    @Getter
    @Setter
    private String clave;

    @Column(name = "DESCRIPCION")
    @Getter
    @Setter
    private String descripcion;

    @Column(name = "ACTIVO")
    @Getter
    @Setter
    private Integer activo;

}
