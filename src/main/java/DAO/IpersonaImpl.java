package DAO;

import entities.Persona;

import java.util.List;

public class IpersonaImpl implements Ipersona {
    /**
     * @return
     */
    @Override
    public List<Persona> findAll() {
        return List.of();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Persona findById(Integer id) {
        return null;
    }

    /**
     * @param persona
     * @return
     */
    @Override
    public Persona create(Persona persona) {
        return null;
    }

    /**
     * @param persona
     * @return
     */
    @Override
    public Persona update(Persona persona) {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public boolean deleteById(Integer id) {
        return false;
    }
}
