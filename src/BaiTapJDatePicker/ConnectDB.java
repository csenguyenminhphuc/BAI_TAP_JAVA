package BaiTapJDatePicker;	

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private static Connection con = null;
    private static ConnectDB instance = new ConnectDB();

    public static ConnectDB getInstance() {
        return instance;
    }

    public void connect() throws SQLException {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=PhongToChuc;encrypt=true;trustServerCertificate=true";
        String user = "sa";
        String password = "sapassword";
        con = DriverManager.getConnection(url, user, password);
    }

    public void disconnect() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection getConnection() {
        try {
            if (con == null || con.isClosed()) {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                getInstance().connect();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return con;
    }
}
