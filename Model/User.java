package Model;
// Creating encapsulation to get the data of the user
public class User {
	private int id;
	private String firstname;
	private String lastname;
	private String phonenumber;
	private String gender;
	private String username;
	public User(int id, String firstname, String lastname,String phonenumber, String gender, String username){
		this.id= id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.phonenumber = phonenumber;
		this.gender = gender;
		this.username = username;
	}
	public int getId() {
		return id;
	}
	public String getFirstname() {
		return firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public String getGender() {
		return gender;
	}
	public String getUsername() {
		return username;
	}
}
