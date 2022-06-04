import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;

class login extends JFrame implements ActionListener {

  JLabel l1, l2, l3, l4;
  JButton b1, b2;
  JTextField t1;
  JPasswordField p1;
  ImageIcon i;

  login() {
    super("Login");
    setSize(600, 450);
    setLocation(100, 100);
    setLayout(null);
    setResizable(true);
    i = new ImageIcon("xyz.png");
    l1 = new JLabel("UserName");
    l2 = new JLabel("Password");
    l3 = new JLabel(i);
    add(l3);

    t1 = new JTextField();
    p1 = new JPasswordField();

    b1 = new JButton("Login");
    b1.setMnemonic('L');
    b1.setToolTipText("login here");
    b2 = new JButton("cancel");
    b2.setMnemonic('c');
    b2.setToolTipText("cancel here");

    add(l3);
    add(l1);
    add(l2);
    add(t1);
    add(p1);
    add(b1);
    add(b2);

    l1.setBounds(350, 20, 100, 20);
    t1.setBounds(460, 20, 100, 20);
    l2.setBounds(350, 50, 100, 20);
    p1.setBounds(460, 50, 100, 20);
    b1.setBounds(350, 80, 70, 30);
    b2.setBounds(460, 80, 80, 30);

    l3.setBounds(10, 10, 320, 368);

    b1.addActionListener(this);
    b2.addActionListener(this);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    setVisible(true);
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == b1) {

      if (t1.getText().equals("bank") && p1.getText().equals("bank")) {
        JOptionPane.showMessageDialog(null, "Sucessfully login");
        login l = new login();
        dispose();
        l.setVisible(false);

        BankSystem b = new BankSystem();
        b.setVisible(true);

        t1.requestFocus();
      }
      if (!t1.getText().equals("bank") && !p1.getText().equals("bank")) {
        JOptionPane.showMessageDialog(null, "Both filed are wrong", "Error", JOptionPane.ERROR_MESSAGE);
        t1.setText("");
        p1.setText("");
        t1.requestFocus();
      }
      if (t1.getText().equals("bank") && !p1.getText().equals("bank")) {
        JOptionPane.showMessageDialog(null, "password filed are wrong", "Error", JOptionPane.ERROR_MESSAGE);
        t1.setText("");
        p1.setText("");
        t1.requestFocus();
      }
      if (!t1.getText().equals("bank") && p1.getText().equals("bank")) {
        JOptionPane.showMessageDialog(null, "UserName filed are wrong", "Error", JOptionPane.ERROR_MESSAGE);
        t1.setText("");
        p1.setText("");
        t1.requestFocus();
      }

    }
    if (e.getSource() == b2) {
      System.exit(0);
    }
  }

  public static void main(String a[]) {
    new login();
  }
}
