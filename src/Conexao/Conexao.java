package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String url = "jdbc:mysql://localhost:3306/teste";
    private static final String user = "root";
    private static final String password = "987678274";
    private static Connection connection;
    public static Connection getConexao(){
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(url, user, password);
                return connection;
            }
            else{
                return connection;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
