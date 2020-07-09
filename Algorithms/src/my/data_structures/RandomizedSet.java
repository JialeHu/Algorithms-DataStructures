package my.data_structures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * 
 * @author Jiale Hu
 * 
 *         RandomizedSet
 */
public class RandomizedSet {

    HashMap<Integer, Integer> map;
    ArrayList<Integer> list;
    Random rand;

    /** Initialize data structure. */
    public RandomizedSet() {
	map = new HashMap<Integer, Integer>();
	list = new ArrayList<Integer>();
	rand = new Random();
    }

    /**
     * Inserts a value to the set.
     * 
     * @param val {@code int} value to be inserted.
     * @return Returns true if the set did not already contain the specified
     *         element.
     */
    public boolean insert(int val) {
	if (map.containsKey(val))
	    return false;
	list.add(val);
	map.put(val, list.size() - 1);
	return true;
    }

    /**
     * Removes a value from the set.
     * 
     * @param val {@code int} value to be removed.
     * @return Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
	if (!map.containsKey(val))
	    return false;
	int idx = map.remove(val);
	if (idx < list.size() - 1) {
	    list.set(idx, list.remove(list.size() - 1));
	    map.replace(list.get(idx), idx);
	} else
	    list.remove(list.size() - 1);
	return true;
    }

    /**
     * Get a random element from the set.
     * 
     * @return a random element from the set.
     */
    public int getRandom() {
	return list.get(rand.nextInt(list.size()));
    }
}
