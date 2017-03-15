package studentcourses;

import java.sql.Statement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseLogicController {
	
	  private Connection connection;
	  private Statement statement;
	  private ResultSet resultSet;
	  private CallableStatement callableStatement;

	  public DatabaseLogicController(Connection connection) throws SQLException {
	    this.connection = connection;
	  }
	  
	  public ResultSet InfoStudent(String nume) {
		    String query = "select s.nume as Nume, s.data_nasterii as DATA_NASTERII, a.tara as TARA, a.oras as ORAS, a.strada as STRADA\n" +
		    		"from student s,adresa a\n" +
		    		"where s.nume='" +nume+ "'\n"+
		    		"and s.id_adresa=a.id;";
		    try {
		        statement = connection.createStatement();
		        resultSet = statement.executeQuery(query);
		      
		    while (resultSet.next())
		      {
		        String Nume=resultSet.getString("Nume");
		        Date Data_nasterii = resultSet.getDate("data_nasterii");
		        String Tara = resultSet.getString("Tara");
		        String Oras = resultSet.getString("Oras");
		        String Strada=resultSet.getString("Strada");
		       
		        // print the results
		        System.out.format("%s, %s, %s:, %s, %s\n",Nume,Data_nasterii,Tara,Oras,Strada);
		      }
	  }
		      catch (SQLException e) {
		        e.printStackTrace();
		      }
		      return resultSet;
		    }
	  
	  

	  public ResultSet InfoCourse(String nume) {
		    String query = "select c.nume as Nume, c.profesor as Profesor, c.an_studiu as AnStudiu\n" +
		    		"from curs c\n" +
		    		"where c.nume='" +nume+ "';";
		    try {
		        statement = connection.createStatement();
		        resultSet = statement.executeQuery(query);
		      
		    while (resultSet.next())
		      {
		        String Nume=resultSet.getString("Nume");
		        String Profesor = resultSet.getString("Profesor");
		        int AnStudiu = resultSet.getInt("AnStudiu");
		        
		       
		        // print the results
		        System.out.format("%s, %s, %s\n",Nume,Profesor,AnStudiu);
		      }
	  }
		      catch (SQLException e) {
		        e.printStackTrace();
		      }
		      return resultSet;
		    }
	  
	  
	  
	  public ResultSet InfoEnrolment(String nume) {
		    String query = "select c.nume,s.nume as NumeS\n" +
		    		"from curs c,student s,inrolare i\n" +
		    		"where c.nume='" +nume+ "'\n"+
		    		"and c.id=i.id_curs\n"+
		    		"and s.id=i.id_student;";
		    try {
		        statement = connection.createStatement();
		        resultSet = statement.executeQuery(query);
		        System.out.format("%s:\n",nume); 
		        
		    while (resultSet.next())
		      {
		        // print the results
		    	String nume1=resultSet.getString("NumeS");
		    	//String nume2=resultSet.getString("NumeS");
		    	//String nume3=resultSet.getString("NumeS");
		    	
		    	
		        System.out.format("%s ",nume1);
		      }
	  }
		      catch (SQLException e) {
		        e.printStackTrace();
		      }
		      return resultSet;
		    }
	  

	  public String insertStudent(Student s) {
		    String result = new String();
		    try {
		      callableStatement = connection.prepareCall("{call STUDENT_NOU(?,?,?)}");
		      callableStatement.setString(1, s.getName());
		      callableStatement.setDate(2, s.getDate());
		      callableStatement.setInt(3, s.getAdress());
		      callableStatement.execute();
		      resultSet = callableStatement.getResultSet();
		      if (callableStatement.getMoreResults()) {
		        resultSet = callableStatement.getResultSet();
		        resultSet.next();
		        result = resultSet.getString(1);
		      }
		    }
		    catch (SQLException e) {
		          e.printStackTrace();
		          
		    }
		    return result;
		  }
	  
	  
	  public String insertCourse(Course c) {
		    String result = new String();
		    try {
		      callableStatement = connection.prepareCall("{call CURS_NOU(?,?,?)}");
		      callableStatement.setString(1,c.getName());
		      callableStatement.setString(2, c.getProf());
		      callableStatement.setInt(3, c.getYear());
		      callableStatement.execute();
		      resultSet = callableStatement.getResultSet();
		      if (callableStatement.getMoreResults()) {
		        resultSet = callableStatement.getResultSet();
		        resultSet.next();
		        result = resultSet.getString(1);
		      }
		    }
		    catch (SQLException e) {
		          e.printStackTrace();
		          
		    }
		    return result;
		  }
	  
	  public String updateStudent(String nume, String tara,String oras,String strada) {
		    String result = new String();
		    try {
		      callableStatement = connection.prepareCall("{call ACTUALIZARE_STUDENT(?,?,?,?)}");
		      callableStatement.setString(1, nume);
		      callableStatement.setString(2, tara);
		      callableStatement.setString(3, oras);
		      callableStatement.setString(4, strada);
		      callableStatement.execute();
		      resultSet = callableStatement.getResultSet();
		      if (callableStatement.getMoreResults()) {
		        resultSet = callableStatement.getResultSet();
		        resultSet.next();
		        result = resultSet.getString(1);
		      }
		    }
		    catch (SQLException e) {
		          e.printStackTrace();
		          
		    }
		    return result;
		  }
	  
	  public String updateCourse(String nume, String profesor,int an_studiu) {
		    String result = new String();
		    try {
		      callableStatement = connection.prepareCall("{call ACTUALIZARE_CURS(?,?,?)}");
		      callableStatement.setString(1, nume);
		      callableStatement.setString(2, profesor);
		      callableStatement.setInt(3, an_studiu);
		      callableStatement.execute();
		      resultSet = callableStatement.getResultSet();
		      if (callableStatement.getMoreResults()) {
		        resultSet = callableStatement.getResultSet();
		        resultSet.next();
		        result = resultSet.getString(1);
		      }
		    }
		    catch (SQLException e) {
		          e.printStackTrace();
		          
		    }
		    return result;
		  }
	  
	  public String deleteStudent(String nume) {
		    String result = new String();
		    try {
		      callableStatement = connection.prepareCall("{call STERGE_STUDENT(?)}");
		      callableStatement.setString(1, nume);
		      callableStatement.execute();
		      resultSet = callableStatement.getResultSet();
		      if (callableStatement.getMoreResults()) {
		        resultSet = callableStatement.getResultSet();
		        resultSet.next();
		        result = resultSet.getString(1);
		      }
		    }
		    catch (SQLException e) {
		          e.printStackTrace();
		          
		    }
		    return result;
		  }
	  

	  public String deleteCourse(String nume) {
		    String result = new String();
		    try {
		      callableStatement = connection.prepareCall("{call STERGE_CURS(?)}");
		      callableStatement.setString(1, nume);
		      callableStatement.execute();
		      resultSet = callableStatement.getResultSet();
		      if (callableStatement.getMoreResults()) {
		        resultSet = callableStatement.getResultSet();
		        resultSet.next();
		        result = resultSet.getString(1);
		      }
		    }
		    catch (SQLException e) {
		          e.printStackTrace();
		          
		    }
		    return result;
		  }
	  
	  
	  public void enroleStudent(int ids,int idc) {
		    String result = new String();
		    try {
		      callableStatement = connection.prepareCall("{call INROLARE_PR(?,?)}");
		      callableStatement.setInt(1, ids);
		      callableStatement.setInt(2, idc);
		      callableStatement.execute();
		      resultSet = callableStatement.getResultSet();
		      if (callableStatement.getMoreResults()) {
		        resultSet = callableStatement.getResultSet();
		        resultSet.next();
		        result = resultSet.getString(1);
		      }
		    }
		    catch (SQLException e) {
		          e.printStackTrace();
		          
		    }
		  }

	
	 
}
