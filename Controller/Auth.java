package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import Model.Administrator;
import Model.Instructor;
import Model.Student;
import Model.User;
import View.DashBoards;
import View.StudentDashBoards;
import helper.EmailMismatchedError;

// Creating the class and method to register and login the into the dashboard
public class Auth {
	PreparedStatement pst;
	
	// Register the information of the admin, instructor and student into the database
	public int registration(String logIn, User user, String course, String password) {
		try {
		      Connection connection = ConnectionJDBC.getConnection();
		      String sql = null;
		      // Using condition to insert the data for the admin, instructor and student
		      if(logIn.equalsIgnoreCase("Administrator")) {
			     sql = " INSERT INTO administrator(firstname, lastname, phonenumber, gender, username, password) VALUES (?,?,?,?,?,?)";
		      }else if(logIn.equalsIgnoreCase("Instructor")) {
		    	  sql = "INSERT INTO instructor(firstname, lastname, phonenumber, gender, username, password, course) VALUES (?,?,?,?,?,?,?)";
		      }else {
		    	  sql = "INSERT INTO student(firstname, lastname, phonenumber, gender, username, password, course,level,semester) VALUES (?,?,?,?,?,?,?,?,?)";
		      }
		      
		      pst = connection.prepareStatement(sql);
		      pst.setString(1,user.getFirstname());
		      pst.setString(2, user.getLastname());
		      pst.setString(3, user.getPhonenumber());
		      pst.setString(4, user.getGender());
		      pst.setString(5,  user.getUsername());
		      pst.setString(6, password);
		      if(logIn.equalsIgnoreCase("Instructor")) {
		    	  pst.setString(7, course);
		      }else if(logIn.equalsIgnoreCase("student")) {
		    	  pst.setString(7,course);
		    	  pst.setString(8, "1");
		    	  pst.setString(9, "SEM1");
		      }

		     
		      int row = pst.executeUpdate();
		      return row;
		     
			}catch(Exception e2) {
				System.out.println(e2);
			}
		return 0;
	}
	
//	Creating user class to login the information and shows into the database
	public User login(String role, String selectcourse,String username, String password) {

		   try {
			   // Connection to the database
			   Connection connection = ConnectionJDBC.getConnection();
			   
			   String sql = null;
			   if(role.equalsIgnoreCase("Administrator")) {
				     sql = "select * from administrator where Username = ? and Password = ?";
			      }else if(role.equalsIgnoreCase("Instructor")) {
			    	  sql = "select * from instructor where Username = ? and Password = ?";
			      }else if(role.equalsIgnoreCase("Student")){
			    	  sql = "select * from student where Username = ? and Password = ? and course = ?";
			      }
			   
			   pst = connection.prepareStatement(sql);
			   pst.setString(1, username);
			   pst.setString(2, password);
			   if(role.equalsIgnoreCase("Student")) {
				   pst.setString(3, selectcourse);
			   }
			   
			   ResultSet rs = pst.executeQuery();
			   // Using if condition to check the data
			   if(rs.next()) {
				   if(role.equalsIgnoreCase("Administrator")) {
					   Administrator admin= new Administrator(rs.getInt("id"), rs.getString("firstname"),rs.getString("lastname"), rs.getString("phonenumber"), rs.getString("gender"), rs.getString("password"));
				       return admin;
				   }else if(role.equalsIgnoreCase("Instructor")) {
				    	  Instructor teacher = new Instructor(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("phonenumber"), rs.getString("gender"), rs.getString("username"),rs.getString("password"), rs.getString("course"));
				          return teacher;   
				   }else if(role.equalsIgnoreCase("student")){
				    	  Student student = new Student(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("phonenumber"), rs.getString("gender"), rs.getString("password"), rs.getString("username"),rs.getString("course"),rs.getString("semester"),rs.getString("password"));
                          return student;
				      }
				   							   
			   }else {
//				   Creating the user defined throws function
				   throw new EmailMismatchedError("Username and password doesnot match with the given role"); 
			   }
		   }catch(Exception e1){
			   JOptionPane.showMessageDialog(null,e1.getMessage());
		   }
		return null;
	}
    
}
