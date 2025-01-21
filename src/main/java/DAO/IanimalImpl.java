package DAO;

import entities.Animal;

import java.util.List;

public class IanimalImpl implements Ianimal {
    /**
     * @return
     */
    @Override
    public List<Animal> findAll() {
        return List.of();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Animal findById(Integer id) {
        return null;
    }

    /**
     * @param animal
     * @return
     */
    @Override
    public Animal create(Animal animal) {
        return null;
    }

    /**
     * @param animal
     * @return
     */
    @Override
    public Animal update(Animal animal) {
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
