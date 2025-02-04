import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Test;

public class PointTest {

    private Point[] points;
    private static final int SIZE = 5; // max x-coordinate

    @BeforeEach
    public void setUp() {
        int sideLength = 2 * SIZE + 1;
        points = new Point[sideLength * sideLength];
        for (int y = SIZE; y >= -SIZE; y--) {
            for (int x = -SIZE; x <= SIZE; x++) {
                // goes from (-SIZE, -SIZE) to (SIZE, SIZE)
                points[rc(x, y)] = new Point(x, y);
            }
        }
    }

    @Test
    public void testSlopeToSimple() {
        // (1, 1) to (5, 2)
        assertEquals(0.25, points[rc(1, 1)].slopeTo(points[rc(5, 2)]), 0.00001);
        // (5, 2) to (1, 1)
        assertEquals(0.25, points[rc(5, 2)].slopeTo(points[rc(1, 1)]), 0.00001);
        // (1, 1) to (4, 4)
        assertEquals(1.0, points[rc(1, 1)].slopeTo(points[rc(4, 4)]), 0.00001);
        // (4, 4) to (1, 1)
        assertEquals(1.0, points[rc(4, 4)].slopeTo(points[rc(1, 1)]), 0.00001);
        // (1, 1) to (2, 3)
        assertEquals(2.0, points[rc(1, 1)].slopeTo(points[rc(2, 3)]), 0.00001);
        // (2, 3) to (1, 1)
        assertEquals(2.0, points[rc(2, 3)].slopeTo(points[rc(1, 1)]), 0.00001);
    }

    @Test
    public void testSlopeToHorizontal() {
        // (1, 1) to (3, 1)
        assertEquals(+0.0, points[rc(1, 1)].slopeTo(points[rc(3, 1)]), 0.00001);
        // (3, 1) to (1, 1)
        assertEquals(+0.0, points[rc(3, 1)].slopeTo(points[rc(1, 1)]), 0.00001);
        // (3, -2) to (4, -2)
        assertEquals(+0.0, points[rc(3, -2)].slopeTo(points[rc(4, -2)]), 0.00001);
        // (4, -2) to (3, -2)
        assertEquals(+0.0, points[rc(4, -2)].slopeTo(points[rc(3, -2)]), 0.00001);
        // (-4, -5) to (-1, -5)
        assertEquals(+0.0, points[rc(-4, -5)].slopeTo(points[rc(-1, -5)]), 0.00001);
        // (-1, -5) to (-4, -5)
        assertEquals(+0.0, points[rc(-1, -5)].slopeTo(points[rc(-4, -5)]), 0.00001);
        // (-1, 5) to (0, 5)
        assertEquals(+0.0, points[rc(-1, 5)].slopeTo(points[rc(0, 5)]), 0.00001);
        // (0, 5) to (-1, 5)
        assertEquals(+0.0, points[rc(0, 5)].slopeTo(points[rc(-1, 5)]), 0.00001);
    }

    @Test
    public void testSlopeToVertical() {
        // (1, 1) to (1, 3)
        assertEquals(Double.POSITIVE_INFINITY, points[rc(1, 1)].slopeTo(points[rc(1, 3)]), 0.00001);
        // (1, 3) to (1, 1)
        assertEquals(Double.POSITIVE_INFINITY, points[rc(1, 3)].slopeTo(points[rc(1, 1)]), 0.00001);
        // (-2, 3) to (-2, 4)
        assertEquals(Double.POSITIVE_INFINITY, points[rc(-2, 3)].slopeTo(points[rc(-2, 4)]), 0.00001);
        // (-2, 4) to (-2, 3)
        assertEquals(Double.POSITIVE_INFINITY, points[rc(-2, 4)].slopeTo(points[rc(-2, 3)]), 0.00001);
        // (-5, -4) to (-5, -1)
        assertEquals(Double.POSITIVE_INFINITY, points[rc(-5, -4)].slopeTo(points[rc(-5, -1)]), 0.00001);
        // (-5, -1) to (-5, -4)
        assertEquals(Double.POSITIVE_INFINITY, points[rc(-5, -1)].slopeTo(points[rc(-5, -4)]), 0.00001);
        // (5, -1) to (5, 0)
        assertEquals(Double.POSITIVE_INFINITY, points[rc(5, -1)].slopeTo(points[rc(5, 0)]), 0.00001);
        // (5, 0) to (5, -1)
        assertEquals(Double.POSITIVE_INFINITY, points[rc(5, 0)].slopeTo(points[rc(5, -1)]), 0.00001);
    }

    @Test
    public void testSlopeToSelf() {
        // (1, 1) to (1, 1)
        assertEquals(Double.NEGATIVE_INFINITY, points[rc(1, 1)].slopeTo(points[rc(1, 1)]), 0.00001);
        // (-4, 3) to (-4, 3)
        assertEquals(Double.NEGATIVE_INFINITY, points[rc(-4, 3)].slopeTo(points[rc(-4, 3)]), 0.00001);
        // (2, -5) to (2, -5)
        assertEquals(Double.NEGATIVE_INFINITY, points[rc(2, -5)].slopeTo(points[rc(2, -5)]), 0.00001);
        // (-5, -1) to (-5, -1)
        assertEquals(Double.NEGATIVE_INFINITY, points[rc(-5, -1)].slopeTo(points[rc(-5, -1)]), 0.00001);
    }

