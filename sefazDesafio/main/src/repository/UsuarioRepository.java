package repository;

import models.Usuario;

import javax.persistence.*;
import java.util.List;

public class UsuarioRepository {


    EntityManagerFactory emf;
    EntityManager em;

    public UsuarioRepository() {
        emf = Persistence.createEntityManagerFactory("hibernateSefazDesafio");
        em = emf.createEntityManager();
    }
    public void salvar(Usuario usuario) {
        em.getTransaction().begin();
        em.persist(usuario);
        em.flush();
        em.getTransaction().commit();
    }

    public void update(Usuario usuario) {
        em.merge(usuario);
    }

    public void deletar(Long id) {
        em.remove(id);
    }

    /*public Usuario findById(Integer id) {
        return em.find(Usuario.class, id);
    }*/

    public int findByEmail(String email) {
        return em.createQuery("SELECT u FROM Usuario u where u.email = :email").setParameter("email", email).getFirstResult();
    }

    public List<Usuario> list() {
        Query query = em.createQuery("SELECT u FROM Usuario u");
        @SuppressWarnings("unchecked")
        List<Usuario> usuarios = query.getResultList();
        return usuarios;
    }

    public boolean validateUser(Usuario usuario) throws Exception {
        boolean result;
        List<Usuario> list = null;
        try {

            String q = " SELECT u FROM Usuario u WHERE Nome = :nome AND Senha = :senha ";
            TypedQuery<Usuario> query = em.createQuery(q, Usuario.class);
            query.setParameter("nome", usuario.getNome());
            query.setParameter("senha", usuario.getSenha());

            list = query.getResultList();

            if (list.size() > 0) {
                result = true;
            } else {
                result = false;
            }
            return result;

        } catch (Exception ex) {

            throw new Exception("Atencao: Ocorreu um erro ao tentar verificar login.");
        }
    }

}
