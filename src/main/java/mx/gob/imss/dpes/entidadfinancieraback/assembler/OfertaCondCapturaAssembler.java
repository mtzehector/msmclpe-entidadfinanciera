/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.entidadfinancieraback.assembler;

import javax.ws.rs.ext.Provider;
import mx.gob.imss.dpes.baseback.assembler.BaseAssembler;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcCondicionOfertaOldie;
import mx.gob.imss.dpes.interfaces.entidadfinanciera.model.OfertaCondCaptura;

/**
 *
 * @author Diego
 */
@Provider
public class OfertaCondCapturaAssembler extends BaseAssembler<MclcCondicionOfertaOldie, OfertaCondCaptura, Long, Long> {

  @Override
  public MclcCondicionOfertaOldie toEntity(OfertaCondCaptura source) {
    return new MclcCondicionOfertaOldie();
  }

  @Override
  public Long toPKEntity(Long source) {
    return source;
  }

  @Override
  public OfertaCondCaptura assemble(MclcCondicionOfertaOldie source) {

    OfertaCondCaptura oferta = new OfertaCondCaptura();
    
    oferta.setId(source.getId());
    oferta.setCat( source.getPorCat() != null ? source.getPorCat().doubleValue() : 0.0 );
    oferta.setTasaAnual(source.getPorTasaAnual().doubleValue());
    oferta.getPlazo().setId(source.getMclcPlazo().getId());
    oferta.getPlazo().setNumPlazo(source.getMclcPlazo().getNumMeses());
    oferta.getPlazo().setDescripcion(source.getMclcPlazo().getDescripcion());
    return oferta; 
    }
 
  @Override
  public Long assemblePK(Long source) {
    return source;
  } 
    }
