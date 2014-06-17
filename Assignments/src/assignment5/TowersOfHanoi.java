package assignment5;

/**
 * Represents the Towers of Hanoi puzzle.
 * Executed from the command-line and solves for a given number of discs.
 * Verbose option allows for printing the state of the towers after each move.
 * 
 * To solve the problem we need to move all N discs from Tower A to Tower B.
 * There are three rules governing the movement of discs from pole to tower.
 * 
 * Rule 1. Only one disc may be moved at a time.
 * Rule 2. Only the topmost disc on each pole is accessible. 
 * Rule 3. Only a smaller disc may be moved on top a larger disc or on an empty tower.
 * 
 * Stacks are used to represent the towers and recursion is used to solve and the problem. 
 * 
 * @author Paymon Saebi 
 * @author Maks Cegielski-Johnson
 * @author William Guss	
 */
public class TowersOfHanoi
{
    private int numOfDiscs;
    private long numOfMoves; 
    private boolean verbose;      
    private MyStack<Integer> towerA;
    private MyStack<Integer> towerB;
    private MyStack<Integer> towerC;

    /**
     * The main method first displays the initial state of the towers and then
     * solves its and at last displays the final state of the towers. The method is
     * set to be invoked from the command line with 1 or 2 arguments. The first
     * argument is necessary and it is the number of the discs to be solved and the
     * second argument "-v" is optional to print the state of the towers after each move.  
     */
    public static void main (String[] args)
    {
    	if (args.length > 2) // Ensure that there are no more than two arguments
        {
        	System.out.println("\nInvalid number of arguments!\n");
        	return;
        }
        else if (args.length < 1) // Ensure that there is at least one argument
        {
            System.out.println("\nMust specify the number of discs!\n");
            return;
        }
        else
        {
        	int numDiscs = 0;
        	boolean option = false;
        	TowersOfHanoi myTowers = null;
        	
        	try
	        {
        		numDiscs = Integer.parseInt(args[0]); // Ensure valid integer argument
	        }
	        catch (Exception e)
	        {
	        	System.out.println("\nInvalid number of discs argument!\n");
	        	return;
	        }
        	
       		if (args.length == 2) 
       			if(args[1].equalsIgnoreCase("-v")) // check for verbose option
       				option = true;
       			else 
       			{
       				System.out.println("\nInvalid verbose option argument!\n");
       				return;
       			}
   	
       		myTowers = new TowersOfHanoi(numDiscs, option); // Build the tower with main's arguments
       		
       		if(myTowers.towerA.peek() == null) // Ensure the initial stack for tower A is populated        
       			System.out.println("\nAbort! Failed to build the tower!\n");       	
       		else    	
	   	        try
	   	        {
	   	        	System.out.println("\nInitial state of the Towers of Hanoi:");
		   	        System.out.println(myTowers.toString());
	   	            
		   	        myTowers.solve(); // Ensure the solution finding process is exception free
	   	            
		   	        System.out.println("Final state of the Towers of Hanoi:");
	    	        System.out.println(myTowers.toString());
		   	        
		   	        if(myTowers.towerB.size() == numDiscs) // Check to see if everything was moved		   	        
			   	        System.out.println("Done! " + myTowers.numOfMoves + " moves were required to solve " + 
		    	        					myTowers.numOfDiscs + " discs.\n");
		   	        else
		   	        	System.out.println("Bummer! no solution found, recursion failed!");			   	     
	   	        }
	   	        catch (Exception e)
	   	        {
	   	            System.err.println("ERROR: " + e.getMessage());
	   	            return;
	   	        }   	               	       
        }
    }

    /**
     * Towers Of Hanoi Constructor.
     * 
     * Three towers are created. Initially, the first tower contain the given
     * number of discs in a downward ascending order and the other towers
     * initialized to be empty.
     * 
     * @param _numOfDisks (Number of discs to be solved for)
     * @param _verbose (Option of printing the state of towers)
     */
    public TowersOfHanoi (int _numOfDiscs, boolean _verbose)
    {    	 
    	numOfMoves = 0;
    	verbose = _verbose;    	
    	numOfDiscs = _numOfDiscs;
        towerA = new MyStack<Integer>();
        towerB = new MyStack<Integer>();
        towerC = new MyStack<Integer>();
    	
        for (int i = numOfDiscs; i >= 1; i--)
            towerA.push(i);
    }

    /**
     * The solve method is the driver with a call to the , it calls the
     * moveDiscs method with parameters for moving the given number of discs from
     * tower "A" to tower "B" using tower "C".
     */
    public void solve () throws Exception
    {
        moveDiscsRecursive('A', 'B', 'C', numOfDiscs);
    }

