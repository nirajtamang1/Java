package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import Model.Course;
// Using CRUD (Create Read Update Delete) Operation for the course
public class CourseCrud {
	// Storing preparedStatement interface as pre and ResultSet as rs
	PreparedStatement pre;
	ResultSet rs;
	Connection connection = ConnectionJDBC.getConnection();
	public void insertCourse(Course course) {	   
		try {
			String sql = "INSERT INTO course(course, Years, isActive) VALUES (?,?,?)";
			pre = connection.prepareStatement(sql);
			pre.setString(1,course.getName());
			pre.setString(2, course.getYears());
			pre.setString(3, course.getisActive());			
			int insert = pre.executeUpdate();
			if(insert>0) {
				JOptionPane.showMessageDialog(null, "Data insert successfully");			   
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateCourse(Course course) {
		try {			
			String sql = "UPDATE course SET course=?,Years=?,isActive = ? WHERE id=? ";
			pre = connection.prepareStatement(sql);
			pre.setString(1,course.getName());
			pre.setString(2, course.getYears());
			pre.setString(3, course.getisActive());
			pre.setString(4,course.getId());
			
			
			int update = pre.executeUpdate();
			if(update>0) {
				JOptionPane.showMessageDialog(null, "Update data successfully"); 
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
	}
	}
	public void deleteCourse(String id) {
			try {
				String sql = "DELETE FROM course WHERE  id='"+id+"'";	
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
