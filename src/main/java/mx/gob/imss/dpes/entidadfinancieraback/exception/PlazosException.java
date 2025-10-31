/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.entidadfinancieraback.exception;

import mx.gob.imss.dpes.common.exception.AlternateFlowException;
import mx.gob.imss.dpes.common.exception.BusinessException;

/**
 *
 * @author osiris.hernandez
 */
public class PlazosException extends AlternateFlowException{
  private final static String KEY = "err001";
  public final static String NOTFOUND = "err002";
  public final static String ERROR_DESCONOCIDO_EN_EL_SERVICIO = "err003";
  
  public PlazosException() {
    super(KEY);
  }
  public PlazosException(String resource) {
    super(resource);
  }
}

