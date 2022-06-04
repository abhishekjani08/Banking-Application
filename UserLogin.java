import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class UserLogin extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton btnNewButton;
    private JButton b1;
    private JLabel label;
    private JPanel contentPane;
    private JCheckBox show;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UserLogin frame = new UserLogin();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public UserLogin() {

        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 90, 900, 600);
        setResizable(false);
        contentPane = new javax.swing.JPanel();
		contentPane.setBackground(new java.awt.Color(204, 255, 204));
		contentPane.setBorder(new javax.swing.border.MatteBorder(null));
		contentPane.setForeground(new java.awt.Color(0, 255, 204));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Login");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 46));
        lblNewLabel.setBounds(370, 13, 273, 93);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        textField.setBounds(481, 170, 281, 68);
        contentPane.add(textField);
        textField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        passwordField.setBounds(481, 286, 281, 68);
        contentPane.add(passwordField);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBackground(Color.BLACK);
        lblUsername.setForeground(Color.BLACK);
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblUsername.setBounds(150, 166, 193, 52);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setForeground(Color.BLACK);
        lblPassword.setBackground(Color.CYAN);
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblPassword.setBounds(150, 286, 193, 52);
        contentPane.add(lblPassword);

        btnNewButton = new JButton("Login");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnNewButton.setBounds(345, 392, 162, 73);
        btnNewButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if(textField.getText().equals("abhishek") && passwordField.getText().equals("bankadmin")){
                   
                    BankSystem ah = new BankSystem();
                    ah.setVisible(true);
                    dispose();
                    JOptionPane.showMessageDialog(btnNewButton, "Admin is Logged in Successfully","BankSystem ",JOptionPane.INFORMATION_MESSAGE);
                }
                else if(textField.getText().equals("jayesh") && passwordField.getText().equals("bankadmin")){
                   
                    BankSystem ah = new BankSystem();
                    ah.setVisible(true);
                    dispose();
                    JOptionPane.showMessageDialog(btnNewButton, "Admin is Logged in Successfully","BankSystem ",JOptionPane.INFORMATION_MESSAGE);
                }
                else if(textField.getText().equals("zaid") && passwordField.getText().equals("bankadmin")){
                   
                    BankSystem ah = new BankSystem();
                    ah.setVisible(true);
                    dispose();
                    JOptionPane.showMessageDialog(btnNewButton, "Admin is Logged in Successfully","BankSystem ",JOptionPane.INFORMATION_MESSAGE);
                }
                else if(textField.getText().equals("saurabh") && passwordField.getText().equals("bankadmin")){
                   
                    BankSystem ah = new BankSystem();
                    ah.setVisible(true);
                    dispose();
                    JOptionPane.showMessageDialog(btnNewButton, "Admin is Logged in Successfully","BankSystem ",JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                String userName = textField.getText();
                String password = passwordField.getText();
                try {

                    Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/swing_demo?autoReconnect=true&useSSL=false","root","root");

                    PreparedStatement st = (PreparedStatement) connection
                        .prepareStatement("Select username, password from student where username=? and password=?");

                    st.setString(1, userName);
                    st.setString(2, password);
                    ResultSet rs = st.executeQuery();
                    if (rs.next()) {
                        dispose();
                       BankSystemUser ah = new BankSystemUser();
                        ah.setVisible(true);
                        dispose();
                        JOptionPane.showMessageDialog(btnNewButton, "You have successfully logged in","BankSystem ",JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(btnNewButton, "Wrong Username & Password");
                    }
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        }   
        });
		btnNewButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnNewButton.setBackground(Color.lightGray);
			}
			public void mouseExited(java.awt.event.MouseEvent evt) {
			btnNewButton.setBackground(UIManager.getColor("control"));
			}
		});

        contentPane.add(btnNewButton);
        label = new JLabel("");
        label.setBounds(0, 0, 1008, 562);
        contentPane.add(label);

        b1 = new JButton("Back");
        b1.setFont(new Font("Tahoma", Font.PLAIN, 26));
        b1.setSize(100, 50);
		b1.setLocation(380, 480);
        b1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Collect gh = new Collect();
				gh.setVisible(true);
           setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
        } 
        });
        contentPane.add(b1);
        label = new JLabel("");
        label.setBounds(0, 0, 1008, 662);
        contentPane.add(label);

        show = new JCheckBox("Show password");
        show.setSize(250, 30);
        show.setLocation(481, 360);
        show.setBackground(new java.awt.Color(204, 255, 204));
        show.setFont(new Font("Tahoma", Font.PLAIN, 18));
        show.addActionListener(this);
        contentPane.add(show);
    }
    public void actionPerformed(ActionEvent e) {

        if (show.isSelected()) {
            passwordField.setEchoChar((char) 0);

        } else {
            passwordField.setEchoChar('*');
        }
    }
}