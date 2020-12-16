package entities;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllProjetistas",
                query = "SELECT c FROM Projetista c ORDER BY c.nome" // JPQL
        )
})
public class Projetista extends User {
    private String nome;
    @OneToMany
    private List<Projeto> projetos;


    public Projetista() {
        this.projetos = new LinkedList<>();
    }

    public Projetista(String username, String password, String email, String nome) {
        super(username, password, email);
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(List<Projeto> projetos) {
        this.projetos = projetos;
    }

    public void addProjeto(Projeto projeto) {
        this.projetos.add(projeto);
    }

    public void removeProjeto(Projeto projeto) {
        this.projetos.remove(projeto);
    }
}


