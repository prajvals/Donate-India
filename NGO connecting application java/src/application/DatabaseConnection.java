package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
	
class InsertFunctions{
		ResultSet myRs=null;
		public Connection myConnection;
		public Statement myStatement;
			void setConnection()
			{
				try {
					myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","password");
					  myStatement = myConnection.createStatement();	
				}
				catch(SQLException e)
				{
					System.out.println("Something went wrong");
				}
				
			}
			void insertNGODetails(int ngoOwnerId,String ngoName,String ngoStreet,String City,int ngoPincode,String ngoKOR,String string,String ngoInfo)
			{	try {
				myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","password");
				PreparedStatement preparedStatement = myConnection.prepareStatement("insert into NGO(ngo_owner_id,ngo_name,street,city,pincode,kind_of_request,central_phone_no,ngo_info) values("+ngoOwnerId +","+"\"" +  ngoName +"\"" + ","+"\"" + ngoStreet+ "\"" + ","+"\"" +City +"\"" + ","+ ngoPincode+"," + "\"" + ngoKOR +"\"" +","+ "\"" + string + "\"" +"," +"\"" +ngoInfo+"\"" +");");
				preparedStatement.execute();
			}
				catch(SQLException e)
			{
					System.out.println(e.getMessage());
			}
			}
			void insertUserDetails(int user_id,String user_fname,String user_lname,String street,String City,int pincode)
			{
				try {
				myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","password");
				PreparedStatement preparedStatement = myConnection.prepareStatement("insert into users values(" + user_id +","+"\"" + user_fname + "\""  +  "," + "\"" + user_lname + "\""  + "," + "\""  +street + "\"" + ","+ "\"" + City + "\"" + "," + pincode + ");");
				boolean result = preparedStatement.execute();
				if(result)
				{
					System.out.println("Values successfully entered");
				}
				else {
					System.out.println("Values were not enterd what to do?");
				}
				}
				catch(SQLException e)
				{
					System.out.println(e.getMessage());
				}
			}
	}
	 class CollectionFunctions {
		//this contains all the required functions which are needed to be added
		//in our code, and the call for these should be made from the event handlers
		ResultSet myRs=null;
		Connection con;
		PreparedStatement myStatement;
			CollectionFunctions()
			{
				String password = "password";
				String userName = "root";
				String path = "jdbc:mysql://localhost:3306/project";
				try {
					 con = DriverManager.getConnection(path,userName,password);
					 
				}
				catch(SQLException e)
				{
					System.out.println("Something went wrong");
				}
			}
			void runCustomQuery(String query) throws SQLException
			{
				Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","password");
				PreparedStatement ps = myConnection.prepareStatement(query);
				myRs = ps.executeQuery();
				
			}
			 void ngo_more_details(int ngo_owner_id,Connection myConnection ,PreparedStatement myStatement,ResultSet myRs)
			{
				//this is for the more information on ngo button
				//i guess the connection will be done in the main code so skiping the peice of code
				try {
				myStatement=myConnection.prepareStatement("select n.ngo_name,n.ngo_owner_id,n.central_office,n.central_phoneno,c.certification_no,c.certifyingauthiority from ngo n,certification c where c.ngo_owner_id=" + ngo_owner_id + "and n.ngo_owner_id="+ "ngo_owner_id");
				myStatement.setDouble(1,ngo_owner_id);
				myStatement.setDouble(2,ngo_owner_id);
				myRs=myStatement.executeQuery();
				
				System.out.println("NGO NAME"+" "+"NGO ID"+"");
				while(myRs.next())
				{
					System.out.println(myRs.getString("ngo_name")+","+myRs.getString("ngo_owner_id")+","+myRs.getString("central_office")+","+myRs.getString("central_phoneno")+","+myRs.getString("certification_no")+","+myRs.getString("certifyingauthority"));    
				
			}
				}
				catch(SQLException e)
				{
					System.out.println(e.getMessage());
				}
			}
			
			void user_ngo_search(String city, String kind_of_donation) throws SQLException
			{
				Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","password");
				//this gives the information when submit button is pressed when the user submits the kind of donation he wants to make
				myStatement=myConnection.prepareStatement("select n.ngo_name,n.ngo_owner_id,n.central_phone_no from ngo n where ?=n.kind_of_request and ?=n.city");
				myStatement.setString(1,kind_of_donation);
				myStatement.setString(2,city);
				myRs=myStatement.executeQuery();
				
				while(myRs.next())
				{
					System.out.println(myRs.getString("ngo_name")+","+myRs.getString("ngo_owner_id")+","+myRs.getString("central_phone_no"
							+ ""));
				}
				  
			}
			
		    void ngo_projects(int ngo_owner_id,Connection myConnection,PreparedStatement myStatement,ResultSet myRs) throws SQLException
			{
			//this is when more inforamtion about the ngo projects is required
			myStatement=myConnection.prepareStatement("select * from projects p, ngo n where ?=p.ngo_owner_id and n.ngo_owner_id=?");
			myStatement.setDouble(1,ngo_owner_id);
			myStatement.setDouble(2,ngo_owner_id);
			myRs=myStatement.executeQuery();
			 
			 while(myRs.next())
			 {
			 	System.out.println(myRs.getString("project_name")+","+myRs.getString("project_id")+","+myRs.getString("city"));
			 } 
			 //i believe we can remove the city thing, the projects are basically not based in some cities only, so yeah we can remove that
			}
			
			 void information_ngo_employee(int ngo_owner_id,Connection myConnection,PreparedStatement myStatement,ResultSet myRs)
			{
				// this is for the employee data seeing by the ngo, see for every edit operation, the exsisting values need to be  ed
				//these values are  ed this way
				try {
			myStatement=myConnection.prepareStatement("select * from employee as e join ngo as n on ?=e.ngo_owner_id and n.ngo_owner_id=?");
			myStatement.setDouble(1,ngo_owner_id);
			myStatement.setDouble(2,ngo_owner_id);
			myRs=myStatement.executeQuery();
			  
			  while(myRs.next())
			 {
			 	System.out.println(myRs.getString("emp_id")+","+myRs.getString("emp_name")+","+myRs.getString("office_id"));
			 }
				}
				catch(SQLException e)
				{
					System.out.println(e.getMessage());
				}
			}
			void project_name(int ngo_owner_id,Connection myConnection,PreparedStatement myStatement,ResultSet myRs) throws SQLException
			{
				myStatement=myConnection.prepareStatement("select * from project where n.ngo_owner_id=?");
				myStatement.setDouble(1,ngo_owner_id);
				myRs=myStatement.executeQuery();
				  
			}
			//i have made two functions, whicever feels convivent for the ngo project   use that one
			
			void office_ (int ngo_owner_id,Connection myConnection,PreparedStatement myStatement,ResultSet myRs) throws SQLException
			{// this is to show the offices which are there in the ngo and their details
				myStatement=myConnection.prepareStatement("select * from offices o, ngo n where ?=o.ngo_owner_id and n.ngo_owner_id=?");
				myStatement.setDouble(1,ngo_owner_id);
				myStatement.setDouble(2,ngo_owner_id);
				myRs=myStatement.executeQuery();

		while(myRs.next())
			 {
			 	System.out.println(myRs.getString("ngo_owner_id")+","+myRs.getString("office_name")+","+myRs.getString("phone_number")+","+myRs.getString("city")+","+myRs.getString("street"));
			 }		  
		//bro here we have to change the street to area, because that would be more meaningful, or we can have one more attribute where we have the street and one area	 
			}
			void transaction_views(int ngo_owner_id,Connection myConnection,PreparedStatement myStatement,ResultSet myRs) throws SQLException
			{
				//this is to view the transactions of the ngo to be handlled by the view transactions in the ngo window
				myStatement=myConnection.prepareStatement("select * from transactions t, ngo n where ?=t.ngo_owner_id");
				myStatement.setDouble(1,ngo_owner_id);
				myRs=myStatement.executeQuery();
				  
				  while(myRs.next())
			 {
			 	System.out.println(myRs.getString("ngo_owner_id")+","+myRs.getString("transaction_id")+","+myRs.getString("from")+","+myRs.getString("to"));
			 }
			}
			//the board of direcctors function is not needed, 
			 void board_of_directors(int ngo_owner_id,Connection myConnection,PreparedStatement myStatement,ResultSet myRs)
			{
				//this will be the event handling of another button in the information about ngo window which tells us the board of directors information
				try {
				myStatement=myConnection.prepareStatement("select director_name,designation,email_id from boardofdirecrtors b, where b.ngo_owner_id=?");
				myStatement.setDouble(1,ngo_owner_id);
				myRs=myStatement.executeQuery();
				while(myRs.next())
			  {
			 	System.out.println(myRs.getString("project_name")+","+myRs.getString("project_id")+","+myRs.getString("city"));
			  } 
				}
				catch(SQLException e)
				{
					System.out.println(e.getMessage());
				}
			}
			boolean login_window_userName (String username)
			{
				try {
				Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","password");
				myStatement=myConnection.prepareStatement("select * from userPassword" );
				//to do list !!!
				/*myStatement.setDouble(1,ngo_owner_id);
				myStatement.setString(2,username);
				myStatement.setString(3,password);
				*/
				myRs=myStatement.executeQuery();
				while(myRs.next())
				{
				if(username.equalsIgnoreCase(myRs.getString("user_name")) )
					{
						return true;
					}
				}
				}
				catch (SQLException e)
				{
					System.out.println(e.getMessage());
				}
			//the following are the instructions which are to followed when you are making the login screen
				//see in the action even, call these functions, and then match the value which is taken from the text feilds with these values,
				// put them inside a if condition, and if the condition is met, show up a window, with like login successful, with an okay button, and then direct the page to the ngos main menu where we have those options
				//the ones like where we hace edit details and those options
				return false;
				
			}
			boolean login_window_ngoUsername (String userName)
			{
				try {
				Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","password");
				String select = "select * from NGOPassword ";
				myStatement=myConnection.prepareStatement(select );
				//to do list !!!
				/*myStatement.setDouble(1,ngo_owner_id);
				
				myStatement.setString(3,password);
				*/
				myRs=myStatement.executeQuery();
				while(myRs.next())
				{
				if(userName.equalsIgnoreCase(myRs.getString("ngo_name")) )
					{
						return true;
					}
					
				}
				}
				catch (SQLException e)
				{
					System.out.println(e.getMessage());
				}
			//the following are the instructions which are to followed when you are making the login screen
				//see in the action even, call these functions, and then match the value which is taken from the text feilds with these values,
				// put them inside a if condition, and if the condition is met, show up a window, with like login successful, with an okay button, and then direct the page to the ngos main menu where we have those options
				//the ones like where we hace edit details and those options
				return false;
				
			}
			 boolean login_window_ngoPassword (String userName,String password) throws SQLException
				{
					
					Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","password");
					//String select = "select * from NGOPassword where ngo_name=? and password=?";
					
					myStatement=myConnection.prepareStatement("select * from NGOPassword where 'ngo_name'=? and 'password'=?");
					myStatement.setString(1,userName);
					myStatement.setString(2,password);
					myRs = myStatement.executeQuery();
					//myStatement.setDouble(1,ngo_owner_id);
					//myStatement.setString(2,username);
					
					
					//myRs=myStatement.executeQuery();
					/*while(myRs.next())
					{
					if((password == myRs.getString("password") ))//&& userName.equalsIgnoreCase(myRs.getString("ngo_name"))))
						{
							return true;
						}
					}
				
					}*/
					if(myRs.next())
					{
						System.out.println(myRs.getString("ngo_name")+myRs.getString("password"));
						return false;
					}
					else
						return true;
				//the following are the instructions which are to followed when you are making the login screen
					//see in the action even, call these functions, and then match the value which is taken from the text feilds with these values,
					// put them inside a if condition, and if the condition is met, show up a window, with like login successful, with an okay button, and then direct the page to the ngos main menu where we have those options
					//the ones like where we hace edit details and those options
					}
				
		
	 
	/* boolean login_window_userPassword (String password)
		{
			try {
			Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","password");
			myStatement=myConnection.prepareStatement("select * from userPassword");
			/*myStatement.setDouble(1,ngo_owner_id);
			myStatement.setString(2,username);
			myStatement.setString(3,password);
			
			
			myRs=myStatement.executeQuery();
			while(myRs.next())
			{
				if(myRs.getString("password") == password)
				{
					return true;
				}
			}
			}
			
			catch (SQLException e)
			{
				System.out.println(e.getMessage());
			}
		//the following are the instructions which are to followed when you are making the login screen
			//see in the action even, call these functions, and then match the value which is taken from the text feilds with these values,
			// put them inside a if condition, and if the condition is met, show up a window, with like login successful, with an okay button, and then direct the page to the ngos main menu where we have those options
			//the ones like where we hace edit details and those options
			return false;
			
		}
	}
	*/
	 }	

 

 	
 class updateFunctions {
	 ResultSet myRs=null;
		Connection con;
		Statement myStatement;
	 updateFunctions()
	 {
		 String password = "password";
			String userName = "root";
			String path = "jdbc:mysql://localhost:3306/project";
			try {
				 con = DriverManager.getConnection(path,userName,password);
				 myStatement = con.createStatement();	
			}
			catch(SQLException e)
			{
				System.out.println("Something went wrong");
			}
	 }
    //ResultSet myRs=null;
    
    
    void update_ngo_central_office_number(String ngoName,String central_office)
    {
    	System.out.println(ngoName);
    	try {
    		Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","password");
			//String select = "select * from NGOPassword where ngo_name=? and password=?";
			
			PreparedStatement myStatement;
			
         myStatement=myConnection.prepareStatement("update NGO set central_phone_no=? where ngo_name=?");
       // myStatement.setString(1,central_office);
        myStatement.setString(2,ngoName);
        myStatement.setString(1,central_office);
        //System.out.println(myStatement);
        myStatement.executeUpdate();
    	
       /* while(myRs.next())
        {
       	 System.out.println("NGO Owner ID : " + myRs.getInt("ngo_owner_id") + "  NGO Name : " + myRs.getString("ngo_name") + "  Central Phone Number" + myRs.getString("central_phone_no"));
        }*/
        }
    	catch(SQLException e)
    	{
    		System.out.println(e.getMessage());
    	}
    }
  void update_office_phone_no(String ngo_name,String central_phone_no)
    {
	  try {
		 Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","password");
        PreparedStatement  myStatement=myConnection.prepareStatement("update offices set phone_no=? where ngo_owner_id in (select ngo_owner_id from NGO where ngo_name=?)");
        myStatement.setString(1,central_phone_no);
        myStatement.setString(2,ngo_name);
        myStatement.executeUpdate();
          }
    
      catch(SQLException e)
      {
    	  System.out.println(e.getMessage());
      }
    }
  void update_office_head_name(String ngo_name,String headName)
  {
	  try {
		  Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","password");
		  PreparedStatement myStatement = myConnection.prepareStatement("update board_of_directors set head = ? where ngo_owner_id in (select ngo_owner_id from NGO where ngo_name = ?)");
		  myStatement.setString(1, headName);
		  myStatement.setString(2,ngo_name);
		  myStatement.executeUpdate();
	  }
	  catch(SQLException e)
	  {
		  System.out.println(e.getMessage());
	  }
  }
  String getInfo(String ngoName) throws SQLException
  {
	  Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","password");
      PreparedStatement  myStatement=myConnection.prepareStatement("select ngo_info  where ngo_name=?");
      myStatement.setString(1,ngoName);
      myStatement.executeQuery();
      String result = null;
      if(myRs.next())
      {
    	result =  myRs.getString("ngo_name");
      }
      return result;
  }
 }
 

