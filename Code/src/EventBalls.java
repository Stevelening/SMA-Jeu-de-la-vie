public class EventBalls extends Event{
    private Balls balls;
    private int xDep ;

    public EventBalls(long date, Balls balls){
        super(date);
        this.balls = balls;
    }
    public void execute(){this.balls.translate(this.xDep , 0);}

    public int getxDep() {
        return xDep;
    }
    public void setxDep(int xDep) {
        this.xDep = xDep;
    }
}