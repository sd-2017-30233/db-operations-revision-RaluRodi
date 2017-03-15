package studentcourses;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class TestClass {
	
	DatabaseConnection conn=new DatabaseConnection();
	DatabaseLogicController a;

	@SuppressWarnings("static-access")
	@Test
	public void testAddStudent() throws SQLException {
		
		a=new DatabaseLogicController(conn.getConnection());
		
		String nume="Popescu Mirabela";
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		Student s=new Student(nume,sqlDate,2);
		
		a.insertStudent(s);
		
		String query= "select s.nume as Nume\n" +
	    		"from student s\n" +
	    		"where s.nume='" +nume+ "';";
		
		        Statement statement = conn.getConnection().createStatement();
		        ResultSet resultSet = statement.executeQuery(query);
		        
		        assertNotNull(resultSet); 
	}

	
	
	
	@SuppressWarnings("static-access")
	@Test
	public void testAddCourse() throws SQLException {
		
		a=new DatabaseLogicController(conn.getConnection());
		
		String nume="Algebra";
		String numeP="Ionescu Dan";
		Course c=new Course(nume,numeP,3);
		
		a.insertCourse(c);
		
		String query= "select c.nume as Nume\n" +
	    		"from curs c\n" +
	    		"where c.nume='" +nume+ "';";
		
		        Statement statement = conn.getConnection().createStatement();
		        ResultSet resultSet = statement.executeQuery(query);
		        
		        assertNotNull(resultSet); 
	}
	
	
	@SuppressWarnings("static-access")
	@Test
	public void testDeleteStudent() throws SQLException {
		
		a=new DatabaseLogicController(conn.getConnection());
		
		String nume="Sechel Raluca";
		
		a.deleteStudent(nume);
		
		String query= "select s.nume as Nume\n" +
	    		"from student s\n" +
	    		"where s.nume='" +nume+ "';";
		
		        Statement statement = conn.getConnection().createStatement();
		        ResultSet resultSet = statement.executeQuery(query);
		        
		        assertNotNull(resultSet); 
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testDeleteCourse() throws SQLException {
		
		a=new DatabaseLogicController(conn.getConnection());
		
		String nume="Inginerie software";
		
		a.deleteCourse(nume);
		
		String query= "select c.nume as Nume\n" +
	    		"from curs c\n" +
	    		"where c.nume='" +nume+ "';";
		
		        Statement statement = conn.getConnection().createStatement();
		        ResultSet resultSet = statement.executeQuery(query);
		        
		        assertNotNull(resultSet); 
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testUpdateStudent() throws SQLException {
		
		a=new DatabaseLogicController(conn.getConnection());
		
		String name="Dregan Anda";
		a.updateStudent(name, "Romania", "Jibou", "Padurii5H");
		
		String query= "select s.nume as Nume, a.tara as TARA, a.oras as ORAS, a.strada as STRADA\n" +
	    		"from student s,adresa a\n" +
	    		"where s.nume='" +name+ "'\n"+
	    		"and s.id_adresa=a.id;";
		
		        Statement statement = conn.getConnection().createStatement();
		        ResultSet resultSet = statement.executeQuery(query);
		        while (resultSet.next())
			      {
			        String nume=resultSet.getString("Nume");
			        assertEquals(nume,name);
			        
			        String tara=resultSet.getString("Tara");
			        assertEquals(tara,"Romania");
			        
			        String oras=resultSet.getString("Oras");
			        assertEquals(oras,"Jibou");
			        
			        String strada=resultSet.getString("Strada");
			        assertEquals(strada,"Padurii5H");
			       
			       
			      }
	}
	
	
	@SuppressWarnings("static-access")
	@Test
	public void testUpdateCourse() throws SQLException {
		
		a=new DatabaseLogicController(conn.getConnection());
		
		String nume="Matematica";
		a.updateCourse(nume, "Ralu", 2);
		
		String query="select c.nume as Nume, c.profesor as Profesor, c.an_studiu as AnStudiu\n" +
	    		"from curs c\n" +
	    		"where c.nume='" +nume+ "';";
		
		        Statement statement = conn.getConnection().createStatement();
		        ResultSet resultSet = statement.executeQuery(query);
		        while (resultSet.next())
			      {
			        String numec=resultSet.getString("Nume");
			        assertEquals(nume,numec);
			        
			        int an=resultSet.getInt("AnStudiu");
			        assertEquals(an,2);
			      }
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testEnrole() throws SQLException {
		
		a=new DatabaseLogicController(conn.getConnection());
		
		int ids=4;
		int idc=1;
		
		a.enroleStudent(ids,idc);
		
		String query= "select i.id_student as is_c,i.id_curs as id_c\n" +
	    		"from inrolare i\n" +
	    		"where i.id_student='" +ids+ "'\n"+
	    		"and i.id_curs='" +idc+ "';";
		
		        Statement statement = conn.getConnection().createStatement();
		        ResultSet resultSet = statement.executeQuery(query);
		        
		        assertNotNull(resultSet); 
	}
	
	
}
	

