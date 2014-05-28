package assignment2;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.Scanner;

/**
 * Testing class for LibraryGeneric.
 *
 * @author William Guss & Maks Cegielski-Johnson
 *
 */
public class LibraryGenericTest {

	public static void main(String[] args) {

		// test a library that uses names (String) to id patrons
		LibraryGeneric<String> lib1 = new LibraryGeneric<String>();
		lib1.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		lib1.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		lib1.add(9780446580342L, "David Baldacci", "Simple Genius");

		String patron1 = "Jane Doe";
		String name = "Jane DopeAf";
		PhoneNumber mydigitsgirl = new PhoneNumber("801.420.6969");

		if (!lib1.checkout(9780330351690L, patron1, 1, 1, 2008))
			System.err.println("TEST FAILED: first checkout");
		if (!lib1.checkout(9780374292799L, patron1, 1, 1, 2008))
			System.err.println("TEST FAILED: second checkout");
		ArrayList<LibraryBookGeneric<String>> booksCheckedOut1 = lib1
				.lookup(patron1);
		if (booksCheckedOut1 == null
				|| booksCheckedOut1.size() != 2
				|| !booksCheckedOut1.contains(new Book(9780330351690L, "Jon Krakauer",
						"Into the Wild"))
						|| !booksCheckedOut1.contains(new Book(9780374292799L,
								"Thomas L. Friedman", "The World is Flat"))
								|| !booksCheckedOut1.get(0).getHolder().equals(patron1)
								|| !booksCheckedOut1.get(0).getDueDate().equals(
										new GregorianCalendar(2008, 1, 1))
										|| !booksCheckedOut1.get(1).getHolder().equals(patron1)
										|| !booksCheckedOut1.get(1).getDueDate().equals(
												new GregorianCalendar(2008, 1, 1)))
			System.err.println("TEST FAILED: lookup(holder)");
		if (!lib1.checkin(patron1))
			System.err.println("TEST FAILED: checkin(holder)");

		// test a library that uses phone numbers (PhoneNumber) to id patrons
		LibraryGeneric<PhoneNumber> lib2 = new LibraryGeneric<PhoneNumber>();
		lib2.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		lib2.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		lib2.add(9780446580342L, "David Baldacci", "Simple Genius");

		PhoneNumber patron2 = new PhoneNumber("801.555.1234");

		if (!lib2.checkout(9780330351690L, patron2, 1, 1, 2008))
			System.err.println("TEST FAILED: first checkout");
		if (!lib2.checkout(9780374292799L, patron2, 1, 1, 2008))  
			System.err.println("TEST FAILED: second checkout");
		ArrayList<LibraryBookGeneric<PhoneNumber>> booksCheckedOut2 = lib2
				.lookup(patron2);
		if (booksCheckedOut2 == null
				|| booksCheckedOut2.size() != 2
				|| !booksCheckedOut2.contains(new Book(9780330351690L, "Jon Krakauer",
						"Into the Wild"))
						|| !booksCheckedOut2.contains(new Book(9780374292799L,
								"Thomas L. Friedman", "The World is Flat"))
								|| !booksCheckedOut2.get(0).getHolder().equals(patron2)
								|| !booksCheckedOut2.get(0).getDueDate().equals(
										new GregorianCalendar(2008, 1, 1))
										|| !booksCheckedOut2.get(1).getHolder().equals(patron2)
										|| !booksCheckedOut2.get(1).getDueDate().equals(
												new GregorianCalendar(2008, 1, 1)))
			System.err.println("TEST FAILED: lookup(holder)");
		if (!lib2.checkin(patron2))                           
			System.err.println("TEST FAILED: checkin(holder)");



		
		ArrayList<Long> isbns = loadIsbns("Mushroom_Publishing.txt");

		//PERFORM EXHAUSTIVE STRING NAME HOLDER TESTING
		lib1.addAll("Mushroom_Publishing.txt");


		//Stabilize thread
		long j = 0;
		while(j < 1E7)
			j++;

		int timesToLoop = (int)1E5;

		long startTime = System.nanoTime();



		for(int i = 0; i < timesToLoop; i++)
			for(long l : isbns){
				if(!lib1.checkout(l, name, 0, 1, 1999))
					System.err.println("Test unsuccessful >.<  name string library (lib1) : checkout(ISBN EXHAUSTIVE)");
				if(!lib1.lookup(l).equals(name))
					System.err.println("Test unsuccessful >.<  name string library (lib1) : lookup(EXHAUSTIVE)");
				if(!lib1.checkin(l))
					System.err.println("Test unsuccessful >.<  name string library (lib1) : checkin(EXHAUSTIVE)");

			}

		long midTime = System.nanoTime();


		for(int i = 0; i < timesToLoop; i++)
			for(long l : isbns);

		long endTime = System.nanoTime();

		System.out.println("Time of individual checkout process for name string generics: " + ((midTime - startTime) - (endTime - midTime)) / timesToLoop / isbns.size() + " nano seconds");


		//BIG LIBRARY
		ArrayList<LibraryBookGeneric<String>> newBooks = generateLibrary(10000);
		lib1.addAll(newBooks);
		for(LibraryBookGeneric<String> bnook : newBooks){
			if(!lib1.checkout(bnook.getIsbn(), name, 0, 1, 1999))
				System.err.println("Test unsuccessful >.<  name string library (lib1) : checkout(ISBN EXHAUSTIVE)");
			if(!bnook.getDueDate().equals(new GregorianCalendar(1999, 0, 1)))
				System.err.println("Test unsuccessful >.<  name string library (lib1) : getDueDate(EXHAUSTIVE)");
			if(!lib1.lookup(bnook.getIsbn()).equals(name))
				System.err.println("Test unsuccessful >.<  name string library (lib1) : lookup(EXHAUSTIVE)");
			if(!lib1.lookup(bnook.getIsbn()).equals(name))
				System.err.println("Test unsuccessful >.<  name string library (lib1) : lookup(EXHAUSTIVE)");
			if(!lib1.checkin(bnook.getIsbn()))
				System.err.println("Test unsuccessful >.<  name string library (lib1) : checkin(EXHAUSTIVE)");
			if((bnook.getDueDate() != null))
				System.err.println("Test unsuccessful >.<  name string library (lib1) : getDueDate(EXHAUSTIVE NULL)");

			//Recheck out for final checkin (by holder)
			if(!lib1.checkout(bnook.getIsbn(), name, 0, 1, 1999))
				System.err.println("Test unsuccessful >.<  name string library (lib1) : checkout(ISBN EXHAUSTIVE)");

		}

		if(!lib1.checkin(name))
			System.err.println("Test unsuccessful >.< name string library (lib1) : holder checkin exhaustive" );

		if(lib1.lookup(name).size() != 0)
			System.err.println("Test unsuccessful >.<  name string library (lib1) : not all books checkedin");


		
		//PERFORM TESTING IN TERMS OF PHONE NUMBERS
		
		lib2.addAll("Mushroom_Publishing.txt");


		//Stabilize thread
		j = 0;
		while(j < 1E7)
			j++;

		timesToLoop = (int)1E5;

		startTime = System.nanoTime();



		for(int i = 0; i < timesToLoop; i++)
			for(long l : isbns){
				if(!lib2.checkout(l, mydigitsgirl, 0, 1, 1999))
					System.err.println("Test unsuccessful >.<  phone library (lib2) : checkout(ISBN EXHAUSTIVE)");
				if(!lib2.lookup(l).equals(mydigitsgirl))
					System.err.println("Test unsuccessful >.<  phone library (lib2) : lookup(EXHAUSTIVE)");
				if(!lib2.checkin(l))
					System.err.println("Test unsuccessful >.<  phone library (lib2) : checkin(EXHAUSTIVE)");

			}

		midTime = System.nanoTime();


		for(int i = 0; i < timesToLoop; i++)
			for(long l : isbns);

		endTime = System.nanoTime();

		System.out.println("Time of individual checkout process for phone library generics: " + ((midTime - startTime) - (endTime - midTime)) / timesToLoop / isbns.size() + " nano seconds");


		//BIG LIBRARY
		ArrayList<LibraryBookGeneric<PhoneNumber>> newBooksByPhone = generateLibrary(10000);
		lib2.addAll(newBooksByPhone);
		for(LibraryBookGeneric<PhoneNumber> bnook : newBooksByPhone){
			if(!lib2.checkout(bnook.getIsbn(), mydigitsgirl, 0, 1, 1999))
				System.err.println("Test unsuccessful >.<  phone library (lib2) : checkout(ISBN EXHAUSTIVE)");
			if(!bnook.getDueDate().equals(new GregorianCalendar(1999, 0, 1)))
				System.err.println("Test unsuccessful >.<  phone library (lib2) : getDueDate(EXHAUSTIVE)");
			if(!lib2.lookup(bnook.getIsbn()).equals(mydigitsgirl))
				System.err.println("Test unsuccessful >.<  phone library (lib2) : lookup(EXHAUSTIVE)");
			if(!lib2.lookup(bnook.getIsbn()).equals(mydigitsgirl))
				System.err.println("Test unsuccessful >.<  phone library (lib2) : lookup(EXHAUSTIVE)");
			if(!lib2.checkin(bnook.getIsbn()))
				System.err.println("Test unsuccessful >.<  phone library (lib2) : checkin(EXHAUSTIVE)");
			if((bnook.getDueDate() != null))
				System.err.println("Test unsuccessful >.<  phone library (lib2) : getDueDate(EXHAUSTIVE NULL)");

			//Recheck out for final checkin (by holder)
			if(!lib2.checkout(bnook.getIsbn(), mydigitsgirl, 0, 1, 1999))
				System.err.println("Test unsuccessful >.<  phone library (lib2) : checkout(ISBN EXHAUSTIVE)");

		}

		if(!lib2.checkin(mydigitsgirl))
			System.err.println("Test unsuccessful >.< phone library (lib2) : holder checkin exhaustive" );

		if(lib2.lookup(mydigitsgirl).size() != 0)
			System.err.println("Test unsuccessful >.<  phone library (lib2) : not all books checkedin");

		
		
		//TEST DUEDATE STUFF
		LibraryGeneric<String> lib3 = new LibraryGeneric<String>();
		lib3.addAll("Mushroom_Publishing.txt");
		
		//Test order by author
		ArrayList<LibraryBookGeneric<String>> authSort = lib3.getOrderedByAuthor();
		if(!authSort.get(0).getAuthor().equals("Alan Burt Akers")
				|| !authSort.get(1).getAuthor().equals("Anthony J D Burns"))
			System.err.println("Test unsuccessful >.< author sorted incorrectly");
		
		
		
		
		//Test tie breaker
		for(int i = 0; i < authSort.size() -1; i++){
			if(authSort.get(i).getTitle().equals("Crystal Legends")
					&& !authSort.get(i+1).getTitle().equals("The Eye of Callanish"))
				System.err.println("Test unsuccessful >.< author tie-breaker incorrect");
			
		}
		
		ArrayList<LibraryBookGeneric<String>> isbnSort = lib3.getInventoryList();
		//Test order by isbn
		if(isbnSort.get(0).getIsbn() != 9781843190004L || isbnSort.get(1).getIsbn() != 9781843190011L)
			System.err.println("Test unsuccessful >.< isbn sorted incorrectly.");
		System.out.println("Testing done :) #0x1A4");
		
		//Test order due date and overdue stuff
		lib3.checkout(9781843190011L, "JIMBO", 1, 1, 1000); //CHECK IT OUT FOR 1000 AD
		lib3.checkout(9781843190004L, "TIMOTHY", 1, 1, 2000); //CHECK IT OUT FOR 1000 AD
		lib3.checkout(9781843190073L, "BOB", 1, 1, 300);
		ArrayList<LibraryBookGeneric<String>> duteSort = lib3.getOverdueList(1, 1, 1500);
		if(!duteSort.get(0).getHolder().equals("BOB")
				||
				!duteSort.get(1).getHolder().equals("JIMBO"))
			System.err.println("Test unsucessful >.< dueDate sort failed.");
		
		
		if(duteSort.size() != 2)
			System.err.println("Test unsucessful >.,< getting more books than overdue");
		
		
	}

