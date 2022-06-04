import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;

public class First extends JFrame {
	private JPanel contentPane;
	private JLabel moving;
	private ImageIcon idealbank;
	private ImageIcon bg;
	JLabel l1;
	JLabel l2;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					First frame = new First();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public First() {

		
		setTitle("IDEAL BANK");
		setBounds(300, 90, 900, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		contentPane = new javax.swing.JPanel();
		contentPane.setBackground(new java.awt.Color(204, 255, 204));
		contentPane.setBorder(new javax.swing.border.MatteBorder(null));
		contentPane.setForeground(new java.awt.Color(0, 255, 204));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		idealbank = new ImageIcon("xyz.png");
		l1 = new JLabel(idealbank);
		add(l1);
		l1.setBounds(100, 100, 690, 268);

		moving = new JLabel("");
		moving.setFont(new Font("Arial", Font.PLAIN, 20));
		moving.setSize(500, 25);
		moving.setLocation(320, 470);
		contentPane.add(moving);

		JLabel lblNewLabel = new JLabel("WELCOME TO IDEAL BANK");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 46));
        lblNewLabel.setBounds(160, 20, 600, 70);
        contentPane.add(lblNewLabel);
		

		JButton b1 = new JButton("REGISTER HERE");
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// creating one object
				Collect gh = new Collect();
				gh.setVisible(true);
				dispose();
				moving.setText("Heading towards Registration Page..");
			}
		});

		b1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				b1.setBackground(Color.LIGHT_GRAY);
			}
			public void mouseExited(java.awt.event.MouseEvent evt) {
			b1.setBackground(UIManager.getColor("control"));
			}
		});

		b1.setForeground(Color.BLACK);
        b1.setFont(new Font("Times New Roman", Font.TYPE1_FONT, 20));
		b1.setSize(200, 50);
		b1.setLocation(350, 400);
		contentPane.add(b1);
	}
}
