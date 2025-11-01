package mx.gob.imss.dpes.entidadfinancieraback.repository;

import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcEstatusConciliacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstatusConciliacionRepository extends JpaRepository<MclcEstatusConciliacion, Long> , JpaSpecificationExecutor<MclcEstatusConciliacion> {

    MclcEstatusConciliacion findByPeriodo(@Param(value = "periodo") String periodo);


}
