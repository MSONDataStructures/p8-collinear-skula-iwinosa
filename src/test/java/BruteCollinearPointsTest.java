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

import java.util.ArrayList;

import edu.princeton.cs.algs4.In;

public class BruteCollinearPointsTest {

  BruteCollinearPoints bcpTest;

  private BruteCollinearPoints generateBCP(String filename) {
    In in = new In("collinear-test-files/" + filename);
    int n = in.readInt();
    Point[] points = new Point[n];
    for (int i = 0; i < n; i++) {
      int x = in.readInt();
      int y = in.readInt();
      points[i] = new Point(x, y);
    }
    return new BruteCollinearPoints(points);
  }

  @Test
  public void testPointsFromAFile() {
    // points are shown here:
    // https://www.desmos.com/calculator/14nsmtpw7u

    String filename;

    filename = "input8.txt";
    bcpTest = generateBCP(filename);
    assertEquals(bcpTest.numberOfSegments(), 2);
    ArrayList<String> segmentsKey0 = new ArrayList<>();
    segmentsKey0.add("(10000, 0) -> (0, 10000)");
    segmentsKey0.add("(3000, 4000) -> (20000, 21000)");
    assertTrue(lineSegmentArrayMatch(bcpTest.segments(), segmentsKey0),
            filename + " segment list incorrect.");

    filename = "equidistant.txt";
    bcpTest = generateBCP(filename);
    assertEquals(bcpTest.numberOfSegments(), 4);
    ArrayList<String> segmentsKey1 = new ArrayList<>();
    segmentsKey1.add("(10000, 0) -> (0, 10000)");
    segmentsKey1.add("(10000, 0) -> (30000, 0)");
    segmentsKey1.add("(30000, 0) -> (0, 30000)");
    segmentsKey1.add("(13000, 0) -> (5000, 12000)");
    assertTrue(lineSegmentArrayMatch(bcpTest.segments(), segmentsKey1),
            filename + " segment list incorrect.");

    filename = "input40.txt";
    bcpTest = generateBCP(filename);
    assertEquals(bcpTest.numberOfSegments(), 4);
    ArrayList<String> segmentsKey2 = new ArrayList<>();
    segmentsKey2.add("(1000, 17000) -> (1000, 31000)");
    segmentsKey2.add("(1000, 17000) -> (29000, 17000)");
    segmentsKey2.add("(2000, 24000) -> (25000, 24000)");
    segmentsKey2.add("(2000, 29000) -> (28000, 29000)");
    assertTrue(lineSegmentArrayMatch(bcpTest.segments(), segmentsKey2),
            filename + " segment list incorrect.");


    filename = "input48.txt";
    bcpTest = generateBCP(filename);
    assertEquals(bcpTest.numberOfSegments(), 6);
    ArrayList<String> segmentsKey3 = new ArrayList<>();
    segmentsKey3.add("(1000, 23000) -> (24000, 23000)");
    segmentsKey3.add("(18000, 13000) -> (18000, 27000)");
    segmentsKey3.add("(1000, 2000) -> (1000, 26000)");
    segmentsKey3.add("(9000, 1000) -> (16000, 22000)");
    segmentsKey3.add("(6000, 2000) -> (19000, 28000)");
    segmentsKey3.add("(1000, 26000) -> (18000, 26000)");
    assertTrue(lineSegmentArrayMatch(bcpTest.segments(), segmentsKey3),
            filename + " segment list incorrect.");
  }

  @Test
  public void testHorizontalSegments() {
    String filename;

    filename = "horizontal5.txt";
    bcpTest = generateBCP(filename);
    assertEquals(bcpTest.numberOfSegments(), 5);
    ArrayList<String> segmentsKey0 = new ArrayList<>();
    segmentsKey0.add("(2682, 14118) -> (7821, 14118)");
    segmentsKey0.add("(4750, 4652) -> (16307, 4652)");
    segmentsKey0.add("(8934, 7996) -> (20547, 7996)");
    segmentsKey0.add("(1888, 7657) -> (13832, 7657)");
    segmentsKey0.add("(10375, 12711) -> (20385, 12711)");
    assertTrue(lineSegmentArrayMatch(bcpTest.segments(), segmentsKey0),
            filename + " segment list incorrect.");

    bcpTest = generateBCP("horizontal25.txt");
    assertEquals(bcpTest.numberOfSegments(), 25);
  }

  @Test
  public void testVerticalSegments() {
    String filename;

    filename = "vertical5.txt";
    bcpTest = generateBCP(filename);
    assertEquals(bcpTest.numberOfSegments(), 5);
    ArrayList<String> segmentsKey0 = new ArrayList<>();
    segmentsKey0.add("(14407, 10367) -> (14407, 19953)");
    segmentsKey0.add("(15976, 3370) -> (15976, 9945)");
    segmentsKey0.add("(2088, 6070) -> (2088, 16387)");
    segmentsKey0.add("(5757, 3426) -> (5757, 20856)");
    segmentsKey0.add("(8421, 1829) -> (8421, 18715)");
    assertTrue(lineSegmentArrayMatch(bcpTest.segments(), segmentsKey0),
            filename + " segment list incorrect.");

    bcpTest = generateBCP("vertical25.txt");
    assertEquals(bcpTest.numberOfSegments(), 25);
  }

  @Test
  public void testNoCollinearPoints() {
    bcpTest = generateBCP("random23.txt");
    assertEquals(bcpTest.numberOfSegments(), 0);
    assertEquals(bcpTest.segments().length, 0);

    bcpTest = generateBCP("random38.txt");
    assertEquals(bcpTest.numberOfSegments(), 0);
    assertEquals(bcpTest.segments().length, 0);
  }

  @Test
  public void testBelowFourPoints() {
    bcpTest = generateBCP("input1.txt");
    assertEquals(bcpTest.numberOfSegments(), 0);
    assertEquals(bcpTest.segments().length, 0);

    bcpTest = generateBCP("input2.txt");
    assertEquals(bcpTest.numberOfSegments(), 0);
    assertEquals(bcpTest.segments().length, 0);

    bcpTest = generateBCP("input3.txt");
    assertEquals(bcpTest.numberOfSegments(), 0);
    assertEquals(bcpTest.segments().length, 0);
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
