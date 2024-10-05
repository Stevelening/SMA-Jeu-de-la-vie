
import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;

import java.awt.*;

public class BoidsSimulator implements Simulable {
    // ====== Attributes ======
    private GUISimulator gui;
    private int xMax;
    private int yMax;
    private int c = 10;
    private double wCohesion = 0.0, wAlignement = 0.1, wSeparation = 0.7, speed = 5;
    private Color bgColor = Color.WHITE;
    private BoidsWorld originalWorld;
    private BoidsWorld world;
    private EventManager eventManager = new EventManager();
    
    // ====== Constructors ======
    public BoidsSimulator(int xMax, int yMax, int c, BoidsWorld originalWorld){
        this.xMax = xMax;
        this.yMax = yMax;
        this.c = c;
        this.originalWorld = originalWorld;
        this.world = originalWorld.getCopyOfWorld();
        gui = new GUISimulator(xMax, yMax, bgColor);
        gui.setSimulable(this);
    }
    // ====== Methods ======
    @Override
    public void next(){
        eventManager.addEvent(new EventBoids(this.eventManager.getCurrentDate()+1, this.world));
        eventManager.next();
        draw();
    }

    @Override
    public void restart(){
        eventManager.restart();
        this.world = originalWorld.getCopyOfWorld();
        draw();
    }

    public void draw(){
        gui.reset();
        for(Boids population : this.world.getBoidsList()){
            for(Boid b : population.getListeBoids()){
                this.fixBoidPosition(b, population.getSpeed());
                gui.addGraphicalElement (
                        new Rectangle((int)b.x, (int)b.y,
                                Color.WHITE,
                                population.getColor(),
                                c));
            }
        }
    }

    private void fixBoidPosition(Boid b, double speed){
        boolean fix = false;
        if(b.x+(c/2)>this.xMax){
            fix = true;
            b.x = this.xMax - c;
        }else if(b.x-(c/2)<0){
            fix = true;
            b.x = c;
        }
        if(b.y+(c/2)>this.yMax){
            fix = true;
            b.y = this.yMax - c;
        }else if(b.y-(c/2)<0){
            fix = true;
            b.y = c;
        }
        if(fix)
            b.invertDirection();
    }

}
