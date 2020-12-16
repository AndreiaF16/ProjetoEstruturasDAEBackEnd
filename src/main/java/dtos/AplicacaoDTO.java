package dtos;

public class AplicacaoDTO {
    private String name;

    public AplicacaoDTO(String name) {
        this.name = name;
    }

    public AplicacaoDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
