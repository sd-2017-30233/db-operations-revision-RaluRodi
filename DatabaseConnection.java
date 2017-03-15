package studentcourses;

import javax.swing.JOptionPane;
import java.sql.*;
import java.sql.DriverManager;

public class DatabaseConnection{
  private static Connection con;
   private String url = "jdbc:mysql://localhost:3306/Students";
	private	String uid = "root";
	private	String pw = "";
       


  public DatabaseConnection() {
    loadDriver();
    connectToDatabase();
  }

  public void loadDriver() {
    try {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
    }
    catch (Exception exception) {
      JOptionPane.showMessageDialog(null, "Connection driver loading failed", "Error", JOptionPane.ERROR_MESSAGE);
    }
  }

  public void connectToDatabase() {
    try {
      Connection conn = DriverManager.getConnection(url,uid,pw);
      DatabaseConnection.con = conn;
    }
    catch (Exception exception) {
      JOptionPane.showMessageDialog(null, "Establising database connection failed", "Error", JOptionPane.ERROR_MESSAGE);
    }
  }

  public void deconnectFromDatabase() {
    if (con != null) {
      try {
        con.close();
        con = null;
      }
      catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Closing database connection failed", "Error", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  public static Connection getConnection() {
    return con;
  }
}