package ai.ineuron;

import java.util.Scanner;

public class Operation 
{
	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("1.Insert\t2.Retrival");
		System.out.print("Enter option as number : ");
		int option=sc.nextInt();
		switch(option)
		{
			case 1: Insertion insertion =new Insertion();
			        insertion.insertion();
			        break;
			case 2: Retrival retrival=new Retrival();
					retrival.retrival();
					break;
		}

	}

}
