package ConnectionPoolManager;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = pool.getConnection();
            // Utiliza la conexión
            // ...

            // Cerrar y devolver la conexión al pool
            pool.closeConnection(connection);
        } catch (SQLException e) {
            System.out.println("Error al obtener conexión: " + e.getMessage());
        }
    }
}

