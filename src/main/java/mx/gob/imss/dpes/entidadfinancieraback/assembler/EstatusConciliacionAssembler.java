package mx.gob.imss.dpes.entidadfinancieraback.assembler;

import mx.gob.imss.dpes.common.assembler.BaseAssembler;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcEstatusConciliacion;
import mx.gob.imss.dpes.interfaces.estatusConciliacion.model.EstatusConciliacionRequest;

import javax.ws.rs.ext.Provider;

@Provider
public class EstatusConciliacionAssembler extends BaseAssembler<MclcEstatusConciliacion, EstatusConciliacionRequest> {


    @Override
    public EstatusConciliacionRequest assemble(MclcEstatusConciliacion source) {
        if(source == null)
            return null;
        EstatusConciliacionRequest model = new EstatusConciliacionRequest();
        model.setId(source.getId());
        model.setPeriodo(source.getPeriodo());
        model.setActivo(source.getActivo());
        return model;
    }
}
