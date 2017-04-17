package KNN;

import Data.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class knn {


    public static void runKNN(List<Student> studentList) {

        ArrayList<Student> testSet = new ArrayList<>();
        for (int i = 0; i < studentList.size() / 3; i++) {
            testSet.add(studentList.get(i));
        }
        System.out.println("There are " + testSet.size() + " students in the Testset");

        ArrayList<Student> trainingSet = new ArrayList<>();
        for (int i = (studentList.size() / 3) + 1; i < studentList.size(); i++) {
            trainingSet.add(studentList.get(i));
        }
        System.out.println("There are " + trainingSet.size() + " students in the Trainingset");

        for (int i = 0; i < testSet.size(); i++) {
            Student testStudent = testSet.get(i);

            Map<Student, Double> allDistances = new HashMap<>();

            for (int j = 0; j < trainingSet.size(); j++) {
                Student studentForComparison = trainingSet.get(j);

                double distanceSum = 0;
                double ageDistance = Math.pow(testStudent.age - studentForComparison.age, 2);
                double heightDistance = testStudent.height - studentForComparison.height;
                double shoeSizeDistance = testStudent.shoeSize - studentForComparison.shoeSize;

                distanceSum = ageDistance + heightDistance + shoeSizeDistance;

                double finalDistance = Math.sqrt(distanceSum);
                allDistances.put(studentForComparison, finalDistance);
            }

            knn.makeAssumption(testStudent, allDistances);
            trainingSet.add(testStudent);

        }

    }

    public static void makeAssumption(Student student, Map<Student, Double> allDistances) {
        int k = 2;

        List<Student> sortedStudents =
                allDistances.entrySet().stream().sorted((x, y) -> x.getValue()
                        .compareTo(y.getValue())).map(Map.Entry::getKey)
                        .collect(Collectors.toList());

        int maleCount = 0;
        for (int i = 0; i < k; i++) {
            Student closeStudent = sortedStudents.get(i);

            if (closeStudent.gender == 1) {
                maleCount++;
            }
        }

        if (maleCount > k / 2) {
            //Assume it is male

            //Check if it is male
            if (student.gender == 1) {
                System.out.println("TP");
            } else {
                System.out.println("TN");
            }
        } else {
            //Assume it is female

            //Check if it is female
            if (student.gender == 0) {
                System.out.println("FP");
            } else {
                System.out.println("FN");
            }
        }
    }
}
