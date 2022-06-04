import javax.swing.*;
import java.io.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class ViewCustomer extends JInternalFrame {

	private JPanel jpShow = new JPanel ();

	private DefaultTableModel dtmCustomer;
	private JTable tbCustomer;
	private JScrollPane jspTable;

	private int row = 0;
	private int total = 0;

	//String Type Array use to Load Records into File.
	private String rowData[][];

	private FileInputStream fis;
	private DataInputStream dis;

	ViewCustomer () {

		//super(Title, Resizable, Closable, Maximizable, Iconifiable)
		super ("View All Account Holders", false, true, false, true);
		setSize (900, 350);

		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getWidth()) / 2,
		(Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 3);


		jpShow.setLayout (null);

		populateArray ();

		tbCustomer = makeTable ();
		jspTable = new JScrollPane (tbCustomer);
		jspTable.setBounds (20, 20, 850, 300);
		//Adding the Table to Panel.
		jpShow.add (jspTable);

		//Adding Panel to Window.
		getContentPane().add (jpShow);

		//In the End Showing the New Account Window.
		setVisible (true);

	}

	//Function use to load all Records from File when Window Open.
	void populateArray () {
		
		Font f = new Font("ARIAL", Font.PLAIN, 17);
		UIManager.put("Table.font", f);
		//String Type Array use to Load Records into File.
		String rows[][] = new String [500][9];
		try {
			fis = new FileInputStream ("Bank.dat");
			dis = new DataInputStream (fis);
			//Loop to Populate the Array.
			while (true) {
				for (int i = 0; i < 9; i++) {
					rows[row][i] = dis.readUTF ();
				}
				row++;
			}
		}
		catch (Exception ex) {
			total = row;
			rowData = new String [total][9];
			if (total == 0) {
				JOptionPane.showMessageDialog (null, "Records File is Empty.\nEnter Records to Display.",
							"BankSystem - EmptyFile", JOptionPane.PLAIN_MESSAGE);
			}
			else {
				for (int i = 0; i < total; i++) {
					rowData[i][0] = rows[i][0];
					rowData[i][1] = rows[i][1];
					rowData[i][2] = rows[i][2] + ", " + rows[i][3] + ", " + rows[i][4];
					rowData[i][3] = rows[i][5];
					rowData[i][4] = rows[i][6];
					rowData[i][5] = rows[i][7];
					rowData[i][6] = rows[i][8];
				}
				try {
					dis.close();
					fis.close();
				}
				catch (Exception exp) { }
			}
		}

	}

	//Function to Create the Table and Add Data to Show.
	private JTable makeTable () {

		//String Type Array use to Give Table Column Names.
		String cols[] = {"Account No.", "Name", "Opening Date", "Address","Aadhar","Bank Balance","Password"};
		
		Font bc = new Font("ARIAL", Font.PLAIN, 17);
		UIManager.put("cols.font", bc);
		
		dtmCustomer  = new DefaultTableModel (rowData, cols);
		tbCustomer = new JTable (dtmCustomer) {
			public boolean isCellEditable (int iRow, int iCol) {
				
				return false;	//Disable All Columns of Table.
			}
		};
		
		//Sizing the Columns of Table.	
		(tbCustomer.getColumnModel().getColumn(0)).setPreferredWidth (180);
		(tbCustomer.getColumnModel().getColumn(1)).setPreferredWidth (200);
		(tbCustomer.getColumnModel().getColumn(2)).setPreferredWidth (320);
		(tbCustomer.getColumnModel().getColumn(3)).setPreferredWidth (285);
		(tbCustomer.getColumnModel().getColumn(4)).setPreferredWidth (285);
		(tbCustomer.getColumnModel().getColumn(5)).setPreferredWidth (280);
		(tbCustomer.getColumnModel().getColumn(6)).setPreferredWidth (280);
		tbCustomer.setRowHeight (40);
		tbCustomer.getTableHeader().setFont( new Font( "Arial" , Font.BOLD, 13));
		tbCustomer.setFont(new Font("Montserrat", Font.PLAIN, 17));
		tbCustomer.setSelectionMode (ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		return tbCustomer;
	}
}