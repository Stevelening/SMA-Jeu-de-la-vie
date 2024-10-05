import java.awt.*;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Vector;

public class Boid extends Vector2D{ 
    // ====== Attributes ======
    private Vector2D direction;

    // ====== Constructors ======
    public Boid(double x, double y, double vitX, double vitY){
        super(x,y);
        this.direction = new Vector2D(vitX, vitY);
    }

    // ====== Getters ======
    public Vector2D getDirection(){ 
        return this.direction; 
    }
    public Vector2D getLocation(){ 
        return new Vector2D(this.x, this.y); 
    }

    // ====== Setters ======
    public void setDirection(double x, double y){
        this.direction.set(x,y);
    }

    // ====== Other setter methods ======
    public void invertDirection(){
        this.direction.x = -this.direction.x;
        this.direction.y = -this.direction.y;
    }

    public void update(Boids population){
        this.direction.normalize();
        Vector2D force = this.calculateForces(population);
        force.multiply(population.getSpeed());
        this.direction.add(force);
        this.direction.normalize();
        this.direction.multiply(population.getSpeed());
        this.add(this.direction);
        this.direction.normalize();
    }

    public void update(BoidsWorld world){
        for(Boids population : world.getBoidsList()){
            this.update(population);
        }
    }
    
    // ====== Other getter methods ======
    public Boids calculateVoisins(Boids population){
        ArrayList<Boid> voisins = new ArrayList<Boid>();
        for(Boid b : population.getListeBoids()){
            if(this.isVoisin(b, population.getSeuilVoisins(), population.getAngle())){
                voisins.add(b);
            }
        }
        return new Boids(voisins, population.getSeuilVoisins(), population.getSeuilSeparation(), population.getAngle(), population.getwCohesion(), population.getwAlignement(), population.getwSeparation(), population.getSpeed(), population.getColor(), population.getType());
    }

    public Vector2D calculateForces(Boids population){
        Vector2D alignement = new Vector2D(0,0);        
        Vector2D cohesion = new Vector2D(0,0);
        Vector2D separation = new Vector2D(0,0);
        double numVoisins = 0;
        
        for (Boid b : population.getListeBoids()) {
            if(this.distance(b)!=0){
                if(this.isVoisin(b, population.getSeuilVoisins(), population.getAngle())){
                    alignement.add(b.getDirection());
                    cohesion.add(b);
                    numVoisins++;
                }
                separation.add(this.calculateSeparation(b, population.getSeuilSeparation()));
            }
        }
        if(numVoisins>0) {
            alignement.multiply(1/numVoisins);
            cohesion.multiply(1/numVoisins);
        }
        double sum = population.getwCohesion() + population.getwAlignement() + population.getwSeparation();
        alignement.normalize();
        cohesion.subtract(this);
        cohesion.normalize();
        separation.normalize();
        
        alignement.multiply(population.getwAlignement()/sum);
        cohesion.multiply(population.getwCohesion()/sum);
        separation.multiply(population.getwSeparation()/sum);
        Vector2D force = Vector2D.add(Vector2D.add(alignement, cohesion), separation);
        return force;
    }

    public Vector2D calculateSeparation(Boid b, double seuilSeparation){
        Vector2D separation = new Vector2D(0, 0);
        if(this.distance(b)!=0 && this.distance(b)<=seuilSeparation){
            separation.set(Vector2D.subtract(this, b));
            separation.multiply(Math.exp(-this.distance(b)));
        }
        return separation;
    }

    public double calculateAngle(Boid b){
        Vector2D forceBoidVoisin = new Vector2D(b.x-this.x, b.y-this.y);
        double dotProduct = this.direction.dot(forceBoidVoisin);
        return Math.acos(dotProduct/(this.direction.getLength()*forceBoidVoisin.getLength()))/Math.PI*180;
    }

    public boolean isVoisin(Boid b, double seuilVoisins, double angle){
        if(b.direction.getLength()==0 && this.distance(b)<=seuilVoisins)
            return true;
        return ((this.distance(b)!=0 && this.distance(b)<=seuilVoisins) &&
                (   (this.calculateAngle(b)<=(180-(angle/2))) || 
                    (this.calculateAngle(b)>=(180+(angle/2)))));
    }

    @Override
    public String toString(){
        return " position "+super.toString()+ " direction "+this.getDirection().toString();
    }
}

