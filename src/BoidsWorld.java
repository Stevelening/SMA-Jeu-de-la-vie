import java.util.LinkedList;
public class BoidsWorld {
    private LinkedList<Boids> world;

    public BoidsWorld(){
        this.world = new LinkedList<Boids>();
    }

    public LinkedList<Boids> getBoidsList(){
        return this.world;
    }

    public BoidsWorld getCopyOfWorld(){
        BoidsWorld copy = new BoidsWorld();
        for(Boids population : this.world){
            copy.addPopulation(population.getCopyOfBoids());
        }
        return copy;
    }

    public void setWorld(LinkedList<Boids> world){
        this.world = world;
    }

    public void addPopulation(Boids population){
        this.world.add(population);
    }

    @Override
    public String toString(){
        String res ="";
        for(Boids population : this.world){
            res+= population.toString() + "\n";
        }
        return res;
    }
}
