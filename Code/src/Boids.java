import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Boids {
    // ====== Attributes ======
    public static enum enumType{PREY, PREDATOR};
    private enumType type;
    private ArrayList<Boid> listeBoids;
    private double seuilVoisins = 0;
    private double seuilSeparation = 0;
    private double angle = 90; // em graus
    private double wCohesion = 1;
    private double wAlignement = 1;
    private double wSeparation = 1;
    private double speed = 1;
    private Color color = Color.GREEN;

    // ====== Constructors ======
    public Boids(){
        this.listeBoids = new ArrayList<Boid>();
    }

    public Boids(ArrayList<Boid> listeBoids, double seuilVoisins, double seuilSeparation, double angle, double wCohesion, double wAlignement, double wSeparation, double speed, Color color, enumType type){
        this.listeBoids = new ArrayList<Boid>();
        for(Boid b : listeBoids){
            this.listeBoids.add(new Boid(b.x, b.y, b.getDirection().x, b.getDirection().y));
        }
        this.seuilVoisins = seuilVoisins;
        this.seuilSeparation = seuilSeparation;
        this.angle = angle;
        this.wCohesion = wCohesion;
        this.wAlignement = wAlignement;
        this.wSeparation = wSeparation;
        this.color = color;
        this.speed = speed;
        this.type = type;
    }

    // ====== Getters ======
    public ArrayList<Boid> getListeBoids(){ return this.listeBoids; }
    public double getSeuilVoisins(){ return this.seuilVoisins; }
    public double getSeuilSeparation(){ return this.seuilSeparation; }
    public double getAngle(){ return this.angle; }
    public double getwCohesion(){ return this.wCohesion; }
    public double getwAlignement(){ return this.wAlignement; }
    public double getwSeparation(){ return this.wSeparation; }
    public double getSpeed(){ return this.speed; }
    public Color getColor(){ return this.color; }
    public enumType getType(){ return this.type; }
    public ArrayList<Boid> getCopyOfBoidsList(){
        ArrayList<Boid> copy = new ArrayList<Boid>();
        for(Boid b : this.listeBoids){
            copy.add(new Boid(b.x*1, b.y*1, b.getDirection().x*1, b.getDirection().y*1));
        }
        return copy;
    }
    public Boids getCopyOfBoids(){
        return new Boids(this.getCopyOfBoidsList(), this.seuilVoisins, this.seuilSeparation, this.angle, this.wCohesion, this.wAlignement, this.wSeparation, this.speed, this.color, this.type);
    }

    // ====== Setters ======
    public void setListeBoids(ArrayList<Boid> listeBoids){
        this.listeBoids = listeBoids;
    }
    public void setSeuilVoisins(double seuilVoisins){
        if(this.seuilSeparation>=seuilVoisins)
            throw new IllegalArgumentException("setSeuilVoisins: Le seuil de separation doit etre inferieur au seuil de voisinage");
        this.seuilVoisins = seuilVoisins;
    }
    public void setSeuilSeparation(double seuilSeparation){
        if(seuilSeparation>=this.getSeuilVoisins())
            throw new IllegalArgumentException("setSeuilSeparation: Le seuil de separation doit etre inferieur au seuil de voisinage");
        this.seuilSeparation = seuilSeparation;
    }

    public void setAngle(double angle){
        this.angle = angle;
    }

    // ====== Other setter methods ======
    public void update(){
        Boids oldBoids = new Boids(this.listeBoids, this.seuilVoisins, this.seuilSeparation, this.angle, this.wCohesion, this.wAlignement, this.wSeparation, this.speed, this.color, this.type);
        for(Boid b : this.listeBoids){
            b.update(oldBoids);
        }
    }

    public void update(BoidsWorld world){
        BoidsWorld oldWorld = world.getCopyOfWorld();
        for(Boid b : this.listeBoids){
            b.update(oldWorld);
        }
    }
    public void addBoid(Boid b){
        this.listeBoids.add(b);
    }
    @Override
    public String toString(){
        String res ="";
        for(Boid b : this.listeBoids){
            res+= b.toString() + "\n";
        }
        return res;
    }
}
