


package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Model.Module;

import javax.swing.JOptionPane;
// Using CRUD in Module
public class ModuleCrud  {
	PreparedStatement pre;
	ResultSet rs;
	Connection connection = ConnectionJDBC.getConnection();
	public void insertModule(Module module) {
		try {
			String sql = "INSERT INTO module(course, Moduled, Title, level, Username, Phonenumber)VALUES(?,?,?,?,?,?)";
			pre = connection.prepareStatement(sql);
			pre.setString(1,module.getCourse().getName());
			pre.setString(2,module.getModuled() );
			pre.setString(3,module.getTitle() );
			pre.setString(4,module.getLevel() );
			pre.setString(5,module.getUsername() );
			pre.setString(6, module.getPhonenumber() );
			int insert = pre.executeUpdate();
			if(insert>0) {
				JOptionPane.showMessageDialog(null, "Data insert successfully");
			   
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateModule(Module module) {
		try {		
			String sql = "UPDATE module SET course=?,Moduled=?,Title=?,level=?,Username=?,Phonenumber=? WHERE id=?";
			pre = connection.prepareStatement(sql);
			pre.setString(1,module.getCourse().getName());
			pre.setString(2,module.getModuled() );
			pre.setString(3,module.getTitle() );
			pre.setString(4,module.getLevel() );
			pre.setString(5,module.getUsername() );
			pre.setString(6, module.getPhonenumber() );
			pre.setInt(7,module.getId() );
			int insert = pre.executeUpdate();
			if(insert>0) {
				JOptionPane.showMessageDialog(null, "Update data successfully");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
	}
	
	public void deleteModule(String id) {
			try {			
				String sql = "DELETE FROM module WHERE id='"+id+"'";
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
