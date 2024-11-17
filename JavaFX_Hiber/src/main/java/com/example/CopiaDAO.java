package com.example;

import com.example.model.Copia;
import com.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Data Access Object (DAO) para gestionar operaciones CRUD de la entidad Copia.
 */
public class CopiaDAO {

    /**
     * Agrega una nueva copia a la base de datos.
     * @param copia La copia que se desea agregar.
     */
    public void guardarCopia(Copia copia) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(copia);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error al guardar la copia: " + e.getMessage());
        }
    }

    /**
     * Elimina una copia de la base de datos dado su ID.
     */
    public Collection<? extends Copia> borrarCopia() {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Copia copia = session.find(Copia.class, tx);

            if (copia != null) {
                session.remove(copia);
                tx.commit();
            } else {
                System.out.println("Copia no encontrada con el ID: " + tx);
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error al eliminar la copia: " + e.getMessage());
        }
        return null;
    }

    /**
     * Obtiene una lista de todas las copias almacenadas en la base de datos.
     * @return Lista de copias.
     */
    public List<Copia> obtenerTodasLasCopias() {
        List<Copia> copias = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Copia> query = session.createQuery("FROM Copia", Copia.class);
            copias = query.getResultList();
        } catch (Exception e) {
            System.err.println("Error al obtener las copias: " + e.getMessage());
        }
        return copias;
    }

    /**
     * Actualiza la información de una copia existente.
     * @param copia La copia con los datos actualizados.
     */
    public void modificarCopia(Long copia) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(copia);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error al actualizar la copia: " + e.getMessage());
        }
    }
}
