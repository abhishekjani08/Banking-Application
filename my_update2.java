import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class my_update2{
	
void  my_db_update2(String str1, String str2,String str3,String str4,String str5,String str6) {
  try
{  
	Class.forName("com.mysql.jdbc.Driver");  
 	
	Connection con=DriverManager.getConnection(  
	"jdbc:mysql://localhost:3306/swing_demo","root","root");
	Statement st=con.createStatement();  

	String query1="INSERT INTO `swing_demo`.`data`"
	 + " ('acc_no', 'name', 'address', 'id', 'balance', 'password')"
	 + "VALUES('" +str1+"','"+str2+"','"+str3+"','"+str4+"','"+str5+"','"+str6+"')";
	st.executeUpdate(query1);
	
	con.close();  
	
}
  catch(Exception e)
  { System.out.println(e);} 
	
  }

}

