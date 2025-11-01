package mx.gob.imss.dpes.entidadfinancieraback.validation;

import mx.gob.imss.dpes.interfaces.entidadfinanciera.model.PrestadorServiciosEF;

import javax.ws.rs.ext.Provider;
import java.util.List;

@Provider
public class ValidacionPrestadorServiciosEF {
    public boolean esPrestadorServiciosEFValido(PrestadorServiciosEF source){
        if(source == null) {
            return false;
        }

        if (
                source.getCveEntidadFinanciera() != null &&
                        source.getCveEntidadFinanciera() > 0 &&
                        source.getCveTipoPrestadorServicios() != null &&
                        source.getCveTipoPrestadorServicios() > 0 &&
                        source.getRegistroPatronal() != null &&
                        source.getRegistroPatronal().trim().length() > 0 &&
                        source.getRfc() != null &&
                        source.getRfc().trim().length() > 0 &&
                        source.getRazonSocial() != null &&
                        source.getRazonSocial().trim().length() > 0 &&
                        source.getDireccion() != null &&
                        source.getDireccion().trim().length() > 0 &&
                        source.getCorreoElectronico() != null &&
                        source.getCorreoElectronico().trim().length() > 0 &&
                        source.getPaginaWeb() != null &&
                        source.getPaginaWeb().trim().length() > 0

        )
            return true;

        return false;
    }

    public boolean sonPrestadoresServiciosEFValidos(List<PrestadorServiciosEF> listaPrestadorServiciosEF){
        if(listaPrestadorServiciosEF == null) {
            return false;
        }
        for (PrestadorServiciosEF ps: listaPrestadorServiciosEF) {
            if (!this.esPrestadorServiciosEFValido(ps))
                return false;
        }
        return true;
    }
}
