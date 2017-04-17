package excluded;

/**
 * Created by Lisa Denzer on 25/03/2017.
 */
public class DataCleaner_notsure {

    public static double distanceFunction(double x, double y){
        return 0;
    }

    public static void detectOutliers(double[] ageList, double r) {
        int n = ageList.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (i == j) continue;

                double O_i = ageList[i];
                double O_j = ageList[j];

                if (distanceFunction(O_i, O_j) < r );

            }
        }
    }

}
