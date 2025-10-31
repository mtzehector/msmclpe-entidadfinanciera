package mx.gob.imss.dpes.entidadfinancieraback.repository;

import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcPrestadorServiciosEF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrestadorServiciosEFRepository extends JpaRepository<MclcPrestadorServiciosEF, Long>, JpaSpecificationExecutor<MclcPrestadorServiciosEF> {

    @Query("SELECT ps FROM MclcPrestadorServiciosEF ps " +
            "WHERE ps.cveEntidadFinanciera=:cveEntidadFinanciera AND ps.bajaRegistro is null " +
            "ORDER BY ps.id DESC")
    List<MclcPrestadorServiciosEF> buscarPorCveEntidadFinanciera(@Param("cveEntidadFinanciera") Long cveEntidadFinanciera);
}
