package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnFactory {

    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String DBHOST = "jdbc:mysql://localhost:3306/db_atividade";

    public static Connection createConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DBHOST, USERNAME, PASSWORD);
            return conn;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main() {
        Connection conn = createConnection();
        if (conn != null) {
            System.out.println("Conexão Obtida");
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
