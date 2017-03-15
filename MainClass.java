package studentcourses;
import java.sql.SQLException;
import java.text.ParseException;


public class MainClass { 

	@SuppressWarnings("static-access")
	public static void main(String[] args) throws SQLException, ParseException {
		
		DatabaseConnection conn=new DatabaseConnection();
		DatabaseLogicController a;
		a=new DatabaseLogicController(conn.getConnection());
		
		//String nume="Popescu Mirabela";
		//a.InfoStudent(nume);
		
		
		String numeC="Tehnici de programare";
		a.InfoCourse(numeC);
		
		a.InfoEnrolment(numeC);
		
		//java.util.Date utilDate = new java.util.Date();
	   // java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	    
	   // Student s=new Student(nume,sqlDate,2);
	   // Course c=new Course(numeC,nume,3);
	
	    //a.insertCourse(c);
	    //a.insertStudent(s);
	    
	    //a.updateStudent("Sechel Raluca", "Romania", "Cluj", "Padurii5H");
	    // a.updateCourse(numeC, "Ralu", 2);
	    //a.deleteStudent(nume);
		//a.deleteCourse(numeC);
		
	}

}
