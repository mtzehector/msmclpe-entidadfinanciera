/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.entidadfinancieraback.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.ws.rs.ext.Provider;
import lombok.Setter;
import mx.gob.imss.dpes.baseback.persistence.BaseSpecification;
import mx.gob.imss.dpes.baseback.service.BaseCRUDService;
import mx.gob.imss.dpes.entidadfinancieraback.assembler.OfertaCondCapturaAssembler;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcCondicionOfertaOldie;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcEntidadFinanciera;
import mx.gob.imss.dpes.entidadfinancieraback.repository.OfertasByEntidadFinancieraSpecification;
import mx.gob.imss.dpes.interfaces.entidadfinanciera.model.OfertaCondCaptura;
import mx.gob.imss.dpes.interfaces.entidadfinanciera.model.Plazo;
import mx.gob.imss.dpes.support.config.CustomSpringBeanAutowiringInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import mx.gob.imss.dpes.entidadfinancieraback.repository.MclcCondicionOfertaRepository_oldie;

/**
 *
 * @author Diego
 */
@Provider
@Interceptors(CustomSpringBeanAutowiringInterceptor.class)
public class ObtenerCondCapturaService extends BaseCRUDService<MclcCondicionOfertaOldie, MclcCondicionOfertaOldie, Long, Long> {

    @Autowired
    @Setter
    private MclcCondicionOfertaRepository_oldie repository;

    @Inject
    @Setter
    OfertaCondCapturaAssembler assembler;

    public List<OfertaCondCaptura> obtenerOfertaCondCaptura(MclcEntidadFinanciera request) {
        log.log(Level.INFO,"OfertaCondCapturaEndPoint BACK SERVICE {0}",request.getId());
        Collection<BaseSpecification> constraints = new ArrayList<>();

        constraints.add(new OfertasByEntidadFinancieraSpecification(request.getId()));
        
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order( Sort.Direction.ASC, "mclcPlazo.numMeses"));
        
        List<MclcCondicionOfertaOldie> list = load(constraints, orders);
        log.log(Level.INFO,"OfertaCondCapturaEndPoint BACK SERVICE list {0}", list.size());
        List<MclcCondicionOfertaOldie> listbaja = new ArrayList<MclcCondicionOfertaOldie>();
        for(MclcCondicionOfertaOldie ofer : list){
            if(ofer.getBajaRegistro() == null){
                listbaja.add(ofer);
            }
        }
        
        List<OfertaCondCaptura> listOferta = assembler.assembleList(listbaja);
        log.log(Level.INFO, "LISTA {0}", listOferta);
        for (OfertaCondCaptura oferta : listOferta) {

            oferta.getId();
            oferta.getCat();
            oferta.getTasaAnual();
            Plazo plazo = new Plazo();
            plazo.getId();
            plazo.getDescripcion();
            plazo.getNumPlazo();

        }
        return listOferta;
    }
    
       
    @Override
    public JpaSpecificationExecutor<MclcCondicionOfertaOldie> getRepository() {
        return repository;
    }

    @Override
    public JpaRepository<MclcCondicionOfertaOldie, Long> getJpaRepository() {
        return repository;
    }
    
}
