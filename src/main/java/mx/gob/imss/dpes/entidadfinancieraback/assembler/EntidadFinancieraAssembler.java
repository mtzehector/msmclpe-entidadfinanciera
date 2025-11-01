/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.entidadfinancieraback.assembler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import javax.ws.rs.ext.Provider;

import mx.gob.imss.dpes.baseback.assembler.BaseAssembler;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcBeneficio;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcCondicionEntfed;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcCondicionOfertaOldie;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcEntidadFinanciera;
import mx.gob.imss.dpes.entidadfinancieraback.entity.McltRegistrosPatronales;
import mx.gob.imss.dpes.interfaces.entidadfinanciera.ot2.model.Beneficio;
import mx.gob.imss.dpes.interfaces.entidadfinanciera.ot2.model.CondicionEntidadFederativa;
import mx.gob.imss.dpes.interfaces.entidadfinanciera.ot2.model.CondicionOferta;
import mx.gob.imss.dpes.interfaces.entidadfinanciera.ot2.model.EntidadFinanciera;
import mx.gob.imss.dpes.interfaces.entidadfinanciera.ot2.model.EstadoEntidad;
import mx.gob.imss.dpes.interfaces.entidadfinanciera.ot2.model.Plazo;
import mx.gob.imss.dpes.interfaces.entidadfinanciera.ot2.model.RegistrosPatronales;
import mx.gob.imss.dpes.interfaces.entidadfinanciera.ot2.model.Sexo;

/**
 * @author Juc
 */
@Provider
public class EntidadFinancieraAssembler extends BaseAssembler<MclcEntidadFinanciera, EntidadFinanciera, Long, Long> {

    @Override
    public MclcEntidadFinanciera toEntity(EntidadFinanciera source) {
        return new MclcEntidadFinanciera();
    }

    @Override
    public Long toPKEntity(Long source) {
        return source;
    }

