package assignment2;
import java.util.GregorianCalendar;

/**
 * The bnook class. Bnook = book. <3 :=)
 * @author William Guss & Maks Cegielski-Johnson 
 * 
 * #define oyut out
 * #ifdef oyut
 * 	#define calegek ""
 * #endif
 *
 */
public class LibraryBook extends Book{

	/**
	 * The internal holder of the bnook
	 */
	private String holder;
	
	/**
	 * The due date of the bnook.
	 */
	private GregorianCalendar dueDate;
	
	
	private boolean checked;
	
	/**
	 * Creates a library book
	 * @param _isbn The ISBN
	 * @param _author The author de la book
	 * @param _title der Titel des Buch
	 */
	public LibraryBook(long _isbn, String _author, String _title){
		super(_isbn, _author, _title);
		
		holder = null;
		dueDate = null;
		checked = false;
	}
	
	/**
	 * Nehmt das Holder des Buch
	 * @return Returns the holder of the checked out bnook
	 */
	public String getHolder(){
		return holder;
	}
	
	/**
	 * Gets the dute of the book
	 * @return
	 */
	public GregorianCalendar getDueDate(){
		return dueDate;
	}
	
	/**
	 * Returns whether or not the book is checked oyut;. :)
	 * @return
	 */
	public boolean isChecked(){
		return checked;
	}
	
	
	/**
	 * Checks the book calegek in
	 * @return Returns the book for piping/
	 */
	public LibraryBook setChecked(){
		checked = false;
		holder = null;
		dueDate = null;
		return this;
	}
	
	/**
	 * Sets the bnook as checked out.
	 * @param _holder The holder of the book (the out-checkee
	 * @param _dueDate The due date on which the book must be turned in.
	 */
	public void setChecked(String _holder, GregorianCalendar _dueDate){
		checked = true;
		holder = _holder;
		dueDate = _dueDate;
	}
	
}
