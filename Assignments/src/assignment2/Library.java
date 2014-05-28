package assignment2;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * @author William Guss & Maks Cegielski-Johnson
 * Class representation of a library (a collection of library books).
 * 
 */
public class Library {


	/**
	 * The list of books in the library.
	 */
	private ArrayList<LibraryBook> library;

	public Library() {
		library = new ArrayList<LibraryBook>();
	}

	/**
	 * Add the specified book to the library, assume no duplicates.
	 * 
	 * @param isbn --
	 *          ISBN of the book to be added
	 * @param author --
	 *          author of the book to be added
	 * @param title --
	 *          title of the book to be added
	 */
	public void add(long isbn, String author, String title) {
		library.add(new LibraryBook(isbn, author, title));
	}

	/**
	 * Add the list of library books to the library, assume no duplicates.
	 * 
	 * @param list --
	 *          list of library books to be added
	 */
	public void addAll(ArrayList<LibraryBook> list) {
		library.addAll(list);
	}

	/**
	 * <3<3<3<3
	 * Add books specified by the input file. One book per line with ISBN, author,
	 * and title separated by tabs.
	 * 
	 * If file does not exist or format is violated, do nothing.
	 * 
	 * @param filename
	 */
	public void addAll(String filename) {
		ArrayList<LibraryBook> toBeAdded = new ArrayList<LibraryBook>();

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

				if (!lineIn.hasNext())
					throw new ParseException("Author", lineNum);
				String author = lineIn.next();

				if (!lineIn.hasNext())
					throw new ParseException("Title", lineNum);
				String title = lineIn.next();

				toBeAdded.add(new LibraryBook(isbn, author, title));

				lineNum++;
			}
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage() + " Nothing added to the library.");
			return;
		} catch (ParseException e) {
			System.err.println(e.getLocalizedMessage()
					+ " formatted incorrectly at line " + e.getErrorOffset()
					+ ". Nothing added to the library.");
			return;
		}

		library.addAll(toBeAdded);
		//lol thanks paymon #udaBest-2-420
	}

	/**
	 * Returns the holder of the library book with the specified ISBN.
	 * 
	 * If no book with the specified ISBN is in the library, returns null.
	 * 
	 * @param isbn --
	 *          ISBN of the book to be looked up
	 */
	public String lookup(long isbn) {
		//For every book that is checked out return the holder of the book with the same ISBN
		for(LibraryBook b : library)
			if(b.getIsbn() == isbn)
				if(b.isChecked())
					return b.getHolder();

		return null;
	}

	/**
	 * Returns the list of library books checked out to the specified holder.
	 * 
	 * If the specified holder has no books checked out, returns an empty list.
	 * 
	 * @param holder --
	 *          holder whose checked out books are returned
	 */
	public ArrayList<LibraryBook> lookup(String holder) {
		ArrayList<LibraryBook> result = new ArrayList<>();

		for(LibraryBook b : library)
			if(b.isChecked())
				if(b.getHolder().equals(holder))
					result.add(b);

		return result;
	}

	/**
	 * Sets the holder and due date of the library book with the specified ISBN.
	 * 
	 * If no book with the specified ISBN is in the library, returns false.
	 * 
	 * If the book with the specified ISBN is already checked out, returns false.
	 * 
	 * Otherwise, returns true.
	 * 
	 * @param isbn --
	 *          ISBN of the library book to be checked out
	 * @param holder --
	 *          new holder of the library book
	 * @param month --
	 *          month of the new due date of the library book
	 * @param day --
	 *          day of the new due date of the library book
	 * @param year --
	 *          year of the new due date of the library book
	 * 
	 */
	public boolean checkout(long isbn, String holder, int month, int day, int year) {
		for(LibraryBook b : library)
			if(b.getIsbn() == isbn)
				if(!b.isChecked()){
					//In the case that the book has the same ISBN and is checked in check the book out to the holder on the given date.
					b.setChecked(holder, new GregorianCalendar(year, month, day));
					return true;
				}

		return false;
	}

	/**
	 * Unsets the holder and due date of the library book.
	 * 
	 * If no book with the specified ISBN is in the library, returns false.
	 * 
	 * If the book with the specified ISBN is already checked in, returns false.
	 * 
	 * Otherwise, returns true.
	 * 
	 * @param isbn --
	 *          ISBN of the library book to be checked in
	 */
	public boolean checkin(long isbn) {
		for(LibraryBook b : library)
			if(b.getIsbn() == isbn)
				if(b.isChecked())
				{
					b.setChecked();
					return true;
				}

		return false;
	}

	/**
	 * Unsets the holder and due date for all library books checked out be the
	 * specified holder.
	 * 
	 * If no books with the specified holder are in the library, returns false;
	 * 
	 * Otherwise, returns true.
	 * 
	 * @param holder --
	 *          holder of the library books to be checked in
	 */
	public boolean checkin(String holder) {
		ArrayList<LibraryBook> books = new ArrayList<LibraryBook>();
		for(LibraryBook b : library){
			if(b.isChecked() && b.getHolder().equals(holder))
				books.add(b.setChecked());
		}

		if(books.size() == 0)
			return false;

		return true;
	}
}
