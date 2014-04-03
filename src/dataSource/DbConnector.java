package dataSource;

import java.sql.*;

public class DbConnector {

    private final String userName, passWord, DbName;
    Connection con;

    public DbConnector() {
        this.userName = "cphtj151";
        this.passWord = "cphtj151";
        this.DbName = "jdbc:oracle:thin:@datdb.cphbusiness.dk:1521:dat";
    }

    public void loadDBdriver() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Driver loaded");
        } catch (ClassNotFoundException ex) {
            System.exit(1);
        }

    }

    public void establishConnection() {
        try {
            con = DriverManager.getConnection(DbName, userName, passWord);
            System.out.println("We are connected!");
            System.out.println("You are logged in as: " + userName);
        } catch (SQLException e) {
            System.out.println("Error in establishConnection method");
        }

    }

    public boolean releaseConnection() {
        try {
            con.close();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public Connection getConnection() {
        return con;
    }

}
