package Apriori; /**
 * Created by Lisa Denzer on 26/03/2017.
 */

import java.util.Arrays;

/***
 * The Apriori.ItemSet class is used to store information concerning a single transaction.
 * Should not need any changes.
 *
 */
public class ItemSet {

    /***
     * The PRIMES array is internally in the Apriori.ItemSet-class' hashCode method
     */
    private static final int[] PRIMES = {2, 3, 5, 7, 11, 13, 17, 23, 27, 31, 37};
    final int[] set;

    /***
     * Creates a new instance of the Apriori.ItemSet class.
     * @param set Transaction content
     */
    public ItemSet(int[] set) {
        this.set = set;
    }

    @Override
    /**
     * hashCode functioned used internally in Hashtable
     */
    public int hashCode() {
        int code = 0;
        for (int i = 0; i < set.length; i++) {
            code += set[i] * PRIMES[i];
        }
        return code;
    }


    @Override
    public String toString() {
        return "Apriori.ItemSet{" +
                "set=" + Arrays.toString(set) +
                '}';
    }

    @Override
    /**
     * Used to determine whether two Apriori.ItemSet objects are equal
     */

    public boolean equals(Object o) {
        if (!(o instanceof ItemSet)) {
            return false;
        }
        ItemSet other = (ItemSet) o;
        if (other.set.length != this.set.length) {
            return false;
        }
        for (int i = 0; i < set.length; i++) {
            if (set[i] != other.set[i]) {
                return false;
            }
        }
        return true;
    }
}
