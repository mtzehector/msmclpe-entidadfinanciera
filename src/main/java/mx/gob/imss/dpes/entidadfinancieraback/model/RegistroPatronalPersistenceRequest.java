/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.entidadfinancieraback.model;
import java.util.ArrayList;
import java.util.Collection;
import mx.gob.imss.dpes.common.model.BaseModel;
import lombok.Data;
import mx.gob.imss.dpes.entidadfinancieraback.entity.McltRegistroPatronal;
/**
 *
 * @author juanf.barragan
 */
@Data
public class RegistroPatronalPersistenceRequest extends BaseModel{
    Collection<McltRegistroPatronal> registroPatronal = new ArrayList();
}
