package ai.ineuron;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import ai.ineuron.utility.JdbcConnection;

public class Delete 
{
	Scanner sc=new Scanner(System.in);
	Connection con=null;
	PreparedStatement p=null;
	ResultSet rs=null;
	public void delete()
	{
		try
		{
			System.out.println("1.Delete one employee details\t2.Delete all employee details");
			System.out.print("Enter your option as number : ");
			int option=sc.nextInt();
			sc.nextLine();
			switch(option)
			{
				case 1: deleteOneStudent();
				        break;
				case 2: deleteAllStudent();
		        break;       
			}
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
	public void deleteOneStudent()
	{
		try 
		{
			System.out.print("Enter the eid number of the employee : ");
			String eid=sc.nextLine();
			if(con==null)
			{
				con=JdbcConnection.getConnection();
			}
			if(con!=null)
			{
				
				p=con.prepareStatement("select count(*) from employee where eid = ?");
			}
			if(p!=null)
			{
				p.setString(1, eid);
				rs = p.executeQuery();
			}
			if(rs!=null)
			{
				rs.next();
				if(rs.getInt(1)==1)
				{
					p=null;
					if(p==null)
					{
						p=con.prepareStatement("delete from employee where eid = ?");
					}
					if(p!=null)
					{
						p.setString(1, eid);
						int rows=p.executeUpdate();
						System.out.println(rows+" row's affected");
					}
				}
				else
				{
					System.out.println("roll number not exist in database");
				}
			}
		} 
		catch (SQLException e) 
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
	public void deleteAllStudent()
	{
		try 
		{
			if(con==null)
			{
				con=JdbcConnection.getConnection();
			}
			if(con!=null)
			{
				
					p=con.prepareStatement("delete from employee");
			}
			if(p!=null)
			{
				int rows = p.executeUpdate();
				System.out.println(rows+" row's affected");
			}
		} 
		catch (SQLException e) 
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
