package mx.gob.imss.dpes.entidadfinancieraback.assembler;

import mx.gob.imss.dpes.common.assembler.BaseAssembler;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcPrestadorServiciosEF;
import mx.gob.imss.dpes.interfaces.entidadfinanciera.model.PrestadorServiciosEF;

import javax.ws.rs.ext.Provider;

@Provider
public class PrestadorServiciosEFBeanFRomModelAssembler extends BaseAssembler<MclcPrestadorServiciosEF, PrestadorServiciosEF> {
    @Override
    public PrestadorServiciosEF assemble(MclcPrestadorServiciosEF source) {
        if(source == null)
            return null;

        PrestadorServiciosEF ps = new PrestadorServiciosEF();
        ps.setId(source.getId());
        ps.setCveEntidadFinanciera(source.getCveEntidadFinanciera());
        ps.setCveTipoPrestadorServicios(source.getCveTipoPrestadorServicios());
        ps.setRegistroPatronal(source.getRegistroPatronal());
        ps.setRfc(source.getRfc());
        ps.setRazonSocial(source.getRazonSocial());
        ps.setDireccion(source.getDireccion());
        ps.setCorreoElectronico(source.getCorreoElectronico());
        ps.setPaginaWeb(source.getPaginaWeb());
        ps.setBajaRegistro(source.getBajaRegistro());
        return ps;
    }
}
