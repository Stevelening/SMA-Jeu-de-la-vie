public class EventGrille extends Event{
    private Grille grille;
    public EventGrille(long date, Grille grille){
        super(date);
        this.grille = grille;
    }
    public void execute(){
         this.grille.translate();
    }
}