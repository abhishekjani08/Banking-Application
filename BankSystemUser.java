import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.*;
import java.io.*;

public class BankSystemUser extends JFrame implements ActionListener, ItemListener {

	private JDesktopPane desktop = new JDesktopPane();

	private JMenuBar bar;

	// All the Main Menu of the Program.
	private JMenu mnuFile, mnuEdit, mnuView, mnuWin,mnuothers, mnuHelp;
	private JMenuItem addNew, printRec, end; 
	private JMenuItem deposit, withdraw, delRec, search; 
	private JMenuItem allCustomer; 
	private JMenuItem close, closeAll; 
	private JMenuItem kit;
	private JMenuItem about; 

	// PopupMenu of Program.
	private JPopupMenu popMenu = new JPopupMenu();

	// MenuItems for PopupMenu of the Program.
	private JMenuItem open, report, dep, with, del, find,all;

	// For Program's ToolBar.
	private JToolBar toolBar;

	// For ToolBar's Button.
	private JButton btnNew, btnDep, btnWith, btnRec, btnDel, btnSrch;

	// Main Form StatusBar where Program's Name & Welcome Message Display.
	private JPanel statusBar = new JPanel();

	// Labels for Displaying Program's Name & saying Welcome to Current User on
	// StatusBar.
	private JLabel welcome;
	// private JLabel author;

	// Getting the Current System Date.
	private java.util.Date currDate = new java.util.Date();
	SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyy hh:mm:ss a");
	private String d = s.format(currDate);

	// Following all Variables are use in BankSystem's IO's.

	// Variable use in Reading the BankSystem Records File & Store it in an Array.
	private int rows = 0;
	private int total = 0;

	// String Type Array use to Load Records From File.
	private String records[][] = new String[500][8];

	// Variable for Reading the BankSystem Records File.
	private FileInputStream fis;
	private DataInputStream dis;

	// Constructor of The Bank Program to Iniatilize all Variables of Program.

