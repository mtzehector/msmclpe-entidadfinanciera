/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.entidadfinancieraback.assembler;

import javax.ws.rs.ext.Provider;
import mx.gob.imss.dpes.common.assembler.BaseAssembler;
import mx.gob.imss.dpes.entidadfinancieraback.entity.McltDocumento;
import mx.gob.imss.dpes.interfaces.documento.model.Documento;

/**
 *
 * @author eduardo.loyo
 */
@Provider
public class DocumentoAssembler extends BaseAssembler<McltDocumento, Documento> {


    @Override
    public Documento assemble(McltDocumento source) {
        Documento doc = new Documento();
        doc.setId( source.getId());
        doc.setCveSolicitud(source.getCveSolicitud());
        doc.setRefSello(source.getRefSello());
        doc.setRefDocBoveda(source.getRefDocBoveda());
        doc.setRefDocumento(source.getRefDocumento());
        doc.setSello(source.getRefSello());
        doc.setCvePersona(source.getCvePersona());
        doc.setCveEntidadFinanciera(source.getCveEntidadFinanciera());
        return doc;
    }

}
