package app;

import com.amazonaws.util.json.JSONArray;

public class ClassDataManager {
	private String course;
	private String title;
	
	public ClassDataManager(String course, String title)
	{
		this.course = course;
		this.title = title;
	}
	
	public ClassDataManager()
	{}
	
	public String getCourseNumber()
	{
		return course;
	}
	
	public String getClassTitle()
	{
		return title;
	}
	
	public void setCourseNumber(String courseNumber)
	{
		course = courseNumber;
	}
	
	public void setClassTitle(String title)
	{
		this.title = title;
	}
	
}
