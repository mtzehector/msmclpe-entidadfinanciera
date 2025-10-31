/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.entidadfinancieraback.repository;


import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcMotivoSuspension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * @author Diego Velazquez
 */
public interface MclcMotivoSuspensionRepository extends JpaRepository<MclcMotivoSuspension, Short>,
        JpaSpecificationExecutor<MclcMotivoSuspension>{  
}
