package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllClients",
                query = "SELECT c FROM Cliente c ORDER BY c.nome" // JPQL
        )
})
public class Cliente extends User{
    @NotNull
    private String nome;

    @NotNull
    private long contacto;

    @NotNull
    private String morada;


    public Cliente() {
    }



    public Cliente(String username, String password, String email, @NotNull String nome, @NotNull long contacto, @NotNull String morada) {
        super(username, password, email);
        this.nome = nome;
        this.contacto = contacto;
        this.morada = morada;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getContacto() {
        return contacto;
    }

    public void setContacto(long contacto) {
        this.contacto = contacto;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }
}
