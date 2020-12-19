package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.io.Serializable;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "SobreCargaCategoria",
                query = "SELECT e FROM SobreCargaCategoria e ORDER BY e.name" // JPQL
        )
})
public class SobreCargaCategoria implements Serializable {
    @Id
    private Character code;

    private String name;

    public SobreCargaCategoria() {
    }

    public SobreCargaCategoria(Character code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Character getCode() {
        return code;
    }

    public void setCode(Character code) {
        this.code = code;
    }
}
