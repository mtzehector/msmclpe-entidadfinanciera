package mx.gob.imss.dpes.entidadfinancieraback.assembler;

import mx.gob.imss.dpes.common.assembler.BaseAssembler;
import mx.gob.imss.dpes.entidadfinancieraback.model.ImporteFallecidos;
import mx.gob.imss.dpes.interfaces.entidadfinanciera.model.RetencionDelegacionImporteFallecidos;

import javax.ws.rs.ext.Provider;

@Provider
public class ImporteFallecidosAssembler extends BaseAssembler<ImporteFallecidos, RetencionDelegacionImporteFallecidos> {


    @Override
    public RetencionDelegacionImporteFallecidos assemble(ImporteFallecidos source) {
        if (source == null)
            return null;

        RetencionDelegacionImporteFallecidos importeFallecidos = new RetencionDelegacionImporteFallecidos();
        importeFallecidos.setImporteFallecidos(source.getIMPORTE_FALLECIDOS() == null? 0.0 : source.getIMPORTE_FALLECIDOS().doubleValue());
        return importeFallecidos;
    }
}
