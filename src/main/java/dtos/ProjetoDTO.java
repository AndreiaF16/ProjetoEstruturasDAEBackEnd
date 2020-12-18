package dtos;

import entities.Cliente;
import entities.Estrutura;
import entities.Ficheiro;

import java.util.LinkedList;
import java.util.List;

public class ProjetoDTO {
    private Integer id;
    private String nomeProjeto;
    private Integer clienteId;
    private List<FicheiroDTO> ficheiros;
    private List<EstruturaDTO> estruturas;
    private List<ProjetistaDTO> projetistas;
    private Boolean disponivel;

    public ProjetoDTO() {
        this.ficheiros = new LinkedList<>();
        this.estruturas = new LinkedList<>();
        this.projetistas = new LinkedList<>();

    }

    public ProjetoDTO(Integer id, String nomeProjeto, Integer clienteId, Boolean disponivel) {
        this.id = id;
        this.nomeProjeto = nomeProjeto;
        this.clienteId = clienteId;
        this.disponivel = disponivel;
        this.ficheiros = new LinkedList<>();
        this.estruturas = new LinkedList<>();
        this.projetistas = new LinkedList<>();
    }

    public ProjetoDTO(Integer id, String nomeProjeto, Integer clienteId, List<FicheiroDTO> ficheiros, List<EstruturaDTO> estruturas, List<ProjetistaDTO> projetistas, Boolean disponivel) {
        this.id = id;
        this.nomeProjeto = nomeProjeto;
        this.clienteId = clienteId;
        this.ficheiros = ficheiros;
        this.estruturas = estruturas;
        this.projetistas = projetistas;
        this.disponivel = disponivel;
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

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public List<FicheiroDTO> getFicheiros() {
        return ficheiros;
    }

    public void setFicheiros(List<FicheiroDTO> ficheiros) {
        this.ficheiros = ficheiros;
    }

    public List<EstruturaDTO> getEstruturas() {
        return estruturas;
    }

    public void setEstruturas(List<EstruturaDTO> estruturas) {
        this.estruturas = estruturas;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    public List<ProjetistaDTO> getProjetistas() {
        return projetistas;
    }

    public void setProjetistas(List<ProjetistaDTO> projetistas) {
        this.projetistas = projetistas;
    }
}
