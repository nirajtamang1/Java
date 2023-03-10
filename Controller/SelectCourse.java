package Controller;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Course;
//Creaing the list to show into the other combox box of the active course
public class SelectCourse {
	public List<Course> course(){
    	List<Course> courselist=new ArrayList();
    	String sql = "select * from course where isactive='True'";
    	Connection connection = ConnectionJDBC.getConnection();
    	try {
			PreparedStatement pre = connection.prepareStatement(sql);
			ResultSet rs = pre.executeQuery();
			while(rs.next()) {
				Course course = new Course(rs.getString("id"), rs.getString("course"), rs.getString("Years"), rs.getString("isActive"));
				courselist.add(course);
			}
			return courselist;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    	
    }
	
	// selecting the name of the course and show in in checkbox
	public Course getCourse(String name){
    	String sql = "select * from course where course='"+name+"'";
    	Connection connection = ConnectionJDBC.getConnection();
    	try {
			PreparedStatement pre = connection.prepareStatement(sql);
			ResultSet rs = pre.executeQuery();
			if(rs.next()) {
				Course course = new Course(rs.getString("id"), rs.getString("course"), rs.getString("Years"), rs.getString("isActive"));
				return course;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }

}
