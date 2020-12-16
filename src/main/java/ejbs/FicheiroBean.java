package ejbs;


import entities.Familia;
import entities.Ficheiro;
import entities.Projeto;
import exceptions.MyConstraintViolationException;
import exceptions.MyEntityAlreadyExistsException;
import exceptions.MyEntityNotFoundException;
import exceptions.Utils;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.ConstraintViolationException;
import java.util.List;

@Stateless
public class FicheiroBean extends BaseBean<Ficheiro, Integer> {
    @EJB
    private ProjetoBean projetoBean;
    public Ficheiro create(Integer idProjeto, String filepath, String filename) throws Exception {
        Projeto projeto = projetoBean.find(idProjeto);
        if (projeto == null) {
            throw new MyEntityNotFoundException("O projeto não foi encontrado!");
        }
        try {
            Ficheiro ficheiro = new Ficheiro(filepath,filename,projeto);
            //qnd se adiciona um ficheiro, o ficheiro deve ser adicionado à lista do projeto
            projeto.addFicheiros(ficheiro);
            em.persist(ficheiro);
            return ficheiro;
        } catch (ConstraintViolationException e){
            throw new MyConstraintViolationException(Utils.getConstraintViolationMessages(e));
        }
    }

    public List<Ficheiro> getFicheirosProjeto(Integer id) throws MyEntityNotFoundException {
        try{
            Projeto projeto = em.find(Projeto.class, id);
            return projeto.getFicheiros();
        } catch (Exception e) {
            throw new MyEntityNotFoundException("Erro a encontrar os ficheiros do projeto", e);
        }
    }
}
