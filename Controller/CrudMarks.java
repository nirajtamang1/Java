package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Model.Marks;
// Crud function for the marks
public class CrudMarks {
    PreparedStatement pre;
	ResultSet rs;
	Connection connection = ConnectionJDBC.getConnection();
	public void insertMarks(String name, String course, String level, String marks) {	   
		try {
			String sql = "INSERT INTO marks(name,course,level,marks) VALUES (?,?,?,?)";
			pre = connection.prepareStatement(sql);
			pre.setString(1,name);
			pre.setString(2, course);
			pre.setString(3, level);
			pre.setString(4, marks);
			int insert = pre.executeUpdate();
			if(insert>0) {
				JOptionPane.showMessageDialog(null, "Data insert successfully");  
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateMarks(String id, String name, String course, String level, String mark) {
		try {		
			String sql = "UPDATE marks SET name=?,course=?,level=?,marks=? WHERE id=?";
			pre = connection.prepareStatement(sql);
			pre.setString(1,name);
			pre.setString(2, course);
			pre.setString(3, level);
			pre.setString(4,mark);
			pre.setString(5, id);	
			int insert = pre.executeUpdate();
			if(insert>0) {
				JOptionPane.showMessageDialog(null, "Update data successfully");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
	}
	
	public void deleteMarks(String id) {
		System.out.println(id);
		System.out.println("Hello everyone");
			try {			
				String sql = "DELETE FROM marks WHERE  id='"+id+"'";
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