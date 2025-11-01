package mx.gob.imss.dpes.entidadfinancieraback.repository;

import mx.gob.imss.dpes.entidadfinancieraback.model.TramiteErogacion;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class TramiteErogacionRepository {

    @PersistenceContext
    private EntityManager em;

    private String obtenerConsultaSQL(Long cveEntidadFinanciera) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT  ");
        sb.append("ef.cve_entidad_financiera cve_entidad_financiera,ef.nom_comercial nombre_comercial,");
        sb.append("tramitesErogacion.cve_documento_cr,tramitesErogacion.tipo_documento_cr,");
        sb.append("tramitesErogacion.fecha_descarga_cr,tramitesErogacion.fecha_firma_cr,");
        sb.append("tramitesErogacion.erogacion,");
        sb.append("solicitudTR.cve_documento_st,solicitudTR.tipo_documento_st,");
        sb.append("solicitudTRD.cve_documento_std,solicitudTRD.tipo_documento_std,");
        sb.append("reporteRD.cve_documento_rrd,reporteRD.tipo_documento_rrd  ");
        sb.append("FROM mgpmclpe04.mclc_entidad_financiera ef  ");
        sb.append("LEFT JOIN (");
        sb.append("SELECT  ");
        sb.append("financiera.cve_entidad_financiera cve_entidad_financiera,documentoCR.cve_documento cve_documento_cr,");
        sb.append("documentoCR.cve_tipo_documento tipo_documento_cr,conciliacionCR.fecha_descarga_imss fecha_descarga_cr,");
        sb.append("conciliacionCR.fec_registro_alta fecha_firma_cr,conciliacionCR.genero_erogacion erogacion  ");
        sb.append("FROM mgpmclpe04.mclc_entidad_financiera financiera  ");
        sb.append("INNER JOIN mgpmclpe04.mclt_documento_conciliacion conciliacionCR  ");
        sb.append("ON (financiera.cve_entidad_financiera = conciliacionCR.cve_entidad_financiera)  ");
        sb.append("INNER JOIN mgpmclpe04.mclt_documento documentoCR  ");
        sb.append("ON (documentoCR.cve_documento = conciliacionCR.cve_documento AND documentoCR.cve_tipo_documento = 36)  ");
        sb.append("WHERE conciliacionCR.periodo = :periodo) tramitesErogacion  ");
        sb.append("ON (ef.cve_entidad_financiera = tramitesErogacion.cve_entidad_financiera)  ");
        sb.append("LEFT JOIN (");
        sb.append("SELECT  ");
        sb.append("conciliacionST.cve_entidad_financiera cve_entidad_financiera,documentoST.cve_documento cve_documento_st,");
        sb.append("documentoST.cve_tipo_documento tipo_documento_st  ");
        sb.append("FROM mgpmclpe04.mclt_documento_conciliacion conciliacionST  ");
        sb.append("INNER JOIN mgpmclpe04.mclt_documento documentoST  ");
        sb.append("ON (conciliacionST.cve_documento = documentoST.cve_documento AND documentoST.cve_tipo_documento = 31)  ");
        sb.append("WHERE conciliacionST.periodo = :periodo) solicitudTR  ");
        sb.append("ON (tramitesErogacion.cve_entidad_financiera = solicitudTR.cve_entidad_financiera)  ");
        sb.append("LEFT JOIN (");
        sb.append("SELECT  ");
        sb.append("conciliacionSTD.cve_entidad_financiera cve_entidad_financiera,");
        sb.append("documentoSTD.cve_documento cve_documento_std,");
        sb.append("documentoSTD.cve_tipo_documento tipo_documento_std  ");
        sb.append("FROM mgpmclpe04.mclt_documento_conciliacion conciliacionSTD  ");
        sb.append("INNER JOIN mgpmclpe04.mclt_documento documentoSTD  ");
        sb.append("ON (conciliacionSTD.cve_documento = documentoSTD.cve_documento AND documentoSTD.cve_tipo_documento = 32)  ");
        sb.append("WHERE conciliacionSTD.periodo = :periodo) solicitudTRD  ");
        sb.append("ON (tramitesErogacion.cve_entidad_financiera = solicitudTRD.cve_entidad_financiera)  ");
        sb.append("LEFT JOIN (");
        sb.append("SELECT ");
        sb.append("conciliacionRRD.cve_entidad_financiera cve_entidad_financiera,");
        sb.append("documentoRRD.cve_documento cve_documento_rrd,");
        sb.append("documentoRRD.cve_tipo_documento tipo_documento_rrd  ");
        sb.append("FROM mgpmclpe04.mclt_documento_conciliacion conciliacionRRD  ");
        sb.append("INNER JOIN mgpmclpe04.mclt_documento documentoRRD  ");
        sb.append("ON (conciliacionRRD.cve_documento = documentoRRD.cve_documento AND documentoRRD.cve_tipo_documento = 33)  ");
        sb.append("WHERE conciliacionRRD.periodo = :periodo) reporteRD  ");
        sb.append("ON (tramitesErogacion.cve_entidad_financiera = reporteRD.cve_entidad_financiera)  ");

        if (cveEntidadFinanciera != null && cveEntidadFinanciera > 0)
            sb.append("WHERE ef.cve_entidad_financiera = :cveEntidadFinanciera ");

        sb.append("ORDER BY ef.nom_comercial ASC");
        return sb.toString();
    }

    public List<TramiteErogacion> ejecutarConsultaSQL(String periodo, Long cveEntidadFinanciera) {
        try{
            Query query = em.createNativeQuery(this.obtenerConsultaSQL(cveEntidadFinanciera));
            query.setParameter("periodo", periodo);

            if (cveEntidadFinanciera != null && cveEntidadFinanciera > 0)
                query.setParameter("cveEntidadFinanciera", cveEntidadFinanciera);

            return query.unwrap(org.hibernate.Query.class)
                    .setResultTransformer(Transformers.aliasToBean(TramiteErogacion.class))
                    .getResultList();
        }catch (Exception e){
            throw e;
        }
    }

}
