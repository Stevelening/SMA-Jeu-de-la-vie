public class TestImmigrationSimulator {
    public static void main ( String [] args ){
        int nbetats = 4;
        String[] colorSample = {"#ffffff", "#808080", "#696969", "#000000"};
        ImmigrationSimulator simulator = new ImmigrationSimulator(nbetats, colorSample);
        simulator.draw();
    }
}
