package mx.gob.imss.dpes.entidadfinancieraback.repository;

import mx.gob.imss.dpes.entidadfinancieraback.model.PermisoItineranteCostoAdministrativo;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class PermisoItineranteRepository {

    @PersistenceContext
    private EntityManager em;

    private String generaConsultaTasasEntidadFinancieraPeriodo() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT  ");
        sb.append("entidad_financiera.cve_entidad_financiera as cve_entidad_financiera,");
        sb.append("entidad_financiera.nom_comercial as nom_comercial,");
        sb.append("entidad_financiera.numero_proveedor as numero_proveedor,");
        sb.append("TblPorcentajes.cve_convenio_admin_financiera,");
        sb.append("TblPorcentajes.porcentaje_administracion,");
        sb.append("TblPorcentajes.porcentaje_permiso_itinerante,");
        sb.append("TblPorcentajes.periodo_inicio_vigencia,");
        sb.append("TblPorcentajes.periodo_fin_vigencia  ");
        sb.append("FROM mgpmclpe04.mclc_entidad_financiera entidad_financiera  ");
        sb.append("LEFT JOIN (");
        sb.append("SELECT  ");
        sb.append("convenio.cve_entidad_financiera_sipre,");
        sb.append("porcentaje.cve_convenio_admin_financiera as cve_convenio_admin_financiera,");
        sb.append("porcentaje.porcentaje_administracion as porcentaje_administracion,");
        sb.append("porcentaje.porcentaje_permiso_itinerante as porcentaje_permiso_itinerante,");
        sb.append("convenio.periodo_inicio_vigencia as periodo_inicio_vigencia,");
        sb.append("convenio.periodo_fin_vigencia as periodo_fin_vigencia  ");
        sb.append("FROM mgpmclpe04.mclc_inst_financiera_convenio convenio  ");
        sb.append("INNER JOIN mgpmclpe04.mclc_convenio_admin_financiera porcentaje  ");
        sb.append("ON convenio.cve_convenio_admin_financiera = porcentaje.cve_convenio_admin_financiera  ");
        sb.append("WHERE :periodo >= convenio.periodo_inicio_vigencia AND  ");
        sb.append(":periodo <= convenio.periodo_fin_vigencia  ");
        sb.append(") TblPorcentajes  ");
        sb.append("ON entidad_financiera.cve_entidad_financiera_sipre = TblPorcentajes.cve_entidad_financiera_sipre  ");
        sb.append("WHERE entidad_financiera.cve_entidad_financiera = :entidad");
        return sb.toString();
    }

    public PermisoItineranteCostoAdministrativo consultaTasasEntidadFinanciera(Long entidad, String periodo){
        try{
            Query query = em.createNativeQuery(this.generaConsultaTasasEntidadFinancieraPeriodo());
            query.setParameter("entidad", entidad);
            query.setParameter("periodo", periodo);
            return (PermisoItineranteCostoAdministrativo)
                    query.unwrap(org.hibernate.Query.class)
                            .setResultTransformer(
                                    Transformers.aliasToBean(PermisoItineranteCostoAdministrativo.class)
                            ).uniqueResult();
        }catch (Exception e){
            throw e;
        }
    }

}
