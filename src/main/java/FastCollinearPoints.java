/*************************************************************************
 *   Given a point p, the following method determines
 *   whether p participates in a set of 4 or more collinear points.
 *   Think of p as the origin.
 *   - For each other point q, determine the slope it makes with p.
 *   - Sort the points according to the slopes they makes with p.
 *   - Check if any 3 (or more) adjacent points in 
 *      the sorted order have equal slopes with respect to p. 
 *      If so, these points, together with p, are collinear.
 *************************************************************************/
import java.util.ArrayList;
import java.util.Arrays;

// Skula's Part
public class FastCollinearPoints {

    private final ArrayList<LineSegment> segmentsList = new ArrayList<>();

    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException("Points array is null");
        int n = points.length;
        Point[] pointsCopy = new Point[n];
        for (int i = 0; i < n; i++) {
            if (points[i] == null) throw new IllegalArgumentException("Point is null");
            pointsCopy[i] = points[i];
        }
        Arrays.sort(pointsCopy);
        for (int i = 0; i < n - 1; i++) {
            if (pointsCopy[i].compareTo(pointsCopy[i + 1]) == 0) {
                throw new IllegalArgumentException("Duplicate points");
            }
        }

        for (int i = 0; i < n; i++) {
            Point p = pointsCopy[i];
            Point[] otherPoints = new Point[n - 1];
            int k = 0;
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    otherPoints[k++] = pointsCopy[j];
                }
            }

            Arrays.sort(otherPoints, p.slopeOrder());

            int count = 1;
            for (int j = 1; j < otherPoints.length; j++) {
                if (p.slopeTo(otherPoints[j]) == p.slopeTo(otherPoints[j - 1])) {
                    count++;
                } else {
                    if (count >= 3 && p.compareTo(otherPoints[j - count]) < 0) {
                        segmentsList.add(new LineSegment(p, otherPoints[j - 1]));
                    }
                    count = 1;
                }
            }
            if (count >= 3 && p.compareTo(otherPoints[otherPoints.length - count]) < 0) {
                segmentsList.add(new LineSegment(p, otherPoints[otherPoints.length - 1]));
            }
        }
    }

    public int numberOfSegments() {
        return segmentsList.size();
    }

    public LineSegment[] segments() {
        return segmentsList.toArray(new LineSegment[0]);
    }
}
