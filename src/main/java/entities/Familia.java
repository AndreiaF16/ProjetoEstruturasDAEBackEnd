package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.io.Serializable;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "Familia",
                query = "SELECT e FROM Familia e ORDER BY e.name" // JPQL
        )
})
public class Familia implements Serializable {
    @Id
    private String name;

    public Familia(String name) {
        this.name = name;
    }

    public Familia() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
