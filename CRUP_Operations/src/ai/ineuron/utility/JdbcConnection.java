package ai.ineuron.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//JDBC Connection Utility code

public class JdbcConnection 
{
	public static Connection getConnection()
	{
		Connection con=null;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			try 
			{
				if(con==null)
				{
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ineuron","root","root");
				}
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
}
