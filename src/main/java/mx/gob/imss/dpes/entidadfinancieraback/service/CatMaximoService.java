/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.entidadfinancieraback.service;

import java.util.List;
import java.util.logging.Level;
import javax.interceptor.Interceptors;
import javax.ws.rs.ext.Provider;
import mx.gob.imss.dpes.baseback.service.BaseCRUDService;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcCondicionOferta;
import mx.gob.imss.dpes.entidadfinancieraback.exception.CatMaximoException;
import mx.gob.imss.dpes.entidadfinancieraback.model.CatMaximoModel;
import mx.gob.imss.dpes.entidadfinancieraback.repository.MclcCondicionOfertaRepository;
import mx.gob.imss.dpes.support.config.CustomSpringBeanAutowiringInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * @author juanf.barragan
 */
@Provider
@Interceptors(CustomSpringBeanAutowiringInterceptor.class)
public class CatMaximoService extends BaseCRUDService<MclcCondicionOferta, MclcCondicionOferta, Long, Long> {

    @Autowired
    private MclcCondicionOfertaRepository repository;
    
    public Message<CatMaximoModel> actualizaCatalogo(Message<CatMaximoModel> request) throws BusinessException {

        try {
            if (repository.actualizaPorTasaAnual(request.getPayload().getCatIMSS()) > 0) {
                request.getPayload().setCondicionesOferta(
                        repository.obtieneCondicionesConCatEFSuperiorAlCatMaximo(request.getPayload().getCatIMSS()));
                repository.actualizaPorCat(request.getPayload().getCatIMSS());
            }
        } catch(Exception e) {
            log.log(Level.SEVERE, "ERROR CatMaximoService.actualizaCatalogo ", e);
            throw new CatMaximoException(CatMaximoException.ERROR_DE_ESCRITURA_EN_BD);
        }

        return request;
    }
    
    public CatMaximoModel read() throws BusinessException{

        CatMaximoModel model = new CatMaximoModel();

        try {
            List<MclcCondicionOferta> lista = repository.findAll();
            String actual = lista.get(0).getPorTasaAnual().toString();
            model.setCatAnterior(actual);
        } catch(Exception e) {
            log.log(Level.SEVERE, "ERROR CatMaximoService.read ", e);
            throw new CatMaximoException(CatMaximoException.ERROR_DE_LECTURA_DE_BD);
        }
        
        return model;
    }
    

    @Override
    public JpaSpecificationExecutor<MclcCondicionOferta> getRepository() {
        return repository;
    }

    @Override
    public JpaRepository<MclcCondicionOferta, Long> getJpaRepository() {
        return repository;
    }

    
}
