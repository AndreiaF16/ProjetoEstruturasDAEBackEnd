package ejbs;

import entities.CombinacaoAcoesVerificacaoDeformacao;
import entities.Familia;
import exceptions.MyConstraintViolationException;
import exceptions.MyEntityAlreadyExistsException;
import exceptions.Utils;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.LockModeType;
import javax.validation.ConstraintViolationException;

@Stateless
public class FamiliaBean extends BaseBean<Familia, String>{
    public Familia create(String name) throws MyEntityAlreadyExistsException, MyConstraintViolationException {
        if (find(name) != null) {
            throw new MyEntityAlreadyExistsException("O Tipo de Família já existe!");
        }
        try {
            Familia familia = new Familia(name);
            em.persist(familia);
            return familia;
        } catch (ConstraintViolationException e){
            throw new MyConstraintViolationException(Utils.getConstraintViolationMessages(e));
        }
    }

    public Familia update(String name, String newName) throws MyEntityAlreadyExistsException, MyConstraintViolationException {
        try {
            Familia familia = findOrFail(name);
            Familia familiaNew = find(newName);
            if (familiaNew != null){
                throw new MyEntityAlreadyExistsException("Este nome de combinação já existe!");
            }
            em.lock(familia, LockModeType.OPTIMISTIC);
            familia.setName(newName);
            em.persist(familia);
            return familia;
        } catch (Exception e) {
            throw new EJBException("ERROR_UPDATING_TYPE", e);
        }
    }


}
