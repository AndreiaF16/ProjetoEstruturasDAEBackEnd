package ejbs;

import entities.Cliente;
import entities.Projeto;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;

@Stateless
public class ClienteBean {
    @PersistenceContext
    private EntityManager em;

    @EJB
    private ProjetoBean projetoBean;

    public Cliente create(String username, String password,String email, String nome,long contacto, String morada) throws Exception {
        /*if (find(id)!=null){
            throw new Exception("Id '" + id + "' already exists");
        }*/

        Cliente cliente = new Cliente(username, password, email, nome, contacto, morada);
        em.persist(cliente);
        return cliente;
    }

    public List<Cliente> all() {
        try {
            return (List<Cliente>) em.createNamedQuery("getAllClients").getResultList();
        } catch (Exception e) {
            throw new EJBException("ERRO_RETORNAR_CLIENTES", e);
        }
    }

    public Cliente find(Integer id) throws Exception {
        try{
            return em.find(Cliente.class, id);
        } catch (Exception e) {
            throw new Exception("ERRO_ENCONTRAR_CLIENTE");
        }
    }

    public List<Projeto> getProjetosCliente(Integer id) throws Exception {
        try{
            Cliente cliente = find(id);
            List<Projeto> projetos = new LinkedList<>();
            for (Projeto projeto : projetoBean.all()) {
                if(projeto.getCliente().getId() == cliente.getId() && projeto.getDisponivel() == true){
                    projetos.add(projeto);
                }
            }
            return projetos;
        } catch (Exception e) {
            throw new Exception("ERRO_ENCONTRAR_CLIENTE");
        }
    }
}
