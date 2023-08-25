// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Cristian Valencia
// -- Connor Marks
// -- Josh Cole (joshcole137)

package prj5;

import student.TestCase;

/**
 * @author <Cristian Valencia> <cristianv>
 * @author <Connor Marks> <connorm20>
 * @author <Josh Cole> <joshcole137>
 * 
 * @version <2021.04.23>
 *
 */
public class SorterTest extends TestCase {
    private Statistics white;
    private Statistics black;
    private Statistics asian;
    private Statistics latinx;

    private LinkedList<Statistics> list1;
    private LinkedList<Statistics> list2;
    private LinkedList<Statistics> list3;

    private Sorter dc;
    private Sorter va;
    private Sorter tx;
    private Sorter ny;
    private Sorter va1;
    private Sorter nj;
    private Object nothing;

    /**
     * Set up method runs before every test
     */

    public void setUp() {
        white = new Statistics("White", 546, 1947);
        black = new Statistics("Black", 1000, 10000);
        asian = new Statistics("Asian", 2045, 90456);
        latinx = new Statistics("Latinx", 23198, 58923475);

        list1 = new LinkedList<Statistics>();
        list1.add(white);
        list1.add(black);
        list1.add(asian);

        list2 = new LinkedList<Statistics>();
        list2.add(white);
        list2.add(black);
        list2.add(asian);

        list3 = new LinkedList<Statistics>();
        list3.add(latinx);
        list3.add(black);

        dc = new Sorter("DC");
        va = new Sorter("Virginia");
        tx = new Sorter("Texas");
        ny = new Sorter("New York");
        va1 = new Sorter("Virginia");
        nj = null;
        nothing = "nj";
    }


    /**
     * test getName()
     */

    public void testGetName() {
        assertEquals("DC", dc.getName());
        assertEquals("Virginia", va.getName());
        assertEquals("Texas", tx.getName());

    }


    /**
     * test getStats()
     */

    public void testGetStats() {
        dc.setStats(list1);
        assertEquals(white, dc.getStats()[0]);
        assertEquals(black, dc.getStats()[1]);

        va.setStats(list2);
        assertEquals(asian, dc.getStats()[2]);

        tx.setStats(list3);
        assertEquals(latinx, tx.getStats()[0]);
    }


    /**
     * test sortedToString()
     */

    public void testSortedToString() {
        dc.setStats(list1);
        assertEquals(dc.sortedToString(), "DC"
            + "\nAsian: 90456 cases, 2.3% CFR" + "\nBlack: 10000 cases, 10% CFR"
            + "\nWhite: 1947 cases, 28% CFR" + "\n====="
            + "\nWhite: 1947 cases, 28% CFR" + "\nBlack: 10000 cases, 10% CFR"
            + "\nAsian: 90456 cases, 2.3% CFR" + "\n=====");
    }


    /**
     * test equals()
     */

    public void testEquals() {
        dc.setStats(list1);

        assertFalse(dc.equals(nj));
        assertFalse(dc.equals(nothing));
        assertTrue(dc.equals(dc));
        va.setStats(list1);
        va1.setStats(list2);
        assertTrue(va.equals(va1));

        tx.setStats(list3);
        assertFalse(va.equals(tx));

        ny.setStats(list2);
        assertFalse(va.equals(ny));
        assertFalse(ny.equals(tx));

    }

}
