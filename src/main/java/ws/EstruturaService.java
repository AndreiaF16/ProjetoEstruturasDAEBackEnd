package ws;

import dtos.*;
import ejbs.EmailBean;
import ejbs.EstruturaBean;
import ejbs.FamiliaBean;
import entities.*;
import exceptions.MyConstraintViolationException;
import exceptions.MyEntityNotFoundException;
import exceptions.Utils;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashSet;
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

    @EJB
    private FamiliaBean familiaBean;

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


    @POST
    @Path("/")
    public EstruturaDTO createEstrutura(EstruturaDTO estruturaDTO) throws Exception {
        try {
            Set<Familia> familias = new HashSet<>();
            for (FamiliaDTO familia : estruturaDTO.getFamilias()) {
                familias.add(familiaBean.findOrFail(familia.getName()));
            }
            Estrutura estrutura = estruturaBean.create(estruturaDTO.getProjeto().getId(),familias,
                    estruturaDTO.getAplicacao().getName(),
                    estruturaDTO.getSobrecargaCategoria().getCode(),
                    estruturaDTO.getMaterial(),
                    estruturaDTO.getNumVaos(),
                    estruturaDTO.getComprimentoVao(),
                    estruturaDTO.getEspacamentoEntreVigas(),
                    estruturaDTO.getAngulo(),
                    estruturaDTO.getCargaPermanente(),
                    estruturaDTO.getSobrecarga(),
                    estruturaDTO.getNeve(),
                    estruturaDTO.isAltitudeMaior1000(),
                    estruturaDTO.getPressaoVento(),
                    estruturaDTO.getSuccaoVento(),
                    estruturaDTO.isContraventamentoTotal(),
                    estruturaDTO.getContraventamentoLateral(),
                    estruturaDTO.isContribuicaoChapaRevestimento(),
                    estruturaDTO.getNumFixacoes(),
                    estruturaDTO.getInerciaChapaRevestimento(),
                    estruturaDTO.getCombinacaoAcoesVerificacaoDeformacao().getName(),
                    estruturaDTO.getLimiteDeformacao(),
                    estruturaDTO.getCoeficienteCombinacaoSobrecarga(),
                    estruturaDTO.getCoeficienteCombinacaoSobrecargaNum1(),
                    estruturaDTO.getCoeficienteCombinacaoSobrecargaNum2(),
                    estruturaDTO.getCoeficienteCombinacaoSobrecargaNum3(),
                    estruturaDTO.getCoeficienteCombinacaoNeve(),
                    estruturaDTO.getCoeficienteCombinacaoNeveNum1(),
                    estruturaDTO.getCoeficienteCombinacaoNeveNum2(),
                    estruturaDTO.getCoeficienteCombinacaoNeveNum3(),
                    estruturaDTO.getCoeficienteCombinacaoVento(),
                    estruturaDTO.getCoeficienteCombinacaoNeveVento1(),
                    estruturaDTO.getCoeficienteCombinacaoNeveVento2(),
                    estruturaDTO.getCoeficienteCombinacaoNeveVento3());
            return toDTO(estrutura);
        } catch (ConstraintViolationException e) {
            throw new Exception(Utils.getConstraintViolationMessages(e));
        }
    }
    @GET
    @Path("/{id}")
    public Response getStructure(@PathParam("id")Integer id) {
        try{
            Estrutura estrutura = estruturaBean.find(id);
            return Response.status(Response.Status.OK).entity(toDTO(estrutura)).build();
        } catch (ConstraintViolationException e) {
            throw new EJBException("Erro ao realizar update ao projeto", e);
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
        }catch (ConstraintViolationException e) {
            throw new Exception(Utils.getConstraintViolationMessages(e));
        }
    }

    @PUT
    @Path("/{id}")
    public EstruturaDTO updateEstrutura(int id, EstruturaDTO estruturaDTO){
        try{
            estruturaBean.findOrFail(id);
            Set<Familia> familias = new HashSet<>();
            for(FamiliaDTO familia : estruturaDTO.getFamilias()){
                familias.add(familiaBean.findOrFail(familia.getName()));
            }
            Estrutura estruturaUpdated = estruturaBean.update(id, familias, estruturaDTO.getAplicacao().getName(), estruturaDTO.getSobrecargaCategoria().getCode(), estruturaDTO.getMaterial(), estruturaDTO.getNumVaos(), estruturaDTO.getComprimentoVao(),
                    estruturaDTO.getEspacamentoEntreVigas(), estruturaDTO.getAngulo(), estruturaDTO.getCargaPermanente(), estruturaDTO.getSobrecarga(), estruturaDTO.getNeve(), estruturaDTO.isAltitudeMaior1000(),
                    estruturaDTO.getPressaoVento(), estruturaDTO.getSuccaoVento(),estruturaDTO.isContraventamentoTotal(), estruturaDTO.getContraventamentoLateral(), estruturaDTO.isContribuicaoChapaRevestimento(),
                    estruturaDTO.getNumFixacoes(), estruturaDTO.getInerciaChapaRevestimento(),estruturaDTO.getCombinacaoAcoesVerificacaoDeformacao().getName(), estruturaDTO.getLimiteDeformacao(), estruturaDTO.getCoeficienteCombinacaoSobrecarga(),
                    estruturaDTO.getCoeficienteCombinacaoSobrecargaNum1(), estruturaDTO.getCoeficienteCombinacaoSobrecargaNum2(), estruturaDTO.getCoeficienteCombinacaoSobrecargaNum3(), estruturaDTO.getCoeficienteCombinacaoNeve(), estruturaDTO.getCoeficienteCombinacaoNeveNum1(),
                    estruturaDTO.getCoeficienteCombinacaoNeveNum2(), estruturaDTO.getCoeficienteCombinacaoNeveNum3(), estruturaDTO.getCoeficienteCombinacaoVento(), estruturaDTO.getCoeficienteCombinacaoNeveVento1(), estruturaDTO.getCoeficienteCombinacaoNeveVento2(),
                    estruturaDTO.getCoeficienteCombinacaoNeveVento3());
            return toDTO(estruturaUpdated);
        }catch (Exception e){
            throw new EJBException("Erro no update");
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteEstrutura(int id){
        try{
            estruturaBean.delete(id);
            return Response.status(Response.Status.OK).build();
        }catch (Exception e){
            throw new EJBException("Erro ao eliminar esturturas");
        }
    }
}
