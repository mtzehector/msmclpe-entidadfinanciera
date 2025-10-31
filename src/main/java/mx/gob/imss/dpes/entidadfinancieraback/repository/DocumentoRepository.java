package mx.gob.imss.dpes.entidadfinancieraback.repository;

import java.util.List;
import mx.gob.imss.dpes.entidadfinancieraback.entity.McltDocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DocumentoRepository extends JpaRepository<McltDocumento, Long>,
    JpaSpecificationExecutor<McltDocumento> {
    McltDocumento findByCvePersonaAndTipoDocumento(Long cvePersona,Long tipoDocumento);
    McltDocumento findByCveEntidadFinancieraAndTipoDocumento(Long cvePersona,Long tipoDocumento);
    List<McltDocumento> findByCvePersona(Long cvePersona);
    List<McltDocumento> findByCveEntidadFinanciera(Long cveEntidadFinanciera);
    
}
