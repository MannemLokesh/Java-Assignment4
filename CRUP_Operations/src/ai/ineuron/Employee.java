package ai.ineuron;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Employee 
{
	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		try
		{
			System.out.println("1.Insert\t2.Select\t3.Update\t4.Delete");
			System.out.print("Choose your option as number : ");
			int option=sc.nextInt();
			switch(option)
			{
				case 1:Insert insert=new Insert();
					   insert.insert();
					   break;
				case 2:Select select=new Select();
					   select.select();
					   break;
				case 3:Update update=new Update();
				       update.update();
					   break;
				case 4:Delete delete=new Delete();
				       delete.delete();
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
		finally
		{
			sc.close();
		}
	}
}
