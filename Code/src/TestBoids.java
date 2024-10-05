import java.util.ArrayList;
import java.awt.*;

public class TestBoids {

    public static void main(String[] args) {
        System.out.println("** TestBoids **");
        System.out.println("\n--> Test voisins");
        Boid x = new Boid(0, 0, 0, 3);
        Boid y = new Boid(4, 0, -4, 3);
        Boid z = new Boid(0, -2, -4, 3);
        //Boid x = new Boid(0, 0, 0, 3);
        //Boid y = new Boid(3, 0, -3, 3);
        System.out.println("Angle (0,0): " + x.calculateAngle(y) + ", expected: 90");
        System.out.println("Angle (4,0): " + y.calculateAngle(x) + ", expected: 36");
        System.out.println("isVoisin: " + x.isVoisin(y, 5, 90) + ", expected: true");     
        System.out.println("isVoisin: " + x.isVoisin(y, 5, 180) + ", expected: true");
        System.out.println("isVoisin: " + x.isVoisin(y, 5, 190) + ", expected: false");
        System.out.println("isVoisin: " + x.isVoisin(y, 1, 180) + ", expected: false");
        System.out.println("isVoisin: " + x.isVoisin(z, 2, 0) + ", expected: true");
        System.out.println("isVoisin: " + x.isVoisin(z, 1, 0) + ", expected: false");
        System.out.println("isVoisin: " + x.isVoisin(z, 2, 180) + ", expected: false");
        System.out.println("isVoisin: " + x.isVoisin(z, 1, 180) + ", expected: false");

        System.out.println("\n--> Test population (class Boids)");
        
        Boids population = new Boids(new ArrayList<Boid>(), 500, 200, 40.0, 1, 1, 1, 10.0, Color.PINK, Boids.enumType.PREDATOR);

        for(int i=1; i<4; i++){
            Boid b = new Boid(i, i, i, i);
            population.addBoid(b);
        }
        /* 
        for(int i=1; i<4; i++){
            Boid b = new Boid(-i, -i, -i, -i);
            population.addBoid(b);
        }
        */
        System.out.println("-> Population:\n" + population.toString() + "\n");
        for(Boid b : population.getListeBoids()){
            System.out.println("\n-> For boid: " + b);
            System.out.println("Voisins: " + b.calculateVoisins(population).toString());
            System.out.println("Total force: " + b.calculateForces(population));
        }
        //System.out.println("\n\nAfter update:\n");
        population.update();
        //System.out.println("\n\nAfter: \n" + population.toString());
    }
}
