package ws;

import dtos.ClienteDTO;
import dtos.ProjetoDTO;
import ejbs.ClienteBean;
import ejbs.ProjetoBean;
import entities.Cliente;
import entities.Projeto;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/clients")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class ClienteService {
    @EJB
    private ClienteBean clienteBean;
    @EJB
    private ProjetoBean projetoBean;

    public static ClienteDTO toDTO(Cliente cliente) {
        return new ClienteDTO(
                cliente.getId(),
                cliente.getUsername(),
                cliente.getPassword(),
                cliente.getEmail(),
                cliente.getNome(),
                cliente.getContacto(),
                cliente.getMorada()
        );
    }

    public static List<ClienteDTO> toDTOs(List<Cliente> clientes) {
        return clientes.stream().map(ClienteService::toDTO).collect(Collectors.toList());
    }

    private List<ProjetoDTO> toDTOsProjetos(List<Projeto> projetos) {
        return projetos.stream().map(ProjetoService::toDTONoDetails).collect(Collectors.toList());
    }

    @GET
    @Path("/")
    public List<ClienteDTO> getAllClientesWS() {
        return toDTOs(clienteBean.all());
    }

    @GET
    @Path("/{id}")
    public Response getCliente(@PathParam("id")Integer id) throws Exception {
        try{
            Cliente cliente = clienteBean.find(id);
            return Response.status(Response.Status.OK).entity(toDTO(cliente)).build();
        } catch (Exception e) {
            throw new EJBException("Erro ao encontrar estrutura", e);
        }
    }

    @GET
    @Path("/{id}/projects")
    public Response getProjetosCliente(@PathParam("id")Integer id) throws Exception {
        try{
            List<Projeto> projetos = clienteBean.getProjetosCliente(id);
            return Response.status(Response.Status.OK).entity(toDTOsProjetos(projetos)).build();
        } catch (Exception e) {
            throw new EJBException("Erro ao encontrar projeto", e);
        }
    }
}
