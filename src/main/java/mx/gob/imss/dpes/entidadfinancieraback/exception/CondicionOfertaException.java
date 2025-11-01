package mx.gob.imss.dpes.entidadfinancieraback.exception;

import mx.gob.imss.dpes.common.exception.BusinessException;

public class CondicionOfertaException extends BusinessException {

    public final static String ERROR_DESCONOCIDO_EN_EL_SERVICIO = "err003";
    public final static String MENSAJE_DE_SOLICITUD_INCORRECTO = "err004";
    public final static String ERROR_DE_ESCRITURA_EN_BD = "err005";
    public final static String ERROR_DE_LECTURA_DE_BD = "err006";

    public CondicionOfertaException(String causa) {
        super(causa);
    }
}
