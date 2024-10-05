public class EventBoids extends Event{
    private BoidsWorld world;
    public EventBoids(long date, BoidsWorld world){
        super(date);
        this.world = world;
    }
    public void execute(){
        for(Boids population : this.world.getBoidsList()){
            if(population.getType()==Boids.enumType.PREDATOR)
                population.update(this.world);
            else
                population.update();
        }
    }   
}
