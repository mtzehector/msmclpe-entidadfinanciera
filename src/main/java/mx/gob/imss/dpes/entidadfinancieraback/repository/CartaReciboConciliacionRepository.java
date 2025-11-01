package mx.gob.imss.dpes.entidadfinancieraback.repository;


import mx.gob.imss.dpes.interfaces.entidadfinanciera.model.CartaRecibo;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class CartaReciboConciliacionRepository {

    @PersistenceContext
    private EntityManager em;

    private String obtenerConsultaSQL(){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("SUM(IMPORTE_PAGADOS) pagoTotalPrimas,");
        sb.append("ROUND(SUM(PORCENTAJE_COSTO_ADMIN), 2) tasaCostoAdmin,");
        sb.append("ROUND(SUM(IVA_COSTO_ADMIN), 2) ivaCostoAdmin,");
        //sb.append("SUM(PORCENTAJE_PERMISO_ITINERANTE) tasaPermisoAccesoItinerante,");
        //sb.append("SUM(IVA_PERMISO_ITINERANTE) ivaPermisoItinerante,");
        sb.append("SUM(IMPORTE_FALLECIDOS) importePagadoDefunciones ");
        sb.append("FROM mgpmclpe04.mclt_resumen_conciliacion ");
        sb.append("WHERE CVE_ENTIDAD_FINANCIERA_SIPRE = :cveEntidadFinancieraSipre AND ");
        sb.append("PERIODO = :periodo");
        return sb.toString();
    }

    public CartaRecibo ejecutarConsulta(String cveEntidadFinancieraSipre, String periodo) throws Exception {
        try{
            Query query = em.createNativeQuery(this.obtenerConsultaSQL());
            query.setParameter("cveEntidadFinancieraSipre", cveEntidadFinancieraSipre);
            query.setParameter("periodo", periodo);
            return (CartaRecibo) query.unwrap(org.hibernate.Query.class)
                                .setResultTransformer(
                                    Transformers.aliasToBean(CartaRecibo.class)
                                ).uniqueResult();
        }catch (Exception e){
            throw e;
        }
    }

}
