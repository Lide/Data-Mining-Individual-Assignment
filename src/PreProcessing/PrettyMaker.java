package PreProcessing;

import Data.Student;

import java.util.*;


/**
 * Created by Lisa Denzer on 09/04/2017.
 */
public class PrettyMaker {

    //Replacing all ""Value"" with "Value"
    public static String[][] cleanDoubleQuotes(String[][] data) {

        for (String[] line : data) {
            line[1] = line[1].replaceAll("\"", "");
            line[2] = line[2].replaceAll("\"", "");
            line[3] = line[3].replaceAll("\"", "");
            line[4] = line[4].replaceAll("\"", "");
            line[5] = line[5].replaceAll("\"", "");
            line[8] = line[8].replaceAll("\"", "");
            line[20] = line[20].replaceAll("\"", "");

        }
        return data;
    }

    public static String[][] replaceCommaWithDot(String[][] data) {
        for (String[] line : data) {
            line[3] = line[3].replaceAll(",", ".");
        }
        return data;
    }

    public static double parseAge(String ageLine) {
        double age = Student.INVALID_NUMBER;
        try {
            age = Double.parseDouble(ageLine);
            if (age < 15 || age > 70) {
                age = Student.INVALID_NUMBER;
            }
        } catch (NumberFormatException e) {
            System.out.println("Sorry, could not parse value for age");
        }
        return age;
    }

    public static int parseGender(String genderLine) {
        String gender = genderLine;
        int newGender = 0;
        try {
            if (gender.length() > 0) {
                char ch = gender.charAt(0);
                gender = Character.toString(ch);
                gender = gender.toLowerCase();
                if (gender != "m" && gender != "f") {
                    gender = "m";
                }
                if (gender == "m") {
                    newGender = 1;
                }
                if (gender == "f") {
                    newGender = 0;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Sorry, could not parse value for gender");
        }
        return newGender;
    }

    public static double parseShoesize(String shoesizeLine) {
        double shoeSize = 0.0;
        shoesizeLine = shoesizeLine.replaceAll("[\\D]", "");
        try {
            shoeSize = Double.parseDouble(shoesizeLine);
            if (shoeSize < 35 || shoeSize > 50) {
                shoeSize = 0.0;
            }

        } catch (NumberFormatException e) {
            System.out.println("Sorry, could not parse value for shoesize");
        }
        return shoeSize;
    }


    public static double parseHeight(String heightLine) {
        int height = 0;
        heightLine = heightLine.replaceAll("[\\D]", "");
        try {
            height = Integer.parseInt(heightLine);
            if (height < 100 || height > 230) {
                height = 0;
            }

        } catch (NumberFormatException e) {
            System.out.println("Sorry, could not parse value for height");
        }
        return height;
    }

    public static void removeFishyStudents(List<Student> studentList, double medianAge, double medianShoesize, double medianHeight) {
        List<Student> toRemove = new ArrayList<>();
        for (Student s : studentList) {
            if (s.age == medianAge && s.shoeSize == medianShoesize && s.height == medianHeight) {
                toRemove.add(s);
            }
        }
        studentList.removeAll(toRemove);

        for (Student s : studentList) {
            System.out.println(s);
        }
    }

    public static StringEnumerator gameStringEnumerator = new StringEnumerator();

    public static int[] parseGame(String gameLine) {
        String playedGames = gameLine;
        String[] gameNames = playedGames.split(";");


        int[] gameNumbers = new int[gameNames.length];
        for (int i = 0; i < gameNames.length; i++) {
            if (!gameNames[i].equals("I have not played any of these games")) {
                gameNumbers[i] = gameStringEnumerator.getNumber(gameNames[i]);
            }
        }
        Arrays.sort(gameNumbers);
        return gameNumbers;
    }


    public static int[][] getGamesArray(String[][] data, List<Student> studentList) {
        int[][] GAMES = new int[data.length][];
        for (int i = 0; i < studentList.size(); i++) {
            Student Marco = studentList.get(i);
            GAMES[i] = Marco.playedGames;
        }
        return GAMES;
    }
}
