package ws;

import dtos.AplicacaoDTO;
import dtos.SobrecargaCategoriaDTO;
import ejbs.AplicacaoBean;
import ejbs.SobrecargaCategoriaBean;
import entities.Aplicacao;
import entities.SobreCargaCategoria;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;


//rotas
@Path("/sobreCargaCategoria") // relative url web path for this service
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”

public class SobreCargaCategoriaService {
    @EJB
    private SobrecargaCategoriaBean sobrecargaCategoriaBean;


    public static SobrecargaCategoriaDTO toDTONoDetails(SobreCargaCategoria sobreCargaCategoria) {
        return new SobrecargaCategoriaDTO(
                sobreCargaCategoria.getCode(),
                sobreCargaCategoria.getName()
        );
    }

    public static List<SobrecargaCategoriaDTO> toDTOs(List<SobreCargaCategoria> sobreCargaCategorias) {
        return sobreCargaCategorias.stream().map(SobreCargaCategoriaService::toDTONoDetails).collect(Collectors.toList());
    }

    @GET
    @Path("/")
    public List<SobrecargaCategoriaDTO> getAllClientesWS() {
        List<SobreCargaCategoria> all = sobrecargaCategoriaBean.all();
        return toDTOs(all);
    }
}
