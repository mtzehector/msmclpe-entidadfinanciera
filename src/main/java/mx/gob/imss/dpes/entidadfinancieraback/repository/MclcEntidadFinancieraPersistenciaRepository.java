/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.entidadfinancieraback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcEntidadFinancieraPersistencia;

/**
 *
 * @author Diego Velazquez
 */
public interface MclcEntidadFinancieraPersistenciaRepository
		extends JpaRepository<MclcEntidadFinancieraPersistencia, Long>, JpaSpecificationExecutor<MclcEntidadFinancieraPersistencia> {

	
}
