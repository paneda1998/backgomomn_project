import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.awt.Color.*;
import static java.lang.StrictMath.abs;

public class MainPanel extends JPanel {
    Polygon triangle = new Polygon();
    Polygon triangle1 = new Polygon();
    BufferedImage bufferedImage;
    BufferedImage bufferedImagebg;
    BufferedImage blackPieceNotHighlighted;
    BufferedImage whitePieceNotHighlighted;
    int whiteWin=15;
    int blackWin=15;
    int counter = 0;
    int whiteDice=0;
    int blackDice=0;
    boolean whiteEnd= false;
    boolean blackEnd=false;
    boolean[] extraCordinates = new boolean[25];
    int X;
    int Y;
    int fuck = 0;
    int movedFrom;
    int clickedPiece;
    boolean flag;
    int turn;// 1 for white
    int tt=0;
    int player1DiceSum=0;
    int player2DiceSum=0;
    int whitePieceOut;
    int blackPieceOut;
    ArrayList<Piece> pieces = new ArrayList<>();

    boolean[] possiblePieces = new boolean[31];
    boolean[] impossiblePieces = new boolean[31];

    boolean canMoveBlack;
    boolean canMoveWhite;

    int[] whiteInPlace= new int[25];
    int[] blackInPlace= new int[25];

    BoardCordinates[] cordinator = new BoardCordinates[25];
    int temp;

    Dice dice;
    Dice dice1;
    Dice dice2;
    Dice dice3;
    public MainPanel() {


        for (int i = 1; i <31 ; i++) {
            impossiblePieces[i]=true;
        }
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

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //System.out.println(e.getY());
//                System.out.println(e.getY());
//                System.out.println(e.getX());
                if ((dice.getMaxX() > e.getX() & dice.getMinX() < e.getX() & dice.getMaxY() > e.getY() & dice.getMinY() < e.getY()) | (dice1.getMaxX() > e.getX() & dice1.getMinX() < e.getX() & dice1.getMaxY() > e.getY() & dice1.getMinY() < e.getY())) {
                    dice.randomGenerator();
                    dice1.randomGenerator();
                    dice.active=true;
                    dice1.active=true;
                    dice2.active=true;
                    dice3.active=true;
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
                }
                if (turn==1 && dice.getRand()!=dice1.getRand())
                    whiteDice+=dice.getRand() + dice1.getRand();
                else if (turn==1 && dice.getRand()==dice1.getRand())
                    whiteDice+=dice.getRand()*4;
                if (turn==-1 && dice.getRand()!=dice1.getRand())
                    blackDice+=dice.getRand() + dice1.getRand();
                else if (turn==-1 && dice.getRand()==dice1.getRand())
                    blackDice+=dice.getRand()*4;
            }

