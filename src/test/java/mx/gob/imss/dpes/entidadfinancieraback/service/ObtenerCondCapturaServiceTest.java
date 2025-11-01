/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.entidadfinancieraback.service;

import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.entidadfinancieraback.assembler.OfertaCondCapturaAssembler;
import mx.gob.imss.dpes.entidadfinancieraback.config.BaseTest;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcEntidadFinanciera;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import mx.gob.imss.dpes.entidadfinancieraback.repository.MclcCondicionOfertaRepository_oldie;

/**
 *
 * @author Diego Velazquez
 */
public class ObtenerCondCapturaServiceTest  extends BaseTest{
  
  @Autowired
  MclcCondicionOfertaRepository_oldie repository;

   @Test
  public void testSomeMethod() throws BusinessException {
     
        ObtenerCondCapturaService service = new ObtenerCondCapturaService();
          
        OfertaCondCapturaAssembler assembler = new OfertaCondCapturaAssembler();
        // Falta de CDI en Test
        service.setRepository(repository);
        service.setAssembler(assembler);
        MclcEntidadFinanciera request = new MclcEntidadFinanciera();
        request.setId(1L);
        System.out.println(service.obtenerOfertaCondCaptura(request));        
    }
}
