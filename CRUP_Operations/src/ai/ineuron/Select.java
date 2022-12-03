package ai.ineuron;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import ai.ineuron.utility.JdbcConnection;

public class Select 
{
	Connection con=null;
	PreparedStatement p=null;
	ResultSet rs=null;
	Scanner sc=new Scanner(System.in);
	public void select()
	{
		try
		{
			System.out.println("1. Read specific employee details\t2. Read all the employee details");
			System.out.print("Choose your option as number : ");
			int option=sc.nextInt();
			switch(option)
			{
			case 1: readOne();
				break;
				
			case 2: readAll();
				break;
			}
		}
		catch(InputMismatchException e)
		{
			e.printStackTrace();
		}
		finally
		{
			sc.close();
		}
	}
	
	public void readOne()
	{
		if(con==null)
		{
			con=JdbcConnection.getConnection();
		}
		Scanner sc=new Scanner(System.in);
		try
		{
			System.out.print("Enter eid number of the employee to check : ");
			String eid = sc.nextLine().toLowerCase();
			if(con!=null)
			{
				p=con.prepareStatement("select count(*) from employee where eid =?");
			}
			if(p!=null)
			{
				p.setString(1, eid);
				rs=p.executeQuery();
			}
			if(rs!=null)
			{
				rs.next();
				if(rs.getInt(1)==1)//roll exist 
				{
					p=null;
					if(p==null)
					{
						p=con.prepareStatement("select * from employee where eid = ?");
					}
					if(p!=null)
					{
						p.setString(1, eid);
						rs=p.executeQuery();
					}
					if(rs!=null)
					{
						System.out.println("eid\tname\tmobile\tcompany_name");
						while(rs.next())
						{
							System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getLong(3)+"\t"+rs.getString(4));
						}
					}
					
				}
				else
				{
					System.out.println("eid number not exist in database");
				}
			}
		con.close();
		}
		catch(InputMismatchException e)
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
	
	public void readAll()
	{
		try
		{
			if(con==null)
			{
				con=JdbcConnection.getConnection();
			}
			if(con!=null)
			{
				p=con.prepareStatement("select count(*) from employee;");
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
						p=con.prepareStatement("select * from employee;");
					}
					if(p!=null)
					{
						rs=p.executeQuery();
					}
					if(rs!=null)
					{
						System.out.println("eid\tname\tmobile\tcompany_name");
						while(rs.next())
						{
							System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getLong(3)+"\t"+rs.getString(4));
						}
					}		
				}
				else
				{
					System.out.println("employee database is empty");
				}
			}
		con.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
