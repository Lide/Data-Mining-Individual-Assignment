//import excluded.Apriori;

import Apriori.Apriori;
import Data.Student;
import KMeans.KMeans;
import KNN.knn;
import PreProcessing.CSVFileReader;
import PreProcessing.PrettyMaker;
import PreProcessing.StringEnumerator;

import java.io.IOException;
import java.util.*;

/**
 * Created by Lisa Denzer on 06.03.2017.
 */
public class Main {

    public static void main(String args[]) {
        String[][] data;
        List<Student> studentList = new ArrayList<>();

        //Tries to read in the data and parse the values by calling methods from PrettyMaker
        try {
            data = CSVFileReader.readDataFile("C:\\Users\\Lisa Denzer\\Google Drive\\IT University of Copenhagen\\3rd Sem 2017\\Data Mining\\Data Mining Individual Assignment\\src\\Data Mining - Spring 2017.csv", ";;", "-", false);
            System.out.println("Dataset was loaded successfully and contains " + data.length + " tuples.");

            data = PrettyMaker.cleanDoubleQuotes(data);
            data = PrettyMaker.replaceCommaWithDot(data);

            for (String[] line : data) {
                double age = PrettyMaker.parseAge(line[1]);
                int gender = PrettyMaker.parseGender(line[2]);
                double shoeSize = PrettyMaker.parseShoesize(line[3]);
                double height = PrettyMaker.parseHeight(line[4]);
                int[] playedGames = PrettyMaker.parseGame(line[20]);

                //Updating variables
                Student student = new Student();
                student.age = age;
                student.gender = gender;
                student.shoeSize = shoeSize;
                student.height = height;
                student.playedGames = playedGames;

                //Adding the student to the studentList
                studentList.add(student);
            }

            //Basic statistical descriptions for key attributes
            Student.medianAge(studentList);
            Student.medianHeight(studentList);
            Student.medianShoesize(studentList);
            System.out.println("Basic statistical descriptions for key attributes: Median Age: " + Student.medianAge(studentList)
                    + " Median Height: " + Student.medianHeight(studentList) + " Median Shoesize: " + Student.medianShoesize(studentList));

            //For every student on the studentList, check if the age is invalid, and if so, replace with medianAge;
            double medianAge = Student.medianAge(studentList);
            for (Student s : studentList) {
                double age = s.age;

                if (age == Student.INVALID_NUMBER) {
                    s.age = (int) medianAge;
                }
            }

            //For every student on the studentList, check if the shoeSize is invalid, and if so, replace with medianAge;
            double medianShoesize = Student.medianShoesize(studentList);
            for (Student s : studentList) {
                double shoeSize = s.shoeSize;

                if (shoeSize == Student.INVALID_NUMBER) {
                    s.shoeSize = (int) medianShoesize;
                }
            }

            //For every student on the studentList, check if the height is invalid, and if so, replace with medianAge;
            double medianHeight = Student.medianHeight(studentList);
            for (Student s : studentList) {
                double height = s.height;

                if (height == Student.INVALID_NUMBER) {
                    s.height = (int) medianHeight;
                }
            }

            //Start Apriori
            System.out.println("Starting Apriori");
            //Setting supportThreshold for Apriori
            final int supportThreshold = 20;
            Apriori.apriori(PrettyMaker.getGamesArray(data, studentList), supportThreshold);

            //Remove students that have invalid values in age, shoeSize and height;
            PrettyMaker.removeFishyStudents(studentList, medianAge, medianShoesize, medianHeight);

            //Start K-Nearest-Neighbour
            System.out.println("Starting K-Nearest-Neighbour using age, height and shoesize to predict gender");
            knn.runKNN(studentList);

            //Start K-Means
            System.out.println("Starting K-Means using shoeSize and age to create two clusters");
            KMeans kMeans = new KMeans();
            kMeans.runKMeans(studentList);

        } catch (
                IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }
}
