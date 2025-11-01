/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.entidadfinancieraback.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcEntidadFinanciera;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 *
 * @author Diego Velazquez
 */
public interface MclcEntidadFinancieraRepository
		extends JpaRepository<MclcEntidadFinanciera, Long>, JpaSpecificationExecutor<MclcEntidadFinanciera> {

	@EntityGraph(attributePaths = {"mclcEstadoEf", "mclcMotivoSuspension", "mclcCondicionOfertaCollection", "mclcCondicionOfertaCollection.mclcPlazo"})
	MclcEntidadFinanciera findById(Long id);


	@EntityGraph(attributePaths = {"mclcCondicionOfertaCollection", "mclcCondicionOfertaCollection.mclcPlazo"})
	@Query(
		"SELECT mclcEntidadFinanciera FROM MclcEntidadFinanciera AS mclcEntidadFinanciera " +
		"LEFT JOIN mclcEntidadFinanciera.mclcCondicionOfertaCollection AS mclcCondicionOfertaOldie " +
		"WHERE mclcEntidadFinanciera.id = :idEntidadFinanciera " +
		"AND mclcEntidadFinanciera.bajaRegistro IS NULL " +
		"AND mclcCondicionOfertaOldie.bajaRegistro IS NULL "
	)
	MclcEntidadFinanciera obtenerEFConCondicionesOferta(@Param("idEntidadFinanciera") Long idEntidadFinanciera);

	@EntityGraph(attributePaths = {"mclcCondicionOfertaCollection", "mclcCondicionOfertaCollection.mclcBeneficioCollection"})
	@Query(
			"SELECT mclcEntidadFinanciera FROM MclcEntidadFinanciera AS mclcEntidadFinanciera " +
					"LEFT JOIN mclcEntidadFinanciera.mclcCondicionOfertaCollection AS mclcCondicionOfertaOldie " +
					"LEFT JOIN mclcCondicionOfertaOldie.mclcBeneficioCollection AS mclcBeneficio " +
					"WHERE mclcEntidadFinanciera.id = :idEntidadFinanciera " +
					"AND mclcEntidadFinanciera.bajaRegistro IS NULL " +
					"AND mclcCondicionOfertaOldie.bajaRegistro IS NULL " +
					"AND mclcBeneficio.bajaRegistro IS NULL "
	)
	MclcEntidadFinanciera obtenerEFConCondicionesOfertaYBeneficios(@Param("idEntidadFinanciera") Long idEntidadFinanciera);

	@Query(
			"SELECT mclcEntidadFinanciera FROM MclcEntidadFinanciera AS mclcEntidadFinanciera " +
					"WHERE mclcEntidadFinanciera.id = :idEntidadFinanciera " +
					"AND mclcEntidadFinanciera.bajaRegistro IS NULL "
	)
	MclcEntidadFinanciera obtenerEF(@Param("idEntidadFinanciera") Long idEntidadFinanciera);
}
