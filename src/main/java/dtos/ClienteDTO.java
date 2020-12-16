package dtos;

public class ClienteDTO extends UserDTO{

    private String nome;
    private long contacto;
    private String morada;

    public ClienteDTO() {
    }

    public ClienteDTO(Integer id, String username, String password, String email, String nome, long contacto, String morada) {
        super(id, username, password, email);
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
