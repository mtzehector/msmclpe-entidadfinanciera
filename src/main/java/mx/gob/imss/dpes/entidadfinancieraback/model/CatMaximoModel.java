/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.entidadfinancieraback.model;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import mx.gob.imss.dpes.common.model.BaseModel;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcCondicionOferta;

/**
 *
 * @author juanf.barragan
 */
public class CatMaximoModel extends BaseModel{
    
    @Getter @Setter private BigDecimal CatIMSS;
    @Getter @Setter private String catAnterior;
    @Getter @Setter private List<MclcCondicionOferta> condicionesOferta;
}
