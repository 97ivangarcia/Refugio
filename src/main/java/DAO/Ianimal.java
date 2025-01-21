package DAO;

import entities.Animal;

import java.util.List;

public interface Ianimal {

    /**
     * @return
     */
    List<Animal> findAll();

    /**
     * @param id
     * @return
     */
    Animal findById(Integer id);

    /**
     * @param animal
     * @return
     */
    Animal create(Animal animal);

    /**
     * @param animal
     * @return
     */
    Animal update(Animal animal);

    /**
     * @param id
     * @return
     */
    boolean deleteById(Integer id);
}
