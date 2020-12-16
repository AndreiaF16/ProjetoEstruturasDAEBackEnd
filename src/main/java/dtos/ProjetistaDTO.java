package dtos;

import java.util.LinkedList;
import java.util.List;

public class ProjetistaDTO extends UserDTO{
    private String nome;
    private List<ProjetoDTO> projetos;

    public ProjetistaDTO() {
        this.projetos= new LinkedList<>();
    }

    public ProjetistaDTO(Integer id, String username, String password, String email, String nome) {
        super(id, username, password, email);
        this.nome = nome;
        this.projetos= new LinkedList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<ProjetoDTO> getProjetos() {
        return projetos;
    }

    public void setProjetos(List<ProjetoDTO> projetos) {
        this.projetos = projetos;
    }
}
