package dtos;

public class AceiteDTO {
    private Integer idProjeto;
    private Boolean aceite;
    private String observacoes;

    public AceiteDTO() {
    }

    public AceiteDTO(Integer idProjeto, Boolean aceite, String observacoes) {
        this.idProjeto = idProjeto;
        this.aceite = aceite;
        this.observacoes = observacoes;
    }

    public Integer getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(Integer idProjeto) {
        this.idProjeto = idProjeto;
    }

    public Boolean getAceite() {
        return aceite;
    }

    public void setAceite(Boolean aceite) {
        this.aceite = aceite;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
