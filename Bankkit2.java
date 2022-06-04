import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class Bankkit2 extends JInternalFrame implements ActionListener {

	private JPanel jpInfo = new JPanel();
	private JLabel lbNo, lbName, lbDate, lbAddress, lbId, lbDeposit;
	private JTextField txtNo, txtName, txtDeposit, txtAddress, txtId;
	private JComboBox cboMonth, cboDay, cboYear;
	private JButton btnSave, btnCancel;

	private int count = 0;
	private int rows = 0;
	private int total = 0;

	// String Type Array use to Load Records From File.
	private String records[][] = new String[500][8];

	// String Type Array use to Save Records into File.
	private String saves[][] = new String[500][8];

	private FileInputStream fis;
	private DataInputStream dis;

	Bankkit2() {

		// super(Title, Resizable, Closable, Maximizable, Iconifiable)
		super("Create New Account", false, true, false, true);
		setSize(450, 400);

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
		btnSave.setBounds(75, 300, 120, 35);
		btnSave.addActionListener(this);
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 15));

		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(245, 300, 120, 35);
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
		jpInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
		jpInfo.setAlignmentY(Component.CENTER_ALIGNMENT);
		// Adding Panel to Window.
		getContentPane().add(jpInfo);

		
		// In the End Showing the New Account Window.
		setVisible(true);

	}

	// Function use By Buttons of Window to Perform Action as User Click Them.
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();

		if (obj == btnSave) {
			if (txtNo.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Please! Provide Id of Customer.",
						"BankSystem - EmptyField", JOptionPane.PLAIN_MESSAGE);
				txtNo.requestFocus();
			} else if (txtName.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Please! Provide Name of Customer.",
						"BankSystem - EmptyField", JOptionPane.PLAIN_MESSAGE);
				txtName.requestFocus();
			} else if (txtAddress.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Please! Provide Your Address.",
						"BankSystem - EmptyField", JOptionPane.PLAIN_MESSAGE);
				txtAddress.requestFocus();
			} else if (txtId.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Please! Provide Aadhar Number.",
						"BankSystem - EmptyField", JOptionPane.PLAIN_MESSAGE);
				txtId.requestFocus();
			} else if (txtId.getText().length() != 12) {
				JOptionPane.showMessageDialog(this, "Please! Provide A valid UID Number.",
						"BankSystem - EmptyField", JOptionPane.WARNING_MESSAGE);
				txtId.requestFocus();
			} else if (txtDeposit.getText().length() <= 3) {
				JOptionPane.showMessageDialog(this,
						"Please! Minimum Rs.1000 deposit required while Creating a new account ",
						"BankSystem - EmptyField", JOptionPane.ERROR_MESSAGE);
				txtDeposit.requestFocus();
			} else 
            {
				populateArray(); // Load All Existing Records in Memory.
				findRec(); // Finding if Account No. Already Exist or Not.
			}
		}
		if (obj == btnCancel) {
			txtClear();
			setVisible(false);
			dispose();
		}

	}

	// //Function use to Clear all TextFields of Window.
	void txtClear() {

		txtNo.setText("");
		txtName.setText("");
		txtDeposit.setText("");
		txtAddress.setText("");
		txtId.setText("");
		txtNo.requestFocus();
	}

	// Function use to load all Records from File when Application Execute.
	void populateArray() {

		try {
			fis = new FileInputStream("Bank.dat");
			dis = new DataInputStream(fis);
			// Loop to Populate the Array.
			while (true) {
				for (int i = 0; i < 8; i++) {
					records[rows][i] = dis.readUTF();
				}
				rows++;
			}
		} catch (Exception ex) {
			total = rows;
			if (total == 0) {
			} else {
				try {
					dis.close();
					fis.close();
				} catch (Exception exp) {
				}
			}
		}

	}

	// //Function use to Find Record by Matching the Contents of Records Array with
	// ID TextBox.
	void findRec() {

		boolean found = false;
		for (int x = 0; x < total; x++) {
			if (records[x][0].equals(txtNo.getText())) {
				found = true;
				JOptionPane.showMessageDialog(this, "Account No. " + txtNo.getText() + " is Already Exist.",
						"BankSystem - WrongNo", JOptionPane.PLAIN_MESSAGE);
				txtClear();
				break;
			}
		}
		if (found == false) {
			saveArray();
		}

	}

	// //Function use to add new Element to Array.
	void saveArray() {

		saves[count][0] = txtNo.getText();
		saves[count][1] = txtName.getText();
		saves[count][2] = "" + cboMonth.getSelectedItem();
		saves[count][3] = "" + cboDay.getSelectedItem();
		saves[count][4] = "" + cboYear.getSelectedItem();
		saves[count][5] = txtAddress.getText();
		saves[count][6] = txtId.getText();
		saves[count][7] = txtDeposit.getText();
		saveFile(); // Save This Array to File.
		count++;

	}

	// //Function use to Save new Record to the File.
	void saveFile() {

		try {
			FileOutputStream fos = new FileOutputStream("Bank.dat", true);
			DataOutputStream dos = new DataOutputStream(fos);
			dos.writeUTF(saves[count][0]);
			dos.writeUTF(saves[count][1]);
			dos.writeUTF(saves[count][2]);
			dos.writeUTF(saves[count][3]);
			dos.writeUTF(saves[count][4]);
			dos.writeUTF(saves[count][5]);
			dos.writeUTF(saves[count][6]);
			dos.writeUTF(saves[count][7]);
			JOptionPane.showMessageDialog(this, "The Record has been Saved Successfully",
					"BankSystem - Record Saved", JOptionPane.PLAIN_MESSAGE);
			txtClear();
			dos.close();
			fos.close();
		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(this, "There are Some Problem with File",
					"BankSystem - Problem", JOptionPane.PLAIN_MESSAGE);
		}

	}

}
