import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class Bankkit extends JInternalFrame implements ActionListener {
    private JPanel jpkit = new JPanel();
    private JLabel info;
    private JLabel info2;
    private JButton btnyes, btnno;
    private JLabel lbNo, lbName, lbget;
	private JTextField txtNo, txtName;

    Bankkit() {
        super("Bank Kit", false, true, false, true);
        setSize(500, 400);
        
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getWidth()) / 2,
		(Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 3);

		jpkit.setBounds (0, 0, 500, 400);
		jpkit.setLayout (null);

        info = new JLabel(
        "<html>Our bank kit consists of all the things you need to make your life simple. <br> It consists of:- <br> 1)PassBook <br> 2)Chequebook <br> 3)Debit Card</html>");
        info.setSize(500, 100);
        info.setFont(new Font("ARIAL", Font.BOLD, 13));
        info.setLocation(10, 8);
        jpkit.add(info);

        btnyes = new JButton("Yes");
        btnyes.setSize(80, 30);
		btnyes.setLocation(130,320);

		lbNo = new JLabel("Account No:");
		lbNo.setForeground(Color.black);
		lbNo.setBounds(30, 100, 150, 75);
		lbNo.setFont(new Font("Montserrat", Font.PLAIN, 15));

		lbName = new JLabel("Person Name:");
		lbName.setForeground(Color.black);
		lbName.setBounds(30, 155, 150, 115);
		lbName.setFont(new Font("Montserrat", Font.PLAIN, 15));

		lbget = new JLabel("Avail your services now!");
		lbget.setForeground(Color.black);
		lbget.setBounds(140, 190, 250, 200);
		lbget.setFont(new Font("Montserrat", Font.PLAIN, 15));

		txtNo = new JTextField();
		txtNo.setBounds(170, 120, 250, 35);
		txtNo.setFont(new Font("Montserrat", Font.PLAIN, 15));

		txtName = new JTextField();
		txtName.setBounds(170, 200, 250, 35);
		txtName.setFont(new Font("Montserrat", Font.PLAIN, 15));

        btnyes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    info2= new JLabel("<html>Our bank <br> It consists of:- </html>");
                    info2.setSize(200, 100);
                    info2.setLocation(100, 200);
                    jpkit.add(info2);
        }
        });
        btnyes.addActionListener(this);
		btnno = new JButton("No");
		btnno.setSize(80, 30);
		btnno.setLocation(280, 320);
		btnno.addActionListener(this);
       
        // jpkit.add(info);
        jpkit.add(lbNo);
		jpkit.add(txtNo);
		jpkit.add(lbName);
		jpkit.add(txtName);
		jpkit.add(lbget);
        jpkit.add(btnyes);
        jpkit.add(btnno);
        getContentPane().add(jpkit);
        // JOptionPane.showConfirmDialog(jpkit,JOptionPane.YES_NO_OPTION);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}