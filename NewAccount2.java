import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class NewAccount2 extends JInternalFrame implements ActionListener {

    private JPanel jpInfo = new JPanel();
    private JLabel lbNo, lbName, lbDate, lbAddress, lbId, lbDeposit, lbPass;
    private JTextField txtNo, txtName, txtDeposit, txtAddress, txtId;
    private JPasswordField txtPass;
    private JComboBox cboMonth, cboDay, cboYear;
    private JButton btnSave, btnCancel;

    private int count = 0;
    private int rows = 0;
    private int total = 0;

    // String Type Array use to Load Records From File.
    private String records[][] = new String[500][9];

    // String Type Array use to Save Records into File.
    private String saves[][] = new String[500][9];

    private FileInputStream fis;
    private DataInputStream dis;

    NewAccount2() {

        // super(Title, Resizable, Closable, Maximizable, Iconifiable)
        super("Create New Account", false, true, false, true);
        setSize(450, 420);

        jpInfo.setBounds(0, 0, 500, 115);
        jpInfo.setLayout(null);

        lbNo = new JLabel("Account No:");
        lbNo.setForeground(Color.black);
        lbNo.setBounds(15, 15, 150, 35);
        lbNo.setFont(new Font("Montserrat", Font.PLAIN, 20));

        lbName = new JLabel("Person Name:");
        lbName.setForeground(Color.black);
        lbName.setBounds(15, 60, 150, 35);
        lbName.setFont(new Font("Montserrat", Font.PLAIN, 20));

        lbDate = new JLabel("Deposit Date:");
        lbDate.setForeground(Color.black);
        lbDate.setBounds(15, 105, 150, 35);
        lbDate.setFont(new Font("Montserrat", Font.PLAIN, 20));

        lbAddress = new JLabel("Address:");
        lbAddress.setForeground(Color.black);
        lbAddress.setBounds(15, 150, 150, 35);
        lbAddress.setFont(new Font("Montserrat", Font.PLAIN, 20));

        lbId = new JLabel("Aadhar:");
        lbId.setForeground(Color.black);
        lbId.setBounds(15, 195, 150, 35);
        lbId.setFont(new Font("Montserrat", Font.PLAIN, 20));

        lbDeposit = new JLabel("Dep. Amount:");
        lbDeposit.setForeground(Color.black);
        lbDeposit.setBounds(15, 240, 150, 35);
        lbDeposit.setFont(new Font("Montserrat", Font.PLAIN, 20));

        lbPass = new JLabel("Password:");
        lbPass.setForeground(Color.black);
        lbPass.setBounds(15, 290, 150, 35);
        lbPass.setFont(new Font("Montserrat", Font.PLAIN, 20));

        txtNo = new JTextField();
        txtNo.setBounds(170, 20, 250, 35);
        txtNo.setFont(new Font("Montserrat", Font.PLAIN, 15));

        txtName = new JTextField();
        txtName.setBounds(170, 60, 250, 35);
        txtName.setFont(new Font("Montserrat", Font.PLAIN, 15));

        txtAddress = new JTextField();
        txtAddress.setBounds(170, 150, 250, 35);
        txtAddress.setFont(new Font("Montserrat", Font.PLAIN, 15));

        txtId = new JTextField();
        txtId.setBounds(170, 195, 250, 35);
        txtId.setFont(new Font("Montserrat", Font.PLAIN, 15));

        txtDeposit = new JTextField();
        txtDeposit.setBounds(170, 240, 250, 35);
        txtDeposit.setFont(new Font("Montserrat", Font.PLAIN, 15));

        txtPass = new JPasswordField();
        txtPass.setBounds(170, 290, 250, 35);
        txtPass.setFont(new Font("Montserrat", Font.PLAIN, 15));

        // Restricting The User Input to only Numerics in Numeric TextBoxes.
        txtNo.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent ke) {
                char c = ke.getKeyChar();
                if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE)))) {
                    getToolkit().beep();
                    ke.consume();
                }
            }
        });
        txtDeposit.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent ke) {
                char c = ke.getKeyChar();
                if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE)))) {
                    getToolkit().beep();
                    ke.consume();
                }
            }
        });
        txtId.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent ke) {
                char c = ke.getKeyChar();
                if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE)))) {
                    getToolkit().beep();
                    ke.consume();
                }
            }
        });

        // Creating Date Option.
        String Months[] = { "JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE",
                "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER" };
        cboMonth = new JComboBox(Months);
        cboDay = new JComboBox();
        cboYear = new JComboBox();
        for (int i = 1; i <= 31; i++) {
            String days = "" + i;
            cboDay.addItem(days);
        }
        for (int i = 2000; i <= 2025; i++) {
            String years = "" + i;
            cboYear.addItem(years);
        }

        if (cboMonth.getSelectedItem().equals("FEBRUARY")) {
            if (cboDay.getSelectedItem().equals("30")) {
                JOptionPane.showMessageDialog(this, "Please! Provide a valid date.",
                        "BankSystem - EmptyField", JOptionPane.ERROR_MESSAGE);
                cboDay.requestFocus();
                cboMonth.requestFocus();
            }
        }

        // Aligning The Date Option Controls.
        cboMonth.setBounds(170, 105, 95, 35);
        cboDay.setBounds(268, 105, 73, 35);
        cboYear.setBounds(344, 105, 76, 35);

        // Aligning The Buttons.
        btnSave = new JButton("Save");
        btnSave.setBounds(75, 340, 120, 35);
        btnSave.addActionListener(this);
        btnSave.setFont(new Font("Tahoma", Font.PLAIN, 15));

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(245, 340, 120, 35);
        btnCancel.addActionListener(this);
        btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));

        btnSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSave.setBackground(Color.lightGray);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSave.setBackground(UIManager.getColor("control"));
            }
        });

        // Adding the All the Controls to Panel.
        jpInfo.add(lbNo);
        jpInfo.add(txtNo);
        jpInfo.add(lbName);
        jpInfo.add(txtName);
        jpInfo.add(lbDate);
        jpInfo.add(cboMonth);
        jpInfo.add(cboDay);
        jpInfo.add(cboYear);
        jpInfo.add(lbAddress);
        jpInfo.add(txtAddress);
        jpInfo.add(lbId);
        jpInfo.add(txtId);
        jpInfo.add(lbDeposit);
        jpInfo.add(txtDeposit);
        jpInfo.add(btnSave);
        jpInfo.add(btnCancel);
        jpInfo.add(lbPass);
        jpInfo.add(txtPass);

        jpInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        jpInfo.setAlignmentY(Component.CENTER_ALIGNMENT);
        // Adding Panel to Window.
        getContentPane().add(jpInfo);

        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getWidth()) / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 3);

        // In the End Showing the New Account Window.
        setVisible(true);

    


    btnSave.addActionListener(new ActionListener() 
    {
    // Function use By Buttons of Window to Perform Action as User Click Them.
    public void actionPerformed(ActionEvent ae) {

                String acc_no = txtNo.getText();
                String name = txtName.getText();
                String address = txtAddress.getText();
                String id = txtId.getText();
                String balance = txtDeposit.getText();
                String password = txtPass.getText();

                // creating one object
                my_update2 abc = new my_update2();
                abc.my_db_update2(acc_no, name, address, id, balance, password);

            }
        });
    
    btnCancel.addActionListener(new ActionListener()   {
        public void actionPerformed(ActionEvent ae) {
            txtNo.setText("");
            txtName.setText("");
            txtDeposit.setText("");
            txtAddress.setText("");
            txtId.setText("");
            txtPass.setText("");
            txtNo.requestFocus();
            setVisible(true);
        }

    
    });

}

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    } 
}
    
