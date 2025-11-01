/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.entidadfinancieraback.assembler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import javax.ws.rs.ext.Provider;
import mx.gob.imss.dpes.baseback.assembler.BaseAssembler;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcBeneficio;
import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcCondicionOfertaOldie;
import mx.gob.imss.dpes.interfaces.entidadfinanciera.model.Beneficio;
import mx.gob.imss.dpes.interfaces.entidadfinanciera.model.Oferta;

/**
 *
 * @author Diego Velazquez
 */
@Provider
public class OfertaAssembler extends BaseAssembler<MclcCondicionOfertaOldie, Oferta, Long, Long> {
    @Override
    public MclcCondicionOfertaOldie toEntity(Oferta source) {
        return new MclcCondicionOfertaOldie();
    }

    @Override
    public Long toPKEntity(Long source) {
        return source;
    }

    @Override
    public Oferta assemble(MclcCondicionOfertaOldie source) {

        Oferta oferta = new Oferta();
        oferta.setId(source.getId());
        oferta.getEntidadFinanciera().setId(source.getMclcEntidadFinanciera().getId());
        oferta.getEntidadFinanciera().setNombreComercial(source.getMclcEntidadFinanciera().getNombreComercial());
        oferta.getEntidadFinanciera().setRazonSocial(source.getMclcEntidadFinanciera().getRazonSocial());
        oferta.getEntidadFinanciera().setNumTelefono(source.getMclcEntidadFinanciera().getNumeroTelefono());
        oferta.getEntidadFinanciera().setPaginaWeb(source.getMclcEntidadFinanciera().getPaginaWeb());
        oferta.getEntidadFinanciera().setIdSipre(source.getMclcEntidadFinanciera().getIdSipre());

        oferta.getPlazo().setId(source.getMclcPlazo().getId());
        oferta.getPlazo().setNumPlazo(source.getMclcPlazo().getNumMeses());
        oferta.getPlazo().setDescripcion(source.getMclcPlazo().getDescripcion());

        oferta.setCat(source.getPorCat() != null ? source.getPorCat().doubleValue() : 0.0);
        oferta.setTasaAnual(source.getPorTasaAnual() != null ? source.getPorTasaAnual().doubleValue() : 0.0);

        Collections.sort(new ArrayList<>(source.getMclcBeneficioCollection()), new Comparator<MclcBeneficio>() {
            @Override
            public int compare(MclcBeneficio b1, MclcBeneficio b2) {
                return b2.getId().intValue() - b1.getId().intValue();
            }
        });

        final List<Beneficio> listBeneficios = new ArrayList<>();
        source.getMclcBeneficioCollection().forEach(new Consumer<MclcBeneficio>() {
                @Override
                public void accept(MclcBeneficio source) {
                    if (source.getBajaRegistro() == null && listBeneficios.size() < 3){
                        Beneficio b = new Beneficio();
                        b.setId(source.getId());
                        b.setDesBeneficio(source.getDesBeneficio());
                        listBeneficios.add(b);
                    }
                }
            }
        );
        oferta.setBeneficios(listBeneficios);
        return oferta;
    }

    @Override
    public Long assemblePK(Long source) {
        return source;
    }
}
