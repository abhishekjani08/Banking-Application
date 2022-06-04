import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class FindAccount extends JInternalFrame implements ActionListener {

	private JPanel jpFind = new JPanel();
	private JLabel lbNo, lbName, lbDate, lbBal,lbPass;
	private JTextField txtNo, txtName, txtDate, txtBal, txtPass;
	private JButton btnFind, btnCancel;

	private int rows = 0;
	private int total = 0;

	// String Type Array use to Load Records From File.
	private String records[][] = new String[500][9];
	private FileInputStream fis;
	private DataInputStream dis;

	FindAccount() {

		super("View Balance", false, true, false, true);
		setSize(500, 350);

		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getWidth()) / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 3);

		jpFind.setLayout(null);

		lbNo = new JLabel("Account No:");
		lbNo.setForeground(Color.black);
		lbNo.setBounds(45, 20, 100, 45);
		lbNo.setFont(new Font("Montserrat", Font.BOLD, 15));

		lbPass = new JLabel("Password:");
		lbPass.setForeground(Color.black);;
		lbPass.setBounds(45, 60, 100, 45);
		lbPass.setFont(new Font("Montserrat", Font.BOLD, 15));

		lbName = new JLabel("Person Name:");
		lbName.setForeground(Color.black);
		lbName.setBounds(45, 100, 120, 45);
		lbName.setFont(new Font("Montserrat", Font.BOLD, 15));

		lbDate = new JLabel("Last Transaction:");
		lbDate.setForeground(Color.black);
		lbDate.setBounds(45, 140, 140,45);
		lbDate.setFont(new Font("Montserrat", Font.BOLD, 15));

		lbBal = new JLabel("Balance:");
		lbBal.setForeground(Color.black);
		lbBal.setBounds(45, 180, 100, 45);
		lbBal.setFont(new Font("Montserrat", Font.BOLD, 15));

		txtNo = new JTextField();
		txtNo.setBounds(195, 20, 230, 35);
		txtNo.setFont(new Font("Montserrat", Font.PLAIN, 15));

		txtPass = new JTextField();
		txtPass.setBounds(195, 60, 230, 35);
		txtPass.setFont(new Font("Montserrat", Font.PLAIN, 15));
		
		txtName = new JTextField();
		txtName.setEnabled(false);
		txtName.setBounds(195, 100, 230, 35);
		txtName.setFont(new Font("Montserrat", Font.BOLD, 15));

		txtDate = new JTextField();
		txtDate.setEnabled(false);
		txtDate.setBounds(195, 140, 230, 35);
		txtDate.setFont(new Font("Montserrat", Font.PLAIN, 15));

		txtBal = new JTextField();
		txtBal.setEnabled(false);
		txtBal.setBounds(195, 180, 230, 35);
		txtBal.setFont(new Font("Montserrat", Font.BOLD, 15));

		txtNo.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke) {
				char c = ke.getKeyChar();
				if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE)))) {
					getToolkit().beep();
					ke.consume();
				}
			}
		});

		btnFind = new JButton("Search");
		btnFind.setBounds(100, 255, 120, 45);
		btnFind.addActionListener(this);

		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(250, 255, 120, 45);
		btnCancel.addActionListener(this);

		jpFind.add(lbNo);
		jpFind.add(txtNo);
		jpFind.add(lbName);
		jpFind.add(txtName);
		jpFind.add(lbDate);
		jpFind.add(txtDate);
		jpFind.add(lbBal);
		jpFind.add(txtBal);
		jpFind.add(btnFind);
		jpFind.add(btnCancel);
		jpFind.add(txtPass);
		jpFind.add(lbPass);
		// Adding Panel to Window.
		getContentPane().add(jpFind);

		populateArray(); // Load All Existing Records in Memory.

		// In the End Showing the New Account Window.
		setVisible(true);

	}

	public void actionPerformed(ActionEvent ae) {

		Object obj = ae.getSource();

		if (obj == btnFind) {
			if (txtNo.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Please! Provide Id of Customer to Search.",
						"BankSystem - EmptyField", JOptionPane.PLAIN_MESSAGE);
				txtNo.requestFocus();
				txtPass.requestFocus();
			} else {
				rows = 0;
				populateArray(); // Load All Existing Records in Memory.
				findRec(); // Finding if Account No. Exist or Not.
			}
		}
		if (obj == btnCancel) {
			txtClear();
			setVisible(false);
			dispose();
		}

	}

	// Function use to load all Records from File when Application Execute.
	void populateArray() {

		try {
			fis = new FileInputStream("Bank.dat");
			dis = new DataInputStream(fis);
			// Loop to Populate the Array.
			while (true) {
				for (int i = 0; i < 9; i++) {
					records[rows][i] = dis.readUTF();
				}
				rows++;
			}
		} catch (Exception ex) {
			total = rows;
			if (total == 0) {
				JOptionPane.showMessageDialog(null, "Records File is Empty.\nEnter Records First to Display.",
						"BankSystem - EmptyFile", JOptionPane.PLAIN_MESSAGE);
				btnEnable();
			} else {
				try {
					dis.close();
					fis.close();
				} catch (Exception exp) {
				}
			}
		}

	}

	// Function use to Find Record by Matching the Contents of Records Array with ID
	// TextBox.
	void findRec() {

		boolean found = false;
		for (int x = 0; x < total; x++) {
			if (records[x][0].equals(txtNo.getText())) {
					if (records[x][8].equals(txtPass.getText())) {
				
				found = true;
				showRec(x);
				break;
					}
			}

		}

		if (found == false) {
			JOptionPane.showMessageDialog(this, "Account No. " + txtNo.getText() + " doesn't Exist.",
					"BankSystem - WrongNo", JOptionPane.PLAIN_MESSAGE);
			txtClear();
		}

	}

	// Function which display Record from Array onto the Form.
	public void showRec(int intRec) {

		txtNo.setText(records[intRec][0]);
		txtName.setText(records[intRec][1]);
		txtDate.setText(records[intRec][2] + ", " + records[intRec][3] + ", " + records[intRec][4]);
		txtPass.setText(records[intRec][8]);
		txtBal.setText(records[intRec][7]);

	}

	// Function use to Clear all TextFields of Window.
	void txtClear() {

		txtNo.setText("");
		txtName.setText("");
		txtDate.setText("");
		txtBal.setText("");
		txtPass.setText("");
		txtNo.requestFocus();
		txtPass.requestFocus();

	}

	// Function use to Lock Controls of Window.
	void btnEnable() {

		txtNo.setEnabled(false);
		btnFind.setEnabled(false);

	}

}