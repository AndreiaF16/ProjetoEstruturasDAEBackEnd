package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.io.Serializable;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "CombinacaoAcoesVerificacaoDeformacao",
                query = "SELECT e FROM CombinacaoAcoesVerificacaoDeformacao e ORDER BY e.name" // JPQL
        )
})
public class CombinacaoAcoesVerificacaoDeformacao implements Serializable {
    @Id
    private String name;

    public CombinacaoAcoesVerificacaoDeformacao() {
    }

    public CombinacaoAcoesVerificacaoDeformacao(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
