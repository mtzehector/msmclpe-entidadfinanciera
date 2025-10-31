package mx.gob.imss.dpes.entidadfinancieraback.repository;

import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcBeneficios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficiosRepository extends JpaRepository<MclcBeneficios, Long>, JpaSpecificationExecutor<MclcBeneficios> {

}
