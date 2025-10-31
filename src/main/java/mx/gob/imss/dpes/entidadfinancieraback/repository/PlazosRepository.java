/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.entidadfinancieraback.repository;

import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mx.gob.imss.dpes.interfaces.entidadfinanciera.model.Plazo;
import mx.gob.imss.dpes.interfaces.pensionado.model.Pensionado;
import mx.gob.imss.dpes.interfaces.userdata.model.UserData;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;

/**
 *
 * @author edgar.arenas
 */
@Component
public class PlazosRepository {
    
    protected final transient Logger log = Logger.getLogger(getClass().getName());
    @PersistenceContext
    private EntityManager entityManager;
    
    public PlazosRepository() {
    }
    
    public List<Plazo> getPlazosByMonto(Pensionado pensionado, double monto) {
        
        List<Plazo> plazos = (List<Plazo>) entityManager.createNativeQuery(
                " SELECT DISTINCT"
                + " p.cve_plazo, "
                + " p.num_meses,  "
                + " p.des_plazo "
                + " from mclc_condicion_oferta co inner join mclc_entidad_financiera ef "
                + " on  ef.cve_entidad_financiera = co.cve_entidad_financiera "
                + " inner join mclc_condicion_entfed efe " 
                + " on ef.cve_entidad_financiera = efe.cve_entidad_financiera "
                + " inner join mclc_plazo p "
                + " on co.cve_plazo = p.cve_plazo "
                + " where efe.mon_maximo >= ?1 "
                + " and efe.mon_minimo <= ?1 "
                + " and efe.cve_sexo = ?2 "
                + " and efe.cve_delegacion = ?3 "
                + " and efe.num_edad_limite >= ?4 "
                + " order by p.num_meses asc ")
                .setParameter(1, monto)
                .setParameter(2, pensionado.getSexo().getId())
                .setParameter(3, pensionado.getDelegacion().getCveDelegacion())
                .setParameter(4, pensionado.getSexo().getId())
                .unwrap(org.hibernate.Query.class).setResultTransformer(Transformers.aliasToBean(UserData.class)).uniqueResult();
        return plazos;
    }
    
}
