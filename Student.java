package studentcourses;

public class Student {
	
	private String name;
	private java.sql.Date birth_date;
	private int id_adress;
	
	public Student(String name, java.sql.Date birth_date, int id_adress)
	{
		this.name=name;
		this.birth_date=birth_date;
		this.id_adress=id_adress;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setName(String name)
	{
		this.name=name;
	}
	
	
	public java.sql.Date getDate()
	{
		return this.birth_date;
	}
	
	public void setName(java.sql.Date birth_date)
	{
		this.birth_date=birth_date;
	}

	public int getAdress()
	{
		return this.id_adress;
	}
	
	public void setAdress(int id_adress)
	{
		this.id_adress=id_adress;
	}
}
