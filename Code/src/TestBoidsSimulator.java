import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class TestBoidsSimulator {
    public static void main ( String [] args ){ 
        int xMax=800, yMax=600, c=15;
        BoidsWorld world = new BoidsWorld();
        Random rand = new Random();
        if(args.length>0 && args[0].equals("--cohesion")){
            System.out.println("Testing only cohesion: boids are gonna come together");
            Boids population = new Boids(new ArrayList<Boid>(), 500, c*3, 0.0, 1, 0, 0, 10.0, Color.GREEN, Boids.enumType.PREY);
            population.addBoid(new Boid(100, 100, 0,0));
            population.addBoid(new Boid(400, 100, 0,0));
            population.addBoid(new Boid(100, 400, 0,0));
            population.addBoid(new Boid(400, 400, 0,0));    
            world.addPopulation(population);
        }else{
            Boids population = new Boids(new ArrayList<Boid>(), 500, 30, 90.0, 10, 10.0, 300.0, 10.0, Color.BLUE, Boids.enumType.PREY);
            Boids predators = new Boids(new ArrayList<Boid>(), 300, 50, 20.0, 0.01, 100.0, 300.0, 20.0, Color.PINK, Boids.enumType.PREDATOR);
            Boids fasterPredators = new Boids(new ArrayList<Boid>(), 300, 50, 20.0, 0.01, 100.0, 300.0, 30.0, Color.RED, Boids.enumType.PREDATOR);
            
            for(int i=1; i<=5; i++){
                population.addBoid(new Boid(rand.nextInt(xMax), rand.nextInt(yMax), rand.nextDouble(), rand.nextDouble()));        
                predators.addBoid(new Boid(rand.nextInt(xMax), rand.nextInt(yMax), rand.nextDouble(), rand.nextDouble()));
                fasterPredators.addBoid(new Boid(rand.nextInt(xMax), rand.nextInt(yMax), rand.nextDouble(), rand.nextDouble()));
            }
            world.addPopulation(population);
            world.addPopulation(predators);
            world.addPopulation(fasterPredators);
        }
        BoidsSimulator simulator = new BoidsSimulator(xMax, yMax, c, world);
        simulator.draw();
    }
}
