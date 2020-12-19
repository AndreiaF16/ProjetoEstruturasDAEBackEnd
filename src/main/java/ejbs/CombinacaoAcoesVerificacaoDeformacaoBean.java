package ejbs;

import entities.CombinacaoAcoesVerificacaoDeformacao;
import entities.SobreCargaCategoria;
import exceptions.MyConstraintViolationException;
import exceptions.MyEntityAlreadyExistsException;
import exceptions.MyEntityNotFoundException;
import exceptions.Utils;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.LockModeType;
import javax.validation.ConstraintViolationException;
import java.util.List;

@Stateless
public class CombinacaoAcoesVerificacaoDeformacaoBean extends BaseBean<CombinacaoAcoesVerificacaoDeformacao, String>{

    public CombinacaoAcoesVerificacaoDeformacao create(String name) throws MyEntityAlreadyExistsException, MyConstraintViolationException {
        if (find(name) != null) {
            throw new MyEntityAlreadyExistsException("O Tipo de Combinação de Ações de Verificacao de Deformação já existe!");
        }
        try {
            CombinacaoAcoesVerificacaoDeformacao combinacaoAcoesVerificacaoDeformacao = new CombinacaoAcoesVerificacaoDeformacao(name);
            em.persist(combinacaoAcoesVerificacaoDeformacao);
            return combinacaoAcoesVerificacaoDeformacao;
        } catch (ConstraintViolationException e){
            throw new MyConstraintViolationException(Utils.getConstraintViolationMessages(e));
        }
    }

    public CombinacaoAcoesVerificacaoDeformacao update(String name, String newName) throws MyEntityAlreadyExistsException, MyConstraintViolationException {
        try {
            CombinacaoAcoesVerificacaoDeformacao combinacaoAcoesVerificacaoDeformacao = findOrFail(name);
            CombinacaoAcoesVerificacaoDeformacao combinacaoAcoesVerificacaoDeformacaoNew = find(newName);
            if (combinacaoAcoesVerificacaoDeformacaoNew != null){
                throw new MyEntityAlreadyExistsException("Este nome de combinação já existe!");
            }
            em.lock(combinacaoAcoesVerificacaoDeformacao, LockModeType.OPTIMISTIC);
            combinacaoAcoesVerificacaoDeformacao.setName(newName);
            em.persist(combinacaoAcoesVerificacaoDeformacao);
            return combinacaoAcoesVerificacaoDeformacao;
        } catch (Exception e) {
            throw new EJBException("ERROR_UPDATING_TYPE", e);
        }
    }

    public List<CombinacaoAcoesVerificacaoDeformacao> all(){
        try {
            return (List<CombinacaoAcoesVerificacaoDeformacao>) em.createNamedQuery("CombinacaoAcoesVerificacaoDeformacao").getResultList();
        } catch (Exception e) {
            throw new EJBException("ERRO_RETORNAR_CLIENTES", e);
        }
    }
}
