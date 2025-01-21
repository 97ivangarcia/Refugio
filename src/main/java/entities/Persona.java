package entities;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Personas")
public class Persona implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nombre;

    @Column
    private Integer edad;

    @Column
    private String ciudad;

    // Relación uno a uno con Animal
    @OneToOne
    @JoinColumn(name = "animal_id", referencedColumnName = "id", unique = true) // animal_id como clave foránea
    private Animal animalAdoptado;

    public Persona(Integer id, String nombre, Integer edad, String ciudad, Animal animalAdoptado) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.ciudad = ciudad;
        this.animalAdoptado = animalAdoptado;
    }

    public Persona() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Animal getAnimalAdoptado() {
        return animalAdoptado;
    }

    public void setAnimalAdoptado(Animal animalAdoptado) {
        this.animalAdoptado = animalAdoptado;
    }
}