    @Test
    public void testCompareToSize5() {
        if (SIZE < 5) {
            throw new IllegalStateException("Need SIZE to be >= 5.");
        }
        int[] solutionsFiveOrigin = {
                 1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1, // y = 5
                 1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,
                 1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,
                 1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,
                 1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1, // y = 1
                -1, -1, -1, -1, -1,  0,  1,  1,  1,  1,  1, // y = 0
                -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, // y = -1
                -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, // y = -5
        };
        Point origin = points[rc(0, 0)];
        Point minCorner = points[rc(-5, -5)];
        Point maxCorner = points[rc(5, 5)];
        for (int y = 5; y >= -5; y--) {
            for (int x = -5; x <= 5; x++) {
                Point testPoint = points[rc(x, y)]; // the point to compareTo
                int solutionOrigin = solutionsFiveOrigin[rc(x, y)];
                int testOrigin = testPoint.compareTo(origin);
                int testMin = testPoint.compareTo(minCorner);
                int testMax = testPoint.compareTo(maxCorner);
                if (x == 0 && y == 0) {
                    assertEquals(0, testOrigin, "The origin is the origin.");
                    assertTrue(testMin > 0, "The origin should be more than the min");
                    assertTrue(testMax < 0, "The origin should be less than the max");
                } else if (x == -5 && y == -5) {
                    assertTrue(testOrigin < 0, "The min should be less than the origin.");
                    assertEquals(0, testMin, "The min is the min");
                    assertTrue(testMax < 0, "The min should be less than the max");
                } else if (x == 5 && y == 5) {
                    assertTrue(testOrigin > 0, "The max should be more than the origin.");
                    assertTrue(testMin > 0, "The max should be more than the min");
                    assertEquals(0, testMax, "The max is the max");
                } else {
                    assertTrue(solutionOrigin * testOrigin > 0);
                    assertTrue(testMin > 0, "This point should be bigger than the min");
                    assertTrue(testMax < 0, "This point should be smaller than the max");
                }
            }
        }
    }

    @Test
    public void testSlopeOrderSize5() {
        double[] solutionsSlopeOrigin = {
                -1.0, -1.25, -1.666667, -2.5, -5.0, Double.POSITIVE_INFINITY, +5.0, +2.5, +1.666667, +1.25, +1.0, // y = 5
                -0.8, -1.00, -1.333333, -2.0, -4.0, Double.POSITIVE_INFINITY, +4.0, +2.0, +1.333333, +1.00, +0.8,
                -0.6, -0.75, -1.000000, -1.5, -3.0, Double.POSITIVE_INFINITY, +3.0, +1.5, +1.000000, +0.75, +0.6,
                -0.4, -0.50, -0.666667, -1.0, -2.0, Double.POSITIVE_INFINITY, +2.0, +1.0, +0.666667, +0.50, +0.4,
                -0.2, -0.25, -0.333333, -0.5, -1.0, Double.POSITIVE_INFINITY, +1.0, +0.5, +0.333333, +0.25, +0.2, // y = 1
                +0.0, +0.00, +0.000000, +0.0, +0.0, Double.NEGATIVE_INFINITY, +0.0, +0.0, +0.000000, +0.00, +0.0, // y = 0
                +0.2, +0.25, +0.333333, +0.5, +1.0, Double.POSITIVE_INFINITY, -1.0, -0.5, -0.333333, -0.25, -0.2, // y = 1
                +0.4, +0.50, +0.666667, +1.0, +2.0, Double.POSITIVE_INFINITY, -2.0, -1.0, -0.666667, -0.50, -0.4,
                +0.6, +0.75, +1.000000, +1.5, +3.0, Double.POSITIVE_INFINITY, -3.0, -1.5, -1.000000, -0.75, -0.6,
                +0.8, +1.00, +1.333333, +2.0, +4.0, Double.POSITIVE_INFINITY, -4.0, -2.0, -1.333333, -1.00, -0.8,
                +1.0, +1.25, +1.666667, +2.5, +5.0, Double.POSITIVE_INFINITY, -5.0, -2.5, -1.666667, -1.25, -1.0, // y = -5
        };
        Point origin = points[rc(0, 0)]; // slopes shown are to the origin
        for (int y1 = 5; y1 >= -5; y1--) {
            for (int x1 = -5; x1 <= 5; x1++) {
                for (int y2 = 5; y2 >= -5; y2--) {
                    for (int x2 = -5; x2 <= 5; x2++) {
                        // four nested loops!
                        Point p1 = points[rc(x1, y1)];
                        Point p2 = points[rc(x2, y2)];
                        double slope1 = solutionsSlopeOrigin[rc(x1, y1)];
                        double slope2 = solutionsSlopeOrigin[rc(x2, y2)];
                        if (slope1 == slope2) {
                            // is this dangerous for doubles?
                            assertEquals(0, origin.slopeOrder().compare(p1, p2));
                        } else if (slope1 < slope2) {
                            assertTrue(origin.slopeOrder().compare(p1, p2) < 0);
                        } else { // slope1 > slope2
                            assertTrue(origin.slopeOrder().compare(p1, p2) > 0);
                        }
                    }
                }
            }
        }
    }

    /**
     * Converts an x and y coordinate for our grid of points into an index
     * for the one-dimensional array of Point objects, starting with the
     * coordinate (-SIZE, SIZE) in the upper left, which maps to index 0,
     * to coordinate (SIZE, -SIZE) in the lower right, which maps to index
     *
     * @param x the x-coordinate of the point, which should satisfy |x| <= SIZE
     * @param y the y-coordinate of the point, which should satisfy |y| <= SIZE
     * @return the row-major 1D index for that point
     */
    private int rc(int x, int y) {
        int sideLength = 2 * SIZE + 1;
        // positive x is right, positive y is up
        return (SIZE - y) * sideLength + (x + SIZE);
    }
}
