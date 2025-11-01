package mx.gob.imss.dpes.entidadfinancieraback.repository;

import mx.gob.imss.dpes.entidadfinancieraback.model.ImporteFallecidos;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class ImporteFallecidosRepository {

    @PersistenceContext
    private EntityManager em;

    private String obtenerConsultaSQL(){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT  ");
        sb.append("nvl(SUM(resumen.importe_fallecidos),0) as importe_fallecidos  ");
        sb.append("FROM mgpmclpe04.mclt_resumen_conciliacion resumen  ");
        sb.append("INNER JOIN mgpmclpe04.mclc_entidad_financiera entidad_financiera  ");
        sb.append("ON entidad_financiera.cve_entidad_financiera_sipre = resumen.cve_entidad_financiera_sipre  ");
        sb.append("WHERE resumen.periodo = :periodo AND entidad_financiera.cve_entidad_financiera = :entidad");
        return sb.toString();
    }

    public ImporteFallecidos ejecutarConsultaSQL(String periodo, Long entidad){
        try{
            Query query = em.createNativeQuery(this.obtenerConsultaSQL());
            query.setParameter("periodo", periodo);
            query.setParameter("entidad", entidad);
            return (ImporteFallecidos)
                    query.unwrap(org.hibernate.Query.class)
                    .setResultTransformer(
                            Transformers.aliasToBean(ImporteFallecidos.class)
                    ).uniqueResult();
        }catch (Exception e){
            throw e;
        }
    }

}
