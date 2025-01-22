package entities;

import Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;


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
        Animal animal = new Animal(null, "Nevado", "Caniche", 6, "Ganó 50.000€ en el casino y se fué para siempre", true, true, 10);
        Animal animal2 = new Animal(null, "Berengario", "Gato", 8, "Lo echaron de casa por borracho", false, true, 48);
        Animal animal3 = new Animal(null, "Paco", "Caballo", 6, "Se metio en las drogas y huyó de casa", true, true, 10);

        session.beginTransaction();

        session.persist(animal);
        session.persist(animal2);
        session.persist(animal3);

        session.getTransaction().commit();

        session.close();
        sessionFactory.close();

        System.out.println("Animal guardado");
    }



    @Test
    void createTables2Test() {

    }


}