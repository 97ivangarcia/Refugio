package org.example.Jdbc;

import DAO.IanimalImpl;

import DAO.IpersonaImpl;
import entities.Animal;
import entities.Persona;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        IanimalImpl animalDAO = new IanimalImpl();
        IpersonaImpl personaDAO = new IpersonaImpl();
        Scanner scanner = new Scanner(System.in);

        int opcion;
        do {
            System.out.println("\n----- Menú -----");
            System.out.println("1. Registrar nuevo animal");
            System.out.println("2. Buscar animales por especie");
            System.out.println("3. Actualizar estado del animal");
            System.out.println("4. Registrar acogida de un animal");
            System.out.println("5. Mostrar todos los animales");
            System.out.println("6. Mostrar todas las personas");
            System.out.println("7. Borrar un animal del refugio por ID");
            System.out.println("8. Borrar una persona por ID");
            System.out.println("9. Mostrar mascotas con sus personas de acogida");
            System.out.println("10. Salir");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    registrarNuevoAnimal(animalDAO, scanner);
                    break;
                case 2:
                    buscarAnimalesPorEspecie(animalDAO, scanner);
                    break;
                case 3:
                    actualizarEstadoAnimal(animalDAO, scanner);
                    break;
                case 4:
                    registrarFamilia(personaDAO, animalDAO, scanner);
                    break;
                case 5:
                    mostrarTodosLosAnimales(animalDAO);
                    break;
                case 6:
                    mostrarTodasLasFamilias(personaDAO);
                    break;
                case 7:
                    borrarAnimalPorId(animalDAO, scanner);
                    break;
                case 8:
                    borrarFamiliaPorId(personaDAO, scanner);
                    break;
                case 9:
                    mostrarMascotasConFamilia(animalDAO, personaDAO);
                    break;
                case 10:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción no válida");
            }
        } while (opcion != 9);

        scanner.close();
    }

    private static void registrarNuevoAnimal(IanimalImpl animalDAO, Scanner scanner) {
        System.out.print("Ingrese el nombre del animal: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese la especie: ");
        String especie = scanner.nextLine();

        System.out.print("Ingrese la edad: ");
        int edad = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        System.out.print("Ingrese la descripción: ");
        String descripcion = scanner.nextLine();

        System.out.print("¿El animal fue recién abandonado? (true/false): ");
        boolean recienAbandonado = scanner.nextBoolean();

        System.out.print("¿El animal será próximamente acogido? (true/false): ");
        boolean proximamenteAcogido = scanner.nextBoolean();

        System.out.print("Ingrese los días en el refugio: ");
        int diasRefugio = scanner.nextInt();

        Animal animal = new Animal(null, nombre, especie, edad, descripcion, recienAbandonado, proximamenteAcogido, diasRefugio);
        animalDAO.create(animal);
        System.out.println("Animal registrado correctamente.");
    }

    private static void buscarAnimalesPorEspecie(IanimalImpl animalDAO, Scanner scanner) {
        System.out.print("Ingrese la especie a buscar: ");
        String especie = scanner.nextLine();

        List<Animal> animales = animalDAO.findAll();
        System.out.println("Animales encontrados:");
        animales.stream()
                .filter(a -> a.getEspecie().equalsIgnoreCase(especie))
                .forEach(a -> System.out.println("ID: " + a.getId() + ", Nombre: " + a.getNombre()));
    }

    private static void actualizarEstadoAnimal(IanimalImpl animalDAO, Scanner scanner) {
        System.out.print("Ingrese el ID del animal a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        Animal animal = animalDAO.findById(id);
        if (animal == null) {
            System.out.println("Animal no encontrado.");
            return;
        }

        System.out.print("¿El animal fue recién abandonado? (true/false): ");
        boolean recienAbandonado = scanner.nextBoolean();

        System.out.print("¿El animal será próximamente acogido? (true/false): ");
        boolean proximamenteAcogido = scanner.nextBoolean();

        System.out.print("Ingrese los días en el refugio: ");
        int diasRefugio = scanner.nextInt();

        animal.setRecienAbandonado(recienAbandonado);
        animal.setProximamenteAcogido(proximamenteAcogido);
        animal.setDiasRefugio(diasRefugio);

        animalDAO.update(animal);
        System.out.println("Estado del animal actualizado correctamente.");
    }

    private static void registrarFamilia(IpersonaImpl personaDAO, IanimalImpl animalDAO, Scanner scanner) {
        System.out.print("Ingrese el ID del animal acogido: ");
        int idAnimal = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        Animal animal = animalDAO.findById(idAnimal);
        if (animal == null) {
            System.out.println("Animal no encontrado.");
            return;
        }

        System.out.print("Ingrese el nombre de la persona: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese la edad del representante: ");
        int edad = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        System.out.print("Ingrese la ciudad de residencia: ");
        String ciudad = scanner.nextLine();

        Persona persona = new Persona(null, nombre, edad, ciudad, animal);
        personaDAO.create(persona);
        System.out.println("Persona registrada correctamente.");
    }

    private static void mostrarTodosLosAnimales(IanimalImpl animalDAO) {
        List<Animal> animales = animalDAO.findAll();
        System.out.println("Lista de animales:");
        animales.forEach(a -> System.out.println("ID: " + a.getId() + ", Nombre: " + a.getNombre()));
    }

    private static void mostrarTodasLasFamilias(IpersonaImpl personaDAO) {
        List<Persona> personas = personaDAO.findAll();
        System.out.println("Lista de personas:");
        personas.forEach(p -> System.out.println("ID: " + p.getId() + ", Nombre: " + p.getNombre()));
    }

    private static void borrarAnimalPorId(IanimalImpl animalDAO, Scanner scanner) {
        System.out.print("Ingrese el ID del animal a borrar: ");
        int id = scanner.nextInt();

        // Intentar obtener el animal antes de borrarlo
        Animal animal = animalDAO.findById(id);
        if (animal != null) {
            if (animalDAO.deleteById(id)) {
                System.out.println("Animal '" + animal.getNombre() + "' eliminado correctamente.");
            } else {
                System.out.println("Error al eliminar el animal.");
            }
        } else {
            System.out.println("Animal no encontrado.");
        }
    }


    private static void borrarFamiliaPorId(IpersonaImpl personaDAO, Scanner scanner) {
        System.out.print("Ingrese el ID de la familia a borrar: ");
        int id = scanner.nextInt();

        // Intentar obtener la familia antes de borrarla
        Persona familia = personaDAO.findById(id); // Supongamos que `findById` devuelve la familia o null
        if (familia != null) {
            if (personaDAO.deleteById(id)) {
                System.out.println("Persona '" + familia.getNombre() + "' eliminada correctamente.");
            } else {
                System.out.println("Error al eliminar la persona.");
            }
        } else {
            System.out.println("Persona no encontrada.");
        }
    }

    private static void mostrarMascotasConFamilia(IanimalImpl animalDAO, IpersonaImpl personaDAO) {
        List<Animal> animales = animalDAO.findAll();
        if (animales.isEmpty()) {
            System.out.println("No hay animales registrados.");
            return;
        }

        System.out.println("Lista de mascotas con sus personas de acogida:");
        for (Animal animal : animales) {
            Persona familia = animal.getAdoptante();
            if (familia != null) {
                System.out.println("Animal: " + animal.getNombre() + " (ID: " + animal.getId() + "), Especie: " + animal.getEspecie()
                        + " - Adoptado por: " + familia.getNombre() + " (ID: " + familia.getId() + ")");
            } else {
                System.out.println("Animal: " + animal.getNombre() + " (ID: " + animal.getId() + "), Especie: " + animal.getEspecie()
                        + " - Sin familia de acogida.");
            }
        }
    }

}

