package Model;

//Using Encapsulation to get the data and using inheritance to get the data of the parent class

public class Instructor extends User{
	
	private String course;
	private String password;
	public Instructor(int id, String firstname, String lastname,String phonenumber, String gender, String username, String course, String password){
		super(id, firstname, lastname, phonenumber, gender, username);
		this.course = course;
		this.password = password;
	}
	public String getCourse() {
		return course;
	}
	public String getPassword() {
		return password;
	}
}