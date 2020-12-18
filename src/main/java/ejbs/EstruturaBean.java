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

    public EstruturaBean() {
    }

    public List<Estrutura> all(){
        try {
            return (List<Estrutura>) em.createNamedQuery("getAllStructures").getResultList();
        } catch (Exception e) {
            throw new EJBException("ERRO_RETORNAR_ESTRUTURAS", e);
        }
    }

    public Estrutura create(String[] familiasParameter, String aplicacao, Character sobreCargaCategoria, String material, int numVaos, int comprimentoVao, int espacamentoEntreVigas, double angulo, double cargaPermanente, double sobrecarga, double neve, boolean altitudeMaior1000, double pressaoVento, double succaoVento, boolean contraventamentoTotal, int contraventamentoLateral, boolean contribuicaoChapaRevestimento, int numFixacoes, String inerciaChapaRevestimento, String combinacaoAcoesVerificacaoDeformacao, double limiteDeformacao, double coeficienteCombinacaoSobrecarga, double coeficienteCombinacaoSobrecargaNum1, double coeficienteCombinacaoSobrecargaNum2, double coeficienteCombinacaoSobrecargaNum3, double coeficienteCombinacaoNeve, double coeficienteCombinacaoNeveNum1, double coeficienteCombinacaoNeveNum2, double coeficienteCombinacaoNeveNum3, double coeficienteCombinacaoVento, double coeficienteCombinacaoNeveVento1, double coeficienteCombinacaoNeveVento2, double coeficienteCombinacaoNeveVento3) throws MyEntityNotFoundException, MyConstraintViolationException {
        Set<Familia> familias = new LinkedHashSet<>();
        Familia familia;
        for(String f : familiasParameter){
            familia = familiaBean.findOrFail(f);
            familias.add(familia);
        }
        Aplicacao app = aplicacaoBean.findOrFail(aplicacao);
        SobreCargaCategoria sobreCargaCat = sobrecargaCategoriaBean.findOrFail(sobreCargaCategoria);
        CombinacaoAcoesVerificacaoDeformacao combinacaoAcoesVerificacaoDef = combinacaoAcoesVerificacaoDeformacaoBean.findOrFail(combinacaoAcoesVerificacaoDeformacao);
        try {
            Estrutura estrutura = new Estrutura(familias, app, sobreCargaCat, material, numVaos, comprimentoVao, espacamentoEntreVigas, angulo, cargaPermanente, sobrecarga, neve, altitudeMaior1000, pressaoVento, succaoVento, contraventamentoTotal, contraventamentoLateral, contribuicaoChapaRevestimento, numFixacoes, inerciaChapaRevestimento, combinacaoAcoesVerificacaoDef, limiteDeformacao, coeficienteCombinacaoSobrecarga, coeficienteCombinacaoSobrecargaNum1, coeficienteCombinacaoSobrecargaNum2, coeficienteCombinacaoSobrecargaNum3, coeficienteCombinacaoNeve, coeficienteCombinacaoNeveNum1, coeficienteCombinacaoNeveNum2, coeficienteCombinacaoNeveNum3, coeficienteCombinacaoVento, coeficienteCombinacaoNeveVento1, coeficienteCombinacaoNeveVento2, coeficienteCombinacaoNeveVento3);
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
}
