// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Cristian Valencia
// -- Connor Marks
// -- Josh Cole (joshcole137)

package prj5;

import cs2.Shape;
import cs2.Button;
import cs2.Window;
import cs2.TextShape;
import java.awt.Color;
import cs2.WindowSide;

/**
 *
 *
 * @author <Cristian Valencia> <cristianv>
 * @author <Connor Marks> <connorm20>
 * @author <Josh Cole> <joshcole137>
 *
 * @version <2021.04.27>
 */
public class Viewer {
    private Window window;
    private Controller server;

    private Button sortByAlpha;
    private Button sortByCFR;
    private Button quit;

    private Button representVA;
    private Button representDC;
    private Button representMD;
    private Button representNC;
    private Button representTN;
    private Button representGA;

    private int adjuster;
    private static int DISPLAY_FACTOR = 210;
    private static int DISPLAY_WIDTH = 20;
    private static int DISPLAY_HEIGHT = 233;
    private static int NUMBER_OF_STATES = 5;

    /**
     * GUIDisplayWindow constructor
     * 
     * @param serve
     *            communicatoin with Controller class
     */
    public Viewer(Controller serve) {
        this.window = new Window();
        server = serve;
        adjuster = window.getWidth() / NUMBER_OF_STATES + 1;
        this.window.setTitle(server.getActiveSort()
            + "Case Fatality Ratios By Race");
        this.quit = new Button("Quit");
        quit.onClick(this, "clickedQuit");

        /**
         * initializing all of the necessary buttons
         */
        this.sortByAlpha = new Button("Sort by Alpha");
        sortByAlpha.onClick(this, "clickedSortByAlpha");

        this.sortByCFR = new Button("Sort by CFR");
        sortByCFR.onClick(this, "clickedSortByCFR");

        this.quit = new Button("Quit");
        quit.onClick(this, "clickedQuit");

        this.representVA = new Button("Represent VA");
        representVA.onClick(this, "clickedRepresentVA");

        this.representMD = new Button("Represent MD");
        representMD.onClick(this, "clickedRepresentMD");

        this.representNC = new Button("Represent NC");
        representNC.onClick(this, "clickedRepresentNC");

        this.representTN = new Button("Represent TN");
        representTN.onClick(this, "clickedRepresentTN");

        this.representGA = new Button("Represent GA");
        representGA.onClick(this, "clickedRepresentGA");

        this.representDC = new Button("Represent DC");
        representDC.onClick(this, "clickedRepresentDC");

        // initialize all of the buttons
        window.addButton(quit, WindowSide.NORTH);
        window.addButton(sortByAlpha, WindowSide.NORTH);
        window.addButton(sortByCFR, WindowSide.NORTH);

        window.addButton(representDC, WindowSide.SOUTH);
        window.addButton(representGA, WindowSide.SOUTH);
        window.addButton(representMD, WindowSide.SOUTH);
        window.addButton(representNC, WindowSide.SOUTH);
        window.addButton(representTN, WindowSide.SOUTH);
        window.addButton(representVA, WindowSide.SOUTH);

    }


    /**
     * sorts the stats alphabetically on the viewer
     * 
     * @param button
     *            sort by alpha button
     */

    public void clickedSortByAlpha(Button button) {
        if (server.waiting()) {
            server.sortAlpha();
            refresh();
        }
    }


    /**
     * sorts the stats by CFR on the viewer
     * 
     * @param button
     *            sort by cfr button
     */

    public void clickedSortByCFR(Button button) {
        if (server.waiting()) {
            server.sortCfr();
            refresh();
        }
    }


    /**
     * closes the program
     * 
     * @param button
     *            quit button
     */

    public void clickedQuit(Button button) {
        System.exit(0);
    }


    /**
     * Show stats related to VA
     * 
     * @param button
     *            represent VA
     */

    public void clickedRepresentVA(Button button) {
        server.setCurrentStats("VA");
        refresh();
    }


    /**
     * Show stats related to MD
     * 
     * @param button
     *            represent MD
     */

    public void clickedRepresentMD(Button button) {
        server.setCurrentStats("MD");
        refresh();
    }


    /**
     * Show stats related to NC
     * 
     * @param button
     *            represent NC
     */

    public void clickedRepresentNC(Button button) {
        server.setCurrentStats("NC");
        refresh();
    }


    /**
     * Show stats related to TN
     * 
     * @param button
     *            represent TN
     */

    public void clickedRepresentTN(Button button) {
        server.setCurrentStats("TN");
        refresh();
    }


    /**
     * Show stats related to GA
     * 
     * @param button
     *            represent GA
     */

    public void clickedRepresentGA(Button button) {
        server.setCurrentStats("GA");
        refresh();
    }


    /**
     * Show stats related to DC
     * 
     * @param button
     *            represent DC
     */

    public void clickedRepresentDC(Button button) {
        server.setCurrentStats("DC");
        refresh();
    }


    /**
     * displays the request from the user
     */

    public void refresh() {
        window.removeAllShapes();
        adjuster = window.getWidth() / 9;
        drawGraph();
    }


    /**
     * creates the charts and graphs
     */

    private void drawGraph() {
        Statistics[] stats = server.getStatArray();
        int barY = 0;

        TextShape header = new TextShape(0, 0, server.getActiveSort()
            + " Case Fatality Ratios by Race", Color.BLACK);

        header.moveTo((window.getGraphPanelWidth() - header.getWidth()) / 2,
            window.getGraphPanelHeight() - (window.getGraphPanelHeight() - 15));

        window.addShape(header);

        for (int i = 0; i < NUMBER_OF_STATES; i++) {
             int thisAdjuster = adjuster + (adjuster * i);
//            int thisAdjuster = 0;
////            if (i == 0) {
////                thisAdjuster = adjuster - 100;
////            }

            //thisAdjuster = (adjuster + (i * 2)) + (adjuster * (i));

            // can't have a CFR < 0

            if (stats[i].getCFR() >= 0) {
                barY = (int)(DISPLAY_FACTOR * (stats[i].getCFR() / 10.0));

            }

            else {
                TextShape badCFR = new TextShape(thisAdjuster, DISPLAY_HEIGHT
                    - 20, "NA", Color.BLACK);
                badCFR.setBackgroundColor(Color.WHITE);
                window.addShape(badCFR);
            }

            int barHeight = DISPLAY_HEIGHT - barY;
            Shape bar = new Shape(thisAdjuster, barHeight, DISPLAY_WIDTH, barY,
                Color.BLUE);
            window.addShape(bar);

            // shows name of race under bar
            TextShape statistics = new TextShape(thisAdjuster - DISPLAY_WIDTH,
                barHeight + barY + 5, stats[i].getRace(), Color.BLACK);
            statistics.setBackgroundColor(Color.WHITE);
            window.addShape(statistics);

            // shows text for cfr
            if (stats[i].getCFR() >= 0) {
                TextShape cfr = new TextShape(thisAdjuster - DISPLAY_WIDTH,
                    barHeight + barY + 25, stats[i].getCFR() + "%",
                    Color.BLACK);
                cfr.setBackgroundColor(Color.WHITE);
                window.addShape(cfr);
            }

            barY = 0;
        }
    }

}
