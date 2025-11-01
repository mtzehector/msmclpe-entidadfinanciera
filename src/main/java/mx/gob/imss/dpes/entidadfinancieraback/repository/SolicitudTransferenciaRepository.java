package mx.gob.imss.dpes.entidadfinancieraback.repository;

import mx.gob.imss.dpes.entidadfinancieraback.model.SolicitudTransferenciaConciliacion;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class SolicitudTransferenciaRepository {

    @PersistenceContext
    private EntityManager em;

    private String obtenerConsultaSQL() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("    SUM(retencion_nacional_i) AS retencion_nacional, ");
        sb.append("    SUM(costo_administrativo_i) AS costo_administrativo, ");
        sb.append("    SUM(iva_costo_administrativo_i) AS iva_costo_administrativo, ");
        sb.append("    SUM(importe_fallecidos_i) AS importe_fallecidos, ");
        sb.append("    SUM(neto_pagar_i) AS neto_pagar ");
        sb.append("FROM ( ");
        sb.append("    SELECT ");
        sb.append("        entidad_financiera.cve_entidad_financiera, ");
        sb.append("        NVL(SUM(resumen.importe_pagados),0) as retencion_nacional_i, ");
        sb.append("        ROUND(NVL(SUM(resumen.porcentaje_costo_admin),0),2) as costo_administrativo_i, ");
        sb.append("        ROUND(NVL(SUM(resumen.iva_costo_admin),0),2) as iva_costo_administrativo_i, ");
        sb.append("        NVL(SUM(resumen.importe_fallecidos),0) as importe_fallecidos_i, ");
        sb.append("        (NVL(SUM(resumen.importe_pagados),0) - ROUND(NVL(SUM(resumen.porcentaje_costo_admin),0),2) - ROUND(NVL(SUM(resumen.iva_costo_admin),0),2) - NVL(SUM(resumen.importe_fallecidos),0)) as neto_pagar_i ");
        sb.append("    FROM mgpmclpe04.mclt_resumen_conciliacion resumen ");
        sb.append("        INNER JOIN mgpmclpe04.mclc_entidad_financiera entidad_financiera ");
        sb.append("            ON entidad_financiera.cve_entidad_financiera_sipre = resumen.cve_entidad_financiera_sipre ");
        sb.append("    WHERE entidad_financiera.cve_entidad_financiera in (:entidades) ");
        sb.append("    AND resumen.periodo = :periodo ");
        sb.append("    GROUP BY entidad_financiera.cve_entidad_financiera ");
        sb.append(") ");
        return sb.toString();
    }

    public SolicitudTransferenciaConciliacion ejecutarConsulta(List<Long> entidades, String periodo) throws Exception {
        try{
            Query query = em.createNativeQuery(this.obtenerConsultaSQL());
            query.setParameter("entidades", entidades);
            query.setParameter("periodo", periodo);
            return (SolicitudTransferenciaConciliacion) query.unwrap(org.hibernate.Query.class)
                    .setResultTransformer(
                            Transformers.aliasToBean(SolicitudTransferenciaConciliacion.class)
                    ).uniqueResult();
        }catch (Exception e){
            throw e;
        }
    }

}
