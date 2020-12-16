package dtos;

import java.util.LinkedHashSet;
import java.util.Set;

public class EstruturaDTO {
    private Integer id;

    private Boolean aceite;

    private String observacoes;

    private ProjetoDTO projeto;

    private Set<FamiliaDTO> familias;

    private AplicacaoDTO aplicacao;

    private SobrecargaCategoriaDTO sobrecargaCategoria;

    private String material;

    private int numVaos;

    private int comprimentoVao;

    private int espacamentoEntreVigas;

    private double angulo;

    private double cargaPermanente;

    private double sobrecarga;

    private double neve;

    private boolean altitudeMaior1000;

    private double pressaoVento;

    private double succaoVento;

    private boolean contraventamentoTotal;

    private int contraventamentoLateral; //1-4 apenas

    private boolean contribuicaoChapaRevestimento;

    private int numFixacoes;

    private String inerciaChapaRevestimento;

    private CombinacaoAcoesVerificacaoDeformacaoDTO combinacaoAcoesVerificacaoDeformacao;

    private double limiteDeformacao;

    private double coeficienteCombinacaoSobrecarga;

    private double  coeficienteCombinacaoSobrecargaNum1;

    private double  coeficienteCombinacaoSobrecargaNum2;

    private double  coeficienteCombinacaoSobrecargaNum3;

    private double coeficienteCombinacaoNeve;

    private double  coeficienteCombinacaoNeveNum1;

    private double  coeficienteCombinacaoNeveNum2;

    private double  coeficienteCombinacaoNeveNum3;

    private double coeficienteCombinacaoVento;

    private double  coeficienteCombinacaoNeveVento1;

    private double  coeficienteCombinacaoNeveVento2;

    private double  coeficienteCombinacaoNeveVento3;

    public EstruturaDTO() {
        this.familias = new LinkedHashSet<>();
    }

   /* public EstruturaDTO(Integer id, Set<FamiliaDTO> familias, AplicacaoDTO aplicacao, SobrecargaCategoriaDTO sobrecargaCategoria, String material, int numVaos, int comprimentoVao, int espacamentoEntreVigas, double angulo, double cargaPermanente, double sobrecarga, double neve, boolean altitudeMaior1000, double pressaoVento, double succaoVento, boolean contraventamentoTotal, int contraventamentoLateral, boolean contribuicaoChapaRevestimento, int numFixacoes, String inerciaChapaRevestimento, CombinacaoAcoesVerificacaoDeformacaoDTO combinacaoAcoesVerificacaoDeformacao, double limiteDeformacao, double coeficienteCombinacaoSobrecarga, double coeficienteCombinacaoSobrecargaNum1, double coeficienteCombinacaoSobrecargaNum2, double coeficienteCombinacaoSobrecargaNum3, double coeficienteCombinacaoNeve, double coeficienteCombinacaoNeveNum1, double coeficienteCombinacaoNeveNum2, double coeficienteCombinacaoNeveNum3, double coeficienteCombinacaoVento, double coeficienteCombinacaoNeveVento1, double coeficienteCombinacaoNeveVento2, double coeficienteCombinacaoNeveVento3) {
        this.id = id;
        this.familias = familias;
        this.aplicacao = aplicacao;
        this.sobrecargaCategoria = sobrecargaCategoria;
        this.material = material;
        this.numVaos = numVaos;
        this.comprimentoVao = comprimentoVao;
        this.espacamentoEntreVigas = espacamentoEntreVigas;
        this.angulo = angulo;
        this.cargaPermanente = cargaPermanente;
        this.sobrecarga = sobrecarga;
        this.neve = neve;
        this.altitudeMaior1000 = altitudeMaior1000;
        this.pressaoVento = pressaoVento;
        this.succaoVento = succaoVento;
        this.contraventamentoTotal = contraventamentoTotal;
        this.contraventamentoLateral = contraventamentoLateral;
        this.contribuicaoChapaRevestimento = contribuicaoChapaRevestimento;
        this.numFixacoes = numFixacoes;
        this.inerciaChapaRevestimento = inerciaChapaRevestimento;
        this.combinacaoAcoesVerificacaoDeformacao = combinacaoAcoesVerificacaoDeformacao;
        this.limiteDeformacao = limiteDeformacao;
        this.coeficienteCombinacaoSobrecarga = coeficienteCombinacaoSobrecarga;
        this.coeficienteCombinacaoSobrecargaNum1 = coeficienteCombinacaoSobrecargaNum1;
        this.coeficienteCombinacaoSobrecargaNum2 = coeficienteCombinacaoSobrecargaNum2;
        this.coeficienteCombinacaoSobrecargaNum3 = coeficienteCombinacaoSobrecargaNum3;
        this.coeficienteCombinacaoNeve = coeficienteCombinacaoNeve;
        this.coeficienteCombinacaoNeveNum1 = coeficienteCombinacaoNeveNum1;
        this.coeficienteCombinacaoNeveNum2 = coeficienteCombinacaoNeveNum2;
        this.coeficienteCombinacaoNeveNum3 = coeficienteCombinacaoNeveNum3;
        this.coeficienteCombinacaoVento = coeficienteCombinacaoVento;
        this.coeficienteCombinacaoNeveVento1 = coeficienteCombinacaoNeveVento1;
        this.coeficienteCombinacaoNeveVento2 = coeficienteCombinacaoNeveVento2;
        this.coeficienteCombinacaoNeveVento3 = coeficienteCombinacaoNeveVento3;
    }*/

