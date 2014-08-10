package FinalProject.language;

import java.util.Comparator;

/**
 * 
 * @author Maks Cegielski-Johnson
 * @author William Guss
 * 
 * This Comparator compares Words based on their frequencies, and if they have the same frequency
 * then in lexographical order.
 *
 */
public class FrequencyCompare implements Comparator<Word> {

	@Override
	public int compare(Word lhs, Word rhs) {
		if(lhs.getFrequency() == rhs.getFrequency())
			return lhs.compareTo(rhs);
		return rhs.getFrequency() - lhs.getFrequency();
	}

}
