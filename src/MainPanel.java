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

    int X;
    int Y;
    int movedFrom;
    int clickedPiece;
    boolean flag;
    int turn;// 1 for white
    int tt=0;
    int player1DiceSum=0;
    int player2DiceSum=0;
    ArrayList<Piece> pieces = new ArrayList<>();
    Boolean[] possiblePieces = new Boolean[31];
    int[] whiteInPlace= new int[25];
    int[] blackInPlace= new int[25];

    BoardCordinates[] cordinator = new BoardCordinates[25];
    int temp;

    Dice dice;
    Dice dice1;
    Dice dice2;
    Dice dice3;
    public MainPanel() {



        //15 black beads
        pieces.add(null);
        pieces.add(new Piece(137, 465, true, "black", blackPieceNotHighlighted));
        pieces.add(new Piece(137, 420, true, "black", blackPieceNotHighlighted));
        pieces.add(new Piece(137, 375, true, "black", blackPieceNotHighlighted));
        pieces.add(new Piece(137, 330, true, "black", blackPieceNotHighlighted));
        pieces.add(new Piece(137, 285, true, "black", blackPieceNotHighlighted));

        pieces.add(new Piece(506, 28, true, "black", blackPieceNotHighlighted));
        pieces.add(new Piece(506, 73, true, "black", blackPieceNotHighlighted));
        pieces.add(new Piece(506, 118, true, "black", blackPieceNotHighlighted));
        pieces.add(new Piece(506, 163, true, "black", blackPieceNotHighlighted));
        pieces.add(new Piece(506, 208, true, "black", blackPieceNotHighlighted));

        pieces.add(new Piece(348, 28, true, "black", blackPieceNotHighlighted));
        pieces.add(new Piece(348, 73, true, "black", blackPieceNotHighlighted));
        pieces.add(new Piece(348, 118, true, "black", blackPieceNotHighlighted));

        pieces.add(new Piece(772, 465, true, "black", blackPieceNotHighlighted));
        pieces.add(new Piece(772, 420, true, "black", blackPieceNotHighlighted));

        //15 white beads
        pieces.add(new Piece(137, 28, true, "white", whitePieceNotHighlighted));
        pieces.add(new Piece(137, 73, true, "white", whitePieceNotHighlighted));
        pieces.add(new Piece(137, 118, true, "white", whitePieceNotHighlighted));
        pieces.add(new Piece(137, 163, true, "white", whitePieceNotHighlighted));
        pieces.add(new Piece(137, 208, true, "white", whitePieceNotHighlighted));

        pieces.add(new Piece(506, 465, true, "white", whitePieceNotHighlighted));
        pieces.add(new Piece(506, 420, true, "white", whitePieceNotHighlighted));
        pieces.add(new Piece(506, 375, true, "white", whitePieceNotHighlighted));
        pieces.add(new Piece(506, 330, true, "white", whitePieceNotHighlighted));
        pieces.add(new Piece(506, 285, true, "white", whitePieceNotHighlighted));

        pieces.add(new Piece(348, 465, true, "white", whitePieceNotHighlighted));
        pieces.add(new Piece(348, 420, true, "white", whitePieceNotHighlighted));
        pieces.add(new Piece(348, 375, true, "white", whitePieceNotHighlighted));

        pieces.add(new Piece(772, 28, true, "white", whitePieceNotHighlighted));
        pieces.add(new Piece(772, 73, true, "white", whitePieceNotHighlighted));

        //pieces in plsces
        whiteInPlace[1] = 0;    whiteInPlace[13] = 5;   blackInPlace[13] = 0;   blackInPlace[1] = 2;
        whiteInPlace[2] = 0;    whiteInPlace[14] = 0;   blackInPlace[14] = 0;   blackInPlace[2] = 0;
        whiteInPlace[3] = 0;    whiteInPlace[15] = 0;   blackInPlace[15] = 0;   blackInPlace[3] = 0;
        whiteInPlace[4] = 0;    whiteInPlace[16] = 0;   blackInPlace[16] = 0;   blackInPlace[4] = 0;
        whiteInPlace[5] = 0;    whiteInPlace[17] = 0;   blackInPlace[17] = 3;   blackInPlace[5] = 0;
        whiteInPlace[6] = 5;    whiteInPlace[18] = 0;   blackInPlace[18] = 0;   blackInPlace[6] = 0;
        whiteInPlace[7] = 0;    whiteInPlace[19] = 0;   blackInPlace[19] = 5;   blackInPlace[7] = 0;
        whiteInPlace[8] = 3;    whiteInPlace[20] = 0;   blackInPlace[20] = 0;   blackInPlace[8] = 0;
        whiteInPlace[9] = 0;    whiteInPlace[21] = 0;   blackInPlace[21] = 0;   blackInPlace[9] = 0;
        whiteInPlace[10] = 0;   whiteInPlace[22] = 0;   blackInPlace[22] = 0;   blackInPlace[10] = 0;
        whiteInPlace[11] = 0;   whiteInPlace[23] = 0;   blackInPlace[23] = 0;   blackInPlace[11] = 0;
        whiteInPlace[12] = 0;   whiteInPlace[24] = 2;   blackInPlace[24] = 0;   blackInPlace[12] = 5;

        //cordinates according to white
        cordinator[0]=null;
        cordinator[1] = new BoardCordinates(768, 820, false);
        cordinator[2] = new BoardCordinates(714, 768, false);
        cordinator[3] = new BoardCordinates(660, 714, false);
        cordinator[4] = new BoardCordinates(608, 660, false);
        cordinator[5] = new BoardCordinates(555, 608, false);
        cordinator[6] = new BoardCordinates(502, 555, false);
        cordinator[7] = new BoardCordinates(395, 448, false);
        cordinator[8] = new BoardCordinates(343, 395, false);
        cordinator[9] = new BoardCordinates(289, 343, false);
        cordinator[10] = new BoardCordinates(238, 289, false);
        cordinator[11] = new BoardCordinates(185, 238, false);
        cordinator[12] = new BoardCordinates(133, 185, false);
        cordinator[24] = new BoardCordinates(768, 820, true);
        cordinator[23] = new BoardCordinates(714, 768, true);
        cordinator[22] = new BoardCordinates(660, 714, true);
        cordinator[21] = new BoardCordinates(608, 660, true);
        cordinator[20] = new BoardCordinates(555, 608, true);
        cordinator[19] = new BoardCordinates(502, 555, true);
        cordinator[18] = new BoardCordinates(395, 448, true);
        cordinator[17] = new BoardCordinates(343, 395, true);
        cordinator[16] = new BoardCordinates(289, 343, true);
        cordinator[15] = new BoardCordinates(238, 289, true);
        cordinator[14] = new BoardCordinates(185, 238, true);
        cordinator[13] = new BoardCordinates(133, 185, true);

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
                //System.out.println(e.getY());

                if ((dice.getMaxX() > e.getX() & dice.getMinX() < e.getX() & dice.getMaxY() > e.getY() & dice.getMinY() < e.getY()) | (dice1.getMaxX() > e.getX() & dice1.getMinX() < e.getX() & dice1.getMaxY() > e.getY() & dice1.getMinY() < e.getY())) {
                    dice.randomGenerator();
                    dice1.randomGenerator();
                    repaint();
                    if (tt == 1)
                        turn *= -1;
                    if (dice.getRand() != dice1.getRand()) {
                        if (turn == 1)
                            player1DiceSum += dice.getRand() + dice1.getRand();
                        if (turn == -1)
                            player2DiceSum += dice.getRand() + dice1.getRand();
                    } else {
                        if (turn == 1)
                            player1DiceSum += 4 * dice.getRand();
                        if (turn == -1)
                            player2DiceSum += 4 * dice.getRand();
                    }
                    tt = 1;
                    System.out.println(turn);
                }
                //!!!after each move, possible positions should become null

                // TODO: 6/28/2019 add showing possible pieces
                if (dice.getBegin() == 1 ){
                    if(turn ==1) {
                        for (int i = 1; i < 25; i++) {
                            if ((dice.active && i - dice.getRand() > 0 && blackInPlace[i - dice.getRand()] == 0)) {//sharte harekat sefid bedone zadan
                                for (int j = 16; j < 31; j++) {     //the piece is in the selected cordinate                    //the piece is the topest in the cordinate
                                    if (pieces.get(j).x + 10 < cordinator[i].xcordinateEnd && pieces.get(j).x + 10 > cordinator[i].xcordinateBegin && (((46 * (whiteInPlace[i] - 1) + 28 - 10 < pieces.get(j).y && pieces.get(j).y < 46 * (whiteInPlace[i] - 1) + 28 + 10)) || ((464 - 46 * (whiteInPlace[i] - 1) - 10 < pieces.get(j).y && pieces.get(j).y < 464 - 46 * (whiteInPlace[i] - 1) + 10)))) {
                                        possiblePieces[j] = true;
                                        // System.out.println(i);
                                        //System.out.println(j);
                                        pieces.get(j).possibleCordinates[i - dice.getRand()] = true;
                                    }
                                }

                            }
                            if (dice1.active && i - dice1.getRand() > 0 && blackInPlace[i - dice1.getRand()] == 0) {
                                for (int j = 16; j < 31; j++) {     //the piece is in the selected cordinate                    //the piece is the topest in the cordinate
                                    if (pieces.get(j).x + 10 < cordinator[i].xcordinateEnd && pieces.get(j).x + 10 > cordinator[i].xcordinateBegin && (((46 * (whiteInPlace[i] - 1) + 28 - 10 < pieces.get(j).y && pieces.get(j).y < 46 * (whiteInPlace[i] - 1) + 28 + 10)) || ((464 - 46 * (whiteInPlace[i] - 1) - 10 < pieces.get(j).y && pieces.get(j).y < 464 - 46 * (whiteInPlace[i] - 1) + 10)))) {
                                        possiblePieces[j] = true;
                                        pieces.get(j).possibleCordinates[i - dice1.getRand()] = true;
                                    }
                                }

                            }
                        }
                    }

                    else {
                        for (int i = 1; i < 25; i++) {
                            if ((dice.active && i + dice.getRand() < 25 && whiteInPlace[i + dice.getRand()] == 0)) {//sharte harekat sefid bedone zadan
                                for (int j = 1; j < 16; j++) {     //the piece is in the selected cordinate                    //the piece is the topest in the cordinate
                                    if (pieces.get(j).x + 10 < cordinator[i].xcordinateEnd && pieces.get(j).x + 10 > cordinator[i].xcordinateBegin && (((46 * (blackInPlace[i] - 1) + 28 - 10 < pieces.get(j).y && pieces.get(j).y < 46 * (blackInPlace[i] - 1) + 28 + 10)) || ((464 - 46 * (blackInPlace[i] - 1) - 10 < pieces.get(j).y && pieces.get(j).y < 464 - 46 * (blackInPlace[i] - 1) + 10)))) {
                                        possiblePieces[j] = true;
                                        // System.out.println(i);
                                        //System.out.println(j);
                                        pieces.get(j).possibleCordinates[i + dice.getRand()] = true;
                                    }
                                }

                            }
                            if ((dice1.active && i + dice1.getRand() < 25 && whiteInPlace[i + dice1.getRand()] == 0)) {//sharte harekat sefid bedone zadan
                                for (int j = 1; j < 16; j++) {     //the piece is in the selected cordinate                    //the piece is the topest in the cordinate
                                    if (pieces.get(j).x + 10 < cordinator[i].xcordinateEnd && pieces.get(j).x + 10 > cordinator[i].xcordinateBegin && (((46 * (blackInPlace[i] - 1) + 28 - 10 < pieces.get(j).y && pieces.get(j).y < 46 * (blackInPlace[i] - 1) + 28 + 10)) || ((464 - 46 * (blackInPlace[i] - 1) - 10 < pieces.get(j).y && pieces.get(j).y < 464 - 46 * (blackInPlace[i] - 1) + 10)))) {
                                        possiblePieces[j] = true;
                                        // System.out.println(i);
                                        //System.out.println(j);
                                        pieces.get(j).possibleCordinates[i + dice1.getRand()] = true;
                                    }
                                }

                            }
                        }
                    }


                }
                for (int i = 1; i <31 ; i++) {
                    //for (int j = 1; j <25; j++) {
                        System.out.println(possiblePieces[i]);
                    //}
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                clickedPiece = 0;
                for (int i = 1; i < 31; i++) {
                    flag = false;
                    // TODO: 6/28/2019 find possible positions of clicked piece
                    if (pieces.get(i).x < e.getX() && pieces.get(i).y < e.getY() && pieces.get(i).x + 46 > e.getX() && pieces.get(i).y + 46 > e.getY()) {
                        clickedPiece = i;
                        for (int j = 1; j <25 ; j++) {
                            if (cordinator[j].xcordinateBegin < e.getX() && cordinator[j].xcordinateEnd > e.getX() && ((e.getY()<278 && cordinator[j].top)||(e.getY()>278 && !cordinator[j].top))) {
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
                if (flag && e.getY() < 520) {                           //a piece has been clicked and now it is releasing in the board
                    for (int i = 1; i <25 ; i++) {
                        if (cordinator[i].xcordinateBegin < e.getX() && cordinator[i].xcordinateEnd > e.getX() && ((e.getY() < 278 && cordinator[i].top) || (e.getY() > 278 && !cordinator[i].top))) {
                            pieces.get(clickedPiece).oldx = pieces.get(clickedPiece).x;
                            pieces.get(clickedPiece).x = cordinator[i].xcordinateBegin+5;
                            temp = blackInPlace[i] + whiteInPlace[i];
                            pieces.get(clickedPiece).oldy = pieces.get(clickedPiece).y;
                            if (cordinator[i].top)
                                pieces.get(clickedPiece).y = 46*temp + 28;

                            else if (!cordinator[i].top)
                                pieces.get(clickedPiece).y = 510 - 46*temp - 46;

                            if (pieces.get(clickedPiece).color == "white"){
                                whiteInPlace[movedFrom]--;
                                whiteInPlace[i]++;
                            }
                            else if (pieces.get(clickedPiece).color == "black"){
                                blackInPlace[movedFrom]--;
                                blackInPlace[i]++;
                            }
                        }
                    }
                }
                repaint();
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
       // g.drawString("ssss",450,278);

        while(dice.getBegin()==0){
            if(dice.getRand()>dice1.getRand()){
                turn=1;
                dice.setBegin(1);

            }
            else if(dice.getRand()<dice1.getRand()){
                turn=-1;
                dice.setBegin(1);

            }
            else {
                dice.randomGenerator();
                dice1.randomGenerator();
                repaint();
            }
        }
        // System.out.print(playerturn);

        if(dice.getBegin()==1&&dice.getRand()==dice1.getRand()){
            dice2.setRand(dice.getRand());
            dice3.setRand(dice1.getRand());
            dice2.paint((Graphics2D) g);
            dice3.paint((Graphics2D) g);
        }

        for (int i = 1; i < 31; i++) {
            if(pieces.get(i).color=="black")
                g.drawImage(blackPieceNotHighlighted, pieces.get(i).x, pieces.get(i).y, null);
            else if(pieces.get(i).color=="white")
                g.drawImage(whitePieceNotHighlighted, pieces.get(i).x, pieces.get(i).y, null);
        }

    }

}
