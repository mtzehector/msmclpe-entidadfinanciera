package mx.gob.imss.dpes.entidadfinancieraback.repository;


import mx.gob.imss.dpes.entidadfinancieraback.model.ReporteRetencionPorDelegacion;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class RetencionPorDelegacionRepository {

    @PersistenceContext
    private EntityManager em;


    private String obtenerConsultaSQL(){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT  ");
        sb.append("delegacion.num_delegacion as numerodelegacion,delegacion.des_delegacion as delegacion,");
        sb.append("NVL(tblTotales.total, 0) as total,NVL(tblTotales.netopagar,0) as netopagar,");
        sb.append("tblTotales.numeroproveedor as numeroproveedor,tblTotales.nombrecomercial as nombrecomercial  ");
        sb.append("FROM mgpmclpe04.mclc_delegacion delegacion  ");
        sb.append("LEFT OUTER JOIN (");
        sb.append("SELECT  ");
        sb.append("delegacion.num_delegacion as num_delegacion,delegacion.des_delegacion as des_delegacion,");
        sb.append("resumen.prestamos_pagados as total,resumen.importe_pagados as netopagar,");
        sb.append("entidad_financiera.numero_proveedor as numeroproveedor,entidad_financiera.nom_comercial as nombrecomercial  ");
        sb.append("FROM mgpmclpe04.mclc_delegacion delegacion  ");
        sb.append("INNER JOIN mgpmclpe04.mclt_resumen_conciliacion resumen  ");
        sb.append("ON delegacion.cve_delegacion = resumen.cve_delegacion  ");
        sb.append("INNER JOIN mgpmclpe04.mclc_entidad_financiera entidad_financiera  ");
        sb.append("ON resumen.cve_entidad_financiera_sipre = entidad_financiera.cve_entidad_financiera_sipre  ");
        sb.append("WHERE resumen.periodo = :periodo AND entidad_financiera.cve_entidad_financiera = :entidad  ");
        sb.append("GROUP BY ");
        sb.append("delegacion.num_delegacion,");
        sb.append("delegacion.des_delegacion,");
        sb.append("resumen.prestamos_pagados,");
        sb.append("resumen.importe_pagados,");
        sb.append("entidad_financiera.numero_proveedor,");
        sb.append("entidad_financiera.nom_comercial  ");
        sb.append(") tblTotales  ");
        sb.append("ON delegacion.num_delegacion = tblTotales.num_delegacion  ");
        sb.append("ORDER BY delegacion.num_delegacion ASC");
        return sb.toString();
    }

    public List<ReporteRetencionPorDelegacion> ejecutarConsultaSQL(String periodo, Long entidad){
        try{
            Query query = em.createNativeQuery(this.obtenerConsultaSQL());
            query.setParameter("periodo", periodo);
            query.setParameter("entidad", entidad);
            return query.unwrap(org.hibernate.Query.class)
                    .setResultTransformer(Transformers.aliasToBean(ReporteRetencionPorDelegacion.class))
                    .getResultList();
        }catch (Exception e){
            throw e;
        }
    }

}
