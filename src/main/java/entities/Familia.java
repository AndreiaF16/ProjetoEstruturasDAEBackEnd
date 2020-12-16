package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
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
