// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Cristian Valencia
// -- Connor Marks
// -- Josh Cole (joshcole137) prj5;

package prj5;

import student.TestCase;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * @author <Cristian Valencia> <cristianv>
 * @author <Connor Marks> <connorm20>
 * @author <Josh Cole> <joshcole137>
 * 
 * @version <2021.04.27>
 *
 */
public class ControllerTest extends TestCase {
    private Controller testController;
    private CovidReader testRead;
    private LinkedList<Sorter> nullTest;

    /**
     * sets up the controller field objects
     */
    public void setUp() throws FileNotFoundException, IOException {
        this.testRead = new CovidReader(
            "Cases_and_Deaths_by_race_CRDT_Sep2020.csv");
        this.testController = new Controller(testRead.getStats());
        this.nullTest = null;

    }


    /**
     * tests the controller constructor
     */
    public void testController() {
        Exception exception = null;
        try {
            new Controller(nullTest);
        }
        catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertTrue(exception instanceof IllegalArgumentException);
    }


    /**
     * tests the getSorted() method from the Controller class
     */
    public void testGetSorted() {
        assertEquals(this.testController.getSorted(), testRead.getStats());
    }


    /**
     * tests the sortAlpha() method from the Controller class
     */
    public void testSortAlpha() {

        testController.setCurrentStats("NC");
        testController.sortAlpha();

        Statistics[] sortedArray = testController.getStatArray();

        assertEquals(sortedArray[0].toString(), "asian: 41305 cases, 1.8% CFR");
        assertEquals(sortedArray[1].toString(),
            "black: 525056 cases, 3.2% CFR");
        assertEquals(sortedArray[2].toString(),
            "latinx: 787616 cases, -1% CFR");
        assertEquals(sortedArray[3].toString(),
            "other: 1192110 cases, 0.5% CFR");
        assertEquals(sortedArray[4].toString(),
            "white: 1202651 cases, 2.5% CFR");

    }


    /**
     * tests the sortCFR() method from Controller
     */
    public void testSortCfr() {

        // initializes the testController for testing
        testController.setCurrentStats("NC");
        testController.sortCfr();

        // places contents into a Statistics array for testing
        Statistics[] sortedArray = testController.getStatArray();

        // ensures the array is valid at each index
        assertEquals(sortedArray[0].toString(),
            "black: 525056 cases, 3.2% CFR");
        assertEquals(sortedArray[1].toString(),
            "white: 1202651 cases, 2.5% CFR");
        assertEquals(sortedArray[2].toString(), "asian: 41305 cases, 1.8% CFR");
        assertEquals(sortedArray[4].toString(),
            "latinx: 787616 cases, -1% CFR");
        assertEquals(sortedArray[3].toString(),
            "other: 1192110 cases, 0.5% CFR");

    }


    /**
     * tests the setCurrentStats() method from the Controller class
     */
    public void testSetCurrentStats() {
        // tests for the case where setCurrentStats() is passed an invalid
        // parameter
        Exception exception = null;
        try {
            testController.setCurrentStats("VT");
        }
        catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);

        testController.setCurrentStats("VA");

        testController.setCurrentStats("GA");
        assertEquals(testController.getActiveSort(), "GA");

    }


    /**
     * tests the getActiveSort() method from the Controller class
     */
    public void testGetActiveSort() {
        // tests for the case where setCurrentStats is passed a null parameter
        Exception exception = null;
        try {
            testController.setCurrentStats(null);
        }
        catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertTrue(exception instanceof NullPointerException);

        // tests for a valid setCurrentStats() call
        testController.setCurrentStats("VA");
        assertEquals(testController.getActiveSort(), "VA");
    }


    /**
     * tests the waiting() method from the Controller class
     */
    public void testWaiting() {
        Exception exception = null;
        try {
            testController.setCurrentStats(null);
        }
        catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertTrue(exception instanceof NullPointerException);
        testController.setCurrentStats("VA");
        assertTrue(testController.waiting());

    }


    public void testGetStatsArray() {
        testController.setCurrentStats("NC");
        testController.sortAlpha();

        Statistics[] test = testController.getStatArray();

        assertEquals(test[0].toString(), "asian: 41305 cases, 1.8% CFR");
        assertEquals(test[1].toString(), "black: 525056 cases, 3.2% CFR");
        assertEquals(test[2].toString(), "latinx: 787616 cases, -1% CFR");
        assertEquals(test[3].toString(), "other: 1192110 cases, 0.5% CFR");
        assertEquals(test[4].toString(), "white: 1202651 cases, 2.5% CFR");

    }

}
