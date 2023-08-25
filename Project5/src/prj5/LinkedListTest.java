// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Cristian Valencia
// -- Connor Marks
// -- Josh Cole (joshcole137)

package prj5;

// import java.util.Comparator;
import student.TestCase;

/**
 * Tests the methods of LinkedList
 * 
 * @author Cristian Valencia
 * @version 4.22.21
 */
public class LinkedListTest extends TestCase {
    private LinkedList<Statistics> test1;
    private LinkedList<Statistics> equal;
    private Statistics white;
    private Statistics black;
    private Statistics latinx;
    private Statistics other;
    // private Comparator comp;

    /**
     * The setUp for test methods
     */
    public void setUp() {
        white = new Statistics("white", 10, 100);
        black = new Statistics("black", 15, 80);
        latinx = new Statistics("latinx", 20, 101);
        other = new Statistics("other", 100, 200);
        test1 = new LinkedList<Statistics>();
        equal = new LinkedList<Statistics>();
        // comp = new Comparator();

    }


    /**
     * Tests the isEmpty method
     */
    public void testIsEmpty() {
        test1.add(white);
        assertFalse(test1.isEmpty());
        test1.clear();
        assertTrue(test1.isEmpty());
    }


    /**
     * Tests add and size
     */
    public void testAddAndRemove() {
        test1.add(white);
        assertEquals(1, test1.size());
        test1.add(black);
        assertEquals(2, test1.size());
        assertTrue(test1.remove(white));
        assertFalse(test1.remove(white));
        test1.add(1, white);
        test1.add(latinx);
        assertTrue(test1.remove(1));
        assertFalse(test1.contains(white));
    }


    /**
     * Tests remove exceptions
     */
    public void testRemoveExceptions() {
        Exception exception = null;
        try {
            test1.remove(-1);
            fail("remove() is not throwing an exception when it should");
        }
        catch (Exception e) {
            exception = e;
        }
        assertTrue(exception instanceof IndexOutOfBoundsException);
    }


    /**
     * Tests get
     */
    public void testGet() {
        test1.add(white);
        test1.add(black);
        test1.add(latinx);

        assertEquals(latinx, test1.get(2));
        Exception exception = null;
        try {
            test1.remove(-1);
            fail("remove() is not throwing an exception when it should");
        }
        catch (Exception e) {
            exception = e;
        }
        assertTrue(exception instanceof IndexOutOfBoundsException);
        assertFalse(test1.contains(other));

    }


    /**
     * Tests lastIndexOf
     */
    public void testLastIndexOf() {
        test1.add(white);
        test1.add(black);
        test1.add(latinx);

        assertEquals(1, test1.lastIndexOf(black));
        assertEquals(0, test1.lastIndexOf(white));
        assertEquals(-1, test1.lastIndexOf(other));
    }


    /**
     * Tests the equals method for all cases
     */
    public void testEquals() {
        test1.add(white);
        test1.add(black);
        test1.add(latinx);
        equal.add(white);
        equal.add(black);
        equal.add(latinx);

        assertTrue(test1.equals(equal));
        equal.remove(latinx);
        assertFalse(test1.equals(equal));
        assertFalse(test1.equals(latinx));
        // assertFalse(equal.equals(null));

    }


    /**
     * Tests toString
     */
    public void testToString() {
        test1.add(white);
        assertEquals("{white: 100 cases, 10% CFR, }", test1.toString());
        assertEquals("{}", equal.toString());
    }


    /**
     * Tests toArray
     */
    public void testToArray() {
        test1.add(white);
        test1.add(black);
        assertEquals(white, test1.toArray()[0]);
        assertEquals(black, test1.toArray()[1]);

    }



}
