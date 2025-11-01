package mx.gob.imss.dpes.entidadfinancieraback.validation;

import mx.gob.imss.dpes.interfaces.entidadfinanciera.model.ConciliacionRequest;

import javax.ws.rs.ext.Provider;

@Provider
public class ValidaConciliacionRequest {

    public boolean validaRequest(ConciliacionRequest req){

        if (req == null)
            return false;

        if (req.getCveEntidadFinanciera() == null)
            return false;

        if (req.getPeriodo() == null || req.getPeriodo().isEmpty())
            return false;

        return true;
    }

    public boolean validaPeriodoYEntidades(ConciliacionRequest req){
        if (req == null)
            return false;

        if (req.getArregloIdEntidadFinanciera() == null || req.getArregloIdEntidadFinanciera().length == 0)
            return false;

        if (req.getPeriodo() == null || req.getPeriodo().isEmpty())
            return false;

        return true;
    }

    public boolean validaEntidadSipreYPeriodo(ConciliacionRequest req){

        if (req == null)
            return false;

        if (req.getCveEntidadFinancieraSipre() == null || req.getCveEntidadFinancieraSipre().isEmpty())
            return false;

        if (req.getPeriodo() == null || req.getPeriodo().isEmpty())
            return false;

        return true;
    }
    
    public boolean validaEntidadSipreYPeriodo(String idEntidad, String periodo){

        if (idEntidad == null)
            return false;

        if (periodo == null)
            return false;
        
        if (periodo == null || periodo.isEmpty())
            return false;

        if (idEntidad == null || idEntidad.isEmpty())
            return false;

        return true;
    }

    public boolean validaPeriodo(ConciliacionRequest req){
        if (req == null)
            return false;

        if (req.getPeriodo() == null || req.getPeriodo().isEmpty())
            return false;

        return true;
    }

}
