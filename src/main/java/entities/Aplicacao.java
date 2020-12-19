package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.io.Serializable;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "Aplicacao",
                query = "SELECT e FROM Aplicacao e ORDER BY e.name" // JPQL
        )
})
public class Aplicacao implements Serializable {
    @Id
    private String name;

    public Aplicacao() {
    }

    public Aplicacao(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
