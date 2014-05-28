package assignment2;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.Scanner;

/**
 * Testing class for Library.
 * @author William Guss & Maks Cegielski-Johnson
 */
public class LibraryTest {

	public static void main(String[] args) {
		// test an empty library
		Library lib = new Library();

		if (lib.lookup(978037429279L) != null)
			System.err.println("TEST FAILED -- empty library: lookup(isbn)");
		ArrayList<LibraryBook> booksCheckedOut = lib.lookup("Jane Doe");
		if (booksCheckedOut == null || booksCheckedOut.size() != 0)
			System.err.println("TEST FAILED -- empty library: lookup(holder)");
		if (lib.checkout(978037429279L, "Jane Doe", 1, 1, 2008))
			System.err.println("TEST FAILED -- empty library: checkout");
		if (lib.checkin(978037429279L))
			System.err.println("TEST FAILED -- empty library: checkin(isbn)");
		if (lib.checkin("Jane Doe"))
			System.err.println("TEST FAILED -- empty library: checkin(holder)");

		// test a small library
		lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		lib.add(9780446580342L, "David Baldacci", "Simple Genius");

		if (lib.lookup(9780330351690L) != null)
			System.err.println("TEST FAILED -- small library: lookup(isbn)");
		if (!lib.checkout(9780330351690L, "Jane Doe", 1, 1, 2008))
			System.err.println("TEST FAILED -- small library: checkout");
		booksCheckedOut = lib.lookup("Jane Doe");
		if (booksCheckedOut == null
				|| booksCheckedOut.size() != 1
				|| !booksCheckedOut.get(0).equals(
						new Book(9780330351690L, "Jon Krakauer", "Into the Wild"))
						|| !booksCheckedOut.get(0).getHolder().equals("Jane Doe")
						|| !booksCheckedOut.get(0).getDueDate().equals(
								new GregorianCalendar(2008, 1, 1)))
			System.err.println("TEST FAILED -- small library: lookup(holder)");
		if (!lib.checkin(9780330351690L))
			System.err.println("TEST FAILED -- small library: checkin(isbn)");
		if (lib.checkin("Jane Doe"))
			System.err.println("TEST FAILED -- small library: checkin(holder)");

		// test a medium library
		lib.addAll("Mushroom_Publishing.txt");

		String name = "William Calegksiksisk";
		if (lib.lookup(9781843190004L) != null)
			System.err.println("Test unsuccessful >.<  medium library: lookup(isbn)");
		if (!lib.checkout(9781843192954L, name, 1, 2, 3))
			System.err.println("Test unsuccessful >.<  medium library: checkout");
		booksCheckedOut = lib.lookup(name);
		if (booksCheckedOut == null
				|| booksCheckedOut.size() != 1
				|| !booksCheckedOut.get(0).equals(
						new Book(9781843192954L, "Dennis Radha-Rose", "The Anxiety Relief Program"))
						|| !booksCheckedOut.get(0).getHolder().equals(name)
						|| !booksCheckedOut.get(0).getDueDate().equals(
								new GregorianCalendar(3, 1, 2)))
			System.err.println("Test unsuccessful >.<  medium library: lookup(holder)");
		if (!lib.checkin(9781843192954L))
			System.err.println("Test unsuccessful >.<  medium library: checkin(isbn)");
		if (lib.checkin(name))
			System.err.println("Test unsuccessful >.<  medium library: checkin(holder)");

		//PERFORM EXHAUSTIVE ISBN TESTING
		ArrayList<Long> isbns = loadIsbns("Mushroom_Publishing.txt");

		//See if timing is not unreasonably large given ample thread stabilization time
		for(int timerLoop = 0; timerLoop < 5; timerLoop++){
			//Stabilize thread
			long j = 0;
			while(j < 1E7)
				j++;

			int timesToLoop = (int)1E5;

			long startTime = System.nanoTime();



			for(int i = 0; i < timesToLoop; i++)
				for(long l : isbns){
					if(!lib.checkout(l, name, 0, 1, 1999))
						System.err.println("Test unsuccessful >.<  medium library: checkout(ISBN EXHAUSTIVE)");
					if(!lib.lookup(l).equals(name))
						System.err.println("Test unsuccessful >.<  medium library: lookup(EXHAUSTIVE)");
					if(!lib.checkin(l))
						System.err.println("Test unsuccessful >.<  medium library: checkin(EXHAUSTIVE)");

				}

			long midTime = System.nanoTime();


			for(int i = 0; i < timesToLoop; i++)
				for(long l : isbns);

			long endTime = System.nanoTime();

			System.out.println("Time of individual checkout process: " + ((midTime - startTime) - (endTime - midTime)) / timesToLoop / isbns.size() + " nano seconds");

		}
		
		
		//BIG LIBRARY
		ArrayList<LibraryBook> newBooks = generateLibrary(10000);
		lib.addAll(newBooks);
		for(LibraryBook bnook : newBooks){
			if(!lib.checkout(bnook.getIsbn(), name, 0, 1, 1999))
				System.err.println("Test unsuccessful >.<  large library: checkout(ISBN EXHAUSTIVE)");
			if(!bnook.getDueDate().equals(new GregorianCalendar(1999, 0, 1)))
				System.err.println("Test unsuccessful >.<  large library: getDueDate(EXHAUSTIVE)");
			if(!lib.lookup(bnook.getIsbn()).equals(name))
				System.err.println("Test unsuccessful >.<  large library: lookup(EXHAUSTIVE)");
			if(!lib.lookup(bnook.getIsbn()).equals(name))
				System.err.println("Test unsuccessful >.<  large library: lookup(EXHAUSTIVE)");
			if(!lib.checkin(bnook.getIsbn()))
				System.err.println("Test unsuccessful >.<  large library: checkin(EXHAUSTIVE)");
			if((bnook.getDueDate() != null))
				System.err.println("Test unsuccessful >.<  large library: getDueDate(EXHAUSTIVE NULL)");
			
			//Recheck out for final checkin (by holder)
			if(!lib.checkout(bnook.getIsbn(), name, 0, 1, 1999))
				System.err.println("Test unsuccessful >.<  large library: checkout(ISBN EXHAUSTIVE)");
			
		}
		
		if(!lib.checkin(name))
			System.err.println("Test unsuccessful >.< large library: holder checkin exhaustive" );
		
		if(lib.lookup(name).size() != 0)
			System.err.println("Test unsuccessful >.<  large library: not all books checkedin");
		

		System.out.println("Testing done :) #0x1A4");
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
	public static ArrayList<LibraryBook> generateLibrary(int size) {
		ArrayList<LibraryBook> result = new ArrayList<LibraryBook>();

		for (int i = 0; i < size; i++) {
			// generate random ISBN
			Random randomNumGen = new Random();
			String isbn = "";
			for (int j = 0; j < 13; j++)
				isbn += randomNumGen.nextInt(10);

			result.add(new LibraryBook(Long.parseLong(isbn), "An author", "A title"));
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
