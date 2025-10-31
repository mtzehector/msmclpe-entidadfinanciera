/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.gob.imss.dpes.entidadfinancieraback.service;

import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcBancos;
import mx.gob.imss.dpes.entidadfinancieraback.repository.MclcBancosRepository;
import mx.gob.imss.dpes.baseback.service.BaseCRUDService;
import javax.interceptor.Interceptors;
import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import javax.ws.rs.ext.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import mx.gob.imss.dpes.support.config.CustomSpringBeanAutowiringInterceptor;
/**
 *
 * @author juanf.barragan
 */
@Provider
@Interceptors(CustomSpringBeanAutowiringInterceptor.class)
public class MclcBancosService extends BaseCRUDService<MclcBancos, MclcBancos, Long, Long>  {

    @Autowired
    private MclcBancosRepository repository;

    public List<MclcBancos> getByClave (String clave){
        return repository.findByClave(clave);
    }

    @Override
    public JpaSpecificationExecutor<MclcBancos> getRepository() {
        return repository;
    }

    @Override
    public JpaRepository<MclcBancos, Long> getJpaRepository() {
        return repository;
    }
}
