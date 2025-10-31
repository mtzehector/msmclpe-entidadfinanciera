package mx.gob.imss.dpes.entidadfinancieraback.repository;

import mx.gob.imss.dpes.entidadfinancieraback.model.ReporteResumenConciliacion;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ResumenConciliacionRepository {

    @PersistenceContext
    private EntityManager em;

    private String obtenerConsultaSQL(){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT entidad_financiera.cve_entidad_financiera_sipre as cve_entidad,entidad_financiera.nom_comercial as entidad_financiera,entidad_financiera.numero_proveedor as numero_proveedor,");
        sb.append("SUM(NVL(resumen.casos_301_380,0)) as casos_emitidos_301_380,SUM(NVL(resumen.importe_301_380,0)) as importe_emitidos_301_380,");
        sb.append("SUM(NVL(resumen.casos_301,0)) as casos_emitidos_301,SUM(NVL(resumen.importe_301,0)) as importe_emitidos_301,");
        sb.append("SUM(NVL(resumen.casos_380,0)) as casos_emitidos_380,SUM(NVL(resumen.importe_380,0)) as importe_emitidos_380,");
        sb.append("SUM(NVL(resumen.casos_pagados_301_380,0)) as casos_pagados_301_380,SUM(NVL(resumen.importe_pagados_301_380,0)) as importe_pagado_301_380,");
        sb.append("SUM(NVL(resumen.casos_no_pagados,0)) as casos_no_pagados,SUM(NVL(resumen.importe_no_pagados,0)) as no_pagado,");
        sb.append("SUM(NVL(resumen.total_retenciones,0)) as total_retenciones,");
        sb.append("ROUND(SUM(NVL(resumen.porcentaje_costo_admin_resumen,0)),2) as costo_administrativo, ROUND(SUM(NVL(resumen.iva_costo_admin_resumen,0)),2) as iva_costo_administrativo,");
        sb.append("ROUND(SUM(NVL(resumen.permiso_itinerante_resumen,0)),2) as permiso_itinerante, ROUND(SUM(NVL(resumen.iva_permiso_itinerante_resumen,0)),2) as iva_permiso_itinerante,");
        //sb.append("SUM(NVL(resumen.neto_sin_descuento_fallecidos,0)) as neto_fallecidos,");
        sb.append("(SUM(NVL(resumen.total_retenciones,0)) - ROUND(SUM(NVL(resumen.porcentaje_costo_admin_resumen,0)),2) - ROUND(SUM(NVL(resumen.iva_costo_admin_resumen,0)),2)) as neto_fallecidos,");
        sb.append("SUM(NVL(resumen.casos_fallecidos_resumen,0)) as casos_fallecidos,SUM(NVL(resumen.importe_fallecidos_resumen,0)) as recuperacion_fallecidos,");
        //sb.append("SUM(NVL(resumen.pago_real,0)) as pago_real ");
        sb.append("(SUM(NVL(resumen.total_retenciones,0)) - ROUND(SUM(NVL(resumen.porcentaje_costo_admin_resumen,0)),2) - ROUND(SUM(NVL(resumen.iva_costo_admin_resumen,0)),2) - SUM(NVL(resumen.importe_fallecidos_resumen,0))) as pago_real ");
        sb.append("FROM mgpmclpe04.mclc_entidad_financiera entidad_financiera ");
        sb.append("LEFT JOIN mgpmclpe04.mclt_resumen_conciliacion resumen ");
        sb.append("ON entidad_financiera.cve_entidad_financiera_sipre = resumen.cve_entidad_financiera_sipre ");
        sb.append("WHERE entidad_financiera.cve_entidad_financiera in (SELECT cve_entidad_financiera FROM mgpmclpe04.mclc_entidad_financiera WHERE cve_estado_ent_financiera = 1 AND fec_registro_baja is null) AND ");
        sb.append("resumen.periodo = :periodo ");
        sb.append("GROUP BY ");
        sb.append("entidad_financiera.cve_entidad_financiera_sipre,");
        sb.append("entidad_financiera.nom_comercial,");
        sb.append("entidad_financiera.numero_proveedor");
        return sb.toString();
    }

    public List<ReporteResumenConciliacion> ejecutarConsulta(String periodo) throws Exception {
        try{
            Query query = em.createNativeQuery(this.obtenerConsultaSQL());
            query.setParameter("periodo", periodo);
            return query.unwrap(org.hibernate.Query.class)
                    .setResultTransformer(
                            Transformers.aliasToBean(ReporteResumenConciliacion.class)
                    ).getResultList();
        }catch (Exception e){
            throw e;
        }
    }

}
