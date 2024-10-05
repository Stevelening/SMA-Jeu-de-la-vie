import java.awt.Color;

// Cette classe represente la grille du jeu de l'immigration
public class Immigration extends Grille{
    private int nbetats;
    private int[] listeEtats;
    private Color[] listeCouleurs;

    public void setListeCouleurs(Color[] listeCouleurs) {
        this.listeCouleurs = listeCouleurs;
    }

    public Immigration(int xMax, int yMax, int c, int nbetats, String[] liste){
        super(xMax, yMax, c);
        this.nbetats = nbetats;
        listeEtats = new int[nbetats];
        listeCouleurs = new Color[nbetats];
        initColorsAndStates(liste);
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
        // retourne le nombre de voisins a l'etat k+1 (la cellule est a l'etat k)
        int state = (getListeCellules()[k][m].getEtat() + 1)%nbetats;
        int compteur = 0;
        if(getListeCellules()[editK(k-1)%getNbx()][editM(m-1)%getNby()].getEtat() == state)
            compteur += 1;
        if(getListeCellules()[(k)%getNbx()][editM(m-1)%getNby()].getEtat() == state)
            compteur += 1;
        if(getListeCellules()[(k+1)%getNbx()][editM(m-1)%getNby()].getEtat() == state)
            compteur += 1;
        if(getListeCellules()[editK(k-1)%getNbx()][(m)%getNby()].getEtat() == state)
            compteur += 1;
        if(getListeCellules()[(k+1)%getNbx()][(m)%getNby()].getEtat() == state)
            compteur += 1;
        if(getListeCellules()[editK(k-1)%getNbx()][(m+1)%getNby()].getEtat() == state)
            compteur += 1;
        if(getListeCellules()[(k)%getNbx()][(m+1)%getNby()].getEtat() == state)
            compteur += 1;
        if(getListeCellules()[(k+1)%getNbx()][(m+1)%getNby()].getEtat() == state)
            compteur += 1;
        return compteur;
    }

    @Override
    void translate(){
        Cellule[][] tmp = cloneList(getListeCellules());
        for(int i = 0;i<getNbx();i++){
            for(int j= 0;j<getNby();j++){
                if(neighborAlive(i, j) >= 3){
                    int k = (getListeCellules()[i][j].getEtat() + 1)%nbetats;
                    tmp[i][j].change(k, listeCouleurs[k]);
                }

            }
        }
        setListeCellules(cloneList(tmp));
    }

    void initColorsAndStates(String[] liste){
        for(int i = 0;i<nbetats;i++){
            listeEtats[i] = i;
            listeCouleurs[i] = Color.decode(liste[i]);
        }
    }

    @Override
    void randomInit(){
        for(int i = 0; i<this.getNbx(); i++){
            for(int j = 0; j<this.getNby(); j++){
                int k = (int)(Math.random()*nbetats);
                getListeCellules()[i][j].change(k, listeCouleurs[k]);
            }
        }
        this.setInitCellules(cloneList(this.getListeCellules()));
    }
}