    @Override
    public EntidadFinanciera assemble(MclcEntidadFinanciera load) {

        EntidadFinanciera response = new EntidadFinanciera();
        response.setId(load.getId());
        response.setRfc(load.getRfc());
        response.setRazonSocial(load.getRazonSocial());
        response.setNombreComercial(load.getNombreComercial());
        response.setRegistroPatronal(load.getRegistroPatronal());
        response.setCurpRepresentanteLegal(load.getCurpRepresentateLegal());
        response.setNombreLegal(load.getNombreLegal());
        response.setPrimerApeLegal(load.getPrimerApeLegal());
        response.setSegundoApeLegal(load.getSegundoApeLegal());
        response.setCurpAdmin(load.getCurpAdmin());
        response.setNombreAdmin(load.getNombreAdmin());
        response.setPrimerApAdmin(load.getPrimerApAdmin());
        response.setSegundoApeAdmin(load.getSegundoApeAdmin());
        response.setCorreoAdmin(load.getCorreoAdmin());
        response.setNumeroTelefono(load.getNumeroTelefono());
        response.setTelefonoAtencionClientes(load.getTelefonoAtencionClientes());
        response.setPaginaWeb(load.getPaginaWeb());
        response.setCorreoElectronico(load.getCorreoElectronico());
        response.setCorreo2(load.getCorreo2());
        response.setCorreo3(load.getCorreo3());
        response.setDireccion(load.getDireccion());
        response.setFecFirmaContra(load.getFecFirmaContra());
        response.setFecIniFirmaContra(load.getFecIniFirmaContra());
        response.setNumeroProveedor(load.getNumeroProveedor());
        response.setClabe(load.getClabe());
        response.setInstBancaria(load.getInstBancaria());
        response.setNombreComercial(load.getNombreComercial());
        response.setSinConvenio(load.getSinConvenio());

        if (load.getMclcEstadoEf() != null) {
            response.setMclcEstadoEf(new EstadoEntidad());
            response.getMclcEstadoEf().setId(load.getMclcEstadoEf().getId());
            response.getMclcEstadoEf().setDescripcion(load.getMclcEstadoEf().getDesEstadoEntFinanciera());
        }

        for (MclcCondicionEntfed mclcCondicionEntfed : load.getMclcCondicionEntfedCollection()) {
            if (mclcCondicionEntfed.getCveDelegacion() != null) {
                CondicionEntidadFederativa condicionEntidad = new CondicionEntidadFederativa();
                condicionEntidad.setId(mclcCondicionEntfed.getId());
                condicionEntidad.setMclcSexo(new Sexo());
                condicionEntidad.getMclcSexo().setId(mclcCondicionEntfed.getMclcSexo().getId());
                condicionEntidad.getMclcSexo().setDesSexo(mclcCondicionEntfed.getMclcSexo().getDesSexo());
                condicionEntidad.setCveDelegacion(mclcCondicionEntfed.getCveDelegacion());
                condicionEntidad.setMonMaximo(mclcCondicionEntfed.getMonMaximo());
                condicionEntidad.setMonMinimo(mclcCondicionEntfed.getMonMinimo());
                condicionEntidad.setNumEdadLimite(mclcCondicionEntfed.getNumEdadLimite());
                response.getMclcCondicionEntfedCollection().add(condicionEntidad);
            }
        }

        for (MclcCondicionOfertaOldie mclcCondicionOfertaOldie : load.getMclcCondicionOfertaCollection()) {
            if (mclcCondicionOfertaOldie.getBajaRegistro() == null) {
                CondicionOferta condicion = new CondicionOferta();
                condicion.setId(mclcCondicionOfertaOldie.getId());
                condicion.setPorTasaAnual(mclcCondicionOfertaOldie.getPorTasaAnual());
                condicion.setPorCat(mclcCondicionOfertaOldie.getPorCat());
                condicion.setFecRegistroAlta(mclcCondicionOfertaOldie.getAltaRegistro());

                if (mclcCondicionOfertaOldie.getMclcPlazo() != null) {
                    condicion.setMclcPlazo(new Plazo());
                    condicion.getMclcPlazo().setId(mclcCondicionOfertaOldie.getMclcPlazo().getId());
                    condicion.getMclcPlazo().setDescripcion(mclcCondicionOfertaOldie.getMclcPlazo().getDescripcion());
                }

                if (mclcCondicionOfertaOldie.getMclcBeneficioCollection() != null &&
                    !mclcCondicionOfertaOldie.getMclcBeneficioCollection().isEmpty()) {

                    List<MclcBeneficio> listBeneficios = new ArrayList<>();
                    listBeneficios.addAll(mclcCondicionOfertaOldie.getMclcBeneficioCollection());

                    Collections.sort(listBeneficios, new Comparator<MclcBeneficio>() {
                        @Override
                        public int compare(MclcBeneficio b1, MclcBeneficio b2) {
                            return b2.getId().intValue() - b1.getId().intValue();
                        }
                    });


                    for (MclcBeneficio mclcBeneficio : listBeneficios) {
                        if (condicion.getMclcBeneficioCollection().size() < 3 &&
                            mclcBeneficio.getBajaRegistro() == null) {
                            Beneficio beneficio = new Beneficio();
                            beneficio.setId(mclcBeneficio.getId());
                            beneficio.setDesBeneficio(mclcBeneficio.getDesBeneficio());
                            condicion.getMclcBeneficioCollection().add(beneficio);
                        }
                    }
                }
                response.getMclcCondicionOfertaCollection().add(condicion);
            }
        }

        for (McltRegistrosPatronales rp : load.getMcltRegistrosPatronalesCollection()) {
            if (rp.getBajaRegistro() == null) {
                RegistrosPatronales regPat = new RegistrosPatronales();
                regPat.setId(rp.getId());
                regPat.setIdEntidadFinanciera(rp.getMclcEntidadFinanciera().getId());
                regPat.setRegistroPatronal(rp.getRegistroPatronal());
                response.getMcltRegistrosPatronalesCollection().add(regPat);
            }

        }
        return response;
    }

    @Override
    public Long assemblePK(Long source) {
        return source;
    }
}
