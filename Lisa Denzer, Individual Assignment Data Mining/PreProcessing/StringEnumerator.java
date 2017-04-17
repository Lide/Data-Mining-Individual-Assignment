package PreProcessing;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lisa Denzer on 10/04/2017.
 */
public class StringEnumerator {

    private Map<String, Integer> stringMap = new HashMap<>();
    private Map<Integer, String> intMap = new HashMap<>();

    public int getNumber(String key){
        if (stringMap.containsKey(key)){
            return stringMap.get(key);
        }

        int numberOfValues = stringMap.size();
        stringMap.put(key, numberOfValues);
        intMap.put(numberOfValues, key);
        return numberOfValues;
    }

    public String getName(int key){
        return intMap.get(key);
    }

    public int size(){
        return intMap.size();
    }

}

