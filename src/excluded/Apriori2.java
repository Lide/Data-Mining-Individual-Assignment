package excluded;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class Apriori {
    /***
     * The TRANSACTIONS 2-dimensional array holds the full data set for the lab
     */

    protected int k; // Level of the scan
    private int itemsetCount;  // number of itemsets found
    protected int totalCandidateCount = 0; // number of candidates generated


    public List<ItemSet> runApriori(int[][] BOOK_TRANSACTIONS, int supportThreshold) {
        itemsetCount = 0; //set number of found itemsets to zero
        totalCandidateCount = 0;// set the number of candidate found to zero

        //Hashtable to hold frequent itemsets
        //Map to hold itemsets and support-count;
        //Key: ItemSet, Value: Support-Count
        Hashtable<ItemSet, Integer> frequentItemSets;

        //Creates the first frequent-1-itemset L1 and saves it in "frequentItemSets"
        frequentItemSets = generateFrequentItemSetsLevel1(BOOK_TRANSACTIONS, supportThreshold);

        //Loops through itemsets
        for (k = 1; frequentItemSets.size() > 0; k++) {
            //Indicates that the Algorithms is starting to find frequent itemsets
            System.out.print("Finding frequent itemsets of length " + (k + 1) + "…");

            //Generates frequent-k-itemsets and updates the Hashtable "frequentitemsets" with it.
            frequentItemSets = generateFrequentItemSets(supportThreshold, BOOK_TRANSACTIONS, frequentItemSets);

            Map<ItemSet, Integer> itemSets = new Hashtable<ItemSet, Integer>();


            // TODO: add to list

            //Prints out how many frequent itemsets were found
            System.out.println(" found " + frequentItemSets.size());
        }
        // TODO: create association rules from the frequent itemsets

        // TODO: return something useful
        return null;
    }

    private static Hashtable<ItemSet, Integer> generateFrequentItemSets(int supportThreshold, int[][] transactions,
                                                                        Hashtable<ItemSet, Integer> lowerLevelItemSets) {
        // TODO: first generate candidate itemsets from the lower level itemsets

        /*
         * TODO: now check the support for all candidates and add only those
         * that have enough support to the set
         */

        // TODO: return something useful
        return null;
    }

    private static ItemSet joinSets(ItemSet first, ItemSet second) {
        // TODO: return something useful
        return null;
    }

    private static Hashtable<ItemSet, Integer> generateFrequentItemSetsLevel1(int[][] transactions, int supportThreshold) {
        // TODO: return something useful
        return null;
    }

    private static int countSupport(int[] itemSet, int[][] transactions) {
        // Assumes that items in ItemSets and transactions are both unique

        // TODO: return something useful
        return 0;
    }

}
