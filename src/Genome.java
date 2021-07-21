import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
/**
 * Create by Kannika Armstrong
 * TCSS342(Spring 2021): April 14, 2021
 * Assignment1 : Evolved Names (Genome class)
 * Professor. Christopher Paul Marriott
 */

public class Genome implements Comparable<Genome> {

    protected MyLinkedList<Character> genes = new MyLinkedList<>();
    private MyLinkedList<Character> target = new MyLinkedList<>();
    public String name = "CHRISTOPHER PAUL MARRIOTT";
    public List<Character> Characters = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' ', '-', '\'');

    public Genome() {
        genes = new MyLinkedList<>();
        this.target = new MyLinkedList<>();
        for(char c : name.toCharArray()){
            this.target.add(c);
        }
    }

    public Genome(Genome genome){
        this.target = genome.target;
        for(int i = 0; i < genome.genes.size(); i++){
            genes.add(genome.genes.get(i));
        }
    }

    public void mutate(double mutationRate){
        Random r = new Random();
        Character c = Characters.get(r.nextInt(Characters.size()));
        int index = r.nextInt(genes.size() + 1);

        // Add random char to random location
        if (r.nextDouble() < mutationRate) {
            if (genes.size() == 0) {
                genes.add(c);
            } else {
                genes.add(c, index);
            }
        }

        //Delete char at random location
        if(r.nextDouble() < mutationRate && genes.size() >= 1) {
            genes.remove(index);
        }

        //Run over each character, swap char with another random char (mutationRate chance)
        for(int i = 0; i < genes.size(); i++) {
            if(r.nextDouble() < mutationRate) {
                genes.set(i, c);
            }
        }
    }

    public void crossover(Genome parent){
        Random r = new Random();
        MyLinkedList<Character> newGenes = new MyLinkedList<>();

        int max = Math.max(genes.size(), parent.genes.size());

        for(int i = 0 ; i < max ; i++){
            if(genes.iterator().hasNext() && i < genes.size()){
                newGenes.add(genes.get(i));
            }
            else if(i < parent.genes.size()){
                newGenes.add(parent.genes.get(i));
            }
        }

    }

    public int fitness(){
        int f = (Math.abs(target.size() - genes.size())*2);
        int min = Math.min(target.size(), genes.size());
        for(int i = 0; i < min; i++){
            if (!(genes.get(i).equals(target.get(i)))) {
                f++;
            }
        }
        return (-1 * f);
    }

    public int compareTo(Genome other) {
        return Integer.compare(this.fitness(), other.fitness());
    }

    public String toString(){ return  genes.toString(); }


}
