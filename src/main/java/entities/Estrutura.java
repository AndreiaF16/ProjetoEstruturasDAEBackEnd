package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;



@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllStructures",
                query = "SELECT e FROM Estrutura e ORDER BY e.aplicacao.name" // JPQL
        )
})
public class Estrutura implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Boolean aceite;

    private String observacoes;

    @ManyToOne
    private Projeto projeto;

    @OneToMany
    private Set<Familia> familias;

    @ManyToOne
    private Aplicacao aplicacao;

    @OneToOne
    private SobreCargaCategoria sobreCargaCategoria;

    private String material;

    //First Parameter without application
    private int numVaos;
    private double comprimentoVao;
    private int espacamentoEntreVigas;

    //Parammeter common
    private double angulo;
    private double cargaPermanente;
    private double sobrecarga;
    private double neve;
    private boolean altitudeMaior1000;
    private double pressaoVento;
    private double succaoVento;

    //Parametros Adicionais
    private boolean contraventamentoTotal;
    private int contraventamentoLateral; //1-4 apenas
    private boolean contribuicaoChapaRevestimento;
    private int numFixacoes;
    private String inerciaChapaRevestimento;
    @OneToOne
    private CombinacaoAcoesVerificacaoDeformacao combinacaoAcoesVerificacaoDeformacao;
    private double limiteDeformacao;

    //Mais parâmetros Adicionais (os que estão locked)
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
    @Version
    private int version;

    public Estrutura() {
        this.familias = new LinkedHashSet<>();
    }

    public Estrutura(Projeto projeto,Set<Familia> familias, Aplicacao aplicacao, SobreCargaCategoria sobreCargaCategoria, String material, int numVaos, double comprimentoVao, int espacamentoEntreVigas, double angulo, double cargaPermanente, double sobrecarga, double neve, boolean altitudeMaior1000, double pressaoVento, double succaoVento, boolean contraventamentoTotal, int contraventamentoLateral, boolean contribuicaoChapaRevestimento, int numFixacoes, String inerciaChapaRevestimento, CombinacaoAcoesVerificacaoDeformacao combinacaoAcoesVerificacaoDeformacao, double limiteDeformacao, double coeficienteCombinacaoSobrecarga, double coeficienteCombinacaoSobrecargaNum1, double coeficienteCombinacaoSobrecargaNum2, double coeficienteCombinacaoSobrecargaNum3, double coeficienteCombinacaoNeve, double coeficienteCombinacaoNeveNum1, double coeficienteCombinacaoNeveNum2, double coeficienteCombinacaoNeveNum3, double coeficienteCombinacaoVento, double coeficienteCombinacaoNeveVento1, double coeficienteCombinacaoNeveVento2, double coeficienteCombinacaoNeveVento3) {
        this.projeto = projeto;
        this.familias = familias;
        this.aplicacao = aplicacao;
        this.sobreCargaCategoria = sobreCargaCategoria;
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
        this.aceite = null;
        this.observacoes = null;
    }

    public Estrutura(int id, Boolean aceite, String observacoes, Projeto projeto, Set<Familia> familias, Aplicacao aplicacao, SobreCargaCategoria sobreCargaCategoria, String material, int numVaos, double comprimentoVao, int espacamentoEntreVigas, double angulo, double cargaPermanente, double sobrecarga, double neve, boolean altitudeMaior1000, double pressaoVento, double succaoVento, boolean contraventamentoTotal, int contraventamentoLateral, boolean contribuicaoChapaRevestimento, int numFixacoes, String inerciaChapaRevestimento, CombinacaoAcoesVerificacaoDeformacao combinacaoAcoesVerificacaoDeformacao, double limiteDeformacao, double coeficienteCombinacaoSobrecarga, double coeficienteCombinacaoSobrecargaNum1, double coeficienteCombinacaoSobrecargaNum2, double coeficienteCombinacaoSobrecargaNum3, double coeficienteCombinacaoNeve, double coeficienteCombinacaoNeveNum1, double coeficienteCombinacaoNeveNum2, double coeficienteCombinacaoNeveNum3, double coeficienteCombinacaoVento, double coeficienteCombinacaoNeveVento1, double coeficienteCombinacaoNeveVento2, double coeficienteCombinacaoNeveVento3) {
        this.id = id;
        this.aceite = aceite;
        this.observacoes = observacoes;
        this.projeto = projeto;
        this.familias = familias;
        this.aplicacao = aplicacao;
        this.sobreCargaCategoria = sobreCargaCategoria;
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

    public SobreCargaCategoria getSobreCargaCategoria() {
        return sobreCargaCategoria;
    }

    public void setSobreCargaCategoria(SobreCargaCategoria sobreCargaCategoria) {
        this.sobreCargaCategoria = sobreCargaCategoria;
    }

    public Set<Familia> getFamilias() {
        return familias;
    }

    public void setFamilias(Set<Familia> familias) {
        this.familias = familias;
    }

    public Aplicacao getAplicacao() {
        return aplicacao;
    }

    public void setAplicacao(Aplicacao aplicacao) {
        this.aplicacao = aplicacao;
    }

    public int getNumVaos() {
        return numVaos;
    }

    public void setNumVaos(int numVaos) {
        this.numVaos = numVaos;
    }

    public double getComprimentoVao() {
        return comprimentoVao;
    }

    public void setComprimentoVao(double comprimentoVao) {
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

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
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

    public CombinacaoAcoesVerificacaoDeformacao getCombinacaoAcoesVerificacaoDeformacao() {
        return combinacaoAcoesVerificacaoDeformacao;
    }

    public void setCombinacaoAcoesVerificacaoDeformacao(CombinacaoAcoesVerificacaoDeformacao combinacaoAcoesVerificacaoDeformacao) {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }
}
