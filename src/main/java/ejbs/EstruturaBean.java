package ejbs;

import entities.*;
import exceptions.MyConstraintViolationException;
import exceptions.MyEntityNotFoundException;
import exceptions.Utils;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.LockModeType;
import javax.validation.ConstraintViolationException;
import java.lang.reflect.Array;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Stateless
public class EstruturaBean extends BaseBean<Estrutura, Integer>{

    @EJB
    private FamiliaBean familiaBean;

    @EJB
    private AplicacaoBean aplicacaoBean;

    @EJB
    private SobrecargaCategoriaBean sobrecargaCategoriaBean;

    @EJB
    private CombinacaoAcoesVerificacaoDeformacaoBean combinacaoAcoesVerificacaoDeformacaoBean;

    @EJB
    private SimulacaoBean simulacaoBean;

    @EJB
    private ProjetoBean projetoBean;

    public EstruturaBean() {
    }

    public List<Estrutura> all(){
        try {
            return (List<Estrutura>) em.createNamedQuery("getAllStructures").getResultList();
        } catch (Exception e) {
            throw new EJBException("ERRO_RETORNAR_ESTRUTURAS", e);
        }
    }

    //TODO Fazer o search mas que atributos utilizar?
    /*public Estrutura search(){

    }*/


    public Estrutura create(int projeto_id, Set<Familia> familiasParameter, String aplicacao, Character sobreCargaCategoria, String material, int numVaos, int comprimentoVao, int espacamentoEntreVigas, double angulo, double cargaPermanente, double sobrecarga, double neve, boolean altitudeMaior1000, double pressaoVento, double succaoVento, boolean contraventamentoTotal, int contraventamentoLateral, boolean contribuicaoChapaRevestimento, int numFixacoes, String inerciaChapaRevestimento, String combinacaoAcoesVerificacaoDeformacao, double limiteDeformacao, double coeficienteCombinacaoSobrecarga, double coeficienteCombinacaoSobrecargaNum1, double coeficienteCombinacaoSobrecargaNum2, double coeficienteCombinacaoSobrecargaNum3, double coeficienteCombinacaoNeve, double coeficienteCombinacaoNeveNum1, double coeficienteCombinacaoNeveNum2, double coeficienteCombinacaoNeveNum3, double coeficienteCombinacaoVento, double coeficienteCombinacaoNeveVento1, double coeficienteCombinacaoNeveVento2, double coeficienteCombinacaoNeveVento3) throws Exception {
        Projeto projeto = projetoBean.find(projeto_id);
        Aplicacao app = aplicacaoBean.findOrFail(aplicacao);
        SobreCargaCategoria sobreCargaCat = sobrecargaCategoriaBean.findOrFail(sobreCargaCategoria);
        CombinacaoAcoesVerificacaoDeformacao combinacaoAcoesVerificacaoDef = combinacaoAcoesVerificacaoDeformacaoBean.findOrFail(combinacaoAcoesVerificacaoDeformacao);
        try {
            Estrutura estrutura = new Estrutura(projeto, familiasParameter, app, sobreCargaCat, material, numVaos, comprimentoVao, espacamentoEntreVigas, angulo, cargaPermanente, sobrecarga, neve, altitudeMaior1000, pressaoVento, succaoVento, contraventamentoTotal, contraventamentoLateral, contribuicaoChapaRevestimento, numFixacoes, inerciaChapaRevestimento, combinacaoAcoesVerificacaoDef, limiteDeformacao, coeficienteCombinacaoSobrecarga, coeficienteCombinacaoSobrecargaNum1, coeficienteCombinacaoSobrecargaNum2, coeficienteCombinacaoSobrecargaNum3, coeficienteCombinacaoNeve, coeficienteCombinacaoNeveNum1, coeficienteCombinacaoNeveNum2, coeficienteCombinacaoNeveNum3, coeficienteCombinacaoVento, coeficienteCombinacaoNeveVento1, coeficienteCombinacaoNeveVento2, coeficienteCombinacaoNeveVento3);
            em.persist(estrutura);
            return estrutura;
        } catch (ConstraintViolationException e){
            throw new MyConstraintViolationException(Utils.getConstraintViolationMessages(e));
        }
    }

    public String delete(int id) throws MyEntityNotFoundException, MyConstraintViolationException {
        try {
            Estrutura estrutura = findOrFail(id);
            em.lock(estrutura, LockModeType.OPTIMISTIC);
            estrutura.setFamilias(null);
            em.remove(estrutura);
            return "Estrutura " + id + " foi removida com sucesso!";
        }catch (ConstraintViolationException exception){
            throw new MyConstraintViolationException(Utils.getConstraintViolationMessages(exception));
        }
    }

    public Estrutura aceitarEstrutura(Integer id, Boolean aceite, String observacoes) throws Exception {
        Estrutura estrutura = find(id);

        try{
            //em.lock(estrutura, LockModeType.OPTIMISTIC);
            estrutura.setAceite(aceite);
            estrutura.setObservacoes(observacoes);
            em.merge(estrutura);
            return estrutura;
        }catch (Exception e){
            throw new Exception("Erro ao aceitar estrutura");
        }
    }

