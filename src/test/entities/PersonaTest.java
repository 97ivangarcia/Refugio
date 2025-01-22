package entities;

import Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

class PersonaTest {

    @Test
    void createTablesTest() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        // Limpia la tabla antes de insertar nuevos datos
        session.beginTransaction();
        session.createQuery("DELETE FROM Persona").executeUpdate();
        session.getTransaction().commit();

        // Insertar nuevas filas con valores válidos para el constructor
        Persona persona1 = new Persona(null, "Ivan", 28, "Sevilla", null);
        Persona persona2 = new Persona(null, "Paco", 29, "Sevilla", null);
        Persona persona3 = new Persona(null, "Luis", 30, "Sevilla", null);
        Persona persona4 = new Persona(null, "Maria", 30, "Sevilla", null);

        session.beginTransaction();

        session.persist(persona1);
        session.persist(persona2);
        session.persist(persona3);
        session.persist(persona4);

        session.getTransaction().commit();

        session.close();
        sessionFactory.close();

        System.out.println("Personas guardadas exitosamente.");
    }

    @Test
    void createTables2Test() {
        // Implementar otro caso de prueba si es necesario
    }
}
