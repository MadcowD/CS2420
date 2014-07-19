package assignment9;

import java.io.File;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * represents the user controller software for remote device utilization
 
 * @author Paymon Saebi
 * @author Maks Cegielski-Johnson
 * @author William Guss
 */
public class UserController 
{
	/**
	 * The main method that runs the Task Manager, catching any exceptions
	 * @param args - the first paramater must be the task file
	 */
	public static void main(String[] args) 
	{ 		
		
		if(args.length < 1){
			System.out.println("You need at least one parameter to run the program!");
			return;
		}
		File f = null;
		
		try{
			f = new File(args[0]);	
		} catch(Exception e){
			System.out.println("Incorrect file");
			return;
		}

		
		run_task_manager(f);
		
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
	        		manager.runTask();
	        		break;
	        	}
	        	else 
	        		manager.sendComm(input);        	
	        }        
		}
	}
}
