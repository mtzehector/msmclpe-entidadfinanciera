package mx.gob.imss.dpes.entidadfinancieraback.service;

import java.util.LinkedList;
import java.util.List;
import mx.gob.imss.dpes.baseback.service.BaseCRUDService;
import mx.gob.imss.dpes.support.config.CustomSpringBeanAutowiringInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.interceptor.Interceptors;
import javax.ws.rs.ext.Provider;
import mx.gob.imss.dpes.entidadfinancieraback.entity.McltDocumento;
import mx.gob.imss.dpes.entidadfinancieraback.repository.DocumentoRepository;

@Provider
@Interceptors(CustomSpringBeanAutowiringInterceptor.class)
public class DocumentoPersistenceService extends BaseCRUDService<McltDocumento, McltDocumento, Long, Long> {

    @Autowired
    private DocumentoRepository repository;

    @Override
    public JpaSpecificationExecutor<McltDocumento> getRepository() {
        return repository;
    }

    @Override
    public JpaRepository<McltDocumento, Long> getJpaRepository() {
        return repository;
    }

    

    

    public List<McltDocumento> findByPersona(Long cvePersona) {
        List<McltDocumento> documentoList = new LinkedList<>();
        documentoList = this.repository.findByCvePersona(cvePersona);
        if (documentoList == null) {
            documentoList = new LinkedList<>();
        }
        return documentoList;
   }
    
    public List<McltDocumento> findByEntidadFinanciera(Long cvePersona) {
        List<McltDocumento> documentoList = new LinkedList<>();
        documentoList = this.repository.findByCveEntidadFinanciera(cvePersona);
        if (documentoList == null) {
            documentoList = new LinkedList<>();
        }
        return documentoList;
   }
    
    public McltDocumento findByCvePersonaAndTipoDocumento(Long cvePersona, Long tipoDocumento) {
        McltDocumento documento = this.repository.findByCvePersonaAndTipoDocumento(cvePersona,tipoDocumento);
        return documento;
   }
    
    public McltDocumento findByCveEntidadFinancieraAndTipoDocumento(Long cveEntidadFinanciera, Long tipoDocumento) {
        McltDocumento documento = this.repository.findByCveEntidadFinancieraAndTipoDocumento(cveEntidadFinanciera,tipoDocumento);
        return documento;
   }

}
