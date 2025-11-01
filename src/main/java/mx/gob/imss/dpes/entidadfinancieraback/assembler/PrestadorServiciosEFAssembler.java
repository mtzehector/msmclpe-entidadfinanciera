package mx.gob.imss.dpes.entidadfinancieraback.assembler;

import mx.gob.imss.dpes.common.assembler.BaseAssembler;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcPrestadorServiciosEF;
import mx.gob.imss.dpes.interfaces.entidadfinanciera.model.PrestadorServiciosEF;

import javax.ws.rs.ext.Provider;
import java.util.Date;

@Provider
public class PrestadorServiciosEFAssembler extends BaseAssembler<PrestadorServiciosEF, MclcPrestadorServiciosEF> {

    @Override
    public MclcPrestadorServiciosEF assemble(PrestadorServiciosEF source) {

        if (source == null)
            return null;

        MclcPrestadorServiciosEF mclcPrestadorServicios = new MclcPrestadorServiciosEF();
        mclcPrestadorServicios.setId(source.getId());
        mclcPrestadorServicios.setCveEntidadFinanciera(source.getCveEntidadFinanciera());
        mclcPrestadorServicios.setCveTipoPrestadorServicios(source.getCveTipoPrestadorServicios());
        mclcPrestadorServicios.setRegistroPatronal(source.getRegistroPatronal());
        mclcPrestadorServicios.setRfc(source.getRfc());
        mclcPrestadorServicios.setRazonSocial(source.getRazonSocial());
        mclcPrestadorServicios.setDireccion(source.getDireccion());
        mclcPrestadorServicios.setCorreoElectronico(source.getCorreoElectronico());
        mclcPrestadorServicios.setPaginaWeb(source.getPaginaWeb());
        if (source.getBajaRegistro() != null){
            mclcPrestadorServicios.setBajaRegistro(source.getBajaRegistro());
        }
        return mclcPrestadorServicios;
    }
}
