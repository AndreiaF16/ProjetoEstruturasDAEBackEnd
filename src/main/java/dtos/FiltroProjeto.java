package dtos;

public class FiltroProjeto {
    private String nome;
    private Integer idCliente;

    public FiltroProjeto() {
    }

    public FiltroProjeto(String nome, Integer idCliente) {
        this.nome = nome;
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }
}
