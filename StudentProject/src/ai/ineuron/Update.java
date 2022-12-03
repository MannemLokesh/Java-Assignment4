package ai.ineuron;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import ai.ineuron.utility.JdbcConnection;

public class Update 
{
	public void update()
	{
		Connection con=null;
		PreparedStatement p=null;
		ResultSet rs=null;
		Scanner sc=new Scanner(System.in);
		try 
		{
			System.out.print("Enter roll number of the student to update : ");
			String roll = sc.nextLine().toLowerCase();
			if(con==null)
			{
				con=JdbcConnection.getConnection();
			}
			if(con!=null)
			{
				p=con.prepareStatement("select count(*) from student where roll=?");
			}
			if(p!=null)
			{
				p.setString(1, roll);
				rs=p.executeQuery();
			}
			if(rs!=null)
			{
				rs.next();
				if(rs.getInt(1)==1)
				{
					System.out.println("1.name\t2.parent_mobile\t3.parent_name");
					System.out.print("Choose your option as number : ");
					int option=sc.nextInt();
					sc.nextLine();
					String column_name="";
					switch(option)
					{
						case 1: column_name="name";
						        break;
						case 2: column_name="parent_mobile";
				        	    break;
						case 3: column_name="parent_name";
		        	            break;
					}
					System.out.print("Enter the new data to be updated in the column "+column_name+" : ");
					String new_data=sc.nextLine();
					if(column_name.equals("parent_mobile"))
					{
						p=null;
						if(p==null)
						{
							p=con.prepareStatement("update student set "+column_name+" = ? where roll=?");
						}
						if(p!=null)
						{
							p.setLong(1, Long.parseLong(new_data));
							p.setString(2, roll);
							int rows=p.executeUpdate();
							System.out.println(rows+" row's affected");
						}
					}
					
					else
					{
						p=null;
						if(p==null)
						{
							p=con.prepareStatement("update student set "+column_name+"= ? where roll=?");
						}
						if(p!=null)
						{
							p.setString(1, new_data);
							p.setString(2, roll);
							int rows=p.executeUpdate();
							System.out.println(rows+" row's affected");
						}
					}
				}
				else
				{
					System.out.println("roll number not exist in the database");
				}
			}
			con.close();
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
		finally
		{
			sc.close();
		}
	}
}
