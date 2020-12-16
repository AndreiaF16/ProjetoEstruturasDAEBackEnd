package dtos;

public class SobrecargaCategoriaDTO {
    private Character code;
    private String name;

    public SobrecargaCategoriaDTO(Character code, String name) {
        this.code = code;
        this.name = name;
    }

    public SobrecargaCategoriaDTO() {
    }

    public Character getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
