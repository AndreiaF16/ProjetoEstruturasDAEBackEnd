package ws;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.ResponseBuilder;


import dtos.FicheiroDTO;
import ejbs.FicheiroBean;
import ejbs.ProjetoBean;
import entities.Ficheiro;
import entities.Projeto;
import exceptions.MyEntityNotFoundException;
import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;


//rotas
@Path("/files") // relative url web path for this service
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”

public class FicheiroService {
    @EJB
    private ProjetoBean projetoBean;

    @EJB
    private FicheiroBean ficheiroBean;

    @POST
    @Path("upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response upload(MultipartFormDataInput input) throws Exception {
        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();

        // Get file data to save
        Integer id = Integer.parseInt(uploadForm.get("id").get(0).getBodyAsString());
        Projeto projeto = projetoBean.find(id);

        if(projeto == null) {
            throw new MyEntityNotFoundException("Project with id " + id + " not found.");
        }

        List<InputPart> inputParts = uploadForm.get("file");

        for (InputPart inputPart : inputParts) {
            try {
                MultivaluedMap<String, String> header = inputPart.getHeaders();
                String filename = getFilename(header);

                // convert the uploaded file to inputstream
                InputStream inputStream = inputPart.getBody(InputStream.class, null);

                byte[] bytes = IOUtils.toByteArray(inputStream);

                String path = System.getProperty("user.home") + File.separator + "uploads";
                File customDir = new File(path);

                if (!customDir.exists()) {
                    customDir.mkdir();
                }

                String filepath =  customDir.getCanonicalPath() + File.separator + filename;
                writeFile(bytes, filepath);

                ficheiroBean.create(projeto.getId(), path, filename);

                return Response.status(200).entity("Uploaded file name : " + filename).build();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @GET
    @Path("download/{id}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(@PathParam("id") Integer id) throws MyEntityNotFoundException {
        Ficheiro ficheiro = ficheiroBean.find(id);

        File fileDownload = new File(ficheiro.getFilepath() + File.separator + ficheiro.getFilename());

        Response.ResponseBuilder response = Response.ok((Object) fileDownload);
        response.header("Content-Disposition", "attachment;filename=" + ficheiro.getFilename());

        return response.build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<FicheiroDTO> getDocuments(@PathParam("id") Integer id) throws Exception {
        Projeto projeto = projetoBean.find(id);
        if(projeto == null) {
            throw new MyEntityNotFoundException("Project with id " + id + " not found.");
        }

        return toDTOs(ficheiroBean.getFicheirosProjeto(id));
    }

    @GET
    @Path("{id}/exists")
    public Response hasDocuments(@PathParam("id") Integer id) throws Exception {
        Projeto projeto = projetoBean.find(id);
        if(projeto == null) {
            throw new MyEntityNotFoundException("Project with id " + id + " not found.");
        }

        return Response.status(Response.Status.OK).entity(new Boolean(!projeto.getFicheiros().isEmpty())).build();
    }

    public static FicheiroDTO toDTO(Ficheiro ficheiro) {
        return new FicheiroDTO(
                ficheiro.getId(),
                ficheiro.getFilepath(),
                ficheiro.getFilename(),
                ProjetoService.toDTONoDetails(ficheiro.getProjeto())
        );
    }

    public static List<FicheiroDTO> toDTOs(List<Ficheiro> ficheiros) {
        return ficheiros.stream().map(FicheiroService::toDTO).collect(Collectors.toList());
    }

    private String getFilename(MultivaluedMap<String, String> header) {

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

    private void writeFile(byte[] content, String filename) throws IOException {
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
