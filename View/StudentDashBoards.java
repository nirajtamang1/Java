package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.AbstractButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;

import Controller.ConnectionJDBC;
import Controller.DataTable;
import Model.Student;
import Model.User;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class StudentDashBoards {

	private JFrame frame;
	private CardLayout cl_cards = new CardLayout(0, 0);
	private JPanel cards;
	private JTable table1;
	PreparedStatement pre;
	ResultSet rs;
	private JTable moduletable;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentDashBoards window = new StudentDashBoards(null, null);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */


	public StudentDashBoards(User user, String course) {
		initialize(user);
		loadinstructor(course);
		loadmodule(course);
		
	 
	}
	public void loadinstructor(String cours) {
		//Connecting with the database to load the data of the instructor
		Connection connection = ConnectionJDBC.getConnection();
		try {
			pre = connection.prepareStatement("select firstname, lastname, phonenumber,username from instructor where course = '"+cours+"'");
			
			rs = pre.executeQuery();
			System.out.println(rs);
			table1.setModel(DataTable.resultSetToTableModel(rs));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	public void loadmodule(String cours) {
		//Connecting with the database to load the module
		Connection connection = ConnectionJDBC.getConnection();
		try {
			pre = connection.prepareStatement("select course, Moduled, Title,Username, Phonenumber from module where course = '"+cours+"'");
			rs = pre.executeQuery();
			//Calling the setModule result to  display the data into the jtable
			moduletable.setModel(DataTable.resultSetToTableModel(rs));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(User user) {
		frame = new JFrame();
		frame.setBounds(100, 100, 826, 524);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(0);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(splitPane, GroupLayout.DEFAULT_SIZE, 781, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(splitPane, GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE))
		);
		
		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		
		JButton myinfo = new JButton("My Result");
		myinfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl_cards.show(cards, "myinfo");
			}
		});
		
		JButton btnInstructor = new JButton("Instructor");
		btnInstructor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl_cards.show(cards, "instructor");
			}
		});
		
		JButton btnModule = new JButton("Module");
		btnModule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl_cards.show(cards, "module");
			}
		});
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(myinfo)
						.addComponent(btnInstructor, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnModule, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(128)
					.addComponent(myinfo)
					.addGap(18)
					.addComponent(btnInstructor)
					.addGap(18)
					.addComponent(btnModule)
					.addGap(18)
					.addComponent(btnLogout)
					.addContainerGap(209, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		cards = new JPanel();
		splitPane.setRightComponent(cards);
		cards.setLayout(cl_cards);
		
		JPanel Myinfo = new JPanel();
		Myinfo.setBackground(new Color(255, 255, 255));
		cards.add(Myinfo, "myinfo");
		
		JLabel lblNewLabel = new JLabel("My information");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		
		GroupLayout gl_Myinfo = new GroupLayout(Myinfo);
		gl_Myinfo.setHorizontalGroup(
			gl_Myinfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Myinfo.createSequentialGroup()
					.addGroup(gl_Myinfo.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_Myinfo.createSequentialGroup()
							.addGap(254)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_Myinfo.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 643, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(36, Short.MAX_VALUE))
		);
		gl_Myinfo.setVerticalGroup(
			gl_Myinfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Myinfo.createSequentialGroup()
					.addGap(30)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addGap(48)
					.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(163, Short.MAX_VALUE))
		);
		
		table = new JTable();
		//Showing the result of the the student
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Student ID", "Name", "Username", "Email", "Course"
				}
			));
			
			try { 
		        
                Connection connection = ConnectionJDBC.getConnection(); 
                // Using query to get the student marks of the specific student
                String query = "SELECT * from marks where name = '"+user.getFirstname()+"'"; 
	            PreparedStatement pre = connection.prepareStatement(query);
	            
	            ResultSet resultSet = pre.executeQuery();
	            DefaultTableModel model = (DefaultTableModel) table.getModel();
	            while(resultSet.next()) {
	            	int Id = resultSet.getInt("id");
	            	String name = resultSet.getString("name"); 
	            	String course = resultSet.getString("course");         	
	            	String level = resultSet.getString("level");            	
	            	String marks = resultSet.getString("marks");
	            	
	            	Object row[] = {Id, name, course, level, marks};
	            	model.addRow(row);
	            }
			}catch(Exception e) {
				System.out.println(e);
			}
		scrollPane_2.setViewportView(table);
		Myinfo.setLayout(gl_Myinfo);
		
		JPanel instructor = new JPanel();
		instructor.setBackground(new Color(255, 255, 255));
		cards.add(instructor, "instructor");
		
		JLabel lblMyInstructor = new JLabel("My instructor");
		lblMyInstructor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_instructor = new GroupLayout(instructor);
		gl_instructor.setHorizontalGroup(
			gl_instructor.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_instructor.createSequentialGroup()
					.addGroup(gl_instructor.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_instructor.createSequentialGroup()
							.addGap(237)
							.addComponent(lblMyInstructor, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_instructor.setVerticalGroup(
			gl_instructor.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_instructor.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblMyInstructor, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addGap(37)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(53, Short.MAX_VALUE))
		);
		
		table1 = new JTable();
		scrollPane.setViewportView(table1);
		instructor.setLayout(gl_instructor);
		
		JPanel module = new JPanel();
		module.setBackground(new Color(255, 255, 255));
		cards.add(module, "module");
		
		JLabel lblMyModule = new JLabel("My module");
		lblMyModule.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout gl_module = new GroupLayout(module);
		gl_module.setHorizontalGroup(
			gl_module.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_module.createSequentialGroup()
					.addGroup(gl_module.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_module.createSequentialGroup()
							.addGap(231)
							.addComponent(lblMyModule, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_module.createSequentialGroup()
							.addGap(29)
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 637, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		gl_module.setVerticalGroup(
			gl_module.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_module.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblMyModule, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 336, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(74, Short.MAX_VALUE))
		);
		
		moduletable = new JTable();
		scrollPane_1.setViewportView(moduletable);
		module.setLayout(gl_module);
		frame.getContentPane().setLayout(groupLayout);
		frame.setVisible(true);
	}
}
