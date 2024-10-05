import java.awt.*;

public class Cellule extends Point {
    private int etat = 0; // 0 -> mort et 1 -> vivant
    private Color couleur = Color.WHITE; // white -> mort et blue -> vivant

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public Color getCouleur() {
        return couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    public Cellule(){
        super();
    }

    public Cellule(Cellule c){
        super((int)c.x, (int)c.y);
        this.etat = c.etat;
        this.couleur = c.couleur;
    }

    public Cellule(int x, int y){
        super(x, y);
    }

    void nait(){
        etat = 1;
        couleur = Color.decode ( "#1f77b4" );
    }

    void meurt(){
        etat = 0;
        couleur = Color.WHITE;
    }

    void change(int etat, Color couleur){
        this.etat = etat;
        this.couleur = couleur;
    }
}
