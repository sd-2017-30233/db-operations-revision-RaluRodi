package studentcourses;

public class Course {
	
	private String name;
	private String prof;
	private int year;
	
	public Course(String name, String prof, int year)
	{
		this.name=name;
		this.prof=prof;
		this.year=year;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setName(String name)
	{
		this.name=name;
	}
	
	public String getProf()
	{
		return this.prof;
	}
	
	public void setProf(String prof)
	{
		this.prof=prof;
	}
	
	public int getYear()
	{
		return this.year;
	}
	
    public void setYear(int year)
    {
    	this.year=year;
    }

}
