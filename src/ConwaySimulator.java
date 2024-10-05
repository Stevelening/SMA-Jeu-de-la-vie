import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;

import java.awt.Color;

public class ConwaySimulator implements Simulable {
    private GUISimulator gui;
    private int xMax = 800;
    private int yMax = 500;
    private int c = 50;
    private int nbx = xMax/c - 1;
    private int nby = yMax/c - 1;
    private Color bgcolor = Color.WHITE;
    Grille grille;

    private EventManager eventManager = new EventManager();


    public ConwaySimulator(){
        grille = new Grille(xMax, yMax, c);
        gui = new GUISimulator(xMax , yMax , bgcolor );
        gui.setSimulable(this);
    }

    public ConwaySimulator(int nbetats, String[] listeColors){
        grille = new Immigration(xMax, yMax, c, nbetats, listeColors);
        gui = new GUISimulator(xMax , yMax , bgcolor );
        gui.setSimulable(this);
    }

    public ConwaySimulator(int nbcolors, String[] listeColors, int k){
        grille = new Shelling(xMax, yMax, c, nbcolors, listeColors, k);
        gui = new GUISimulator(xMax , yMax , bgcolor );
        gui.setSimulable(this);
    }

    @Override
    public void next(){
        eventManager.addEvent(new EventGrille(this.eventManager.getCurrentDate()+1, this.grille));
        eventManager.next();
        draw();
    }

    @Override
    public void restart(){
        eventManager.restart();
        grille.reInit();
        draw();
    }

    void draw(){
        gui.reset();
        for(int i = 0; i<nbx; i++){
            for(int j = 0; j<nby; j++){
                gui.addGraphicalElement (
                        new Rectangle((int)grille.getListeCellules()[i][j].x ,
                                (int)grille.getListeCellules()[i][j].y,
                                Color.GRAY ,
                                grille.getListeCellules()[i][j].getCouleur() ,
                                c));
            }
        }
    }
}
