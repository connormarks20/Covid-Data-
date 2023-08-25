package prj5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
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
 * @author <Cristian Valencia> <cristianv>
 * @author <Connor Marks> <connorm20>
 * @author <Josh Cole> <joshcole137>
 * @version 4.22.2021
 *
 */
public class CovidReader {

    /**
     * LinkedList field of type sorter that will include stats info
     */
    private LinkedList<Sorter> stats;

    public CovidReader(String name) throws IOException, FileNotFoundException {
        // this.stats = readFile(name);
        stats = readFile(name);
    }


    /**
     *
     * @param read
     *            for the string being read
     * @return
     * @throws IOException
     *             for input/output exceptions
     * @throws ParseException
     *             for parsing exceptions
     * @throws FileNotFoundException
     *             for file not found exceptiosn
     */
    public LinkedList<Sorter> readFile(String name)
        throws FileNotFoundException,
        IOException {
        Scanner scan = new Scanner(new File(name));
        String line = scan.nextLine();
        LinkedList<Sorter> stats = new LinkedList<Sorter>();

        while (scan.hasNextLine()) {
            LinkedList<Statistics> raceData = new LinkedList<Statistics>();
            line = scan.nextLine();
            String[] lineSplit = line.split(", *");
            Sorter sortData = new Sorter(lineSplit[0]);

            Statistics white = new Statistics("white", convertInt(lineSplit[6]),
                convertInt(lineSplit[1]));
            Statistics black = new Statistics("black", convertInt(lineSplit[7]),
                convertInt(lineSplit[2]));
            Statistics latin = new Statistics("latinx", convertInt(lineSplit[8]),
                convertInt(lineSplit[3]));
            Statistics asian = new Statistics("asian", convertInt(lineSplit[9]),
                convertInt(lineSplit[4]));
            Statistics other = new Statistics("other", convertInt(
                lineSplit[10]), convertInt(lineSplit[5]));

            raceData.add(other);
            raceData.add(asian);
            raceData.add(latin);
            raceData.add(black);
            raceData.add(white);

            sortData.setStats(raceData);

            stats.add(sortData);

        }
        return stats;

    }


    /**
     * method to return the necessary stats data after parsing
     *
     * @return the stats
     */
    public LinkedList<Sorter> getStats() {
        return stats;
    }


    /**
     * method to parse through a string
     *
     * @param string
     * @return the conversion
     */
    private int convertInt(String string) {
        try {
            int n = Integer.parseInt(string);
            return n;
        }
        catch (NumberFormatException exception) {
            return -1;
        }
    }
}
