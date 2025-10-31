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
import javax.validation.constraints.Size;
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
@Table(name = "MCLT_CARTA_RECIBO")
public class McltCartaRecibo extends LogicDeletedEntity<Long>{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CVE_CARTA_RECIBO")
    @Getter
    @Setter
    @GeneratedValue(generator = "SEQ_GEN_MCLS_CARTA_RECIBO")
    @GenericGenerator(
            name = "SEQ_GEN_MCLS_CARTA_RECIBO",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "MCLS_CARTA_RECIBO")
                ,
        @Parameter(name = "initial_value", value = "1")
                ,
        @Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;
    
    @Column(name = "CVE_ENTIDAD_FINANCIERA")
    @Getter
    @Setter
    private Long cveEntFin;
    
    @Size(max = 10)
    @Column(name = "PERIODO")
    @Getter
    @Setter
    private String periodo;
    
    @Size(max = 30)
    @Column(name = "PROVEEDOR")
    @Getter
    @Setter
    private String proveedor;
    
    @Size(max = 13)
    @Column(name = "RFC")
    @Getter
    @Setter
    private String rfc;
    
    @Size(max = 30)
    @Column(name = "MONTO")
    @Getter
    @Setter
    private String monto;
    
    @Size(max = 300)
    @Column(name = "MONTOLETRA")
    @Getter
    @Setter
    private String montoLetra;
    
    @Size(max = 30)
    @Column(name = "PRIMAS")
    @Getter
    @Setter
    private String primas;
    
    @Size(max = 30)
    @Column(name = "TASA")
    @Getter
    @Setter
    private String tasa;
    
    @Size(max = 30)
    @Column(name = "IVA")
    @Getter
    @Setter
    private String iva;
    
    @Size(max = 30)
    @Column(name = "DEMASIA")
    @Getter
    @Setter
    private String demasia;
    
    @Size(max = 30)
    @Column(name = "NETO")
    @Getter
    @Setter
    private String neto;
    
    @Size(max = 4000)
    @Column(name = "FIRMA")
    @Getter
    @Setter
    private String firma;
    
    @Size(max = 100)
    @Column(name = "REF_DOC_BOVEDA")
    @Getter
    @Setter
    private String refDocBoveda;
    
}
