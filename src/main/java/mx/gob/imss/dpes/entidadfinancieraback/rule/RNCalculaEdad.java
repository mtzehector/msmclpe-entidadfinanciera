/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.entidadfinancieraback.rule;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Level;
import javax.ws.rs.ext.Provider;
import lombok.Getter;
import mx.gob.imss.dpes.common.model.BaseModel;
import mx.gob.imss.dpes.common.rule.BaseRule;

/**
 *
 * @author Diego Velazquez
 */
@Provider
public class RNCalculaEdad extends BaseRule<RNCalculaEdad.Input, RNCalculaEdad.Output>{

    
    @Override
    public Output apply(Input input) {
    
      LocalDate fecActual = LocalDate.now();
log.log(Level.INFO,"input  [{0}]",input.fechaNacimiento);
      LocalDate  fecNacimiento= input.fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
      log.log(Level.INFO,"String {0}",fecNacimiento);     
      Period periodo = Period.between(fecNacimiento, fecActual);
      log.info("periodo "+ periodo.getYears());
     return new Output(periodo.getYears());
    }

    public class Input extends BaseModel {
           
      @Getter private final transient Date fechaNacimiento;
        
        public Input(Date fechaNacimiento) {
            this.fechaNacimiento = fechaNacimiento;
        }
    }

    public class Output extends BaseModel {

        @Getter private final transient int edad;
 
        public Output(int edad) {
            this.edad = edad;
        }
    } 
}
