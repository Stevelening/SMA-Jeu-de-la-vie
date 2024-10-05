import java.awt.Color;
import java.util.LinkedList;

// Cette classe représente la grille du modele de Shelling
public class Shelling extends Grille{
    private int nbcolors;
    private int seuil; // c'est le seuil
    private Color[] listeCouleurs;
    private LinkedList<Cellule> cellulesVacantes = new LinkedList<Cellule>();
    private LinkedList<Cellule> initCellulesVacantes = new LinkedList<Cellule>();

    public Shelling(int xMax, int yMax, int c, int nbcolors, String[] liste, int seuil){
        super(xMax, yMax, c);
        this.nbcolors = nbcolors;
        this.seuil = seuil;
        listeCouleurs = new Color[nbcolors];
        initColors(liste);
        randomInit();
    }

    @Override
    void initCells(){
        for(int i = 0; i<this.getNbx(); i++){
            for(int j = 0; j<this.getNby(); j++){
                this.getListeCellules()[i][j] = new Cellule((i+1)*this.getC(), (j+1)*this.getC());
            }
        }
    }

    @Override
    int neighborAlive(int k, int m){
        // retourne le nombre de voisins de couleur differente de celle de la cellule
        int state = getListeCellules()[k][m].getEtat();
        if(state == 0)
            return -1; // retourne -1 si la cellule est vacante (etat = 0)
        Color color = getListeCellules()[k][m].getCouleur();
        int compteur = 0;
        if(getListeCellules()[editK(k-1)%getNbx()][editM(m-1)%getNby()].getCouleur() != color && getListeCellules()[editK(k-1)%getNbx()][editM(m-1)%getNby()].getEtat() == 1)
            compteur += 1;
        if(getListeCellules()[(k)%getNbx()][editM(m-1)%getNby()].getCouleur() != color && getListeCellules()[editK(k-1)%getNbx()][editM(m-1)%getNby()].getEtat() == 1)
            compteur += 1;
        if(getListeCellules()[(k+1)%getNbx()][editM(m-1)%getNby()].getCouleur() != color && getListeCellules()[editK(k-1)%getNbx()][editM(m-1)%getNby()].getEtat() == 1)
            compteur += 1;
        if(getListeCellules()[editK(k-1)%getNbx()][(m)%getNby()].getCouleur() != color && getListeCellules()[editK(k-1)%getNbx()][editM(m-1)%getNby()].getEtat() == 1)
            compteur += 1;
        if(getListeCellules()[(k+1)%getNbx()][(m)%getNby()].getCouleur() != color && getListeCellules()[editK(k-1)%getNbx()][editM(m-1)%getNby()].getEtat() == 1)
            compteur += 1;
        if(getListeCellules()[editK(k-1)%getNbx()][(m+1)%getNby()].getCouleur() != color && getListeCellules()[editK(k-1)%getNbx()][editM(m-1)%getNby()].getEtat() == 1)
            compteur += 1;
        if(getListeCellules()[(k)%getNbx()][(m+1)%getNby()].getCouleur() != color && getListeCellules()[editK(k-1)%getNbx()][editM(m-1)%getNby()].getEtat() == 1)
            compteur += 1;
        if(getListeCellules()[(k+1)%getNbx()][(m+1)%getNby()].getCouleur() != color && getListeCellules()[editK(k-1)%getNbx()][editM(m-1)%getNby()].getEtat() == 1)
            compteur += 1;
        return compteur;
    }

    @Override
    void translate(){
        Cellule[][] tmp = cloneList(getListeCellules());
        for(int i = 0;i<getNbx();i++){
            for(int j= 0;j<getNby();j++){
                if(neighborAlive(i, j) > seuil && !cellulesVacantes.isEmpty() && getListeCellules()[i][j].getEtat() == 1){
                    // demenage
                    tmp[i][j].change(0, Color.WHITE);
                    cellulesVacantes.add(new Cellule(tmp[i][j]));
                    // amenage
                    int x = (int)cellulesVacantes.get(0).x/getC() - 1;
                    int y = (int)cellulesVacantes.get(0).y/getC() - 1;
                    tmp[x][y].change(1, getListeCellules()[i][j].getCouleur());
                    cellulesVacantes.remove(0);
                }

            }
        }
        setListeCellules(cloneList(tmp));
    }

    void initColors(String[] liste){
        for(int i = 0;i<nbcolors;i++){
            listeCouleurs[i] = Color.decode(liste[i]);
        }
    }

    @Override
    void randomInit(){
        for(int i = 0; i<this.getNbx(); i++){
            for(int j = 0; j<this.getNby(); j++){
                int k = (int)(Math.random()*2);
                if(k == 0){
                    //getListeCellules()[i][j].change(0, Color.WHITE);
                    cellulesVacantes.add(new Cellule(getListeCellules()[i][j]));
                }
                else{
                    int t = (int)(Math.random()*nbcolors);
                    getListeCellules()[i][j].change(1, listeCouleurs[t]);
                }
            }
        }
        this.setInitCellules(cloneList(this.getListeCellules()));
        initCellulesVacantes = copie(cellulesVacantes);
    }

    @Override
    void reInit(){
        setListeCellules(cloneList(getInitCellules()));
        cellulesVacantes = copie(initCellulesVacantes);
    }

    LinkedList<Cellule> copie(LinkedList<Cellule> liste){
        LinkedList<Cellule> copie = new LinkedList<Cellule>();
        for (Cellule cel: liste) {
            copie.add(new Cellule(cel));
        }
        return copie;
    }
}
