package excluded;

import java.util.Arrays;

/**
 * Created by Lisa Denzer on 06.03.2017.
 */
public class DataCleaner {

    public int cleanAge(int age) {
        //Parsing values
        int age = Integer.parseInt(line[1]);
        //Replacing all ""Value"" with "Value"
        line[1] = line[1].replace("\"", "");

        //allAges = addAges(age);

        line[2] = line[2].replaceAll("\"", "");
        line[3] = line[3].replaceAll("\"", "");
        line[4] = line[4].replaceAll("\"", "");
        line[5] = line[5].replaceAll("\"", "");
        line[6] = line[6].replaceAll("\"", "");
        line[7] = line[7].replaceAll("\"", "");
        line[8] = line[8].replaceAll("\"", "");


        String gender = line[2];
        gender = Character.toString(gender.charAt(0)).toLowerCase();
        if (gender != "m") {
            gender = "null";
        }

        //double shoeSize = Double.parseDouble(line[3]);
        //int height = Integer.parseInt(line[4]);
        String degree = line[5];
        String courseMotivation = line[6];
        String programmingLanguages = line[7];
        String preferredOS = line[8];
        String interestLargeDBs = line[9];

        //Updating variables
        Student student = new Student();

        //Trying to set age to 0 if it is above 50
        student.age = age;
        if (age > 50) {
            student.age = 0;
        }

        student.gender = gender;

        //student.shoeSize = shoeSize;
        //student.height = height;


        //Printing out variables with values
        System.out.println("The students age: " + student.age + ", gender: " + student.gender +
                ", centimeters" + ", degree: " + degree);
        System.out.println(Arrays.toString(line));
    }
//for(int i : allAges){}

			System.out.println("Number of tuples loaded: "+data.length);

    public static int[] addAges(int age) {
        int[] array = new int[]{};
        int s = array.length;
        array[s] = age;

        return array;
    }

}
