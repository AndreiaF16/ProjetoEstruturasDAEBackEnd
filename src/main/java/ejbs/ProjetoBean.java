package ejbs;

import entities.*;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import java.util.List;

//TODO: falta o filtrar

@Stateless
public class ProjetoBean {

    @PersistenceContext
    private EntityManager em;

    @EJB
    private ClienteBean clienteBean;

    @EJB
    private FicheiroBean ficheiroBean;

    @EJB
    private EstruturaBean estruturaBean;

    @EJB
    private ProjetistaBean projetistaBean;

    public ProjetoBean() {
    }

    public Projeto create(String nomeProjeto, Integer clienteId, Boolean disponivel) throws Exception {
        Cliente cliente = clienteBean.find(clienteId);
        if (cliente == null) {
            throw new Exception("Id '" + clienteId + "' does not exists");
        }

        Projeto projeto = new Projeto(nomeProjeto, cliente, disponivel);
        em.persist(projeto);
        return projeto;
    }

    public Projeto createV2(String nomeProjeto, Integer clienteId, Boolean disponivel, List<Integer> projetistas) throws Exception {
        Cliente cliente = clienteBean.find(clienteId);
        if (cliente == null) {
            throw new Exception("Id '" + clienteId + "' does not exists");
        }

        Projeto projeto = new Projeto(nomeProjeto, cliente, disponivel);
        for (Integer IdProjetista : projetistas) {
            Projetista projetista = projetistaBean.find(IdProjetista);
            projeto.addProjetista(projetista);
            projetista.addProjeto(projeto);
        }
        em.persist(projeto);
        return projeto;
    }

    public List<Projeto> all() {
        try {
            return (List<Projeto>) em.createNamedQuery("getAllProjects").getResultList();
        } catch (Exception e) {
            throw new EJBException("ERRO_RETORNAR_CLIENTES", e);
        }
    }

    public Projeto find(Integer id) throws Exception {
        try {
            return em.find(Projeto.class, id);
        } catch (Exception e) {
            throw new Exception("ERRO_ENCONTRAR_PROJETO");
        }
    }

    public List<Estrutura> getAllEstruturasProjeto(Integer projetoId) throws Exception {
        try {
            return em.find(Projeto.class, projetoId).getEstruturas();
        } catch (Exception e) {
            throw new Exception("Erro ao encontrar as Estruturas do projeto", e);
        }
    }

    public List<Ficheiro> getAllFicheirosProjeto(Integer projetoId) throws Exception {
        try {
            return em.find(Projeto.class, projetoId).getFicheiros();
        } catch (Exception e) {
            throw new Exception("Erro ao encontrar os Ficheiros do projeto", e);
        }
    }

    public Projeto update(Integer id, String nomeProjeto) throws Exception {
        Projeto projeto = find(id);

        if (projeto == null) {
            throw new Exception("ERRO_ENCONTRAR_PROJETO");
        }
        if (id <= 0 || nomeProjeto.equals("") /*|| clienteId <= 0*/) {
            throw new Exception("ERRO - Não é possível atualizar o projeto!"); //TODO VER PLS
        }

        try {
            //em.lock(projeto, LockModeType.OPTIMISTIC);
            projeto.setNomeProjeto(nomeProjeto);
            em.merge(projeto);
            return projeto;
        } catch (Exception e) {
            throw new Exception("ERRO_EDITAR_CLIENTE");
        }
    }

    public boolean delete(Integer id) throws Exception {
        Projeto projeto = find(id);

        if (projeto == null) {
            throw new Exception("ERRO_ENCONTRAR_PROJETO");
        }

        if (!projeto.getEstruturas().isEmpty()) {
            throw new Exception("Erro ao apagar o Projeto");
        }

        //TODO confirmar se o projeto pode ser apagado com ficheiros
        if (!projeto.getFicheiros().isEmpty()) {
            throw new Exception("Erro ao apagar o Projeto");
        }

        try {
            em.lock(projeto, LockModeType.OPTIMISTIC);
            em.remove(projeto);
            return true;
        } catch (Exception e) {
            throw new Exception("Erro ao apagar o Projeto");
        }
    }

    public Projeto disponibilizarProjeto(Integer id) throws Exception {
        Projeto projeto = find(id);

        try {
            em.lock(projeto, LockModeType.OPTIMISTIC);
            projeto.setDisponivel(Boolean.TRUE);
            em.merge(projeto);
            return projeto;
        } catch (Exception e) {
            throw new Exception("Erro ao disponiibilizar projeto");
        }
    }

    private void adicionarFicheiroProjeto(Integer idProjeto, Integer idFicheiro) throws Exception {
        Projeto projeto = find(idProjeto);
        Ficheiro ficheiro = ficheiroBean.find(idFicheiro);
        projeto.addFicheiros(ficheiro);
        ficheiro.setProjeto(projeto);
    }

    private void removerFicheiroProjeto(Integer idProjeto, Integer idFicheiro) throws Exception {
        Projeto projeto = find(idProjeto);
        Ficheiro ficheiro = ficheiroBean.find(idFicheiro);
        projeto.removeFicheiros(ficheiro);
        ficheiro.setProjeto(null);
    }

    private void adicionarEstruturaProjeto(Integer idProjeto, Integer idEstrutura) throws Exception {
        Projeto projeto = find(idProjeto);
        Estrutura estrutura = estruturaBean.find(idEstrutura);
        projeto.addEstruturas(estrutura);
        estrutura.setProjeto(projeto);
    }

    private void removerEstruturaProjeto(Integer idProjeto, Integer idEstrutura) throws Exception {
        Projeto projeto = find(idProjeto);
        Estrutura estrutura = estruturaBean.find(idEstrutura);
        projeto.removeEstruturas(estrutura);
        estrutura.setProjeto(null);
    }

    public void addProjetistaToProject(Integer idProjetista, Integer idProjeto) throws Exception {
        Projetista projetista = projetistaBean.find(idProjetista);
        Projeto projeto = find(idProjeto);
        projetista.addProjeto(projeto);
        projeto.addProjetista(projetista);
        em.merge(projetista);
        em.merge(projeto);
    }
}
