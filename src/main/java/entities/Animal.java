package entities;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Animales")
public class Animal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nombre;

    @Column
    private String especie;

    @Column
    private Integer edad;

    @Column
    private String descripcion;

    @Column
    private Boolean recienAbandonado;

    @Column
    private Boolean proximamenteAcogido;

    @Column
    private Integer diasRefugio;

    // Relación inversa con Persona
    @OneToOne(mappedBy = "animalAdoptado")
    private Persona adoptante;

    public Animal() {}

    public Animal(Integer id, String nombre, String especie, Integer edad, String descripcion,
                  Boolean recienAbandonado, Boolean proximamenteAcogido, Integer diasRefugio) {
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.edad = edad;
        this.descripcion = descripcion;
        this.recienAbandonado = recienAbandonado;
        this.proximamenteAcogido = proximamenteAcogido;
        this.diasRefugio = diasRefugio;
    }

    // Getters y setters...

    public Persona getAdoptante() {
        return adoptante;
    }

    public void setAdoptante(Persona adoptante) {
        this.adoptante = adoptante;
    }
}
