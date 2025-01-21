package org.example.Jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            //1. Establecer la conexión

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hibernateanimales", "root", "");
            System.out.println("Conexión establecida");

            //2. Crear una sentencia SQL
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Personas");

            //3. Recorrer el ResultSet
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int edad = rs.getInt("edad");
                String ciudad = rs.getString("ciudad");
                System.out.println("ID: " + id + ", Nombre: " + nombre + ", Edad: " + edad + ", Ciudad: " + ciudad);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}