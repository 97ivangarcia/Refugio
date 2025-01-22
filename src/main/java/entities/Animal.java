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

    // Constructor vacío
    public Animal() {}

    // Constructor con parámetros
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

    // Getters y Setters
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

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getRecienAbandonado() {
        return recienAbandonado;
    }

    public void setRecienAbandonado(Boolean recienAbandonado) {
        this.recienAbandonado = recienAbandonado;
    }

    public Boolean getProximamenteAcogido() {
        return proximamenteAcogido;
    }

    public void setProximamenteAcogido(Boolean proximamenteAcogido) {
        this.proximamenteAcogido = proximamenteAcogido;
    }

    public Integer getDiasRefugio() {
        return diasRefugio;
    }

    public void setDiasRefugio(Integer diasRefugio) {
        this.diasRefugio = diasRefugio;
    }

    public Persona getAdoptante() {
        return adoptante;
    }

    public void setAdoptante(Persona adoptante) {
        this.adoptante = adoptante;
    }
}
