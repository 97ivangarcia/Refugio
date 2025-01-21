package entities;

import Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import DAO.IanimalImpl;


class AnimalTest {

    @Test
    void crateTablesTest() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        // Limpia la tabla antes de insertar nuevos datos
        session.beginTransaction();
        session.createQuery("DELETE FROM Animal").executeUpdate();
        session.getTransaction().commit();

        // Insertar nuevas filas
        Persona persona = new Persona(null, "Ivan", 28, "Sevilla");
        Animal animal = new Animal(null, "Perro", "Labrador", 6, "Ganó 50.000€ en el casino y se fué para siempre", true, true, 10);
        Animal animal2 = new Animal(null, "Gato", "Egipcio", 8, "Lo echaron de casa por calvo", false, true, 48);

        session.beginTransaction();

        session.persist(animal);
        session.persist(animal2);

        session.getTransaction().commit();

        session.close();
        sessionFactory.close();

        System.out.println("Animal guardado");
    }



    @Test
    void createTables2Test() {

    }


}