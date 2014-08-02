package FinalProject.language;

/**
 * 
 * @author Maks Cegielski-Johnson
 * @author William Guss
 * 
 * Encapsulates a dictionary word and its frequency, used for building the dictionary for spell check
 */
public class Word implements Comparable<Word>{

	
	private String word;
	private int frequency;
	
	public Word(String _word, int _freq){
		this.word = _word;
		this.frequency = _freq;
	}
	
	public Word(String _word){
		this.word = _word;
		this.frequency =  -1;
	}
	
	public int getFrequency(){
		return frequency;
	}

	@Override
	public int compareTo (Word arg0) {
		return this.word.compareTo(arg0.word);
	}
	
	public String toString(){
		return word + "=" + frequency; 
	}
	
	public String getWord(){
		return word;
	}
	
	public char[] toCharArray(){
		return word.toCharArray();
	}

	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + frequency;
		result = prime * result + ((word == null) ? 0 : word.hashCode());
		return result;
	}

	@Override
	public boolean equals (Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Word other = (Word)obj;
		if (word == null) {
			if (other.word != null) return false;
		} else if (!word.equals(other.word)) return false;
		return true;
	}
}
