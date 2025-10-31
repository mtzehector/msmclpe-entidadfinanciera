/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.entidadfinancieraback.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.gob.imss.dpes.common.enums.SexoEnum;
import mx.gob.imss.dpes.common.enums.TipoSimulacionEnum;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.EntidadFederativa;
import mx.gob.imss.dpes.interfaces.pensionado.model.Pensionado;
import mx.gob.imss.dpes.entidadfinancieraback.config.BaseTest;
import mx.gob.imss.dpes.entidadfinancieraback.model.PlazosRequest;
import mx.gob.imss.dpes.entidadfinancieraback.service.ObtenerPlazosService;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author antonio
 */
public class MclcCondicionOfertaRepositoryTest  extends BaseTest{
  
  @Autowired
  MclcCondicionOfertaRepository_oldie repository;

  @Test
  public void testLoadContext() {
    assertNotNull(repository);
  }
  
  @Test
  public void testMonto() throws BusinessException {
      try {
          ObtenerPlazosService service = new ObtenerPlazosService();
          
          service.setRepository(repository);
          
          PlazosRequest request = new PlazosRequest();
          
          Pensionado pensionado = new Pensionado ();
          
          
          request.setPensionado(pensionado);
          
          pensionado.setSexo(SexoEnum.MASCULINO);
          //pensionado.setEdad(80);
          pensionado.setFechaNacimiento(new SimpleDateFormat("yyyy-MM-dd").parse("1939-07-27"));
          EntidadFederativa entidadFederativa = new EntidadFederativa();
          entidadFederativa.setCveEntidadFederativa("09");
          pensionado.setEntidadFederativa(entidadFederativa);
          request.setMonto(11000);
          request.setCapacidadCredito(150000);
          request.setTipoSimulacion(TipoSimulacionEnum.MONTO);
          
          System.out.println(service.obtenerOfertas(request));
      } catch (ParseException ex) {
          Logger.getLogger(MclcCondicionOfertaRepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
      }
    
 
  }
}
