package Model;
//Using the inheritance to access the data of parent class user
// Super function used to call the constructor of the parent class
public class Student extends User{
	
	private String course;
	private String level;
	private String semester;
	private String password;
	public Student(int id, String firstname, String lastname,String phonenumber, String gender, String username, String course, String level, String semester, String password){
		super(id, firstname, lastname, phonenumber, gender, username);
		this.course = course;
		this.level = level;
		this.semester = semester;
		this.password = password;
	}
	public String getCourse() {
		return course;
	}
	public String getlevel() {
		return level;
	}
	public String getsemester() {
		return semester;
	}
	public String getpassword() {
		return password;
	}
}


