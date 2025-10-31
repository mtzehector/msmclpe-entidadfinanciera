package mx.gob.imss.dpes.entidadfinancieraback.exception;

import mx.gob.imss.dpes.common.exception.BusinessException;

public class CargaInformacionException extends BusinessException {

    public final static String ERROR_DESCONOCIDO_EN_EL_SERVICIO = "err003";
    public final static String MENSAJE_DE_SOLICITUD_INCORRECTO = "err004";
    public final static String ERROR_DE_LECTURA_DE_BD = "err006";
    public final static String ERROR_AL_EJECUTAR_INSERT_UPDATE = "err009";

    public CargaInformacionException(String messageKey) {
        super(messageKey);
    }
}
