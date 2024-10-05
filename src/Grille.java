import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Grille {
    private int xMax;
    private int yMax;
    private int c; // c'est le coté d'une cellule
    private int nbx;
    private int nby;
    private Cellule[][] initCellules;
    private Cellule[][] listeCellules;
    public Grille(int xMax, int yMax, int c){
        this.xMax = xMax;
        this.yMax = yMax;
        this.nbx = xMax/c - 1;
        this.nby = yMax/c - 1;
        this.c = c;
        initCellules = new Cellule[xMax/c - 1][yMax/c - 1];
        listeCellules = new Cellule[xMax/c - 1][yMax/c - 1];
        initCells();
    }
    // on cree toutes les cellules
    public Cellule[][] getListeCellules() {
        return listeCellules;
    }

    public void setListeCellules(Cellule[][] listeCellules) {
        this.listeCellules = listeCellules;
    }

    public int getC() {
        return c;
    }

    public int getNbx() {
        return nbx;
    }

    public int getNby() {
        return nby;
    }

    public Cellule[][] getInitCellules() {
        return initCellules;
    }

    public void setInitCellules(Cellule[][] initCellules) {
        this.initCellules = initCellules;
    }

    void initCells(){
        for(int i = 0; i<nbx; i++){
            for(int j = 0; j<nby; j++){
                listeCellules[i][j] = new Cellule((i+1)*c, (j+1)*c);
            }
        }
        randomInit();
        initCellules = cloneList(listeCellules);
    }

    int neighborAlive(int k, int m){
        int compteur = 0;
        if(listeCellules[editK(k-1)%nbx][editM(m-1)%nby].getEtat() == 1)
            compteur += 1;
        if(listeCellules[(k)%nbx][editM(m-1)%nby].getEtat() == 1)
            compteur += 1;
        if(listeCellules[(k+1)%nbx][editM(m-1)%nby].getEtat() == 1)
            compteur += 1;
        if(listeCellules[editK(k-1)%nbx][(m)%nby].getEtat() == 1)
            compteur += 1;
        if(listeCellules[(k+1)%nbx][(m)%nby].getEtat() == 1)
            compteur += 1;
        if(listeCellules[editK(k-1)%nbx][(m+1)%nby].getEtat() == 1)
            compteur += 1;
        if(listeCellules[(k)%nbx][(m+1)%nby].getEtat() == 1)
            compteur += 1;
        if(listeCellules[(k+1)%nbx][(m+1)%nby].getEtat() == 1)
            compteur += 1;
        return compteur;
    }

    int editK(int k){
        return (k<0) ? nbx - 1 : k;
    }

    int editM(int m){
        return (m<0) ? nby - 1 : m;
    }

    public Cellule[][] cloneList(Cellule[][] liste){
        Cellule[][] copie = new Cellule[xMax/c - 1][yMax/c - 1];
        for(int i= 0;i<nbx;i++){
            for(int j= 0;j<nby;j++){
                if(liste[i][j] != null)
                    copie[i][j] = new Cellule(liste[i][j]);
                else
                    copie[i][j] = new Cellule();
            }
        }
        return copie;
    }

    void translate(){
        Cellule[][] tmp = cloneList(listeCellules);

        for(int i = 0;i<nbx;i++){
            for(int j= 0;j<nby;j++){
                if(listeCellules[i][j].getEtat() == 0 && neighborAlive(i, j) == 3)
                    tmp[i][j].nait();
                if(listeCellules[i][j].getEtat() == 1 && neighborAlive(i, j) != 2 && neighborAlive(i, j) != 3)
                    tmp[i][j].meurt();
            }
        }
        listeCellules = cloneList(tmp);
    }

    void reInit(){
        listeCellules = cloneList(initCellules);
    }

    @Override
    public String toString(){
        String allPoints = "";
        for(int i = 0; i<nbx; i++){
            for(int j = 0; j<nby; j++){
                if(!allPoints.equals("")) allPoints += ", ";
                allPoints += "("+(int)listeCellules[i][j].x+", "
                        +(int)listeCellules[i][j].y+
                        ", "+listeCellules[i][j].getEtat()+
                        ", "+listeCellules[i][j].getCouleur()+")";
            }
        }
        return allPoints;
    }

    void randomInit(){
        for(int i = 0; i<this.getNbx(); i++){
            for(int j = 0; j<this.getNby(); j++){
                int k = (int)(Math.random()*2);
                if(k == 0) listeCellules[i][j].nait();
            }
        }
    }

}
