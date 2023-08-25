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
public class StatisticsTest extends TestCase {
    private Statistics white;
    private Statistics black;
    private Statistics asian;
    private Statistics black1;
    private Statistics black2;
    private Statistics black3;
    private Statistics latinx;
    private Statistics who;
    private Statistics dis;
    private Statistics noMansLand;
    private Object nothing;

    /**
     * Set up method runs before every test
     */

    public void setUp() {
        white = new Statistics("White", 1000, 10000);
        black = new Statistics("Black", 100, 1000);
        black1 = new Statistics("Black", 100, 1000);
        black2 = new Statistics("Black", 1000, 1000);
        black3 = new Statistics("Black", 100, 10);
        asian = new Statistics("Asian", 368, 15674);
        latinx = new Statistics("Latinx", 100, 1000);
        who = new Statistics("Who", -1, 1000);
        dis = new Statistics("Dis", 100334, -1);
        noMansLand = null;
        nothing = "no";
    }


    /**
     * test getters
     * getRace()
     * getDeaths()
     * getCases()
     * 
     */

    public void testGetRace() {
        assertEquals("White", white.getRace());
        assertEquals("Black", black1.getRace());
        assertEquals(1000, white.getDeaths());
        assertEquals(100, black.getDeaths(), 0.001);
        assertEquals(368, asian.getDeaths(), 0.001);
        assertEquals(10000, white.getCases(), 0.001);
        assertEquals(15674, asian.getCases(), 0.001);
    }


    /**
     * test CFR related methods
     * calculate CFR
     * get CFR
     */

    public void testCFR() {
        assertEquals(10.0, white.calculateCFR(), 0.001);
        assertEquals(-1, who.calculateCFR(), 0.1);
        assertEquals(-1, dis.calculateCFR(), 0.1);
        assertEquals(2.3, asian.getCFR(), 0.001);
    }


    /**
     * test toString()
     */

    public void testToString() {
        assertEquals("White: 10000 cases, 10% CFR", white.toString());
        assertEquals("Asian: 15674 cases, 2.3% CFR", asian.toString());
    }


    /**
     * test equals()
     */

    public void testEquals() {
        assertTrue(black.equals(black1));
        assertTrue(black.equals(black));
        assertFalse(black.equals(white));
        assertFalse(asian.equals(noMansLand));
        assertFalse(white.equals(nothing));
        assertFalse(black.equals(black2));
        assertFalse(black.equals(black3));
        assertFalse(black.equals(latinx));
    }

}
