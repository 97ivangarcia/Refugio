package entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "Animales") //cambiar el nombre de la tabla
public class Animal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    private String especie;

    @Column(unique = true)
    private String edad;

    private String descripcion;

    private boolean recienAbandonado;
    private boolean proximamenteAcogido;

    private Integer diasRefugio;


    public Animal(Integer id, String nombre, String especie, String edad, String descripcion, boolean recienAbandonado, boolean proximamenteAcogido, Integer diasRefugio) {
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.edad = edad;
        this.descripcion = descripcion;
        this.recienAbandonado = false;
        this.proximamenteAcogido = false;
        this.diasRefugio = 0;
    }

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

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isRecienAbandonado() {
        return recienAbandonado;
    }

    public void setRecienAbandonado(boolean recienAbandonado) {
        this.recienAbandonado = recienAbandonado;
    }

    public boolean isProximamenteAcogido() {
        return proximamenteAcogido;
    }

    public void setProximamenteAcogido(boolean proximamenteAcogido) {
        this.proximamenteAcogido = proximamenteAcogido;
    }

    public Integer getDiasRefugio() {
        return diasRefugio;
    }
}