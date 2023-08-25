// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Cristian Valencia (cristianv)
// -- Connor Marks (connorm20)
// -- Josh Cole (joshcole137)
/**
 *
 */
package prj5;

import java.util.Comparator;

/**
 * @author <Cristian Valencia> <cristianv>
 * @author <Connor Marks> <connorm20>
 * @author <Josh Cole> <joshcole137>
 *
 * @version <2021.04.20>
 *
 */

public class Sorter {
    private LinkedList<Statistics> statistics;
    private String stateName;

    /**
     * Constructor
     *
     * @param name
     *            name of the state
     */

    public Sorter(String name) {

        stateName = name;
        // sorted = new LinkedList<Statistics>();
    }


    /**
     * sets the statistics linkedlist
     * 
     * @param stats
     *            for instantiating statistics
     */
    public void setStats(LinkedList<Statistics> stats) {
        this.statistics = stats;
    }

    /**
     *
     * @return the statistics of the races
     */

// public LinkedList<Statistics> getStats() {
// return statistics;
// }


    /**
     *
     * @return name of the state
     */

    /**
     *
     * @return name of the state
     */

    public String getName() {
        return stateName;
    }


    /**
     *
     * @return statistics list in array
     */

    public Statistics[] getStats() {
        Object[] temp = statistics.toArray();
        Statistics[] stats = new Statistics[temp.length];
        for (int i = 0; i < temp.length; i++) {
            stats[i] = (Statistics)temp[i];
        }
        return stats;

    }


    /**
     * @return String version of the sorted lists
     */
    public String sortedToString() {
        StringBuilder s = new StringBuilder();
        this.sortByAlpha();
        s.append(stateName);
        s.append("\n");

        for (Statistics stats : this.getStats()) {
            s.append(stats.toString());
            s.append("\n");
        }

        s.append("=====");
        s.append("\n");
        this.sortByCFR();

        for (Statistics stats : this.getStats()) {
            s.append(stats.toString());
            s.append("\n");
        }

        s.append("=====");
        return s.toString();
    }


    /**
     * sort the list alphabetically by race
     */

    public void sortByAlpha() {
        LinkedList.sort(statistics, alphaComparator());
    }


    /**
     * sort the list by CFR by race
     */

    public void sortByCFR() {
        LinkedList.sort(statistics, cfrComparator());
    }


    /**
     * Helper method to sort by CFR
     *
     * @return a compartor for CFR
     */

    static Comparator<Statistics> cfrComparator() {
        return new Comparator<Statistics>() {

            /**
             * Compare the two object's CFRs
             */
            @Override
            public int compare(Statistics thisObject, Statistics other) {
                if (thisObject.getCFR() == other.getCFR()) {
                    return thisObject.getRace().compareTo(other.getRace());
                }
                double comparedCFR = (other.getCFR() - thisObject.getCFR())
                    * 10;
                return (int)comparedCFR;
            }
        };
    }


    /**
     * Helper method to sort alphabetically
     *
     * @return a comparator for alphabetically
     */

    static Comparator<Statistics> alphaComparator() {
        return new Comparator<Statistics>() {

            /**
             * compares the objects by the first letter of their race
             */

            @Override
            public int compare(Statistics thisObject, Statistics other) {
                return thisObject.getRace().compareTo(other.getRace());
            }
        };
    }


    /**
     * checks if two sorted lists are the same
     *
     * @param obj
     *            the object to be compared
     *
     * @return boolean if the lists are the same
     */

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (this.getClass() == obj.getClass()) {
            Sorter temp = (Sorter)obj;

            if (this.getStats().length != temp.getStats().length || !this
                .getName().equals(temp.getName())) {
                return false;
            }

            Statistics[] thisArray = this.getStats();
            Statistics[] otherArray = temp.getStats();

            for (int i = 0; i < thisArray.length; i++) {
                if (!thisArray[i].equals(otherArray[i])) {
                    return false;
                }
            }
            return true;

        }
        return false;
    }

}
