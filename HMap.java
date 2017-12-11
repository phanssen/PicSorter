import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 
 * @author Rayne Wang
 * Date: 11/11/2017
 * This class creates a hash map to sort the photos 
 *
 */
public class HMap <K, V>{
	private HashMap<K, ArrayList<V>> hMap; // hashmap 
	
	//constructor 
	public HMap(){
		hMap = new HashMap<K, ArrayList<V>>();
	}
	
	/**
	 * add to map
	 * @param key
	 * @param value
	 */
	public  void insert(K key, V value){
		if(!hMap.containsKey(key)){ 
			hMap.put(key, new ArrayList<V>()); //creates a new array if new key
		}
		hMap.get(key).add(value); // add value to the array of keys
	}
	
	/**
	 * get all the corresponding addresses for a given key
	 * @param key
	 * @return the arrayList of values
	 */
	public ArrayList<V> get(K key){
		return hMap.get(key);
	}
	
	/**
	 * removes an address 
	 * @param key
	 * @param value
	 * @return true if the item was removed
	 */
	public boolean remove(K key, V value){
		return hMap.get(key).remove(value);
	}
	
	/**
	 * 
	 * @return set of all keys
	 */
	public Set<K> getKeys(){
		return hMap.keySet();
	}
	
	/**
	 * 
	 * @return arrayList of all values in the hash map
	 */
	public Collection<ArrayList<V>> getAllValues(){
		return hMap.values();
	}
	
	//returns an array list of all duplicates in the hash map
	public Collection<ArrayList<V>> getAllDuplicates(){
		Collection<ArrayList<V>> duplicates = hMap.values();
		
		Iterator<ArrayList<V>> iter = duplicates.iterator();
		
		while(iter.hasNext()){
			ArrayList<V> list = iter.next();
			
			if (list.size() == 1) iter.remove();
		}

		return duplicates;
	}
	
	//test function
	//expected output:
	//keys:										[0.3, 0.1]
	//duplicate list:							[[world,earth][hi,hello]]
	//entry list for key 0.1:					[hi,hello]
	//entry list for key 0.1 after removing hi:	[hello]
	public static void main(String[] args) {
		HMap <Double, String> n = new HMap();
		n.insert(0.1, "hi");
		n.insert(0.1, "hello");
		n.insert(0.3, "world");
		n.insert(0.3, "earth");
		System.out.println(n.getKeys());
		System.out.println(n.getAllDuplicates());
		System.out.println(n.get(0.1));
		n.remove(0.1, "hi");
		System.out.println(n.get(0.1));
		
	}

}
