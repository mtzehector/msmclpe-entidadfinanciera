/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.entidadfinancieraback.repository;

import java.util.List;
import javax.ws.rs.ext.Provider;
import mx.gob.imss.dpes.entidadfinancieraback.entity.EntidadFinanciera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * @author Diego Velazquez
 */
public interface EntidadFinancieraRepository extends JpaRepository<EntidadFinanciera, Long>,
        JpaSpecificationExecutor<EntidadFinanciera>{
        List<EntidadFinanciera> findByRegistroPatronal(String regPatronal);
        List<EntidadFinanciera> findByCveEntidadFinancieraSipre(String cveEntidadFinancieraSipre);

}
