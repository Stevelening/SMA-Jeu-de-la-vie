
public class TestBalls {
    public static void main(String[] args) {
        System.out.println("** TestBalls **");
        Balls ball = new Balls();
        System.out.println(ball.toString());
        ball.translate(5, 0);
        System.out.println(ball.toString());
        ball.translate(0, 2);
        System.out.println(ball.toString());
        ball.translate(10, 25);
        System.out.println(ball.toString());
        ball.reInit();
        System.out.println(ball.toString());
    }
}
