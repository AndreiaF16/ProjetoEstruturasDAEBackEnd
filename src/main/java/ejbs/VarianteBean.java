package ejbs;

import entities.Estrutura;
import entities.Produto;
import entities.Variante;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class VarianteBean extends BaseBean<Variante, Integer>{

    @PersistenceContext
    EntityManager em;

    public void create(int codigo, String nomeProduto, String name, double weff_p, double weff_n, double ar, double sigmaC){
        Produto produto = em.find(Produto.class, nomeProduto);
        Variante p = new Variante(codigo, produto, name, weff_p, weff_n, ar, sigmaC);
        em.persist(p);
    }

    public Variante getVariante(int codigo){
        return em.find(Variante.class, codigo);
    }

    public List<Variante> all(){
        try {
            return (List<Variante>) em.createNamedQuery("getAllVariantes").getResultList();
        } catch (Exception e) {
            throw new EJBException("ERRO_RETORNAR_VARIANTES", e);
        }
    }
}
