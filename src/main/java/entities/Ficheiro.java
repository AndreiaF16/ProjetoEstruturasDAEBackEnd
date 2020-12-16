package entities;

import javax.persistence.*;
import java.io.Serializable;

@NamedQuery(name = "getStudentDocuments", query = "SELECT doc FROM Ficheiro doc WHERE doc.projeto.id = :id")
@Entity
public class Ficheiro implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String filepath;

    private String filename;

    @ManyToOne
    private Projeto projeto;

    public Ficheiro() {
    }

    public Ficheiro(String filepath, String filename, Projeto projeto) {
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

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
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


}
