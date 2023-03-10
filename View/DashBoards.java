package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.TableModel;

import com.mysql.cj.PerConnectionLRUFactory;

import Controller.CRUD;
import Controller.ConnectionJDBC;
import Controller.CourseCrud;
import Controller.CrudMarks;
import Controller.DataTable;
import Controller.ModuleCrud;
import Controller.SelectCourse;
import Model.Course;
import Model.Instructor;
import Model.Student;
import Model.User;

import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.DefaultTableModel;
import Model.Module;
public class DashBoards extends JFrame{

	private JFrame frmCourseManagementSystem;
	private JPanel cardLayout;
    private CardLayout card = new CardLayout(0, 0);
    private JSplitPane splitPane;
    private JTextField firstname;
    private JTextField lastname;
    private JTextField phonenumber;
    private JTextField usernam;
    private JTable table;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private JComboBox comboBox;
    private JRadioButton male;
    private JRadioButton female;
    PreparedStatement pre;
    ResultSet rs;
    private JTextField id;
    private JTextField sfirstname;
    private JTextField slastname;
    private JTextField susername;
    private JTextField sphonenumber;
    private JTextField sid;
    private JTable tablestudent;
    private JRadioButton smale;
    private JRadioButton sfemale;
    private JPanel panel_1;
    private JTable course;
    private JTextField courses;
    private JTable moduleTable;
    private JTextField mmodules;
    private JTextField mtitles;
    private JTextField myears;
    private JTextField musername;
    private JTextField mphonenumber;
    private JTextField mid;
    private JPanel marks;
    private JTable markstable;
    private JTextField name;
    private JTextField level;
    private JTextField mark;
    private JTextField mids;
    private JComboBox sem;
    private JComboBox syear;
    private JComboBox cyear;
    private JComboBox isactive;
    private JTextField cid;
    private JComboBox mcourses;
    private JPanel username_1;
    private JPanel panel_2;
    private JPanel panel_3;
    String sql  = null;
    private JButton printmarks;
    private JButton back;
    private JComboBox mcourse;
    private JTextField password;
    private JTextField textField;
    private JLabel lblPassword;
    private JButton btnNewButton_3;
    private JButton btnNewButton_3_1;
    private JButton btnNewButton_3_2;
    private JLabel lblNewLabel_3_5_1;
//    private String role;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DashBoards window = new DashBoards();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */

	public DashBoards() {

		initialize();
		// Showing the data into jtable of administrator and teacher
		loadData();
		//Calling the data of the student
		loadStudents();		
		// Calling the function of loadCourse and Display into the Jtable
	    loadCourse();
	    loadModule();
	    loadMarks();
	   
	    
	}

