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

// import java.util.Comparator;
import java.text.DecimalFormat;

/**
 * @author <Cristian Valencia> <cristianv>
 * @author <Connor Marks> <connorm20>
 * @author <Josh Cole> <joshcole137>
 * 
 * @version <2021.04.23>
 *
 */
public class Statistics {

    private String raceName;
    private int covidDeaths;
    private int covidCases;
    private double cfr;

    /**
     * Constructor
     *
     * @param race
     *            Race of the associated data
     * @param deaths
     *            Covid deaths
     * @param cases
     *            Covid cases
     */

    public Statistics(String race, int deaths, int cases) {
        raceName = race;
        covidDeaths = deaths;
        covidCases = cases;
        cfr = calculateCFR();
    }


    /**
     * 
     * @return race of the data
     */

    public String getRace() {
        return raceName;
    }


    /**
     * 
     * @return number of Covid deaths
     */

    public int getDeaths() {
        return covidDeaths;
    }


    /**
     * 
     * @return number of cases
     */

    public int getCases() {
        return covidCases;
    }


    /**
     * Calculates CFR
     * 
     * @return CFR (Case Fatality Ratio)
     */

    public double calculateCFR() {
        if (this.getDeaths() == -1 || this.getCases() == -1) {
            return -1;
        }

        double deaths = (double)this.getDeaths();
        double cases = (double)this.getCases();

        double initial = (deaths / cases) * 100;
        double answer = Math.round(initial * 10.0) / 10.0;
        return answer;
    }


    /**
     * 
     * @return CFR (Case Fatalitly Ratio)
     */

    public double getCFR() {
        return cfr;
    }


    /**
     * turns our statistics into a nice string
     * 
     * @return string of the statistic
     */
    public String toString() {
        String that = ("##.#");

        DecimalFormat please = new DecimalFormat(that);

        StringBuilder s = new StringBuilder();
        s.append(raceName);
        s.append(": ");
        s.append(getCases());
        s.append(" cases, ");
        s.append(please.format(getCFR()));
        s.append("%");
        s.append(" CFR");
        return s.toString();
    }


    /**
     * Checks if two statistics are equals
     * 
     * @param obj
     *            object to be checked
     * @return boolean on if they are equals
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (this.getClass() == obj.getClass()) {
            Statistics temp = (Statistics)obj;

            return this.getCases() == temp.getCases() && this
                .getDeaths() == temp.getDeaths() && this.getRace().equals(temp
                    .getRace());
        }

        return false;
    }

}
