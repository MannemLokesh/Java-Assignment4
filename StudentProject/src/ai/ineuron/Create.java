package ai.ineuron;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import ai.ineuron.utility.JdbcConnection;

public class Create 
{
	public void create()
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
			System.out.print("Enter roll : ");
			String roll=sc.next().toLowerCase();
			sc.nextLine();
			
			System.out.print("Enter name : ");
			String name=sc.nextLine();
			
			System.out.print("Enter parent mobile number : ");
			long parent_mobile=sc.nextLong();
			sc.nextLine();
			
			System.out.print("Enter parent name : ");
			String parent_name=sc.nextLine();
			if(con!=null)
			{
				p=con.prepareStatement("select count(*) from student where roll=?");
			}
			if(p!=null)
			{
				p.setString(1, roll);
				rs=p.executeQuery();
			}
			rs.next();
			p=null; //changing again to null
			
			if(rs.getInt(1)==1)//to check whether student exist or not in database
			{
				System.out.println("student roll number already exist in database");
			}
			else //if student not exist in database insert details into database
			{
				if(con!=null)
				{
					p=con.prepareStatement("insert into student (roll,name,parent_mobile,parent_name) values(?,?,?,?)");
				}
				if(p!=null)
				{
					p.setString(1, roll);
					p.setString(2, name);
					p.setLong(3, parent_mobile);
					p.setString(4, parent_name);
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

