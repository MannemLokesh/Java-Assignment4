package ai.ineuron;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import ai.ineuron.utility.JdbcConnection;

public class Insert 
{
	public void insert()
	{
		Connection con=null;
		PreparedStatement p=null;
		ResultSet rs=null;
		if(con==null)
		{
			con=JdbcConnection.getConnection();
		}
		Scanner sc=new Scanner(System.in);
		try
		{
			//Taking input details from user
			System.out.print("Enter eid : ");
			String eid=sc.next().toLowerCase();
			sc.nextLine();
			
			System.out.print("Enter name : ");
			String name=sc.nextLine();
			
			System.out.print("Enter mobile number : ");
			long mobile=sc.nextLong();
			sc.nextLine();
			
			System.out.print("Enter Company name : ");
			String company_name=sc.nextLine();
			if(con!=null)
			{
				p=con.prepareStatement("select count(*) from employee where eid=?");
			}
			if(p!=null)
			{
				p.setString(1, eid);
				rs=p.executeQuery();
			}
			rs.next();
			p=null; //changing again to null
			
			if(rs.getInt(1)==1)//to check whether employee exist or not in database
			{
				System.out.println("employee eid number already exist in database");
			}
			else //if employee not exist in database insert details into database
			{
				if(con!=null)
				{
					p=con.prepareStatement("insert into employee (eid,name,mobile,company_name) values(?,?,?,?)");
				}
				if(p!=null)
				{
					p.setString(1, eid);
					p.setString(2, name);
					p.setLong(3, mobile);
					p.setString(4, company_name);
					int rows=p.executeUpdate();
					System.out.println(rows +"row's affected");
				}
			}
			con.close();
		}
		catch(InputMismatchException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			sc.close();
		}
	}
}

