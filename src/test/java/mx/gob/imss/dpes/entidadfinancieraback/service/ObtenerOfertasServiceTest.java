/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.entidadfinancieraback.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.gob.imss.dpes.common.enums.SexoEnum;
import mx.gob.imss.dpes.common.enums.TipoSimulacionEnum;
import mx.gob.imss.dpes.common.exception.AlternateFlowException;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.EntidadFederativa;
import mx.gob.imss.dpes.common.model.PageRequestModel;
import mx.gob.imss.dpes.interfaces.pensionado.model.Pensionado;
import mx.gob.imss.dpes.common.rule.MontoTotalRule;
import mx.gob.imss.dpes.common.rule.PagoMensualRule;
import mx.gob.imss.dpes.entidadfinancieraback.assembler.OfertaAssembler;
import mx.gob.imss.dpes.entidadfinancieraback.config.BaseTest;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcCondicionOfertaOldie;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcPlazo;
import mx.gob.imss.dpes.interfaces.entidadfinanciera.model.Oferta;
import mx.gob.imss.dpes.entidadfinancieraback.model.OfertasRequest;
import mx.gob.imss.dpes.entidadfinancieraback.model.PlazosRequest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import mx.gob.imss.dpes.entidadfinancieraback.repository.MclcCondicionOfertaRepository_oldie;

/**
 *
 * @author Diego Velazquez
 */
public class ObtenerOfertasServiceTest  extends BaseTest{
  
  @Autowired
  MclcCondicionOfertaRepository_oldie repository;

   @Test
  public void testMonto() throws BusinessException, AlternateFlowException {
      try {
          ObtenerOfertasService service = new ObtenerOfertasService();
          
          OfertaAssembler assembler = new OfertaAssembler();
          PagoMensualRule pagoMensualRule = new PagoMensualRule();
          // Falta de CDI en Test
          service.setRepository(repository);
          service.setAssembler(assembler);
          service.setPagoMensualRule(pagoMensualRule);
          
          
          OfertasRequest request = new OfertasRequest();
          
          Pensionado pensionado = new Pensionado ();
          
          
          request.setPensionado(pensionado);
          
          pensionado.setSexo(SexoEnum.MASCULINO);
          //pensionado.setEdad(80);
          pensionado.setFechaNacimiento(new SimpleDateFormat("yyyy-MM-dd").parse("1939-07-27"));
          EntidadFederativa entidadFederativa = new EntidadFederativa();
          entidadFederativa.setCveEntidadFederativa("09");
          pensionado.setEntidadFederativa(entidadFederativa);
          request.setMonto(11000);
          request.setDescuentoMensual(0d);
          //request.setCapacidadCredito(5000);
          request.setPlazo(5L);
          
          request.setTipoSimulacion(TipoSimulacionEnum.MONTO);
          
          PageRequestModel<OfertasRequest> pageRequest = new PageRequestModel<>();
          
          pageRequest.setModel(request);
          pageRequest.setPage(1);
          
          Page<Oferta> page = service.obtenerOfertas(pageRequest);
          
          System.out.println( page.getTotalElements() );
          System.out.println( page.getContent() );
          System.out.println( page.getNumber() );
      } catch (ParseException ex) {
          Logger.getLogger(ObtenerOfertasServiceTest.class.getName()).log(Level.SEVERE, null, ex);
      }
  } 
  
  
  @Test
  public void testDescuento() throws BusinessException, AlternateFlowException {
      try {
          ObtenerOfertasService service = new ObtenerOfertasService();
          
          OfertaAssembler assembler = new OfertaAssembler();
          PagoMensualRule pagoMensualRule = new PagoMensualRule();
          MontoTotalRule montoTotalRule = new MontoTotalRule();
          // Falta de CDI en Test
          service.setRepository(repository);
          service.setAssembler(assembler);
          service.setPagoMensualRule(pagoMensualRule);
          service.setMontoTotalRule(montoTotalRule);
          
          
          OfertasRequest request = new OfertasRequest();
          
          Pensionado pensionado = new Pensionado ();
          
          
          request.setPensionado(pensionado);
          
          pensionado.setSexo(SexoEnum.AMBOS);
          //pensionado.setEdad(80);
          pensionado.setFechaNacimiento(new SimpleDateFormat("yyyy-MM-dd").parse("1939-07-27"));
          EntidadFederativa entidadFederativa = new EntidadFederativa();
          entidadFederativa.setCveEntidadFederativa("19");
          pensionado.setEntidadFederativa(entidadFederativa);
          request.setMonto(11000);
          request.setDescuentoMensual(5000d);
          //request.setCapacidadCredito(5000);
          request.setPlazo(3L);
          
          request.setTipoSimulacion(TipoSimulacionEnum.DESCUENTO_MENSUAL);
          
          PageRequestModel<OfertasRequest> pageRequest = new PageRequestModel<>();
          
          pageRequest.setModel(request);
          pageRequest.setPage(1);
          
          Page<Oferta> page = service.obtenerOfertas(pageRequest);
          
          System.out.println( page.getTotalElements() );
          System.out.println( page.getContent() );
          System.out.println( page.getNumber() );
      } catch (ParseException ex) {
          Logger.getLogger(ObtenerOfertasServiceTest.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
  
  
}
