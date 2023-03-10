package Model;
// Using object association of course inside module
import Controller.SelectCourse;

public class Module{
	private int id;
	private Course course;
	private String moduled;
	private String title;
	private String level;
	private String username;
	private String phonenumber;
	public Module(int id, String course, String moduled, String title, String level, String username, String phonenumber) {
		this.id = id;
		SelectCourse sc=new SelectCourse();
		this.course = sc.getCourse(course);
		this.moduled = moduled;
		this.title = title;
		this.level = level;
		this.username = username;
		this.phonenumber = phonenumber;
	}
	public int getId() {
		return id;
	}
	public Course getCourse() {
		return course;
	}
	public String getModuled() {
		return moduled;
	}
	public String getTitle() {
		return title;
	}
	public String getLevel() {
		return level;
	}
	public String getUsername() {
		return username;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
}