   //It load the data of administrator and instructor in jtable
	public void loadData() {
	Connection connection = ConnectionJDBC.getConnection();
	try {

		sql = "select id,firstname, lastname, phonenumber, gender, username, course from instructor";
		pre = connection.prepareStatement(sql);
		rs = pre.executeQuery();
		table.setModel(DataTable.resultSetToTableModel(rs));

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	public void loadStudents() {
		Connection connection = ConnectionJDBC.getConnection();
		try {
			sql = "select id, firstname, lastname, phonenumber, gender, username,level,semester from student";
			pre = connection.prepareStatement(sql);
			rs = pre.executeQuery();
			tablestudent.setModel(DataTable.resultSetToTableModel(rs));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

	public void loadCourse() {
			Connection connection = ConnectionJDBC.getConnection();
			try {
				pre = connection.prepareStatement("select * from course");
				rs = pre.executeQuery();
				course.setModel(DataTable.resultSetToTableModel(rs));


			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
	public void loadModule() {
		Connection connection = ConnectionJDBC.getConnection();
		try {
			pre = connection.prepareStatement("select * from module");
			rs = pre.executeQuery();
			moduleTable.setModel(DataTable.resultSetToTableModel(rs));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	public void loadMarks() {
		Connection connection = ConnectionJDBC.getConnection();
		try {
			pre = connection.prepareStatement("select * from marks");
			rs = pre.executeQuery();
			markstable.setModel(DataTable.resultSetToTableModel(rs));
			clearMarks();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

	
//	it clear all the data of the text field
	public void clearData(){
		firstname.setText("");
		lastname.setText("");
		phonenumber.setText("");
		usernam.setText("");
		comboBox.setSelectedIndex(0);
	}
	public void clearCourse() {
		courses.setText("");
		cyear.setSelectedIndex(0);
		isactive.setSelectedIndex(0);
		
	}
	
	public void clearModule() {
		mmodules.setText("");
		myears.setText("");
		mtitles.setText("");
		mphonenumber.setText("");
		musername.setText("");
	}
   
	public void clearMarks() {
		name.setText("");
		level.setText("");
		mcourse.setSelectedItem(0);
		mark.setText("");
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCourseManagementSystem = new JFrame();
		frmCourseManagementSystem.setTitle("Course Management System");
		frmCourseManagementSystem.setBounds(100, 100, 1087, 528);
		frmCourseManagementSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		splitPane = new JSplitPane();
		splitPane.setDividerSize(0);
		GroupLayout groupLayout = new GroupLayout(frmCourseManagementSystem.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(splitPane, GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(splitPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
		);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		splitPane.setLeftComponent(panel);
		
		JButton teacherbutton = new JButton("Teacher");
		teacherbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(cardLayout,"teacher");

			}
		});
		
		JButton studentButton = new JButton("Student");
		studentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(cardLayout,"student");
				
			}
		});
		
		JButton btnNewButton = new JButton("Module");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(cardLayout,"module");
			}
		});
		
		JButton btnNewButton_1 = new JButton("Course");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(cardLayout, "course");
			}
		});
		
		JButton logout = new JButton("Logout");
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Login();
				frmCourseManagementSystem.setVisible(false);

			}
		});
		
		JButton btnMarks = new JButton("Marks");
		btnMarks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(cardLayout, "marks");
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(teacherbutton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(studentButton)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1)
						.addComponent(btnMarks, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(logout, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(145)
					.addComponent(teacherbutton)
					.addGap(18)
					.addComponent(studentButton)
					.addGap(18)
					.addComponent(btnNewButton_1)
					.addGap(15)
					.addComponent(btnNewButton)
					.addGap(18)
					.addComponent(btnMarks)
					.addGap(33)
					.addComponent(logout)
					.addContainerGap(138, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		cardLayout = new JPanel();
		cardLayout.setBackground(new Color(0, 128, 64));
		splitPane.setRightComponent(cardLayout);
		cardLayout.setLayout(card);
		
		JPanel teacher = new JPanel();
		teacher.setBackground(new Color(0, 128, 64));
		cardLayout.add(teacher, "teacher");
		
		username_1 = new JPanel();
		username_1.setBackground(new Color(0, 128, 64));
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_teacher = new GroupLayout(teacher);
		gl_teacher.setHorizontalGroup(
			gl_teacher.createParallelGroup(Alignment.LEADING)
				.addComponent(username_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 998, Short.MAX_VALUE)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 998, Short.MAX_VALUE)
		);
		gl_teacher.setVerticalGroup(
			gl_teacher.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_teacher.createSequentialGroup()
					.addComponent(username_1, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(23, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setDefaultEditor(Object.class,null);
//      Displaying the data jtextfield and JcomboBox
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				TableModel model = table.getModel();
                
				id.setText(model.getValueAt(index, 0).toString());
				firstname.setText(model.getValueAt(index, 1).toString());
				lastname.setText(model.getValueAt(index, 2).toString());
				phonenumber.setText(model.getValueAt(index, 3).toString());
				String sex = model.getValueAt(index, 4).toString();
				if(sex.equalsIgnoreCase("male")) {
					male.setSelected(true);
				}else {
					female.setSelected(true);
				}
				usernam.setText(model.getValueAt(index, 5).toString());
				String course = model.getValueAt(index, 6).toString();
				switch(course){

				case "BSCHons)Computer Science":
					comboBox.setSelectedIndex(0);
				case "BIBM":
					comboBox.setSelectedIndex(1);

				}

			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		firstname = new JTextField();
		firstname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		firstname.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setForeground(Color.WHITE);
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblNewLabel_3_2 = new JLabel("Phonenumber");
		lblNewLabel_3_2.setForeground(Color.WHITE);
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblNewLabel_3_3 = new JLabel("Gender");
		lblNewLabel_3_3.setForeground(Color.WHITE);
		lblNewLabel_3_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblNewLabel_3_4 = new JLabel("Course");
		lblNewLabel_3_4.setForeground(Color.WHITE);
		lblNewLabel_3_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		lastname = new JTextField();
		lastname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lastname.setColumns(10);
		
		phonenumber = new JTextField();
		phonenumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		phonenumber.setColumns(10);
		
		usernam = new JTextField();
		usernam.setFont(new Font("Tahoma", Font.PLAIN, 14));
		usernam.setColumns(10);
		
		male = new JRadioButton("Male");
		buttonGroup.add(male);
		male.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		female = new JRadioButton("Female");
		buttonGroup.add(female);
		female.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		comboBox = new JComboBox();
		SelectCourse sr = new SelectCourse();
		List<Course> courseList=sr.course();
		String[] courseName=new String[courseList.size()];
		int index=0;
		for(Course course:courseList) {
			courseName[index]=course.getName();
			index++;
			}
		
		
		comboBox.setModel(new DefaultComboBoxModel(courseName));
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton addbtn = new JButton("Add");
         // Adding the data into the database and showin into the JTabel
		addbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String firstName = firstname.getText().trim();
				String lastName = lastname.getText().trim();
				String phoneNumber = phonenumber.getText().trim();
				String Gender = "male";
				if(female.isSelected()) {
					Gender = "female";
				}
				String username = usernam.getText().trim();
				String course = comboBox.getSelectedItem().toString();
				String passwords = password.getText();
                
                if(firstName == "" || lastName == "" || phoneNumber == "" || course == ""||username == "") {
                	JOptionPane.showMessageDialog(null, "Data not inserted");
                }else {
                Instructor instructor=new Instructor(0,firstName,lastName,phoneNumber,Gender,username,course, passwords);
				CRUD cd = new CRUD();
				
				
				cd.insertInstructor(instructor);
				loadData();
				clearData();
                }
				
			}
		});
		addbtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
//		Update the instructor information
		JButton updatebtn = new JButton("Update");
		updatebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id1 = id.getText();
				String firstName = firstname.getText().trim();
				String lastName = lastname.getText().trim();
				String phoneNumber = phonenumber.getText().trim();
				String Gender = "male";
				if(female.isSelected()) {
					Gender = "female";
				}
				String username = usernam.getText().trim();
				String course = comboBox.getSelectedItem().toString();
				String passwords = "";
				Instructor instructor=new Instructor(Integer.parseInt(id1),firstName,lastName,phoneNumber,Gender,username,course, passwords);
				CRUD cd = new CRUD();
				cd.updateInstructor(instructor);
				loadData();
				clearData();
			}
		});
		updatebtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		//Deleter the instructor information from the database
		JButton deletebtn = new JButton("Delete");
		deletebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CRUD cd = new CRUD();
				String id1 = id.getText();
				cd.deleteInstructor(id1);
				loadData();
				clearData();
			}
		});
		deletebtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton clearbtn = new JButton("Clear");
		clearbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearData();
			}
		});
		clearbtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNewLabel_3_3_1 = new JLabel("id");
		lblNewLabel_3_3_1.setVisible(false);
		lblNewLabel_3_3_1.setForeground(Color.WHITE);
		lblNewLabel_3_3_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		id = new JTextField();
		id.setVisible(false);
		id.setVerifyInputWhenFocusTarget(false);
		id.setFont(new Font("Tahoma", Font.PLAIN, 14));
		id.setColumns(10);
		
		password = new JTextField();
		password.setFont(new Font("Tahoma", Font.PLAIN, 14));
		password.setColumns(10);
		
		lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GroupLayout gl_username_1 = new GroupLayout(username_1);
		gl_username_1.setHorizontalGroup(
			gl_username_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_username_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_username_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_username_1.createSequentialGroup()
							.addComponent(addbtn)
							.addGap(27)
							.addComponent(updatebtn)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(deletebtn, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_username_1.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(firstname, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_username_1.createSequentialGroup()
							.addComponent(lblLastName, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lastname, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_username_1.createSequentialGroup()
							.addGroup(gl_username_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_username_1.createParallelGroup(Alignment.LEADING)
								.addComponent(password, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
								.addComponent(usernam, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_username_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_username_1.createSequentialGroup()
							.addGap(40)
							.addGroup(gl_username_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_username_1.createSequentialGroup()
									.addComponent(lblNewLabel_3_2, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(phonenumber, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_username_1.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_username_1.createSequentialGroup()
										.addComponent(lblNewLabel_3_3, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(male, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(female))
									.addGroup(gl_username_1.createSequentialGroup()
										.addGroup(gl_username_1.createParallelGroup(Alignment.LEADING)
											.addComponent(lblNewLabel_3_4, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblNewLabel_3_3_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_username_1.createParallelGroup(Alignment.LEADING)
											.addComponent(id, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
											.addComponent(comboBox, 0, 356, Short.MAX_VALUE)))))
							.addContainerGap(237, Short.MAX_VALUE))
						.addGroup(gl_username_1.createSequentialGroup()
							.addComponent(clearbtn, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(647, Short.MAX_VALUE))))
		);
		gl_username_1.setVerticalGroup(
			gl_username_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_username_1.createSequentialGroup()
					.addGroup(gl_username_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_username_1.createSequentialGroup()
							.addGap(56)
							.addGroup(gl_username_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(firstname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(25)
							.addGroup(gl_username_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblLastName, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
								.addComponent(lastname, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_username_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
								.addComponent(usernam, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_username_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(password, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_username_1.createSequentialGroup()
							.addGap(55)
							.addGroup(gl_username_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_3_2, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
								.addComponent(phonenumber, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_username_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_3_3, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
								.addComponent(male)
								.addComponent(female))
							.addGap(18)
							.addGroup(gl_username_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_3_4, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_username_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(id, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_3_3_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))))
					.addGap(29)
					.addGroup(gl_username_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(addbtn)
						.addComponent(updatebtn, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(deletebtn, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(clearbtn, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(18, Short.MAX_VALUE))
		);
		username_1.setLayout(gl_username_1);
		teacher.setLayout(gl_teacher);
		
		JPanel student = new JPanel();
		student.setBackground(new Color(0, 128, 64));
		cardLayout.add(student, "student");
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 128, 64));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout gl_student = new GroupLayout(student);
		gl_student.setHorizontalGroup(
			gl_student.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(gl_student.createSequentialGroup()
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 998, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_student.setVerticalGroup(
			gl_student.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_student.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
					.addGap(18))
		);
		
		tablestudent = new JTable();
		tablestudent.setDefaultEditor(Object.class,null);
		
		tablestudent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tablestudent.getSelectedRow();
				TableModel model = tablestudent.getModel();

				// id firstname lastname phonenumber, gender, username
				sid.setText(model.getValueAt(index, 0).toString());
				sfirstname.setText(model.getValueAt(index, 1).toString());
				slastname.setText(model.getValueAt(index, 2).toString());
				sphonenumber.setText(model.getValueAt(index, 3).toString());
				String ssex = model.getValueAt(index, 4).toString();
				if(ssex.equalsIgnoreCase("male")) {
					smale.setSelected(true);
				}else {
					sfemale.setSelected(true);
				}
				susername.setText(model.getValueAt(index, 5).toString());
				String scourse = model.getValueAt(index, 6).toString();
//				Using switch condition to show form the comboxbox updated by the user
				switch(scourse){
				case "BSC (Hons) Computer Science":
					comboBox.setSelectedIndex(0);
				case "BIBM":
					comboBox.setSelectedIndex(1);

				}
				String levels = syear.getSelectedItem().toString();
				switch(levels) {
				case "1":
					syear.setSelectedIndex(0);
				case "2":
					syear.setSelectedIndex(1);
				case "3":
					syear.setSelectedIndex(2);
				}
				String sems = sem.getSelectedItem().toString();
				if(sems == "SEM1") {
					sem.setSelectedIndex(0);
				}else {
					sem.setSelectedIndex(1);
				}
			}
		});
		scrollPane_1.setViewportView(tablestudent);
		
		JLabel lblNewLabel_1 = new JLabel("First Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		
		JLabel jlabelt = new JLabel("Last Name");
		jlabelt.setForeground(Color.WHITE);
		jlabelt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblNewLabel_1_2 = new JLabel("User Name");
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblNewLabel_1_4 = new JLabel("Phone Number");
		lblNewLabel_1_4.setForeground(Color.WHITE);
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblNewLabel_1_5 = new JLabel("Gender");
		lblNewLabel_1_5.setForeground(Color.WHITE);
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblNewLabel_1_6 = new JLabel("");
		lblNewLabel_1_6.setForeground(Color.WHITE);
		lblNewLabel_1_6.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblNewLabel_1_7 = new JLabel("id");
		lblNewLabel_1_7.setVisible(false);
		lblNewLabel_1_7.setForeground(Color.WHITE);
		lblNewLabel_1_7.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		sfirstname = new JTextField();
		sfirstname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sfirstname.setColumns(10);
		
		JLabel lblNewLabel_1_5_1 = new JLabel("Course");
		lblNewLabel_1_5_1.setForeground(Color.WHITE);
		lblNewLabel_1_5_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		slastname = new JTextField();
		slastname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		slastname.setColumns(10);
		
		susername = new JTextField();
		susername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		susername.setColumns(10);
		
		sphonenumber = new JTextField();
		sphonenumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sphonenumber.setColumns(10);
		
		sid = new JTextField();
		sid.setVisible(false);
		sid.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sid.setColumns(10);
		
		JComboBox comboBox_1 = new JComboBox();
		
		comboBox_1.setModel(new DefaultComboBoxModel(courseName));
		
		smale = new JRadioButton("male");
		buttonGroup.add(smale);
		smale.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		sfemale = new JRadioButton("female");
		buttonGroup.add(sfemale);
		sfemale.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton addStudent = new JButton("Add");
		//Add the information of the student
		addStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String firstName = sfirstname.getText().trim();
				String lastName = slastname.getText().trim();
				String phoneNumber = sphonenumber.getText().trim();
				String Gender = "male";
				if(female.isSelected()) {
					Gender = "female";
				}
				String username = susername.getText().trim();
				String course = comboBox_1.getSelectedItem().toString();
				String years = syear.getSelectedItem().toString();
				String sems = sem.getSelectedItem().toString();
                String passwords = password.getText();
				CRUD cd = new CRUD();
				Student stdudent = new Student(0,firstName, lastName, phoneNumber, Gender, username, course, years, sems, passwords);
				cd.insertStudent(stdudent);
				loadStudents();
				clearData();
			}
		});
		addStudent.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnUpdate = new JButton("Update");
//		Update the information of the student 
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = sid.getText();
				String firstName = sfirstname.getText().trim();
				String lastName = slastname.getText().trim();
				String phoneNumber = sphonenumber.getText().trim();
				String Gender = "male";
				if(female.isSelected()) {
					Gender = "female";
				}
				String username = susername.getText().trim();
				String course = comboBox_1.getSelectedItem().toString();
				String years = syear.getSelectedItem().toString();
				String sems = sem.getSelectedItem().toString();
                String password = "";
				CRUD cd = new CRUD();
				Student student = new Student(Integer.parseInt(id), firstName, lastName, phoneNumber, Gender, username, course, years, sems,password);

				cd.updateStudent(student);
				loadStudents();
			
				clearData();
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		//Delete the information of the student
		JButton sdelete = new JButton("Delete");
		sdelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = sid.getText();
				CRUD cd = new CRUD();
				cd.deleteStudent(id);
				loadStudents();

				clearData();
			}
		});
		sdelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnClear = new JButton("Clear");
		// Clear the information of the field
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearData();
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		syear = new JComboBox();
		syear.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3"}));
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Level");
		lblNewLabel_1_3_1.setForeground(Color.WHITE);
		lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblNewLabel_1_3_1_1 = new JLabel("Semester");
		lblNewLabel_1_3_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_3_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		sem = new JComboBox();
		sem.setModel(new DefaultComboBoxModel(new String[] {"SEM1", "SEM2"}));
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Password");
		lblNewLabel_1_2_1.setForeground(Color.WHITE);
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setColumns(10);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(jlabelt, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_1.createSequentialGroup()
											.addComponent(addStudent)
											.addGap(18)
											.addComponent(btnUpdate)
											.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
											.addComponent(sdelete)
											.addGap(57))
										.addGroup(gl_panel_1.createSequentialGroup()
											.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
												.addComponent(lblNewLabel_1_4, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNewLabel_1_2, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
												.addComponent(susername, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
												.addComponent(slastname, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
												.addComponent(sphonenumber, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
												.addComponent(sfirstname, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
												.addComponent(textField, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE))
											.addGap(41)))
									.addGroup(gl_panel_1.createSequentialGroup()
										.addComponent(lblNewLabel_1)
										.addGap(232)))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblNewLabel_1_2_1, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(8)
									.addComponent(lblNewLabel_1_5, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(smale, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(sfemale, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_panel_1.createSequentialGroup()
											.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
												.addComponent(lblNewLabel_1_3_1, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNewLabel_1_5_1, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNewLabel_1_3_1_1, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(ComponentPlacement.RELATED))
										.addComponent(lblNewLabel_1_7, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_1.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(sid, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel_1.createSequentialGroup()
											.addGap(6)
											.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
												.addComponent(sem, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
												.addGroup(gl_panel_1.createSequentialGroup()
													.addComponent(syear, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
													.addGap(170)
													.addComponent(lblNewLabel_1_6, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))))
										.addGroup(gl_panel_1.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnClear, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)))))
					.addGap(155))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(41)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(sfirstname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_5)
						.addComponent(smale)
						.addComponent(sfemale))
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(18)
							.addComponent(jlabelt))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(27)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(slastname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1_5_1)
								.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(2)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(29)
									.addComponent(lblNewLabel_1_6, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(18)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblNewLabel_1_4)
										.addComponent(sphonenumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(16)
									.addComponent(lblNewLabel_1_2))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(18)
									.addComponent(susername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1_3_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
								.addComponent(syear, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1_3_1_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
								.addComponent(sem, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_7)
						.addComponent(sid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_2_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(49)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(sdelete)
							.addComponent(btnClear))
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(addStudent)
							.addComponent(btnUpdate)))
					.addContainerGap(28, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		student.setLayout(gl_student);
		
		JPanel Course = new JPanel();
		Course.setBackground(new Color(0, 128, 64));
		cardLayout.add(Course, "course");
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 128, 64));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		GroupLayout gl_Course = new GroupLayout(Course);
		gl_Course.setHorizontalGroup(
			gl_Course.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_Course.createSequentialGroup()
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE)
					.addContainerGap())
				.addComponent(scrollPane_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 777, Short.MAX_VALUE)
		);
		gl_Course.setVerticalGroup(
			gl_Course.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Course.createSequentialGroup()
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(31, Short.MAX_VALUE))
		);
		
		JLabel lblNewLabel_2 = new JLabel("Years");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		
		JLabel lblNewLabel_2_4_1 = new JLabel("Course");
		lblNewLabel_2_4_1.setForeground(Color.WHITE);
		lblNewLabel_2_4_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		courses = new JTextField();
		courses.setFont(new Font("Tahoma", Font.PLAIN, 14));
		courses.setColumns(10);
		
       // Adding the information of the course
		JButton cadd = new JButton("Add");
		cadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String course = courses.getText().trim();
				String years = cyear.getSelectedItem().toString();
				String active = isactive.getSelectedItem().toString();
				CourseCrud cd = new CourseCrud();
				String id = "0";
				Course cours = new Course(id, course, years, active);
				cd.insertCourse(cours);
				loadCourse();
				clearCourse();
				
			}
		});
		//Update the information the course
		JButton cupdate = new JButton("Update");
		cupdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = cid.getText();
				String course = courses.getText().trim();
				String years = cyear.getSelectedItem().toString();
				String active = isactive.getSelectedItem().toString();
//				Passing the information into the Course and getting the value using encapsulation
				Course cds = new Course(id, course, years, active);
				CourseCrud cd = new CourseCrud();
				cd.updateCourse(cds);
				 loadCourse();
				 clearCourse();
			}
		});
		
