package ai.ineuron;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

import ai.ineuron.utility.JdbcConnection;

public class Insertion 
{
	Connection con=null;
	PreparedStatement p=null;
	ResultSet rs=null;
	Scanner sc=new Scanner(System.in);
	public void insertion()
	{
		try
		{
			System.out.print("Enter Name : ");
			String name=sc.nextLine();
			System.out.print("Enter Address : ");
			String address=sc.nextLine();
			System.out.print("Enter Gender : ");
			String gender=sc.nextLine();
			System.out.print("Enter Date of Birth (dd-MM-yyyy) : ");
			String dob=sc.nextLine();
			System.out.print("Enter Date of Joining (MM-dd-yyyy) : ");
			String doj=sc.nextLine();
			System.out.print("Enter Date of Marriage (yyyy-MM-dd) : ");
			String dom=sc.nextLine();
			
			if(con==null)
			{
				con=JdbcConnection.getConnection();
			}
			if(con!=null)
			{
				p=con.prepareStatement("insert into dateio values(?,?,?,?,?,?)");
			}
			if(p!=null)
			{
				p.setString(1, name);
				p.setString(2, address);
				p.setString(3, gender);
				SimpleDateFormat sd1=new SimpleDateFormat("dd-MM-yyyy");
				SimpleDateFormat sd2=new SimpleDateFormat("MM-dd-yyyy");
				SimpleDateFormat sd3=new SimpleDateFormat("yyyy-MM-dd");
				
				java.util.Date d1=sd1.parse(dob);
				java.util.Date d2=sd2.parse(doj);
				java.util.Date d3=sd3.parse(dom);
				
				java.sql.Date s1=new java.sql.Date(d1.getTime());
				java.sql.Date s2=new java.sql.Date(d2.getTime());
				java.sql.Date s3=new java.sql.Date(d3.getTime());
				
				p.setDate(4, s1);
				p.setDate(5, s2);
				p.setDate(6, s3);
				
				int rows=p.executeUpdate();
				System.out.println(rows+" row's affected");
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