    public EstruturaDTO(Integer id, Boolean aceite, String observacoes, ProjetoDTO projeto, Set<FamiliaDTO> familias, AplicacaoDTO aplicacao, SobrecargaCategoriaDTO sobrecargaCategoria, String material, int numVaos, int comprimentoVao, int espacamentoEntreVigas, double angulo, double cargaPermanente, double sobrecarga, double neve, boolean altitudeMaior1000, double pressaoVento, double succaoVento, boolean contraventamentoTotal, int contraventamentoLateral, boolean contribuicaoChapaRevestimento, int numFixacoes, String inerciaChapaRevestimento, CombinacaoAcoesVerificacaoDeformacaoDTO combinacaoAcoesVerificacaoDeformacao, double limiteDeformacao, double coeficienteCombinacaoSobrecarga, double coeficienteCombinacaoSobrecargaNum1, double coeficienteCombinacaoSobrecargaNum2, double coeficienteCombinacaoSobrecargaNum3, double coeficienteCombinacaoNeve, double coeficienteCombinacaoNeveNum1, double coeficienteCombinacaoNeveNum2, double coeficienteCombinacaoNeveNum3, double coeficienteCombinacaoVento, double coeficienteCombinacaoNeveVento1, double coeficienteCombinacaoNeveVento2, double coeficienteCombinacaoNeveVento3) {
        this.id = id;
        this.aceite = aceite;
        this.observacoes = observacoes;
        this.projeto = projeto;
        this.familias = familias;
        this.aplicacao = aplicacao;
        this.sobrecargaCategoria = sobrecargaCategoria;
        this.material = material;
        this.numVaos = numVaos;
        this.comprimentoVao = comprimentoVao;
        this.espacamentoEntreVigas = espacamentoEntreVigas;
        this.angulo = angulo;
        this.cargaPermanente = cargaPermanente;
        this.sobrecarga = sobrecarga;
        this.neve = neve;
        this.altitudeMaior1000 = altitudeMaior1000;
        this.pressaoVento = pressaoVento;
        this.succaoVento = succaoVento;
        this.contraventamentoTotal = contraventamentoTotal;
        this.contraventamentoLateral = contraventamentoLateral;
        this.contribuicaoChapaRevestimento = contribuicaoChapaRevestimento;
        this.numFixacoes = numFixacoes;
        this.inerciaChapaRevestimento = inerciaChapaRevestimento;
        this.combinacaoAcoesVerificacaoDeformacao = combinacaoAcoesVerificacaoDeformacao;
        this.limiteDeformacao = limiteDeformacao;
        this.coeficienteCombinacaoSobrecarga = coeficienteCombinacaoSobrecarga;
        this.coeficienteCombinacaoSobrecargaNum1 = coeficienteCombinacaoSobrecargaNum1;
        this.coeficienteCombinacaoSobrecargaNum2 = coeficienteCombinacaoSobrecargaNum2;
        this.coeficienteCombinacaoSobrecargaNum3 = coeficienteCombinacaoSobrecargaNum3;
        this.coeficienteCombinacaoNeve = coeficienteCombinacaoNeve;
        this.coeficienteCombinacaoNeveNum1 = coeficienteCombinacaoNeveNum1;
        this.coeficienteCombinacaoNeveNum2 = coeficienteCombinacaoNeveNum2;
        this.coeficienteCombinacaoNeveNum3 = coeficienteCombinacaoNeveNum3;
        this.coeficienteCombinacaoVento = coeficienteCombinacaoVento;
        this.coeficienteCombinacaoNeveVento1 = coeficienteCombinacaoNeveVento1;
        this.coeficienteCombinacaoNeveVento2 = coeficienteCombinacaoNeveVento2;
        this.coeficienteCombinacaoNeveVento3 = coeficienteCombinacaoNeveVento3;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<FamiliaDTO> getFamilias() {
        return familias;
    }

    public void setFamilias(Set<FamiliaDTO> familias) {
        this.familias = familias;
    }

    public AplicacaoDTO getAplicacao() {
        return aplicacao;
    }

    public void setAplicacao(AplicacaoDTO aplicacao) {
        this.aplicacao = aplicacao;
    }

    public SobrecargaCategoriaDTO getSobrecargaCategoria() {
        return sobrecargaCategoria;
    }

    public void setSobrecargaCategoria(SobrecargaCategoriaDTO sobrecargaCategoria) {
        this.sobrecargaCategoria = sobrecargaCategoria;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getNumVaos() {
        return numVaos;
    }

    public void setNumVaos(int numVaos) {
        this.numVaos = numVaos;
    }

    public int getComprimentoVao() {
        return comprimentoVao;
    }

    public void setComprimentoVao(int comprimentoVao) {
        this.comprimentoVao = comprimentoVao;
    }

    public int getEspacamentoEntreVigas() {
        return espacamentoEntreVigas;
    }

    public void setEspacamentoEntreVigas(int espacamentoEntreVigas) {
        this.espacamentoEntreVigas = espacamentoEntreVigas;
    }

    public double getAngulo() {
        return angulo;
    }

    public void setAngulo(double angulo) {
        this.angulo = angulo;
    }

    public double getCargaPermanente() {
        return cargaPermanente;
    }

    public void setCargaPermanente(double cargaPermanente) {
        this.cargaPermanente = cargaPermanente;
    }

    public double getSobrecarga() {
        return sobrecarga;
    }

    public void setSobrecarga(double sobrecarga) {
        this.sobrecarga = sobrecarga;
    }

    public double getNeve() {
        return neve;
    }

    public void setNeve(double neve) {
        this.neve = neve;
    }

    public boolean isAltitudeMaior1000() {
        return altitudeMaior1000;
    }

    public void setAltitudeMaior1000(boolean altitudeMaior1000) {
        this.altitudeMaior1000 = altitudeMaior1000;
    }

    public double getPressaoVento() {
        return pressaoVento;
    }

    public void setPressaoVento(double pressaoVento) {
        this.pressaoVento = pressaoVento;
    }

    public double getSuccaoVento() {
        return succaoVento;
    }

    public void setSuccaoVento(double succaoVento) {
        this.succaoVento = succaoVento;
    }

    public boolean isContraventamentoTotal() {
        return contraventamentoTotal;
    }

    public void setContraventamentoTotal(boolean contraventamentoTotal) {
        this.contraventamentoTotal = contraventamentoTotal;
    }

    public int getContraventamentoLateral() {
        return contraventamentoLateral;
    }

    public void setContraventamentoLateral(int contraventamentoLateral) {
        this.contraventamentoLateral = contraventamentoLateral;
    }

    public boolean isContribuicaoChapaRevestimento() {
        return contribuicaoChapaRevestimento;
    }

    public void setContribuicaoChapaRevestimento(boolean contribuicaoChapaRevestimento) {
        this.contribuicaoChapaRevestimento = contribuicaoChapaRevestimento;
    }

    public int getNumFixacoes() {
        return numFixacoes;
    }

    public void setNumFixacoes(int numFixacoes) {
        this.numFixacoes = numFixacoes;
    }

    public String getInerciaChapaRevestimento() {
        return inerciaChapaRevestimento;
    }

    public void setInerciaChapaRevestimento(String inerciaChapaRevestimento) {
        this.inerciaChapaRevestimento = inerciaChapaRevestimento;
    }

    public CombinacaoAcoesVerificacaoDeformacaoDTO getCombinacaoAcoesVerificacaoDeformacao() {
        return combinacaoAcoesVerificacaoDeformacao;
    }

    public void setCombinacaoAcoesVerificacaoDeformacao(CombinacaoAcoesVerificacaoDeformacaoDTO combinacaoAcoesVerificacaoDeformacao) {
        this.combinacaoAcoesVerificacaoDeformacao = combinacaoAcoesVerificacaoDeformacao;
    }

    public double getLimiteDeformacao() {
        return limiteDeformacao;
    }

    public void setLimiteDeformacao(double limiteDeformacao) {
        this.limiteDeformacao = limiteDeformacao;
    }

    public double getCoeficienteCombinacaoSobrecarga() {
        return coeficienteCombinacaoSobrecarga;
    }

    public void setCoeficienteCombinacaoSobrecarga(double coeficienteCombinacaoSobrecarga) {
        this.coeficienteCombinacaoSobrecarga = coeficienteCombinacaoSobrecarga;
    }

    public double getCoeficienteCombinacaoSobrecargaNum1() {
        return coeficienteCombinacaoSobrecargaNum1;
    }

    public void setCoeficienteCombinacaoSobrecargaNum1(double coeficienteCombinacaoSobrecargaNum1) {
        this.coeficienteCombinacaoSobrecargaNum1 = coeficienteCombinacaoSobrecargaNum1;
    }

    public double getCoeficienteCombinacaoSobrecargaNum2() {
        return coeficienteCombinacaoSobrecargaNum2;
    }

    public void setCoeficienteCombinacaoSobrecargaNum2(double coeficienteCombinacaoSobrecargaNum2) {
        this.coeficienteCombinacaoSobrecargaNum2 = coeficienteCombinacaoSobrecargaNum2;
    }

    public double getCoeficienteCombinacaoSobrecargaNum3() {
        return coeficienteCombinacaoSobrecargaNum3;
    }

    public void setCoeficienteCombinacaoSobrecargaNum3(double coeficienteCombinacaoSobrecargaNum3) {
        this.coeficienteCombinacaoSobrecargaNum3 = coeficienteCombinacaoSobrecargaNum3;
    }

    public double getCoeficienteCombinacaoNeve() {
        return coeficienteCombinacaoNeve;
    }

    public void setCoeficienteCombinacaoNeve(double coeficienteCombinacaoNeve) {
        this.coeficienteCombinacaoNeve = coeficienteCombinacaoNeve;
    }

    public double getCoeficienteCombinacaoNeveNum1() {
        return coeficienteCombinacaoNeveNum1;
    }

    public void setCoeficienteCombinacaoNeveNum1(double coeficienteCombinacaoNeveNum1) {
        this.coeficienteCombinacaoNeveNum1 = coeficienteCombinacaoNeveNum1;
    }

    public double getCoeficienteCombinacaoNeveNum2() {
        return coeficienteCombinacaoNeveNum2;
    }

    public void setCoeficienteCombinacaoNeveNum2(double coeficienteCombinacaoNeveNum2) {
        this.coeficienteCombinacaoNeveNum2 = coeficienteCombinacaoNeveNum2;
    }

    public double getCoeficienteCombinacaoNeveNum3() {
        return coeficienteCombinacaoNeveNum3;
    }

    public void setCoeficienteCombinacaoNeveNum3(double coeficienteCombinacaoNeveNum3) {
        this.coeficienteCombinacaoNeveNum3 = coeficienteCombinacaoNeveNum3;
    }

    public double getCoeficienteCombinacaoVento() {
        return coeficienteCombinacaoVento;
    }

    public void setCoeficienteCombinacaoVento(double coeficienteCombinacaoVento) {
        this.coeficienteCombinacaoVento = coeficienteCombinacaoVento;
    }

    public double getCoeficienteCombinacaoNeveVento1() {
        return coeficienteCombinacaoNeveVento1;
    }

    public void setCoeficienteCombinacaoNeveVento1(double coeficienteCombinacaoNeveVento1) {
        this.coeficienteCombinacaoNeveVento1 = coeficienteCombinacaoNeveVento1;
    }

    public double getCoeficienteCombinacaoNeveVento2() {
        return coeficienteCombinacaoNeveVento2;
    }

    public void setCoeficienteCombinacaoNeveVento2(double coeficienteCombinacaoNeveVento2) {
        this.coeficienteCombinacaoNeveVento2 = coeficienteCombinacaoNeveVento2;
    }

    public double getCoeficienteCombinacaoNeveVento3() {
        return coeficienteCombinacaoNeveVento3;
    }

    public void setCoeficienteCombinacaoNeveVento3(double coeficienteCombinacaoNeveVento3) {
        this.coeficienteCombinacaoNeveVento3 = coeficienteCombinacaoNeveVento3;
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

    public ProjetoDTO getProjeto() {
        return projeto;
    }

    public void setProjeto(ProjetoDTO projeto) {
        this.projeto = projeto;
    }
}
