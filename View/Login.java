package View;

import java.awt.EventQueue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.SpringLayout;
import javax.swing.DropMode;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import Controller.Auth;
import Controller.ConnectionJDBC;
import Model.User;
import helper.EmailMismatchedError;

import java.awt.ComponentOrientation;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
public class Login extends JFrame {

	private JFrame frmLoginPage;
	private JTextField username;
	private Image img_logo = new ImageIcon(Login.class.getResource("/resource/school.jpg")).getImage().getScaledInstance(80, 80 , Image.SCALE_SMOOTH);
	Connection connection;
	PreparedStatement pst;
	ResultSet rs;
	private JPasswordField password;
	private JLabel iconLogo;
	private JLabel selectcourse;
	private JComboBox select;
	private JComboBox selectCourse;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmLoginPage.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLoginPage = new JFrame();
		frmLoginPage.setTitle("Login Page");
		frmLoginPage.getContentPane().setBackground(new Color(0, 128, 64));
		frmLoginPage.getContentPane().setForeground(new Color(0, 0, 0));
		frmLoginPage.setBounds(100, 100, 537, 495);
		frmLoginPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 64));
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		
		
		JLabel lblNewLabel_1_1 = new JLabel("LogIn as");
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JLabel lblNewLabel_1_2 = new JLabel("Password");
		lblNewLabel_1_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		username = new JTextField();
		username.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		username.setColumns(10);
		
		select = new JComboBox();
		select.addActionListener(new ActionListener() {
		   //if role selected as student it show the course the student and instructor to enroll
			public void actionPerformed(ActionEvent e) {
				String selectrole = select.getSelectedItem().toString();
				if(selectrole == "Student") {
					selectcourse.setVisible(true);
					selectCourse.setVisible(true);
				}else {
					selectcourse.setVisible(false);
					selectCourse.setVisible(false);
				}
			}
		});
		select.setFont(new Font("Tahoma", Font.PLAIN, 18));
		select.setModel(new DefaultComboBoxModel(new String[] {"Administrator", "Instructor", "Student"}));
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//cancel button
				System.exit(0);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JButton registerbtn = new JButton("Register");
		registerbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Registration();
				frmLoginPage.setVisible(false);
			}
		});
		registerbtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		password = new JPasswordField();
		password.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));

		JButton login = new JButton("Login");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Getting the value given by the user if the given email and password does not match with database it show the error
				   String uname = username.getText();
				   String pwd = new String(password.getPassword());
				   String role = select.getSelectedItem().toString();
				   String selectcourse = selectCourse.getSelectedItem().toString() ;
				   
				   if (uname == null || uname.isEmpty() || uname.trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Please Enter Username");
						username.requestFocus();
						return;
					}
					if (pwd == null || pwd.isEmpty() || pwd.trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Please Enter Password");
						password.requestFocus();
						return;
					}
				
				   else {
//					   Calling the function login for the course
					   Auth auth =new Auth();
					   User user = auth.login(role, selectcourse, uname, pwd);
					   if(user!=null) {
						   if(role.equalsIgnoreCase("student")) {
							 
							   new StudentDashBoards(user, selectcourse);
						   }else if(role.equalsIgnoreCase("administrator")) {
							   DashBoards db = new DashBoards();
							   db.authtication(role);
							   frmLoginPage.setVisible(false);
						   }else if(role.equals("Instructor")){
							   DashBoards db = new DashBoards();
							   db.authtication(role);
							   frmLoginPage.setVisible(false);

						   }
						 
					   }
				   }
				
			}
		});
		login.setFont(new Font("Tahoma", Font.PLAIN, 18));
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 115, SpringLayout.NORTH, frmLoginPage.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, frmLoginPage.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, -10, SpringLayout.SOUTH, frmLoginPage.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, 495, SpringLayout.WEST, frmLoginPage.getContentPane());
		frmLoginPage.getContentPane().setLayout(springLayout);
		frmLoginPage.getContentPane().add(panel);
		
		iconLogo = new JLabel();
		springLayout.putConstraint(SpringLayout.NORTH, iconLogo, 10, SpringLayout.NORTH, frmLoginPage.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, iconLogo, 200, SpringLayout.WEST, frmLoginPage.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, iconLogo, -25, SpringLayout.NORTH, panel);
		
		selectcourse = new JLabel("Select Course");
		selectcourse.setVisible(false);
		selectcourse.setForeground(Color.WHITE);
		selectcourse.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		selectCourse = new JComboBox();
		selectCourse.setVisible(false);
		selectCourse.setModel(new DefaultComboBoxModel(new String[] {"Select Course", "Bsc(Hons) Computer Science", "BIBM"}));
		selectCourse.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(103)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(username, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap(83, Short.MAX_VALUE)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(login, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
									.addGap(21))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(selectcourse)
									.addGap(18))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(35)
									.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(select, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
										.addComponent(selectCourse, 0, 0, Short.MAX_VALUE)))))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(101)
							.addComponent(lblNewLabel_1_2, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(password, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(55, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(168)
					.addComponent(registerbtn, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(188, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(31)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(username, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addGap(25)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_1_2, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
						.addComponent(password, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1)
						.addComponent(select, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(selectcourse)
						.addComponent(selectCourse, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(51)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(login, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addGap(18)
					.addComponent(registerbtn, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(22))
		);
		panel.setLayout(gl_panel);
		springLayout.putConstraint(SpringLayout.EAST, iconLogo, 280, SpringLayout.WEST, frmLoginPage.getContentPane());
		frmLoginPage.getContentPane().add(iconLogo);
		iconLogo.setIcon(new ImageIcon(img_logo));
		frmLoginPage.setVisible(true);
	}
}
