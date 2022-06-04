import java.awt.EventQueue;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Collect extends JFrame implements ActionListener {

    private javax.swing.JPanel JPanel1;
    private JTextField t1;
    private JTextField t2;
    private JTextField t3;
    private JTextField t4;
    private JPasswordField t5;
    private JLabel res;
	private JCheckBox show;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Collect frame = new Collect();
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
	public Collect() {

		setTitle("Registration Form");
		setBounds(300, 90, 900, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		JPanel1 = new javax.swing.JPanel();
		JPanel1.setBackground(new java.awt.Color(204, 255, 204));
		JPanel1.setBorder(new javax.swing.border.MatteBorder(null));
		JPanel1.setForeground(new java.awt.Color(0, 255, 204));

	
		setContentPane(JPanel1);
		JPanel1.setLayout(null);

        JLabel lblNewLabel = new JLabel("Registration");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 46));
        lblNewLabel.setBounds(350, 10, 253, 70);
    	JPanel1.add(lblNewLabel);

        JLabel lblNLabel = new JLabel("Already have an account? Login here");
        lblNLabel.setForeground(Color.BLACK);
        lblNLabel.setFont(new Font("Arial", Font.PLAIN, 17));
        lblNLabel.setSize(350, 200);
		lblNLabel.setLocation(290, 350);
    	JPanel1.add(lblNLabel);
		
		JLabel l1 = new JLabel("Name");
		l1.setFont(new Font("Arial", Font.PLAIN, 20));
		l1.setSize(100, 20);
		l1.setLocation(250, 100);
		JPanel1.add(l1);
		
		JLabel l2 = new JLabel("Mobile");
		l2.setFont(new Font("Arial", Font.PLAIN, 20));
		l2.setSize(100, 20);
		l2.setLocation(250, 150);
		JPanel1.add(l2);
		
		JLabel l3 = new JLabel("Email ID");
		l3.setFont(new Font("Arial", Font.PLAIN, 20));
		l3.setSize(100, 20);
		l3.setLocation(250, 200);
		JPanel1.add(l3);
		
		JLabel l4 = new JLabel("Username");
		l4.setFont(new Font("Arial", Font.PLAIN, 20));
		l4.setSize(100, 20);
		l4.setLocation(250, 250);
		JPanel1.add(l4);

		JLabel l5 = new JLabel("Password");
		l5.setFont(new Font("Arial", Font.PLAIN, 20));
		l5.setSize(100, 20);
		l5.setLocation(250, 300);
		JPanel1.add(l5);
		
		t1 = new JTextField();
		t1.setSize(250, 30);
		t1.setLocation(400, 100);
		JPanel1.add(t1);
		t1.setColumns(10);
		

		t2 = new JTextField();
		t2.setSize(250, 30);
		t2.setLocation(400, 150);
		JPanel1.add(t2);
		t2.setColumns(10);
		
		t2.addKeyListener (new KeyAdapter() {
			public void keyTyped (KeyEvent ke) {
				char c = ke.getKeyChar ();
				if (!((Character.isDigit (c) || (c == KeyEvent.VK_BACK_SPACE)))) {
					getToolkit().beep ();
					ke.consume ();
      				}
    			}
  		}
		);

		t3 = new JTextField();
		t3.setSize(250 ,30);
		t3.setLocation(400, 200);
		JPanel1.add(t3);
		t3.setColumns(10);
		t3.setToolTipText("Email Id must have @ symbol");

		t4 = new JTextField();
		t4.setSize(250, 30);
		t4.setLocation(400, 250);
		JPanel1.add(t4);
		t4.setColumns(10);
		t4.setToolTipText("Have a unique username");

		t5 = new JPasswordField();
		t5.setSize(250, 30);
		t5.setLocation(400, 300);
		JPanel1.add(t5);
		t5.setColumns(10);
		
		JButton b1 = new JButton("Submit");
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=t1.getText();
				String mobile=t2.getText();
				String emailid=t3.getText();
				String username=t4.getText();
				String password=t5.getText();
				if(t3.getText().contains("@gmail.com")) {
				// creating one object 
				my_update obj=new my_update();
				obj.my_db_update(name, mobile, emailid, username, password);
                UserLogin ah = new UserLogin();  
                ah.setVisible(true);
				dispose();
				}
				else{
					JOptionPane.showMessageDialog (t3, "PLEASE PROVIDE A PROPER EMAIL ID",
							"BankSystem - EmptyField", JOptionPane.ERROR_MESSAGE);
							t3.requestFocus();	
				}
			}
		});
		b1.setFont(new Font("Arial", Font.BOLD, 20));
		b1.setSize(100, 30);
		b1.setLocation(300, 380);
		JPanel1.add(b1);


		b1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				b1.setBackground(Color.lightGray);
			}
			public void mouseExited(java.awt.event.MouseEvent evt) {
			b1.setBackground(UIManager.getColor("control"));
			}
		});

		
			

		JButton b2 = new JButton("Reset");
		b2.addActionListener(new ActionListener()
         {
			public void actionPerformed(ActionEvent e) {
			t1.setText("");
			t2.setText("");
			t3.setText("");
			t4.setText("");
			t5.setText("");
			res.setText("");
				}
			});
			b2.setFont(new Font("Arial", Font.PLAIN, 20));
		b2.setSize(100, 30);
		
		b2.setLocation(450, 380);
		JPanel1.add(b2);
       
        JButton b3 = new JButton("Login");
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserLogin bc = new UserLogin();  
                bc.setVisible(true);
				dispose();
                res.setText("Heading towards Login Page..");
				}
		});
		b3.setFont(new Font("Arial", Font.PLAIN, 20));
		b3.setSize(100, 30);
		b3.setLocation(380, 480);
		JPanel1.add(b3);

		show = new JCheckBox("Show password");
		show.setSize(150, 30);
		show.setLocation(400, 340);
		show.addActionListener(this);
		show.setBackground(new java.awt.Color(204, 255, 204));
		show.setFont(new Font("Tahoma", Font.PLAIN, 15));
		JPanel1.add(show);
	}
	public void actionPerformed(ActionEvent e) {
		
		if(show.isSelected()){
			t5.setEchoChar((char)0);
			
		}
		else{
			t5.setEchoChar('*');
		}
	}
}