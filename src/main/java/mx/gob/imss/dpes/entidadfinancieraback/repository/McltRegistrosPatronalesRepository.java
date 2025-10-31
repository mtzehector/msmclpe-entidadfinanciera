/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.gob.imss.dpes.entidadfinancieraback.repository;

import java.util.List;
import mx.gob.imss.dpes.entidadfinancieraback.entity.McltRegistroPatronal;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * @author juanf.barragan
 */
public interface McltRegistrosPatronalesRepository extends JpaRepository<McltRegistroPatronal, Long>, JpaSpecificationExecutor<McltRegistroPatronal> {

    McltRegistroPatronal findByRegistroPatronal(String registroPatronal);
    List<McltRegistroPatronal> findByMclcEntidadFinanciera(Long mclcEntidadFinanciera);
}
