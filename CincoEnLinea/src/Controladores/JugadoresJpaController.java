/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.NonexistentEntityException;
import Controladores.exceptions.PreexistingEntityException;
import Persistencia.Jugadores;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author marianacro
 */
public class JugadoresJpaController implements Serializable {

    public JugadoresJpaController() {
        this.emf = Persistence.createEntityManagerFactory("CincoEnLineaPU2");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Jugadores jugadores) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(jugadores);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findJugadores(jugadores.getUsuario()) != null) {
                throw new PreexistingEntityException("Jugadores " + jugadores + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Jugadores jugadores) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            jugadores = em.merge(jugadores);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = jugadores.getUsuario();
                if (findJugadores(id) == null) {
                    throw new NonexistentEntityException("The jugadores with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Jugadores jugadores;
            try {
                jugadores = em.getReference(Jugadores.class, id);
                jugadores.getUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The jugadores with id " + id + " no longer exists.", enfe);
            }
            em.remove(jugadores);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Jugadores> findJugadoresEntities() {
        return findJugadoresEntities(true, -1, -1);
    }

    public List<Jugadores> findJugadoresEntities(int maxResults, int firstResult) {
        return findJugadoresEntities(false, maxResults, firstResult);
    }

    private List<Jugadores> findJugadoresEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Jugadores.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Jugadores findJugadores(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Jugadores.class, id);
        } finally {
            em.close();
        }
    }

    public int getJugadoresCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Jugadores> rt = cq.from(Jugadores.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
