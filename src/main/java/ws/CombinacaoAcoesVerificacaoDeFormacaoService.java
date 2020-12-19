package ws;

import dtos.AplicacaoDTO;
import dtos.CombinacaoAcoesVerificacaoDeformacaoDTO;
import ejbs.AplicacaoBean;
import ejbs.CombinacaoAcoesVerificacaoDeformacaoBean;
import entities.Aplicacao;
import entities.CombinacaoAcoesVerificacaoDeformacao;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;


//rotas
@Path("/combinacaoAcoesVerificacaoDeFormacao") // relative url web path for this service
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”

public class CombinacaoAcoesVerificacaoDeFormacaoService {
    @EJB
    private CombinacaoAcoesVerificacaoDeformacaoBean combinacaoAcoesVerificacaoDeformacaoBean;


    public static CombinacaoAcoesVerificacaoDeformacaoDTO toDTONoDetails(CombinacaoAcoesVerificacaoDeformacao combinacaoAcoesVerificacaoDeformacao) {
        return new CombinacaoAcoesVerificacaoDeformacaoDTO(
                combinacaoAcoesVerificacaoDeformacao.getName()
        );
    }

    public static List<CombinacaoAcoesVerificacaoDeformacaoDTO> toDTOs(List<CombinacaoAcoesVerificacaoDeformacao> combinacaoAcoesVerificacaoDeformacaos) {
        return combinacaoAcoesVerificacaoDeformacaos.stream().map(CombinacaoAcoesVerificacaoDeFormacaoService::toDTONoDetails).collect(Collectors.toList());
    }

    @GET
    @Path("/")
    public List<CombinacaoAcoesVerificacaoDeformacaoDTO> getAllClientesWS() {
        return toDTOs(combinacaoAcoesVerificacaoDeformacaoBean.all());
    }
}
