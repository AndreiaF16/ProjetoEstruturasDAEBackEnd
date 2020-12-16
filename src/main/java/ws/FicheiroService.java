package ws;

import dtos.FicheiroDTO;
import ejbs.FicheiroBean;
import ejbs.ProjetoBean;
import entities.Ficheiro;
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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


//rotas
@Path("/files") // relative url web path for this service
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”

public class FicheiroService {
    @EJB
    private FicheiroBean ficheiroBean;
    @EJB
    private ProjetoBean projetoBean;

    public static FicheiroDTO toDTO(Ficheiro ficheiro) {
        return new FicheiroDTO(
                ficheiro.getId(),
                ficheiro.getFilepath(),
                ficheiro.getFilename(),
                ProjetoService.toDTO(ficheiro.getProjeto())
        );
    }

    public static List<FicheiroDTO> toDTOs(List<Ficheiro> ficheiros) {
        return ficheiros.stream().map(FicheiroService::toDTO).collect(Collectors.toList());
    }

    @GET
    @Path("/")
    public List<FicheiroDTO> getAllFicheirosWS() {
        return toDTOs(ficheiroBean.all());
    }

    @POST
    @Path("{id}/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(
            @PathParam("id") Integer id,
            MultipartFormDataInput input) throws Exception {
        Projeto projeto = projetoBean.find(id);
        if (projeto == null) {
            throw new MyEntityNotFoundException("Student with username " +
                    id + " not found.");
        }
        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
// Get file data to save
        List<InputPart> inputParts = uploadForm.get("attachment");
        for (InputPart inputPart : inputParts) {
            try {
                MultivaluedMap<String, String> header =
                        inputPart.getHeaders();
                String fileName = getFileName(header);
// convert the uploaded file to inputstream
                InputStream inputStream =
                        inputPart.getBody(InputStream.class, null);
                byte[] bytes = IOUtils.toByteArray(inputStream);
                String path = System.getProperty("user.home") +
                        File.separator + "uploads";
                File customDir = new File(path);
                if (!customDir.exists()) {
                    customDir.mkdir();
                }
                fileName = customDir.getCanonicalPath() + File.separator +
                        fileName;
                writeFile(bytes, fileName);
                ficheiroBean.create(
                        id,
                        path,
                        fileName);
                return Response.status(200).entity("Nome do ficheiro atualizado: "
                        + fileName).build();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    @POST
    @Path("/download")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @Consumes("application/x-www-form-urlencoded")
    public Response downloadFileWithPost(@FormParam("file") String file) {
        String path = System.getProperty("user.home") + File.separator +
                "uploads";
        File fileDownload = new File(path + File.separator + file);
        Response.ResponseBuilder response = Response.ok((Object) fileDownload);
        response.header("Content-Disposition", "attachment;filename=" +
                file);
        return response.build();
    }

    @GET
    @Path("{id}/files/")
    public List<FicheiroDTO> getDocuments(@PathParam("id") Integer id) throws Exception {
        Projeto projeto = projetoBean.find(id);
        if (projeto == null) {
            throw new MyEntityNotFoundException("Ficheiro com o id " + id + " não foi encontrado.");
        }
        return toDTOs(ficheiroBean.getFicheirosProjeto(id));
    }
    @GET
    @Path("{id}/hasFiles/")
    public Response hasDocuments(@PathParam("id") Integer id) throws Exception {
        Projeto projeto = projetoBean.find(id);
        if (projeto == null) {
            throw new MyEntityNotFoundException("Ficheiro com o id " + id + " não foi encontrado.");
        }
        return Response.status(Response.Status.OK)
                .entity(new Boolean(!projeto.getFicheiros().isEmpty()))
                .build();
    }

    private String getFileName(MultivaluedMap<String, String> header) {
        String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
        for (String filename : contentDisposition) {
            if ((filename.trim().startsWith("filename"))) {
                String[] name = filename.split("=");
                String finalFileName = name[1].trim().replaceAll("\"", "");
                return finalFileName;
            }
        }
        return "unknown";
    }
    private void writeFile(byte[] content, String filename) throws
            IOException {
        File file = new File(filename);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fop = new FileOutputStream(file);
        fop.write(content);
        fop.flush();
        fop.close();
        System.out.println("Written: " + filename);
    }
}
