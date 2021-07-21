import java.util.Random;
/**
 * Create by Kannika Armstrong
 * TCSS342(Spring 2021): April 14, 2021
 * Assignment1 : Evolved Names (Population class)
 * Professor. Christopher Paul Marriott
 */
public class Population {

    public MyLinkedList<Genome> population;
    public Genome mostFit;
    private int size;
    private double mutationRate;

    public Population(){
        this.size = 100;
        this.mutationRate = 0.05;
        this.population = new MyLinkedList<>();
        for (int i = 0; i < size; i++) {
            this.population.add(new Genome());
        }
        this.mostFit = population.iterator().next();
    }

    public void nextGeneration(){
        Random r = new Random();
        Genome clone;
        Genome other;
        for(int i = 0; i < 50; i++){
            int j = r.nextInt(50) + 50;
            clone = new Genome(population.get(j));
            if (r.nextInt(2) == 1) {
                other = new Genome(population.get(j));
                clone.crossover(other);
            }
            clone.mutate(mutationRate);
            population.set(i, new Genome(clone));
        }
        population.sort();
        //check which is the highest fitness
        this.mostFit = population.get(this.size - 1);
    }

    public String toString(){
        return "(\"" + mostFit.toString() + "\", " + mostFit.fitness() + ")";
    }
}
