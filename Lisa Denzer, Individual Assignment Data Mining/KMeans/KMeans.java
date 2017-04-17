package KMeans;

import Data.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Lisa Denzer on 15/04/2017.
 */
public class KMeans {

    public KMeans() {
    }

    private final int k = 2;

    public void runKMeans(List<Student> studentList) {
        createClusters(studentList);
        KMeanstest(studentList);
    }

    List<KMeansCluster> clusterList = new ArrayList<>();

    //creating a number of clusters k and assigning random students to it
    public void createClusters(List<Student> studentList) {
        Random randomizer = new Random();
        for (int i = 0; i < k; i++) {
            KMeansCluster cluster = new KMeansCluster();
            Student randomStudent = studentList.get(randomizer.nextInt(studentList.size()));
            cluster.ClusterMembers.add(randomStudent);
            cluster.calculateCentroid();
            clusterList.add(cluster);
        }
    }


    public void KMeanstest(List<Student> studentList) {
        boolean hasChanges = true;
        while (hasChanges) {
            for (KMeansCluster cluster : clusterList) {
                cluster.ClusterMembers.clear();
            }
            for (Student student : studentList) {
                KMeansCluster closestClusterTemp = null;
                double shortestDistanceToTemp = 1e6;
                for (KMeansCluster cluster : clusterList) {
                    double currentDistance = cluster.calculateDistance(student);
                    if (currentDistance < shortestDistanceToTemp) {
                        closestClusterTemp = cluster;
                        shortestDistanceToTemp = currentDistance;
                    }
                }
                closestClusterTemp.ClusterMembers.add(student);
                //put student into closest cluster
            }

            hasChanges = false;
            //recalculate centroid for all clusters
            for (KMeansCluster cluster : clusterList) {
                Double X = cluster.centroidX;
                Double Y = cluster.centroidY;
                cluster.calculateCentroid();
                if (Math.abs(X - cluster.centroidX) > 0.01) {
                    hasChanges = true;
                }
                if (Math.abs(Y - cluster.centroidY) > 0.01) {
                    hasChanges = true;
                }
            }
        }
        for (KMeansCluster cluster : clusterList) {
            System.out.println(cluster.toString());
        }
    }
}