	public BankSystemUser() {

		// Setting Program's Title.
		super("Banking App JAVA MINIPROJECT");
		UIManager.addPropertyChangeListener(new UISwitchListener((JComponent) getRootPane()));

		// Creating the MenuBar.
		bar = new JMenuBar();
		bar.setSize(100,200);
		
		Font f = new Font("Montserrat", Font.PLAIN, 25);
		UIManager.put("Menu.font", f);

		// Setting the Main Window of Program.
		setIconImage(getToolkit().getImage("Images/Bank.gif"));
		setSize(1545, 830);
		setJMenuBar(bar);

		// Closing Code of Main Window.
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				quitApp();
			}
		});

		// Creating the MenuBar Items.
		mnuFile = new JMenu("File");
		mnuEdit = new JMenu("Edit");
		mnuView = new JMenu("View");
		mnuWin = new JMenu("Window");
		mnuothers = new JMenu("Other Services");
		mnuHelp = new JMenu("Help");
		Font abc = new Font("Montserrat", Font.PLAIN, 20);
		UIManager.put("MenuItem.font", abc);

		// Creating the MenuItems of Program.
		// MenuItems for FileMenu.
	
		// addNew = new JMenuItem("Open New Account", new ImageIcon("Images/newacc.png"));
		// addNew.addActionListener(this);
		printRec = new JMenuItem("Print Customer Balance", new ImageIcon("Images/New.gif"));
		printRec.addActionListener(this);
		end = new JMenuItem("Quit BankSystem ?", new ImageIcon("Images/export.gif"));
		end.addActionListener(this);

		// MenuItems for EditMenu.
		deposit = new JMenuItem("Deposit Money", new ImageIcon("Images/3menu.png"));
		deposit.addActionListener(this);
		withdraw = new JMenuItem("Withdraw Money", new ImageIcon("Images/2menu.png"));
		withdraw.addActionListener(this);
		delRec = new JMenuItem("Delete Customer", new ImageIcon("Images/1menu.png"));
		delRec.addActionListener(this);
		search = new JMenuItem("Search By No.", new ImageIcon("Images/searchmenu.gif"));
		search.addActionListener(this);
		
		allCustomer = new JMenuItem("View Your Account", new ImageIcon("Images/refresh.gif"));
		allCustomer.addActionListener(this);

		// MenuItems for WindowMenu.
		close = new JMenuItem("Close Active Window");
		close.addActionListener(this);
		closeAll = new JMenuItem("Close All Windows...");
		closeAll.addActionListener(this);

		// MenuItems for Others Menu.
		kit = new JMenuItem("Bank Kit",new ImageIcon("Images/kit.png"));
		kit.addActionListener(this);

		// MenuItems for HelpMenu.
		about = new JMenuItem("About BankSystem", new ImageIcon("Images/Save.gif"));
		about.addActionListener(this);

		// Adding MenuItems to Menu.
		// File Menu Items.
		// mnuFile.add(addNew);
		// mnuFile.addSeparator();
		mnuFile.add(printRec);
		mnuFile.addSeparator();
		mnuFile.add(end);

		// Edit Menu Items.
		mnuEdit.add(deposit);
		mnuEdit.addSeparator();
		mnuEdit.add(withdraw);
		mnuEdit.addSeparator();
		mnuEdit.add(delRec);
		mnuEdit.addSeparator();
		mnuEdit.add(search);

		// View Menu Items.
		mnuView.add(allCustomer);

		// Window Menu Items.
		mnuWin.add(close);
		mnuWin.add(closeAll);

		//Others Menu Items.
		mnuothers.add(kit);

		// Help Menu Items.
		mnuHelp.add(about);

		// Adding Menues to Bar.
		bar.add(mnuFile);
		bar.add(new JMenu(""));
		bar.add(mnuEdit);
		bar.add(new JMenu(""));
		bar.add(mnuView);
		bar.add(new JMenu(""));
		bar.add(mnuWin);
		bar.add(new JMenu(""));
		bar.add(mnuothers);
		bar.add(new JMenu(""));
		bar.add(mnuHelp);

		// MenuItems for PopupMenu.
		// open = new JMenuItem("Open New Account", new ImageIcon("Images/xyz.gif"));
		// open.addActionListener(this);
		// open.setSize(100,50);

		report = new JMenuItem("Print BankSystem Report", new ImageIcon("Images/New.gif"));
		report.addActionListener(this);
		dep = new JMenuItem("Deposit Money", new ImageIcon("Images/3menu.png"));
		dep.addActionListener(this);
		with = new JMenuItem("Withdraw Money", new ImageIcon("Images/2menu.png"));
		with.addActionListener(this);
		del = new JMenuItem("Delete Customer", new ImageIcon("Images/1menu.png"));
		del.addActionListener(this);
		all = new JMenuItem("View Your Account",new ImageIcon("Images/searchmenu.png"));
		all.addActionListener(this);

		// Adding Menues to PopupMenu.
		popMenu.setSize(150,100);
		// popMenu.add(open);
		popMenu.add(report);
		popMenu.add(dep);
		popMenu.add(with);
		popMenu.add(del);
		popMenu.add(all);

		// Following Procedure display the PopupMenu of Program.
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				checkMouseTrigger(me);
			}

			public void mouseReleased(MouseEvent me) {
				checkMouseTrigger(me);
			}

			private void checkMouseTrigger(MouseEvent me) {
				if (me.isPopupTrigger())
					popMenu.show(me.getComponent(), me.getX(), me.getY());
			}
		});

		// Creating the ToolBar's Buttons of Program.
		// btnNew = new JButton(new ImageIcon("Images/xyz.png"));
		// btnNew.setToolTipText("Create New Account");
		// btnNew.addActionListener(this);

		btnDep = new JButton(new ImageIcon("Images/3.png"));
		btnDep.setToolTipText("Deposit Money");
		btnDep.addActionListener(this);
		btnWith = new JButton(new ImageIcon("Images/2.png"));
		btnWith.setToolTipText("Withdraw Money");
		btnWith.addActionListener(this);
		btnRec = new JButton(new ImageIcon("Images/newbtn.gif"));
		btnRec.setToolTipText("Print Customer Balance");
		btnRec.addActionListener(this);
		btnDel = new JButton(new ImageIcon("Images/1.png"));
		btnDel.setToolTipText("Delete Customer");
		btnDel.addActionListener(this);
		btnSrch = new JButton(new ImageIcon("Images/Search.gif"));
		btnSrch.setToolTipText("Search Customer");
		btnSrch.addActionListener(this);

		// Creating the ToolBar of Program.
		toolBar = new JToolBar();
		// toolBar.add(btnNew);
		toolBar.add(btnDep);
		toolBar.add(btnWith);
		toolBar.add(btnRec);
		toolBar.add(btnDel);
		toolBar.add(btnSrch);

		// Creating the StatusBar of Program.
		welcome = new JLabel("Welcome Today is " + d + " ", JLabel.RIGHT);
		welcome.setForeground(Color.black);
		welcome.setToolTipText("Welcoming the User & System Current Date");
		statusBar.setLayout(new BorderLayout());
		// statusBar.add (author, BorderLayout.WEST);
		statusBar.add(welcome, BorderLayout.EAST);


		statusBar.setBackground(new java.awt.Color(204, 255, 204));
		statusBar.setBorder(new javax.swing.border.MatteBorder(null));
		statusBar.setForeground(new java.awt.Color(0, 255, 204));

		desktop.setBackground(new java.awt.Color(204, 255, 204));
		desktop.setBorder(new javax.swing.border.MatteBorder(null));
		desktop.setForeground(new java.awt.Color(0, 255, 204));


		// For Making the Dragging Speed of Internal Frames Faster.
		desktop.putClientProperty("JDesktopPane.dragMode", "outline");

		// Setting the Contents of Programs.
		getContentPane().add(toolBar, BorderLayout.NORTH);
		getContentPane().add(desktop, BorderLayout.CENTER);
		getContentPane().add(statusBar, BorderLayout.SOUTH);

		// Showing The Main Form of Application.
		setVisible(true);

	}

	// Function For Performing different Actions By Menus of Program.

	public void actionPerformed(ActionEvent ae) {

		Object obj = ae.getSource();

		if (obj == addNew || obj == open || obj == btnNew) {

			boolean b = openChildWindow("Create New Account");
			if (b == false) {
				NewAccount newAcc = new NewAccount();
				desktop.add(newAcc);
				newAcc.show();
			}

		} else if (obj == end) {

			quitApp();

		} else if (obj == deposit || obj == dep || obj == btnDep) {

			boolean b = openChildWindow("Deposit Money");
			if (b == false) {
				DepositMoney depMon = new DepositMoney();
				desktop.add(depMon);
				depMon.show();
			}

		} else if (obj == withdraw || obj == with || obj == btnWith) {

			boolean b = openChildWindow("Withdraw Money");
			if (b == false) {
				WithdrawMoney withMon = new WithdrawMoney();
				desktop.add(withMon);
				withMon.show();
			}

		} else if (obj == delRec || obj == del || obj == btnDel) {

			boolean b = openChildWindow("Delete Account Holder");
			if (b == false) {
				DeleteCustomer delCus = new DeleteCustomer();
				desktop.add(delCus);
				delCus.show();
			}

		} else if (obj == search || obj == find || obj == btnSrch) {

			boolean b = openChildWindow("Search Customer [By No.]");
			if (b == false) {
				FindAccount fndAcc = new FindAccount();
				desktop.add(fndAcc);
				fndAcc.show();
			}

		} else if (obj == allCustomer || obj == all) {

			boolean b = openChildWindow("Your Account details");
			if (b == false) {
				FindAccount fndAcc = new FindAccount();
				desktop.add(fndAcc);
				fndAcc.show();
			}

		} else if (obj == close) {

			try {
				desktop.getSelectedFrame().setClosed(true);
			} catch (Exception CloseExc) {
			}

		} else if (obj == closeAll) {

			JInternalFrame Frames[] = desktop.getAllFrames(); // Getting all Open Frames.
			for (int getFrameLoop = 0; getFrameLoop < Frames.length; getFrameLoop++) {
				try {
					Frames[getFrameLoop].setClosed(true); // Close the frame.
				} catch (Exception CloseExc) {
				} // if we can't close it then we have a problem.
			}

		}
		else if (obj == kit) {

			boolean b = openChildWindow("Bank Kit");
			if (b == false) {
				Bankkit kitbank = new Bankkit();
				desktop.add(kitbank);
				kitbank.show();

			}

		else if (obj == about) {

			String abbb= "Banking App JAVA MINIPROJECT.\n\n" + "Created & Designed By:\n"
					+ "Abhishek Jani\nZaid Khan\nJayesh Khandare\nSaurabh Jagtap\n\n"
					+ "E-mail me:\n abhishekdarshanjani@gmail.com";
			JOptionPane.showMessageDialog(this,abbb, "About BankSystem", JOptionPane.PLAIN_MESSAGE);

		}
	}
	}



	// Function For Closing the Program.

	private void quitApp() {

		try {
			// Show a Confirmation Dialog.
			int reply = JOptionPane.showConfirmDialog(this, "Are you really want to exit\nFrom BankSystem?",
					"BankSystem - Exit", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
			// Check the User Selection.
			if (reply == JOptionPane.YES_OPTION) {
				setVisible(false); // Hide the Frame.
				dispose(); // Free the System Resources.
				System.out.println("Thanks for Using BankSystem");
				System.exit(0); // Close the Application.
			} else if (reply == JOptionPane.NO_OPTION) {
				setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			}
		}
		catch (Exception e) {
		}

	}


	// Loop Through All the Opened JInternalFrame to Perform the Task.

	private boolean openChildWindow(String title) {

		JInternalFrame[] childs = desktop.getAllFrames();
		for (int i = 0; i < childs.length; i++) {
			if (childs[i].getTitle().equalsIgnoreCase(title)) {
				childs[i].show();
				return true;
			}
		}
		return false;

	}

	// Function use to load all Records from File when Application Execute.

	boolean populateArray() {

		boolean b = false;
		try {
			fis = new FileInputStream("Bank.dat");
			dis = new DataInputStream(fis);
			// Loop to Populate the Array.
			while (true) {
				for (int i = 0; i < 6; i++) {
					records[rows][i] = dis.readUTF();
				}
				rows++;
			}
		} catch (Exception ex) {
			total = rows;
			if (total == 0) {
				JOptionPane.showMessageDialog(null, "Records File is Empty.\nEnter Records First to Display.",
						"BankSystem - EmptyFile", JOptionPane.PLAIN_MESSAGE);
				b = false;
			} else {
				b = true;
				try {
					dis.close();
					fis.close();
				} catch (Exception exp) {
				}
			}
		}
		return b;

	}


	public static void main(String args[]) {
		new BankSystemUser();
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}

}