    /**
     * This method recursively solves the Tower of Hanoi problem by dividing the
     * overall problem into subproblems, first number of discs - 1 is moved from
     * tower "A" to tower "C" using tower "B", then moving one disc from tower "A"
     * to tower "B", and finally move those number of discs - 1 from tower "C" to
     * tower "B", and stop when there is no more moves.
     * 
     * @param from (The tower to move the stack of discs from)
     * @param to (The tower to move the stack of discs to)
     * @param use (The tower to be used to help the movements)
     * @param numToMove (The number of discs to be solved for)
     * @throws Exception (Any error while operating on the stacks)
     */
    private void moveDiscsRecursive (char from, char to, char use, int numToMove) throws Exception
    {
        if (numToMove > 0)
        {
            moveDiscsRecursive(from, use, to, numToMove - 1);
            moveDiscSingle(from, to);
            moveDiscsRecursive(use, to, from, numToMove - 1);
        }        
    }

    /**
     * Moves one disc from the given "from" tower to the given "to" tower. Throws
     * an exception if an illegal move is requested (move to or from an unknown tower, 
     * move from an empty tower, or larger disc on top of a smaller disc).
     * It also keeps count of the number of moves taken. 
     * 
     * If the verbose argument has been given, then a textual representation of the
     * towers is printed after each move.
     * 
     * @param from (The tower to move ONE disc from)
     * @param to (The tower to move ONE disc to)
     * @throws Exception (Any illegal movement case)
     */
    public void moveDiscSingle (char from, char to) throws Exception
    {
    	// Throw an exception for every illegal case.
    	if(from == to)
    		throw new Exception("Abort! Illegal tower request!");
    	// if from tower has no discs throw Illegal tower request exception
    	//TODO
    	
    	MyStack start = null;
    	MyStack finish = null;
    	
    	switch(from){
    	case 'A':
    		start = towerA;
    		break;
    	case 'B':
    		start = towerB;
    		break;
    	case 'C':
    		start = towerC;
    		break;
    	default:
    		throw new Exception("Abort! Illegal tower ID!");
    	}
    	
    	switch(to){
    	case 'A':
    		finish = towerA;
    		break;
    	case 'B':
    		finish = towerB;
    		break;
    	case 'C':
    		finish = towerC;
    		break;
    	default:
    		throw new Exception("Abort! Illegal tower ID!");
    	}
    	
    	if(start.size() == 0)
    		throw new Exception("Abort! Illegal tower request!");
    	
    	int s = (int)start.peek();
    	int f = (int)finish.peek();
    	if(s > f && finish.size() != 0)
    		throw new Exception("Abort! Illegal disc movement!");
    	
    	
    	int temp = (int) start.pop();
    	finish.push(temp);
    	numOfMoves++;
    	
    	// Move one disc from the "from" tower to the "to" tower    	
    	// If you made a move, increment this towers object's numOfMoves field.
    	//TODO        
    	
    	// Leave this code here at the end of the method.
        if (verbose) // Print the state of the towers if the verbose option was passed.
        {
        	System.out.println("State of the towers after " + numOfMoves + " moves:");
            System.out.println(this.toString());
        }
    }

    /**
     * This method overrides the default Object toString method and formats the
     * current state of the towers to the criteria given by the assignment. It
     * first grabs a copy of each tower and stores them on an array of objects for
     * each tower, and then displaying the towers in the required manner.
     */
    public String toString ()
    {
        // Putting the towers in object arrays
        String towerVisual = "";
        Object[] towA = towerA.toArray();
        Object[] towB = towerB.toArray();
        Object[] towC = towerC.toArray();

        // Looping to print each line for towers
        for (int i = numOfDiscs; i > 0; i--)
        {
            // Checking for and putting | in empty spots in towers "A"
            if (towA.length < i)
                towerVisual = towerVisual + "\t  |  ";
            else
                // Formatting each line according to the discs number of digits
                if (Integer.parseInt(towA[i - 1].toString()) < 10)
                    towerVisual = towerVisual + "\t  " + towA[i - 1] + "  ";
                else
                    towerVisual = towerVisual + "\t  " + towA[i - 1] + " ";

            // Checking for and putting | in empty spots in towers "A"
            if (towB.length < i)
                towerVisual = towerVisual + "   |   ";
            else
                // Formatting each line according to the discs number of digits
                if (Integer.parseInt(towB[i - 1].toString()) < 10)
                    towerVisual = towerVisual + "   " + towB[i - 1] + "   ";
                else
                    towerVisual = towerVisual + "   " + towB[i - 1] + "  ";

            // Checking for and putting | in empty spots in towers "A"
            if (towC.length < i)
                towerVisual = towerVisual + "  |  ";
            else
                // Formatting each line according to the discs number of digits
                if (Integer.parseInt(towC[i - 1].toString()) < 10)
                    towerVisual = towerVisual + "  " + towC[i - 1] + "  ";
                else
                    towerVisual = towerVisual + "  " + towC[i - 1] + "  ";

            towerVisual = towerVisual + "\n";
        }

        // Creating the base of the towers
        towerVisual = towerVisual + "\t----- ----- -----" + "\n" + "\tTow A Tow B Tow C" + "\n";

        return towerVisual;
    }    
}