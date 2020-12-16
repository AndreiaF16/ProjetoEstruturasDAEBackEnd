package dtos;

public class FamiliaDTO {
    private String name;

    public FamiliaDTO(String name) {
        this.name = name;
    }

    public FamiliaDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
