package dtos;

public class FicheiroDTO {
    private Integer id;
    private String filepath;
    private String filename;
    private ProjetoDTO projeto;

    public FicheiroDTO() {
    }

    public FicheiroDTO(Integer id, String filepath, String filename, ProjetoDTO projeto) {
        this.id = id;
        this.filepath = filepath;
        this.filename = filename;
        this.projeto = projeto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public ProjetoDTO getProjeto() {
        return projeto;
    }

    public void setProjeto(ProjetoDTO projeto) {
        this.projeto = projeto;
    }
}
