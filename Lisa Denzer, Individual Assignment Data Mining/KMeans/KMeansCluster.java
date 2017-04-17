package KMeans;

import Data.Student;

import java.util.ArrayList;

/**
 * Created by Lisa Denzer on 13/04/2017.
 */
public class KMeansCluster {

//ToDo: Compute cluster mean based on cluster members.

    //Method to calculate the mean based of all students
    //Centroid

    public double centroidX;
    public double centroidY;

    //Calculate centroid => Method
    public double calculateDistance(Student student) {
        double sum = 0;
        sum += Math.pow(student.height - centroidX, 2);
        sum += Math.pow(student.shoeSize - centroidY, 2);
        return Math.sqrt(sum);
    }

    public void calculateCentroid() {
        double X = 0;
        double Y = 0;
        for (Student student : ClusterMembers) {
            X += student.height;
            Y += student.shoeSize;
        }
        X = X / ClusterMembers.size();
        Y = Y / ClusterMembers.size();

        centroidX = X;
        centroidY = Y;
    }

    public ArrayList<Student> ClusterMembers;

    public KMeansCluster() {
        this.ClusterMembers = new ArrayList<Student>();
    }


    @Override
    public String toString() {
        String toPrintString = "-----------------------------------CLUSTER START------------------------------------------" + System.getProperty("line.separator");

        for (Student i : this.ClusterMembers) {
            toPrintString += i.shoeSize + " " + i.height + System.getProperty("line.separator");
        }
        toPrintString += "-----------------------------------CLUSTER END-------------------------------------------" + System.getProperty("line.separator");

        return toPrintString;
    }

}
