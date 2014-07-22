package maksFinal;

public class CompressionData implements Comparable<CompressionData>{

	private int frequency;
	private int characterValue;	
	
	public CompressionData(int frequency, int characterValue){
		this.frequency = frequency;
		this.characterValue = characterValue;
	}
	
	public CompressionData(int value, boolean frequency){
		if(frequency){
			this.frequency = value;
			this.characterValue = -1;
			return;
		}
		else{
			this.frequency = 1;
			this.characterValue = value;
		}
	}
	
	

	public int getCharacter(){
		return this.characterValue;
	}
	
	public int getFrequency(){
		return this.frequency;
	}
	
	public void updateFrequency(){
		this.frequency++;
	}
	
	@Override
	public boolean equals(Object arg0){
		CompressionData other = (CompressionData) arg0;
		return this.characterValue == other.characterValue;
	}
	

	
	public String toString(){
		return "Character: " + (char)this.characterValue + " and Frequency: " + this.frequency;
	}

	@Override
	public int compareTo(CompressionData other) {
		if(this.frequency == other.frequency)
			return -other.characterValue + this.characterValue;
		return -other.frequency + this.frequency;
	}
	
	
}
