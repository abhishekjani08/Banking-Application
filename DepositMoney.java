import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class DepositMoney extends JInternalFrame implements ActionListener {

	private JPanel jpDep = new JPanel();
	private JLabel lbNo, lbName, lbDate, lbDeposit;
	private JTextField txtNo, txtName, txtDeposit;
	private JComboBox cboMonth, cboDay, cboYear;
	private JButton btnSave, btnCancel;

	private int recCount = 0;
	private int rows = 0;
	private	int total = 0;
	private	int curr;
	private	int deposit;

	//String Type Array use to Load Records From File.
	private String records[][] = new String [500][9];

	private FileInputStream fis;
	private DataInputStream dis;

	DepositMoney () {

		// super(Title, Resizable, Closable, Maximizable, Iconifiable)
		super ("Deposit Money", false, true, false, true);
		setSize (550, 380);

		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getWidth()) / 2,
		(Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 3);


		jpDep.setLayout (null);

		lbNo = new JLabel ("Account No:");
		lbNo.setForeground (Color.black);
		lbNo.setBounds (70, 30, 150, 35);
		lbNo.setFont(new Font("Montserrat", Font.PLAIN, 20));
	    
		lbName = new JLabel ("Person Name:");
		lbName.setForeground (Color.black);
	    lbName.setBounds (70, 85, 150, 35);
		lbName.setFont(new Font("Montserrat", Font.PLAIN, 20));

		lbDate = new JLabel ("Deposit Date:");
		lbDate.setForeground (Color.black);
		lbDate.setBounds (70, 135, 150, 35);
		lbDate.setFont(new Font("Montserrat", Font.PLAIN, 20));

		lbDeposit = new JLabel ("Dep. Amount:");
		lbDeposit.setForeground (Color.black);
		lbDeposit.setBounds (70, 185, 150, 35);
		lbDeposit.setFont(new Font("Montserrat", Font.PLAIN, 20));

		txtNo = new JTextField ();
		txtNo.setBounds (285, 30, 205, 35);
		txtNo.setFont(new Font("Montserrat", Font.PLAIN, 15));

		txtName = new JTextField ();
		txtName.setEnabled (false);
		txtName.setBounds (285, 85, 205, 35);
		txtName.setFont(new Font("Montserrat", Font.BOLD, 15));

		txtDeposit = new JTextField ();
		txtDeposit.setBounds (285, 185, 205, 35);
		txtDeposit.setFont(new Font("Montserrat", Font.PLAIN, 15));

		//Creating Date Option.
		String Months[] = {"January", "February", "March", "April", "May", "June",
			"July", "August", "September", "October", "November", "December"};
		cboMonth = new JComboBox (Months);
		cboDay = new JComboBox ();
		cboYear = new JComboBox ();
		for (int i = 1; i <= 31; i++) {
			String days = "" + i;
			cboDay.addItem (days);
		}
		for (int i = 2000; i <= 2015; i++) {
			String years = "" + i;
			cboYear.addItem (years);
		}

		//Aligning The Date Option Controls.
		cboMonth.setBounds (285, 135, 92, 35);
		cboDay.setBounds (382, 135, 43, 35);
		cboYear.setBounds (430, 135, 60, 35);

		//Aligning The Buttons.
		btnSave = new JButton ("Save");
		btnSave.setBounds (120, 265, 120, 45);
		btnSave.addActionListener (this);
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCancel = new JButton ("Cancel");
		btnCancel.setBounds (295,265, 120, 45);
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCancel.addActionListener (this);

		//Restricting The User Input to only Numerics in Numeric TextBoxes.
		txtNo.addKeyListener (new KeyAdapter() {
			public void keyTyped (KeyEvent ke) {
				char c = ke.getKeyChar ();
				if (!((Character.isDigit (c) || (c == KeyEvent.VK_BACK_SPACE)))) {
					getToolkit().beep ();
					ke.consume ();
      				}
    			}
  		}
		);
		txtDeposit.addKeyListener (new KeyAdapter() {
			public void keyTyped (KeyEvent ke) {
				char c = ke.getKeyChar ();
				if (!((Character.isDigit (c) || (c == KeyEvent.VK_BACK_SPACE)))) {
					getToolkit().beep ();
					ke.consume ();
      				}
    			}
  		}
		);
		//Checking the Accunt No. Provided By User on Lost Focus of the TextBox.
		txtNo.addFocusListener (new FocusListener () {
			public void focusGained (FocusEvent e) { }
			public void focusLost (FocusEvent fe) {
				if (txtNo.getText().equals ("")) { }
				else {
					rows = 0;
					populateArray ();	//Load All Existing Records in Memory.
					findRec ();		//Finding if Account No. Already Exist or Not.
				}
			}
		}
		);

		btnSave.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnSave.setBackground(Color.lightGray);
			}
			public void mouseExited(java.awt.event.MouseEvent evt) {
			btnSave.setBackground(UIManager.getColor("control"));
			}
		});

		//Adding the All the Controls to Panel.
		jpDep.add (lbNo);
		jpDep.add (txtNo);
		jpDep.add (lbName);
		jpDep.add (txtName);
		jpDep.add (lbDate);
		jpDep.add (cboMonth);
		jpDep.add (cboDay);
		jpDep.add (cboYear);
		jpDep.add (lbDeposit);
		jpDep.add (txtDeposit);
		jpDep.add (btnSave);
		jpDep.add (btnCancel);

		//Adding Panel to Window.
		getContentPane().add (jpDep);

		populateArray ();	//Load All Existing Records in Memory.

		//In the End Showing the New Account Window.
		setVisible (true);

	}

	//Function use By Buttons of Window to Perform Action as User Click Them.
	public void actionPerformed (ActionEvent ae) {

		Object obj = ae.getSource();

		if (obj == btnSave) {
			if (txtNo.getText().equals("")) {
				JOptionPane.showMessageDialog (this, "Please! Provide Id of Customer.",
						"BankSystem - EmptyField", JOptionPane.PLAIN_MESSAGE);
				txtNo.requestFocus();
			}
			else if (txtDeposit.getText().equals("")) {
				JOptionPane.showMessageDialog (this, "Please! Provide Deposit Amount.",
						"BankSystem - EmptyField", JOptionPane.PLAIN_MESSAGE);
				txtDeposit.requestFocus ();
			}
			else {
				editRec ();	//Update the Contents of Array.
			}
		}
		if (obj == btnCancel) {
			txtClear ();
			setVisible (false);
			dispose();
		}

	}

	//Function use to load all Records from File when Application Execute.
	void populateArray () {

		try {
			fis = new FileInputStream ("Bank.dat");
			dis = new DataInputStream (fis);
			//Loop to Populate the Array.
			while (true) {
				for (int i = 0; i < 9; i++) {
					records[rows][i] = dis.readUTF ();
				}
				rows++;
			}
		}
		catch (Exception ex) {
			total = rows;
			if (total == 0) {
				JOptionPane.showMessageDialog (null, "Records File is Empty.\nEnter Records First to Display.",
							"BankSystem - EmptyFile", JOptionPane.PLAIN_MESSAGE);
				btnEnable ();
			}
			else {
				try {
					dis.close();
					fis.close();
				}
				catch (Exception exp) { }
			}
		}

	}

	//Function use to Find Record by Matching the Contents of Records Array with ID TextBox.
	void findRec () {

		boolean found = false;
		for (int x = 0; x < total; x++) {
			if (records[x][0].equals (txtNo.getText())) {
				found = true;
				showRec (x);
				break;
			}
		}
		if (found == false) {
			String str = txtNo.getText ();
			txtClear ();
			JOptionPane.showMessageDialog (this, "Account No. " + str + " doesn't Exist.",
						"BankSystem - WrongNo", JOptionPane.PLAIN_MESSAGE);
		}

	}

	//Function which display Record from Array onto the Form.
	public void showRec (int intRec) {

		txtNo.setText (records[intRec][0]);
		txtName.setText (records[intRec][1]);
		curr = Integer.parseInt (records[intRec][7]);
		recCount = intRec;

	}

	//Function use to Clear all TextFields of Window.
	void txtClear () {

		txtNo.setText ("");
		txtName.setText ("");
		txtDeposit.setText ("");
		txtNo.requestFocus ();

	}

	//Function use to Edit an Element's Value of the Array.
	public void editRec () {

		deposit = Integer.parseInt (txtDeposit.getText ());
		records[recCount][0] = txtNo.getText ();
		records[recCount][1] = txtName.getText ();
		records[recCount][2] = "" + cboMonth.getSelectedItem ();
		records[recCount][3] = "" + cboDay.getSelectedItem ();
		records[recCount][4] = "" + cboYear.getSelectedItem ();
		records[recCount][7] = "" + (curr + deposit);
		editFile ();	//Save This Array to File.
	
	}

	//Function use to Save Records to File After editing the Record of User Choice.
	public void editFile () {

		try {
			FileOutputStream fos = new FileOutputStream ("Bank.dat");
			DataOutputStream dos = new DataOutputStream (fos);
			if (records != null) {
				for (int i = 0; i < total; i++) {
					for (int c = 0; c < 9; c++) {
						dos.writeUTF (records[i][c]);
						if (records[i][c] == null) break;
					}
				}
				JOptionPane.showMessageDialog (this, "The File is Updated Successfully",
						"BankSystem - Record Saved", JOptionPane.PLAIN_MESSAGE);
				txtClear ();
				dos.close();
				fos.close();
			}
		}
		catch (IOException ioe) {
			JOptionPane.showMessageDialog (this, "There are Some Problem with File",
						"BankSystem - Problem", JOptionPane.PLAIN_MESSAGE);
		}
	
	}

	//Function use to Lock all Buttons of Window.
	void btnEnable () {

		txtNo.setEnabled (false);
		cboMonth.setEnabled (false);
		cboDay.setEnabled (false);
		cboYear.setEnabled (false);
		txtDeposit.setEnabled (false);
		btnSave.setEnabled (false);

	}

}	