            @Override
            public void mousePressed(MouseEvent e) {
                whiteEnd = false;
                blackEnd = false;

                if (dice.getBegin() == 1) {
                    for (int i = 1; i < 31; i++) {
                        possiblePieces[i] = false;
                        for (int j = 1; j < 25; j++) {
                            pieces.get(i).possibleCordinates[j] = false;
                        }
                    }
                    if (turn == 1) {
                        for (int i = 1; i < 25; i++) {
                            if ((dice.active && i - dice.getRand() > 0 && blackInPlace[i - dice.getRand()] < 2)) {//sharte harekat sefid bedone zadan
                                for (int j = 16; j < 31; j++) {     //the piece is in the selected cordinate// the piece is the topest in the cordinate
                                    int k = whiteInPlace[i];
                                    if (whiteInPlace[i] > 5) k = 5;
                                    if (pieces.get(j).x + 20 < cordinator[i].xcordinateEnd && pieces.get(j).x + 20 > cordinator[i].xcordinateBegin && (((46 * (k - 1) + 28 - 20 < pieces.get(j).y && pieces.get(j).y < 46 * (k - 1) + 28 + 20)) || ((464 - 46 * (k - 1) - 20 < pieces.get(j).y && pieces.get(j).y < 464 - 46 * (k - 1) + 20)))) {
                                        possiblePieces[j] = true;
                                        pieces.get(j).possibleCordinates[i - dice.getRand()] = true;
                                    }
                                }
                            }
                            if (dice1.active && i - dice1.getRand() > 0 && blackInPlace[i - dice1.getRand()] < 2) {
                                for (int j = 16; j < 31; j++) {     //the piece is in the selected cordinate                    //the piece is the topest in the cordinate
                                    int k = whiteInPlace[i];
                                    if (whiteInPlace[i] > 5) k = 5;
                                    if (pieces.get(j).x + 20 < cordinator[i].xcordinateEnd && pieces.get(j).x + 20 > cordinator[i].xcordinateBegin && (((46 * (k - 1) + 28 - 20 < pieces.get(j).y && pieces.get(j).y < 46 * (k - 1) + 28 + 20)) || ((464 - 46 * (k - 1) - 20 < pieces.get(j).y && pieces.get(j).y < 464 - 46 * (k - 1) + 20)))) {
                                        possiblePieces[j] = true;
                                        pieces.get(j).possibleCordinates[i - dice1.getRand()] = true;
                                    }
                                }
                            }
                        }
                    } else {
                        for (int i = 1; i < 25; i++) {
                            if ((dice.active && i + dice.getRand() < 25 && whiteInPlace[i + dice.getRand()] < 2)) {//sharte harekat sefid bedone zadan
                                for (int j = 1; j < 16; j++) {     //the piece is in the selected cordinate                    //the piece is the topest in the cordinate
                                    int k = blackInPlace[i];
                                    if (blackInPlace[i] > 5) k = 5;
                                    if (pieces.get(j).x + 20 < cordinator[i].xcordinateEnd && pieces.get(j).x + 20 > cordinator[i].xcordinateBegin && (((46 * (k - 1) + 28 - 20 < pieces.get(j).y && pieces.get(j).y < 46 * (k - 1) + 28 + 20)) || ((464 - 46 * (k - 1) - 20 < pieces.get(j).y && pieces.get(j).y < 464 - 46 * (k - 1) + 20)))) {
                                        possiblePieces[j] = true;
                                        // System.out.println(i);
                                        //System.out.println(j);
                                        pieces.get(j).possibleCordinates[i + dice.getRand()] = true;
                                    }
                                }
                            }
                            if ((dice1.active && i + dice1.getRand() < 25 && whiteInPlace[i + dice1.getRand()] < 2)) {//sharte harekat sefid bedone zadan
                                for (int j = 1; j < 16; j++) {     //the piece is in the selected cordinate                    //the piece is the topest in the cordinate
                                    int k = blackInPlace[i];
                                    if (blackInPlace[i] > 5) k = 5;
                                    if (pieces.get(j).x + 20 < cordinator[i].xcordinateEnd && pieces.get(j).x + 20 > cordinator[i].xcordinateBegin && (((46 * (k - 1) + 28 - 20 < pieces.get(j).y && pieces.get(j).y < 46 * (k - 1) + 28 + 20)) || ((464 - 46 * (k - 1) - 20 < pieces.get(j).y && pieces.get(j).y < 464 - 46 * (k - 1) + 20)))) {
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
                clickedPiece = 0;
                for (int i = 1; i < 31; i++) {
                    flag = false;
                    if (pieces.get(i).x < e.getX() && pieces.get(i).y < e.getY() && pieces.get(i).x + 46 > e.getX() && pieces.get(i).y + 46 > e.getY()) {
//                        if ((pieces.get(i).color=="white" && whitePieceOut==0)||(pieces.get(i).color=="black" && blackPieceOut==0)) {
                        clickedPiece = i;
                        for (int j = 1; j < 25; j++) {
                            if (cordinator[j].xcordinateBegin < e.getX() && cordinator[j].xcordinateEnd > e.getX() && ((e.getY() < 278 && cordinator[j].top) || (e.getY() > 278 && !cordinator[j].top))) {
                                movedFrom = j;

                                if (turn==1 && whitePieceOut != 0)
                                    for (int k = 1; k < 25; k++)
                                        pieces.get(clickedPiece).possibleCordinates[k]=false;

                                if (turn==-1 && blackPieceOut != 0)
                                    for (int k = 1; k < 25; k++)
                                        pieces.get(clickedPiece).possibleCordinates[k]=false;

                                if (turn==1 && whitePieceOut != 0)
                                    for (int k = 1; k < 25; k++)
                                        pieces.get(clickedPiece).possibleCordinates[k]=false;

                                for (int k = 1; k < 25; k++) {
                                    if (pieces.get(clickedPiece).possibleCordinates[k]) {
                                        if (cordinator[k].top) {
                                            triangle.addPoint(cordinator[k].xcordinateMean, 5 * 46 + 28);
                                            triangle.addPoint(cordinator[k].xcordinateBegin, 28);
                                            triangle.addPoint(cordinator[k].xcordinateEnd, 28);
                                        } else if (!cordinator[k].top) {
                                            triangle.addPoint(cordinator[k].xcordinateMean, 510 - 5 * 46);
                                            triangle.addPoint(cordinator[k].xcordinateBegin, 510);
                                            triangle.addPoint(cordinator[k].xcordinateEnd, 510);
                                        }
                                        temp = k;
                                        break;
                                    }
                                }
                                for (int k = temp + 1; k < 25; k++) {
                                    if (pieces.get(clickedPiece).possibleCordinates[k]) {
                                        if (cordinator[k].top) {
                                            triangle1.addPoint(cordinator[k].xcordinateMean, 5 * 46 + 28);
                                            triangle1.addPoint(cordinator[k].xcordinateBegin, 28);
                                            triangle1.addPoint(cordinator[k].xcordinateEnd, 28);
                                        } else if (!cordinator[k].top) {
                                            triangle1.addPoint(cordinator[k].xcordinateMean, 510 - 5 * 46);
                                            triangle1.addPoint(cordinator[k].xcordinateBegin, 510);
                                            triangle1.addPoint(cordinator[k].xcordinateEnd, 510);
                                        }
                                        break;
                                    }
                                }
                                repaint();
                                break;
                            }
                        }
                        if (turn == -1) {
                            counter = 0;
                            for (int k = 1; k < 16; k++) {
                                for (int j = 1; j < 25; j++) {
                                    if (pieces.get(k).possibleCordinates[j])
                                        counter++;
                                }
                            }
                            if (counter == 0)
                                canMoveBlack = false;
                        }
                        if (turn == 1) {
                            counter = 0;
                            for (int k = 16; k < 31; k++) {
                                for (int j = 1; j < 25; j++) {
                                    if (pieces.get(k).possibleCordinates[j])
                                        counter++;
                                }
                            }
                            if (counter == 0)
                                canMoveWhite = false;
                        }


                        flag = true;                //means that a piece has been clicked
                        break;
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                triangle.reset();
                triangle1.reset();

                if (turn == 1 && whiteInPlace[1]+whiteInPlace[2]+whiteInPlace[3]+whiteInPlace[4]+whiteInPlace[5]+whiteInPlace[6]==whiteWin){
                    whiteEnd = true;
                }
                else if(turn == -1 && blackInPlace[24]+blackInPlace[23]+blackInPlace[22]+blackInPlace[21]+blackInPlace[20]+blackInPlace[19]==blackWin){
                    blackEnd = true;
                }
                if (flag && e.getY() < 520) {                           //a piece has been clicked and now it is releasing in the board
                    if (true) {
                        for (int i = 1; i < 25; i++) {
                            if ((blackPieceOut != 0 && turn == -1) || (whitePieceOut != 0 && turn == 1)) {
                                fuck = 0;
                                if (turn == 1) {
                                    if (dice.active && i == 25 - dice.getRand() && blackInPlace[i] < 2 && (cordinator[i].xcordinateBegin < e.getX() && cordinator[i].xcordinateEnd > e.getX() && ((e.getY() < 278 && cordinator[i].top)))) {

                                        //System.out.println("vaaaay!");
                                        if (blackInPlace[i] == 1) {
                                            for (int j = 1; j < 16; j++) {
                                                if (pieces.get(j).x + 10 < cordinator[i].xcordinateEnd && pieces.get(j).x + 10 > cordinator[i].xcordinateBegin && ((pieces.get(j).y < 278 && cordinator[i].top) || (pieces.get(j).y > 278 && !cordinator[i].top))) { //
                                                    pieces.get(j).oldx = pieces.get(j).x;
                                                    pieces.get(j).x = 660;
                                                    pieces.get(j).oldy = pieces.get(j).y;
                                                    pieces.get(j).y = 700 - blackPieceOut * 46;
                                                    blackPieceOut++;
                                                    blackInPlace[i] = 0;
                                                }
                                            }
                                        }
                                        pieces.get(clickedPiece).oldx = pieces.get(clickedPiece).x;
                                        pieces.get(clickedPiece).oldy = pieces.get(clickedPiece).y;
                                        pieces.get(clickedPiece).x = cordinator[i].xcordinateBegin + 5;

                                        if (whiteInPlace[i] < 5)
                                            pieces.get(clickedPiece).y = 46 * whiteInPlace[i] + 28;
                                        else {
                                            pieces.get(clickedPiece).y = 46 * 4 + 28;
                                            extraCordinates[i] = true;
                                        }
                                        fuck = 1;
                                        dice.active = false;
                                    }
                                    if (dice1.active && i == 25 - dice1.getRand() && blackInPlace[i] < 2 && (cordinator[i].xcordinateBegin < e.getX() && cordinator[i].xcordinateEnd > e.getX() && ((e.getY() < 278 && cordinator[i].top)))) {
                                        if (blackInPlace[i] == 1) {
                                            for (int j = 1; j < 16; j++) {
                                                if (pieces.get(j).x + 10 < cordinator[i].xcordinateEnd && pieces.get(j).x + 10 > cordinator[i].xcordinateBegin && ((pieces.get(j).y < 278 && cordinator[i].top) || (pieces.get(j).y > 278 && !cordinator[i].top))) { //
                                                    pieces.get(j).oldx = pieces.get(j).x;
                                                    pieces.get(j).x = 660;
                                                    pieces.get(j).oldy = pieces.get(j).y;
                                                    pieces.get(j).y = 700 - blackPieceOut * 46;
                                                    blackPieceOut++;
                                                    blackInPlace[i] = 0;
                                                }
                                            }
                                        }
                                        pieces.get(clickedPiece).oldx = pieces.get(clickedPiece).x;
                                        pieces.get(clickedPiece).oldy = pieces.get(clickedPiece).y;
                                        pieces.get(clickedPiece).x = cordinator[i].xcordinateBegin + 5;
                                        if (whiteInPlace[i] < 5)
                                            pieces.get(clickedPiece).y = 46 * whiteInPlace[i] + 28;
                                        else {
                                            pieces.get(clickedPiece).y = 46 * 4 + 28;
                                            extraCordinates[i] = true;
                                        }
                                        fuck = 1;
                                        dice1.active = false;
                                    }
                                    if (fuck == 1) {
                                        whiteInPlace[i]++;
                                        whitePieceOut--;
                                    }
                                    if (dice.getRand() != dice1.getRand()) {
                                        if (dice.getRand() == 25 - i)
                                            dice.active = false;
                                        if (dice1.getRand() == 25 - i)
                                            dice1.active = false;}
                                    if (dice.getRand() == dice1.getRand()) {
                                        if (dice3.active && 25 - dice3.getRand() == i)
                                            dice3.active = false;
                                        else if (dice2.active && 25 - dice2.getRand() == i)
                                            dice2.active = false;
                                        else if (dice1.active && 25 - dice1.getRand() == i)
                                            dice1.active = false;
                                        else if (dice.active && 25 - dice.getRand() == i)
                                            dice.active = false;
                                    }
                                } else if (turn == -1) {
                                    System.out.println(2);
                                    if (dice.active && i == dice.getRand() && whiteInPlace[i] < 2 && (cordinator[i].xcordinateBegin < e.getX() && cordinator[i].xcordinateEnd > e.getX() && e.getY() > 278 && !cordinator[i].top)) {
                                        System.out.println(i);
                                        if (whiteInPlace[i] == 1) {
                                            System.out.println(3);
                                            for (int j = 16; j < 31; j++) {
                                                if (pieces.get(j).x + 5 < cordinator[i].xcordinateEnd && pieces.get(j).x + 5 > cordinator[i].xcordinateBegin && ((pieces.get(j).y > 278 && !cordinator[i].top))) { //
                                                    System.out.println(j);
                                                    pieces.get(j).oldx = pieces.get(j).x;
                                                    pieces.get(j).x = 260;
                                                    pieces.get(j).oldy = pieces.get(j).y;
                                                    pieces.get(j).y = 700 - whitePieceOut * 46;
                                                    whitePieceOut++;
                                                    whiteInPlace[i] = 0;
                                                }
                                            }
                                        }
                                        pieces.get(clickedPiece).oldx = pieces.get(clickedPiece).x;
                                        pieces.get(clickedPiece).oldy = pieces.get(clickedPiece).y;
                                        pieces.get(clickedPiece).x = cordinator[i].xcordinateBegin + 5;
                                        if (blackInPlace[i] < 5)
                                            pieces.get(clickedPiece).y = 510 - 46 * blackInPlace[i] - 46;
                                        else {
                                            pieces.get(clickedPiece).y = 510 - 46 * 4 - 46;
                                            extraCordinates[i] = true;
                                        }
                                        fuck = 1;
                                        dice.active = false;
                                    }
                                    if (dice1.active && i == dice1.getRand() && whiteInPlace[i] < 2 && (cordinator[i].xcordinateBegin < e.getX() && cordinator[i].xcordinateEnd > e.getX() && ((e.getY() < 278 && cordinator[i].top) || (e.getY() > 278 && !cordinator[i].top)))) {
                                        System.out.println(i);
                                        if (whiteInPlace[i] == 1) {
                                            for (int j = 16; j < 31; j++) {
                                                if (pieces.get(j).x + 10 < cordinator[i].xcordinateEnd && pieces.get(j).x + 10 > cordinator[i].xcordinateBegin && ((pieces.get(j).y < 278 && cordinator[i].top) || (pieces.get(j).y > 278 && !cordinator[i].top))) { //
                                                    System.out.println(j);
                                                    pieces.get(j).oldx = pieces.get(j).x;
                                                    pieces.get(j).x = 260;
                                                    pieces.get(j).oldy = pieces.get(j).y;
                                                    pieces.get(j).y = 700 - whitePieceOut * 46;
                                                    whitePieceOut++;
                                                    whiteInPlace[i] = 0;
                                                }
                                            }
                                        }
                                        pieces.get(clickedPiece).oldx = pieces.get(clickedPiece).x;
                                        pieces.get(clickedPiece).oldy = pieces.get(clickedPiece).y;
                                        pieces.get(clickedPiece).x = cordinator[i].xcordinateBegin + 5;
                                        if (blackInPlace[i] < 5)
                                            pieces.get(clickedPiece).y = 510 - 46 * blackInPlace[i] - 46;
                                        else {
                                            pieces.get(clickedPiece).y = 510 - 46 * 4 - 46;
                                            extraCordinates[i] = true;
                                        }
                                        fuck = 1;
                                        dice1.active = false;

                                    }
                                    if (fuck == 1) {
                                        blackPieceOut--;
                                        blackInPlace[i]++;

                                    }
                                    if (dice.getRand() == dice1.getRand()) {
                                        if (dice3.active && dice3.getRand() == i)
                                            dice3.active = false;
                                        else if (dice2.active && dice2.getRand() == i)
                                            dice2.active = false;
                                        else if (dice1.active && dice1.getRand() == i)
                                            dice1.active = false;
                                        else if (dice.active && dice.getRand() == i)
                                            dice.active = false;
                                    }
                                }
                            } else if (cordinator[i].xcordinateBegin < e.getX() && cordinator[i].xcordinateEnd > e.getX() && ((e.getY() < 278 && cordinator[i].top) || (e.getY() > 278 && !cordinator[i].top)) && pieces.get(clickedPiece).possibleCordinates[i] && ((pieces.get(clickedPiece).color == "white" && whitePieceOut == 0 && turn == 1) || (pieces.get(clickedPiece).color == "black" && blackPieceOut == 0 && turn == -1))) {
                                // System.out.println("خر");
                                if (pieces.get(clickedPiece).color == "white" && blackInPlace[i] == 1) {
                                    for (int j = 1; j < 16; j++) {
                                        if (pieces.get(j).x + 10 < cordinator[i].xcordinateEnd && pieces.get(j).x + 10 > cordinator[i].xcordinateBegin && ((pieces.get(j).y < 278 && cordinator[i].top) || (pieces.get(j).y > 278 && !cordinator[i].top))) { //
                                            pieces.get(j).oldx = pieces.get(j).x;
                                            pieces.get(j).x = 660;
                                            pieces.get(j).oldy = pieces.get(j).y;
                                            pieces.get(j).y = 700 - blackPieceOut * 46;
                                            blackPieceOut++;
                                            blackInPlace[i] = 0;
                                        }
                                    }
                                }

                                if (pieces.get(clickedPiece).color == "black" && whiteInPlace[i] == 1) {
                                    for (int j = 16; j < 31; j++) {
                                        if (pieces.get(j).x + 10 < cordinator[i].xcordinateEnd && pieces.get(j).x + 10 > cordinator[i].xcordinateBegin && ((pieces.get(j).y < 278 && cordinator[i].top) || (pieces.get(j).y > 278 && !cordinator[i].top))) {
                                            pieces.get(j).oldx = pieces.get(j).x;
                                            pieces.get(j).x = 260;
                                            pieces.get(j).oldy = pieces.get(j).y;
                                            pieces.get(j).y = 700 - whitePieceOut * 46;
                                            whitePieceOut++;
                                            whiteInPlace[i] = 0;
                                        }
                                    }
                                }
                                //    System.out.println(blackPieceOut+whitePieceOut);

                                pieces.get(clickedPiece).oldx = pieces.get(clickedPiece).x;
                                pieces.get(clickedPiece).x = cordinator[i].xcordinateBegin + 5;
                                temp = blackInPlace[i] + whiteInPlace[i];
                                pieces.get(clickedPiece).oldy = pieces.get(clickedPiece).y;
                                if (cordinator[i].top)
                                    if (temp < 5)
                                        pieces.get(clickedPiece).y = 46 * temp + 28;
                                    else {
                                        pieces.get(clickedPiece).y = 46 * 4 + 28;
                                        extraCordinates[i] = true;
                                    }

                                if (!cordinator[i].top)
                                    if (temp < 5)
                                        pieces.get(clickedPiece).y = 510 - 46 * temp - 46;
                                    else {
                                        pieces.get(clickedPiece).y = 510 - 46 * 4 - 46;
                                        extraCordinates[i] = true;
                                    }

                                if (pieces.get(clickedPiece).color == "white") {
                                    whiteInPlace[movedFrom]--;
                                    if (whiteInPlace[movedFrom] < 6)
                                        extraCordinates[movedFrom] = false;
                                    whiteInPlace[i]++;
                                } else if (pieces.get(clickedPiece).color == "black") {
                                    blackInPlace[movedFrom]--;
                                    if (blackInPlace[movedFrom] < 6)
                                        extraCordinates[movedFrom] = false;
                                    blackInPlace[i]++;
                                }
                                if (dice.getRand() != dice1.getRand()) {
                                    if (dice.getRand() == i)
                                        dice.active = false;
                                    if (dice1.getRand() == i)
                                        dice1.active = false;}
                                if (dice.getRand() == dice1.getRand()) {
                                    if (dice3.active && dice3.getRand() == i)
                                        dice3.active = false;
                                    else if (dice2.active && dice2.getRand() == i)
                                        dice2.active = false;
                                    else if (dice1.active && dice1.getRand() == i)
                                        dice1.active = false;
                                    else if (dice.active && dice.getRand() == i)
                                        dice.active = false;
                                }
                            }
                        }
                    }
                    if (whiteEnd && turn==1){
                        counter = 0;
                        if(e.getX()>840 && e.getX()< 890&& e.getY()>278){
                            if (dice.getRand()==movedFrom && dice.active){
                                impossiblePieces[clickedPiece] = false;
                                possiblePieces[clickedPiece] = false;
                                whiteInPlace[movedFrom]--;
                                whiteWin--;
                                dice.active = false;
                                repaint();
                            }
                            else if (dice.getRand() > movedFrom && dice.active){
                                for (int i = movedFrom+1; i < 7 ; i++) {
                                    if (whiteInPlace[i] != 0)
                                        counter++;
                                }
                                if (counter == 0) {
                                    impossiblePieces[clickedPiece] = false;
                                    possiblePieces[clickedPiece] = false;
                                    whiteInPlace[movedFrom]--;
                                    whiteWin--;
                                    dice.active = false ;
                                    repaint();
                                }
                            }

                            if (dice1.getRand()==movedFrom && dice1.active){
                                impossiblePieces[clickedPiece] = false;
                                possiblePieces[clickedPiece] = false;
                                whiteInPlace[movedFrom]--;
                                whiteWin--;
                                dice1.active = false;
                                repaint();
                            }
                            else if (dice1.getRand() > movedFrom && dice1.active){
                                for (int i = movedFrom+1; i < 7 ; i++) {
                                    if (whiteInPlace[i] != 0)
                                        counter++;
                                }
                                if (counter == 0) {
                                    impossiblePieces[clickedPiece] = false;
                                    possiblePieces[clickedPiece] = false;
                                    whiteInPlace[movedFrom]--;
                                    whiteWin--;
                                    dice1.active = false ;
                                    repaint();
                                }
                            }
                            else if (dice.getRand() == dice1.getRand()){
                                dice.active=true;
                                dice1.active=true;
                                dice3.active=false;
                                dice2.active=false;
                                if (dice.getRand()==movedFrom && dice.active){
                                    impossiblePieces[clickedPiece] = false;
                                    possiblePieces[clickedPiece] = false;
                                    whiteInPlace[movedFrom]--;
                                    whiteWin--;
                                    dice.active = false;
                                    repaint();
                                }
                                else if (dice.getRand() > movedFrom && dice.active){
                                    for (int i = movedFrom+1; i < 7 ; i++) {
                                        if (whiteInPlace[i] != 0)
                                            counter++;
                                    }
                                    if (counter == 0) {
                                        impossiblePieces[clickedPiece] = false;
                                        possiblePieces[clickedPiece] = false;
                                        whiteInPlace[movedFrom]--;
                                        whiteWin--;
                                        dice.active = false ;
                                        repaint();
                                    }
                                }

                                if (dice1.getRand()==movedFrom && dice1.active){
                                    impossiblePieces[clickedPiece] = false;
                                    possiblePieces[clickedPiece] = false;
                                    whiteInPlace[movedFrom]--;
                                    whiteWin--;
                                    dice1.active = false;
                                    repaint();
                                }
                                else if (dice1.getRand() > movedFrom && dice1.active){
                                    for (int i = movedFrom+1; i < 7 ; i++) {
                                        if (whiteInPlace[i] != 0)
                                            counter++;
                                    }
                                    if (counter == 0) {
                                        impossiblePieces[clickedPiece] = false;
                                        possiblePieces[clickedPiece] = false;
                                        whiteInPlace[movedFrom]--;
                                        whiteWin--;
                                        dice1.active = false ;
                                        repaint();
                                    }
                                }
                            }

                        }
                    }
                    else if (blackEnd && turn==-1){

                        counter = 0;
                        if(e.getX()>840 && e.getX()< 890&& e.getY()<278){
                            if (dice.getRand()==25-movedFrom && dice.active){
                                impossiblePieces[clickedPiece] = false;
                                possiblePieces[clickedPiece] = false;
                                blackInPlace[movedFrom]--;
                                blackWin--;
                                dice.active = false;
                                repaint();
                            }
                            else if (dice.getRand() > 25-movedFrom && dice.active){
                                for (int i = movedFrom-1; i > 18 ; i--) {
                                    if (blackInPlace[i] != 0)
                                        counter++;
                                }
                                if (counter == 0) {
                                    impossiblePieces[clickedPiece] = false;
                                    possiblePieces[clickedPiece] = false;
                                    blackInPlace[movedFrom]--;
                                    blackWin--;
                                    dice.active = false ;
                                    repaint();
                                }
                            }

                            if (dice1.getRand()==25-movedFrom && dice1.active){
                                impossiblePieces[clickedPiece] = false;
                                possiblePieces[clickedPiece] = false;
                                blackInPlace[movedFrom]--;
                                blackWin--;
                                dice1.active = false;
                                repaint();
                            }
                            else if (dice1.getRand() > 25-movedFrom && dice1.active){
                                for (int i = movedFrom-1; i > 18 ; i--) {
                                    if (blackInPlace[i] != 0)
                                        counter++;
                                }
                                if (counter == 0) {
                                    impossiblePieces[clickedPiece] = false;
                                    possiblePieces[clickedPiece] = false;
                                    blackInPlace[movedFrom]--;
                                    blackWin--;
                                    dice1.active = false ;
                                    repaint();
                                }
                            }
                            else if (dice.getRand() == dice1.getRand()){
                                dice.active=true;
                                dice1.active=true;
                                dice3.active=false;
                                dice2.active=false;
                                if (dice.getRand()==25-movedFrom && dice.active){
                                    impossiblePieces[clickedPiece] = false;
                                    possiblePieces[clickedPiece] = false;
                                    blackInPlace[movedFrom]--;
                                    blackWin--;
                                    dice.active = false;
                                    repaint();
                                }
                                else if (dice.getRand() > 25-movedFrom && dice.active){
                                    for (int i = movedFrom-1; i > 18 ; i--) {
                                        if (blackInPlace[i] != 0)
                                            counter++;
                                    }
                                    if (counter == 0) {
                                        impossiblePieces[clickedPiece] = false;
                                        possiblePieces[clickedPiece] = false;
                                        blackInPlace[movedFrom]--;
                                        blackWin--;
                                        dice.active = false ;
                                        repaint();
                                    }
                                }

                                if (dice1.getRand()==25-movedFrom && dice1.active){
                                    impossiblePieces[clickedPiece] = false;
                                    possiblePieces[clickedPiece] = false;
                                    blackInPlace[movedFrom]--;
                                    blackWin--;
                                    dice1.active = false;
                                    repaint();
                                }
                                else if (dice1.getRand() > 25-movedFrom && dice1.active){
                                    for (int i = movedFrom-1; i >18 ; i--) {
                                        if (blackInPlace[i] != 0)
                                            counter++;
                                    }
                                    if (counter == 0) {
                                        impossiblePieces[clickedPiece] = false;
                                        possiblePieces[clickedPiece] = false;
                                        blackInPlace[movedFrom]--;
                                        blackWin--;
                                        dice1.active = false ;
                                        repaint();
                                    }
                                }
                            }

                        }

                    }


                }
                System.out.println(clickedPiece);
                for (int i = 1; i <31 ; i++) {
                    System.out.println(impossiblePieces[i]);
                }
//                System.out.println(possiblePieces[clickedPiece]);
//                System.out.println(clickedPiece);
                repaint();
//                if (turn==1 && pieces.get(clickedPiece).x==pieces.get(clickedPiece).oldx && pieces.get(clickedPiece).y==pieces.get(clickedPiece).oldy)
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
//        if (!dice1.active)
//
        dice.paint((Graphics2D) g);
        dice1.paint((Graphics2D) g);
        //g.drawString("ssss",450,278);
        g.setColor(GREEN);
        g.fillPolygon(triangle);
        g.fillPolygon(triangle1);

//        g.set(.5);
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

        if(dice.getBegin()==1&&dice.getRand()==dice1.getRand()){
            dice2.setRand(dice.getRand());
            dice3.setRand(dice1.getRand());
            dice2.paint((Graphics2D) g);
            dice3.paint((Graphics2D) g);
        }

        for (int i = 1; i < 31; i++) {
            if(pieces.get(i).color=="black" && impossiblePieces[i]){
                g.setColor(Color.BLACK);
                g.drawImage(blackPieceNotHighlighted, pieces.get(i).x, pieces.get(i).y, null);
            }
            else if(pieces.get(i).color=="black" && !impossiblePieces[i]){
                for (int j = 1; j <16-blackWin ; j++) {
                    g.drawRect(840, 215-(j)*13, 50, 10);
                    g.fillRect(840, 215-(j)*13, 50, 10);
                }
            }
            else if(pieces.get(i).color=="white" && impossiblePieces[i]) {
                g.setColor(Color.YELLOW);
                g.drawImage(whitePieceNotHighlighted, pieces.get(i).x, pieces.get(i).y, null);
            }
            else if(pieces.get(i).color=="white" && !impossiblePieces[i]){
                for (int j = 1; j <16-whiteWin ; j++) {
                    g.drawRect(840, 510-(j)*13, 50, 10);
                    g.fillRect(840, 510-(j)*13, 50, 10);
                }
            }

        }

        g.setColor(YELLOW);
        g.setFont(new Font("test1" , Font.ITALIC , 10));
        g.drawString("white hitted pieces: "+ whitePieceOut, 230,600 );
        g.drawString("white out pieces: "+ (15-whiteWin), 230,630 );
        g.drawString("black hitted pieces: "+ blackPieceOut, 630,600 );
        g.drawString("black out pieces: "+ (15-blackWin), 630,630 );
        g.drawString(Integer.toString(whiteDice), 230, 660);
        g.drawString(Integer.toString(blackDice), 630, 660);

        g.setColor(GREEN);
        g.setFont(new Font("test2" , Font.BOLD , 30));
        if (whiteWin==0) {
            g.drawString("white won!", 350, 250);
            if(blackEnd){
                int k = blackInPlace[19]*6 + blackInPlace[20]*5+blackInPlace[21]*4+blackInPlace[22]*3+blackInPlace[23]*2+blackInPlace[24];
                g.drawString("score: "+k, 350, 280);
            }
            else
                g.drawString("score: 90", 350, 280);
        }
        if (blackWin==0) {
            g.drawString("black won!", 350, 250);
            if(whiteEnd){
                int k = whiteInPlace[6]*6 + whiteInPlace[5]*5+whiteInPlace[4]*4+whiteInPlace[3]*3+whiteInPlace[2]*2+whiteInPlace[1];
                g.drawString("score: "+k, 350, 280);
            }
            else
                g.drawString("score: 90", 350, 280);
        }



        for (int i = 1; i <25 ; i++) {
            temp = whiteInPlace[i] + blackInPlace[i];
            if (extraCordinates[i] && cordinator[i].top && temp-5>0)
                g.drawString(Integer.toString(temp-5), cordinator[i].xcordinateMean-5, 46 * 5 + 28 -10);
            if (extraCordinates[i] && !cordinator[i].top && temp-5>0)
                g.drawString(Integer.toString(temp-5), cordinator[i].xcordinateMean-5, 510 - 46 * 5 +35);
        }

        if (turn == 1)
            g.drawString("white turn", 400, 600);
//        else if (turn == 1 && !canMoveWhite)
//            g.drawString("white cannot move", 400, 600);
        if (turn == -1)
            g.drawString("black turn", 400, 600);
//        else if (turn == -1 && canMoveBlack)
//            g.drawString("black cannot move", 400, 600);

    }

}
