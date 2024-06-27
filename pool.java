package ConnectionPoolManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class pool {
    private static final int MIN_IDLE = 5;
    private static final int MAX_IDLE = 10;
    private static final int MAX_TOTAL = 20;
    private static final String URL = "jdbc:postgresql://localhost:5432/productos";
    private static final String USER = "postgres";
    private static final String PASS = "1234";
    private static Connection[] connections;

    static {
        connections = new Connection[MAX_TOTAL];
        for (int i = 0; i < MAX_TOTAL; i++) {
            try {
                connections[i] = DriverManager.getConnection(URL, USER, PASS);
            } catch (SQLException e) {
                System.out.println("Error al crear conexión: " + e.getMessage());
            }
        }
    }

    public static Connection getConnection() throws SQLException {
        for (int i = 0; i < connections.length; i++) {
            if (!connections[i].isClosed()) {
                return connections[i];
            }
        }
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
    }
}
