package Data;

import java.util.*;

/**
 * Created by Lisa Denzer on 06.03.2017.
 */
public class Student implements Comparable<Student> {
    //in the data-cleaning we replace missing values with 0;
    // this variable is used to check for those entries so that they can be processed; e.g. in age
    public static double INVALID_NUMBER = 0;

    public double age;
    public int gender;
    public double shoeSize;
    public double height;
    public int[] playedGames;

    @Override
    public String toString() {
        return "Data.Student{" +
                "age=" + age +
                ", gender='" + gender + '\'' +
                ", shoeSize=" + shoeSize +
                ", playedGames=" + Arrays.toString(playedGames) +
                '}';
    }

    //Calculates the sum of a list of doubles
    public static double sum(List<Double> a) {
        double sum = 0;
        for (int i = 0; i < a.size(); i++) {
            sum = sum + a.get(i);
        }
        return sum;
    }

    //Calculates the mean of a list of doubles
    public static double mean(List<Double> a) {
        double sum = sum(a);
        double mean = 0;

        for (int i = 0; i < a.size(); i++) {
            sum = sum + a.get(i);
        }
        mean = sum / a.size();
        return mean;
    }

    //Calculates the median of a list of doubles
    public static double median(List<Double> a) {
        int middle = a.size() / 2;
        if (a.size() % 2 == 1) {
            return a.get(middle);
        } else {
            return ((a.get(middle - 1) + a.get(middle)) / 2.0);
        }
    }

    //calculates standardDeviation
    public static double stanDev(List<Double> a) {
        double deviationSum = 0;
        double mean = mean(a);
        for (double i : a) {
            deviationSum = Math.pow((i - mean), 2);
        }
        return Math.sqrt(deviationSum / (a.size() - 1));
    }

    public static double standardDeviationAge(List<Student> students) {
        List<Double> ages = new ArrayList<>();
        for (Student s : students) {
            ages.add(s.age);
        }
        return stanDev(ages);
    }

    public static double meanAge(List<Student> students) {
        List<Double> ages = new ArrayList<>();
        for (Student s : students) {
            ages.add(s.age);
        }
        return mean(ages);
    }

    public static double medianAge(List<Student> students) {
        List<Double> ages = new ArrayList<>();

        for (Student s : students) {
            double age = s.age;

            if (age != Student.INVALID_NUMBER) {
                ages.add(age);
            }
        }

        Collections.sort(ages);
        return median(ages);
    }

    public static double medianShoesize(List<Student> students) {
        List<Double> shoeSizes = new ArrayList<>();

        for (Student s : students) {
            double shoeSize = s.shoeSize;

            if (shoeSize != Student.INVALID_NUMBER) {
                shoeSizes.add(shoeSize);
            }
        }

        Collections.sort(shoeSizes);
        return median(shoeSizes);
    }

    public static double medianHeight(List<Student> students) {
        List<Double> heights = new ArrayList<>();

        for (Student s : students) {
            double height = s.height;

            if (height != Student.INVALID_NUMBER) {
                heights.add(height);
            }
        }

        Collections.sort(heights);
        return median(heights);
    }

    @Override
    public int compareTo(Student o) {
        return (int) (this.age - o.age);
    }
}
