import gui.GUISimulator;
import gui.Oval;
import gui.Rectangle;
import gui.Simulable;

import java.awt.*;
import java.util.Map;

public class BallsSimulator implements Simulable {
    private Balls ball = new Balls();
    private GUISimulator gui;
    private int xMax = 800;
    private int yMax = 500;
    private Color bgcolor = Color.WHITE;
    private int xDep = 10; // deplacement suivant l'axe des X

    private EventManager eventManager = new EventManager();



    public BallsSimulator(){
        gui = new GUISimulator(xMax , yMax , bgcolor );
        gui.setSimulable(this);
    }

    @Override
    public void next(){
        EventBalls e = new EventBalls(this.eventManager.getCurrentDate()+1, this.ball);
        e.setxDep(this.xDep);
        eventManager.addEvent(e);
        eventManager.next();
        draw();
    }

    @Override
    public void restart(){
        eventManager.restart();
        ball.reInit();
        draw();
    }

    void draw(){
        gui.reset();
        for(Map.Entry balle:ball.getListeBalles().entrySet()){
            int key = (int) balle.getKey();
            int x = (int)ball.getListeBalles().get(key).x;
            int y = (int)ball.getListeBalles().get(key).y;
            gui.addGraphicalElement (
                    new Oval(x , y ,
                            Color.decode ( "#1f77b4" ) ,
                            Color.decode ( "#1f77b4" ) ,
                            20) );
            if(x >= xMax || x <= 10) xDep = - xDep;
        }
    }

    void showInit(){
        for(Map.Entry balle:ball.getListeBalles().entrySet()){
            int key = (int) balle.getKey();
            int x = (int)ball.getListeBalles().get(key).x;
            int y = (int)ball.getListeBalles().get(key).y;
            gui.addGraphicalElement (
                    new Oval (x , y ,
                            Color.decode ( "#1f77b4" ) ,
                            Color.decode ( "#1f77b4" ) ,
                            20) ) ;

        }
    }
}
