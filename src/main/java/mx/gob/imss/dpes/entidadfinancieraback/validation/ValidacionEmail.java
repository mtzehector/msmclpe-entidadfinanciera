package mx.gob.imss.dpes.entidadfinancieraback.validation;

import mx.gob.imss.dpes.common.rule.utils.EmailUtil;

import javax.ws.rs.ext.Provider;

@Provider
public class ValidacionEmail {

    public boolean esValidoEmail(String email){

        if (EmailUtil.validarEmail(email))
            return true;

        return false;
    }
}
