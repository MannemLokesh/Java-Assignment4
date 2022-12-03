package ai.ineuron;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;

import ai.ineuron.utility.JdbcConnection;

public class Retrival 
{
	Connection con=null;
	PreparedStatement p=null;
	ResultSet rs=null;
	public void retrival()
	{
		try
		{
			if(con==null)
			{
				con=JdbcConnection.getConnection();
			}
			if(con!=null)
			{
				p=con.prepareStatement("select count(*) from dateio");
			}
			if(p!=null)
			{
				rs=p.executeQuery();
			}
			if(rs!=null)
			{
				rs.next();
				if(rs.getInt(1)!=0)
				{
					p=null;
					if(p==null)
					{
						p=con.prepareStatement("select * from dateio");
					}
					if(p!=null)
					{
						rs=p.executeQuery();
					}
					if(rs!=null)
					{
						SimpleDateFormat s1=new SimpleDateFormat("dd-MM-yyyy");
						SimpleDateFormat s2=new SimpleDateFormat("MM-dd-yyyy");
						SimpleDateFormat s3=new SimpleDateFormat("yyyy-MM-dd");
						System.out.println("Retriving data from database");
						System.out.println("Name\tAddress\tGender\tDOB(dd-MM-yyyy)\tDOJ(MM-dd-yyyy)\tDOM(yyyy-MM-dd)");
						while(rs.next())
						{
							System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+s1.format(rs.getDate(4))+"\t"+s2.format(rs.getDate(5))+"\t"+s3.format(rs.getDate(6)));
						}
					}
				}
				else
				{
					System.out.println("database is empty");
				}
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(InputMismatchException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
