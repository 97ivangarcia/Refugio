package org.example.Jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Connection conn = null;
        Scanner scanner = new Scanner(System.in);

        try {
            // 1. Establecer la conexión
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hibernateanimales", "root", "");
            System.out.println("Conexión establecida");

            int opcion;
            do {
                System.out.println("\n----- Menú -----");
                System.out.println("1. Registrar nuevo animal");
                System.out.println("2. Buscar animales por especie");
                System.out.println("3. Actualizar estado del animal");
                System.out.println("4. Registrar datos de la familia que lo acoge");
                System.out.println("5. Mostrar todos los animales");
                System.out.println("6. Mostrar todas las familias");
                System.out.println("7. Borrar todos los datos");
                System.out.println("8. Salir");
                System.out.print("Elige una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer

                switch (opcion) {
                    case 1:
                        registrarNuevoAnimal(conn, scanner);
                        break;
                    case 2:
                        buscarAnimalesPorEspecie(conn, scanner);
                        break;
                    case 3:
                        actualizarEstadoAnimal(conn, scanner);
                        break;
                    case 4:
                        registrarFamilia(conn, scanner);
                        break;
                    case 5:
                        mostrarTodosLosAnimales(conn);
                        break;
                    case 6:
                        mostrarTodasLasFamilias(conn);
                        break;
                    case 7:
                        borrarTodosLosDatos(conn);
                        break;
                    case 8:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción no válida");
                }
            } while (opcion != 7);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            scanner.close();
        }
    }

    private static void registrarNuevoAnimal(Connection conn, Scanner scanner) {
        try {
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
            scanner.nextLine(); // Limpiar buffer

            String sql = "INSERT INTO Animales (nombre, especie, edad, descripcion, recienAbandonado, proximamenteAcogido, diasRefugio) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombre);
            stmt.setString(2, especie);
            stmt.setInt(3, edad);
            stmt.setString(4, descripcion);
            stmt.setBoolean(5, recienAbandonado);
            stmt.setBoolean(6, proximamenteAcogido);
            stmt.setInt(7, diasRefugio);

            stmt.executeUpdate();
            System.out.println("Animal registrado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void buscarAnimalesPorEspecie(Connection conn, Scanner scanner) {
        try {
            System.out.print("Ingrese la especie a buscar: ");
            String especie = scanner.nextLine();

            String sql = "SELECT * FROM Animales WHERE especie = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, especie);

            ResultSet rs = stmt.executeQuery();

            System.out.println("Resultados:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int edad = rs.getInt("edad");
                String descripcion = rs.getString("descripcion");
                System.out.println("ID: " + id + ", Nombre: " + nombre + ", Edad: " + edad + ", Descripción: " + descripcion);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void actualizarEstadoAnimal(Connection conn, Scanner scanner) {
        try {
            System.out.print("Ingrese el ID del animal a actualizar: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            String verificarSql = "SELECT * FROM Animales WHERE id = ?";
            PreparedStatement verificarStmt = conn.prepareStatement(verificarSql);
            verificarStmt.setInt(1, id);
            ResultSet rs = verificarStmt.executeQuery();

            if (!rs.next()) {
                System.out.println("El ID ingresado no existe.");
                return;
            } else {
                System.out.println("Animal encontrado: ");
                System.out.println("ID: " + rs.getInt("id") + ", Nombre: " + rs.getString("nombre") + ", Especie: " + rs.getString("especie") + ", Edad: " + rs.getInt("edad"));
            }

            System.out.print("¿El animal fue recién abandonado? (true/false): ");
            boolean recienAbandonado = scanner.nextBoolean();

            System.out.print("¿El animal será próximamente acogido? (true/false): ");
            boolean proximamenteAcogido = scanner.nextBoolean();

            System.out.print("Ingrese los días en el refugio: ");
            int diasRefugio = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            String sql = "UPDATE Animales SET recienAbandonado = ?, proximamenteAcogido = ?, diasRefugio = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setBoolean(1, recienAbandonado);
            stmt.setBoolean(2, proximamenteAcogido);
            stmt.setInt(3, diasRefugio);
            stmt.setInt(4, id);

            stmt.executeUpdate();
            System.out.println("Estado del animal actualizado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void registrarFamilia(Connection conn, Scanner scanner) {
        try {
            System.out.print("Ingrese el ID del animal acogido: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            String verificarSql = "SELECT * FROM Animales WHERE id = ?";
            PreparedStatement verificarStmt = conn.prepareStatement(verificarSql);
            verificarStmt.setInt(1, id);
            ResultSet rs = verificarStmt.executeQuery();

            if (!rs.next()) {
                System.out.println("El ID ingresado no corresponde a ningún animal.");
                return;
            } else {
                System.out.println("Animal encontrado: ");
                System.out.println("ID: " + rs.getInt("id") + ", Nombre: " + rs.getString("nombre") + ", Especie: " + rs.getString("especie") + ", Edad: " + rs.getInt("edad"));
            }

            System.out.print("Ingrese el nombre de la familia: ");
            String nombre = scanner.nextLine();

            System.out.print("Ingrese la edad del representante: ");
            int edad = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            System.out.print("Ingrese la ciudad de residencia: ");
            String ciudad = scanner.nextLine();

            String sql = "INSERT INTO Personas (idAnimal, nombre, edad, ciudad) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setString(2, nombre);
            stmt.setInt(3, edad);
            stmt.setString(4, ciudad);

            stmt.executeUpdate();
            System.out.println("Datos de la familia registrados correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void mostrarTodosLosAnimales(Connection conn) {
        try {
            String sql = "SELECT * FROM Animales";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            System.out.println("Lista de animales:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Nombre: " + rs.getString("nombre") + ", Especie: " + rs.getString("especie") + ", Edad: " + rs.getInt("edad") + ", Descripción: " + rs.getString("descripcion"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void mostrarTodasLasFamilias(Connection conn) {
        try {
            String sql = "SELECT * FROM Personas";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            System.out.println("Lista de personas:");
            while (rs.next()) {
                System.out.println("ID Persona: " + rs.getInt("id") + ", Nombre persona: " + rs.getString("nombre") + ", Edad: " + rs.getInt("edad") + ", Ciudad: " + rs.getString("ciudad"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void borrarTodosLosDatos(Connection conn) {
        try {
            String sqlAnimales = "DELETE FROM Animales";
            String sqlPersonas = "DELETE FROM Personas";

            PreparedStatement stmtAnimales = conn.prepareStatement(sqlAnimales);
            PreparedStatement stmtPersonas = conn.prepareStatement(sqlPersonas);

            int rowsDeletedAnimales = stmtAnimales.executeUpdate();
            int rowsDeletedPersonas = stmtPersonas.executeUpdate();
            System.out.println("A chuparla todos los registros.");
            System.out.println("Se han eliminado " + rowsDeletedAnimales + " registros de la tabla 'Animales'.");
            System.out.println("Se han eliminado " + rowsDeletedPersonas + " registros de la tabla 'Personas'.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
