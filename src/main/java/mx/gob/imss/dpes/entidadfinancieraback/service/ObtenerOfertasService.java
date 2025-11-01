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
import mx.gob.imss.dpes.common.exception.AlternateFlowException;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.exception.PartialContentFlowException;
import mx.gob.imss.dpes.common.exception.VariableMessageException;
import mx.gob.imss.dpes.common.model.PageRequestModel;
import mx.gob.imss.dpes.entidadfinancieraback.exception.CondicionOfertaException;
import mx.gob.imss.dpes.interfaces.pensionado.model.Pensionado;
import mx.gob.imss.dpes.baseback.persistence.BaseSpecification;
import mx.gob.imss.dpes.common.rule.MontoTotalRule;
import mx.gob.imss.dpes.common.rule.PagoMensualRule;
import mx.gob.imss.dpes.baseback.service.BasePageableCRUDService;
import mx.gob.imss.dpes.entidadfinancieraback.assembler.OfertaAssembler;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcCondicionOfertaOldie;
import mx.gob.imss.dpes.interfaces.entidadfinanciera.model.Oferta;
import mx.gob.imss.dpes.entidadfinancieraback.model.OfertasRequest;
import mx.gob.imss.dpes.entidadfinancieraback.repository.OfertasByPagoMensualAndPlazoSpecification;
import mx.gob.imss.dpes.support.config.CustomSpringBeanAutowiringInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import mx.gob.imss.dpes.entidadfinancieraback.repository.OfertasByPagoTotalAndPlazoSpecification;
import mx.gob.imss.dpes.entidadfinancieraback.rule.RNCalculaEdad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import mx.gob.imss.dpes.entidadfinancieraback.repository.MclcCondicionOfertaRepository_oldie;

/**
 *
 * @author Diego Velazquez
 */
@Provider
@Interceptors(CustomSpringBeanAutowiringInterceptor.class)
public class ObtenerOfertasService extends BasePageableCRUDService<OfertasRequest, MclcCondicionOfertaOldie, Long, Long> {

    @Autowired
    @Setter
    private MclcCondicionOfertaRepository_oldie repository;

    @Inject
    @Setter
    OfertaAssembler assembler;

    @Inject
    @Setter
    PagoMensualRule pagoMensualRule;

    @Inject
    @Setter
    MontoTotalRule montoTotalRule;

    @Override
    public JpaSpecificationExecutor<MclcCondicionOfertaOldie> getRepository() {
        return repository;
    }

    @Override
    public JpaRepository<MclcCondicionOfertaOldie, Long> getJpaRepository() {
        return repository;
    }

    public Oferta ofertaById(Long id) {
        return assembler.assemble(findOne(id));
    }

    @Override
    public Collection<BaseSpecification<MclcCondicionOfertaOldie>> customConstraints(OfertasRequest model) {
        Collection<BaseSpecification<MclcCondicionOfertaOldie>> constraints = new ArrayList<>();

        double monto;
        double descuentoMensual;
        Pensionado pensionado = model.getPensionado();
        Long plazo = model.getPlazo();

        RNCalculaEdad.Input entrada = new RNCalculaEdad().new Input(model.getPensionado().getFechaNacimiento());
        RNCalculaEdad regla = new RNCalculaEdad();
        RNCalculaEdad.Output salida = regla.apply(entrada);
        pensionado.setEdad(salida.getEdad());

        switch (model.getTipoSimulacion()) {
            case MONTO:
                monto = model.getMonto();
                OfertasByPagoTotalAndPlazoSpecification s1 = new OfertasByPagoTotalAndPlazoSpecification(pensionado, monto, plazo);
                constraints.add(s1);
                break;
            case DESCUENTO_MENSUAL:
                descuentoMensual = model.getDescuentoMensual();
                OfertasByPagoMensualAndPlazoSpecification s2 = new OfertasByPagoMensualAndPlazoSpecification(pensionado, descuentoMensual, plazo);
                constraints.add(s2);
                break;
        }

        return constraints;
    }

    private Page<MclcCondicionOfertaOldie> consultaOfertas(PageRequestModel<OfertasRequest> request)
        throws CondicionOfertaException {

        try {
            List<Sort.Order> orders = new ArrayList<>();
            orders.add(new Sort.Order(Direction.ASC, "porCat"));
            orders.add(new Sort.Order(Direction.ASC, "mclcEntidadFinanciera.nombreComercial"));

            return fetch(request, 200, orders);
        } catch (Exception e) {
            log.log(Level.SEVERE, "ERROR ObtenerOfertasService.consultaOfertas = {0}", e);
            throw new CondicionOfertaException(CondicionOfertaException.ERROR_DE_LECTURA_DE_BD);
        }
    }

    public Page<Oferta> obtenerOfertas(PageRequestModel<OfertasRequest> request) throws BusinessException {

        //List<Sort.Order> orders = new ArrayList<>();
        //orders.add(new Sort.Order(Direction.ASC, "porCat"));
        //orders.add(new Sort.Order(Direction.ASC, "mclcEntidadFinanciera.nombreComercial"));
        //log.log(Level.INFO, " obtenerOfertas {0}",request);
        //Page<MclcCondicionOfertaOldie> page = fetch(request, 200, orders);

        Page<MclcCondicionOfertaOldie> page = consultaOfertas(request);

        if (page.getTotalElements() == 0) {
            //throw new AlternateFlowException("alt002");
            throw new AlternateFlowException("alt003");
        }

        Page<Oferta> pageOferta = assembler.assemblePage(page);

        for (Oferta oferta : pageOferta.getContent()) {
            switch (request.getModel().getTipoSimulacion()) {
                case MONTO:
                    obtenerOfertasMonto(oferta, request);
                    break;
                case DESCUENTO_MENSUAL:
                    obtenerOfertasPagoMensual(oferta, request);
                    break;
            }
        }
      
        return pageOferta;
    }

    private void obtenerOfertasMonto(Oferta oferta, PageRequestModel<OfertasRequest> request) {
        double importeTotal;
        oferta.getId();
        oferta.setMonto(request.getModel().getMonto());

        oferta.setDescuentoMensual(
                pagoMensualRule.apply(
                        pagoMensualRule.new Input(
                                oferta.getMonto(),
                                oferta.getPlazo().getNumPlazo(),
                                oferta.getCat() / 100d)).getPagoMensual());

        oferta.getTasaAnual();
        importeTotal = df(oferta.getPlazo().getNumPlazo(), oferta.getDescuentoMensual());
        oferta.setImporteTotal(importeTotal);
        oferta.getBeneficios();
    }

    private void obtenerOfertasPagoMensual(Oferta oferta, PageRequestModel<OfertasRequest> request) {
        double importeTotal;
        oferta.getId();
        oferta.setMonto(
                montoTotalRule.apply(
                        montoTotalRule.new Input(
                                request.getModel().getDescuentoMensual(),
                                oferta.getPlazo().getNumPlazo(),
                                oferta.getCat() / 100d)).getMonto());

        oferta.setDescuentoMensual(request.getModel().getDescuentoMensual());
        oferta.getTasaAnual();
        oferta.getBeneficios();
        importeTotal = df(oferta.getPlazo().getNumPlazo(), oferta.getDescuentoMensual());
        oferta.setImporteTotal(importeTotal);
    }

    public double df(Integer entero, Double doble) {
        return Math.round((entero * doble) * 100) / 100d;
    }

}
