package ejbs;

import entities.Cliente;
import entities.Projetista;
import entities.Projeto;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.LinkedList;
import java.util.List;

@Stateless
public class ProjetistaBean {
    @PersistenceContext
    private EntityManager em;

    @EJB
    private ProjetoBean projetoBean;

    public Projetista create(String username, String password, String email, String nome) throws Exception {
        Projetista projetista = new Projetista(username, password, email, nome);
        em.persist(projetista);
        return projetista;
    }

    public List<Projetista> all() {
        try {
            return (List<Projetista>) em.createNamedQuery("getAllProjetistas").getResultList();
        } catch (Exception e) {
            throw new EJBException("ERRO_RETORNAR_PROJETISTAS", e);
        }
    }

    public Projetista find(Integer id) throws Exception {
        try{
            return em.find(Projetista.class, id);
        } catch (Exception e) {
            throw new Exception("ERRO_ENCONTRAR_CLIENTE");
        }
    }

    public List<Projeto> getProjetosProjetistas(Integer id) throws Exception {
        try{
            Projetista projetista = find(id);
            List<Projeto> projetos = new LinkedList<>();
            for (Projeto projeto : projetoBean.all()) {
                for (Projetista projetoProjetista : projeto.getProjetistas()) {
                    if(projetoProjetista.getId() == projetista.getId()){
                        projetos.add(projeto);
                    }
                }
            }
            return projetos;
        } catch (Exception e) {
            throw new Exception("ERRO_ENCONTRAR_CLIENTE");
        }
    }
}
