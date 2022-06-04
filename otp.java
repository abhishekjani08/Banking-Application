import java.awt.Color;
import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.*;
import java.util.Random;

public class otp extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JTextField api;
    private JTextField verify;
    private JTextField passwordField;
    private JButton btnNewButton;
    private JButton b1;
    private JButton b2;
    private JLabel label;
    private JPanel contentPane;
    int OTP;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
                    otp frame = new otp();
                    frame.setVisible(true);
    }

    /**
     * Create the frame.
     */
    public otp() 
    {
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 90, 900, 600);
        setResizable(false);
        contentPane = new javax.swing.JPanel();
        contentPane.setBackground(new java.awt.Color(204, 255, 204));
        contentPane.setBorder(new javax.swing.border.MatteBorder(null));
        contentPane.setForeground(new java.awt.Color(0, 255, 204));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("OTP");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 46));
        lblNewLabel.setBounds(370, 13, 273, 93);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        textField.setBounds(481, 170, 281, 68);
        contentPane.add(textField);
        textField.setColumns(10);

        verify = new JTextField();
        verify.setFont(new Font("Tahoma", Font.PLAIN, 32));
        verify.setBounds(581, 370, 281, 68);
        contentPane.add(verify);
        verify.setColumns(10);

        passwordField = new JTextField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        passwordField.setBounds(481, 286, 281, 68);
        contentPane.add(passwordField);

        api = new JTextField("");
        api.setFont(new Font("Tahoma", Font.PLAIN, 32));
        api.setBounds(181, 286, 281, 68);
        contentPane.add(api);

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

        b2 = new JButton("verify");
        b2.setFont(new Font("Tahoma", Font.PLAIN, 26));
        b2.setSize(100, 50);
        b2.setLocation(580, 480);

        contentPane.add(b2);
        label = new JLabel("");
        label.setBounds(0, 0, 1308, 762);
        contentPane.add(label);
    }
    public void btnNewButtonactionPerformed(java.awt.event.ActionEvent evt) {
        try {
            // Construct data
            String apiKey = api.getText();
            Random rand = new Random();
            OTP = rand.nextInt(999999);
            String name = textField.getText();
            String message = "&message=" + "Hii" + name + "your OTP is" + OTP;
            String sender = "&sender=" + "Ideal Bank ";
            String numbers = "&numbers=" + passwordField.getText();

            // Send data
            HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
            String data = apiKey + numbers + message + sender;
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
            conn.getOutputStream().write(data.getBytes("UTF-8"));
            final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            final StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                stringBuffer.append(line);
            }
            rd.close();
            JOptionPane.showMessageDialog(null, "OTP SEND SUCCESSFULLY");
            // return stringBuffer.toString();
        } catch (Exception e) {
            System.out.println(e);
            // return "Error "+e;
        }
    }
    public void b2actionPerformed(java.awt.event.ActionEvent evt) {
            if (Integer.parseInt(verify.getText()) == OTP) {
                JOptionPane.showMessageDialog(null, "You are logged in successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Login Failed");
            }
        }
    
}
