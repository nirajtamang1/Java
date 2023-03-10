package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Model.Instructor;
import Model.Student;
import View.DashBoards;

//Creating the CRUD Function for the instructor and student

public class CRUD {
	PreparedStatement pre;
	ResultSet rs;
	Connection connection = ConnectionJDBC.getConnection();
	public void insertInstructor(Instructor instructor) {
		try {
			String sql = "INSERT INTO instructor(firstname, lastname, phonenumber, gender, username, course, password) VALUES (?,?,?,?,?,?,?)";
			pre = connection.prepareStatement(sql);		
			pre.setString(1,instructor.getFirstname());
			pre.setString(2, instructor.getLastname());
			pre.setString(3, instructor.getPhonenumber());
			pre.setString(4, instructor.getGender());
			pre.setString(5, instructor.getUsername());		
			pre.setString(6, instructor.getCourse());
			pre.setString(7, instructor.getPassword());
			int insert = pre.executeUpdate();
			if(insert>0) {
				JOptionPane.showMessageDialog(null, "Data insert successfully");		   
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateInstructor(Instructor instructor) {
		try {	
			String sql = "UPDATE instructor SET firstname=?,lastname=?,phonenumber=?,gender=?,username=?,course=? WHERE id = ?";
			pre = connection.prepareStatement(sql);
			pre.setString(1,instructor.getFirstname());
			pre.setString(2, instructor.getLastname());
			pre.setString(3, instructor.getPhonenumber());
			pre.setString(4, instructor.getGender());
			pre.setString(5, instructor.getUsername());
			pre.setString(6, instructor.getCourse());
			pre.setInt(7, instructor.getId());
			int insert = pre.executeUpdate();
			if(insert>0) {
				JOptionPane.showMessageDialog(null, "Data Update successfully");
			   
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
	}
	}
	
	public void deleteInstructor(String id) {
			try {
				String sql = "DELETE FROM instructor WHERE id = '"+id+"'";
				pre = connection.prepareStatement(sql);
				int delete = pre.executeUpdate();
				if(delete>0) {
					JOptionPane.showMessageDialog(null, "Data Delete successfully");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			
		}
	}
		
	public void insertStudent(Student student) {	   
		try {
			String sql = "INSERT INTO student(firstname, lastname, phonenumber, gender, username, course, level, semester, password) VALUES (?,?,?,?,?,?,?,?,?)";
			pre = connection.prepareStatement(sql);
			pre.setString(1, student.getFirstname());
			pre.setString(2, student.getLastname());
			pre.setString(3, student.getPhonenumber());
			pre.setString(4, student.getGender());
			pre.setString(5, student.getUsername());	
			pre.setString(6, student.getCourse());
			pre.setString(7, student.getlevel());
			pre.setString(8, student.getsemester());
			pre.setString(9, student.getpassword());
			int insert = pre.executeUpdate();
			if(insert>0) {
				JOptionPane.showMessageDialog(null, "Data insert successfully");	   
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
	}
	public void updateStudent(Student student) {
		try {
			String sql = "UPDATE student SET firstname=?,lastname=?,phonenumber=?,gender=?,username=?,course=?,level = ?,semester= ? WHERE id = ?;";
			pre = connection.prepareStatement(sql);
			pre.setString(1,student.getFirstname());
			pre.setString(2, student.getLastname());
			pre.setString(3, student.getPhonenumber());
			pre.setString(4, student.getGender());
			pre.setString(5, student.getUsername());
			pre.setString(6, student.getCourse());	
			pre.setString(7, student.getlevel());
			pre.setString(8, student.getsemester());
			pre.setInt(9, student.getId());
			int insert = pre.executeUpdate();
			if(insert>0) {
				JOptionPane.showMessageDialog(null, "Data Update successfully");			   
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
	}
	
	public void deleteStudent(String id) {
		try {
			
			String sql = "DELETE FROM student WHERE id = '"+id+"'";
			pre = connection.prepareStatement(sql);
			int delete = pre.executeUpdate();
			if(delete>0) {
				JOptionPane.showMessageDialog(null, "Data Delete successfully");
			   
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
	}	
}
