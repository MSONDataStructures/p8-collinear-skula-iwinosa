/*************************************************************************
 *   A program that examines 4 points at a time
 *   and checks whether they all lie on the same line segment, 
 *   returning all such line segments. 
 *   To check whether the 4 points p, q, r, and s are collinear, 
 *   check whether the three slopes between p and q, 
 *   between p and r, and between p and s are all equal.
 *************************************************************************/

// Iwinosa's Part
import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private ArrayList<LineSegment> segments = new ArrayList<>();
    public BruteCollinearPoints(Point[] points) {
        // finds all line segments containing 4 points
        // TODO: YOUR CODE HERE
        if (points == null) {
            throw new IllegalArgumentException("Points array cannot be null");
        }

        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException("Point cannot be null");
            }
        }
        Point[] sortedPoints = points.clone();
        Arrays.sort(sortedPoints);

        for (int i = 0; i < sortedPoints.length - 3; i++) {
            for (int j = i + 1; j < sortedPoints.length - 2; j++) {
                for (int k = j + 1; k < sortedPoints.length - 1; k++) {
                    for (int l = k + 1; l < sortedPoints.length; l++) {
                        Point p = sortedPoints[i];
                        Point q = sortedPoints[j];
                        Point r = sortedPoints[k];
                        Point s = sortedPoints[l];

                        if (p.slopeTo(q) == p.slopeTo(r) && p.slopeTo(r) == p.slopeTo(s)) {
                            segments.add(new LineSegment(p, s));
                        }
                    }
                }
            }
        }
    }

//    private double slope(Point p, Point q) {
//        if (p.x == q.x) {
//            return Double.POSITIVE_INFINITY; // Handle vertical lines
//        }
//        return (double) (q.y - p.y) / (q.x - p.x);
//    }



    public int numberOfSegments() {
        // the number of line segments
        // TODO: YOUR CODE HERE
        return segments.size();
    }

    public LineSegment[] segments() {
        // the line segments
        // TODO: YOUR CODE HERE
        return segments.toArray(new LineSegment[0]);
    }
}
