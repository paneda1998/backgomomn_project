import java.awt.*;
import java.util.ArrayList;

public class Piece {
    int x;
    int y;
    int oldx;
    int oldy;
    int z;
    boolean ingame;
    String  color;
    ArrayList possible_positions_x = new ArrayList<Integer>();
    ArrayList possible_positions_y = new ArrayList<Integer>();
    ArrayList possible_positions_hit_x = new ArrayList<Integer>();
    ArrayList possible_positions_hit_y = new ArrayList<Integer>();

    public Piece(int x, int y, boolean ingame, String color) {
        this.x = x;
        this.y = y;
        this.ingame = ingame;
        this.color = color;

    }
// public viod DrawCircle(Graphics g){


    //  }
}
