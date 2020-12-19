package ws;

import dtos.ProdutoDTO;
import dtos.VarianteDTO;
import ejbs.VarianteBean;
import entities.Produto;
import entities.Variante;
import exceptions.Utils;

import javax.ejb.EJB;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/variante")
public class VarianteService {
    @EJB
    private VarianteBean varianteBean;

    public static VarianteDTO toDTO(Variante variante) {
        return new VarianteDTO(variante.getCodigo(), toDTOforProduto(variante.getProduto()),variante.getNome(), variante.getWeff_p(), variante.getWeff_n(), variante.getAr(), variante.getSigmaC(), variante.getPp());
    }

    public static List<VarianteDTO> toDTOs(List<Variante> variantes) {
        return variantes.stream().map(VarianteService::toDTO).collect(Collectors.toList());
    }

    public static ProdutoDTO toDTOforProduto(Produto produto){
        return new ProdutoDTO(produto.getNome());
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<VarianteDTO> getAllVariatesWS() throws Exception {
        try {
            return toDTOs(varianteBean.all());
        }catch (ConstraintViolationException e) {
            throw new Exception(Utils.getConstraintViolationMessages(e));
        }
    }
}
