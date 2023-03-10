package Controller;


// Showing the data of all the table though the rs and return the tablemodle
	import java.sql.ResultSet;
	import java.sql.ResultSetMetaData;
	import java.util.Vector;
	import javax.swing.table.DefaultTableModel;
	import javax.swing.table.TableModel;
	public class DataTable {
		
	    public static TableModel resultSetToTableModel(ResultSet rs) {
	        try {
	            ResultSetMetaData rsmd = rs.getMetaData();
	            int numberOfColumns = rsmd.getColumnCount();
	         
	            Vector columntitle = new Vector();
                // Used to get the column name
	            for (int column = 1; column <= numberOfColumns; column++) {
	                columntitle.addElement(rsmd.getColumnLabel(column));
	            }
                // 
	            Vector rows = new Vector();

	            while (rs.next()) {
	                Vector newRow = new Vector();

	                for (int i = 1; i <= numberOfColumns; i++) {
	                    newRow.addElement(rs.getObject(i));
	                }

	                rows.addElement(newRow);
	            }
	            return new DefaultTableModel(rows, columntitle);
	        } catch (Exception e) {
	            e.printStackTrace();

	            return null;
	        }
	    }
	}

