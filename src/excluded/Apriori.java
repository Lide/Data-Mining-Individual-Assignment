package excluded; /**
 * Created by Lisa Denzer on 26/03/2017.
 */

import java.io.IOException;
import java.util.*;

public class Apriori {

    protected int k; //Level of the Scan
    private int itemSetCount;
    protected int totalCandidateCount = 0;
    private int supportThreshold;
    private List<int[]> database = null;
    private int databaseSize;

    //Constructor
    public Apriori() {
    }


    public List<ItemSet> apriori(int[][] GAMES, int supportThreshold) throws IOException {

        itemSetCount = 0;
        totalCandidateCount = 0;

        //Map that saves the item and its support-count; key: item, value: support
        Hashtable<ItemSet, Integer> mapItemCount = new Hashtable<>();

        //Database
        database = new ArrayList<int[]>();

        // variable to count the number of transactions
        databaseSize = 0;


            //For each item in this line (games)
            for (int i = 0; i < data.length; i++)
                for (int j = 0; j < GAMES[i].length; j++) {
                    ItemSet currentItem = new ItemSet(new int[]{GAMES[i][j]});
                    Integer count = mapItemCount.get(currentItem);
                    if (count == null) {
                        mapItemCount.put(currentItem, 1);
                    } else {
                        mapItemCount.put(currentItem, ++count);
                    }
                }

            database.add(transaction);
            databaseSize++;

            Object[] finalResult = mapItemCount.keySet().toArray();

        /*Step 2: checks all keys and removes those with values under the supportThreshold*/
            for (Object keyObj : finalResult) {
                ItemSet key = (ItemSet) keyObj;
                if (mapItemCount.get(key) < supportThreshold) {
                    mapItemCount.remove(key);
                }
            }
            //Returns the ItemSet L1, including frequent itemsets and support count
            return mapItemCount;
        }


        // we start looking for itemset of size 1
        k = 1;

        /*//Generate our first itemset from GAMES
        //Hashtable that saves the item and its support-count; key: item, value: support
        private Hashtable<ItemSet, Integer> frequentItemSetL1 = generateFrequentItemSetL1(GAMES, supportThreshold);
        for (k = 1; 0 < frequentItemSetL1.size(); k++) {
            System.out.print("Finding frequent itemsets of length " + (k + 1) + "…");
            frequentItemSetL1 = generateItemSetL2(supportThreshold, GAMES, frequentItemSetL1);


            // TODO: add to list


            //Prints out how many frequent itemsets were found
            System.out.println(" found " + frequentItemSetL1.size());
        }

        // TODO: create association rules from the frequent itemsets

        // TODO: return something useful

        return null;*/


    /*Step 1: In the first iteration, each item is a member of the set of candidate-1-itemsets C1.
    C1 = GAMES Array
     */


        private Hashtable<ItemSet, Integer> generateFrequentItemSetL1 ( int[][] GAMES, int supportThreshold){

            //Creating ItemSetL1 from the dataset GAMES
            Hashtable<ItemSet, Integer> ItemSetL1 = new Hashtable<>();
            for (int i = 0; i < GAMES.length; i++) {
                for (int j = 0; j < GAMES[i].length; j++) {

                    ItemSet currentItem = new ItemSet(new int[]{GAMES[i][j]});
                    if (!ItemSetL1.contains(GAMES[i][j])) {
                        ItemSetL1.put(currentItem, 1);
                    } else {
                        int itemCount = ItemSetL1.get(currentItem);
                        ItemSetL1.put(currentItem, itemCount + 1);
                    }
                }
            }

            Object[] finalResult = ItemSetL1.keySet().toArray();

        /*Step 2: checks all keys and removes those with values under the supportThreshold*/
            for (Object keyObj : finalResult) {
                ItemSet key = (ItemSet) keyObj;
                if (ItemSetL1.get(key) < supportThreshold) {
                    ItemSetL1.remove(key);
                }
            }

            //Returns the ItemSet L1, including frequent itemsets and support count
            return ItemSetL1;
        }

        private static int[] countSupport ( int[] itemSet, int[][] GAMES){

            Map<String, Integer> saveCount = new TreeMap<>();
            int count = 0;
            for (int[] entry : GAMES) {
                //Looping through every item (Game-name) of an entry (all Games of one student)


                int element = 0;
                for (int item : entry) {

                    if (itemSet[element] == item) {
                        element++;
                        if (element == itemSet.length) {
                            count++;
                            break;
                        }
                    }
                }
            }
            return count;
        }

        //Step 3 in the book
        // Generating itemset L2 using Join-Results from L1 and L1
        private static Hashtable<ItemSet, Integer> generateItemSetL2 ( int supportThreshold, int[][] GAMES,
        Hashtable<ItemSet, Integer> ItemSetL1){

            Hashtable<ItemSet, Integer> ItemSetL2 = new Hashtable<>();

            Object[] allItemSetKeys = ItemSetL1.keySet().toArray();

            //Checking if item should be added to new ItemSet
            for (int i = 0; i < allItemSetKeys.length; i++) {
                ItemSet a = (ItemSet) allItemSetKeys[i];

                for (int j = i + 1; j < allItemSetKeys.length; j++) {
                    //ItemSet b gets filled with j's
                    ItemSet b = (ItemSet) allItemSetKeys[j];

                    boolean shouldAdd = true;

                    for (int z = 0; z < a.set.length - 1; z++) {
                        //if set a is not set b don't add it to ItemSetL2
                        if (a.set[z] != b.set[z]) {
                            shouldAdd = false;
                            break;
                        }
                    }

                /*Step 5 "The set of frequent 2-itemsets L2 is then determined,
                consisting of those candidate 2-itemsets in C2 having minimum support.*/
                    if (shouldAdd) {
                        ItemSet newSet = joinSets(a, b);

                        int count = countSupport(newSet.set, GAMES);

                        if (count >= supportThreshold) {
                            ItemSetL2.put(newSet, count);
                        }
                    }
                }

                // TODO: first generate candidate itemsets from the lower level itemsets


        /*
         * TODO: now check the support for all candidates and add only those
         * that have enough support to the set
         */

                for ()

            }

            return ItemSetL2;

        }

        //Joining two ItemSets

    private static ItemSet joinSets(ItemSet aL1, ItemSet bL1) {
        int[] joinedSets = new int[aL1.set.length + 1];

        for (int i = 0; i < aL1.set.length; i++) {
            joinedSets[i] = aL1.set[i];
        }
        joinedSets[joinedSets.length - 1] = bL1.set[bL1.set.length - 1];

        return new ItemSet(joinedSets);
    }

}
