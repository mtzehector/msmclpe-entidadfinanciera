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
import java.util.stream.Stream;
import javax.interceptor.Interceptors;
import javax.ws.rs.ext.Provider;

import lombok.Setter;
import mx.gob.imss.dpes.common.enums.TipoSimulacionEnum;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.entidadfinancieraback.exception.PlazosException;
import mx.gob.imss.dpes.interfaces.pensionado.model.Pensionado;
import mx.gob.imss.dpes.baseback.persistence.BaseSpecification;
import mx.gob.imss.dpes.common.rule.PagoMensualRule;
import mx.gob.imss.dpes.baseback.service.BaseCRUDService;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcCondicionOfertaOldie;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcPlazo;
import mx.gob.imss.dpes.interfaces.entidadfinanciera.model.Plazo;
import mx.gob.imss.dpes.entidadfinancieraback.repository.OfertasByPagoTotalSpecification;
import mx.gob.imss.dpes.support.config.CustomSpringBeanAutowiringInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import mx.gob.imss.dpes.entidadfinancieraback.model.PlazosRequest;
import mx.gob.imss.dpes.entidadfinancieraback.repository.OfertasByPagoMensualSpecification;
import mx.gob.imss.dpes.entidadfinancieraback.rule.RNCalculaEdad;
import org.springframework.data.domain.Sort;
import mx.gob.imss.dpes.entidadfinancieraback.repository.MclcCondicionOfertaRepository_oldie;

/**
 * @author Diego Velazquez
 */
@Provider
@Interceptors(CustomSpringBeanAutowiringInterceptor.class)
public class ObtenerPlazosService extends BaseCRUDService<PlazosRequest, MclcCondicionOfertaOldie, Long, Long> {

    @Autowired
    @Setter
    private MclcCondicionOfertaRepository_oldie repository;

    @Override
    public JpaSpecificationExecutor<MclcCondicionOfertaOldie> getRepository() {
        return repository;
    }

    @Override
    public JpaRepository<MclcCondicionOfertaOldie, Long> getJpaRepository() {
        return repository;

    }

    public List<Plazo> obtenerOfertas(PlazosRequest request) throws BusinessException {

        try {

            double monto = request.getMonto();
            log.info("monto en obtenerOfertas  " + String.valueOf(monto));
            double capacidadCredito = request.getCapacidadCredito();
            log.info("capacidadCredito en obtenerOfertas  " + String.valueOf(capacidadCredito));


            Pensionado pensionado = request.getPensionado();
            log.info("pensionado en obtenerOfertas  " + String.valueOf(pensionado));


            Collection<BaseSpecification> constraints = new ArrayList<>();
            RNCalculaEdad regla = new RNCalculaEdad();
            RNCalculaEdad.Input entrada = regla.new Input(request.getPensionado().getFechaNacimiento());
            RNCalculaEdad.Output salida = regla.apply(entrada);
            pensionado.setEdad(salida.getEdad());
            log.info("edad >" + salida.getEdad());
            log.info("simulacion >" + request.getTipoSimulacion());

            switch (request.getTipoSimulacion()) {
                case MONTO:
                    OfertasByPagoTotalSpecification s1 = new OfertasByPagoTotalSpecification(pensionado, monto);
                    constraints.add(s1);
                    break;
                case DESCUENTO_MENSUAL:
                    OfertasByPagoMensualSpecification s2 = new OfertasByPagoMensualSpecification(pensionado, monto, capacidadCredito);
                    constraints.add(s2);
                    break;
            }
            List<Sort.Order> orders = new ArrayList<>();
            orders.add(new Sort.Order(Sort.Direction.ASC, "mclcPlazo.numMeses"));
            log.info("orders " + orders);
            Stream<BaseSpecification> test = constraints.parallelStream();
            log.info("constraints  ARRAY [0]  " + ((test.findFirst().toString())));

            List<MclcCondicionOfertaOldie> list = load(constraints, orders);

            log.info("lista load " + list.size());

            List<Plazo> plazos = new ArrayList<>();

            //List<MclcPlazo> plazosEntity = new ArrayList<>();

            Plazo plazo = null;
            for (MclcCondicionOfertaOldie row : list) {

                if (TipoSimulacionEnum.MONTO.equals(request.getTipoSimulacion())) {
                    PagoMensualRule.Input input = new PagoMensualRule().new Input(monto, row.getMclcPlazo().getNumMeses(), (row.getPorCat().doubleValue()) / 100d);
                    PagoMensualRule rule = new PagoMensualRule();
                    PagoMensualRule.Output output = rule.apply(input);
                    log.log(Level.INFO, "descuento mensual'{'0'}':{0}", output.getPagoMensual());
                    if (output.getPagoMensual() > capacidadCredito) {
                        continue;
                    }
                }

/*
                if (!plazosEntity.contains(row.getMclcPlazo())) {
                    plazosEntity.add(row.getMclcPlazo());
                    Plazo plazo = new Plazo();
                    plazo.setId(row.getMclcPlazo().getId());
                    plazo.setNumPlazo(row.getMclcPlazo().getNumMeses());
                    plazo.setDescripcion(row.getMclcPlazo().getDescripcion());
                    plazos.add(plazo);
                }
*/
                plazo = new Plazo();
                plazo.setId(row.getMclcPlazo().getId());
                if(!plazos.contains(plazo)) {
                    plazo.setNumPlazo(row.getMclcPlazo().getNumMeses());
                    plazo.setDescripcion(row.getMclcPlazo().getDescripcion());
                    plazos.add(plazo);
                }
            }

            return plazos;
        } catch (Exception e) {
            log.log(Level.SEVERE, "ObtenerPlazosService.obtenerOfertas - request = [" + request + "]", e);
        }

        throw new PlazosException(PlazosException.ERROR_DESCONOCIDO_EN_EL_SERVICIO);
    }

}