	/**
	 * Returns a library of "dummy" books (random ISBN and placeholders for author
	 * and title).
	 * 
	 * Useful for collecting running times for operations on libraries of varying
	 * size.
	 * 
	 * @param size --
	 *          size of the library to be generated
	 */
	public static <T> ArrayList<LibraryBookGeneric<T>> generateLibrary(int size) {
		ArrayList<LibraryBookGeneric<T>> result = new ArrayList<LibraryBookGeneric<T>>();

		for (int i = 0; i < size; i++) {
			// generate random ISBN
			Random randomNumGen = new Random();
			String isbn = "";
			for (int j = 0; j < 13; j++)
				isbn += randomNumGen.nextInt(10);

			result.add(new LibraryBookGeneric<T>(Long.parseLong(isbn), "An Author", "A title"));
		}

		return result;
	}

	/**
	 * Returns a randomly-generated ISBN (a long with 13 digits).
	 * 
	 * Useful for collecting running times for operations on libraries of varying
	 * size.
	 */
	public static long generateIsbn() {
		Random randomNumGen = new Random();

		String isbn = "";
		for (int j = 0; j < 13; j++)
			isbn += randomNumGen.nextInt(10);

		return Long.parseLong(isbn);
	}

	public static ArrayList<Long> loadIsbns(String filename) {
		ArrayList<Long> toBeAdded = new ArrayList<Long>();

		try {
			Scanner fileIn = new Scanner(new File(filename));
			int lineNum = 1;

			while (fileIn.hasNextLine()) {
				String line = fileIn.nextLine();

				Scanner lineIn = new Scanner(line);
				lineIn.useDelimiter("\\t");

				if (!lineIn.hasNextLong())
					throw new ParseException("ISBN", lineNum);
				long isbn = lineIn.nextLong();

				toBeAdded.add(isbn);
				lineNum++;
			}
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage() + " Nothing added to the library.");
			return null;
		} catch (ParseException e) {
			System.err.println(e.getLocalizedMessage()
					+ " formatted incorrectly at line " + e.getErrorOffset()
					+ ". Nothing added to the library.");
			return null;
		}
		return toBeAdded;
	}


}