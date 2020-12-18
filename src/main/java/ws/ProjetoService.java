package ws;

import dtos.EstruturaDTO;
import dtos.ProjetistaDTO;
import dtos.ProjetoDTO;
import ejbs.EmailBean;
import ejbs.ProjetistaBean;
import ejbs.ProjetoBean;
import entities.Estrutura;
import entities.Projeto;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

//rotas
@Path("/projects") // relative url web path for this service
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”


public class ProjetoService {

    @EJB
    private ProjetoBean projetoBean;
    @EJB
    private EmailBean emailBean;

    public static ProjetoDTO toDTO(Projeto projeto) {
        ProjetoDTO prj = new ProjetoDTO(
                projeto.getId(),
                projeto.getNomeProjeto(),
                projeto.getCliente().getId(),
                projeto.getDisponivel()
        );
        if (!projeto.getFicheiros().isEmpty()){
            prj.setFicheiros(FicheiroService.toDTOs(projeto.getFicheiros()));
        }
        if (!projeto.getEstruturas().isEmpty()){
            prj.setEstruturas(EstruturaService.toDTOs(projeto.getEstruturas()));
        }
        if (!projeto.getProjetistas().isEmpty()){
            prj.setProjetistas(ProjetistaService.toDTOs(projeto.getProjetistas()));
        }
        return  prj;
    }

    public static ProjetoDTO toDTONoDetails(Projeto projeto) {
        return new ProjetoDTO(
                projeto.getId(),
                projeto.getNomeProjeto(),
                projeto.getCliente().getId(),
                projeto.getDisponivel()
        );
    }

    private List<ProjetoDTO> toDTOs(List<Projeto> projetos) {
        return projetos.stream().map(ProjetoService::toDTO).collect(Collectors.toList());
    }

    public static List<ProjetoDTO> toDTOsNoDetails(List<Projeto> projetos) {
        return projetos.stream().map(ProjetoService::toDTONoDetails).collect(Collectors.toList());
    }

    @GET
    @Path("/")
    public List<ProjetoDTO> getAllProjetosWS() {
        return toDTOsNoDetails(projetoBean.all());
    }

    @GET
    @Path("/{id}")
    public Response getProjectDetails(@PathParam("id")Integer id) throws Exception {
        Projeto projeto = projetoBean.find(id);
        try{
            ProjetoDTO projetoDTO = toDTO(projeto);
            return Response.status(Response.Status.OK).entity(projetoDTO).build();
        } catch (Exception e) {
            throw new EJBException("Erro ao encontrar projeto", e);
        }
    }

    @POST
    @Path("/")
    public Response createNewProjeto(ProjetoDTO projetoDTO) throws Exception {
        Projeto newProjeto =  projetoBean.create(
                projetoDTO.getNomeProjeto(),
                projetoDTO.getClienteId(),
                projetoDTO.getDisponivel());

        if(!projetoDTO.getProjetistas().isEmpty()) {
            for (ProjetistaDTO projetista : projetoDTO.getProjetistas()) {
                projetoBean.addProjetistaToProject(projetista.getId(), newProjeto.getId());
            }
        }else{
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        try {
            if (newProjeto == null)
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            return Response.status(Response.Status.CREATED).entity(toDTO(newProjeto)).build();
        }catch (Exception e){
            throw new EJBException("Erro a criar projeto");
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateProjeto(ProjetoDTO projetoDTO) throws Exception {
        Projeto projeto =  projetoBean.update(
                projetoDTO.getId(),
                projetoDTO.getNomeProjeto());
        try {
            if (projeto == null)
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            return Response.status(Response.Status.CREATED).entity(toDTO(projeto)).build();
        }catch (Exception e){
            throw new EJBException("Erro a atualizar projeto");
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteProjeto(@PathParam("id") Integer id) throws Exception {
        try {
            projetoBean.delete(id);
            return Response.status(Response.Status.OK).build();
        }catch (Exception e){
            throw new EJBException("Erro a apagar projeto");
        }
    }

    @PUT
    @Path("/{id}/available")
    public Response updateProjetoDisponibilidade(@PathParam("id") Integer id) throws Exception {

        try {
            Projeto projeto = projetoBean.find(id);
            projetoBean.disponibilizarProjeto(id);
            emailBean.send(projeto.getCliente().getEmail(),
                    "Projeto "+projeto.getNomeProjeto()+" disponivel",
                    "O projeto "+projeto.getNomeProjeto()+" foi agora diponibilizado! Pode consultá-lo e selecionar/adjudicar as estruturas e respetivos materiais propostos. \n Cumprimentos!");
            return Response.status(Response.Status.OK).build();
        }catch (Exception e){
            throw new EJBException("Erro a disponibilizar projeto");
        }
    }


}
