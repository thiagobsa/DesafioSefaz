package repository;

import models.Telefone;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class TelefoneRepository {
    EntityManagerFactory emf;
    EntityManager em;

    public TelefoneRepository() {

        emf = Persistence.createEntityManagerFactory("hibernateSefazDesafio");
        em = emf.createEntityManager();
    }

    public void insert(Telefone telefone) throws Exception {

        try {

            em.getTransaction().begin();
            em.persist(telefone);
            em.getTransaction().commit();
            em.close();
            emf.close();

        } catch (Exception ex) {
            throw new Exception("Atenção: Ocorreu um erro ao tentar inserir o telefone.");
        }
    }

    public void update(Telefone telefone) throws Exception {

        try {
            em.getTransaction().begin();
            telefone = em.merge(telefone);
            em.getTransaction().commit();
            em.close();
            emf.close();
        } catch (Exception ex) {

            throw new Exception("Atenção: Ocorreu um erro ao tentar atualizar o telefone.");
        }
    }

    @SuppressWarnings("unchecked")
    public List<Telefone> findAllById(Integer usuarioId) throws Exception {

        try {
            return em.createQuery(" SELECT ddd, numero, tipo FROM telefone WHERE id_usuario ="+ usuarioId).getResultList();
        } catch (Exception ex) {
            throw new Exception("Atenção: Ocorreu um erro ao tentar listar os telefones.");
        }
    }

    public void remove(Telefone telefone) throws Exception {

        try {
            em.getTransaction().begin();
            telefone = em.merge(telefone);
            em.remove(telefone);
            em.getTransaction().commit();
            emf.close();

        } catch (Exception ex) {
            throw new Exception("Atenção: Ocorreu um erro ao tentar ao atualizar o telefone.");
        }
    }
}
