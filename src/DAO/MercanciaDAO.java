/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Mercancia;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

/**
 *
 * @author CORTES
 */
public class MercanciaDAO {

    public void crear(Object object, EntityManager em) {
        em.persist(object);
    }    

    public boolean eliminar(Object object, EntityManager em) {
        boolean rta = false;
        try {
            em.remove(object);
            rta = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return rta;
        }
    }

    public Object leer(Object object, EntityManager em) {
        Mercancia buscado = null;
        //Éste es un mensaje para ver si entra o no al query...        
        System.out.println("Entré en el Query de leer");
        Query q = em.createQuery("SELECT u FROM Mercancia u WHERE u.nombreM = :entrada");
        q.setParameter("entrada", object.toString());
        try {
            buscado = (Mercancia) q.getSingleResult();
        } catch (NonUniqueResultException e) {
            buscado = (Mercancia) q.getResultList().get(0);
        } catch (NoResultException e) {
            buscado = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buscado;
    }
    
    public Object actualizar(Object object1, EntityManager em) {
        Mercancia viejo = (Mercancia) object1;
        try {
            em.merge(viejo); 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           return em.merge(viejo);
        }
    }
    
    public List<Object> leerTodo(EntityManager em) {
        List<Object> arreglo = null;
        Query q = em.createQuery("SELECT u FROM Mercancia u");
        try {
            arreglo = (List<Object>) q.getResultList();
        } catch (NonUniqueResultException e) {
            arreglo = (List<Object>) q.getResultList().get(0);
        } catch (NoResultException e) {
            arreglo = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arreglo;
    }
    
}
