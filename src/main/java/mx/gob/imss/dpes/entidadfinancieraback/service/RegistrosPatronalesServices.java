/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.gob.imss.dpes.entidadfinancieraback.service;

import java.util.ArrayList;
import java.util.List;
import javax.interceptor.Interceptors;
import javax.ws.rs.ext.Provider;

import org.springframework.beans.factory.annotation.Autowired;

import mx.gob.imss.dpes.baseback.service.BaseCRUDService;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.support.config.CustomSpringBeanAutowiringInterceptor;
import mx.gob.imss.dpes.entidadfinancieraback.entity.McltRegistrosPatronales;
import mx.gob.imss.dpes.entidadfinancieraback.repository.McltRegistrosPatronalesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.logging.Level;
import mx.gob.imss.dpes.entidadfinancieraback.entity.McltRegistroPatronal;
import mx.gob.imss.dpes.entidadfinancieraback.model.RegistroPatronalPersistenceRequest;
import mx.gob.imss.dpes.entidadfinancieraback.model.RegistroPatronalResponse;




/**
 *
 * @author juanf.barragan
 */

@Provider
@Interceptors(CustomSpringBeanAutowiringInterceptor.class)
public class RegistrosPatronalesServices extends BaseCRUDService<McltRegistroPatronal, McltRegistroPatronal, Long, Long>{

    @Autowired
    private McltRegistrosPatronalesRepository repository;

    @Override
    public JpaSpecificationExecutor<McltRegistroPatronal> getRepository() {
        return repository;
    }

    @Override
    public JpaRepository<McltRegistroPatronal, Long> getJpaRepository() {
        return repository;
    }

     public RegistroPatronalResponse load(Long id) throws BusinessException{
        log.log(Level.INFO,">>>entidadFinancieraBack RegistrosPatronalesServices load ID:{0}", id);
        RegistroPatronalResponse response = new RegistroPatronalResponse();
//        List<McltRegistrosPatronales> lista = new ArrayList ;//repository.findByIdEntidadFinanciera(id);
//        List<McltRegistrosPatronales> rpFin = new ArrayList<McltRegistrosPatronales>();
//        for(McltRegistrosPatronales rp : lista){
//            if(rp.getBajaRegistro() == null){
//                rpFin.add(rp);
//            }
//        }
//        response.setRegistrosPatronales(rpFin);
        return response;
    }

    public List<McltRegistroPatronal> execute (RegistroPatronalPersistenceRequest request) throws BusinessException {
        log.log(Level.INFO,">>>entidadFinancieraBack RegistrosPatronalesServices execute request:{0}", request);
        List<McltRegistroPatronal> guardados = new ArrayList<McltRegistroPatronal>();
        if(!request.getRegistroPatronal().isEmpty()){
            for(McltRegistroPatronal rp : request.getRegistroPatronal()){
                //McltRegistrosPatronales mrp = new McltRegistrosPatronales();
                //mrp.setId(rp.getId());
                //mrp.setIdEntidadFinanciera(rp.getIdEntidadFinanciera());
                //mrp.setRegistroPatronal(rp.getRegistroPatronal());
                //mrp.setBajaRegistro(rp.isBaja() ? new Date() : null);
                
                
                McltRegistroPatronal saved = save(rp);
                guardados.add(saved);
            }
            return guardados;
        }
        
        return null;
    }
    
    public McltRegistroPatronal getbyRP(String rp){
        McltRegistroPatronal mcltRegistroPatronal = null;
        mcltRegistroPatronal = repository.findByRegistroPatronal(rp);
        return mcltRegistroPatronal;
    }
    
    public List<McltRegistroPatronal> recuperaRPbyEF (Long id)throws BusinessException{
        List<McltRegistroPatronal> list = new ArrayList<McltRegistroPatronal>();
        List<McltRegistroPatronal> listFinal = new ArrayList<McltRegistroPatronal>();
        list = repository.findByMclcEntidadFinanciera(id);
        for(McltRegistroPatronal rp : list){
            if (rp.getBajaRegistro() == null){
                listFinal.add(rp);
            }
        }
        
        return listFinal;
    }

    public List<String> listaRegistrosPatronales(Long id, String padre) throws BusinessException{
        List<McltRegistroPatronal> list = new ArrayList<McltRegistroPatronal>();
        List<String> registros = new ArrayList<String>();
        registros.add(padre);
        log.log(Level.INFO, " antes de el repo : {0}", id);
        list = repository.findByMclcEntidadFinanciera(id);
        log.log(Level.INFO, " despues de el repo : {0}", list);
        for(McltRegistroPatronal rp : list){
            if (rp.getBajaRegistro() == null){
                registros.add(rp.getRegistroPatronal());
            }
        }
        log.log(Level.INFO, " termina el repo : {0}", registros);
        return registros;
    }
}
