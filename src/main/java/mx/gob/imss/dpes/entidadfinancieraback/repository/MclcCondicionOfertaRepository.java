/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.entidadfinancieraback.repository;

import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcCondicionOferta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Diego Velazquez
 */
@Transactional
public interface MclcCondicionOfertaRepository extends JpaRepository<MclcCondicionOferta, Long>,
        JpaSpecificationExecutor<MclcCondicionOferta>{

    @Query(value = "UPDATE MclcCondicionOferta SET porTasaAnual = :catMaximo, actualizacionRegistro = CURRENT_DATE")
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    int actualizaPorTasaAnual(@Param(value = "catMaximo") BigDecimal catMaximo);

    @Query(value = "UPDATE MclcCondicionOferta SET porCat = :catMaximo, actualizacionRegistro = CURRENT_DATE WHERE porCat > :catMaximo")
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    int actualizaPorCat(@Param(value = "catMaximo") BigDecimal catMaximo);

    @Query("SELECT mclcCondicionOferta FROM MclcCondicionOferta AS mclcCondicionOferta " +
            "WHERE mclcCondicionOferta.porCat > :catMaximo " +
            "ORDER BY mclcCondicionOferta.id DESC")
    List<MclcCondicionOferta> obtieneCondicionesConCatEFSuperiorAlCatMaximo(@Param(value = "catMaximo") BigDecimal catMaximo);

    @Query("SELECT mclcCondicionOferta FROM MclcCondicionOferta AS mclcCondicionOferta " +
            "WHERE mclcCondicionOferta.mclcEntidadFinanciera = :mclcEntidadFinanciera " +
            "AND mclcCondicionOferta.bajaRegistro IS NULL " +
            "ORDER BY mclcCondicionOferta.mclcPlazo ASC")
    List<MclcCondicionOferta> obtieneCondicionesPorMclcEntidadFinanciera(@Param(value = "mclcEntidadFinanciera") Long idEntidadFinanciera);
}
