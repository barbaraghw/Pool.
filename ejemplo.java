package ConnectionPoolManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ejemplo {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5); // Crea un pool de 5 threads

        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                try {
                    Connection connection = pool.getConnection();
                    // Utiliza la conexión
                    // ...

                    // Mostrar las tablas de la base de datos "productos"
                    showTables(connection, "postgres");

                    // Cerrar y devolver la conexión al pool
                    pool.closeConnection(connection);
                } catch (SQLException e) {
                    System.out.println("Error al obtener conexión: " + e.getMessage());
                }
            });
        }

        executor.shutdown();
    }

    private static void showTables(Connection connection, String databaseName) {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM " + databaseName)) {
            System.out.println("Tablas en la base de datos \"" + databaseName + "\":");
            while (resultSet.next()) {
                String tableName = resultSet.getString("table_name");
                System.out.println(tableName);
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar las tablas: " + e.getMessage());
        }
    }
} 