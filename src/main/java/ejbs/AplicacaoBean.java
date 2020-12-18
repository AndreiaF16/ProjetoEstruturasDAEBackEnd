package ejbs;

import entities.Aplicacao;
import entities.CombinacaoAcoesVerificacaoDeformacao;
import exceptions.MyConstraintViolationException;
import exceptions.MyEntityAlreadyExistsException;
import exceptions.MyEntityNotFoundException;
import exceptions.Utils;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.LockModeType;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@Stateless
public class AplicacaoBean extends BaseBean<Aplicacao, String>{

    public Aplicacao create(String name) throws MyEntityAlreadyExistsException, MyConstraintViolationException {
        if (find(name) != null) {
            throw new MyEntityAlreadyExistsException("O Tipo de Aplicação já existe!");
        }
        try {
            Aplicacao aplicacao = new Aplicacao(name);
            em.persist(aplicacao);
            return aplicacao;
        } catch (ConstraintViolationException e){
            throw new MyConstraintViolationException(Utils.getConstraintViolationMessages(e));
        }
    }

    public Aplicacao update(String name, String newName) throws MyEntityAlreadyExistsException, MyConstraintViolationException {
        try {
            Aplicacao aplicacao = findOrFail(name);
            Aplicacao aplicacaoNew = find(newName);
            if (aplicacaoNew != null){
                throw new MyEntityAlreadyExistsException("Este nome de combinação já existe!");
            }
            em.lock(aplicacao, LockModeType.OPTIMISTIC);
            aplicacao.setName(newName);
            em.persist(aplicacao);
            return aplicacao;
        } catch (Exception e) {
            throw new EJBException("ERROR_UPDATING_TYPE", e);
        }
    }
}
