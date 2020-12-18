package ws;

import dtos.*;
import ejbs.EmailBean;
import ejbs.EstruturaBean;
import entities.*;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//rotas
@Path("/structures") // relative url web path for this service
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”

public class EstruturaService {

    @EJB
    private EstruturaBean estruturaBean;

    @EJB
    private EmailBean emailBean;

    public static AplicacaoDTO aplicacaoToDTO(Aplicacao aplicacao){
        return new AplicacaoDTO(aplicacao.getName());
    }

    public static SobrecargaCategoriaDTO sobrecargaCategoriaDTO(SobreCargaCategoria sobreCargaCategoria){
        return new SobrecargaCategoriaDTO(sobreCargaCategoria.getCode(), sobreCargaCategoria.getName());
    }

    public static CombinacaoAcoesVerificacaoDeformacaoDTO combinacaoAcoesVerificacaoDeformacaoDTO(CombinacaoAcoesVerificacaoDeformacao combinacaoAcoesVerificacaoDeformacao){
        return new CombinacaoAcoesVerificacaoDeformacaoDTO(combinacaoAcoesVerificacaoDeformacao.getName());
    }

    public static FamiliaDTO familiaDTO(Familia familia){
        return new FamiliaDTO(familia.getName());
    }

    public static ProjetistaDTO projetistaDTO(Projetista projetista){
        ProjetistaDTO projetistaDTO = new ProjetistaDTO(projetista.getId(), projetista.getUsername(),projetista.getPassword(),projetista.getEmail(), projetista.getNome());
        return projetistaDTO;
    }

    public static Set<FamiliaDTO> familiaToDTOs(Set<Familia> familias){
        return familias.stream().map(EstruturaService::familiaDTO).collect(Collectors.toSet());
    }

    public static EstruturaDTO toDTO(Estrutura estrutura) {
        return new EstruturaDTO(
                estrutura.getId(),
                estrutura.getAceite(),
                estrutura.getObservacoes(),
                ProjetoService.toDTONoDetails(estrutura.getProjeto()),
                familiaToDTOs(estrutura.getFamilias()),
                aplicacaoToDTO(estrutura.getAplicacao()),
                sobrecargaCategoriaDTO(estrutura.getSobreCargaCategoria()), estrutura.getMaterial(),
                estrutura.getNumVaos(), estrutura.getComprimentoVao(), estrutura.getEspacamentoEntreVigas(),
                estrutura.getAngulo(), estrutura.getCargaPermanente(), estrutura.getSobrecarga(),
                estrutura.getNeve(), estrutura.isAltitudeMaior1000(), estrutura.getPressaoVento(),
                estrutura.getSuccaoVento(), estrutura.isContraventamentoTotal(), estrutura.getContraventamentoLateral(),
                estrutura.isContribuicaoChapaRevestimento(), estrutura.getNumFixacoes(),
                estrutura.getInerciaChapaRevestimento(),
                combinacaoAcoesVerificacaoDeformacaoDTO(estrutura.getCombinacaoAcoesVerificacaoDeformacao()),
                estrutura.getLimiteDeformacao(), estrutura.getCoeficienteCombinacaoSobrecarga(),
                estrutura.getCoeficienteCombinacaoSobrecargaNum1(), estrutura.getCoeficienteCombinacaoSobrecargaNum2(),
                estrutura.getCoeficienteCombinacaoSobrecargaNum3(), estrutura.getCoeficienteCombinacaoNeve(),
                estrutura.getCoeficienteCombinacaoNeveNum1(), estrutura.getCoeficienteCombinacaoNeveNum2(),
                estrutura.getCoeficienteCombinacaoNeveNum3(), estrutura.getCoeficienteCombinacaoVento(),
                estrutura.getCoeficienteCombinacaoNeveVento1(), estrutura.getCoeficienteCombinacaoNeveVento2(),
                estrutura.getCoeficienteCombinacaoNeveVento3());
    }

    public static List<EstruturaDTO> toDTOs(List<Estrutura> estruturas) {
        return estruturas.stream().map(EstruturaService::toDTO).collect(Collectors.toList());
    }


    @GET
    @Path("/")
    public List<EstruturaDTO> getAllEstruturasWS() {
        return toDTOs(estruturaBean.all());
    }

    @GET
    @Path("/{id}")
    public Response getStructure(@PathParam("id")Integer id) {
        try{
            Estrutura estrutura = estruturaBean.find(id);
            return Response.status(Response.Status.OK).entity(toDTO(estrutura)).build();
        } catch (Exception e) {
            throw new EJBException("Erro ao encontrar projeto", e);
        }
    }

    @PUT
    @Path("/{id}/accept")
    public Response updateEstruturaAceite(AceiteDTO aceiteDTO) throws Exception {

        try {
            Estrutura estrutura = estruturaBean.aceitarEstrutura(aceiteDTO.getIdProjeto(),aceiteDTO.getAceite(),aceiteDTO.getObservacoes());

            if (aceiteDTO.getAceite()){
                emailBean.send(estrutura.getProjeto().getCliente().getEmail(),
                        "Estrutura "+estrutura.getId()+"aceite",
                        "A estrutura "+estrutura.getId()+" foi aceite pelo cliente. Pode consultar para informações adicionais!");
            }
            else {
                emailBean.send(estrutura.getProjeto().getCliente().getEmail(),
                        "Estrutura "+estrutura.getId()+" não aceite",
                        "A estrutura "+estrutura.getId()+" foi rejeitada pelo cliente. Pode consultar para informações adicionais!");
            }

            return Response.status(Response.Status.OK).build();
        }catch (Exception e){
            throw new EJBException("Erro ao disponibilizar esturturas");
        }
    }
}
