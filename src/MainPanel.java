import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainPanel extends JPanel {
    BufferedImage bufferedImage;
    BufferedImage bufferedImagebg;
    BufferedImage blackPieceNotHighlighted;
    BufferedImage whitePieceNotHighlighted;
    BufferedImage blackPieceHighlight;
    BufferedImage whitePieceHighlight;
    BufferedImage blackPiece = whitePieceNotHighlighted;
    BufferedImage whitePiece = blackPieceNotHighlighted;

    int X;
    int Y;
    int movedFrom;
    int clickedPiece;
    boolean flag;
    String turn;

    ArrayList<Piece> pieces = new ArrayList<>();
    ArrayList<Piece>possiblePieces = new ArrayList<>();
    ArrayList<Place> piecesInPlace = new ArrayList<>();//according to the white place numbers
    Map <Integer , BoardCordinates> blackCordinator = new HashMap<Integer, BoardCordinates>();
    Map <Integer, BoardCordinates> whiteCordinator = new HashMap<Integer, BoardCordinates>();

    Dice dice;
    Dice dice1;
    Dice dice2;
    Dice dice3;
    public MainPanel() {



        //15 black beads
        pieces.add(null);
        pieces.add(new Piece(137, 465, true, "black"));
        pieces.add(new Piece(137, 420, true, "black"));
        pieces.add(new Piece(137, 375, true, "black"));
        pieces.add(new Piece(137, 330, true, "black"));
        pieces.add(new Piece(137, 285, true, "black"));

        pieces.add(new Piece(506, 28, true, "black"));
        pieces.add(new Piece(506, 73, true, "black"));
        pieces.add(new Piece(506, 118, true, "black"));
        pieces.add(new Piece(506, 163, true, "black"));
        pieces.add(new Piece(506, 208, true, "black"));

        pieces.add(new Piece(348, 28, true, "black"));
        pieces.add(new Piece(348, 73, true, "black"));
        pieces.add(new Piece(348, 118, true, "black"));

        pieces.add(new Piece(772, 465, true, "black"));
        pieces.add(new Piece(772, 420, true, "black"));

        //15 white beads
        pieces.add(new Piece(137, 28, true, "white"));
        pieces.add(new Piece(137, 73, true, "white"));
        pieces.add(new Piece(137, 118, true, "white"));
        pieces.add(new Piece(137, 163, true, "white"));
        pieces.add(new Piece(137, 208, true, "white"));

        pieces.add(new Piece(506, 465, true, "white"));
        pieces.add(new Piece(506, 420, true, "white"));
        pieces.add(new Piece(506, 375, true, "white"));
        pieces.add(new Piece(506, 330, true, "white"));
        pieces.add(new Piece(506, 285, true, "white"));

        pieces.add(new Piece(348, 465, true, "white"));
        pieces.add(new Piece(348, 420, true, "white"));
        pieces.add(new Piece(348, 375, true, "white"));

        pieces.add(new Piece(772, 28, true, "white"));
        pieces.add(new Piece(772, 73, true, "white"));

        //pieces in plsces
        piecesInPlace.add(0, null);
        piecesInPlace.add(1, new Place(2, "black"));
        piecesInPlace.add(2, new Place(0, null));
        piecesInPlace.add(3, new Place(0, null));
        piecesInPlace.add(4, new Place(0, null));
        piecesInPlace.add(5, new Place(0, null));
        piecesInPlace.add(6, new Place(5, "white"));
        piecesInPlace.add(7, new Place(0, null));
        piecesInPlace.add(8, new Place(3, "white"));
        piecesInPlace.add(9, new Place(0, null));
        piecesInPlace.add(10, new Place(0, null));
        piecesInPlace.add(11, new Place(0, null));
        piecesInPlace.add(12, new Place(5, "black"));
        piecesInPlace.add(13, new Place(5, "white"));
        piecesInPlace.add(14, new Place(0, null));
        piecesInPlace.add(15, new Place(0, null));
        piecesInPlace.add(16, new Place(0, null));
        piecesInPlace.add(17, new Place(3, "black"));
        piecesInPlace.add(18, new Place(0, null));
        piecesInPlace.add(19, new Place(5, "black"));
        piecesInPlace.add(20, new Place(0, null));
        piecesInPlace.add(21, new Place(0, null));
        piecesInPlace.add(22, new Place(0, null));
        piecesInPlace.add(23, new Place(0, null));
        piecesInPlace.add(24, new Place(2, "white"));

        //cordinates for black
        blackCordinator.put(1, new BoardCordinates(749, 795, true));
        blackCordinator.put(2, new BoardCordinates(695, 742, true));
        blackCordinator.put(3, new BoardCordinates(643, 689, true));
        blackCordinator.put(4, new BoardCordinates(590, 636, true));
        blackCordinator.put(5, new BoardCordinates(537, 583, true));
        blackCordinator.put(6, new BoardCordinates(483, 529, true));
        blackCordinator.put(7, new BoardCordinates(379, 425, true));
        blackCordinator.put(8, new BoardCordinates(325, 371, true));
        blackCordinator.put(9, new BoardCordinates(272, 318, true));
        blackCordinator.put(10, new BoardCordinates(220, 266,true));
        blackCordinator.put(11, new BoardCordinates(167, 213, true));
        blackCordinator.put(12, new BoardCordinates(114, 160, true));
        blackCordinator.put(24, new BoardCordinates(749, 795, false));
        blackCordinator.put(23, new BoardCordinates(696, 742, false));
        blackCordinator.put(22, new BoardCordinates(643, 689, false));
        blackCordinator.put(21, new BoardCordinates(590, 636, false));
        blackCordinator.put(20, new BoardCordinates(537, 583, false));
        blackCordinator.put(19, new BoardCordinates(483, 529, false));
        blackCordinator.put(18, new BoardCordinates(379, 425, false));
        blackCordinator.put(17, new BoardCordinates(325, 371, false));
        blackCordinator.put(16, new BoardCordinates(272, 318, false));
        blackCordinator.put(15, new BoardCordinates(220, 266, false));
        blackCordinator.put(14, new BoardCordinates(167, 213, false));
        blackCordinator.put(13, new BoardCordinates(114, 160, false));

        //cordinates for white
        whiteCordinator.put(1, new BoardCordinates(749, 795, false));
        whiteCordinator.put(2, new BoardCordinates(696, 742, false));
        whiteCordinator.put(3, new BoardCordinates(643, 689, false));
        whiteCordinator.put(4, new BoardCordinates(590, 636, false));
        whiteCordinator.put(5, new BoardCordinates(537, 583, false));
        whiteCordinator.put(6, new BoardCordinates(483, 529, false));
        whiteCordinator.put(7, new BoardCordinates(379, 425, false));
        whiteCordinator.put(8, new BoardCordinates(325, 371, false));
        whiteCordinator.put(9, new BoardCordinates(272, 318, false));
        whiteCordinator.put(10, new BoardCordinates(220, 266, false));
        whiteCordinator.put(11, new BoardCordinates(167, 213, false));
        whiteCordinator.put(12, new BoardCordinates(114, 160, false));
        whiteCordinator.put(24, new BoardCordinates(749, 795, true));
        whiteCordinator.put(23, new BoardCordinates(696, 742, true));
        whiteCordinator.put(22, new BoardCordinates(643, 689, true));
        whiteCordinator.put(21, new BoardCordinates(590, 636, true));
        whiteCordinator.put(20, new BoardCordinates(537, 583, true));
        whiteCordinator.put(19, new BoardCordinates(483, 529, true));
        whiteCordinator.put(18, new BoardCordinates(379, 425, true));
        whiteCordinator.put(17, new BoardCordinates(325, 371, true));
        whiteCordinator.put(16, new BoardCordinates(272, 318, true));
        whiteCordinator.put(15, new BoardCordinates(220, 266, true));
        whiteCordinator.put(14, new BoardCordinates(167, 213, true));
        whiteCordinator.put(13, new BoardCordinates(114, 160, true));

        setLayout(null);
        try {
            bufferedImagebg = ImageIO.read(new File("photos/1.jpg"));
            bufferedImage = ImageIO.read(new File("photos/background.png"));
            blackPieceNotHighlighted = ImageIO.read(new File("photos/black_piece.png"));
            whitePieceNotHighlighted = ImageIO.read(new File("photos/white_piece.png"));
            blackPieceHighlight = ImageIO.read(new File("photos/black_piece_highlighted.png"));
            whitePieceHighlight = ImageIO.read(new File("photos/white_piece_highlighted.png"));


        } catch (IOException ex) {
            ex.printStackTrace();
        }
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if ((dice.getMaxX() > e.getX() & dice.getMinX() < e.getX() & dice.getMaxY() > e.getY() & dice.getMinY() < e.getY())|(dice1.getMaxX() > e.getX() & dice1.getMinX() < e.getX() & dice1.getMaxY() > e.getY() & dice1.getMinY() < e.getY())) {
                    dice.randomGenerator();
                    dice1.randomGenerator();
                    repaint();
                }
                                                                        //!!!after each move, possible positions should become null
                turn = "white";
                if (turn == "white") {
                    for (int i = 1; i < 25; i++) {

                        if ((i-dice.getRand()>0 && !(piecesInPlace.get(i - dice.getRand()).color == "black")) || (i-dice1.getRand()>0 && !(piecesInPlace.get(i - dice1.getRand()).color == "black"))) { //move without hit
                            for (int j = 16; j < 31; j++) {
                                if (pieces.get(j).x == whiteCordinator.get(i).xcordinateMean) {
                                    possiblePieces.add(pieces.get(j));
                                    pieces.get(j).possible_positions_x.add(whiteCordinator.get(i).xcordinateMean);
                                }
                            }
                        }
                    }
                }

                // TODO: 6/28/2019 add showing possible pieces
//                for (int i = 1; i < 31; i++) {
//                    if (!(pieces.get(i).possible_positions_x==null) | !(piece.possible_positions_hit_x==null))
//                        highlighit it!!
//                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                clickedPiece = 0;
                for (int i = 1; i < 31; i++) {

                    //update pieces in place for moved piece
                    // TODO: 6/28/2019 find possible positions of clicked piece

                    flag = false;
                    if (pieces.get(i).x < e.getX() && pieces.get(i).y < e.getY() && pieces.get(i).x + 46 > e.getX() && pieces.get(i).y + 46 > e.getY()) {

                        clickedPiece = i;

                        for (int j = 1; j <25 ; j++) {
                            if (whiteCordinator.get(j).xcordinateBegin < e.getX() && whiteCordinator.get(j).xcordinateEnd > e.getX()) {
                                movedFrom = j;
                                break;
                            }
                        }

                        flag = true;                //means that a piece has been clicked
                        break;
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (flag && e.getY() < 490) {                           //a piece has been clicked and now it is releasing in the board
                    if (pieces.get(clickedPiece).color == "black") {
                        for (Integer entry : blackCordinator.keySet()) {
                            if (blackCordinator.get(entry).xcordinateBegin < e.getX() && blackCordinator.get(entry).xcordinateEnd > e.getX()) {
                                pieces.get(clickedPiece).oldx = pieces.get(clickedPiece).x;
                                pieces.get(clickedPiece).x = blackCordinator.get(entry).xcordinateMean;

                                if (piecesInPlace.get(movedFrom).number > 0)
                                    piecesInPlace.get(movedFrom).number--;
                                if (piecesInPlace.get(movedFrom).number == 0)
                                    piecesInPlace.get(movedFrom).color = null;

                                if(e.getY()<245) {
                                    pieces.get(clickedPiece).oldy = pieces.get(clickedPiece).y;
                                    pieces.get(clickedPiece).y = piecesInPlace.get(25-entry).number * 45 + 28;
                                }
                                else {
                                    pieces.get(clickedPiece).oldy = pieces.get(clickedPiece).y;
                                    pieces.get(clickedPiece).y = 465 - piecesInPlace.get(25-entry).number * 45;
                                }

                                piecesInPlace.get(entry).number++;
                                piecesInPlace.get(entry).color = "black";
                                repaint();
                                break;
                            }

                        }
                    }
                    if (pieces.get(clickedPiece).color == "white") {
                        for (Integer entry : whiteCordinator.keySet()) {
                            if (whiteCordinator.get(entry).xcordinateBegin < e.getX() && whiteCordinator.get(entry).xcordinateEnd > e.getX()) {
                                pieces.get(clickedPiece).oldx = pieces.get(clickedPiece).x;
                                pieces.get(clickedPiece).x = whiteCordinator.get(entry).xcordinateMean;

                                if (piecesInPlace.get(movedFrom).number > 0)
                                    piecesInPlace.get(movedFrom).number--;
                                if (piecesInPlace.get(movedFrom).number == 0)
                                    piecesInPlace.get(movedFrom).color = null;

                                if(e.getY()<245) {
                                    pieces.get(clickedPiece).oldy = pieces.get(clickedPiece).y;
                                    pieces.get(clickedPiece).y = piecesInPlace.get(entry).number * 45 + 28;
                                }
                                else {
                                    pieces.get(clickedPiece).oldy = pieces.get(clickedPiece).y;
                                    pieces.get(clickedPiece).y = 465 - piecesInPlace.get(entry).number * 45;
                                }

                                piecesInPlace.get(entry).number++;
                                piecesInPlace.get(entry).color = "white";
                                repaint();
                                break;
                            }

                        }

                    }
                }
                for (int i = 1; i < 25; i++) {
                    System.out.println(piecesInPlace.get(i).number + ";;;");

                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        dice =  new Dice(50, 700);
        dice1 = new Dice(100,700);
        dice2 = new Dice(150,700);
        dice3 = new Dice(200,700);

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(bufferedImagebg, 0, 0, null);
        g.drawImage(bufferedImage, 0, 0, null);
        g.setColor(Color.BLACK);
        dice.paint((Graphics2D) g);
        dice1.paint((Graphics2D) g);
        if(dice.getRand()==dice1.getRand()){
            dice2.setRand(dice.getRand());
            dice3.setRand(dice1.getRand());
            dice2.paint((Graphics2D) g);
            dice3.paint((Graphics2D) g);


        }



        for (int i = 1; i < 31; i++) {

            if(pieces.get(i).color == "black"){
                g.drawImage(blackPieceNotHighlighted, pieces.get(i).x, pieces.get(i).y, null);
            }
            else{
                g.drawImage(whitePieceNotHighlighted, pieces.get(i).x, pieces.get(i).y, null);
            }
        }

    }

}
