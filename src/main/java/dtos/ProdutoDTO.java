package dtos;

public class ProdutoDTO {
    private String nome;

    public ProdutoDTO(String nome) {
        this.nome = nome;
    }

    public ProdutoDTO() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
