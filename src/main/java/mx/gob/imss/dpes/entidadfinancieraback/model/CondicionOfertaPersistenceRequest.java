/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.entidadfinancieraback.model;

import java.util.ArrayList;
import java.util.Collection;
import lombok.Data;
import mx.gob.imss.dpes.common.model.BaseModel;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcCondicionOferta;

/**
 *
 * @author eduardo.loyo
 */
@Data
public class CondicionOfertaPersistenceRequest extends BaseModel{
    Collection<MclcCondicionOferta> condicionOferta = new ArrayList();
}
