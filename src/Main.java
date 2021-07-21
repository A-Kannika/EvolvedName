/**
 * Create by Kannika Armstrong
 * TCSS342(Spring 2021): April 14, 2021
 * Assignment1 : Evolved Names (Main class)
 * Professor. Christopher Paul Marriott
 */
public class Main {
    public static void main(String[] args) {
        runMyProgram();
    }

    private static void runMyProgram() {
        Population p = new Population();
        int generation = 0;
        long start = System.currentTimeMillis();
        do {
            p.nextGeneration();
            System.out.println(p.toString());
            generation++;
        } while (p.mostFit.fitness() != 0);
        long end = System.currentTimeMillis();
        System.out.println("Generations: " + generation + "\nRunning Time: " + (end - start) + " milliseconds");
    }
}
