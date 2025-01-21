package entities;

import Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import DAO.IpersonaImpl;


class PersonaTest {

    @Test
    void crateTablesTest() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        // Limpia la tabla antes de insertar nuevos datos
        session.beginTransaction();
        session.createQuery("DELETE FROM Persona").executeUpdate();
        session.getTransaction().commit();

        // Insertar nuevas filas
        Persona persona = new Persona(null, "Ivan", 28, "Sevilla");
        Persona persona2 = new Persona(null, "Paco", 29, "Sevilla");
        Persona persona3 = new Persona(null, "Luis", 30, "Sevilla");
        Persona persona4 = new Persona(null, "Maria", 30, "Sevilla");


        session.beginTransaction();

        session.persist(persona);
        session.persist(persona2);
        session.persist(persona3);
        session.persist(persona4);

        session.getTransaction().commit();

        session.close();
        sessionFactory.close();

        System.out.println("Persona guardada");
    }



    @Test
    void createTables2Test() {

    }


}