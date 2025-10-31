package mx.gob.imss.dpes.entidadfinancieraback.service;

import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.common.service.ServiceDefinition;
import mx.gob.imss.dpes.entidadfinancieraback.restclient.BitacoraInterfazClient;
import mx.gob.imss.dpes.interfaces.bitacora.model.BitacoraInterfaz;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.util.logging.Level;

@Provider
public class BitacoraInterfazService extends ServiceDefinition<BitacoraInterfaz, BitacoraInterfaz> {

    @Inject
    @RestClient
    private BitacoraInterfazClient bitacoraInterfazClient;

    //Este metodo fue creado tolerante a fallos ya que sirve unicamente de forma estadistica.
    //Por lo que no genera excepciones en caso de fallo
    @Override
    public Message<BitacoraInterfaz> execute(Message<BitacoraInterfaz> request) throws BusinessException {

        try {
            Response bitacoraInterfazResponse = bitacoraInterfazClient.createBitacoraInterfaz(request.getPayload());
            if(bitacoraInterfazResponse != null && bitacoraInterfazResponse.getStatus() == 200)
                return new Message<>(bitacoraInterfazResponse.readEntity(BitacoraInterfaz.class));
            else
                log.log(Level.SEVERE, "BitacoraInterfazService.execute request = [" + request +
                        "] - Hubo un problema con la respuesta del servicio");
        }
        catch (Exception e) {
            log.log(Level.SEVERE, "BitacoraInterfazService.execute request = [" + request + "]", e);
        }

        return request;
    }
}
