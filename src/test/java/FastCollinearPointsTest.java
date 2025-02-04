import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.fail;

//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Test;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;

public class FastCollinearPointsTest {

    FastCollinearPoints fcpTest;

    private FastCollinearPoints generateFCP(String filename) {
        In in = new In("collinear-test-files/" + filename);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        return new FastCollinearPoints(points);
    }

    @Test
    public void fastPointsFromAFile() {
        String filename;

        filename = "input8.txt";
        fcpTest = generateFCP(filename);
        assertEquals(fcpTest.numberOfSegments(), 2);
        ArrayList<String> segmentsKey0 = new ArrayList<>();
        segmentsKey0.add("(10000, 0) -> (0, 10000)");
        segmentsKey0.add("(3000, 4000) -> (20000, 21000)");
        assertTrue(lineSegmentArrayMatch(fcpTest.segments(), segmentsKey0),
                filename + " fast list incorrect.");

        filename = "equidistant.txt";
        fcpTest = generateFCP(filename);
        assertEquals(fcpTest.numberOfSegments(), 4);
        ArrayList<String> segmentsKey1 = new ArrayList<>();
        segmentsKey1.add("(10000, 0) -> (0, 10000)");
        segmentsKey1.add("(10000, 0) -> (30000, 0)");
        segmentsKey1.add("(30000, 0) -> (0, 30000)");
        segmentsKey1.add("(13000, 0) -> (5000, 12000)");
        assertTrue(lineSegmentArrayMatch(fcpTest.segments(), segmentsKey1),
                filename + " fast list incorrect.");

        filename = "input40.txt";
        fcpTest = generateFCP(filename);
        assertEquals(fcpTest.numberOfSegments(), 4);
        ArrayList<String> segmentsKey2 = new ArrayList<>();
        segmentsKey2.add("(1000, 17000) -> (1000, 31000)");
        segmentsKey2.add("(1000, 17000) -> (29000, 17000)");
        segmentsKey2.add("(2000, 24000) -> (25000, 24000)");
        segmentsKey2.add("(2000, 29000) -> (28000, 29000)");
        assertTrue(lineSegmentArrayMatch(fcpTest.segments(), segmentsKey2),
                filename + " fast list incorrect.");


        filename = "input48.txt";
        fcpTest = generateFCP(filename);
        assertEquals(fcpTest.numberOfSegments(), 6);
        ArrayList<String> segmentsKey3 = new ArrayList<>();
        segmentsKey3.add("(1000, 23000) -> (24000, 23000)");
        segmentsKey3.add("(18000, 13000) -> (18000, 27000)");
        segmentsKey3.add("(1000, 2000) -> (1000, 26000)");
        segmentsKey3.add("(9000, 1000) -> (16000, 22000)");
        segmentsKey3.add("(6000, 2000) -> (19000, 28000)");
        segmentsKey3.add("(1000, 26000) -> (18000, 26000)");
        assertTrue(lineSegmentArrayMatch(fcpTest.segments(), segmentsKey3),
                filename + " fast list incorrect.");
    }

    @Test
    public void fastHorizontalSegments() {
        String filename;

        filename = "horizontal5.txt";
        fcpTest = generateFCP(filename);
        assertEquals(fcpTest.numberOfSegments(), 5);
        ArrayList<String> segmentsKey0 = new ArrayList<>();
        segmentsKey0.add("(2682, 14118) -> (7821, 14118)");
        segmentsKey0.add("(4750, 4652) -> (16307, 4652)");
        segmentsKey0.add("(8934, 7996) -> (20547, 7996)");
        segmentsKey0.add("(1888, 7657) -> (13832, 7657)");
        segmentsKey0.add("(10375, 12711) -> (20385, 12711)");
        assertTrue(lineSegmentArrayMatch(fcpTest.segments(), segmentsKey0),
                filename + " fast list incorrect.");

        fcpTest = generateFCP("horizontal25.txt");
        assertEquals(fcpTest.numberOfSegments(), 25);
    }

