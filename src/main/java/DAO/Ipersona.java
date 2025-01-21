package DAO;

import entities.Persona;

import java.util.List;

public interface Ipersona {

    /**
     * @return
     */
    List<Persona> findAll();

    /**
     * @param id
     * @return
     */
    Persona findById(Integer id);

    /**
     * @param persona
     * @return
     */
    Persona create(Persona persona);

    /**
     * @param persona
     * @return
     */
    Persona update(Persona persona);

    /**
     * @param id
     * @return
     */
    boolean deleteById(Integer id);
}
