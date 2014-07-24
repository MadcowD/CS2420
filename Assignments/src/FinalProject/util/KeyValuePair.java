package FinalProject.util;

/**
 * The tuple class for convenient "tupling"
 * @author TODO: MAKE AUTHOR
 *
 * @param The first element of the tuple
 * @param The second element of the tuple
 */
public class KeyValuePair<K,V> {
	public V Value;
	public K Key;

	public KeyValuePair(K a, V b){
		this.Key = a;
		this.Value = b;
	}

}