    @Test
    public void fastVerticalSegments() {
        String filename;

        filename = "vertical5.txt";
        fcpTest = generateFCP(filename);
        assertEquals(fcpTest.numberOfSegments(), 5);
        ArrayList<String> segmentsKey0 = new ArrayList<>();
        segmentsKey0.add("(14407, 10367) -> (14407, 19953)");
        segmentsKey0.add("(15976, 3370) -> (15976, 9945)");
        segmentsKey0.add("(2088, 6070) -> (2088, 16387)");
        segmentsKey0.add("(5757, 3426) -> (5757, 20856)");
        segmentsKey0.add("(8421, 1829) -> (8421, 18715)");
        assertTrue(lineSegmentArrayMatch(fcpTest.segments(), segmentsKey0),
                filename + " fast list incorrect.");

        fcpTest = generateFCP("vertical25.txt");
        assertEquals(fcpTest.numberOfSegments(), 25);
    }

    @Test
    public void fastNoCollinearPoints() {
        fcpTest = generateFCP("random23.txt");
        assertEquals(fcpTest.numberOfSegments(), 0);
        assertEquals(fcpTest.segments().length, 0);

        fcpTest = generateFCP("random38.txt");
        assertEquals(fcpTest.numberOfSegments(), 0);
        assertEquals(fcpTest.segments().length, 0);
    }

    @Test
    public void fastBelowFourPoints() {
        // parallel arrays
        String[] testFiles = {"input1.txt", "input2.txt", "input3.txt"};
        int[] testSegments = {0, 0, 0};

        for (int i = 0; i < testFiles.length; i++) {
            fcpTest = generateFCP(testFiles[i]);
            assertEquals(fcpTest.numberOfSegments(), testSegments[i]);
            assertEquals(fcpTest.segments().length, 0);
        }
    }

    @Test
    public void newTestPointsFromAFile() {
        // points are shown here
        // https://www.desmos.com/calculator/cgr9yboj2o

        String filename;

        filename = "input9.txt";
        fcpTest = generateFCP(filename);
        assertEquals(fcpTest.numberOfSegments(), 1);
        ArrayList<String> segmentsKey0 = new ArrayList<>();
        segmentsKey0.add("(1000, 1000) -> (9000, 9000)");
        assertTrue(lineSegmentArrayMatch(fcpTest.segments(), segmentsKey0),
                filename + " fast list incorrect.");

        filename = "input10.txt";
        fcpTest = generateFCP(filename);
        assertEquals(fcpTest.numberOfSegments(), 2);
        ArrayList<String> segmentsKey1 = new ArrayList<>();
        segmentsKey1.add("(1000, 18000) -> (4000, 30000)");
        segmentsKey1.add("(28000, 13500) -> (3000, 26000)");
        assertTrue(lineSegmentArrayMatch(fcpTest.segments(), segmentsKey1),
                filename + " fast list incorrect.");

        filename = "input20.txt";
        fcpTest = generateFCP(filename);
        assertEquals(fcpTest.numberOfSegments(), 5);
        ArrayList<String> segmentsKey2 = new ArrayList<>();
        segmentsKey2.add("(4096, 20992) -> (8128, 20992)");
        segmentsKey2.add("(4096, 20992) -> (4096, 25088)");
        segmentsKey2.add("(4096, 25088) -> (8192, 25088)");
        segmentsKey2.add("(8192, 25088) -> (8192, 29184)");
        segmentsKey2.add("(4160, 29184) -> (8192, 29184)");
        assertTrue(lineSegmentArrayMatch(fcpTest.segments(), segmentsKey2),
                filename + " fast list incorrect.");
    }

    private boolean lineSegmentArrayMatch(LineSegment[] arr1, ArrayList<String> arr2) {
        if (arr1 == null || arr2 == null || arr1.length != arr2.size()) {
            throw new IllegalArgumentException();
        }
        for (LineSegment lineSegment : arr1) {
            int breakPoint = lineSegment.toString().indexOf("->");
            String segmentReversed =
                    lineSegment.toString().substring(breakPoint + 3) + " -> " +
                            lineSegment.toString().substring(0, breakPoint - 1);
            if (arr2.remove(lineSegment.toString()) == arr2.remove(segmentReversed)) {
                // either desired segment does not appear in arr2 in either order,
                // or segment was added twice in both directions, also not good
                return false;
            }
        }
        return arr2.isEmpty(); // we should have exhausted the list
    }
}
