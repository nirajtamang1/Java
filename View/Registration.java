package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.regex.*;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.LayoutStyle.ComponentPlacement;

import Controller.Auth;
import Controller.ConnectionJDBC;
import Controller.SelectCourse;
import Model.Course;
import Model.User;

import javax.swing.JRadioButton;
import javax.swing.JTextArea;  
public class Registration {

	private JFrame frame;
	private JTextField username;
	private JTextField lastname;
	private JTextField firstname;
	private JTextField phonenumber;
	private JPasswordField conformpassword;
	private JPasswordField rpassword;
	PreparedStatement pst;
	private JComboBox loginas;
	private JLabel course;
	private JComboBox selectcourse;
	private Image logo = new ImageIcon(Registration.class.getResource("/resource/school.jpg")).getImage().getScaledInstance(80, 80 , Image.SCALE_SMOOTH);
	private JLabel image_logo;
	private JRadioButton female;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registration window = new Registration();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Registration() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(){
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 128, 64));
		
		image_logo = new JLabel("");
		image_logo.setForeground(new Color(255, 255, 255));
		image_logo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		image_logo.setIcon(new ImageIcon(logo));
	
		
		firstname = new JTextField();
		firstname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		firstname.setColumns(10);
		
		lastname = new JTextField();
		lastname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lastname.setColumns(10);
		
		JLabel first_name = new JLabel("First Name");
		first_name.setForeground(new Color(255, 255, 255));
		first_name.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel gender = new JLabel("Gender");
		gender.setForeground(new Color(255, 255, 255));
		gender.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel phone_number = new JLabel("Phone Number");
		phone_number.setForeground(new Color(255, 255, 255));
		phone_number.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		phonenumber = new JTextField();
		phonenumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
		phonenumber.setColumns(10);
		
		JLabel user_name = new JLabel("User Name");
		user_name.setForeground(new Color(255, 255, 255));
		user_name.setBackground(new Color(255, 255, 255));
		user_name.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		username = new JTextField();
		username.setFont(new Font("Tahoma", Font.PLAIN, 16));
		username.setColumns(10);
		
		JLabel password1 = new JLabel("Password");
		password1.setForeground(new Color(255, 255, 255));
		password1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		rpassword = new JPasswordField();
		rpassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel conformpassword1 = new JLabel("Conform Password");
		conformpassword1.setForeground(new Color(255, 255, 255));
		conformpassword1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		conformpassword = new JPasswordField();
		conformpassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblNewLabel_1_5 = new JLabel("Login as");
		lblNewLabel_1_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		loginas = new JComboBox();
		loginas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String role = loginas.getSelectedItem().toString();
				if(role == "Student" || role == "Instructor") {
					course.setVisible(true);
					selectcourse.setVisible(true);
				}else {
					course.setVisible(false);
					selectcourse.setVisible(false);
				}
			}
		});
		loginas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		loginas.setModel(new DefaultComboBoxModel(new String[] {"Administrator", "Instructor", "Student"}));
		
		JButton register = new JButton("Register");
		register.addActionListener(new ActionListener() {
//			Getting the value from the user
			public void actionPerformed(ActionEvent e){
				String firstName =firstname.getText();
				String lastName = lastname.getText();
				String phoneNumber = phonenumber.getText();
				String gender = "male";
				if(female.isSelected()) {
					 gender = "female";
				}
				String userName = username.getText();
				String passWord = new String(rpassword.getPassword());
				String conformPassword = new String(conformpassword.getPassword());
				String logIn = loginas.getSelectedItem().toString();
				String course = selectcourse.getSelectedItem().toString();
				if(firstName.equals("")|| lastName.equals("")|| username.equals("")||passWord.equals("")|| logIn.equals("")|| conformPassword.equals("")|| phoneNumber.equals("")) {
					   JOptionPane.showMessageDialog(null, "Some Field are Emplty", "Error", 1);
				   }else {
				// Calling the encapsulation to get the private data from the user
			    User user=new User(0,firstName,lastName,phoneNumber,gender,userName);
			    // Calling the registration function form the auth class to register
				Auth auth=new Auth();
				int status=auth.registration(logIn, user, course, conformPassword);
				if(status>0) {
					JOptionPane.showMessageDialog(null, "Registration successful");
					new Login();
					frame.setVisible(false);
				    
				}else {
					
				}
		      }
			      
			      
			}
		});
		register.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton cancel1 = new JButton("Cancel");
		//If your cancel the program it goes to login page
		cancel1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Login();
				frame.setVisible(false);
			}
		});
		cancel1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		course = new JLabel("Course");
		course.setVisible(false);
		course.setForeground(Color.WHITE);
		course.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		selectcourse = new JComboBox();
		selectcourse.setVisible(false);
		SelectCourse sr = new SelectCourse();
		
		//Displaying the course list that is able
		List<Course> courseList=sr.course();
		String[] courseName=new String[courseList.size()];
		int index=0;
		for(Course course:courseList) {
			courseName[index]=course.getName();
			index++;
			}
		selectcourse.setModel(new DefaultComboBoxModel(courseName));
		selectcourse.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JLabel last_name_1 = new JLabel("Last Name");
		last_name_1.setForeground(Color.WHITE);
		last_name_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JRadioButton male = new JRadioButton("Male");
		male.setFont(new Font("Tahoma", Font.PLAIN, 16));
		male.setForeground(new Color(255, 255, 255));
		male.setBackground(new Color(0, 128, 64));
		
		
		
		female = new JRadioButton("Female");
		female.setFont(new Font("Tahoma", Font.PLAIN, 16));
		female.setForeground(new Color(255, 255, 255));
		female.setBackground(new Color(0, 128, 64));
		ButtonGroup gen = new ButtonGroup();
		gen.add(male);
		gen.add(female);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(106)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(phone_number)
										.addComponent(user_name, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
										.addComponent(password1, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(last_name_1, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
												.addComponent(first_name)
												.addComponent(gender, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
													.addGap(21)
													.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addGroup(groupLayout.createSequentialGroup()
															.addComponent(male)
															.addPreferredGap(ComponentPlacement.UNRELATED)
															.addComponent(female, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))
														.addComponent(phonenumber, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
														.addComponent(username, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
														.addComponent(selectcourse, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
														.addComponent(rpassword, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
														.addComponent(conformpassword, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
														.addComponent(loginas, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)))
												.addGroup(groupLayout.createSequentialGroup()
													.addGap(18)
													.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(firstname, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
														.addComponent(lastname, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)))))))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(174)
									.addComponent(image_logo))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(70)
									.addComponent(conformpassword1, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(108)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(course, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_1_5, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
										.addComponent(register, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))))
							.addGap(106))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(cancel1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
							.addGap(136))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(image_logo, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(first_name)
						.addComponent(firstname, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(last_name_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(lastname, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(male)
						.addComponent(female)
						.addComponent(gender, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(phone_number, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(phonenumber, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(user_name, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(username, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(password1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(rpassword, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(conformpassword1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(conformpassword, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_5, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(loginas, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(selectcourse, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(course, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(cancel1)
						.addComponent(register))
					.addGap(68))
		);
		frame.getContentPane().setLayout(groupLayout);
		frame.setBackground(new Color(0, 128, 64));
		frame.setBounds(100, 100, 524, 593);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
