package ws;

import dtos.ProjetistaDTO;
import dtos.ProjetoDTO;
import ejbs.ProjetistaBean;
import ejbs.ProjetoBean;
import entities.Projetista;
import entities.Projeto;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/projectists")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class ProjetistaService {
    @EJB
    private ProjetistaBean projetistaBean;
    @EJB
    private ProjetoBean projetoBean;

    public static ProjetistaDTO toDTO(Projetista projetista) {
        ProjetistaDTO projetistaDTO = new ProjetistaDTO(
                projetista.getId(),
                projetista.getUsername(),
                projetista.getPassword(),
                projetista.getEmail(),
                projetista.getNome()
        );
        if (!projetista.getProjetos().isEmpty()){
            projetistaDTO.setProjetos(ProjetoService.toDTOsNoDetails(projetista.getProjetos()));
        }
        return projetistaDTO;
    }

    public static ProjetistaDTO toDTONoDetails(Projetista projetista) {
        return new ProjetistaDTO(
                projetista.getId(),
                projetista.getUsername(),
                projetista.getPassword(),
                projetista.getEmail(),
                projetista.getNome()
        );
    }

    public static List<ProjetistaDTO> toDTOs(List<Projetista> projetistas) {
        return projetistas.stream().map(ProjetistaService::toDTONoDetails).collect(Collectors.toList());
    }

    private List<ProjetoDTO> toDTOsProjetos(List<Projeto> projetos) {
        return projetos.stream().map(ProjetoService::toDTONoDetails).collect(Collectors.toList());
    }

    @GET
    @Path("/")
    public List<ProjetistaDTO> getAllClientesWS() {
        return toDTOs(projetistaBean.all());
    }

    @GET
    @Path("{id}")
    public Response getProjetista(@PathParam("id")Integer id) throws Exception {
        try{
            Projetista projetista = projetistaBean.find(id);
            return Response.status(Response.Status.OK).entity(toDTO(projetista)).build();
        } catch (Exception e) {
            throw new EJBException("Erro ao encontrar projeto", e);
        }
    }

    @GET
    @Path("{id}/projects")
    public Response getProjetosProjetista(@PathParam("id")Integer id) throws Exception {
        try{
            List<Projeto> projetos = projetistaBean.getProjetosProjetistas(id);
            return Response.status(Response.Status.OK).entity(toDTOsProjetos(projetos)).build();
        } catch (Exception e) {
            throw new EJBException("Erro ao encontrar projetos do projetista", e);
        }
    }
}
