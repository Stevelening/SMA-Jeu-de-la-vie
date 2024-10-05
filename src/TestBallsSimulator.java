import gui.GUISimulator ;

import java.awt.*;
import java.util.Map;

import gui.Oval;


public class TestBallsSimulator {
    public static void main ( String [] args ) {
        // i1 c'est distance par rapport au top de la fenetre et i2 c'est le coté du carré/ le diametre du cercle
        BallsSimulator simulator = new BallsSimulator();
        simulator.showInit();
    }
}
