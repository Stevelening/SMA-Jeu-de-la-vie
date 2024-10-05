import java.awt.Color;
import java.util.ArrayList;

public class TestEventBoids {
    public static void main ( String [] args ) throws InterruptedException {
        EventManager manager = new EventManager () ;
        Boids population = new Boids(new ArrayList<Boid>(), 500, 200, 40.0, 0.01, 100.0, 3000.0, 10.0, Color.PINK, Boids.enumType.PREY);
        Boids predators = new Boids(new ArrayList<Boid>(), 800, 100, 20.0, 0.001, 100.0, 3000.0, 30.0, Color.BLUE, Boids.enumType.PREDATOR);
        BoidsWorld world = new BoidsWorld();
        for(int i=1; i<4; i++){
            population.addBoid(new Boid(i, i, i, i));
            predators.addBoid(new Boid(i, i+1, -i, -i));
        }
        world.addPopulation(population);
        world.addPopulation(predators);
        manager.addEvent(new EventBoids(1, world));
        while(!manager.isFinished()){
            manager.next() ;
        }
    }
}

