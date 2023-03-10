package Model;
//Using Encapsulation to get the data and extend the property of the the user
public class Administrator extends User{
	public Administrator(int id, String firstname, String lastname,String phonenumber, String gender, String username){
		super(id,firstname, lastname, phonenumber, gender, username);
	}
}
