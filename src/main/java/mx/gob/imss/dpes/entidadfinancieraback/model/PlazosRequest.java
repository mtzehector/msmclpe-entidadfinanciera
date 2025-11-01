/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.entidadfinancieraback.model;

import lombok.Getter;
import lombok.Setter;
import mx.gob.imss.dpes.common.enums.TipoSimulacionEnum;
import mx.gob.imss.dpes.common.model.IdentityBaseModel;
import mx.gob.imss.dpes.interfaces.pensionado.model.Pensionado;

/**
 *
 * @author Diego Velazquez
 */
public class PlazosRequest extends IdentityBaseModel<Long> {
    
    @Getter @Setter Pensionado pensionado;
    @Getter @Setter double monto;
    @Getter @Setter double capacidadCredito;
    @Getter @Setter Double descuentoMensual;
    @Getter @Setter TipoSimulacionEnum tipoSimulacion;
    @Getter @Setter Long sesion;

}
