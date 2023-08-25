// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Cristian Valencia
// -- Connor Marks
// -- Josh Cole (joshcole137) prj5;

package prj5;

import java.util.NoSuchElementException;

/**
 * @author <Cristian Valencia> <cristianv>
 * @author <Connor Marks> <connorm20>
 * @author <Josh Cole> <joshcole137>
 * 
 * @version <2021.04.20>
 *
 */
public class Controller {
    private LinkedList<Sorter> sortedStats;
    private Sorter theseStats;

    private boolean isAlpha = true;

    /**
     * 
     * @param sorted
     *            List of stats coming in to be displayed
     * @throws IllegalArgumentException
     *             when the list is null
     */

    public Controller(LinkedList<Sorter> sorted) {
        if (sorted == null) {
            throw new IllegalArgumentException("List is null");
        }

        sortedStats = sorted;
    }


    /**
     * 
     * @return the sorted stats that the controller needs
     */

    public LinkedList<Sorter> getSorted() {
        return sortedStats;
    }


    /**
     * Show the stats sorted alphabetically by race
     */

    public void sortAlpha() {
        theseStats.sortByAlpha();
        isAlpha = true;
    }


    /**
     * Show the stats sorted by CFR
     */

    public void sortCfr() {
        theseStats.sortByCFR();
        isAlpha = false;
    }


    /**
     * For switching between states but not by sort method
     */

    private void sustain() {
        if (isAlpha) {
            theseStats.sortByAlpha();
        }
        theseStats.sortByCFR();
    }


    /**
     * 
     * @param stats
     *              the stats of the state to display
     * @throws NoSuchElementException
     *          Happens when the stats are not found
     * @return String of the stats
     */
    
    public String setCurrentStats(String stats) {
        stats = stats.toLowerCase();

        for (Sorter currentSort : sortedStats) {
            if (stats.equals(currentSort.getName().toLowerCase())) {
                theseStats = currentSort;
                sustain();
                return currentSort.getName();
            }
        }
        throw new NoSuchElementException("Stats could be found");
    }
    
    /**
     * getter for the name of the current  Stats
     * 
     * @return string version of theseStats
     */
    
    public String getActiveSort() {
        if (theseStats == null) {
            return null;
        }
        
        return theseStats.getName();
    }
    
    /**
     * prints the stats to an array
     * 
     * @throws IllegalArgumentException
     *          when no stats are found
     * @return the stats in an array form
     */
    
    public Statistics[] getStatArray() {
        if (theseStats == null) {
            throw new IllegalArgumentException("No stats selected");
        }
        
        return theseStats.getStats();
    }
    
    /**
     * Are there stats on standby?
     * @return boolean if the stats are on standby
     */
   
    public boolean waiting() {
        return theseStats != null;
    }

}
