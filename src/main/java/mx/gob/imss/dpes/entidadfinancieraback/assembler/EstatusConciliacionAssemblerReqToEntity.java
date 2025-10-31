package mx.gob.imss.dpes.entidadfinancieraback.assembler;

import mx.gob.imss.dpes.common.assembler.BaseAssembler;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcEstatusConciliacion;
import mx.gob.imss.dpes.interfaces.estatusConciliacion.model.EstatusConciliacionRequest;

import javax.ws.rs.ext.Provider;

@Provider
public class EstatusConciliacionAssemblerReqToEntity extends BaseAssembler<EstatusConciliacionRequest, MclcEstatusConciliacion> {
    @Override
    public MclcEstatusConciliacion assemble(EstatusConciliacionRequest source) {

        if(source == null)
            return null;

        MclcEstatusConciliacion estatus = new MclcEstatusConciliacion();
        if (source.getId() != null)
            estatus.setId(source.getId());
        estatus.setPeriodo(source.getPeriodo());
        estatus.setActivo(source.getActivo());
        return estatus;
    }
}
