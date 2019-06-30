import java.awt.*;
import java.util.Random;
public class Dice implements Animatable{
    private int rand;
    private int x;
    private int y;
    private Color color;
    Random random = new Random();
    public Dice(int x , int y){
        this.x = x;
        this.y = y;
        randomGenerator();
        color=Color.BLACK;
    }
    public void randomGenerator(){
        rand = random.nextInt(6)+1;
        //System.out.println(rand);
    }

    @Override
    public void paint(Graphics2D graphics2D) {
        graphics2D.setColor(color);
        graphics2D.fillRect(x - 17,y - 35,50,50);
        graphics2D.setColor(Color.CYAN);
        graphics2D.setFont(new Font("test" , Font.BOLD , 30));
        graphics2D.drawString(Integer.toString(rand),x,y);
    }

    public int getMaxX() {
        return x + 33;
    }

    public int getMaxY() {
        return y + 20;
    }
    public int getMinX() {
        return x - 17;
    }

    public int getMinY() {
        return y -30;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getRand() {
        return rand;
    }

    public void setRand(int rand) {
        this.rand = rand;
    }
}