    public Estrutura update(int id, Set<Familia> familias, String aplicacao, Character code, String material, int numVaos, int comprimentoVao, int espacamentoEntreVigas, double angulo, double cargaPermanente, double sobrecarga, double neve, boolean altitudeMaior1000, double pressaoVento, double succaoVento, boolean contraventamentoTotal, int contraventamentoLateral, boolean contribuicaoChapaRevestimento, int numFixacoes, String inerciaChapaRevestimento, String combinacaoAcoesVerificacaoDeformacao, double limiteDeformacao, double coeficienteCombinacaoSobrecarga, double coeficienteCombinacaoSobrecargaNum1, double coeficienteCombinacaoSobrecargaNum2, double coeficienteCombinacaoSobrecargaNum3, double coeficienteCombinacaoNeve, double coeficienteCombinacaoNeveNum1, double coeficienteCombinacaoNeveNum2, double coeficienteCombinacaoNeveNum3, double coeficienteCombinacaoVento, double coeficienteCombinacaoNeveVento1, double coeficienteCombinacaoNeveVento2, double coeficienteCombinacaoNeveVento3) throws MyEntityNotFoundException, MyConstraintViolationException {
        try {
            Estrutura estrutura = findOrFail(id);
            CombinacaoAcoesVerificacaoDeformacao combinacaoAcoesVerificacaoDeformacao1 = combinacaoAcoesVerificacaoDeformacaoBean.findOrFail(combinacaoAcoesVerificacaoDeformacao);
            SobreCargaCategoria sobreCargaCategoria = sobrecargaCategoriaBean.findOrFail(code);
            if(familias != estrutura.getFamilias()){
                estrutura.setFamilias(familias);
            }
            if (aplicacao != estrutura.getAplicacao().getName()){
                Aplicacao aplicacaoEntity = aplicacaoBean.findOrFail(aplicacao);
                estrutura.setAplicacao(aplicacaoEntity);
            }
            if (estrutura.getSobreCargaCategoria() != sobreCargaCategoria){
                estrutura.setSobreCargaCategoria(sobreCargaCategoria);
            }
            if (material != estrutura.getMaterial()){
                estrutura.setMaterial(material);
            }
            if (estrutura.getNumVaos() != numVaos){
                estrutura.setNumVaos(numVaos);
            }
            estrutura.setComprimentoVao(comprimentoVao);
            estrutura.setEspacamentoEntreVigas(espacamentoEntreVigas);
            estrutura.setAngulo(angulo);
            estrutura.setCargaPermanente(cargaPermanente);
            estrutura.setSobrecarga(sobrecarga);
            estrutura.setNeve(neve);
            estrutura.setAltitudeMaior1000(altitudeMaior1000);
            estrutura.setPressaoVento(pressaoVento);
            estrutura.setSuccaoVento(succaoVento);
            estrutura.setContraventamentoTotal(contraventamentoTotal);
            estrutura.setContraventamentoLateral(contraventamentoLateral);
            estrutura.setContribuicaoChapaRevestimento(contribuicaoChapaRevestimento);
            estrutura.setNumFixacoes(numFixacoes);
            estrutura.setInerciaChapaRevestimento(inerciaChapaRevestimento);
            estrutura.setCombinacaoAcoesVerificacaoDeformacao(combinacaoAcoesVerificacaoDeformacao1);
            estrutura.setLimiteDeformacao(limiteDeformacao);
            estrutura.setCoeficienteCombinacaoSobrecarga(sobrecarga);
            estrutura.setCoeficienteCombinacaoSobrecargaNum1(coeficienteCombinacaoSobrecargaNum1);
            estrutura.setCoeficienteCombinacaoSobrecargaNum2(coeficienteCombinacaoSobrecargaNum2);
            estrutura.setCoeficienteCombinacaoSobrecargaNum3(coeficienteCombinacaoSobrecargaNum3);
            estrutura.setCoeficienteCombinacaoNeve(coeficienteCombinacaoNeve);
            estrutura.setCoeficienteCombinacaoNeveNum1(coeficienteCombinacaoNeveNum1);
            estrutura.setCoeficienteCombinacaoNeveNum2(coeficienteCombinacaoNeveNum2);
            estrutura.setCoeficienteCombinacaoNeveNum3(coeficienteCombinacaoNeveNum3);
            estrutura.setCoeficienteCombinacaoVento(coeficienteCombinacaoVento);
            estrutura.setCoeficienteCombinacaoNeveVento1(coeficienteCombinacaoNeveVento1);
            estrutura.setCoeficienteCombinacaoNeveVento2(coeficienteCombinacaoNeveVento2);
            estrutura.setCoeficienteCombinacaoNeveVento3(coeficienteCombinacaoNeveVento3);

            em.persist(estrutura);
            return estrutura;
        } catch (ConstraintViolationException e){
            throw new MyConstraintViolationException(Utils.getConstraintViolationMessages(e));
        }
    }

    public void realizarSimulacao(Estrutura estrutura){
        //TODO como fazer esta simulação?
        // Temos de criar uma nova variante com alguns dados que estão na estrutura ou não está nada relacionado e a variante é algo completamente independete
        // Variante variante = new Variante()
        // simulacaoBean.simulaVariante()
    }
}
