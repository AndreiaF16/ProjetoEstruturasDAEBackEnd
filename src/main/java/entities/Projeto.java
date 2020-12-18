package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllProjects",
                query = "SELECT p FROM Projeto p ORDER BY p.nomeProjeto" // JPQL
        )
})

public class Projeto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String nomeProjeto;

    @ManyToOne
    private Cliente cliente;

    @OneToMany/*(mappedBy = "ficheiros", cascade = CascadeType.REMOVE)*/
    private List<Ficheiro> ficheiros;

    @OneToMany/*(mappedBy = "estruturas", cascade = CascadeType.REMOVE)*/
    private List<Estrutura> estruturas;

    @ManyToMany
    private List<Projetista> projetistas;

    private Boolean disponivel;

    @Version
    private int version;

    public Projeto() {
        this.estruturas = new LinkedList<>();
        this.ficheiros = new LinkedList<>();
        this.projetistas = new LinkedList<>();
        this.disponivel = false;
    }

    public Projeto( String nomeProjeto, Cliente cliente) {
        this.nomeProjeto = nomeProjeto;
        this.cliente = cliente;
        this.disponivel = false;
        this.estruturas = new LinkedList<>();
        this.ficheiros = new LinkedList<>();
        this.projetistas = new LinkedList<>();
    }

    public Projeto(String nomeProjeto, Cliente cliente, Boolean disponivel) {
        this.nomeProjeto = nomeProjeto;
        this.cliente = cliente;
        this.disponivel = disponivel;
        this.estruturas = new LinkedList<>();
        this.ficheiros = new LinkedList<>();
        this.projetistas = new LinkedList<>();
    }

    public Projeto(Integer id, String nomeProjeto, Cliente cliente, List<Ficheiro> ficheiros, List<Estrutura> estruturas) {
        this.id = id;
        this.nomeProjeto = nomeProjeto;
        this.cliente = cliente;
        this.estruturas = new LinkedList<>();
        this.ficheiros = new LinkedList<>();
        this.projetistas = new LinkedList<>();
        this.ficheiros = ficheiros;
        this.estruturas = estruturas;
        this.disponivel = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeProjeto() {
        return nomeProjeto;
    }

    public void setNomeProjeto(String nomeProjeto) {
        this.nomeProjeto = nomeProjeto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Ficheiro> getFicheiros() {
        return ficheiros;
    }

    public void setFicheiros(List<Ficheiro> ficheiros) {
        this.ficheiros = ficheiros;
    }

    public void addFicheiros(Ficheiro ficheiro) {
        this.ficheiros.add(ficheiro);
    }

    public void removeFicheiros(Ficheiro ficheiro) {
        this.ficheiros.remove(ficheiro);
    }


    public List<Estrutura> getEstruturas() {
        return estruturas;
    }


    public void addEstruturas(Estrutura estrutura) {
        this.estruturas.add(estrutura);
    }

    public void removeEstruturas(Estrutura estrutura) {
        this.estruturas.remove(estrutura);
    }

    public void setEstruturas(List<Estrutura> estruturas) {
        this.estruturas = estruturas;
    }

    public List<Projetista> getProjetistas() {
        return projetistas;
    }

    public void setProjetistas(List<Projetista> projetista) {
        this.projetistas = projetista;
    }

    public void addProjetista(Projetista projetista) {
        this.projetistas.add(projetista);
    }

    public void removeProjetista(Projetista projetista) {
        this.projetistas.remove(projetista);
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

}
