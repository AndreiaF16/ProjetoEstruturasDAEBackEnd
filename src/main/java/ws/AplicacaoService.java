package ws;

import dtos.AplicacaoDTO;
import dtos.FamiliaDTO;
import ejbs.AplicacaoBean;
import ejbs.FamiliaBean;
import entities.Aplicacao;
import entities.Familia;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;


//rotas
@Path("/application") // relative url web path for this service
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”

public class AplicacaoService {
    @EJB
    private AplicacaoBean aplicacaoBean;


    public static AplicacaoDTO toDTONoDetails(Aplicacao aplicacao) {
        return new AplicacaoDTO(
                aplicacao.getName()
        );
    }

    public static List<AplicacaoDTO> toDTOs(List<Aplicacao> aplicacaos) {
        return aplicacaos.stream().map(AplicacaoService::toDTONoDetails).collect(Collectors.toList());
    }

    @GET
    @Path("/")
    public List<AplicacaoDTO> getAllClientesWS() {
        return toDTOs(aplicacaoBean.all());
    }
}
