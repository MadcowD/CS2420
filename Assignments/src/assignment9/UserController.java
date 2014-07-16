package assignment9;

import java.io.File;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * represents the user controller software for remote device utilization
 
 * @author Paymon Saebi
 * @author
 * @author
 */
public class UserController 
{
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) 
	{ 		
		//TODO		
		//Ensure that the user has given at least one parameter
		//If not, print an error message about it and return
		
		//TODO
		//Create a file object from args[0] parameter
		//Ensure that the parameter given is a valid file	
		
		//TODO
		//If the file is valid and everything is good, run the method below
		//If not, then print an error message about it and return
	}	
	
	/**
	 *
	 */
	public static void run_task_manager(File tasks)
	{
		System.out.println("System task manager is initialized ...");
		
		Scanner scanner = new Scanner(new InputStreamReader(System.in));		
		
		TaskManager manager = new TaskManager(tasks);			
		
		manager.runTask();
		
		while(true)
		{       
	        if(manager.isDone())
	        {
	        	System.out.println("\nAll tasks are done, Have a nice day...");
	    		scanner.close();
	        	return;
	        }
	        
	        String input = scanner.nextLine();
	       	        
	        if(input.equals("task"))
	        {
	        	System.out.println("\nCurrent task: " + manager.getTask());
	        }
	        
	        else if(input.equals("run"))
	        {
	        	manager.runTask();
	        }
	        
	        else if(input.equals("next"))
	        {
	        	System.out.println("\nNext task: " + manager.nextTask());
	        }
	        
	        else if(input.equals("exit"))
	        {
	        	System.out.println("\nSystem task manager has exited ...");
	    		scanner.close();
	        	return;
	        }
	        
	        while(manager.getTask().getTaskName().equals("user_comm"))
	        {
	        	input = scanner.nextLine();
	        	
	        	if(input.equals("done"))
	        	{
	        		System.out.println("\nUser command connection has been shut down...");
	        		break;
	        	}
	        	else 
	        		manager.sendComm(input);        	
	        }        
		}
	}
}
