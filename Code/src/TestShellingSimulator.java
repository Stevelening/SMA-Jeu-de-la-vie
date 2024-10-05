public class TestShellingSimulator {
    public static void main ( String [] args ){
        int nbcolors = 4;
        int k = 5; // le seuil de tolerance
        // on a une segregation a partir de k = 5
        String[] colorSample = {"#f00020", "#808080", "#1f77b4", "#000000"};        
        ShellingSimulator simulator = new ShellingSimulator(nbcolors, colorSample, k);
        simulator.draw();
    }
}
