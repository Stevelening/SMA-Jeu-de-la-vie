import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Balls {
    // on cree quelques balles
    private HashMap<Integer, Point> initListeBalles = new HashMap<Integer, Point>();
    private HashMap<Integer, Point> listeBalles = new HashMap<Integer, Point>();

    public HashMap<Integer, Point> getListeBalles() {
        return listeBalles;
    }

    public void setListeBalles(HashMap<Integer, Point> listeBalles) {
        this.listeBalles = listeBalles;
    }

    public Balls(){
        listeBalles.put(0, new Point(20, 50));
        listeBalles.put(1, new Point(25, 50));
        listeBalles.put(2, new Point(30, 50));
        listeBalles.put(3, new Point(35, 50));
        listeBalles.put(4, new Point(40, 50));
        initListeBalles = cloneList(listeBalles); // on copie et conserve la position initiale des points
    }

    void translate(int dx, int dy){
        for(Map.Entry balle:listeBalles.entrySet()){
            listeBalles.get(balle.getKey()).setLocation(listeBalles.get(balle.getKey()).x + dx,
                    listeBalles.get(balle.getKey()).y + dy);
        }
    }

    void reInit(){
        listeBalles = cloneList(initListeBalles);
    }

    @Override
    public String toString(){
        String allPoints = "";
        for(Map.Entry balle:listeBalles.entrySet()){
            if(!allPoints.equals("")) allPoints += ", ";
            allPoints += "("+(int)listeBalles.get(balle.getKey()).x+", "
                    +(int)listeBalles.get(balle.getKey()).y+")";
        }
        return allPoints;
    }

    public HashMap<Integer, Point> cloneList(HashMap<Integer, Point> liste){
        HashMap<Integer, Point> copie = new HashMap<Integer, Point>();
        for(Map.Entry balle:liste.entrySet()){
            int key = (int) balle.getKey();
            copie.put(key, new Point(liste.get(key)));
        }
        return copie;
    }
}
