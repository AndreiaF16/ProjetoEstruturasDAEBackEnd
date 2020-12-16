package ejbs;

import entities.CombinacaoAcoesVerificacaoDeformacao;
import entities.SobreCargaCategoria;
import exceptions.MyConstraintViolationException;
import exceptions.MyEntityAlreadyExistsException;
import exceptions.Utils;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.LockModeType;
import javax.validation.ConstraintViolationException;

@Stateless
public class SobrecargaCategoriaBean extends BaseBean<SobreCargaCategoria, Character>{
    public SobreCargaCategoria create(String name, Character code) throws MyEntityAlreadyExistsException, MyConstraintViolationException {
        if (find(code) != null) {
            throw new MyEntityAlreadyExistsException("O Tipo de Categoria da Sobrecarga já existe!");
        }
        try {
            SobreCargaCategoria sobreCargaCategoria = new SobreCargaCategoria(code, name);
            em.persist(sobreCargaCategoria);
            return sobreCargaCategoria;
        } catch (ConstraintViolationException e){
            throw new MyConstraintViolationException(Utils.getConstraintViolationMessages(e));
        }
    }

    public SobreCargaCategoria update(Character code, String newName, Character newCode){
        try {
            SobreCargaCategoria sobreCargaCategoria = findOrFail(code);
            if (code != newCode){
                SobreCargaCategoria sobreCargaCategoriaNew = find(newCode);
                if (sobreCargaCategoriaNew != null){
                    throw new MyEntityAlreadyExistsException("Este nome de categoria já existe!");
                }
            }
            em.lock(sobreCargaCategoria, LockModeType.OPTIMISTIC);
            sobreCargaCategoria.setName(newName);
            if (code != newCode){
                sobreCargaCategoria.setCode(newCode);
            }
            em.persist(sobreCargaCategoria);
            return sobreCargaCategoria;
        } catch (Exception e) {
            throw new EJBException("ERROR_UPDATING_TYPE", e);
        }
    }
}