//		Delete the information from the course
		JButton cdelete = new JButton("Delete");
		cdelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = cid.getText().trim();
				CourseCrud cd = new CourseCrud();
				cd.deleteCourse(id);
				loadCourse();
				clearCourse();
			}
		});
		
		JButton cclear = new JButton("Clear");
		cclear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearCourse();
			}
		});
		
		cyear = new JComboBox();
		cyear.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4"}));
		
		JLabel lblNewLabel_2_1 = new JLabel("isActive");
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		isactive = new JComboBox();
		isactive.setModel(new DefaultComboBoxModel(new String[] {"True", "False"}));
		
		cid = new JTextField();
		cid.setVisible(false);
		cid.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cid.setColumns(10);
		
		JLabel lblNewLabel_2_4_1_1 = new JLabel("Id");
		lblNewLabel_2_4_1_1.setVisible(false);
		lblNewLabel_2_4_1_1.setForeground(Color.WHITE);
		lblNewLabel_2_4_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(39)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(cadd)
							.addGap(18)
							.addComponent(cupdate, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(cdelete, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(cclear, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_2_4_1))
							.addGap(22)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
								.addComponent(cyear, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(courses, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE))
							.addGap(150)
							.addComponent(lblNewLabel_2_4_1_1, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
							.addGap(26)
							.addComponent(cid, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(isactive, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(235, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
					.addContainerGap(54, Short.MAX_VALUE)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
							.addComponent(cid, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel_2_4_1_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addComponent(courses, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2_4_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(cyear, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(isactive, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGap(54)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(cadd)
						.addComponent(cupdate)
						.addComponent(cdelete)
						.addComponent(cclear))
					.addContainerGap())
		);
		panel_2.setLayout(gl_panel_2);
		
		course = new JTable();
//	    setDefaultEditor gives the user not to edit inside the jtable
		course.setDefaultEditor(Object.class,null);
		course.addMouseListener(new MouseAdapter() {
		
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = course.getSelectedRow();
				TableModel model = course.getModel();
				cid.setText(model.getValueAt(index, 0).toString());
				courses.setText(model.getValueAt(index, 1).toString());
				String levels = model.getValueAt(index, 2).toString();
				switch(levels){
				case "1":
					cyear.setSelectedIndex(0);
				case "2":
					cyear.setSelectedIndex(1);
				case "3":
					cyear.setSelectedIndex(2);
				case "4":
					cyear.setSelectedIndex(3);
					
				}
				String active = model.getValueAt(index, 3).toString();
				System.out.println(active);
				if(active.equalsIgnoreCase("true")) {
					isactive.setSelectedIndex(0);
				}else {
					isactive.setSelectedIndex(1);
				}
			}
		});
		scrollPane_2.setViewportView(course);
		Course.setLayout(gl_Course);
		
		JPanel Module = new JPanel();
		Module.setBackground(new Color(0, 128, 64));
		cardLayout.add(Module, "module");
		
		panel_3 = new JPanel();
		panel_3.setBackground(new Color(0, 128, 64));
		
		JScrollPane scrollPane_3 = new JScrollPane();
		GroupLayout gl_Module = new GroupLayout(Module);
		gl_Module.setHorizontalGroup(
			gl_Module.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Module.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(38))
				.addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 777, Short.MAX_VALUE)
		);
		gl_Module.setVerticalGroup(
			gl_Module.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Module.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(30, Short.MAX_VALUE))
		);
		
		moduleTable = new JTable();
		moduleTable.setDefaultEditor(Object.class, null);
		mcourses = new JComboBox();
//		Set the value in the 
		moduleTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = moduleTable.getSelectedRow();
				TableModel model = moduleTable.getModel();
				mid.setText(model.getValueAt(index,0).toString());
				String course = model.getValueAt(index, 1).toString();
                // Using list to show the data given by the user
				SelectCourse sr = new SelectCourse();
				List<Course> courseList=sr.course();
				String[] courseName=new String[courseList.size()];
				index=0;
				for(Course course1:courseList) {
					courseName[index]=course1.getName();
					String courses = courseName[index];
					if(courses.equalsIgnoreCase(course)) {
						mcourses.setSelectedIndex(index);
						break;
					}
					index++;
				}
				
				
				mmodules.setText(model.getValueAt(index, 2).toString());
				mtitles.setText(model.getValueAt(index, 3).toString());
				myears.setText(model.getValueAt(index, 4).toString());
				musername.setText(model.getValueAt(index, 5).toString());
				mphonenumber.setText(model.getValueAt(index, 6).toString());
			}
		});
		scrollPane_3.setViewportView(moduleTable);
		
		JLabel lblNewLabel_4 = new JLabel("Module");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblNewLabel_4_1 = new JLabel("Title");
		lblNewLabel_4_1.setForeground(Color.WHITE);
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblNewLabel_4_2 = new JLabel("Years");
		lblNewLabel_4_2.setForeground(Color.WHITE);
		lblNewLabel_4_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblNewLabel_4_4 = new JLabel("Username");
		lblNewLabel_4_4.setForeground(Color.WHITE);
		lblNewLabel_4_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblNewLabel_4_5 = new JLabel("Phonenumber");
		lblNewLabel_4_5.setForeground(Color.WHITE);
		lblNewLabel_4_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblNewLabel_4_6 = new JLabel("id");
		lblNewLabel_4_6.setVisible(false);
		lblNewLabel_4_6.setForeground(Color.WHITE);
		lblNewLabel_4_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		mmodules = new JTextField();
		mmodules.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mmodules.setColumns(10);
		
		mtitles = new JTextField();
		mtitles.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mtitles.setColumns(10);
		
		myears = new JTextField();
		myears.setFont(new Font("Tahoma", Font.PLAIN, 14));
		myears.setColumns(10);
		
		musername = new JTextField();
		musername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		musername.setColumns(10);
		
		mphonenumber = new JTextField();
		mphonenumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mphonenumber.setColumns(10);
		
		mid = new JTextField();
		mid.setVisible(false);
		mid.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mid.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("Add");
		//Insert the data of module into jTable
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = mid.getText().trim();
				String course = mcourses.getSelectedItem().toString();
				String modules = mmodules.getText().trim();
				String titles = mtitles.getText().trim();
				String years = myears.getText().trim();
				String username = musername.getText().trim();
				String phonenumber = mphonenumber.getText().toString();
				
                ModuleCrud md = new ModuleCrud();
                Module module=new Module(Integer.parseInt(id), course, modules, titles, years, username, phonenumber);
				md.insertModule(module);
				loadModule();
				clearModule();
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
//		Update the data of module in the data base
		JButton btnNewButton_2_1 = new JButton("Update");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = mid.getText().trim();
				String course = mcourses.getSelectedItem().toString();
				String m = mmodules.getText().trim();
				String titles = mtitles.getText().trim();
				String years = myears.getText().trim();
				String username = musername.getText().trim();
				String phonenumber = mphonenumber.getText().toString();
				ModuleCrud md = new ModuleCrud();
				Module module=new Module(Integer.parseInt(id), course, m, titles, years, username, phonenumber);
				md.updateModule(module);
				loadModule();
				clearModule();

				
			}
		});
		btnNewButton_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		// Delete the data of the module
		JButton btnNewButton_2_2 = new JButton("Delete");
		btnNewButton_2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = mid.getText().trim();
				ModuleCrud md = new ModuleCrud();
				md.deleteModule(id);
				loadModule();
				clearModule();
			}
		});
		btnNewButton_2_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnNewButton_2_2_1 = new JButton("Clear");
		btnNewButton_2_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearModule();
			}
		});
		btnNewButton_2_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel text = new JLabel("Course");
		text.setForeground(Color.WHITE);
		text.setFont(new Font("Tahoma", Font.PLAIN, 16));

		mcourses = new JComboBox();
		mcourses.setModel(new DefaultComboBoxModel(courseName));
		
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(btnNewButton_2)
							.addGap(18)
							.addComponent(btnNewButton_2_1, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnNewButton_2_2)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnNewButton_2_2_1, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGroup(gl_panel_3.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_panel_3.createSequentialGroup()
									.addComponent(text, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(mcourses, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(gl_panel_3.createSequentialGroup()
									.addComponent(lblNewLabel_4_1, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(mtitles))
								.addGroup(gl_panel_3.createSequentialGroup()
									.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(mmodules, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_3.createSequentialGroup()
									.addComponent(lblNewLabel_4_2, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(myears)))
							.addGap(50)
							.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel_3.createSequentialGroup()
									.addComponent(lblNewLabel_4_4, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(musername, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_3.createSequentialGroup()
									.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_4_5)
										.addComponent(lblNewLabel_4_6, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
										.addComponent(mid, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
										.addComponent(mphonenumber))))))
					.addContainerGap(266, Short.MAX_VALUE))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(14)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(text, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(mcourses, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_4_4, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(musername, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_4_5, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_4_1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(mphonenumber, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(mmodules, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(mtitles, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_4_6, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_4_2, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(myears, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGap(27)
							.addComponent(mid, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_2)
						.addComponent(btnNewButton_2_1)
						.addComponent(btnNewButton_2_2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_2_2_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(30))
		);
		panel_3.setLayout(gl_panel_3);
		Module.setLayout(gl_Module);
		
		marks = new JPanel();
		marks.setBackground(new Color(0, 128, 64));
		cardLayout.add(marks, "marks");
		
		JLabel lblNewLabel_3 = new JLabel("Student Name");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		
		JLabel lblNewLabel_3_6 = new JLabel("Level");
		lblNewLabel_3_6.setForeground(Color.WHITE);
		lblNewLabel_3_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JScrollPane scrollPane_4 = new JScrollPane();
		
		btnNewButton_3 = new JButton("Add");
//		insert the marks of the student
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mid = id.getText().trim();
				String mname= name.getText().trim();
				String course= mcourse.getSelectedItem().toString();
				String mlevel = level.getText().trim();
				String mmarks = mark.getText().trim();
				CrudMarks cd = new CrudMarks();
				cd.insertMarks(mname, course, mlevel, mmarks);
				clearMarks();
				loadMarks();

			}
		});
		
		JLabel lblNewLabel_3_5 = new JLabel("Course");
		lblNewLabel_3_5.setForeground(Color.WHITE);
		lblNewLabel_3_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNewLabel_3_7 = new JLabel("Marks");
		lblNewLabel_3_7.setForeground(Color.WHITE);
		lblNewLabel_3_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		name = new JTextField();
		name.setFont(new Font("Tahoma", Font.PLAIN, 14));
		name.setColumns(10);
		
		level = new JTextField();
		level.setFont(new Font("Tahoma", Font.PLAIN, 14));
		level.setColumns(10);
		
		mark = new JTextField();
		mark.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mark.setColumns(10);
		//Update the marks of the student
		btnNewButton_3_1 = new JButton("Update");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mid = mids.getText().trim();
				String mname= name.getText().trim();
				String course= mcourse.getSelectedItem().toString();
				String mlevel = level.getText().trim();
				String mmarks = mark.getText().trim();
				CrudMarks cd = new CrudMarks();
				cd.updateMarks(mid, mname, course, mlevel, mmarks);
				clearMarks();
				loadMarks();
			}
		});
		//Delete the marks of the student
		btnNewButton_3_2 = new JButton("Delete");
		btnNewButton_3_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mid = mids.getText().trim();
				CrudMarks cd = new CrudMarks();
				cd.deleteMarks(mid);
				clearMarks();
				loadMarks();
			}
		});
		
		lblNewLabel_3_5_1 = new JLabel("id");
		lblNewLabel_3_5_1.setVisible(false);
		lblNewLabel_3_5_1.setForeground(Color.WHITE);
		lblNewLabel_3_5_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		mids = new JTextField();
		mids.setVisible(false);
		mids.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mids.setColumns(10);
		
		printmarks = new JButton("Generate Markssheet");
		printmarks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    String sname = name.getText();
				sql = "select * from marks where name = '"+sname+"'";
				Connection connection = ConnectionJDBC.getConnection();
				try {
					PreparedStatement pre = connection.prepareStatement(sql);
                    ResultSet rs = pre.executeQuery();
                    markstable.setModel(DataTable.resultSetToTableModel(rs));
                    clearMarks();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		printmarks.setVisible(false);
		
		back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				loadMarks();
			}
		});
		back.setVisible(false);
		
		mcourse = new JComboBox();
		mcourse.setModel(new DefaultComboBoxModel(courseName));
		GroupLayout gl_marks = new GroupLayout(marks);
		gl_marks.setHorizontalGroup(
			gl_marks.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_marks.createSequentialGroup()
					.addGroup(gl_marks.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_marks.createSequentialGroup()
							.addGap(43)
							.addGroup(gl_marks.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_marks.createSequentialGroup()
									.addGroup(gl_marks.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_marks.createSequentialGroup()
											.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED))
										.addGroup(gl_marks.createSequentialGroup()
											.addComponent(lblNewLabel_3_5_1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED))
										.addGroup(gl_marks.createSequentialGroup()
											.addComponent(lblNewLabel_3_5, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
											.addGap(26)))
									.addGroup(gl_marks.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_marks.createSequentialGroup()
											.addGroup(gl_marks.createParallelGroup(Alignment.LEADING)
												.addComponent(name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(mcourse, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
											.addGap(124)
											.addGroup(gl_marks.createParallelGroup(Alignment.LEADING)
												.addComponent(lblNewLabel_3_7, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNewLabel_3_6, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
											.addGap(23)
											.addGroup(gl_marks.createParallelGroup(Alignment.LEADING)
												.addComponent(level, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
												.addComponent(mark, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)))
										.addComponent(mids, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_marks.createSequentialGroup()
									.addComponent(btnNewButton_3)
									.addGap(18)
									.addComponent(btnNewButton_3_1)
									.addGap(18)
									.addComponent(btnNewButton_3_2, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
									.addGap(28)
									.addComponent(printmarks)
									.addGap(34)
									.addComponent(back, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))))
						.addComponent(scrollPane_4, GroupLayout.PREFERRED_SIZE, 979, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(19, Short.MAX_VALUE))
		);
		gl_marks.setVerticalGroup(
			gl_marks.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_marks.createSequentialGroup()
					.addGap(38)
					.addGroup(gl_marks.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3_6)
						.addComponent(level, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(gl_marks.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3_5, 0, 0, Short.MAX_VALUE)
						.addComponent(lblNewLabel_3_7, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(mark, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(mcourse, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_marks.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3_5_1, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(mids, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(51)
					.addGroup(gl_marks.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_3)
						.addComponent(btnNewButton_3_1)
						.addComponent(btnNewButton_3_2)
						.addComponent(back)
						.addComponent(printmarks))
					.addGap(18)
					.addComponent(scrollPane_4, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE)
					.addGap(21))
		);
		
		markstable = new JTable();
		markstable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				printmarks.setVisible(true);
				back.setVisible(true);
				
				int index = markstable.getSelectedRow();
				TableModel model = markstable.getModel();

				mids.setText(model.getValueAt(index, 0).toString());
				name.setText(model.getValueAt(index, 1).toString());
				String mcourses = model.getValueAt(index, 2).toString();

				SelectCourse srcourse = new SelectCourse();
				List<Course> courseList=srcourse.course();
				String[] mcourseName=new String[courseList.size()];
				index=0;
				for(Course course1:courseList) {
					mcourseName[index]=course1.getName();
					String courses = mcourseName[index];
					if(courses.equalsIgnoreCase(mcourses)) {
						mcourse.setSelectedIndex(index);
						break;
					}
					index++;
				}
				level.setText(model.getValueAt(index, 3).toString());
				mark.setText(model.getValueAt(index, 4).toString());
				
			}
		});
		markstable.setBounds(0, 0, 300, 300);
		markstable.setDefaultEditor(Object.class, null);
		scrollPane_4.setViewportView(markstable);
		marks.setLayout(gl_marks);
		frmCourseManagementSystem.getContentPane().setLayout(groupLayout);
		frmCourseManagementSystem.setVisible(true);
		
	}
	

	public void authtication(String role) {
		
		// TODO Auto-generated method stub
		if(role.equalsIgnoreCase("Instructor")) {
			username_1.setVisible(false);
			panel_1.setVisible(false);
			panel_2.setVisible(false);
			panel_3.setVisible(false);
		}
		else if(role.equalsIgnoreCase("Administrator")) {
			btnNewButton_3.setVisible(false);
			btnNewButton_3_1.setVisible(false);
			btnNewButton_3_2.setVisible(false);
		}
		
	}
}