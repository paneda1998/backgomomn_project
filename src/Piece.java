import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Piece implements Animatable{
    int x;
    int y;
    int oldx;
    int oldy;
    boolean ingame;
    BufferedImage pieceImage;
    String  color;
    Boolean[] possibleCordinates = new Boolean[24];
    Boolean[] possibleCordinatesHit = new Boolean[24];

    public Piece(int x, int y, boolean ingame, String color, BufferedImage pieceImage) {
        this.x = x;
        this.y = y;
        this.ingame = ingame;
        this.color = color;
        this.pieceImage = pieceImage;
    }
    @Override
    public void paint(Graphics2D graphics2D){
        graphics2D.drawImage(pieceImage, x, y, null);
    }
}
