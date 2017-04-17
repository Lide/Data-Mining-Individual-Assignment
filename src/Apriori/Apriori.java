package Apriori;

import PreProcessing.PrettyMaker;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by Lisa Denzer on 06/04/2017.
 */

public class Apriori {

    //Method to execute Apriori
    public static List<ItemSet> apriori(int[][] transactions, int supportThreshold) {
        int k; //Level of itemset

        Hashtable<ItemSet, Integer> finalHashtable = new Hashtable<>();
        Hashtable<ItemSet, Integer> frequentItemSets = generateFrequentItemSetsLevel1(transactions, supportThreshold);
        finalHashtable.putAll(frequentItemSets);

        for (k = 1; frequentItemSets.size() > 0; k++) {
            System.out.print("Finding frequent itemsets of length " + (k + 1) + "…");
            frequentItemSets = generateFrequentItemSet(supportThreshold, frequentItemSets, transactions);
            finalHashtable.putAll(frequentItemSets);
            System.out.println(" found " + frequentItemSets.size());
        }

        //Saves frequent-k-itemsets in final hashtable
        for (Map.Entry<ItemSet, Integer> entry : frequentItemSets.entrySet()) {
            finalHashtable.putAll(frequentItemSets);
        }

        //Creating association rules
        int itemsetSize = transactions.length;

        //Calculating relative support;
        double relativeSupport;
        Hashtable<ItemSet, Double> itemsetCount = new Hashtable<>();

        //Go through the final table with all frequent-k-itemsets and calculate the relative support
        for (ItemSet itemset : finalHashtable.keySet()) {
            int count = countSupport(itemset.set, transactions);
            relativeSupport = ((double) itemsetSize / (double) count) * 10;
            itemsetCount.put(itemset, relativeSupport);
        }

        //Calculating minimum confidence
        int minimumConfidence;
        Hashtable<ItemSet, Integer> itemsetConfidence = new Hashtable<>();
        //First item of every
        List<Integer> firstitem = new LinkedList<>();

        for (ItemSet itemset : itemsetCount.keySet()) {
            for (int i = 0; i < itemset.set.length; i++) {
                int firstItem = itemset.set[i];
                firstitem.add(firstItem);
                ItemSet firstitemset = new ItemSet(new int[]{firstItem});
                if (finalHashtable.containsKey(firstitemset)){
                double supportCount = finalHashtable.get(firstitemset);
                double supportCount2 = finalHashtable.get(itemset);
                double confidence = supportCount2 / supportCount * 100;

                List<String> itemsetLabeled = itemsetToGameList(itemset);
                List<String> firstitemsetLabeled = itemsetToGameList(firstitemset);
                DecimalFormat df = new DecimalFormat("####0.00");
                if (confidence != 100.00 && confidence > 60.00){
                    System.out.println("For " + firstitemsetLabeled.toString() + " there is a confidence of " + df.format(confidence) + "% that the last game mentioned in this itemset was also played " + itemsetLabeled.toString());
                }}
            }
        }
        return null;
    }

    private static List<String> itemsetToGameList(ItemSet itemSet) {
        List<String> retval = new LinkedList<>();
        for(int item : itemSet.set) {
            retval.add(PrettyMaker.gameStringEnumerator.getName(item));
        }
        return retval;
    }

    private static Hashtable<ItemSet, Integer> generateFrequentItemSetsLevel1(int[][] transactions,
                                                                              int supportThreshold) {
        Hashtable<ItemSet, Integer> candidateItemSetC1 = new Hashtable<>();
        //For every row in the dataset until the end of the dataset
        for (int i = 0; i < transactions.length; i++) {
            //For every entry in the row until the end of that entry
            for (int j = 0; j < transactions[i].length; j++) {
                //Save the entry as an Itemset "currentItem"
                ItemSet currentItem;
                currentItem = new ItemSet(new int[]{transactions[i][j]});
                Integer itemCount = candidateItemSetC1.get(currentItem);
                //Only save the current item in the candidateSet if it is not already in there
                if (itemCount == null) {
                    candidateItemSetC1.put(currentItem, 1);
                } else { //if it's already in there, count the item and save it in the itemCount
                    candidateItemSetC1.put(currentItem, itemCount + 1);
                }
            }
        }

        /*//Print out Apriori.ItemSet-Overview to check if it worked
        for (Map.Entry<Apriori.ItemSet, Integer> entry : candidateItemSetC1.entrySet()) {
            System.out.println(entry.getKey() + "," + entry.getValue());
        }
        System.out.println(candidateItemSetC1.size());*/

        //Checking which entries in C1 survive the support-threshold
        Hashtable<ItemSet, Integer> frequentItemSetL1 = new Hashtable<>();
        for (Map.Entry<ItemSet, Integer> entry : candidateItemSetC1.entrySet()) {
            if (entry.getValue() >= supportThreshold) {
                frequentItemSetL1.put(entry.getKey(), entry.getValue());
            }

            /*//Print out frequent Itemset to check if it worked
            for (Map.Entry<Apriori.ItemSet, Integer> entry1 : frequentItemSetL1.entrySet()) {
                System.out.println(entry1.getKey() + "," + entry1.getValue());
            }
            System.out.println(frequentItemSetL1.size());*/
        }
        return frequentItemSetL1;
    }

    private static Hashtable<ItemSet, Integer> generateFrequentItemSet(int supportThreshold, Hashtable<
            ItemSet, Integer> frequentItemSetL1, int[][] transactions) {


        List<ItemSet> candidateItemSetC2 = new ArrayList<>();
        Hashtable<ItemSet, Integer> frequentItemSetL2 = new Hashtable<>();
        // For each itemset I1 and I2 of level k-1
        for (ItemSet is : frequentItemSetL1.keySet()) {
            for (ItemSet is2 : frequentItemSetL1.keySet()) {
                if (is.equals(is2)) {
                    continue;
                }
                if (is.set.length != is2.set.length) {
                    continue;
                }
                boolean shouldJoin = true;
                for (int i = 0; i < is.set.length - 1; i++) {
                    if (is.set[i] != is2.set[i]) {
                        shouldJoin = false;
                    }
                }

                if (shouldJoin == false) {
                    continue;
                }

                int[] join = new int[is.set.length + 1];
                for (int i = 0; i < is.set.length; i++) {
                    join[i] = is.set[i];
                }
                join[join.length - 1] = is2.set[is2.set.length - 1];
                Arrays.sort(join);
                ItemSet joinedSet = new ItemSet(join);

                if (!candidateItemSetC2.contains(joinedSet)) {
                    candidateItemSetC2.add(joinedSet);
                }


                for (int p = 0; p < candidateItemSetC2.size(); p++) {
                    //System.out.print(candidateItemSetC2.get(p));
                }
            }

            for (ItemSet itemset : candidateItemSetC2) {
                int count = countSupport(itemset.set, transactions);
                if (count >= supportThreshold) {
                    frequentItemSetL2.put(itemset, count);
                }
            }
        }
        return frequentItemSetL2;
    }


    private static int countSupport(int[] itemSet, int[][] transactions) {

        int count = 0;
        for (int[] transaction : transactions) {
            int element = 0;
            for (int item : transaction) {
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
}

