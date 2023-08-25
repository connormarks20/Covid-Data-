// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Cristian Valencia
// -- Connor Marks
// -- Josh Cole (joshcole137)

package prj5;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author <Cristian Valencia> <cristianv>
 * @author <Connor Marks> <connorm20>
 * @author <Josh Cole> <joshcole137>
 * 
 * @version <2021.04.20>
 *
 */

public class Input {

    /**
     * 
     * @param args
     *            file of where the data can be found
     * @throws IOException
     *             thrown if there is a problem with reading the file
     * @throws FileNotFoundException
     *             thrown if the file does not exist
     */

    public static void main(String[] args)
        throws IOException,
        FileNotFoundException {
        CovidReader covidReader;
        // CovidReader randomReader;

        if (args.length == 1) {
            covidReader = new CovidReader(args[0]);
            // randomReader = new CovidReader(args[0]);
        }
        else {
            covidReader = new CovidReader(
                "Cases_and_deaths_by_race_CRDT_Sep2020.csv");

        }

        LinkedList<Sorter> covidStats = covidReader.getStats();
        new Viewer(new Controller(covidStats));

        for (Sorter sorted : covidStats) {

            System.out.println(sorted.sortedToString());
        }

    }
}
