package ws;

import dtos.FamiliaDTO;
import dtos.FicheiroDTO;
import dtos.ProjetistaDTO;
import dtos.ProjetoDTO;
import ejbs.FamiliaBean;
import ejbs.FicheiroBean;
import ejbs.ProjetoBean;
import entities.Familia;
import entities.Ficheiro;
import entities.Projetista;
import entities.Projeto;
import exceptions.MyEntityNotFoundException;
import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


//rotas
@Path("/family") // relative url web path for this service
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”

public class FamiliaService {
    @EJB
    private FamiliaBean familiaBean;


    public static FamiliaDTO toDTONoDetails(Familia familia) {
        return new FamiliaDTO(
                familia.getName()
        );
    }

    public static List<FamiliaDTO> toDTOs(List<Familia> familias) {
        return familias.stream().map(FamiliaService::toDTONoDetails).collect(Collectors.toList());
    }

    @GET
    @Path("/")
    public List<FamiliaDTO> getAllClientesWS() {
        return toDTOs(familiaBean.all());
    }
}
