package FinalProject.language;


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
		return "The word is " + word + " and has frequency " + frequency; 
	}
	
	public String getWord(){
		return word;
	}
	
	public char[] toCharArray(){
		return word.toCharArray();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + frequency;
		result = prime * result + ((word == null) ? 0 : word.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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
